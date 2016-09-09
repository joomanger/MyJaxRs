package customer.entities;

/**
 *
 * @author savin
 */
public class RWAddressVL extends RWAddress {

    private String rws_code;
    private String rws_name;

    public RWAddressVL() {

    }

    public RWAddressVL(Long rwaddress_id, String rws_code, String rwrcvcode, String rwbranch, Boolean activestatus, String rws_name) {
        this.rwaddress_id = rwaddress_id;
        this.rws_code = rws_code;
        this.rwbranch = rwbranch;
        this.rws_name = rws_name;
        this.activeStatus = activestatus;
        this.rwrcvcode = rwrcvcode;
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

}
