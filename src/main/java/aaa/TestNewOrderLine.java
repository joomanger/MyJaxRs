package aaa;

import com.isd.myjaxrs.entity.SaleOrderLine;
import config.beans.ConfigClientBean;
import config.beans.ConfigLineClientBean;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import saleorderline.beans.SaleOrderLineBean;

/**
 *
 * @author savin
 */
@Named
@RequestScoped
public class TestNewOrderLine {

    @Inject
    private SaleOrderLineBean clientLine;
    @Inject
    private ViewBean viewBean;
    @Inject
    private ConfigClientBean configBean;
    @Inject
    private ConfigLineClientBean configLineBean;

    public String test() {
        HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        //Соберем строку SO
        SaleOrderLine line = viewBean.getOrder_line();
        line.setHeader_id(1l);
        line.setItem(viewBean.getItem());
        line.setConfig_ver_num(viewBean.getLastConfigVersion());

        String a;
        //Формат ID параметра: ТИППАРАМЕТРА_PARAMETERHEADERID_COLUMNMAPPING_input(or not imput)
        Pattern p = Pattern.compile("(TABLE|INTEGER|DOUBLE|STRING)_[0-9]+_(ATTRIBUTE)[0-9]+(_input|)");

        //начинаем перебирать все параметры которые соответствуют нашему шаблону
        for (Enumeration e = req.getParameterNames(); e.hasMoreElements();) {
            a = e.nextElement().toString();
            Matcher m = p.matcher(a);
            boolean b = m.matches();

            if (b) {
                StringTokenizer st = new StringTokenizer(a, "_");
                int i = 0;
                String paramColumn = "";
                while (st.hasMoreTokens()) {
                    i++;
                    switch (i) {
                        case 3:
                            paramColumn = st.nextToken();
                            break;
                        default:
                            st.nextToken();
                            break;
                    }
                }
                String value = "";
                if (a.endsWith("_input")) {
                    String aa;
                    for (Enumeration ee = req.getParameterNames(); ee.hasMoreElements();) {
                        aa = ee.nextElement().toString();
                        if (aa.equals(a.replace("_input", "_hinput"))) {
                            value = req.getParameter(aa);
                        }
                    }
                } //                else if (a.startsWith(ParameterConfiguration.ParameterType.INTEGER.name())) {
                //                    System.out.println("int parameter_id=" + a + "; value=" + req.getParameter(a));
                //                } else if (a.startsWith(ParameterConfiguration.ParameterType.DOUBLE.name())) {
                //                    System.out.println("dbl parameter_id=" + a + "; value=" + req.getParameter(a));
                //                } else if (a.startsWith(ParameterConfiguration.ParameterType.STRING.name())) {
                //                    System.out.println("str parameter_id=" + a + "; value=" + req.getParameter(a));
                //                } 
                else {
                    value = req.getParameter(a);
                }
                for (Method met : line.getClass().getDeclaredMethods()) {
                    if ((met.getName().replace("set", "").toUpperCase().equals(paramColumn.toUpperCase())) && (!value.isEmpty())) {
                        //System.out.println(met.getName());
                        try {
                            met.invoke(line, value);
                        } catch (IllegalAccessException | InvocationTargetException ex) {
                            System.out.println(ex.getMessage());
                        }
                    }
                }
            }
        }

          clientLine.addItem(line, "Строка успешно добавлена");
        
        UIViewRoot view = FacesContext.getCurrentInstance().getViewRoot();
        return view.getViewId() + "?faces-redirect=true";

    }
}
