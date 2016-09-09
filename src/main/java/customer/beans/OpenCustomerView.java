package customer.beans;

import customer.entities.Customer;
import customer.entities.RWAddress;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import service.AbstractView;

/**
 *
 * @author savin
 */
@Named
@ViewScoped
public class OpenCustomerView extends AbstractView<Customer> {

    @Inject
    private CustomerCBean client;
    @Inject
    private FindCustomerSession fls;
    
    private Customer openedEntity;
    private Long rwaddress_id;
    private RWAddress rwaddress=new RWAddress();
    //Поля для создания новой строки

    @SuppressWarnings("PMD.UnusedPrivateMethod")
    @PostConstruct
    @Override
    protected void init() {
        openedEntity = client.find(fls.getCustomer_id());
        openedEntity.setRwAddressVL(client.findRWAddressVL(openedEntity.getCustomer_id()));
        super.setEntity(openedEntity);
    }

    public Customer getOpenedEntity() {
        return openedEntity;
    }

    public void setOpenedEntity(Customer openedEntity) {
        this.openedEntity = openedEntity;
    }

    public Long getRwaddress_id() {
        return rwaddress_id;
    }

    public void setRwaddress_id(Long rwaddress_id) {
        this.rwaddress_id = rwaddress_id;
        if(rwaddress_id!=null){
            for(RWAddress r:openedEntity.getRWAddresses()){
                if(r.getRwaddress_id().equals(rwaddress_id)){
                    rwaddress=r;
                    break;
                }
            }
//            rwaddress=client.findRWAddressByID(rwaddress_id);
            System.out.println("!!!!!!setRwaddress("+rwaddress_id+")"+" asas="+rwaddress.getRwrcvcode());
        }
    }

    public RWAddress getRwaddress() {
        return rwaddress;
    }

    public void setRwaddress(RWAddress rwaddress) {
        this.rwaddress = rwaddress;
    }
    
}
