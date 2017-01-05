package service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import beans.lookup.LookupEJB;
import entities.lookup.Lookup;
import entities.lookup.LookupItem;
import beans.sys.UserCBean;
import beans.sys.ViewEJB;
import entities.sys.SysUser;
import entities.sys.View;
import java.util.Calendar;

/**
 *
 * @author savin
 */
@Named
@SessionScoped
public class SessionActions implements Serializable {

//    @Inject
//    private SessionConfig sc;
    @Inject
    private LookupEJB lookupEJB;
    @Inject
    private UserCBean client;
    @Inject
    private ViewEJB ejb;

    private SysUser user;

    private String language = "RU";

    private final Map<String, String> viewsMap = new HashMap<>();

    @SuppressWarnings("PMD.UnusedPrivateMethod")
    @PostConstruct
    private void init() {
        user = client.findUserByUserName(FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName());
        updateViewSecurity();
    }

    public SysUser getCurrentUser() {
        return user;
    }

    public String logout() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        session.invalidate();
        return "/index.xhtml";
    }

    public Map<String, String> getViewsMap() {
        return viewsMap;
    }

    public void updateViewSecurity() {
        if (user != null) {
            viewsMap.clear();
            for (View v : ejb.findViewsByUserName(user.getUsername())) {
                viewsMap.put(v.getViewName(), v.getUrl());
            }
        }
    }

    public void sendMessage(String status, String success) {
        ejb.sendMessage(status, success);
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public List<String> getSystemLanguages() {
        Lookup langs = lookupEJB.findByName("LANGUAGES");
        List<String> res = new ArrayList<>(langs.getLookupItems().size());
        for (LookupItem li : langs.getLookupItems()) {
            res.add(li.getValuez());
        }
        return res;
    }

    public void createWHO(WhoIS whoIS) {
        whoIS.setCreatedBy(getCurrentUser().getUser_id());
        whoIS.setCreationDate(Calendar.getInstance().getTime());
        whoIS.setLastUpdatedBy(getCurrentUser().getUser_id());
        whoIS.setLastUpdateDate(Calendar.getInstance().getTime());
    }

    public void updateWHO(WhoIS whoIS) {
        whoIS.setLastUpdatedBy(getCurrentUser().getUser_id());
        whoIS.setLastUpdateDate(Calendar.getInstance().getTime());
    }

}
