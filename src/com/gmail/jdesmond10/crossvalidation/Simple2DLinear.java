package com.gmail.jdesmond10.crossvalidation;

import java.util.function.Function;

import org.ejml.simple.SimpleMatrix;

public class Simple2DLinear implements RegressionAlgorithm {

	@Override
	public Function<Float, Float> generatePredictor(final int Complexity,
			final LinearData data) {
		// Complexity is basically going to be ignored.

		final SimpleMatrix x = data.getX();
		final SimpleMatrix y = data.getY();

		// A is given by (X^T * X)^-1 * (X^T * Y) where X^T is X transpose.
		SimpleMatrix A = new SimpleMatrix(2, 1);

		// y.mult(x.transpose());

		x.transpose().mult(x).invert();

		A = (((x.transpose()).mult(x)).invert()).mult(x.transpose().mult(y));

		return new PolynomialFunction((float) A.get(0),(float) A.get(1),0f,0f,0f);



	}

}
