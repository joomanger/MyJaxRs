package entities.so;

import entities.customer.Address;
import entities.customer.Customer;
import entities.customer.RWAddress;
import entities.item.Item;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SecondaryTable;
import javax.persistence.SecondaryTables;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import entities.payment.PaymentTerm;
import service.IAttributes;

/**
 *
 * @author savin 
 * Строка заказа ыаыаываыва ываыаыва
 * sdklfjsdjslj
 * sdkjhksdhkjfhsdkjfhsk
 * askldjasjkasjkasjkhasjk askdh asjk asjkdh asjkdh kasdh sdfsdfsdfsfsdf
 */
@Entity
@Table(name = "zakaz_line")
@SecondaryTables({
    @SecondaryTable(name = "zakaz_line_tol_mark", foreignKey = @ForeignKey(name = "zl_zltm_fk", value = ConstraintMode.CONSTRAINT,
            foreignKeyDefinition = "FOREIGN KEY (line_id) REFERENCES public.zakaz_line(line_id) MATCH SIMPLE\n"
            + "      ON UPDATE CASCADE ON DELETE CASCADE")),
    @SecondaryTable(name = "zakaz_line_config", foreignKey = @ForeignKey(name = "zl_line_conf_fk", value = ConstraintMode.CONSTRAINT,
            foreignKeyDefinition = "FOREIGN KEY (line_id) REFERENCES public.zakaz_line(line_id) MATCH SIMPLE\n"
            + "      ON UPDATE CASCADE ON DELETE CASCADE"))
})
public class OrderLine implements IAttributes,Serializable {

    @Id
    @SequenceGenerator(name = "zakaz_line_sq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(generator = "zakaz_line_sq")
    private Long line_id;

    @ManyToOne
    @JoinColumn(name = "header_id", foreignKey = @ForeignKey(name = "line_zakaz_fk", value = ConstraintMode.CONSTRAINT,
            foreignKeyDefinition = "FOREIGN KEY (header_id) REFERENCES public.zakaz(header_id) MATCH SIMPLE\n"
            + "      ON UPDATE CASCADE ON DELETE CASCADE"))
    private Order order;
    @Column(name = "line_number")
    private Short lineNumber;

    @Column(length = 15)
    private String lot;

    @OneToOne
    @JoinColumn(name = "item_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Item item;

    private Double quantity;

    private Double quantity2;

    private Double price;

    @Temporal(TemporalType.DATE)
    @Column(name = "request_date")
    private Date requestDate;

    //Под вопросом need_by_date
    @Temporal(TemporalType.DATE)
    @Column(name = "need_by_date")
    private Date needByDate;

    //Реквизиты грузополучателя
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
    @JoinColumn(name = "payment_term_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private PaymentTerm paymentTerm;

    @Column(name = "fob_code", length = 60)
    private String fob;

    //Толерансы, Маркировка
    @Column(table = "zakaz_line_tol_mark", name = "tol_percent_p")
    private Double tolPercentP;
    @Column(table = "zakaz_line_tol_mark", name = "tol_percent_m")
    private Double tolPercentM;
    @Column(table = "zakaz_line_tol_mark", name = "tol_uom1_p")
    private Double tolUOM1P;
    @Column(table = "zakaz_line_tol_mark", name = "tol_uom1_m")
    private Double tolUOM1M;
    @Column(table = "zakaz_line_tol_mark", name = "tol_uom2_p")
    private Double tolUOM2P;
    @Column(table = "zakaz_line_tol_mark", name = "tol_uom2_m")
    private Double tolUOM2M;
    @Column(table = "zakaz_line_tol_mark", name = "tol_length_p")
    private Double tolLengthP;
    @Column(table = "zakaz_line_tol_mark", name = "tol_length_m")
    private Double tolLengthM;
    @Column(table = "zakaz_line_tol_mark", name = "mark_wp_paint")
    private String markWPaint;
    @Column(table = "zakaz_line_tol_mark", name = "mark_additional")
    private String markAdditional;
    @Column(table = "zakaz_line_tol_mark", name = "mark_color")
    private String markColor;
    @Column(table = "zakaz_line_tol_mark", name = "mark")
    private String mark;

    //Атрибуты конфигурации
    @Column(table = "zakaz_line_config")
    private Integer config_ver_num;
    @Column(table = "zakaz_line_config")
    private String attribute1;
    @Column(table = "zakaz_line_config")
    private String attribute2;
    @Column(table = "zakaz_line_config")
    private String attribute3;
    @Column(table = "zakaz_line_config")
    private String attribute4;
    @Column(table = "zakaz_line_config")
    private String attribute5;
    @Column(table = "zakaz_line_config")
    private String attribute6;
    @Column(table = "zakaz_line_config")
    private String attribute7;
    @Column(table = "zakaz_line_config")
    private String attribute8;
    @Column(table = "zakaz_line_config")
    private String attribute9;
    @Column(table = "zakaz_line_config")
    private String attribute10;
    @Column(table = "zakaz_line_config")
    private String attribute11;
    @Column(table = "zakaz_line_config")
    private String attribute12;
    @Column(table = "zakaz_line_config")
    private String attribute13;
    @Column(table = "zakaz_line_config")
    private String attribute14;
    @Column(table = "zakaz_line_config")
    private String attribute15;
    @Column(table = "zakaz_line_config")
    private String attribute16;
    @Column(table = "zakaz_line_config")
    private String attribute17;
    @Column(table = "zakaz_line_config")
    private String attribute18;
    @Column(table = "zakaz_line_config")
    private String attribute19;
    @Column(table = "zakaz_line_config")
    private String attribute20;
    @Column(table = "zakaz_line_config")
    private String attribute21;
    @Column(table = "zakaz_line_config")
    private String attribute22;
    @Column(table = "zakaz_line_config")
    private String attribute23;
    @Column(table = "zakaz_line_config")
    private String attribute24;
    @Column(table = "zakaz_line_config")
    private String attribute25;
    @Column(table = "zakaz_line_config")
    private String attribute26;
    @Column(table = "zakaz_line_config")
    private String attribute27;
    @Column(table = "zakaz_line_config")
    private String attribute28;
    @Column(table = "zakaz_line_config")
    private String attribute29;
    @Column(table = "zakaz_line_config")
    private String attribute30;
    @Column(table = "zakaz_line_config")
    private String attribute31;
    @Column(table = "zakaz_line_config")
    private String attribute32;
    @Column(table = "zakaz_line_config")
    private String attribute33;
    @Column(table = "zakaz_line_config")
    private String attribute34;
    @Column(table = "zakaz_line_config")
    private String attribute35;
    @Column(table = "zakaz_line_config")
    private String attribute36;
    @Column(table = "zakaz_line_config")
    private String attribute37;
    @Column(table = "zakaz_line_config")
    private String attribute38;
    @Column(table = "zakaz_line_config")
    private String attribute39;
    @Column(table = "zakaz_line_config")
    private String attribute40;
    @Transient
    private List<String> lattribute1;
    @Transient
    private List<String> lattribute2;
    @Transient
    private List<String> lattribute3;
    @Transient
    private List<String> lattribute4;
    @Transient
    private List<String> lattribute5;
    @Transient
    private List<String> lattribute6;
    @Transient
    private List<String> lattribute7;
    @Transient
    private List<String> lattribute8;
    @Transient
    private List<String> lattribute9;
    @Transient
    private List<String> lattribute10;
    @Transient
    private List<String> lattribute11;
    @Transient
    private List<String> lattribute12;
    @Transient
    private List<String> lattribute13;
    @Transient
    private List<String> lattribute14;
    @Transient
    private List<String> lattribute15;
    @Transient
    private List<String> lattribute16;
    @Transient
    private List<String> lattribute17;
    @Transient
    private List<String> lattribute18;
    @Transient
    private List<String> lattribute19;
    @Transient
    private List<String> lattribute20;
    @Transient
    private List<String> lattribute21;
    @Transient
    private List<String> lattribute22;
    @Transient
    private List<String> lattribute23;
    @Transient
    private List<String> lattribute24;
    @Transient
    private List<String> lattribute25;
    @Transient
    private List<String> lattribute26;
    @Transient
    private List<String> lattribute27;
    @Transient
    private List<String> lattribute28;
    @Transient
    private List<String> lattribute29;
    @Transient
    private List<String> lattribute30;
    @Transient
    private List<String> lattribute31;
    @Transient
    private List<String> lattribute32;
    @Transient
    private List<String> lattribute33;
    @Transient
    private List<String> lattribute34;
    @Transient
    private List<String> lattribute35;
    @Transient
    private List<String> lattribute36;
    @Transient
    private List<String> lattribute37;
    @Transient
    private List<String> lattribute38;
    @Transient
    private List<String> lattribute39;
    @Transient
    private List<String> lattribute40;

    @Transient
    private final StringBuilder attr = new StringBuilder();

    public void setOrder(Order order) {
        setOrder(order, true);
    }

    public void setOrder(Order order, boolean set) {
        this.order = order;
        if (order != null && set) {
            order.addLine(this, false);
        }
    }

    public Long getLine_id() {
        return line_id;
    }

    public void setLine_id(Long line_id) {
        this.line_id = line_id;
    }

    public Order getOrder() {
        return order;
    }

    public Short getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(Short lineNumber) {
        this.lineNumber = lineNumber;
    }

    public String getLot() {
        return lot;
    }

    public void setLot(String lot) {
        this.lot = lot;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Double getQuantity2() {
        return quantity2;
    }

    public void setQuantity2(Double quantity2) {
        this.quantity2 = quantity2;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public Date getNeedByDate() {
        return needByDate;
    }

    public void setNeedByDate(Date needByDate) {
        this.needByDate = needByDate;
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

    public Integer getConfig_ver_num() {
        return config_ver_num;
    }

    public void setConfig_ver_num(Integer config_ver_num) {
        this.config_ver_num = config_ver_num;
    }

    @Override
    public String getAttribute1() {
        return attribute1;
    }

    @Override
    public void setAttribute1(String attribute1) {
        this.attribute1 = attribute1;
    }

    @Override
    public String getAttribute2() {
        return attribute2;
    }

    @Override
    public void setAttribute2(String attribute2) {
        this.attribute2 = attribute2;
    }

    @Override
    public String getAttribute3() {
        return attribute3;
    }

    @Override
    public void setAttribute3(String attribute3) {
        this.attribute3 = attribute3;
    }

    @Override
    public String getAttribute4() {
        return attribute4;
    }

    @Override
    public void setAttribute4(String attribute4) {
        this.attribute4 = attribute4;
    }

    @Override
    public String getAttribute5() {
        return attribute5;
    }

    @Override
    public void setAttribute5(String attribute5) {
        this.attribute5 = attribute5;
    }

    @Override
    public String getAttribute6() {
        return attribute6;
    }

    @Override
    public void setAttribute6(String attribute6) {
        this.attribute6 = attribute6;
    }

    @Override
    public String getAttribute7() {
        return attribute7;
    }

    @Override
    public void setAttribute7(String attribute7) {
        this.attribute7 = attribute7;
    }

    @Override
    public String getAttribute8() {
        return attribute8;
    }

    @Override
    public void setAttribute8(String attribute8) {
        this.attribute8 = attribute8;
    }

    @Override
    public String getAttribute9() {
        return attribute9;
    }

    @Override
    public void setAttribute9(String attribute9) {
        this.attribute9 = attribute9;
    }

    @Override
    public String getAttribute10() {
        return attribute10;
    }

    @Override
    public void setAttribute10(String attribute10) {
        this.attribute10 = attribute10;
    }

    @Override
    public String getAttribute11() {
        return attribute11;
    }

    @Override
    public void setAttribute11(String attribute11) {
        this.attribute11 = attribute11;
    }

    @Override
    public String getAttribute12() {
        return attribute12;
    }

    @Override
    public void setAttribute12(String attribute12) {
        this.attribute12 = attribute12;
    }

    @Override
    public String getAttribute13() {
        return attribute13;
    }

    @Override
    public void setAttribute13(String attribute13) {
        this.attribute13 = attribute13;
    }

    @Override
    public String getAttribute14() {
        return attribute14;
    }

    @Override
    public void setAttribute14(String attribute14) {
        this.attribute14 = attribute14;
    }

    @Override
    public String getAttribute15() {
        return attribute15;
    }

    @Override
    public void setAttribute15(String attribute15) {
        this.attribute15 = attribute15;
    }

    @Override
    public String getAttribute16() {
        return attribute16;
    }

    @Override
    public void setAttribute16(String attribute16) {
        this.attribute16 = attribute16;
    }

    @Override
    public String getAttribute17() {
        return attribute17;
    }

    @Override
    public void setAttribute17(String attribute17) {
        this.attribute17 = attribute17;
    }

    @Override
    public String getAttribute18() {
        return attribute18;
    }

    @Override
    public void setAttribute18(String attribute18) {
        this.attribute18 = attribute18;
    }

    @Override
    public String getAttribute19() {
        return attribute19;
    }

    @Override
    public void setAttribute19(String attribute19) {
        this.attribute19 = attribute19;
    }

    @Override
    public String getAttribute20() {
        return attribute20;
    }

    @Override
    public void setAttribute20(String attribute20) {
        this.attribute20 = attribute20;
    }

    @Override
    public String getAttribute21() {
        return attribute21;
    }

    @Override
    public void setAttribute21(String attribute21) {
        this.attribute21 = attribute21;
    }

    @Override
    public String getAttribute22() {
        return attribute22;
    }

    @Override
    public void setAttribute22(String attribute22) {
        this.attribute22 = attribute22;
    }

    @Override
    public String getAttribute23() {
        return attribute23;
    }

    @Override
    public void setAttribute23(String attribute23) {
        this.attribute23 = attribute23;
    }

    @Override
    public String getAttribute24() {
        return attribute24;
    }

    @Override
    public void setAttribute24(String attribute24) {
        this.attribute24 = attribute24;
    }

    @Override
    public String getAttribute25() {
        return attribute25;
    }

    @Override
    public void setAttribute25(String attribute25) {
        this.attribute25 = attribute25;
    }

    @Override
    public String getAttribute26() {
        return attribute26;
    }

    @Override
    public void setAttribute26(String attribute26) {
        this.attribute26 = attribute26;
    }

    @Override
    public String getAttribute27() {
        return attribute27;
    }

    @Override
    public void setAttribute27(String attribute27) {
        this.attribute27 = attribute27;
    }

    @Override
    public String getAttribute28() {
        return attribute28;
    }

    @Override
    public void setAttribute28(String attribute28) {
        this.attribute28 = attribute28;
    }

    @Override
    public String getAttribute29() {
        return attribute29;
    }

    @Override
    public void setAttribute29(String attribute29) {
        this.attribute29 = attribute29;
    }

    @Override
    public String getAttribute30() {
        return attribute30;
    }

    @Override
    public void setAttribute30(String attribute30) {
        this.attribute30 = attribute30;
    }

    @Override
    public String getAttribute31() {
        return attribute31;
    }

    @Override
    public void setAttribute31(String attribute31) {
        this.attribute31 = attribute31;
    }

    @Override
    public String getAttribute32() {
        return attribute32;
    }

    @Override
    public void setAttribute32(String attribute32) {
        this.attribute32 = attribute32;
    }

    @Override
    public String getAttribute33() {
        return attribute33;
    }

    @Override
    public void setAttribute33(String attribute33) {
        this.attribute33 = attribute33;
    }

    @Override
    public String getAttribute34() {
        return attribute34;
    }

    @Override
    public void setAttribute34(String attribute34) {
        this.attribute34 = attribute34;
    }

    @Override
    public String getAttribute35() {
        return attribute35;
    }

    @Override
    public void setAttribute35(String attribute35) {
        this.attribute35 = attribute35;
    }

    @Override
    public String getAttribute36() {
        return attribute36;
    }

    @Override
    public void setAttribute36(String attribute36) {
        this.attribute36 = attribute36;
    }

    @Override
    public String getAttribute37() {
        return attribute37;
    }

    @Override
    public void setAttribute37(String attribute37) {
        this.attribute37 = attribute37;
    }

    @Override
    public String getAttribute38() {
        return attribute38;
    }

    @Override
    public void setAttribute38(String attribute38) {
        this.attribute38 = attribute38;
    }

    @Override
    public String getAttribute39() {
        return attribute39;
    }

    @Override
    public void setAttribute39(String attribute39) {
        this.attribute39 = attribute39;
    }

    @Override
    public String getAttribute40() {
        return attribute40;
    }

    @Override
    public void setAttribute40(String attribute40) {
        this.attribute40 = attribute40;
    }

    /*-----------------LAttributes------------------------*/
//    public List<String> getLattribute1() {
//        return getLattribute(attribute1);
//    }
//
//    public void setLattribute1(List<String> lattribute1) {
//        setAttribute1(setLattribute(lattribute1));
//        this.lattribute1 = lattribute1;
//    }
//
//    public List<String> getLattribute2() {
//        return getLattribute(attribute2);
//    }
//
//    public void setLattribute2(List<String> lattribute2) {
//        setAttribute2(setLattribute(lattribute2));
//        this.lattribute2 = lattribute2;
//    }
//
//    public List<String> getLattribute3() {
//        return getLattribute(attribute3);
//    }
//
//    public void setLattribute3(List<String> lattribute3) {
//        setAttribute3(setLattribute(lattribute3));
//        this.lattribute3 = lattribute3;
//    }
//
//    public List<String> getLattribute4() {
//        return getLattribute(attribute4);
//    }
//
//    public void setLattribute4(List<String> lattribute4) {
//        setAttribute4(setLattribute(lattribute4));
//        this.lattribute4 = lattribute4;
//    }
//
//    public List<String> getLattribute5() {
//        return getLattribute(attribute5);
//    }
//
//    public void setLattribute5(List<String> lattribute5) {
//        setAttribute5(setLattribute(lattribute5));
//        this.lattribute5 = lattribute5;
//    }
//
//    public List<String> getLattribute6() {
//        return getLattribute(attribute6);
//    }
//
//    public void setLattribute6(List<String> lattribute6) {
//        setAttribute6(setLattribute(lattribute6));
//        this.lattribute6 = lattribute6;
//    }
//
//    public List<String> getLattribute7() {
//        return getLattribute(attribute7);
//    }
//
//    public void setLattribute7(List<String> lattribute7) {
//        setAttribute7(setLattribute(lattribute7));
//        this.lattribute7 = lattribute7;
//    }
//
//    public List<String> getLattribute8() {
//        return getLattribute(attribute8);
//    }
//
//    public void setLattribute8(List<String> lattribute8) {
//        setAttribute8(setLattribute(lattribute8));
//        this.lattribute8 = lattribute8;
//    }
//
//    public List<String> getLattribute9() {
//        return getLattribute(attribute9);
//    }
//
//    public void setLattribute9(List<String> lattribute9) {
//        setAttribute9(setLattribute(lattribute9));
//        this.lattribute9 = lattribute9;
//    }
//
//    public List<String> getLattribute10() {
//        return getLattribute(attribute10);
//    }
//
//    public void setLattribute10(List<String> lattribute10) {
//        setAttribute10(setLattribute(lattribute10));
//        this.lattribute10 = lattribute10;
//    }
//
//    public List<String> getLattribute11() {
//        return getLattribute(attribute11);
//    }
//
//    public void setLattribute11(List<String> lattribute11) {
//        setAttribute11(setLattribute(lattribute11));
//        this.lattribute11 = lattribute11;
//    }
//
//    public List<String> getLattribute12() {
//        return getLattribute(attribute12);
//    }
//
//    public void setLattribute12(List<String> lattribute12) {
//        setAttribute12(setLattribute(lattribute12));
//        this.lattribute12 = lattribute12;
//    }
//
//    public List<String> getLattribute13() {
//        return getLattribute(attribute13);
//    }
//
//    public void setLattribute13(List<String> lattribute13) {
//        setAttribute13(setLattribute(lattribute13));
//        this.lattribute13 = lattribute13;
//    }
//
//    public List<String> getLattribute14() {
//        return getLattribute(attribute14);
//    }
//
//    public void setLattribute14(List<String> lattribute14) {
//        setAttribute14(setLattribute(lattribute14));
//        this.lattribute14 = lattribute14;
//    }
//
//    public List<String> getLattribute15() {
//        return getLattribute(attribute15);
//    }
//
//    public void setLattribute15(List<String> lattribute15) {
//        setAttribute15(setLattribute(lattribute15));
//        this.lattribute15 = lattribute15;
//    }
//
//    public List<String> getLattribute16() {
//        return getLattribute(attribute16);
//    }
//
//    public void setLattribute16(List<String> lattribute16) {
//        setAttribute16(setLattribute(lattribute16));
//        this.lattribute16 = lattribute16;
//    }
//
//    public List<String> getLattribute17() {
//        return getLattribute(attribute17);
//    }
//
//    public void setLattribute17(List<String> lattribute17) {
//        setAttribute17(setLattribute(lattribute17));
//        this.lattribute17 = lattribute17;
//    }
//
//    public List<String> getLattribute18() {
//        return getLattribute(attribute18);
//    }
//
//    public void setLattribute18(List<String> lattribute18) {
//        setAttribute18(setLattribute(lattribute18));
//        this.lattribute18 = lattribute18;
//    }
//
//    public List<String> getLattribute19() {
//        return getLattribute(attribute19);
//    }
//
//    public void setLattribute19(List<String> lattribute19) {
//        setAttribute19(setLattribute(lattribute19));
//        this.lattribute19 = lattribute19;
//    }
//
//    public List<String> getLattribute20() {
//        return getLattribute(attribute20);
//    }
//
//    public void setLattribute20(List<String> lattribute20) {
//        setAttribute20(setLattribute(lattribute20));
//        this.lattribute20 = lattribute20;
//    }
//
//    public List<String> getLattribute21() {
//        return getLattribute(attribute21);
//    }
//
//    public void setLattribute21(List<String> lattribute21) {
//        setAttribute21(setLattribute(lattribute21));
//        this.lattribute21 = lattribute21;
//    }
//
//    public List<String> getLattribute22() {
//        return getLattribute(attribute22);
//    }
//
//    public void setLattribute22(List<String> lattribute22) {
//        setAttribute22(setLattribute(lattribute22));
//        this.lattribute22 = lattribute22;
//    }
//
//    public List<String> getLattribute23() {
//        return getLattribute(attribute23);
//    }
//
//    public void setLattribute23(List<String> lattribute23) {
//        setAttribute23(setLattribute(lattribute23));
//        this.lattribute23 = lattribute23;
//    }
//
//    public List<String> getLattribute24() {
//        return getLattribute(attribute24);
//    }
//
//    public void setLattribute24(List<String> lattribute24) {
//        setAttribute24(setLattribute(lattribute24));
//        this.lattribute24 = lattribute24;
//    }
//
//    public List<String> getLattribute25() {
//        return getLattribute(attribute25);
//    }
//
//    public void setLattribute25(List<String> lattribute25) {
//        setAttribute25(setLattribute(lattribute25));
//        this.lattribute25 = lattribute25;
//    }
//
//    public List<String> getLattribute26() {
//        return getLattribute(attribute26);
//    }
//
//    public void setLattribute26(List<String> lattribute26) {
//        setAttribute26(setLattribute(lattribute26));
//        this.lattribute26 = lattribute26;
//    }
//
//    public List<String> getLattribute27() {
//        return getLattribute(attribute27);
//    }
//
//    public void setLattribute27(List<String> lattribute27) {
//        setAttribute27(setLattribute(lattribute27));
//        this.lattribute27 = lattribute27;
//    }
//
//    public List<String> getLattribute28() {
//        return getLattribute(attribute28);
//    }
//
//    public void setLattribute28(List<String> lattribute28) {
//        setAttribute28(setLattribute(lattribute28));
//        this.lattribute28 = lattribute28;
//    }
//
//    public List<String> getLattribute29() {
//        return getLattribute(attribute29);
//    }
//
//    public void setLattribute29(List<String> lattribute29) {
//        setAttribute29(setLattribute(lattribute29));
//        this.lattribute29 = lattribute29;
//    }
//
//    public List<String> getLattribute30() {
//        return getLattribute(attribute30);
//    }
//
//    public void setLattribute30(List<String> lattribute30) {
//        setAttribute30(setLattribute(lattribute30));
//        this.lattribute30 = lattribute30;
//    }
//
//    public List<String> getLattribute31() {
//        return getLattribute(attribute31);
//    }
//
//    public void setLattribute31(List<String> lattribute31) {
//        setAttribute31(setLattribute(lattribute31));
//        this.lattribute31 = lattribute31;
//    }
//
//    public List<String> getLattribute32() {
//        return getLattribute(attribute32);
//    }
//
//    public void setLattribute32(List<String> lattribute32) {
//        setAttribute32(setLattribute(lattribute32));
//        this.lattribute32 = lattribute32;
//    }
//
//    public List<String> getLattribute33() {
//        return getLattribute(attribute33);
//    }
//
//    public void setLattribute33(List<String> lattribute33) {
//        setAttribute33(setLattribute(lattribute33));
//        this.lattribute33 = lattribute33;
//    }
//
//    public List<String> getLattribute34() {
//        return getLattribute(attribute34);
//    }
//
//    public void setLattribute34(List<String> lattribute34) {
//        setAttribute34(setLattribute(lattribute34));
//        this.lattribute34 = lattribute34;
//    }
//
//    public List<String> getLattribute35() {
//        return getLattribute(attribute35);
//    }
//
//    public void setLattribute35(List<String> lattribute35) {
//        setAttribute35(setLattribute(lattribute35));
//        this.lattribute35 = lattribute35;
//    }
//
//    public List<String> getLattribute36() {
//        return getLattribute(attribute36);
//    }
//
//    public void setLattribute36(List<String> lattribute36) {
//        setAttribute36(setLattribute(lattribute36));
//        this.lattribute36 = lattribute36;
//    }
//
//    public List<String> getLattribute37() {
//        return getLattribute(attribute37);
//    }
//
//    public void setLattribute37(List<String> lattribute37) {
//        setAttribute37(setLattribute(lattribute37));
//        this.lattribute37 = lattribute37;
//    }
//
//    public List<String> getLattribute38() {
//        return getLattribute(attribute38);
//    }
//
//    public void setLattribute38(List<String> lattribute38) {
//        setAttribute38(setLattribute(lattribute38));
//        this.lattribute38 = lattribute38;
//    }
//
//    public List<String> getLattribute39() {
//        return getLattribute(attribute39);
//    }
//
//    public void setLattribute39(List<String> lattribute39) {
//        setAttribute39(setLattribute(lattribute39));
//        this.lattribute39 = lattribute39;
//    }
//
//    public List<String> getLattribute40() {
//        return getLattribute(attribute40);
//    }
//
//    public void setLattribute40(List<String> lattribute40) {
//        setAttribute40(setLattribute(lattribute40));
//        this.lattribute40 = lattribute40;
//    }
    public List<String> getLattribute1() {
        return lattribute1;
    }

    public void setLattribute1(List<String> lattribute1) {
        setAttribute1(aggregateValues(lattribute1));
        this.lattribute1 = lattribute1;
    }

    public List<String> getLattribute2() {
        return lattribute2;
    }

    public void setLattribute2(List<String> lattribute2) {
        setAttribute2(aggregateValues(lattribute2));
        this.lattribute2 = lattribute2;
    }

    public List<String> getLattribute3() {
        return lattribute3;
    }

    public void setLattribute3(List<String> lattribute3) {
        setAttribute3(aggregateValues(lattribute3));
        this.lattribute3 = lattribute3;
    }

    public List<String> getLattribute4() {
        return lattribute4;
    }

    public void setLattribute4(List<String> lattribute4) {
        setAttribute4(aggregateValues(lattribute4));
        this.lattribute4 = lattribute4;
    }

    public List<String> getLattribute5() {
        return lattribute5;
    }

    public void setLattribute5(List<String> lattribute5) {
        setAttribute5(aggregateValues(lattribute5));
        this.lattribute5 = lattribute5;
    }

    public List<String> getLattribute6() {
        return lattribute6;
    }

    public void setLattribute6(List<String> lattribute6) {
        setAttribute6(aggregateValues(lattribute6));
        this.lattribute6 = lattribute6;
    }

    public List<String> getLattribute7() {
        return lattribute7;
    }

    public void setLattribute7(List<String> lattribute7) {
        setAttribute7(aggregateValues(lattribute7));
        this.lattribute7 = lattribute7;
    }

    public List<String> getLattribute8() {
        return lattribute8;
    }

    public void setLattribute8(List<String> lattribute8) {
        setAttribute8(aggregateValues(lattribute8));
        this.lattribute8 = lattribute8;
    }

    public List<String> getLattribute9() {
        return lattribute9;
    }

    public void setLattribute9(List<String> lattribute9) {
        setAttribute9(aggregateValues(lattribute9));
        this.lattribute9 = lattribute9;
    }

    public List<String> getLattribute10() {
        return lattribute10;
    }

    public void setLattribute10(List<String> lattribute10) {
        setAttribute10(aggregateValues(lattribute10));
        this.lattribute10 = lattribute10;
    }

    public List<String> getLattribute11() {
        return lattribute11;
    }

    public void setLattribute11(List<String> lattribute11) {
        setAttribute11(aggregateValues(lattribute11));
        this.lattribute11 = lattribute11;
    }

    public List<String> getLattribute12() {
        return lattribute12;
    }

    public void setLattribute12(List<String> lattribute12) {
        setAttribute12(aggregateValues(lattribute12));
        this.lattribute12 = lattribute12;
    }

    public List<String> getLattribute13() {
        return lattribute13;
    }

    public void setLattribute13(List<String> lattribute13) {
        setAttribute13(aggregateValues(lattribute13));
        this.lattribute13 = lattribute13;
    }

    public List<String> getLattribute14() {
        return lattribute14;
    }

    public void setLattribute14(List<String> lattribute14) {
        setAttribute14(aggregateValues(lattribute14));
        this.lattribute14 = lattribute14;
    }

    public List<String> getLattribute15() {
        return lattribute15;
    }

    public void setLattribute15(List<String> lattribute15) {
        setAttribute15(aggregateValues(lattribute15));
        this.lattribute15 = lattribute15;
    }

    public List<String> getLattribute16() {
        return lattribute16;
    }

    public void setLattribute16(List<String> lattribute16) {
        setAttribute16(aggregateValues(lattribute16));
        this.lattribute16 = lattribute16;
    }

    public List<String> getLattribute17() {
        return lattribute17;
    }

    public void setLattribute17(List<String> lattribute17) {
        setAttribute17(aggregateValues(lattribute17));
        this.lattribute17 = lattribute17;
    }

    public List<String> getLattribute18() {
        return lattribute18;
    }

    public void setLattribute18(List<String> lattribute18) {
        setAttribute18(aggregateValues(lattribute18));
        this.lattribute18 = lattribute18;
    }

    public List<String> getLattribute19() {
        return lattribute19;
    }

    public void setLattribute19(List<String> lattribute19) {
        setAttribute19(aggregateValues(lattribute19));
        this.lattribute19 = lattribute19;
    }

    public List<String> getLattribute20() {
        return lattribute20;
    }

    public void setLattribute20(List<String> lattribute20) {
        setAttribute20(aggregateValues(lattribute20));
        this.lattribute20 = lattribute20;
    }

    public List<String> getLattribute21() {
        return lattribute21;
    }

    public void setLattribute21(List<String> lattribute21) {
        setAttribute21(aggregateValues(lattribute21));
        this.lattribute21 = lattribute21;
    }

    public List<String> getLattribute22() {
        return lattribute22;
    }

    public void setLattribute22(List<String> lattribute22) {
        setAttribute22(aggregateValues(lattribute22));
        this.lattribute22 = lattribute22;
    }

    public List<String> getLattribute23() {
        return lattribute23;
    }

    public void setLattribute23(List<String> lattribute23) {
        setAttribute23(aggregateValues(lattribute23));
        this.lattribute23 = lattribute23;
    }

    public List<String> getLattribute24() {
        return lattribute24;
    }

    public void setLattribute24(List<String> lattribute24) {
        setAttribute24(aggregateValues(lattribute24));
        this.lattribute24 = lattribute24;
    }

    public List<String> getLattribute25() {
        return lattribute25;
    }

    public void setLattribute25(List<String> lattribute25) {
        setAttribute25(aggregateValues(lattribute25));
        this.lattribute25 = lattribute25;
    }

    public List<String> getLattribute26() {
        return lattribute26;
    }

    public void setLattribute26(List<String> lattribute26) {
        setAttribute26(aggregateValues(lattribute26));
        this.lattribute26 = lattribute26;
    }

    public List<String> getLattribute27() {
        return lattribute27;
    }

    public void setLattribute27(List<String> lattribute27) {
        setAttribute27(aggregateValues(lattribute27));
        this.lattribute27 = lattribute27;
    }

    public List<String> getLattribute28() {
        return lattribute28;
    }

    public void setLattribute28(List<String> lattribute28) {
        setAttribute28(aggregateValues(lattribute28));
        this.lattribute28 = lattribute28;
    }

    public List<String> getLattribute29() {
        return lattribute29;
    }

    public void setLattribute29(List<String> lattribute29) {
        setAttribute29(aggregateValues(lattribute29));
        this.lattribute29 = lattribute29;
    }

    public List<String> getLattribute30() {
        return lattribute30;
    }

    public void setLattribute30(List<String> lattribute30) {
        setAttribute30(aggregateValues(lattribute30));
        this.lattribute30 = lattribute30;
    }

    public List<String> getLattribute31() {
        return lattribute31;
    }

    public void setLattribute31(List<String> lattribute31) {
        setAttribute31(aggregateValues(lattribute31));
        this.lattribute31 = lattribute31;
    }

    public List<String> getLattribute32() {
        return lattribute32;
    }

    public void setLattribute32(List<String> lattribute32) {
        setAttribute32(aggregateValues(lattribute32));
        this.lattribute32 = lattribute32;
    }

    public List<String> getLattribute33() {
        return lattribute33;
    }

    public void setLattribute33(List<String> lattribute33) {
        setAttribute33(aggregateValues(lattribute33));
        this.lattribute33 = lattribute33;
    }

    public List<String> getLattribute34() {
        return lattribute34;
    }

    public void setLattribute34(List<String> lattribute34) {
        setAttribute34(aggregateValues(lattribute34));
        this.lattribute34 = lattribute34;
    }

    public List<String> getLattribute35() {
        return lattribute35;
    }

    public void setLattribute35(List<String> lattribute35) {
        setAttribute35(aggregateValues(lattribute35));
        this.lattribute35 = lattribute35;
    }

    public List<String> getLattribute36() {
        return lattribute36;
    }

    public void setLattribute36(List<String> lattribute36) {
        setAttribute36(aggregateValues(lattribute36));
        this.lattribute36 = lattribute36;
    }

    public List<String> getLattribute37() {
        return lattribute37;
    }

    public void setLattribute37(List<String> lattribute37) {
        setAttribute37(aggregateValues(lattribute37));
        this.lattribute37 = lattribute37;
    }

    public List<String> getLattribute38() {
        return lattribute38;
    }

    public void setLattribute38(List<String> lattribute38) {
        setAttribute38(aggregateValues(lattribute38));
        this.lattribute38 = lattribute38;
    }

    public List<String> getLattribute39() {
        return lattribute39;
    }

    public void setLattribute39(List<String> lattribute39) {
        setAttribute39(aggregateValues(lattribute39));
        this.lattribute39 = lattribute39;
    }

    public List<String> getLattribute40() {
        return lattribute40;
    }

    public void setLattribute40(List<String> lattribute40) {
        setAttribute40(aggregateValues(lattribute40));
        this.lattribute40 = lattribute40;
    }

    //////////////////////////////////
//    private List<String> getLattribute(String attribute) {
//        List<String> lattribute = null;
//        if (attribute != null) {
//            StringTokenizer st = new StringTokenizer(attribute, ";");
//            lattribute = new ArrayList<>();
//            while (st.hasMoreTokens()) {
//                lattribute.add(st.nextToken());
//            }
//        }
//        return lattribute;
//    }
//
    @Transient
    public String aggregateValues(List<String> lattribute) {
        attr.setLength(0);
        if (lattribute != null) {
            for (String s : lattribute) {
                attr.append(s);
                attr.append(";");
            }
        }

        return attr.toString();
    }

}
