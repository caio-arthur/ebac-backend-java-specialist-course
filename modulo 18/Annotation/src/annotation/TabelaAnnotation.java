package annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
public @interface TabelaAnnotation {
    String value(); 
}
