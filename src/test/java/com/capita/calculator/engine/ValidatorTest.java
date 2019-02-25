package com.capita.calculator.engine;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.capita.calculator.exception.InvalidInputException;

public class ValidatorTest {

	Validator validator;
	
	@Before
	public void setup() {
		validator =  new Validator();
	}
	
	@Test(expected=InvalidInputException.class)
	public void emptyData() throws InvalidInputException {
		validator.isValidMathExpression(" ");
	}
	
	@Test(expected=InvalidInputException.class)
	public void startInvalidData() throws InvalidInputException {
		validator.isValidMathExpression(")1+2");
	}
	
	@Test
	public void cosecutiveOperand() throws InvalidInputException {
		boolean isValid = validator.isValidMathExpression("2-+2");
		assertEquals(false, isValid);
	}

	@Test
	public void operatorAfterOpeningParanthesis() throws InvalidInputException {
		boolean isValid = validator.isValidMathExpression("(-+2");
		assertEquals(false, isValid);
	}
	
	@Test
	public void emptyParanthesis() throws InvalidInputException {
		boolean isValid = validator.isValidMathExpression("()");
		assertEquals(false, isValid);
	}
}
