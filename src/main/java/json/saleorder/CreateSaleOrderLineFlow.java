/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package json.saleorder;

import com.isd.myjaxrs.entity.SaleOrderLine;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.ejb.Stateless;
import javax.faces.flow.FlowScoped;
import javax.inject.Named;

/**
 *
 * @author savin
 */
@Named
@Stateless
@FlowScoped("order")
public class CreateSaleOrderLineFlow implements Serializable {

    private short line_num = 0;
    private short selected_line_num;

    private String item;
    private Double quantity;
    private Double price;
    private List<SaleOrderLine> lines = new ArrayList<>();
    private Map<Short, SaleOrderLine> linez = new HashMap<>();

    public short getSelected_line_num() {
        return selected_line_num;
    }

    public void setSelected_line_num(short selected_line_num) {
        this.selected_line_num = selected_line_num;
    }

    public short getLine_num() {
        return line_num;
    }

    public void setLine_num(short line_num) {
        this.line_num = line_num;
    }

    public List<SaleOrderLine> getLines() {
        lines.clear();
        for (Entry<Short, SaleOrderLine> mp : linez.entrySet()) {
            lines.add(mp.getValue());
        }
        return lines;
    }

    public void setLines(List<SaleOrderLine> lines) {
        this.lines = lines;
    }

    public void addLine() {
        line_num++;
        SaleOrderLine line = new SaleOrderLine();
        line.setItem(item);
        line.setPrice(price);
        line.setQuantity(quantity);
        line.setLine_num(line_num);
        linez.put(line_num, line);
    }

    public void delItem() {
        linez.remove(selected_line_num);
    }

    public Map<Short, SaleOrderLine> getLinez() {
        return linez;
    }

    public void setLinez(Map<Short, SaleOrderLine> linez) {
        this.linez = linez;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
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

}
