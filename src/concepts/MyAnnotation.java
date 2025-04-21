package concepts;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Designing my custom annotation
 */
// specify which java elements your custom annotation can be used to annotate.
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD})
// Inherited annotation signals that a custom java annotation used in a class should be inherited by subclasses
// inheriting from that class
@Inherited
// Signals to Java compiler and JVM that the annotation should be available via reflection at runtime.
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation {
    String name() default "John";
    int count() default 999;
    String[] tags() default {"Java", "Annotation"};
}
