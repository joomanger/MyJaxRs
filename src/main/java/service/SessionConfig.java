package service;

import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Singleton;

/**
 *
 * @author savin
 */
@Singleton
public class SessionConfig {

    @Resource
    private SessionContext ctx;

    public SessionContext getSessionContext() {
        return ctx;
    }
    @SuppressWarnings("PMD.UnusedPrivateMethod")
    @PreDestroy
    private void preDestroy() {
        ctx = null;
    }
}
