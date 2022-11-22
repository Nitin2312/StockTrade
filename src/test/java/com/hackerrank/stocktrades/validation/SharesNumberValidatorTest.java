package com.hackerrank.stocktrades.validation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.validation.ConstraintValidatorContext;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class SharesNumberValidatorTest {

    @Mock
    ConstraintValidatorContext constraintValidatorContext;

    @Test
   public void validMinimumShares(){

       assertTrue( new SharesNumberValidator().isValid(1,constraintValidatorContext));
    }
    @Test
    public void validMaxmimumShares(){
        assertTrue( new SharesNumberValidator().isValid(100,constraintValidatorContext));
    }

    @Test
    public void validateItFailsAfterMaximumRange(){
        assertFalse( new SharesNumberValidator().isValid(102,constraintValidatorContext));
    }

    @Test
    public void validateItFailsAfterMinimumRange(){
        assertFalse( new SharesNumberValidator().isValid(0,constraintValidatorContext));
    }
}

