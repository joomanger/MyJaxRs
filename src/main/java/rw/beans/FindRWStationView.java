package rw.beans;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import rw.entities.RWStation;
import service.AbstractView;

/**
 *
 * @author savin
 */
@Named
@RequestScoped
public class FindRWStationView extends AbstractView<RWStation> {

    @Inject
    private RWStationCBean client;
//    @Inject
//    private SessionActions sa;

//    public FindRWStationView() {
//        super(RWStationVL.class);
//    }
    @PostConstruct
    @Override
    protected void init() {
        List<RWStation> rw = client.findAll();
//       try{
//        Collections.sort(rw, new Comparator() {
//            @Override
//            public int compare(Object l1, Object l2) {
//                return ((RWStation) l1).getTranslateObject(sa.getLanguage()).getName().compareTo(((RWStation) l2).getTranslateObject(sa.getLanguage()).getName());
//            }
//        });
//       }catch(Exception ex){
//           System.out.println(ex.getStackTrace());
//       }
        setEntities(rw);
    }

}
