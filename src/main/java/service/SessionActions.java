package service;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

/**
 *
 * @author savin
 */
@Named
@SessionScoped
public class SessionActions implements Serializable {
//
//    @Resource
//    private SessionContext ctx;

    public String logout() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        session.invalidate();
        return "/login.xhtml";
    }

}
