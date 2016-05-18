package service;

import java.lang.annotation.ElementType;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.inject.Qualifier;

/**
 *
 * @author savin
 */
@Qualifier
@Retention(RUNTIME)
@Target({METHOD, ElementType.FIELD, ElementType.PARAMETER, TYPE})
public @interface Who {
    String[] value();
}
