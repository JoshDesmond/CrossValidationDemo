package com.gmail.jdesmond10.crossvalidation;

import org.ejml.simple.SimpleMatrix;

/**
 * This will only work if you give it a single dimensional input X. Multiple
 * inputs will work but it will output the response in the wrong way. This whole
 * class is mostly for testing and drafting purposes, you can pretty much ignore
 * this all.
 * 
 * @author Josh Desmond
 *
 */
public class Simple2DLinear implements RegressionAlgorithm {

	@Override
	public DualFunction generatePredictor(final int Complexity,
			final LinearData data) {
		// Complexity is basically going to be ignored.

		final SimpleMatrix x = data.getX();
		final SimpleMatrix y = data.getY();

		// A is given by (X^T * X)^-1 * (X^T * Y) where X^T is X transpose.
		SimpleMatrix A;
		// y.mult(x.transpose());

		x.transpose().mult(x).invert();

		A = (((x.transpose()).mult(x)).invert()).mult(x.transpose().mult(y));

		System.out.println(A.toString());

		return new PolynomialFunction((float) A.get(0), (float) A.get(1), 0f,
				0f, 0f);

	}

}
