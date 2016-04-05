package config.beans;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author savin
 */
@Named
@SessionScoped
public class FindConfigSession implements Serializable {

    private Long config_id;

    public Long getConfig_id() {
        return config_id;
    }

    public void setConfig_id(Long config_id) {
        this.config_id = config_id;
    }

}
