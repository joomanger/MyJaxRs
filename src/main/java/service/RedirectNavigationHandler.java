package service;

import java.util.Map;
import java.util.Set;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.application.NavigationCase;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import sys.beans.ViewEJB;
import sys.entities.View;

/**
 *
 * @author savin
 */
public class RedirectNavigationHandler extends ConfigurableNavigationHandler {
    @Inject
    private SessionActions sc;
//    @Inject
//    private ViewEJB ejb;
    
    private NavigationHandler parent;

    public RedirectNavigationHandler(NavigationHandler parent) {
        this.parent = parent;
    }

    @Override
    public void handleNavigation(FacesContext context, String from, String outcome) {
        if (outcome != null) {
            System.out.println(sc.getCurrentUser().getUsername()+" outcome="+outcome);
//            for(View v:ejb.findViewsByUserName(sc.getCurrentUser().getUsername())){
//                System.out.println(v.getViewName()+" "+v.getUrl());
//            };
            if((sc.getViewsMap().containsKey(outcome))||(sc.getViewsMap().containsValue(outcome))){
                System.out.println("ACCESS ALLOWED");
            }else{
                System.out.println("ACCESS DENIED");
                outcome="/index.xhtml";
            }
                
            if (!outcome.endsWith("?faces-redirect=true")) {
                outcome += "?faces-redirect=true";
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
