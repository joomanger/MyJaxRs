package com.isd.myjaxrs.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author savin
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = SaleOrderLine.FIND_BY_HEADER_ID, query = "select b from SaleOrderLine b where b.header_id=:p_header_id order by b.line_num"),
    @NamedQuery(name = SaleOrderLine.MAX_LINE_NUM_BY_HEADER_ID, query = "select max(b.line_num) from SaleOrderLine b where b.header_id=:p_header_id")}
)
public class SaleOrderLine implements Serializable {

    public static final String FIND_BY_HEADER_ID = "FBHI";
    public static final String MAX_LINE_NUM_BY_HEADER_ID = "MALBHI";
    @Id
    @SequenceGenerator(name = "order_line_sq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(generator = "order_line_sq")
    private long line_id;

//    private String item;
    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

//    private Long item_id;
    private Integer config_ver_num;

    private short line_num;
    private Double quantity;
    private Double quantity2;
    private String uom;
    private String uom2;
    private Double price;
    private Long header_id;
    //Атрибуты конфигурации
    private String attribute1;
    private String attribute2;
    private String attribute3;
    private String attribute4;
    private String attribute5;
    private String attribute6;
    private String attribute7;
    private String attribute8;
    private String attribute9;
    private String attribute10;
    private String attribute11;
    private String attribute12;
    private String attribute13;
    private String attribute14;
    private String attribute15;
    private String attribute16;
    private String attribute17;
    private String attribute18;
    private String attribute19;
    private String attribute20;
    private String attribute21;
    private String attribute22;
    private String attribute23;
    private String attribute24;
    private String attribute25;
    private String attribute26;
    private String attribute27;
    private String attribute28;
    private String attribute29;
    private String attribute30;
    private String attribute31;
    private String attribute32;
    private String attribute33;
    private String attribute34;
    private String attribute35;
    private String attribute36;
    private String attribute37;
    private String attribute38;
    private String attribute39;
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

    public long getLine_id() {
        return line_id;
    }

    public void setLine_id(long line_id) {
        this.line_id = line_id;
    }

    public short getLine_num() {
        return line_num;
    }

    public void setLine_num(short line_num) {
        this.line_num = line_num;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Double getQuantity2() {
        return quantity2;
    }

    public void setQuantity2(Double quantity2) {
        this.quantity2 = quantity2;
    }

    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }

    public String getUom2() {
        return uom2;
    }

    public void setUom2(String uom2) {
        this.uom2 = uom2;
    }

//    public String getItem() {
//        return item;
//    }
//
//    public void setItem(String item) {
//        this.item = item;
//    }
//
//    public Long getItem_id() {
//        return item_id;
//    }
//
//    public void setItem_id(Long item_id) {
//        this.item_id = item_id;
//    }
    public Integer getConfig_ver_num() {
        return config_ver_num;
    }

    public void setConfig_ver_num(Integer config_ver_num) {
        this.config_ver_num = config_ver_num;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getHeader_id() {
        return header_id;
    }

    public void setHeader_id(Long header_id) {
        this.header_id = header_id;
    }

    //Атрибуты конфигурации
    public String getAttribute1() {
        return attribute1;
    }

    public void setAttribute1(String attribute1) {
        this.attribute1 = attribute1;
    }

    public String getAttribute2() {
        return attribute2;
    }

    public void setAttribute2(String attribute2) {
        this.attribute2 = attribute2;
    }

    public String getAttribute3() {
        return attribute3;
    }

    public void setAttribute3(String attribute3) {
        this.attribute3 = attribute3;
    }

    public String getAttribute4() {
        return attribute4;
    }

    public void setAttribute4(String attribute4) {
        this.attribute4 = attribute4;
    }

    public String getAttribute5() {
        return attribute5;
    }

    public void setAttribute5(String attribute5) {
        this.attribute5 = attribute5;
    }

    public String getAttribute6() {
        return attribute6;
    }

    public void setAttribute6(String attribute6) {
        this.attribute6 = attribute6;
    }

    public String getAttribute7() {
        return attribute7;
    }

    public void setAttribute7(String attribute7) {
        this.attribute7 = attribute7;
    }

    public String getAttribute8() {
        return attribute8;
    }

    public void setAttribute8(String attribute8) {
        this.attribute8 = attribute8;
    }

    public String getAttribute9() {
        return attribute9;
    }

    public void setAttribute9(String attribute9) {
        this.attribute9 = attribute9;
    }

    public String getAttribute10() {
        return attribute10;
    }

    public void setAttribute10(String attribute10) {
        this.attribute10 = attribute10;
    }

    public String getAttribute11() {
        return attribute11;
    }

    public void setAttribute11(String attribute11) {
        this.attribute11 = attribute11;
    }

    public String getAttribute12() {
        return attribute12;
    }

    public void setAttribute12(String attribute12) {
        this.attribute12 = attribute12;
    }

    public String getAttribute13() {
        return attribute13;
    }

    public void setAttribute13(String attribute13) {
        this.attribute13 = attribute13;
    }

    public String getAttribute14() {
        return attribute14;
    }

    public void setAttribute14(String attribute14) {
        this.attribute14 = attribute14;
    }

    public String getAttribute15() {
        return attribute15;
    }

    public void setAttribute15(String attribute15) {
        this.attribute15 = attribute15;
    }

    public String getAttribute16() {
        return attribute16;
    }

    public void setAttribute16(String attribute16) {
        this.attribute16 = attribute16;
    }

    public String getAttribute17() {
        return attribute17;
    }

    public void setAttribute17(String attribute17) {
        this.attribute17 = attribute17;
    }

    public String getAttribute18() {
        return attribute18;
    }

    public void setAttribute18(String attribute18) {
        this.attribute18 = attribute18;
    }

    public String getAttribute19() {
        return attribute19;
    }

    public void setAttribute19(String attribute19) {
        this.attribute19 = attribute19;
    }

    public String getAttribute20() {
        return attribute20;
    }

    public void setAttribute20(String attribute20) {
        this.attribute20 = attribute20;
    }

    public String getAttribute21() {
        return attribute21;
    }

    public void setAttribute21(String attribute21) {
        this.attribute21 = attribute21;
    }

    public String getAttribute22() {
        return attribute22;
    }

    public void setAttribute22(String attribute22) {
        this.attribute22 = attribute22;
    }

    public String getAttribute23() {
        return attribute23;
    }

    public void setAttribute23(String attribute23) {
        this.attribute23 = attribute23;
    }

    public String getAttribute24() {
        return attribute24;
    }

    public void setAttribute24(String attribute24) {
        this.attribute24 = attribute24;
    }

    public String getAttribute25() {
        return attribute25;
    }

    public void setAttribute25(String attribute25) {
        this.attribute25 = attribute25;
    }

    public String getAttribute26() {
        return attribute26;
    }

    public void setAttribute26(String attribute26) {
        this.attribute26 = attribute26;
    }

    public String getAttribute27() {
        return attribute27;
    }

    public void setAttribute27(String attribute27) {
        this.attribute27 = attribute27;
    }

    public String getAttribute28() {
        return attribute28;
    }

    public void setAttribute28(String attribute28) {
        this.attribute28 = attribute28;
    }

    public String getAttribute29() {
        return attribute29;
    }

    public void setAttribute29(String attribute29) {
        this.attribute29 = attribute29;
    }

    public String getAttribute30() {
        return attribute30;
    }

    public void setAttribute30(String attribute30) {
        this.attribute30 = attribute30;
    }

    public String getAttribute31() {
        return attribute31;
    }

    public void setAttribute31(String attribute31) {
        this.attribute31 = attribute31;
    }

    public String getAttribute32() {
        return attribute32;
    }

    public void setAttribute32(String attribute32) {
        this.attribute32 = attribute32;
    }

    public String getAttribute33() {
        return attribute33;
    }

    public void setAttribute33(String attribute33) {
        this.attribute33 = attribute33;
    }

    public String getAttribute34() {
        return attribute34;
    }

    public void setAttribute34(String attribute34) {
        this.attribute34 = attribute34;
    }

    public String getAttribute35() {
        return attribute35;
    }

    public void setAttribute35(String attribute35) {
        this.attribute35 = attribute35;
    }

    public String getAttribute36() {
        return attribute36;
    }

    public void setAttribute36(String attribute36) {
        this.attribute36 = attribute36;
    }

    public String getAttribute37() {
        return attribute37;
    }

    public void setAttribute37(String attribute37) {
        this.attribute37 = attribute37;
    }

    public String getAttribute38() {
        return attribute38;
    }

    public void setAttribute38(String attribute38) {
        this.attribute38 = attribute38;
    }

    public String getAttribute39() {
        return attribute39;
    }

    public void setAttribute39(String attribute39) {
        this.attribute39 = attribute39;
    }

    public String getAttribute40() {
        return attribute40;
    }

    public void setAttribute40(String attribute40) {
        this.attribute40 = attribute40;
    }

    //////////////////////////////////
    private List<String> getLattribute(String attribute, List<String> lattribute) {
        if (attribute != null) {
            StringTokenizer st = new StringTokenizer(attribute, ";");
            lattribute = new ArrayList<>();
            while (st.hasMoreTokens()) {
                lattribute.add(st.nextToken());
            }
        }
        return lattribute;
    }

    private String setLattribute(List<String> lattribute) {
        String attr = "";
        if (lattribute != null) {
            for (String s : lattribute) {
                attr += s + ";";
            }
        }
        return attr;
    }

//    public List<String> getLattribute4() {
//        return getLattribute(attribute4, lattribute4);
//    }
//
//    public void setLattribute4(List<String> lattribute4) {
//        setAttribute4(setLattribute(lattribute4));
//        this.lattribute4 = lattribute4;
//
//    }
//
//    public List<String> getLattribute5() {
//         return getLattribute(attribute5, lattribute5);
//    }
//
//    public void setLattribute5(List<String> lattribute5) {
//        setAttribute5(setLattribute(lattribute5));
//        this.lattribute5 = lattribute5;
//    }
    public List<String> getLattribute1() {
        return getLattribute(attribute1, lattribute1);
    }

    public List<String> getLattribute2() {
        return getLattribute(attribute2, lattribute2);
    }

    public List<String> getLattribute3() {
        return getLattribute(attribute3, lattribute3);
    }

    public List<String> getLattribute4() {
        return getLattribute(attribute4, lattribute4);
    }

    public List<String> getLattribute5() {
        return getLattribute(attribute5, lattribute5);
    }

    public List<String> getLattribute6() {
        return getLattribute(attribute6, lattribute6);
    }

    public List<String> getLattribute7() {
        return getLattribute(attribute7, lattribute7);
    }

    public List<String> getLattribute8() {
        return getLattribute(attribute8, lattribute8);
    }

    public List<String> getLattribute9() {
        return getLattribute(attribute9, lattribute9);
    }

    public List<String> getLattribute10() {
        return getLattribute(attribute10, lattribute10);
    }

    public List<String> getLattribute11() {
        return getLattribute(attribute11, lattribute11);
    }

    public List<String> getLattribute12() {
        return getLattribute(attribute12, lattribute12);
    }

    public List<String> getLattribute13() {
        return getLattribute(attribute13, lattribute13);
    }

    public List<String> getLattribute14() {
        return getLattribute(attribute14, lattribute14);
    }

    public List<String> getLattribute15() {
        return getLattribute(attribute15, lattribute15);
    }

    public List<String> getLattribute16() {
        return getLattribute(attribute16, lattribute16);
    }

    public List<String> getLattribute17() {
        return getLattribute(attribute17, lattribute17);
    }

    public List<String> getLattribute18() {
        return getLattribute(attribute18, lattribute18);
    }

    public List<String> getLattribute19() {
        return getLattribute(attribute19, lattribute19);
    }

    public List<String> getLattribute20() {
        return getLattribute(attribute20, lattribute20);
    }

    public List<String> getLattribute21() {
        return getLattribute(attribute21, lattribute21);
    }

    public List<String> getLattribute22() {
        return getLattribute(attribute22, lattribute22);
    }

    public List<String> getLattribute23() {
        return getLattribute(attribute23, lattribute23);
    }

    public List<String> getLattribute24() {
        return getLattribute(attribute24, lattribute24);
    }

    public List<String> getLattribute25() {
        return getLattribute(attribute25, lattribute25);
    }

    public List<String> getLattribute26() {
        return getLattribute(attribute26, lattribute26);
    }

    public List<String> getLattribute27() {
        return getLattribute(attribute27, lattribute27);
    }

    public List<String> getLattribute28() {
        return getLattribute(attribute28, lattribute28);
    }

    public List<String> getLattribute29() {
        return getLattribute(attribute29, lattribute29);
    }

    public List<String> getLattribute30() {
        return getLattribute(attribute30, lattribute30);
    }

    public List<String> getLattribute31() {
        return getLattribute(attribute31, lattribute31);
    }

    public List<String> getLattribute32() {
        return getLattribute(attribute32, lattribute32);
    }

    public List<String> getLattribute33() {
        return getLattribute(attribute33, lattribute33);
    }

    public List<String> getLattribute34() {
        return getLattribute(attribute34, lattribute34);
    }

    public List<String> getLattribute35() {
        return getLattribute(attribute35, lattribute35);
    }

    public List<String> getLattribute36() {
        return getLattribute(attribute36, lattribute36);
    }

    public List<String> getLattribute37() {
        return getLattribute(attribute37, lattribute37);
    }

    public List<String> getLattribute38() {
        return getLattribute(attribute38, lattribute38);
    }

    public List<String> getLattribute39() {
        return getLattribute(attribute39, lattribute39);
    }

    public List<String> getLattribute40() {
        return getLattribute(attribute40, lattribute40);
    }

    public void setLattribute1(List<String> lattribute1) {
        setAttribute1(setLattribute(lattribute1));
        this.lattribute1 = lattribute1;
    }

    public void setLattribute2(List<String> lattribute2) {
        setAttribute2(setLattribute(lattribute2));
        this.lattribute2 = lattribute2;
    }

    public void setLattribute3(List<String> lattribute3) {
        setAttribute3(setLattribute(lattribute3));
        this.lattribute3 = lattribute3;
    }

    public void setLattribute4(List<String> lattribute4) {
        setAttribute4(setLattribute(lattribute4));
        this.lattribute4 = lattribute4;
    }

    public void setLattribute5(List<String> lattribute5) {
        setAttribute5(setLattribute(lattribute5));
        this.lattribute5 = lattribute5;
    }

    public void setLattribute6(List<String> lattribute6) {
        setAttribute6(setLattribute(lattribute6));
        this.lattribute6 = lattribute6;
    }

    public void setLattribute7(List<String> lattribute7) {
        setAttribute7(setLattribute(lattribute7));
        this.lattribute7 = lattribute7;
    }

    public void setLattribute8(List<String> lattribute8) {
        setAttribute8(setLattribute(lattribute8));
        this.lattribute8 = lattribute8;
    }

    public void setLattribute9(List<String> lattribute9) {
        setAttribute9(setLattribute(lattribute9));
        this.lattribute9 = lattribute9;
    }

    public void setLattribute10(List<String> lattribute10) {
        setAttribute10(setLattribute(lattribute10));
        this.lattribute10 = lattribute10;
    }

    public void setLattribute11(List<String> lattribute11) {
        setAttribute11(setLattribute(lattribute11));
        this.lattribute11 = lattribute11;
    }

    public void setLattribute12(List<String> lattribute12) {
        setAttribute12(setLattribute(lattribute12));
        this.lattribute12 = lattribute12;
    }

    public void setLattribute13(List<String> lattribute13) {
        setAttribute13(setLattribute(lattribute13));
        this.lattribute13 = lattribute13;
    }

    public void setLattribute14(List<String> lattribute14) {
        setAttribute14(setLattribute(lattribute14));
        this.lattribute14 = lattribute14;
    }

    public void setLattribute15(List<String> lattribute15) {
        setAttribute15(setLattribute(lattribute15));
        this.lattribute15 = lattribute15;
    }

    public void setLattribute16(List<String> lattribute16) {
        setAttribute16(setLattribute(lattribute16));
        this.lattribute16 = lattribute16;
    }

    public void setLattribute17(List<String> lattribute17) {
        setAttribute17(setLattribute(lattribute17));
        this.lattribute17 = lattribute17;
    }

    public void setLattribute18(List<String> lattribute18) {
        setAttribute18(setLattribute(lattribute18));
        this.lattribute18 = lattribute18;
    }

    public void setLattribute19(List<String> lattribute19) {
        setAttribute19(setLattribute(lattribute19));
        this.lattribute19 = lattribute19;
    }

    public void setLattribute20(List<String> lattribute20) {
        setAttribute20(setLattribute(lattribute20));
        this.lattribute20 = lattribute20;
    }

    public void setLattribute21(List<String> lattribute21) {
        setAttribute21(setLattribute(lattribute21));
        this.lattribute21 = lattribute21;
    }

    public void setLattribute22(List<String> lattribute22) {
        setAttribute22(setLattribute(lattribute22));
        this.lattribute22 = lattribute22;
    }

    public void setLattribute23(List<String> lattribute23) {
        setAttribute23(setLattribute(lattribute23));
        this.lattribute23 = lattribute23;
    }

    public void setLattribute24(List<String> lattribute24) {
        setAttribute24(setLattribute(lattribute24));
        this.lattribute24 = lattribute24;
    }

    public void setLattribute25(List<String> lattribute25) {
        setAttribute25(setLattribute(lattribute25));
        this.lattribute25 = lattribute25;
    }

    public void setLattribute26(List<String> lattribute26) {
        setAttribute26(setLattribute(lattribute26));
        this.lattribute26 = lattribute26;
    }

    public void setLattribute27(List<String> lattribute27) {
        setAttribute27(setLattribute(lattribute27));
        this.lattribute27 = lattribute27;
    }

    public void setLattribute28(List<String> lattribute28) {
        setAttribute28(setLattribute(lattribute28));
        this.lattribute28 = lattribute28;
    }

    public void setLattribute29(List<String> lattribute29) {
        setAttribute29(setLattribute(lattribute29));
        this.lattribute29 = lattribute29;
    }

    public void setLattribute30(List<String> lattribute30) {
        setAttribute30(setLattribute(lattribute30));
        this.lattribute30 = lattribute30;
    }

    public void setLattribute31(List<String> lattribute31) {
        setAttribute31(setLattribute(lattribute31));
        this.lattribute31 = lattribute31;
    }

    public void setLattribute32(List<String> lattribute32) {
        setAttribute32(setLattribute(lattribute32));
        this.lattribute32 = lattribute32;
    }

    public void setLattribute33(List<String> lattribute33) {
        setAttribute33(setLattribute(lattribute33));
        this.lattribute33 = lattribute33;
    }

    public void setLattribute34(List<String> lattribute34) {
        setAttribute34(setLattribute(lattribute34));
        this.lattribute34 = lattribute34;
    }

    public void setLattribute35(List<String> lattribute35) {
        setAttribute35(setLattribute(lattribute35));
        this.lattribute35 = lattribute35;
    }

    public void setLattribute36(List<String> lattribute36) {
        setAttribute36(setLattribute(lattribute36));
        this.lattribute36 = lattribute36;
    }

    public void setLattribute37(List<String> lattribute37) {
        setAttribute37(setLattribute(lattribute37));
        this.lattribute37 = lattribute37;
    }

    public void setLattribute38(List<String> lattribute38) {
        setAttribute38(setLattribute(lattribute38));
        this.lattribute38 = lattribute38;
    }

    public void setLattribute39(List<String> lattribute39) {
        setAttribute39(setLattribute(lattribute39));
        this.lattribute39 = lattribute39;
    }

    public void setLattribute40(List<String> lattribute40) {
        setAttribute40(setLattribute(lattribute40));
        this.lattribute40 = lattribute40;
    }
}
