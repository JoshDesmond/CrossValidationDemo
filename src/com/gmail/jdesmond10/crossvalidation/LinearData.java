package com.gmail.jdesmond10.crossvalidation;

import org.ejml.simple.SimpleMatrix;

/**
 * LinearData is an object that represents a set of x inputs and y outputs.
 * There can be any number of x inputs, but there is always one y output.
 * LinearData can be formatted to be used for algorithms that require modified
 * matrices. TODO I might add in some built in utility algorithms right into the
 * class.
 * 
 * @author Josh Desmond
 *
 */
public class LinearData {

	private SimpleMatrix X;
	private SimpleMatrix Y;

	/**
	 * Constructor for use with DataConverter. Takes EJML Matrices and builds a
	 * LinearData object. No data validation is done here, so use this method
	 * correctly.
	 */
	public LinearData(final SimpleMatrix X, final SimpleMatrix Y) {
		this.X = X;
		this.Y = Y;
	}

	/**
	 * Randomizes the order of all the data points.
	 */
	public void shuffle() {
		// TODO shuffle

	}

	@Override
	public String toString() {
		return ("The X Matrix is:\n" + X.toString() + "\nThe Y Vector is:\n" + Y
				.toString());
	}

	/**
	 * Returns the number of rows in X.
	 */
	public int getNumDataPoints() {
		return X.numRows();
	}

	/**
	 * returns the number of columns in X
	 */
	public int getNumInputVariables() {
		return X.numCols();
	}

	public SimpleMatrix getX() {
		return X;
	}

	public SimpleMatrix getY() {
		return Y;
	}

	/**
	 * Used to convert data back into one big EJML Matrix that can be exported
	 * into a CVS file. Use this if you do processing on some data and you want
	 * to save it, or if you generate a LinearData in some other method than
	 * loading it from a csv.
	 */
	public SimpleMatrix convertToSingleMatrix() {
		final SimpleMatrix toRet = new SimpleMatrix(getNumDataPoints(),getNumInputVariables()+1	);
		toRet.insertIntoThis(0, 0, X);
		toRet.insertIntoThis(0, getNumInputVariables(), Y);

		return toRet;
	}
}
