package pl.com.navcity.model.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Constraint(validatedBy = TravelDateTimeConstraintValidator.class)
@Target( {ElementType.METHOD, ElementType.FIELD, ElementType.TYPE}) // this means where you can applied this new annotation
@Retention(RetentionPolicy.RUNTIME) // how long annotation will be stored or ided
public @interface TravelDateTime {

    public String message() default "Data is incorrect";

    public Class<?>[] groups() default {};

    public Class<? extends Payload>[] payload() default {};

}
