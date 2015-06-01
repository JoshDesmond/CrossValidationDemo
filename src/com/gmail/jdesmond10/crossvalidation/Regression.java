package com.gmail.jdesmond10.crossvalidation;

import java.awt.Point;
import java.util.LinkedList;
import java.util.List;

import org.ejml.simple.SimpleMatrix;

/**
 * This class is just being used for reference. It's not going to be included in
 * the final program.
 *
 */
public class Regression {

	public static void main(final String[] args) {
		// Gather input data and convert to matrices. Using only example points
		// for now.
		final SimpleMatrix X = convertPointsToXMatrixPoly(getExamplePoints());
		final SimpleMatrix Y = convertPointsToYMatrix(getExamplePoints());

		System.out.println("X:" + X.toString());
		System.out.println("Y:" + Y.toString());

		// Find the matrix A that solves the linear system:
		final SimpleMatrix A = findMatrixAtest(X, Y);
		System.out.println(A);
		System.out.println(prettyPrintAnswer(A));

	}

	/**
	 * Finds the 2x1 matrix A representing [intercept, slope]
	 * 
	 * @param x
	 *            A 2xN size matrix
	 * @param y
	 *            A 1xN size matrix
	 */
	private static SimpleMatrix findMatrixA(final SimpleMatrix x,
			final SimpleMatrix y) {
		// A is given by (X^T * X)^-1 * (X^T * Y) where X^T is X transpose.
		SimpleMatrix A = new SimpleMatrix(2, 1);

		// y.mult(x.transpose());

		x.transpose().mult(x).invert();

		A = (((x.transpose()).mult(x)).invert()).mult(x.transpose().mult(y));

		return A;

	}

	private static SimpleMatrix findMatrixAtest(final SimpleMatrix x,
			final SimpleMatrix y) {
		// A is given by (X^T * X)^-1 * (X^T * Y) where X^T is X transpose.
		SimpleMatrix A = new SimpleMatrix(3, 1);

		// y.mult(x.transpose());

		x.transpose().mult(x).invert();

		A = (((x.transpose()).mult(x)).invert()).mult(x.transpose().mult(y));

		return A;

	}

	/**
	 * Converts a list of points to a vector of x inputs. Return value includes
	 * a column of 1's
	 */
	public static SimpleMatrix convertPointsToXMatrix(
			final List<Point> trainingData) {
		final SimpleMatrix X = new SimpleMatrix(trainingData.size(), 2);
		int n = 0; // n is used to keep track of for loop
		for (final Point point : trainingData) {
			X.set(n, 1, point.getX());
			X.set(n, 0, 1);
			n++;
		}

		return X;
	}

	/**
	 * Converts a list of points to a vector of x inputs. Return value includes
	 * a column of 1's
	 */
	public static SimpleMatrix convertPointsToXMatrixPoly(
			final List<Point> trainingData) {
		final SimpleMatrix X = new SimpleMatrix(trainingData.size(), 3);
		int n = 0; // n is used to keep track of for loop
		for (final Point point : trainingData) {
			X.set(n, 1, point.getX());
			X.set(n, 0, 1);
			X.set(n, 2, Math.pow(point.getX(), 2));
			n++;
		}

		return X;
	}

	/**
	 * Converts list of points to column matrix using just the Y's.
	 */
	public static SimpleMatrix convertPointsToYMatrix(
			final List<Point> trainingData) {
		final SimpleMatrix Y = new SimpleMatrix(trainingData.size(), 1);
		int n = 0; // n is used to keep track of for loop
		for (final Point point : trainingData) {
			Y.set(n, 0, point.getY());
			n++;
		}

		return Y;
	}

	/**
	 * Returns a list of points for testing
	 */
	public static List<Point> getExamplePoints() {
		final LinkedList<Point> points = new LinkedList<Point>();
		points.add(new Point(1, 5));
		points.add(new Point(2, 11));
		points.add(new Point(4, 0));

		return points;
	}

	/**
	 * Prints in the form "y = mx + b"
	 * 
	 * @param A
	 *            the matrix in the form [b;m]
	 */
	public static String prettyPrintAnswer(final SimpleMatrix A) {
		return String.format("y = %s*x + %s", A.get(1), A.get(0));
	}

}
