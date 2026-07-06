package com.example.lab5;

import org.junit.Test;
import static org.junit.Assert.*;

public class ProductValidatorTest {

    @Test
    public void skuMustBePositive() {
        assertFalse(ProductValidator.isValidSku(0));
        assertFalse(ProductValidator.isValidSku(-42));
        assertTrue(ProductValidator.isValidSku(1001));
    }
}