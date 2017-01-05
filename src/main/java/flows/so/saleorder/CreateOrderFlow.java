package flows.so.saleorder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.flow.FlowScoped;
import javax.inject.Inject;
import javax.inject.Named;
import beans.lookup.LookupCBean;
import beans.so.saleorder.OrderCBean;
import entities.lookup.Lookup;

import entities.so.Order;
import entities.so.Attachment;

/**
 *
 * @author savin
 */
@Named
@FlowScoped("Order")
public class CreateOrderFlow implements Serializable {
    
    @Inject
    private OrderCBean sob;
//    @Inject
//    private OrderEJB orderEJB;
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
    private Attachment attachment;
    private List<Attachment> selectedAttachments=new ArrayList<>();
    private String uploadStatus;
    
    @SuppressWarnings("PMD.UnusedPrivateMethod")
    @PostConstruct
    private void init() {
        order = new Order();
       // orderEJB.setOrder(order);
        attachment = new Attachment();
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
    
    public Attachment getAttachment() {
        return attachment;
    }
    
    public void setAttachment(Attachment attachment) {
        this.attachment = attachment;
    }
    
    public String getUploadStatus() {
        return uploadStatus;
    }
    
    public void setUploadStatus(String uploadStatus) {
        this.uploadStatus = uploadStatus;
    }

    public List<Attachment> getSelectedAttachments() {
        return selectedAttachments;
    }

    public void setSelectedAttachments(List<Attachment> selectedAttachments) {
        this.selectedAttachments = selectedAttachments;
    }
    
    
    
}
