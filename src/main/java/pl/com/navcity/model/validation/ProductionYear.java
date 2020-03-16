package pl.com.navcity.model.validation;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Constraint(validatedBy = ProductionYearConstraintValidator.class)
@Target( {ElementType.METHOD, ElementType.FIELD}) // this means where you can applied this new annotation
@Retention(RetentionPolicy.RUNTIME) // how long annotation will be stored or ided
public @interface ProductionYear {

    public String message() default "Year is incorrect";

    public Class<?>[] groups() default {};

    public Class<? extends Payload>[] payload() default {};

}
