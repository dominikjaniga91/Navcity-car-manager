package pl.com.navcity.model.validation;

import pl.com.navcity.model.Color;import pl.com.navcity.model.Route;import javax.persistence.*;import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;import javax.validation.constraints.*;import java.time.LocalDateTime;import java.time.LocalTime;import java.util.ArrayList;import java.util.List;import java.util.Objects;

public class ProductionYearConstraintValidator implements ConstraintValidator<ProductionYear, Integer> {


    @Override
    public void initialize(ProductionYear constraintAnnotation) {

    }

    @Override
    public boolean isValid(Integer productionYear, ConstraintValidatorContext constraintValidatorContext) {
        return productionYear >= 2010 && productionYear <= 2020;
    }
}
