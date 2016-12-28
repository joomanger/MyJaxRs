package flows.so.saleorder;

import entities.item.Item;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.faces.flow.FlowScoped;
import javax.inject.Named;
import org.primefaces.event.CellEditEvent;
import so.config.rules.NumericRule;
import entities.so.config.ParameterConfiguration;
import entities.so.OrderLine;

/**
 *
 * @author savin
 */
@Named
@FlowScoped("Order")
public class CreateOrderLineFlow implements Serializable {

    private Item item;
    private List<OrderLine> lines = new ArrayList<>();
    private List<OrderLine> selectedLines = new ArrayList<>();
    private Set<ParameterConfiguration> parameters;
    //private Map<Long, List<ConfigurationLine>> editableCells = new HashMap<>();
    private Set<String> editableCells = new HashSet<>();
    private Set<Long> newItems = new HashSet<>();
    private Long parameter_id;

    private short lineNumber;

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public List<OrderLine> getLines() {
        return lines;
    }

    public void setLines(List<OrderLine> lines) {
        this.lines = lines;
    }

    public List<OrderLine> getSelectedLines() {
        return selectedLines;
    }

    public void setSelectedLines(List<OrderLine> selectedLines) {
        this.selectedLines = selectedLines;
    }

    public short getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(short lineNumber) {
        this.lineNumber = lineNumber;
    }

    public Set<ParameterConfiguration> getParameters() {
        return parameters;
    }

    public void setParameters(Set<ParameterConfiguration> parameters) {
        this.parameters = parameters;
    }

//    public Map<Long, List<ConfigurationLine>> getEditableCells() {
//        return editableCells;
//    }
//
//    public void setEditableCells(Map<Long, List<ConfigurationLine>> editableCells) {
//        this.editableCells = editableCells;
//    }
    public Set<String> getEditableCells() {
        return editableCells;
    }

    public void setEditableCells(Set<String> editableCells) {
        this.editableCells = editableCells;
    }

    public Long getParameter_id() {
        return parameter_id;
    }

    public void setParameter_id(Long parameter_id) {
        this.parameter_id = parameter_id;
    }

    public Set<Long> getNewItems() {
        return newItems;
    }

    public void setNewItems(Set<Long> newItems) {
        this.newItems = newItems;
    }

    public void onCellEdit(CellEditEvent event) {
        OrderLine line=lines.get(event.getRowIndex());
        NumericRule r=new NumericRule(line, "Number(attribute1+attribute2+attribute3).toFixed(3)", "attribute4");
        //NumericRule r=new NumericRule(line, "'attribute18'", "attribute4");
        r.calculation();
    }

}
