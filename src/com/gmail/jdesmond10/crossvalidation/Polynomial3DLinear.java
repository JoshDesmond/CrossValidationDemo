package com.gmail.jdesmond10.crossvalidation;

import org.ejml.simple.SimpleMatrix;

public class Polynomial3DLinear implements RegressionAlgorithm {

	@Override
	public DualFunction generatePredictor(final int Complexity,
			final LinearData data) {
		// TODO Auto-generated method stub

		// Since our dual polynomial is hardcoded, we need to make our X matrix
		// be in the exact form as our polynomial class. We want X data to be in
		// the form: Z = [1,x,x^2,x^3,x^4,y,y^2,y^3,y^4]

		final SimpleMatrix x = data.getX();

		final SimpleMatrix x0 = x.extractVector(false, 0);
		System.out.println(x0.toString());

		final SimpleMatrix y = data.getY();

		// A is given by (X^T * X)^-1 * (X^T * Y) where X^T is X transpose.
		SimpleMatrix A;

		x.transpose().mult(x).invert();

		A = (((x.transpose()).mult(x)).invert()).mult(x.transpose().mult(y));

		System.out.println(A.toString());

		return new DualPolynomial((float) A.get(0), (float) A.get(1),
				(float) A.get(2), (float) A.get(3), (float) A.get(4),
				(float) A.get(5), (float) A.get(6), (float) A.get(7),
				(float) A.get(8));
	}

}
