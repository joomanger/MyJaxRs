/*

 */
package beans.rw;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author savin
 */
@Named
@SessionScoped
public class FindRWStationSession implements Serializable {

    private String rws_code;

    public String getRws_code() {
        return rws_code;
    }

    public void setRws_code(String rws_code) {
        this.rws_code = rws_code;
    }

}
