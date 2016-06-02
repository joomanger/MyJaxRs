/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sys.beans;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 *
 * @author savin
 */
public class AbstractTest {

    @Produces
    protected EntityManager getEntityManager() {
        return Persistence.createEntityManagerFactory("test").createEntityManager();
    }
}
