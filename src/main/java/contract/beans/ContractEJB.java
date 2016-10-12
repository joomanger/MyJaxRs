package contract.beans;

import contract.entities.Contract;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import service.AbstractEJB;

/**
 *
 * @author savin
 */
@Stateless
public class ContractEJB extends AbstractEJB<Contract> {

    @Inject
    private EntityManager em;

    public ContractEJB() {
        super(Contract.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public List<Contract> findByINV(Long p_customer_id) {
        return em.createNativeQuery("select c.* from contract c, party p \n"
                + "where coalesce(enddate,now())>=now()\n"
                + "and p.contract_id=c.contract_id\n"
                + "and p.role='BUYER'\n"
                + "and p.customer_id=?1\n"
                + "order by c.contractnumber;", Contract.class).setParameter(1, p_customer_id).getResultList();
    }

}
