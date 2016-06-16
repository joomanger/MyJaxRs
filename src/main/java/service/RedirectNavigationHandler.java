package service;

import java.util.Map;
import java.util.Set;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.application.NavigationCase;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

/**
 *
 * @author savin
 */
public class RedirectNavigationHandler extends ConfigurableNavigationHandler {

    @Inject
    private SessionActions sc;
    private HttpSession session;

    private NavigationHandler parent;

    public RedirectNavigationHandler(NavigationHandler parent) {
        this.parent = parent;
    }

    @Override
    public void handleNavigation(FacesContext context, String from, String outcome) {
        System.out.println("dsf;lkjdslkjldsjf");
        session = (HttpSession) context.getExternalContext().getSession(false);
        if (outcome != null) {
            if ((sc.getViewsMap().containsKey(outcome)) || (sc.getViewsMap().containsValue(outcome)) || (outcome.contains("login.xhtml")) || (outcome.contains("error.xhtml")) || (sc.getCurrentUser().getUsername().equals("admin"))) {
              
                if (!outcome.endsWith("?faces-redirect=true")) {
                    outcome += "?faces-redirect=true";
                }
                session.setAttribute("access", "yes");
//                System.out.println("outcome=" + outcome);
                
//
            } else {
                session.setAttribute("access", null);
//                System.out.println("outcome=" + outcome);
//                sc.sendMessage("Доступ закрыт!", null);
                outcome = /*"/index.xhtml?faces-redirect=true"*/null;
            }
        }

        parent.handleNavigation(context, from, outcome);
    }

    @Override
    public NavigationCase getNavigationCase(FacesContext context, String fromAction, String outcome) {
        if (parent instanceof ConfigurableNavigationHandler) {
            return ((ConfigurableNavigationHandler) parent).getNavigationCase(context, fromAction, outcome);
        } else {
            return null;
        }
    }

    @Override
    public Map<String, Set<NavigationCase>> getNavigationCases() {
        if (parent instanceof ConfigurableNavigationHandler) {
            return ((ConfigurableNavigationHandler) parent).getNavigationCases();
        } else {
            return null;
        }
    }

}
