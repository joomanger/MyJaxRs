/*

 */
package lovs;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import service.AbstractLookupLOV;

/**
 *
 * @author savin
 */
@Named
@RequestScoped
public class LangLOV extends AbstractLookupLOV {

    @Override
    protected String getLookupName() {
        return "LANGUAGES";
    }

}
