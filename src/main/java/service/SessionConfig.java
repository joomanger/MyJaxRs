package service;

import java.io.Serializable;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.SessionContext;



/**
 *
 * @author savin ПЕРЕПИСАТЬ, НЕ НУЖЕН СИНГЛЕТОН!!!
 */
//@Stateless
public class SessionConfig implements Serializable{
    @Resource
    private SessionContext ctx;

//    @PostConstruct
//    @SuppressWarnings("PMD.UnusedPrivateMethod")
//    private void init() {
//        InitialContext ic;
//        try {
//            ic = new InitialContext();
//            ctx = (SessionContext) ic.lookup("java:comp/EJBContext");
//        } catch (NamingException ex) {
////            Logger.getLogger(SessionConfig.class.getName()).log(Level.SEVERE, null, ex);
//            System.out.println(ex);
//        }
//
//    }

    public SessionContext getSessionContext() {
        return ctx;
    }

    @SuppressWarnings("PMD.UnusedPrivateMethod")
    @PreDestroy
    private void preDestroy() {
        ctx = null;
    }
}
