package converters;

import aaa.viewBean;
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
public class ParameterValueConverter implements Converter {

    @Inject
    private ParameterValuesClientBean cb;
    
    @Inject 
    private ParameterClientBean pcb;
    
    @Inject
    private viewBean vb;

    private Long parameter_id;

    public Long getParameter_id() {
        return parameter_id;
    }

    public void setParameter_id(Long parameter_id) {
        this.parameter_id = parameter_id;
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && value.trim().length() > 0) {
            try {
                //ps.setParamID(Long.parseLong(value));
                return cb.getValue(Long.parseLong(value));

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
            return String.valueOf(((ParameterConfigurationValues) value).getParamater_value_id());
        } else {
            return null;
        }
    }

    public List<ParameterConfigurationValues> completeValue(String query) {
        List<ParameterConfigurationValues> allItems = pcb.getValues(vb.getHeader_id());
        List<ParameterConfigurationValues> filteredItems = new ArrayList<>();

        for (ParameterConfigurationValues item : allItems) {
            if (item.getParameterValue().toLowerCase().contains(query.toLowerCase())) {
                filteredItems.add(item);
            }
        }

        return filteredItems;
    }
   
}
