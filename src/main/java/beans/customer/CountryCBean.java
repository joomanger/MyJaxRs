package beans.customer;

import entities.customer.Country;
import entities.customer.CountryTL;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import service.AbstractClientBean;
import service.AbstractEJB;
import service.AbstractView;

/**
 *
 * @author savin
 */
@Named
@RequestScoped
public class CountryCBean extends AbstractClientBean<Country> {

    @Inject
    private CountryEJB ejb;
    @Inject
    private CountryTLEJB ejbTL;
    @Inject
    private FindCountryView fcv;
    @Inject
    private NewCountryView ncv;
    @Inject
    private OpenCountryView ocv;

//    public CountryCBean() {
//        super(Country.class);
//    }

    @Override
    protected AbstractEJB<Country> getEJB() {
        return ejb;
    }

    @Override
    protected AbstractView<Country> getOpenView() {
        return ocv;
    }

    @Override
    protected AbstractView<Country> getFindView() {
        return fcv;
    }

    @Override
    protected AbstractView<Country> getNewView() {
        return ncv;
    }

    //Дополнительные методы
//    @Override
//    public void deleteSelectedEntities() {
//        for (CountryVL entity : fcv.getSelectedEntities()) {
//            Country cc = new Country(entity.getCountry_id(), entity.getEu_code(), entity.getIso_code(), entity.getCountryTL());
//
//            String status = getEJB().remove(cc);
//            if (status.equals(getEJB().SUCCESSFUL)) {
//                getEJB().sendMessage(status, "Объект удален успешно");
//                fcv.getEntities().remove(entity);
//            } else {
//                getEJB().sendMessage(status, null);
//            }
//        }
//    }

    public void addCountryTLOCV() {
        CountryTL tl = new CountryTL();
        tl.setLanguage(ocv.getLanguage());
        tl.setName(ocv.getCountryValue());
        tl.setDescription(ocv.getCountryDescription());
        String result = ejbTL.validateMyEntity(tl);
        if (result.equals(ejbTL.SUCCESSFUL)) {
            tl.setCountry(ocv.getEntity());
            String status = ejbTL.create(tl);
            if (status.equals(ejbTL.SUCCESSFUL)) {
                ejbTL.sendMessage(status, "Значение добавлено успешно");
                ocv.setCountryValue(null);
                ocv.setCountryDescription(null);
                ocv.setLanguage(null);
            } else {
                ejbTL.sendMessage(status, null);
            }
        } else {
            ejbTL.sendMessage(result, null);
        }
    }

//    public List<CountryVL> findAllVL() {
//        return ejb.findAllVL();
//    }
//
//    public CountryVL findVL(String country_id) {
//        return ejb.findVL(country_id);
//    }

    @Override
    public String createEntity(String backURL) {
        if (ncv.getEntity().getCountry_id().trim().isEmpty()) {
            ejb.sendMessage("Код ЖД Страны обязателен", null);
            return null;
        }

        for (CountryTL t : ncv.getEntity().getCountryTL()) {
            try {
                if (t.getName().trim().isEmpty()) {
                    ejb.sendMessage("Заполните все варианты перевода", null);
                    return null;
                }
            } catch (NullPointerException ex) {
                ejb.sendMessage("Заполните все варианты перевода", null);
                return null;
            }
        }

        return super.createEntity(backURL);
    }

}
