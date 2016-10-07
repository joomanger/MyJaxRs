package so.entities;

import contract.entities.Contract;
import customer.entities.Address;
import customer.entities.Customer;
import customer.entities.RWAddress;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author savin
 */
@Entity
@Table(name = "zakaz")
public class Order implements Serializable {

    @Id
    @SequenceGenerator(name = "zakaz_sq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(generator = "zakaz_sq")
    private Long header_id;

    @Transient
    private Customer customer;
    private Long customer_id;

    @Transient
    private Customer shp_customer;
    private Long shp_customer_id;
    @Transient
    private Address shp_address;
    private Long shp_address_id;
    @Transient
    private RWAddress shp_rwaddress;
    private Long shp_rwaddress_id;

    @Transient
    private Customer inv_customer;
    private Long inv_customer_id;
    @Transient
    private Address inv_address;
    private Long inv_address_id;

    @Transient
    private Contract contract;
    private Long contract_id;

    @Size(min = 3, max = 3, message = "ВАЛЮТА: длина три символа")
    private String currency;

    @Temporal(TemporalType.DATE)
    @NotNull(message = "ДАТА ЗАКАЗА: обязательно для заполнения")
    @Column(name = "order_date")
    private Date orderDate;

    public Long getHeader_id() {
        return header_id;
    }

    public void setHeader_id(Long header_id) {
        this.header_id = header_id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Long getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Long customer_id) {
        this.customer_id = customer_id;
    }

    public Customer getShp_customer() {
        return shp_customer;
    }

    public void setShp_customer(Customer shp_customer) {
        this.shp_customer = shp_customer;
    }

    public Long getShp_customer_id() {
        return shp_customer_id;
    }

    public void setShp_customer_id(Long shp_customer_id) {
        this.shp_customer_id = shp_customer_id;
    }

    public Address getShp_address() {
        return shp_address;
    }

    public void setShp_address(Address shp_address) {
        this.shp_address = shp_address;
    }

    public Long getShp_address_id() {
        return shp_address_id;
    }

    public void setShp_address_id(Long shp_address_id) {
        this.shp_address_id = shp_address_id;
    }

    public RWAddress getShp_rwaddress() {
        return shp_rwaddress;
    }

    public void setShp_rwaddress(RWAddress shp_rwaddress) {
        this.shp_rwaddress = shp_rwaddress;
    }

    public Long getShp_rwaddress_id() {
        return shp_rwaddress_id;
    }

    public void setShp_rwaddress_id(Long shp_rwaddress_id) {
        this.shp_rwaddress_id = shp_rwaddress_id;
    }

    public Customer getInv_customer() {
        return inv_customer;
    }

    public void setInv_customer(Customer inv_customer) {
        this.inv_customer = inv_customer;
    }

    public Long getInv_customer_id() {
        return inv_customer_id;
    }

    public void setInv_customer_id(Long inv_customer_id) {
        this.inv_customer_id = inv_customer_id;
    }

    public Address getInv_address() {
        return inv_address;
    }

    public void setInv_address(Address inv_address) {
        this.inv_address = inv_address;
    }

    public Long getInv_address_id() {
        return inv_address_id;
    }

    public void setInv_address_id(Long inv_address_id) {
        this.inv_address_id = inv_address_id;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public Long getContract_id() {
        return contract_id;
    }

    public void setContract_id(Long contract_id) {
        this.contract_id = contract_id;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
    
    

}
