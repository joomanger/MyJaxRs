package saleorderline.beans;

import com.isd.myjaxrs.entity.SaleOrderLine;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author savin
 */
@Named
@RequestScoped
public class SaleOrderLineCBean {

    @Inject
    private SaleOrderLineEJB lineEJB;

    public void create(SaleOrderLine line, String msg) {
        String status = lineEJB.create(line);
        lineEJB.sendMessage(status, msg);

    }

    public void edit(SaleOrderLine line, String msg) {
        String status = lineEJB.edit(line);
        lineEJB.sendMessage(status, msg);
    }

    public void delete(Long line_id, String msg) {
        String status = lineEJB.remove(lineEJB.find(line_id));
        lineEJB.sendMessage(status, msg);
    }

}
