package so.saleorder.flows;

import java.io.Serializable;
import java.util.Calendar;
import javax.annotation.PostConstruct;
import javax.faces.flow.FlowScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lookup.beans.LookupCBean;
import lookup.entities.Lookup;
import so.entities.Order;
import so.saleorder.beans.SaleOrderCBean;

/**
 *
 * @author savin
 */
@Named
@FlowScoped("Order")
public class CreateSaleOrderFlow implements Serializable {

    @Inject
    private SaleOrderCBean sob;
    @Inject
    private LookupCBean clientLookup;

    //Используемые справочники
    private Lookup certTypes;
    private Lookup regAccuracy;
    private Lookup stampKind;
    private Lookup docSendInterval;
    private Lookup markWPaint;
    private Lookup postType;

    private Order order;

    @SuppressWarnings("PMD.UnusedPrivateMethod")
    @PostConstruct
    private void init() {
        order = new Order();
        order.setHeader_id(sob.getNewOrderNumber());
        order.setOrderDate(Calendar.getInstance().getTime());
        certTypes = clientLookup.findByName("CERT_TYPE");
        regAccuracy = clientLookup.findByName("REG_ACCURACY");
        stampKind = clientLookup.findByName("STAMP_KIND");
        docSendInterval = clientLookup.findByName("DOCS_SEND_INTERVAL");
        markWPaint = clientLookup.findByName("MARK_WPAINT");
        postType = clientLookup.findByName("POST_TYPE");
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public SaleOrderCBean getSob() {
        return sob;
    }

    public LookupCBean getClientLookup() {
        return clientLookup;
    }

    public Lookup getCertTypes() {
        return certTypes;
    }

    public Lookup getRegAccuracy() {
        return regAccuracy;
    }

    public Lookup getStampKind() {
        return stampKind;
    }

    public Lookup getDocSendInterval() {
        return docSendInterval;
    }

    public Lookup getMarkWPaint() {
        return markWPaint;
    }

    public Lookup getPostType() {
        return postType;
    }

    

}
