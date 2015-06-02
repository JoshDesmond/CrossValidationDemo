package com.gmail.jdesmond10.crossvalidation;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class PolynomialFunctionTest {

	@Test
	public void testFunctionUsage() {
		PolynomialFunction f = new PolynomialFunction(5, 3, 0, 0, 0);
		assertTrue(f.apply(3f) == 14f);

		f = new PolynomialFunction(3, .75f, 2, 0, 0);
		assertTrue(f.apply(2f) == 12.5f);
	}

}
