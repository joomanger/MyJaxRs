package customer.beans;

import customer.entities.Customer;
import customer.entities.RWAddress;
import java.util.Collections;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import rw.entities.RWStationTL;
import service.AbstractView;
import service.SessionActions;

/**
 *
 * @author savin
 */
@Named
@ViewScoped
public class OpenCustomerView extends AbstractView<Customer> {

    @Inject
    private CustomerCBean client;
//    @Inject
//    private RWStationCBean clientRWS;
    @Inject
    private FindCustomerSession fls;
    @Inject
    private SessionActions sa;

    private Customer openedEntity;
    //Поля для создания новой строки

    @SuppressWarnings("PMD.UnusedPrivateMethod")
    @PostConstruct
    @Override
    protected void init() {
        openedEntity = client.find(fls.getCustomer_id());
        for(RWAddress rs:openedEntity.getRWAddresses()){
            for(RWStationTL tl:rs.getStation().getRwstationTL()){
                if(tl.getLanguage().equals(sa.getLanguage())){
                    //rs.getStation().setName(clientRWS.getNameByCode(rs.getStation().getRws_code()));
                    rs.getStation().setName(tl.getName());
                    break;
                }
            }
        }
        Collections.sort(openedEntity.getRWAddresses());
        super.setEntity(openedEntity);
    }

    public Customer getOpenedEntity() {
        return openedEntity;
    }

    public void setOpenedEntity(Customer openedEntity) {
        this.openedEntity = openedEntity;
    }

}
