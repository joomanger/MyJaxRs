package lovs;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author savin
 */
@Named
@RequestScoped
public class UomLOV extends AbstractLookupLOV{

    @Override
    protected String getLookupName() {
        return "UOM";
    }
    
}
