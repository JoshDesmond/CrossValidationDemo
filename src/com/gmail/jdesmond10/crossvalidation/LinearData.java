package com.gmail.jdesmond10.crossvalidation;

import java.util.Collections;
import java.util.LinkedList;

import org.ejml.simple.SimpleMatrix;

/**
 * LinearData is an object that represents a set of x inputs and y outputs.
 * There can be any number of x inputs, but there is always one y output.
 * LinearData can be formatted to be used for algorithms that require modified
 * matrices. A few utility methods are included in this class. Just look at all
 * the methods to get a feel for what there is.
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
	 * Creates a LinearData by splitting B into an X and a Y component.
	 */
	public LinearData(final SimpleMatrix B) {
		// Now convert SimpleMatrix B into a LinearData object. //
		// *****************************************************//
		final int nc = B.numCols(); // Just for convenience/efficiency
		final int nr = B.numRows();

		// Validation:
		if (nc < 2) {
			System.err.println("Need more than one row");
		} else if (nr < 2) {
			System.err.println("need more than 2 data points.");
			System.err.println(Thread.currentThread().getStackTrace()[2]);
		}

		// we want to split this into an N by p matrix called X and an N by
		// 1 matrix called Y.
		Y = B.extractVector(false, nc - 1);
		X = B.extractMatrix(0, nr, 0, nc - 1);
	}

	/**
	 * Randomizes the order of all the data points.
	 */
	public void shuffle() {
		final SimpleMatrix shufflee = this.convertToSingleMatrix();

		final LinkedList<SimpleMatrix> list = new LinkedList<SimpleMatrix>();

		for (int i = shufflee.numRows() - 1; i >= 0; i--) {
			// For every row vector, add it to list
			list.add(shufflee.extractVector(true, i));
		}

		Collections.shuffle(list);

		int i = 0;
		for (final SimpleMatrix m : list) {
			shufflee.insertIntoThis(i, 0, m);
			i++;
		}

		// This is a really round-about way of doing things but whatever.
		// I'm going to create a new lindata with shufflee, then copy X and Y of
		// that lindata.

		final LinearData newLin = new LinearData(shufflee);
		this.X = newLin.getX();
		this.Y = newLin.getY();
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
		final SimpleMatrix toRet = new SimpleMatrix(getNumDataPoints(),
				getNumInputVariables() + 1);
		toRet.insertIntoThis(0, 0, X);
		toRet.insertIntoThis(0, getNumInputVariables(), Y);

		return toRet;
	}
}
