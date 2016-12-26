package beans.so.saleorder;

import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.FileUploadEvent;
import service.AbstractEJB;
import service.MessageSender;
import beans.so.config.ConfigCBean;
import entities.so.Attachment;
import entities.so.config.Configuration;
import entities.so.config.ConfigurationLine;
import entities.so.config.ParameterConfiguration;
import entities.so.OrderLine;
import flows.so.saleorder.CreateOrderFlow;
import flows.so.saleorder.CreateOrderLineFlow;

/**
 *
 * @author savin
 */
@Named
@RequestScoped
public class OrderCBean {

    @Inject
    private OrderEJB orderEJB;
    @Inject
    private AttachmentEJB attachEJB;
    @Inject
    private CreateOrderFlow orderFlow;
    @Inject
    private CreateOrderLineFlow lineFlow;
    @Inject
    private ConfigCBean configClient;


    /*После выбора нового Заказчика обнуляются все зависимые поля*/
    public void clearFieldsHeader() {
        orderFlow.getOrder().setShp_customer(null);
        orderFlow.getOrder().setShp_address(null);
        orderFlow.getOrder().setShp_rwaddress(null);
        orderFlow.getOrder().setInv_customer(null);
        orderFlow.getOrder().setInv_address(null);
        orderFlow.getOrder().setContract(null);
    }

    public void addNewAttachment() {
        Attachment a = orderFlow.getAttachment();
        String result = attachEJB.validateMyEntity(a);
        if (result.equals(AbstractEJB.ERROR)) {
            MessageSender.sendFacesContextMessage(FacesMessage.SEVERITY_ERROR, result);
        } else {
            orderEJB.addNewAttachment(orderFlow.getOrder(),a);
            orderFlow.setAttachment(new Attachment());
            orderFlow.setUploadStatus(null);
        }
    }

    public void deleteSelectedAttachment() {
        orderEJB.deleteSelectedAttachment(orderFlow.getOrder(),orderFlow.getSelectedAttachments());
    }

    public void createOrder() {
        orderEJB.createOrder(orderFlow.getOrder());
    }

    public void handleFileUpload(FileUploadEvent event) throws IOException {
        String filename = orderEJB.handleFileUpload(orderFlow.getOrder(),event);
        orderFlow.getAttachment().setFileName(filename);
        if (!filename.isEmpty()) {
            orderFlow.setUploadStatus(filename + " загружен успешно");
        }
    }

    private void updateConfigColumns() {
        lineFlow.setParameters(getAllConfigParameters());
    }
//////////////////////////////////////////////////////////////////////////////

    /*СТРОКИ ЗАКАЗА*/
    public void addLine() {
        OrderLine line = new OrderLine();
        line.setItem(lineFlow.getItem());
        short lineNumber = lineFlow.getLineNumber();
        line.setLineNumber(++lineNumber);
        lineFlow.setLineNumber(lineNumber);
        line.setConfig_ver_num(configClient.getLastVersion(lineFlow.getItem().getItem_id()));
        orderFlow.getOrder().addLine(line);
        lineFlow.getLines().add(line);
        //Если добавляется позиция, которая раннее не добавлялалсь, то обновим сетку параметров
        if (!lineFlow.getNewItems().contains(lineFlow.getItem().getItem_id())) {
            lineFlow.getNewItems().add(lineFlow.getItem().getItem_id());
            updateConfigColumns();
        }

    }

    public void deleteLines() {
        lineFlow.getLines().removeAll(lineFlow.getSelectedLines());
        orderFlow.getOrder().getLines().removeAll(lineFlow.getSelectedLines());
        for (OrderLine i : lineFlow.getSelectedLines()) {
            if (lineFlow.getNewItems().contains(i.getItem().getItem_id())) {
                lineFlow.getNewItems().remove(i.getItem().getItem_id());
            }
        }
        updateConfigColumns();
    }

    public boolean isEditableCell(Long item_id, String attribute) {
        if (lineFlow.getEditableCells().contains(item_id + attribute)) {
            return true;
        }
        return false;
    }

    private Set<ParameterConfiguration> getAllConfigParameters() {
        Set<Long> items = new HashSet<>();
        Set<ParameterConfiguration> p = new LinkedHashSet<>();
        for (OrderLine line : orderFlow.getOrder().getLines()) {
            long item_id = line.getItem().getItem_id();
            if (!items.contains(item_id)) {
                items.add(item_id);
                Configuration config = configClient.getItem(item_id, configClient.getLastVersion(item_id));
                if (config != null) {
                    //lineFlow.getEditableCells().put(item_id, config.getLines());
                    for (ConfigurationLine cl : config.getLines()) {
                        lineFlow.getEditableCells().add(item_id + cl.getParameter().getAttribute().toLowerCase());
                    }

                    for (ConfigurationLine configLine : config.getLines()) {
                        ParameterConfiguration pc = configLine.getParameter();
                        pc.setAttribute(pc.getAttribute().toLowerCase());
                        p.add(pc);
                    }
                }
            }
        }
        return p;
    }

}
