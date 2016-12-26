package ws.so;

import entities.so.SaleOrderLine;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;
import beans.so.saleorderline.SaleOrderLineBackingBean;
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
public class SaleOrderLineBean extends RestProviderWR<SaleOrderLine> {

    @Inject
    private SaleOrderLineBackingBean sob;

    @Override
    protected String getPath() {
        return "http://localhost:8080/MyJaxRs/webresources/saleorderline/";
    }

    public void edit() {
       super.editItem(sob.getLine(), "Строка изменена успешно");
    }

}
