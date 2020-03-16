package pl.com.navcity.model.validation;

import pl.com.navcity.model.Route;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TravelDateTimeConstraintValidator implements ConstraintValidator<TravelDateTime, Route> {

    @Override
    public void initialize(TravelDateTime constraintAnnotation) {

    }

    @Override
    public boolean isValid(Route route, ConstraintValidatorContext constraintValidatorContext) {

        return route.getArrivalDate().isAfter(route.getDepartureDate()); // finish date cannot be equals or earlier than start date
    }
}
