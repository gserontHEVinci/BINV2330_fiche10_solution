package ioc;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;

@Retention(RetentionPolicy.RUNTIME)   // L’annotation doit être visible à l’exécution
@Target(ElementType.FIELD)            // On ne peut l’appliquer que sur des attributs
public @interface Inject {
}
