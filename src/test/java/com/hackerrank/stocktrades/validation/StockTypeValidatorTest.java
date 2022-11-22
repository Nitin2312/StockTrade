package com.hackerrank.stocktrades.validation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.validation.ConstraintValidatorContext;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class StockTypeValidatorTest {
    @Mock
    ConstraintValidatorContext constraintValidatorContext;
    @Test
    public void notAllowedValues(){
        assertFalse( new StockTypeValidator().isValid("boo",constraintValidatorContext));
    }
    @Test
    public void allowedValues_Sell(){
        assertTrue( new StockTypeValidator().isValid("sell",constraintValidatorContext));
    }

    @Test
    public void allowedValues_buy(){
        assertTrue( new StockTypeValidator().isValid("buy",constraintValidatorContext));
    }
}
