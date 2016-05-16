package so.config.beans;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author savin
 */
public class ParameterConfigurationAttrColumn {

    private List<String> columns;

    public ParameterConfigurationAttrColumn() {
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