package com.gmail.jdesmond10.crossvalidation;

import org.ejml.simple.SimpleMatrix;

public class Main {

	public static final String VERSION = "0.0";

	private final DataConverter dataC;
	/** A set of X points, in n dimensions, arranged as column matrices */
	private LinearData linData;
	private LinearData testLinData;

	private RegressionAlgorithm regressionAlgo;
	/** the number of folds in K-Folds Cross Validation */
	private final int kFolds = 10;

	public Main() {
		System.out.println(String.format("Running CrossValidation version %s",
				VERSION));

		dataC = new DataConverter();

		// The algorithm is pretty linear in execution, and will be primarily
		// run from here. The main steps of the algorithm are outlined here:
		/*
		 * 1: Determine "data", "regression type", and "cross validation type".
		 * 
		 * 2: Convert "data" to EJML format.
		 * 
		 * 3: Shuffle LinearData object, so that from then on, picking the first
		 * ten elements in the matrix is the same as randomly picking ten
		 * elements.
		 * 
		 * 4: Separate a portion of data for testing once algorithm is finished.
		 * 
		 * 5: Run cross validation with the given regression algorithm, and
		 * return a predictor.
		 * 
		 * 6: Evaluate the success of the predictor with the test data, and
		 * output this information into the console.
		 */

		dataC.read(chooseData()); // Load the data into the DataC object.
		linData = dataC.getData(); // Read data back into Main
		chooseAlgorithm(); // Determine what algorithm to use

		System.out.println(linData.toString());

		linData.shuffle(); // Shuffles data

		seperateTestData();
		generatePredictor();
		evaluatePredictor();
	}

	/**
	 * Outputs into the console a rating of success using the given predictor
	 * function and the test points.
	 */
	private void evaluatePredictor() {
		// TODO evaluatePredictor

	}

	/**
	 * Generates a predictor function using the established RegressionAlgorithm
	 */
	private void generatePredictor() {
		// TODO generatePredictor

	}

	/**
	 * Cuts the size of linData, taking about 20% of it and storing it in (by
	 * means of replacing) testLinData
	 */
	private void seperateTestData() {
		// Lets separate about 20% of our points

		SimpleMatrix dataM = linData.convertToSingleMatrix();

		final int n = dataM.numRows() / 5;

		testLinData = new LinearData(dataM.extractMatrix(0, n, 0,
				dataM.numCols() - 1)); // Save to test data
		dataM = dataM.extractMatrix(n, dataM.numRows() - 1, 0,
				dataM.numCols() - 1); // Save to dataM

		linData = new LinearData(dataM); // Convert Matrix to field
	}

	/**
	 * Sets the value of the private fields regressionAlgo and crossValAlgo.
	 */
	private void chooseAlgorithm() {
		regressionAlgo = new Simple2DLinear();

	}

	/**
	 * @return the string of the .csv file
	 */
	private String chooseData() {
		// For now, dataC will just be set to use the given test data.
		final ClassLoader l = ClassLoader.getSystemClassLoader();
		final String test = l.getResource("dat/test.csv").getPath();
		return test;
	}

	/**
	 * Runs a hello message on the console, and runs Main.
	 */
	public static void main(final String[] args) {

		final Main m = new Main(); // Execute algorithm

		// Ignore this stuff.
		final DataConverter c = new DataConverter();
		final ClassLoader l = ClassLoader.getSystemClassLoader();
		final String test = l.getResource("dat/test.csv").getPath();
		c.read(test);

	}
}
