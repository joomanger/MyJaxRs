package lovs;

import service.AbstractLookupLOV;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author savin
 */
@Named
@RequestScoped
public class MarkWPaintLOV extends AbstractLookupLOV{

    @Override
    protected String getLookupName() {
        return "MARK_WPAINT";
    }
    
}
