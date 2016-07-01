package service;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

/**
 *
 * @author savin
 * @param <T>
 */
public abstract class AbstractEJB<T> implements IEJB<T>{

    @Inject
    @MyValidator
    private Validator validator;

    public final String SUCCESSFUL = "S#";
    public final String ERROR = "E#";
    private final Class<T> entityClass;

    public AbstractEJB(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();
    
    /**
     *
     * @param entity
     * @return
     */
    
    @Override
    public String validateMyEntity(T entity) {
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(entity);
        StringBuilder sb = new StringBuilder();
        if (constraintViolations.size() > 0) {
            Iterator<ConstraintViolation<T>> iterator = constraintViolations.iterator();
            while (iterator.hasNext()) {
                ConstraintViolation<T> cv = iterator.next();
                sb.append(cv.getMessage());
            }
            return sb.toString();
        }
        return SUCCESSFUL;
    }

    @Override
    public String create(T entity) {
        try {
            String result = validateMyEntity(entity);
            if (!result.equals(SUCCESSFUL)) {
                return result;
            } else {
                getEntityManager().persist(entity);
                return SUCCESSFUL;
            }
        } catch (Exception ex) {
            return ERROR + " " + ex.getMessage();
        }
    }

    @Override
    public String edit(T entity) {
        try {
            String result = validateMyEntity(entity);
            if (!result.equals(SUCCESSFUL)) {
                return result;
            } else {
                getEntityManager().merge(entity);
                return SUCCESSFUL;
            }
        } catch (Exception ex) {
            return ERROR + " " + ex.getMessage();
        }
    }

    @Override
    public String remove(T entity) {
        try {
            getEntityManager().remove(getEntityManager().merge(entity));
            return SUCCESSFUL;
        } catch (Exception ex) {
            return ERROR + " " + ex.getMessage();
        }
    }

    @Override
    public T find(Long id) {
        if (id != null) {
            return getEntityManager().find(entityClass, id);
        } else {
            return null;
        }
    }

    @Override
    public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq;

        if (getEntityManager() == null) {
            System.out.println("EMA NULL");
        }
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

    @Override
    public void sendMessage(String status, String success_msg) {
        try {
            if ((status != null)) {
                if (status.equals(SUCCESSFUL)) {
                    //FacesContext.getCurrentInstance()
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, success_msg, null));
                } else {
                    //FacesContext.getCurrentInstance().
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, status, null));
                }
            }
        } catch (NullPointerException ex) {
            System.out.println("FacesContext is null");
        }
    }

}
