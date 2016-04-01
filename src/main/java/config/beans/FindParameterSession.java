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
public class FindParameterSession implements Serializable {

    private Long paramID;

    public Long getParamID() {
        return paramID;
    }

    public void setParamID(Long paramID) {
        this.paramID = paramID;
    }

}
