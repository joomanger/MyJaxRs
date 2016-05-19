package service;

import java.lang.annotation.Annotation;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
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

    @Resource
    private SessionContext ctx;
//    @AroundConstruct
//    private void init(InvocationContext ic) throws Exception {
//        logger.fine("Entering constructor");
//        try {
//            ic.proceed();
//        } finally {
//            logger.fine("Exiting constructor");
//        }
//    }

    @AroundInvoke
    public Object logMethod(InvocationContext ic) throws Exception {
        System.out.println("ctx=" + ctx.getCallerPrincipal().getName());
        System.out.println("enter to "+ic.getTarget().getClass().getSimpleName()+"."+ic.getMethod().getName());
        for (Annotation a : ic.getMethod().getDeclaredAnnotations()) {
            System.out.println("Annot annotationType.getName=" + a.annotationType().getName() + " getTypeName=" + a.annotationType().getTypeName() + " simpleName=" + a.annotationType().getSimpleName() + " canonicalName=" + a.annotationType().getCanonicalName());
            if (a.annotationType().getSimpleName().equals("Who")) {
                System.out.println("I'm into Who");
                for (String role : ic.getMethod().getAnnotation(Who.class).value()) {
                    System.out.println("role=" + role);
                }
            }
        }
        try {
            return ic.proceed();
        } finally {
            System.out.println("exiting from " + ic.getMethod().getName());
        }
    }
}
