package entities.rw;

import service.VLIDField;

/**
 *
 * @author savin
 */
public class RWStationVL {
    @VLIDField
    private String rws_code;
    private String rws_name;
    private String rwr_name;

    public RWStationVL() {
    }

    public RWStationVL(String rws_code, String rws_name, String rwr_name) {
        this.rws_code = rws_code;
        this.rws_name = rws_name;
        this.rwr_name = rwr_name;
    }

    public String getRws_code() {
        return rws_code;
    }

    public void setRws_code(String rws_code) {
        this.rws_code = rws_code;
    }

    public String getRws_name() {
        return rws_name;
    }

    public void setRws_name(String rws_name) {
        this.rws_name = rws_name;
    }

    public String getRwr_name() {
        return rwr_name;
    }

    public void setRwr_name(String rwr_name) {
        this.rwr_name = rwr_name;
    }

    

}
