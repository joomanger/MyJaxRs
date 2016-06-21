package service;

import java.io.Serializable;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author savin
 */
@Interceptor
@Secure
public class Security implements Serializable{

    @Inject
    private SessionActions sc;

    @AroundInvoke
    public Object logMethod(InvocationContext ic) throws Exception {
        String action = ic.getTarget().getClass().getSimpleName().replace("$Proxy$_$$_WeldSubclass", "") + "." + ic.getMethod().getName();
        String user = sc.getCurrentUser().getUsername();
        System.out.println("user="+user+" entered to " + action);
        HttpServletRequest req=(HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
        System.out.println("getPathInfo()="+req.getPathInfo());
        System.out.println("getServletPath()="+req.getServletPath());
        
//        for (Annotation a : ic.getMethod().getDeclaredAnnotations()) {
//            System.out.println("Annot annotationType.getName=" + a.annotationType().getName() + " getTypeName=" + a.annotationType().getTypeName() + " simpleName=" + a.annotationType().getSimpleName() + " canonicalName=" + a.annotationType().getCanonicalName());
//            if (a.annotationType().getSimpleName().equals("Who")) {
//                System.out.println("I'm into Who");
//                for (String role : ic.getMethod().getAnnotation(Who.class).value()) {
//                    System.out.println("role=" + role);
//                }
//            }
//        }
        try { 
            
//            System.out.println("before if");
//            for(Map.Entry<String, String> e:sc.getViewsMap().entrySet()){
//                System.out.println("-------------------");
//                System.out.println(e.getKey()+" "+e.getValue());
//            }
            if ((!sc.getViewsMap().containsValue(req.getPathInfo().replace(".xhtml", "")))&&(!sc.getCurrentUser().getUsername().equals("admin"))) {
//                System.out.println("Access denied!");
                HttpServletResponse res=(HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();
                try{
                res.sendRedirect(req.getContextPath()+"/faces/index.xhtml");
                }catch(IllegalStateException ex){
                    System.out.println("error: "+ex.getMessage());
                }
            }
            return ic.proceed();
        } finally {
            System.out.println("exiting from " + ic.getMethod().getName());
        }
    }
}
