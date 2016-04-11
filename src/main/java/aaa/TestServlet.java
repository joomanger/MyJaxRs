package aaa;

import config.entity.ParameterConfiguration;
import java.util.Enumeration;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author savin
 */
@Named
@RequestScoped
public class TestServlet extends HttpServlet {

    public void test() {
        HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String a;
        for (Enumeration e = req.getParameterNames(); e.hasMoreElements();) {
            a=e.nextElement().toString();
            if(a.endsWith("_input")){
                System.out.println("parameter="+a+"; value="+req.getParameter(a));
            }else if(a.startsWith(ParameterConfiguration.ParameterType.INTEGER.name())){
                System.out.println("int parameter="+a+"; value="+req.getParameter(a));
            }else if(a.startsWith(ParameterConfiguration.ParameterType.DOUBLE.name())){
                System.out.println("dbl parameter="+a+"; value="+req.getParameter(a));
            }else if(a.startsWith(ParameterConfiguration.ParameterType.STRING.name())){
                System.out.println("str parameter="+a+"; value="+req.getParameter(a));
            }
        }
    }
}
