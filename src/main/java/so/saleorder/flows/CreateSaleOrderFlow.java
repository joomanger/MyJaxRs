package so.saleorder.flows;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.flow.FlowScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lookup.beans.LookupCBean;
import lookup.entities.Lookup;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import so.entities.Attachment;
import so.entities.Order;
import so.saleorder.beans.OrderEJB;
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
    private OrderEJB orderEJB;
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
        orderEJB.setOrder(order);
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
    
    public void handleFileUpload(FileUploadEvent event) throws IOException {
        UploadedFile file = event.getFile();
        String upload_dir = FacesContext.getCurrentInstance().getExternalContext().getInitParameter("UPLOAD_DIR");
        String filename = file.getFileName();
        String folder = upload_dir + "Z_" + String.valueOf(order.getHeader_id());
        File theDir = new File(folder);
        if (!theDir.exists()) {
            theDir.mkdirs();
        }
        
        try (InputStream in = file.getInputstream();
                OutputStream out = new FileOutputStream(new File(folder + "//" + filename))) {
            int read;
            byte[] bytes = new byte[1024];
            while ((read = in.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            out.flush();
            setUploadStatus(event.getFile().getFileName() + " загружен успешно");
            attachment.setFileName(filename);
            FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } catch (IOException ex) {
            throw ex;
        }
        
    }
    
}
