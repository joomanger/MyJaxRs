/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author savin
 */
@Dependent
public class MyProduces {

    @Produces
    @PersistenceContext(unitName = "myjaxrs")
    private EntityManager em;
}
