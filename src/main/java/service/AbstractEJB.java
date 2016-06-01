package service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

/**
 *
 * @author savin
 * @param <T>
 */
public abstract class AbstractEJB<T> {

    public final String SUCCESSFUL = "S#";
    public final String ERROR = "E#";
    private final Class<T> entityClass;

    public AbstractEJB(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public String create(T entity) {
        try {
            getEntityManager().persist(entity);
            return SUCCESSFUL;
        } catch (Exception ex) {
            return ERROR + " " + ex.getMessage();
        }
    }

    public String edit(T entity) {
        try {
            getEntityManager().merge(entity);
            return SUCCESSFUL;
        } catch (Exception ex) {
            return ERROR + " " + ex.getMessage();
        }
    }

    public String remove(T entity) {
        try {
            getEntityManager().remove(getEntityManager().merge(entity));
            return SUCCESSFUL;
        } catch (Exception ex) {
            return ERROR + " " + ex.getMessage();
        }
    }

    public T find(Object id) {
        if (id != null) {
            return getEntityManager().find(entityClass, id);
        } else {
            return null;
        }
    }

    public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq;
        cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<T> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    public void sendMessage(String status, String success_msg) {
        if ((status != null)) {
            if (status.equals(SUCCESSFUL)) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, success_msg, null));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, status, null));
            }
        }
    }

}
