package beans.so.config;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import entities.so.config.ConfigurationLine;

/**
 *
 * @author savin
 */
@Named
@RequestScoped
public class ConfigLineCBean {

    @Inject
    private ConfigLineEJB ejb;

    public void editItem(ConfigurationLine cl) {
        String status=ejb.edit(cl);
        ejb.sendMessage(status, "Строка "+cl.getLine_num()+" сохранена");
        //ejb.sendMessage(status, );
    }
}
