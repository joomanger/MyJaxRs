package service;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author savin
 */
@Interceptor
@Secure
public class Security implements Serializable {

    @Inject
    private SessionActions sc;

    @Inject
    private Logger log;

    @AroundInvoke
    public Object logMethod(InvocationContext ic) throws Exception {
        HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();

        if (FacesContext.getCurrentInstance().getExternalContext().getInitParameter("logging").equals("true")) {
            String action = ic.getTarget().getClass().getSimpleName().replace("$Proxy$_$$_WeldSubclass", "") + "." + ic.getMethod().getName();
            log.log(Level.INFO, "User [{0}] entered to {1}", new Object[]{sc.getCurrentUser().getUsername(), action});
//        }
//        for (Annotation a : ic.getMethod().getDeclaredAnnotations()) {
//            System.out.println("Annot annotationType.getName=" + a.annotationType().getName() + " getTypeName=" + a.annotationType().getTypeName() + " simpleName=" + a.annotationType().getSimpleName() + " canonicalName=" + a.annotationType().getCanonicalName());
//            if (a.annotationType().getSimpleName().equals("Who")) {
//                System.out.println("I'm into Who");
//                for (String role : ic.getMethod().getAnnotation(Who.class).value()) {
//                    System.out.println("role=" + role);
//                }
//            }
        }
        try {

            if (!sc.getViewsMap().containsValue(req.getPathInfo().replace(".xhtml", "")) && !sc.getCurrentUser().getUsername().equals("admin")) {
                //log.log(Level.SEVERE, "For user [{0}] access denied into {1}", new Object[]{sc.getCurrentUser().getUsername(), req.getPathInfo()});
                throw new AccessDeniedException(req.getPathInfo());
            }
            return ic.proceed();
        } finally {
            if (FacesContext.getCurrentInstance().getExternalContext().getInitParameter("logging").equals("true")) {
                log.log(Level.INFO, "User [{0}] exiting from  {1}", new Object[]{sc.getCurrentUser().getUsername(), ic.getMethod().getName()});

            }
        }

    }

}
