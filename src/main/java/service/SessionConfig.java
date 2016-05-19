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

    public String getUserName() {
        return ctx.getCallerPrincipal().getName();
    }
}
