package so.entities;

import contract.entities.Contract;
import customer.entities.Address;
import customer.entities.Country;
import customer.entities.Customer;
import customer.entities.RWAddress;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.SecondaryTable;
import javax.persistence.SecondaryTables;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.eclipse.persistence.annotations.PrivateOwned;
import payment.entities.PaymentTerm;
import rw.entities.RWStation;
import sys.entities.SysUser;

/**
 *
 * @author savin
 */
@Entity
@Table(name = "zakaz")
@SecondaryTables({
    @SecondaryTable(name = "zakaz_docs", foreignKey = @ForeignKey(name = "zakaz_zakaz_docs_fk", value = ConstraintMode.CONSTRAINT,
            foreignKeyDefinition = "FOREIGN KEY (header_id) REFERENCES public.zakaz(header_id) MATCH SIMPLE\n"
            + "      ON UPDATE CASCADE ON DELETE CASCADE")),
    @SecondaryTable(name = "zakaz_tol_mark", foreignKey = @ForeignKey(name = "zakaz_zakaz_tol_mark_fk", value = ConstraintMode.CONSTRAINT,
            foreignKeyDefinition = "FOREIGN KEY (header_id) REFERENCES public.zakaz(header_id) MATCH SIMPLE\n"
            + "      ON UPDATE CASCADE ON DELETE CASCADE")),
    @SecondaryTable(name = "zakaz_sell_transp", foreignKey = @ForeignKey(name = "zakaz_zakaz_sell_transp_fk", value = ConstraintMode.CONSTRAINT,
            foreignKeyDefinition = "FOREIGN KEY (header_id) REFERENCES public.zakaz(header_id) MATCH SIMPLE\n"
            + "      ON UPDATE CASCADE ON DELETE CASCADE"))
})
public class Order implements Serializable {

    @Id
    @SequenceGenerator(name = "zakaz_sq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(generator = "zakaz_sq")
    private Long header_id;

    @OneToOne
    @JoinColumn(name = "customer_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Customer customer;

    @OneToOne
    @JoinColumn(name = "shp_customer_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Customer shp_customer;

    @OneToOne
    @JoinColumn(name = "shp_address_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Address shp_address;

    @OneToOne
    @JoinColumn(name = "shp_rwaddress_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private RWAddress shp_rwaddress;

    @OneToOne
    @JoinColumn(name = "inv_customer_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Customer inv_customer;

    @OneToOne
    @JoinColumn(name = "inv_address_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Address inv_address;

    @OneToOne
    @JoinColumn(name = "contract_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Contract contract;

    @OneToOne
    @JoinColumn(name = "payment_term_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private PaymentTerm paymentTerm;

    @Size(min = 3, max = 3, message = "ВАЛЮТА: длина три символа")
    @Column(length = 3)
    private String currency;

    @Column(name = "fob_code", length = 60)
    private String fob;

    @Column(name = "freight_term_code", length = 10)
    private String freightTerm;

    @Column(length = 15)
    private String cust_po_number;

    @OneToOne
    @JoinColumn(name = "trader_user_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private SysUser traderUser;

    @Temporal(TemporalType.DATE)
    @NotNull(message = "ДАТА ЗАКАЗА: обязательно для заполнения")
    @Column(name = "order_date")
    private Date orderDate;

    @Temporal(TemporalType.DATE)
    @NotNull(message = "ДАТА ЗАПРОСА: обязательно для заполнения")
    @Column(name = "request_date")
    private Date requestDate;
    @Column(name = "ban_on_manuf")
    private Boolean banOnManufacturing;
    @Column(name = "ban_on_shp")
    private Boolean banOnShipping;

    //Документы по заказу
    @Column(table = "zakaz_docs", name = "certif_reg_accuracy", length = 10)
    private String certifRegAccuracy;
    @Column(table = "zakaz_docs", name = "certif_standard", length = 50)
    private String certifStandard;
    @Column(table = "zakaz_docs", name = "certif_quality")
    private Integer certifQuality;
    @Column(table = "zakaz_docs", name = "certif_quality_consolidated")
    private Integer certifQualityConsolidated;
    @Column(table = "zakaz_docs", name = "packing_list")
    private Integer packingList;
    @Column(table = "zakaz_docs", name = "wagon_list")
    private Integer wagonList;
    @Column(table = "zakaz_docs", name = "certif_origin")
    private Integer certifOrigin;
    @Column(table = "zakaz_docs", name = "certif_non_radioactive")
    private Integer certifNonRadioactive;
    @Column(table = "zakaz_docs", name = "certif_eur1")
    private Integer certifEUR1;
    @Column(table = "zakaz_docs", name = "mail_address")
    private String mailAddress;
    @Column(table = "zakaz_docs", name = "rw_bill")
    private Integer rwBill;
    @Column(table = "zakaz_docs", name = "certif_quality_a")
    private Integer certifQualityA;
    @Column(table = "zakaz_docs", name = "packing_list_a")
    private Integer packingListA;
    @Column(table = "zakaz_docs", name = "wagon_list_a")
    private Integer wagonListA;
    @Column(table = "zakaz_docs", name = "certif_origin_a")
    private Integer certifOriginA;
    @Column(table = "zakaz_docs", name = "certif_non_radioactive_a")
    private Integer certifNonRadioactiveA;
    @Column(table = "zakaz_docs", name = "periodicity", length = 10)
    private String periodicity;
    @Column(table = "zakaz_docs", name = "certif_eur1_a")
    private Integer certifEUR1A;
    @Column(table = "zakaz_docs", name = "mail_type", length = 10)
    private String mailType;

    //Толерансы, Маркировка
    @Column(table = "zakaz_tol_mark", name = "tol_percent_p")
    private Double tolPercentP;
    @Column(table = "zakaz_tol_mark", name = "tol_percent_m")
    private Double tolPercentM;
    @Column(table = "zakaz_tol_mark", name = "tol_uom1_p")
    private Double tolUOM1P;
    @Column(table = "zakaz_tol_mark", name = "tol_uom1_m")
    private Double tolUOM1M;
    @Column(table = "zakaz_tol_mark", name = "tol_uom2_p")
    private Double tolUOM2P;
    @Column(table = "zakaz_tol_mark", name = "tol_uom2_m")
    private Double tolUOM2M;
    @Column(table = "zakaz_tol_mark", name = "tol_length_p")
    private Double tolLengthP;
    @Column(table = "zakaz_tol_mark", name = "tol_length_m")
    private Double tolLengthM;
    @Column(table = "zakaz_tol_mark", name = "mark_wp_paint")
    private String markWPaint;
    @Column(table = "zakaz_tol_mark", name = "mark_additional")
    private String markAdditional;
    @Column(table = "zakaz_tol_mark", name = "mark_color")
    private String markColor;
    @Column(table = "zakaz_tol_mark", name = "mark")
    private String mark;

    //Продажа,Транспортировка
    @OneToOne
    @JoinColumn(table = "zakaz_sell_transp", name = "dest_country", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Country destCountry;
    @Column(table = "zakaz_sell_transp", name = "rw_special_notes")
    private String notes;
    @OneToOne
    @JoinColumn(table = "zakaz_sell_transp", name = "rws_code_1", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private RWStation rws1;
    @OneToOne
    @JoinColumn(table = "zakaz_sell_transp", name = "rws_code_2", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private RWStation rws2;
    @OneToOne
    @JoinColumn(table = "zakaz_sell_transp", name = "rws_code_3", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private RWStation rws3;
    @OneToOne
    @JoinColumn(table = "zakaz_sell_transp", name = "rws_code_4", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private RWStation rws4;
    @Temporal(TemporalType.DATE)
    @Column(table = "zakaz_sell_transp", name = "last_fcr_date")
    private Date lastFCRDate;
    @Temporal(TemporalType.DATE)
    @Column(table = "zakaz_sell_transp", name = "last_kns_date")
    private Date lastKNSDate;
    @Temporal(TemporalType.DATE)
    @Column(table = "zakaz_sell_transp", name = "last_rwb_date")
    private Date lastRWBDate;
    @Column(table = "zakaz_sell_transp", name = "tariff")
    private Double rwTariff;
    @Column(table = "zakaz_sell_transp", name = "transhipment")
    private Double transhipment;
    @Temporal(TemporalType.DATE)
    @Column(table = "zakaz_sell_transp", name = "confirm_selling_date")
    private Date confirmSellingDate;

    //Attachments
    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "order", fetch = FetchType.LAZY)
    @PrivateOwned
    @OrderBy("categoryName asc")
    private List<Attachment> attachments = new ArrayList<>();

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

    public String getCertifRegAccuracy() {
        return certifRegAccuracy;
    }

    public void setCertifRegAccuracy(String certifRegAccuracy) {
        this.certifRegAccuracy = certifRegAccuracy;
    }

    public String getCertifStandard() {
        return certifStandard;
    }

    public void setCertifStandard(String certifStandard) {
        this.certifStandard = certifStandard;
    }

    public Integer getCertifQuality() {
        return certifQuality;
    }

    public void setCertifQuality(Integer certifQuality) {
        this.certifQuality = certifQuality;
    }

    public Integer getCertifQualityConsolidated() {
        return certifQualityConsolidated;
    }

    public void setCertifQualityConsolidated(Integer certifQualityConsolidated) {
        this.certifQualityConsolidated = certifQualityConsolidated;
    }

    public Integer getPackingList() {
        return packingList;
    }

    public void setPackingList(Integer packingList) {
        this.packingList = packingList;
    }

    public Integer getWagonList() {
        return wagonList;
    }

    public void setWagonList(Integer wagonList) {
        this.wagonList = wagonList;
    }

    public Integer getCertifOrigin() {
        return certifOrigin;
    }

    public void setCertifOrigin(Integer certifOrigin) {
        this.certifOrigin = certifOrigin;
    }

    public Integer getCertifNonRadioactive() {
        return certifNonRadioactive;
    }

    public void setCertifNonRadioactive(Integer certifNonRadioactive) {
        this.certifNonRadioactive = certifNonRadioactive;
    }

    public Integer getCertifEUR1() {
        return certifEUR1;
    }

    public void setCertifEUR1(Integer certifEUR1) {
        this.certifEUR1 = certifEUR1;
    }

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    public Integer getRwBill() {
        return rwBill;
    }

    public void setRwBill(Integer rwBill) {
        this.rwBill = rwBill;
    }

    public Integer getCertifQualityA() {
        return certifQualityA;
    }

    public void setCertifQualityA(Integer certifQualityA) {
        this.certifQualityA = certifQualityA;
    }

    public Integer getPackingListA() {
        return packingListA;
    }

    public void setPackingListA(Integer packingListA) {
        this.packingListA = packingListA;
    }

    public Integer getWagonListA() {
        return wagonListA;
    }

    public void setWagonListA(Integer wagonListA) {
        this.wagonListA = wagonListA;
    }

    public Integer getCertifOriginA() {
        return certifOriginA;
    }

    public void setCertifOriginA(Integer certifOriginA) {
        this.certifOriginA = certifOriginA;
    }

    public Integer getCertifNonRadioactiveA() {
        return certifNonRadioactiveA;
    }

    public void setCertifNonRadioactiveA(Integer certifNonRadioactiveA) {
        this.certifNonRadioactiveA = certifNonRadioactiveA;
    }

    public String getPeriodicity() {
        return periodicity;
    }

    public void setPeriodicity(String periodicity) {
        this.periodicity = periodicity;
    }

    public Integer getCertifEUR1A() {
        return certifEUR1A;
    }

    public void setCertifEUR1A(Integer certifEUR1A) {
        this.certifEUR1A = certifEUR1A;
    }

    public String getMailType() {
        return mailType;
    }

    public void setMailType(String mailType) {
        this.mailType = mailType;
    }

    public Double getTolPercentP() {
        return tolPercentP;
    }

    public void setTolPercentP(Double tolPercentP) {
        this.tolPercentP = tolPercentP;
    }

    public Double getTolPercentM() {
        return tolPercentM;
    }

    public void setTolPercentM(Double tolPercentM) {
        this.tolPercentM = tolPercentM;
    }

    public Double getTolUOM1P() {
        return tolUOM1P;
    }

    public void setTolUOM1P(Double tolUOM1P) {
        this.tolUOM1P = tolUOM1P;
    }

    public Double getTolUOM1M() {
        return tolUOM1M;
    }

    public void setTolUOM1M(Double tolUOM1M) {
        this.tolUOM1M = tolUOM1M;
    }

    public Double getTolUOM2P() {
        return tolUOM2P;
    }

    public void setTolUOM2P(Double tolUOM2P) {
        this.tolUOM2P = tolUOM2P;
    }

    public Double getTolUOM2M() {
        return tolUOM2M;
    }

    public void setTolUOM2M(Double tolUOM2M) {
        this.tolUOM2M = tolUOM2M;
    }

    public Double getTolLengthP() {
        return tolLengthP;
    }

    public void setTolLengthP(Double tolLengthP) {
        this.tolLengthP = tolLengthP;
    }

    public Double getTolLengthM() {
        return tolLengthM;
    }

    public void setTolLengthM(Double tolLengthM) {
        this.tolLengthM = tolLengthM;
    }

    public String getMarkWPaint() {
        return markWPaint;
    }

    public void setMarkWPaint(String markWPaint) {
        this.markWPaint = markWPaint;
    }

    public String getMarkAdditional() {
        return markAdditional;
    }

    public void setMarkAdditional(String markAdditional) {
        this.markAdditional = markAdditional;
    }

    public String getMarkColor() {
        return markColor;
    }

    public void setMarkColor(String markColor) {
        this.markColor = markColor;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public Country getDestCountry() {
        return destCountry;
    }

    public void setDestCountry(Country destCountry) {
        this.destCountry = destCountry;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public RWStation getRws1() {
        return rws1;
    }

    public void setRws1(RWStation rws1) {
        this.rws1 = rws1;
    }

    public RWStation getRws2() {
        return rws2;
    }

    public void setRws2(RWStation rws2) {
        this.rws2 = rws2;
    }

    public RWStation getRws3() {
        return rws3;
    }

    public void setRws3(RWStation rws3) {
        this.rws3 = rws3;
    }

    public RWStation getRws4() {
        return rws4;
    }

    public void setRws4(RWStation rws4) {
        this.rws4 = rws4;
    }

    public Date getLastFCRDate() {
        return lastFCRDate;
    }

    public void setLastFCRDate(Date lastFCRDate) {
        this.lastFCRDate = lastFCRDate;
    }

    public Date getLastKNSDate() {
        return lastKNSDate;
    }

    public void setLastKNSDate(Date lastKNSDate) {
        this.lastKNSDate = lastKNSDate;
    }

    public Date getLastRWBDate() {
        return lastRWBDate;
    }

    public void setLastRWBDate(Date lastRWBDate) {
        this.lastRWBDate = lastRWBDate;
    }

    public Double getRwTariff() {
        return rwTariff;
    }

    public void setRwTariff(Double rwTariff) {
        this.rwTariff = rwTariff;
    }

    public Double getTranshipment() {
        return transhipment;
    }

    public void setTranshipment(Double transhipment) {
        this.transhipment = transhipment;
    }

    public Date getConfirmSellingDate() {
        return confirmSellingDate;
    }

    public void setConfirmSellingDate(Date confirmSellingDate) {
        this.confirmSellingDate = confirmSellingDate;
    }

    public Boolean getBanOnManufacturing() {
        return banOnManufacturing;
    }

    public void setBanOnManufacturing(Boolean banOnManufacturing) {
        this.banOnManufacturing = banOnManufacturing;
    }

    public Boolean getBanOnShipping() {
        return banOnShipping;
    }

    public void setBanOnShipping(Boolean banOnShipping) {
        this.banOnShipping = banOnShipping;
    }

    public void addAttachment(Attachment li) {
        addAttachment(li, true);
    }

    public void addAttachment(Attachment li, boolean add) {
        if (li != null) {
            getAttachments().add(li);
            if (add) {
                li.setOrder(this, false);
            }
        }
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }

}
