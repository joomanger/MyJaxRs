package so.config.beans;

import so.config.entity.ConfigurationLine;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;
import service.RestProviderWR;

/**
 *
 * @author savin
 */
@Named
@RequestScoped
@Provider
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ConfigLineClientBean extends RestProviderWR<ConfigurationLine> {

    @Override
    protected String getPath() {
        return ("http://localhost:8080/MyJaxRs/webresources/configurationline/");
    }
    
    
}
