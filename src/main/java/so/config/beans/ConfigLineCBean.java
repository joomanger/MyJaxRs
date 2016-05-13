package so.config.beans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import so.config.entity.ConfigurationLine;

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
