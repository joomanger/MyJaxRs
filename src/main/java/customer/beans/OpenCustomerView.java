package customer.beans;

import customer.entities.Address;
import customer.entities.Customer;
import customer.entities.RWAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import rw.entities.RWStation;
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
    @Inject
    private FindCustomerSession fls;
    @Inject
    private SessionActions sa;
    
    private Customer openedEntity=new Customer();
    
    private List<Address> selectedAddresses = new ArrayList<>();
    private List<RWAddress> selectedRWAddresses = new ArrayList<>();
    private List<RWAddress> filteredRWAddresses;
    private List<Address> filteredAddresses;

    //Поля для создания новой строки
    private RWStation station;
    private String rwBranch;
    private String rwRcvCode;
    
    private String region;

    @SuppressWarnings("PMD.UnusedPrivateMethod")
    @PostConstruct
    @Override
    protected void init() {
        openedEntity = client.find(fls.getCustomer_id());

        List<RWAddress> rw = openedEntity.getRWAddresses();

        Collections.sort(rw, new Comparator<RWAddress>() {
            @Override
            public int compare(RWAddress o1, RWAddress o2) {
                return o1.getStation().getTranslateObject(sa.getLanguage()).getName().
                        compareTo(o2.getStation().getTranslateObject(sa.getLanguage()).getName());
            }
        });
        super.setEntity(openedEntity);
    }

    public Customer getOpenedEntity() {
        return openedEntity;
    }

    public void setOpenedEntity(Customer openedEntity) {
        this.openedEntity = openedEntity;
    }

    public RWStation getStation() {
        return station;
    }

    public void setStation(RWStation station) {
        this.station = station;
    }

    public String getRwBranch() {
        return rwBranch;
    }

    public void setRwBranch(String rwBranch) {
        this.rwBranch = rwBranch;
    }

    public String getRwRcvCode() {
        return rwRcvCode;
    }

    public void setRwRcvCode(String rwRcvCode) {
        this.rwRcvCode = rwRcvCode;
    }

    public List<Address> getSelectedAddresses() {
        return selectedAddresses;
    }

    public void setSelectedAddresses(List<Address> selectedAddresses) {
        this.selectedAddresses = selectedAddresses;
    }

    public List<RWAddress> getFilteredRWAddresses() {
        return filteredRWAddresses;
    }

    public void setFilteredRWAddresses(List<RWAddress> filteredRWAddresses) {
        this.filteredRWAddresses = filteredRWAddresses;
    }

    public List<Address> getFilteredAddresses() {
        return filteredAddresses;
    }

    public void setFilteredAddresses(List<Address> filteredAddresses) {
        this.filteredAddresses = filteredAddresses;
    }

    public List<RWAddress> getSelectedRWAddresses() {
        return selectedRWAddresses;
    }

    public void setSelectedRWAddresses(List<RWAddress> selectedRWAddresses) {
        this.selectedRWAddresses = selectedRWAddresses;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
    
    

}
