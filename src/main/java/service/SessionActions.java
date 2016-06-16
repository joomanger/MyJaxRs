package service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import sys.beans.UserCBean;
import sys.beans.ViewEJB;
import sys.entities.SysUser;
import sys.entities.View;

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
    @Inject
    private ViewEJB ejb;

    private SysUser user;

    private Map<String, String> viewsMap = new HashMap<>();

    @PostConstruct
    private void init() {
        System.out.println("init() into SessionActions");
        user = client.findUserByUserName(sc.getSessionContext().getCallerPrincipal().getName());
        updateViewSecurity();
    }

    public SysUser getCurrentUser() {
        return user;
    }

    public String logout() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        session.invalidate();
        return "/login.xhtml";
    }

    public Map<String, String> getViewsMap() {
        return viewsMap;
    }

    public void updateViewSecurity() {
        viewsMap.clear();
        for (View v : ejb.findViewsByUserName(user.getUsername())) {
            viewsMap.put(v.getViewName(), v.getUrl());
        }
    }
    
    public void sendMessage(String status,String success){
        ejb.sendMessage(status, success);
    }

}
