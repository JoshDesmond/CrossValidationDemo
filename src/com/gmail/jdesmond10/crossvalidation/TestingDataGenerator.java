package com.gmail.jdesmond10.crossvalidation;

import java.util.Random;

import org.ejml.simple.SimpleMatrix;

/**
 * This class will generate an example matrix of points according to a preset
 * function
 * 
 * @author Josh Desmond
 *
 */
public class TestingDataGenerator {

	private static final int NUM_POINTS = 100;
	SimpleMatrix matrixData;

	public TestingDataGenerator() {
		matrixData = new SimpleMatrix(NUM_POINTS, 3);
		for (int i = NUM_POINTS; i>0;i--) {
			appendVector(i,generateVector());
		}
	}

	public LinearData getData() {
		return new LinearData(matrixData);
	}

	/**
	 * the hardcoded function for generating points.
	 */
	private float function(final float a, final float b) {
		final float z = 2 + a + .7f * a * a + 2f * b + .4f * b * b * b;

		return z;
	}

	/**
	 * Hardcoded method that generates a vector that is pretty close to the
	 * function within a certain range.
	 *
	 */
	public SimpleMatrix generateVector() {
		// generate a randomly, generate b randomly, calculate z, add noise

		final Random r = new Random();
		final float x = (r.nextFloat() - 0.5f) * 10;
		final float y = (r.nextFloat() - 0.5f) * 10;
		float z = function(x, y);
		z += (r.nextFloat() - 5f);

		final double[][] array = {{x},{y},{z}};

		final SimpleMatrix m = new SimpleMatrix(array);
		return m.transpose();
	}

	public void appendVector(final int row, final SimpleMatrix vector) {

		matrixData.insertIntoThis(row-1, 0, vector);
	}

}
