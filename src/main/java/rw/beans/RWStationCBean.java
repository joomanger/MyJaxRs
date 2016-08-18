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

    public RWStationCBean() {
        super(RWStation.class);
    }

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
        //tl.setDescription(ocv.getDescription());
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

    public void addRWStationTLNCV() {
        RWStation l = ncv.getEntity();
        RWStationTL tl = new RWStationTL();
        tl.setLanguage(ncv.getLanguage());
        tl.setName(ncv.getName());
        String result = ejbTL.validateMyEntity(tl);
        if (result.equals(ejbTL.SUCCESSFUL)) {
            l.addRWStationTL(tl);
            ncv.setName(null);
            ncv.setLanguage(null);
        } else {
            ejbTL.sendMessage(result, null);
        }
    }

    public void deleteRWStationTLNCV() {
        ncv.getEntity().getRwstationTL().removeAll(ncv.getSelectedEntityLines());
    }

    public void deleteRWStationTLOCV() {
        ocv.getEntity().getRwstationTL().removeAll(ocv.getSelectedEntityLines());
        changeEntity();
    }

    public List<RWStationVL> findAllVL() {
        return ejb.findAllVL();
    }

    @Override
    public String createEntity(String backURL) {
        if (ncv.getEntity().getRwstationTL().isEmpty()) {
            ejb.sendMessage("Название страны обязательно", null);
            return null;
        }
        return super.createEntity(backURL);
    }

}
