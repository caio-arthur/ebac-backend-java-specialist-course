package br.com.carthur.annotation;

import java.lang.annotation.*;

/**
 * @author caio.arthur
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TipoChave {

    String value();
}