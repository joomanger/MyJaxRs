package beans.so.config;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import service.AbstractEJB;
import entities.so.config.ParameterConfiguration;
import entities.so.config.ParameterConfigurationValues;

/**
 *
 * @author savin
 */
@Stateless
public class ParameterEJB extends AbstractEJB<ParameterConfiguration> {

    @PersistenceContext(unitName = "myjaxrs")
    private EntityManager em;

    public ParameterEJB() {
        super(ParameterConfiguration.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

//    @Override
//    public List<ParameterConfiguration> findAll() {
//        return super.findAll();
//    }


    public Integer getMaxLineNum(Long id) {
        TypedQuery<Integer> tq = em.createNamedQuery(ParameterConfigurationValues.MAX_LINE_NUM_BY_HEADER_ID, Integer.class).setParameter("p_header_id", id);
        return tq.getSingleResult();
    }

    public ParameterConfigurationAttrColumn getAttrColumns() {
        List<String> tq = em.createNativeQuery("select upper(column_name) attr "
                + "from information_schema.columns "
                + "WHERE table_schema='public' "
                + "  AND table_name='saleorderline' "
                + "  and column_name like 'attribute%'"
                + "  and not exists (select * from parameterconfiguration where upper(attribute)=upper(column_name)) "
                + "order by to_number(replace(column_name,'attribute',''),'99')").getResultList();
        ParameterConfigurationAttrColumn obj = new ParameterConfigurationAttrColumn();
        obj.setColumns(tq);
        return obj;
    }

}
