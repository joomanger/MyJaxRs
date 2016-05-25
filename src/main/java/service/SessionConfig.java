package service;

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
}
