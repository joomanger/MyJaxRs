package service;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

/**
 *
 * @author savin
 * @param <T>
 */
public abstract class AbstractEJB2<T> {

    public static final String SUCCESSFUL = "Successful";
    public static final String ERROR = "Error";

    private final Class<T> entityClass;

    @Inject
    @MyValidator
    private Validator validator;

    public AbstractEJB2(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public String validateMyEntity(T entity) {
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(entity);
        StringBuilder sb = new StringBuilder();
        if (constraintViolations.size() > 0) {
            Iterator<ConstraintViolation<T>> iterator = constraintViolations.iterator();
            while (iterator.hasNext()) {
                ConstraintViolation<T> cv = iterator.next();
                sb.append(cv.getMessage());
            }
            System.out.println("ERORR!!!!!!!!!"+sb.toString());
            return sb.toString();
        }
        return SUCCESSFUL;
    }

    public void create(T entity) {
        String result = validateMyEntity(entity);
        if (result.equals(AbstractEJB2.SUCCESSFUL)) {
            try {
                getEntityManager().persist(entity);
                MessageSender.sendFacesContextMessage(FacesMessage.SEVERITY_INFO, "Объект создан успешно");
                //FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, outcome);
            } catch (Exception ex) {
                //Выдать в лог ошибку
                throw ex;
            }
        } else {
            MessageSender.sendFacesContextMessage(FacesMessage.SEVERITY_ERROR, result);
        }
//        getEntityManager().persist(entity);
    }

    public void edit(T entity) {
        String result = validateMyEntity(entity);
        if (result.equals(AbstractEJB2.SUCCESSFUL)) {
            try {
                getEntityManager().merge(entity);
                MessageSender.sendFacesContextMessage(FacesMessage.SEVERITY_INFO, "Объект изменен успешно");
                //FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, outcome);
            } catch (Exception ex) {
                //Выдать в лог ошибку
                throw ex; 
            }
        } else {
            MessageSender.sendFacesContextMessage(FacesMessage.SEVERITY_ERROR, result);
        }
//        getEntityManager().merge(entity);
    }

    public void remove(T entity) {
        try {
            getEntityManager().remove(getEntityManager().merge(entity));
            MessageSender.sendFacesContextMessage(FacesMessage.SEVERITY_INFO, "Объект удален успешно");
        } catch (Exception ex) {
            //Выдать в лог ошибку
            throw ex;
        }
//        getEntityManager().remove(getEntityManager().merge(entity));

    }

    public T find(Object id) {
        if (id != null) {
            return getEntityManager().find(entityClass, id);
        }
        return null;
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

}
