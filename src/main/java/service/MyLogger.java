package service;

import java.io.Serializable;
import java.util.logging.Logger;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

/**
 *
 * @author savin
 */
@Dependent
public class MyLogger implements Serializable{

    @SuppressWarnings("PMD.UnusedPrivateMethod")
    @Produces
    private Logger log(InjectionPoint ip) {
        return Logger.getLogger(ip.getMember().getDeclaringClass().getName());
    }
}
