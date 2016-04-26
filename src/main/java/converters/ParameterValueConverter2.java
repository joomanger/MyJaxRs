package converters;

import aaa.ViewBean;
import config.beans.ParameterClientBean;
import config.beans.ParameterValuesClientBean;
import config.entity.ParameterConfigurationValues;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author savin
 */
@Named
@RequestScoped
public class ParameterValueConverter2 implements Converter {

    @Inject
    private ParameterValuesClientBean cb;

    @Inject
    private ParameterClientBean pcb;

    @Inject
    private ViewBean vb;

    private String valuez;
    private String labelz;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && value.trim().length() > 0) {
            System.out.println("converters.ParameterValueConverter2.getAsObject() value=" + value);
            ParameterConfigurationValues val;
            try {
                val = cb.getItem(ParameterConfigurationValues.class, Long.parseLong(value));
                //valuez=String.valueOf(val.getParamater_value_id());
                labelz = val.getParameterValue();
                //  return cb.getValue(Long.parseLong(value));
                return labelz;

            } catch (NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid theme."));
            }
        } else {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {

            ParameterConfigurationValues val;
            try {
                val = cb.getItem(ParameterConfigurationValues.class, Long.parseLong(value.toString()));
                valuez = String.valueOf(val.getParamater_value_id());
                return valuez;
            } catch (Exception ex) {
                return null;
            }

        } else {
            return null;
        }
    }

    public List<String> completeValue(String query) {
        List<ParameterConfigurationValues> allItems = pcb.getValues(vb.getHeader_id());
        List<String> filteredItems = new ArrayList<>();
        for (ParameterConfigurationValues item : allItems) {
            if (item.getParameterValue().toLowerCase().contains(query.toLowerCase())) {
                filteredItems.add(item.getParameterValue());
            }
        }

        return filteredItems;
    }

    public String getValuez() {
        return valuez;
    }

    public void setValuez(String valuez) {
        this.valuez = valuez;
    }

    public String getLabelz() {
        return labelz;
    }

    public void setLabelz(String labelz) {
        this.labelz = labelz;
    }

}
