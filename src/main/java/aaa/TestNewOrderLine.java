package aaa;

import config.entity.ParameterConfiguration;
import java.util.Enumeration;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author savin
 */
@Named
@RequestScoped
public class TestNewOrderLine{
    
    public String test() {
        HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String a;
        for (Enumeration e = req.getParameterNames(); e.hasMoreElements();) {
            a = e.nextElement().toString();
            if (a.endsWith("_input")) {
                System.out.print("parameter_id=" + a + "; value_name=" + req.getParameter(a));
                String aa;
                for (Enumeration ee = req.getParameterNames(); ee.hasMoreElements();) {
                    aa = ee.nextElement().toString();
                    if (aa.equals(a.replace("_input", "_hinput"))) {
                        System.out.println("; value_id=" + req.getParameter(aa));
                    }
                }
            } else if (a.startsWith(ParameterConfiguration.ParameterType.INTEGER.name())) {
                System.out.println("int parameter_id=" + a + "; value=" + req.getParameter(a));
            } else if (a.startsWith(ParameterConfiguration.ParameterType.DOUBLE.name())) {
                System.out.println("dbl parameter_id=" + a + "; value=" + req.getParameter(a));
            } else if (a.startsWith(ParameterConfiguration.ParameterType.STRING.name())) {
                System.out.println("str parameter_id=" + a + "; value=" + req.getParameter(a));
            }
        }
        
        UIViewRoot view = FacesContext.getCurrentInstance().getViewRoot();
        return view.getViewId() + "?faces-redirect=true";
    }
}
