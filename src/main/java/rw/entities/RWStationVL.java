package rw.entities;

/**
 *
 * @author savin
 */
public class RWStationVL extends RWStation {

    private String name;
    

    public RWStationVL() {
    }

    public RWStationVL(String rws_code, String name, RWRoad rwroad) {
        this.rws_code = rws_code;
        this.name = name;
        this.rwroad = rwroad;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
