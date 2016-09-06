/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 *
 * @author savin
 */
@SuppressWarnings("PMD.UnusedPrivateField")
@ApplicationScoped
public class MyProduces {

    
    @Produces
    @PersistenceContext(unitName = "myjaxrs")
    private EntityManager em;
    @Produces
    private ValidatorFactory factory;

    @MyValidator
    @Produces
    public Validator getValidator() {
        factory = Validation.buildDefaultValidatorFactory();
        return factory.getValidator();
    }

}
