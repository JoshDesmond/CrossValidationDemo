package com.gmail.jdesmond10.crossvalidation;

public class DualPolynomial implements DualFunction {

	final float a1;
	final float a2;
	final float a3;
	final float a4;
	final float a5;
	final float b2;
	final float b3;
	final float b4;
	final float b5;

	public DualPolynomial(final float a1, final float a2, final float a3,
			final float a4, final float a5, final float b2, final float b3,
			final float b4, final float b5) {
		this.a1 = a1;
		this.a2 = a2;
		this.a3 = a3;
		this.a4 = a4;
		this.a5 = a5;
		this.b2 = b2;
		this.b3 = b3;
		this.b4 = b4;
		this.b5 = b5;
	}

	@Override
	public float apply(final float a, final float b) {
		float toRet = a1;
		toRet += a2 * a;
		toRet += a3 * a * a;
		toRet += a4 * a * a * a;
		toRet += a5 * a * a * a * a;
		toRet += b2 * b;
		toRet += b3 * b * b;
		toRet += b4 * b * b * b;
		toRet += b5 * b * b * b * b;
		return toRet;
	}

}
