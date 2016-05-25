package service;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import sys.beans.UserCBean;
import sys.entities.SysUser;

/**
 *
 * @author savin
 */
@Named
@SessionScoped
public class SessionActions implements Serializable {

    @Inject
    private SessionConfig sc;
    @Inject
    private UserCBean client;

    private SysUser user;

    @PostConstruct
    private void init() {
        System.out.println("init() into SessionActions");
        user = client.findUserByUserName(sc.getSessionContext().getCallerPrincipal().getName());
    }

    public SysUser getCurrentUser() {
        return user;
    }

    public String logout() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        session.invalidate();
        return "/login.xhtml";
    }

}
