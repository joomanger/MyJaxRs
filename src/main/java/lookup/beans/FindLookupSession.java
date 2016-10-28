package lookup.beans;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author savin
 */
@Named
@SessionScoped
public class FindLookupSession implements Serializable {

    private Long lookup_id;

    public Long getLookup_id() {
        return lookup_id;
    }

    public void setLookup_id(Long lookup_id) {
        this.lookup_id = lookup_id;
    }

    public void handleFileUpload(FileUploadEvent event) throws IOException {
        System.out.println("Into handleFileUpload");
        UploadedFile file = event.getFile();

        String upload_dir = FacesContext.getCurrentInstance().getExternalContext().getInitParameter("UPLOAD_DIR");
        String filename = file.getFileName();
        System.out.println("filename=" + filename + " " + "upload_dir=" + upload_dir);

        try (InputStream in = file.getInputstream(); OutputStream out = new FileOutputStream(new File(upload_dir + filename))) {
            int read;
            byte[] bytes = new byte[1024];
            while ((read = in.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            out.flush();
            FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } catch (IOException ex) {
            throw ex;
        }

    }

}
