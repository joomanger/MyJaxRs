package service;

import java.util.List;

/**
 *
 * @author savin
 * @param <T>
 */
public abstract class AbstractClientBean<T> implements IClientBean<T> {

    private final Class<T> entity;

    private IEJB<T> ejb;

    public AbstractClientBean(Class<T> entity) {
        this.entity = entity;
    }

    protected abstract void setEJB();

    public void setEJBean(IEJB<T> ejb) {
        this.ejb = ejb;
    }

    @Override
    public T find(Long p_id) {
        return null;
    }

    @Override
    public List<T> findAll() {
        List<T> l = ejb.findAll();
        //Collections.sort(l);
        return l;
    }
    
    @Override
    public void saveEntity() {
        
    }

}
