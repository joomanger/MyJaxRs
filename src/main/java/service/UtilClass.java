package service;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author savin
 */
@XmlRootElement
public class UtilClass {

    private List<String> columns;

    public UtilClass() {
        super();
        columns = new ArrayList<>();
    }

    public List<String> getColumns() {
        return columns;
    }

    public void setColumns(List<String> columns) {
        this.columns = columns;
    }

}
