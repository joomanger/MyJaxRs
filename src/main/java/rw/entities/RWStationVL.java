package rw.entities;

/**
 *
 * @author savin
 */
public class RWStationVL extends RWStation {

    private String name;
    private String description;

    public RWStationVL() {
    }

    public RWStationVL(String rws_code, String name, String description, RWRoad rwroad) {
        this.rws_code = rws_code;
        this.name = name;
        this.description = description;
        this.rwroad = rwroad;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
