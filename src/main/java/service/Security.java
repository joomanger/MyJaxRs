package service;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

/**
 *
 * @author savin
 */
@Interceptor
@Secure
public class Security {

    @Inject
    private SessionConfig sc;

    @AroundInvoke
    public Object logMethod(InvocationContext ic) throws Exception {
        String action = ic.getTarget().getClass().getSimpleName().replace("$Proxy$_$$_WeldSubclass", "") + "." + ic.getMethod().getName();
        String user = sc.getUserName();
        System.out.println("user="+user+" entered to " + action);
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
            if ((action).equals("ConfigCBean.deleteConfigs")) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Нет доступа", null));
                return null;
            }
            return ic.proceed();
        } finally {
            System.out.println("exiting from " + ic.getMethod().getName());
        }
    }
}
