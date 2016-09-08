package rw.entities;

/**
 *
 * @author savin
 */
public class RWStationVL extends RWStation {

    //private String name;
    private String rwr_code;
    private String fullname;
    private String shortname;
    private String country_id;

    public RWStationVL() {
    }

    public RWStationVL(String rws_code, String name, String rwr_code, String fullname, String shortname, String country_id) {
        this.rws_code = rws_code;
        this.name = name;
        this.rwr_code = rwr_code;
        this.fullname = fullname;
        this.shortname = shortname;
        this.country_id = country_id;
    }

//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }

    public String getRwr_code() {
        return rwr_code;
    }

    public void setRwr_code(String rwr_code) {
        this.rwr_code = rwr_code;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
    }

    public String getCountry_id() {
        return country_id;
    }

    public void setCountry_id(String country_id) {
        this.country_id = country_id;
    }

}
