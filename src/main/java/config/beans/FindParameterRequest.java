package config.beans;

import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author savin
 */
@Named
@RequestScoped
public class FindParameterRequest implements Serializable {

    private Long paramID;

    public Long getParamID() {
        return paramID;
    }

    public void setParamID(Long paramID) {
        this.paramID = paramID;
    }

}
