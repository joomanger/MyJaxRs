package so.saleorder.flows;

import item.entities.Item;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.faces.flow.FlowScoped;
import javax.inject.Named;
import so.config.entity.ConfigurationLine;
import so.config.entity.ParameterConfiguration;
import so.entities.OrderLine;

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
    private Map<Long, List<ConfigurationLine>> editableCells = new HashMap<>();
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

    public Map<Long, List<ConfigurationLine>> getEditableCells() {
        return editableCells;
    }

    public void setEditableCells(Map<Long, List<ConfigurationLine>> editableCells) {
        this.editableCells = editableCells;
    }

    public Long getParameter_id() {
        return parameter_id;
    }

    public void setParameter_id(Long parameter_id) {
        this.parameter_id = parameter_id;
    }
    
    
}
