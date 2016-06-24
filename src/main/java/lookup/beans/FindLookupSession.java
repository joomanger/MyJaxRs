package lookup.beans;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author savin
 */
@Named
@SessionScoped
public class FindLookupSession implements Serializable {

    private Long lookup_id;

    public Long getLookup_id() {
        return lookup_id;
    }

    public void setLookup_id(Long lookup_id) {
        this.lookup_id = lookup_id;
    }

}
