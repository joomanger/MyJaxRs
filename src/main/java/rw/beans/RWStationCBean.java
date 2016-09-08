package rw.beans;

import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import rw.entities.RWStation;
import rw.entities.RWStationTL;
import rw.entities.RWStationVL;
import service.AbstractClientBean;
import service.AbstractEJB;
import service.AbstractView;

/**
 *
 * @author savin
 */
@Named
@RequestScoped
public class RWStationCBean extends AbstractClientBean<RWStation> {

    @Inject
    private RWStationEJB ejb;
    @Inject
    private RWStationTLEJB ejbTL;
    @Inject
    private FindRWStationView fcv;
    @Inject
    private NewRWStationView ncv;
    @Inject
    private OpenRWStationView ocv;

//    public RWStationCBean() {
//        super(RWStation.class);
//    }
    @Override
    protected AbstractEJB<RWStation> getEJB() {
        return ejb;
    }

    @Override
    protected AbstractView<RWStation> getOpenView() {
        return ocv;
    }

    @Override
    protected AbstractView<RWStation> getFindView() {
        return null;
    }

    @Override
    protected AbstractView<RWStation> getNewView() {
        return ncv;
    }

    //Дополнительные методы
    @Override
    public void deleteSelectedEntities() {
        for (RWStationVL entity : fcv.getSelectedEntities()) {

            RWStation cc = new RWStation(entity.getRws_code(), entity.getLocation_id_orc(), entity.getRwroad(), entity.getRwstationTL());

            String status = getEJB().remove(cc);
            if (status.equals(getEJB().SUCCESSFUL)) {
                getEJB().sendMessage(status, "Объект удален успешно");
                fcv.getEntities().remove(entity);
            } else {
                getEJB().sendMessage(status, null);
            }
        }
    }

    public void addRWStationTLOCV() {
        RWStationTL tl = new RWStationTL();
        tl.setLanguage(ocv.getLanguage());
        tl.setName(ocv.getName());
        String result = ejbTL.validateMyEntity(tl);
        if (result.equals(ejbTL.SUCCESSFUL)) {
            tl.setRwstation(ocv.getEntity());
            String status = ejbTL.create(tl);
            if (status.equals(ejbTL.SUCCESSFUL)) {
                ejbTL.sendMessage(status, "Значение добавлено успешно");
                ocv.setName(null);
                ocv.setLanguage(null);
            } else {
                ejbTL.sendMessage(status, null);
            }
        } else {
            ejbTL.sendMessage(result, null);
        }
    }

    public List<RWStationVL> findAllVL() {
        return ejb.findAllVL();
    }

    @Override
    public String createEntity(String backURL) {
        if (ncv.getEntity().getRws_code().trim().isEmpty()) {
            ejb.sendMessage("Код ЖД Станции обязателен", null);
            return null;
        }

        for (RWStationTL t : ncv.getEntity().getRwstationTL()) {
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

    public String getNameByCode(String p_rws_code) {
        return ejb.getNameByRWcode(p_rws_code);
    }

}
