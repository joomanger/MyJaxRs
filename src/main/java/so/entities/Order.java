package so.entities;

import contract.entities.Contract;
import customer.entities.Address;
import customer.entities.Customer;
import customer.entities.RWAddress;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import payment.entities.PaymentTerm;
import sys.entities.SysUser;

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

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "shp_customer_id")
    private Customer shp_customer;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "shp_address_id")
    private Address shp_address;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "shp_rwaddress_id")
    private RWAddress shp_rwaddress;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "inv_customer_id")
    private Customer inv_customer;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "inv_address_id")
    private Address inv_address;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "contract_id")
    private Contract contract;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "payment_term_id")
    private PaymentTerm paymentTerm;

    @Size(min = 3, max = 3, message = "ВАЛЮТА: длина три символа")
    private String currency;

    private String fob;
    private String freightTerm;
    private String cust_po_number;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "trader_user_id")
    private SysUser traderUser;

    @Temporal(TemporalType.DATE)
    @NotNull(message = "ДАТА ЗАКАЗА: обязательно для заполнения")
    @Column(name = "order_date")
    private Date orderDate;

    @Temporal(TemporalType.DATE)
    @NotNull(message = "ДАТА ЗАПРОСА: обязательно для заполнения")
    @Column(name = "request_date")
    private Date requestDate;

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

    public Customer getShp_customer() {
        return shp_customer;
    }

    public void setShp_customer(Customer shp_customer) {
        this.shp_customer = shp_customer;
    }

    public Address getShp_address() {
        return shp_address;
    }

    public void setShp_address(Address shp_address) {
        this.shp_address = shp_address;
    }

    public RWAddress getShp_rwaddress() {
        return shp_rwaddress;
    }

    public void setShp_rwaddress(RWAddress shp_rwaddress) {
        this.shp_rwaddress = shp_rwaddress;
    }

    public Customer getInv_customer() {
        return inv_customer;
    }

    public void setInv_customer(Customer inv_customer) {
        this.inv_customer = inv_customer;
    }

    public Address getInv_address() {
        return inv_address;
    }

    public void setInv_address(Address inv_address) {
        this.inv_address = inv_address;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
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

    public PaymentTerm getPaymentTerm() {
        return paymentTerm;
    }

    public void setPaymentTerm(PaymentTerm paymentTerm) {
        this.paymentTerm = paymentTerm;
    }

    public String getFob() {
        return fob;
    }

    public void setFob(String fob) {
        this.fob = fob;
    }

    public String getFreightTerm() {
        return freightTerm;
    }

    public void setFreightTerm(String freightTerm) {
        this.freightTerm = freightTerm;
    }

    public String getCust_po_number() {
        return cust_po_number;
    }

    public void setCust_po_number(String cust_po_number) {
        this.cust_po_number = cust_po_number;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public SysUser getTraderUser() {
        return traderUser;
    }

    public void setTraderUser(SysUser traderUser) {
        this.traderUser = traderUser;
    }

}
