package com.hackerrank.stocktrades.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.function.Predicate;

public class SharesNumberValidator implements ConstraintValidator<Shares,Integer> {
    Integer minRange = 1;
    Integer maxRange = 100;
    Predicate<Integer> isWithinMinimumRange = price-> price>=minRange;
    Predicate<Integer> isWithinMaximumRange = price-> price <=maxRange;
    @Override
    public boolean isValid(Integer numberOfShares, ConstraintValidatorContext constraintValidatorContext) {
        return isWithinMinimumRange.test(numberOfShares) && isWithinMaximumRange.test(numberOfShares);
    }
}
