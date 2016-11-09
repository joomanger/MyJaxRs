package so.saleorder.beans;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import service.AbstractEJB2;
import service.MessageSender;
import so.entities.Attachment;
import so.entities.Order;

/**
 *
 * @author savin
 */
@Stateless
public class OrderEJB extends AbstractEJB2<Order> {

    private Order order;

    @PersistenceContext(unitName = "myjaxrs")
    private EntityManager em;

    public OrderEJB() {
        super(Order.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void addNewAttachment(Attachment attachment) {
        order.addAttachment(attachment);
    }

    public void deleteSelectedAttachment(List<Attachment> attachments) {
        order.getAttachments().removeAll(attachments);
    }

    public void createOrder() {
        super.create(order);
    }
    
    public String handleFileUpload(FileUploadEvent event) throws IOException {
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
//            orderFlow.setUploadStatus(event.getFile().getFileName() + " загружен успешно");
//            orderFlow.getAttachment().setFileName(filename);
            MessageSender.sendFacesContextMessage(FacesMessage.SEVERITY_INFO, event.getFile().getFileName() + " загружен успешно");
        } catch (IOException ex) {
            throw ex;
        }
        
        return filename;
        
    }

}
