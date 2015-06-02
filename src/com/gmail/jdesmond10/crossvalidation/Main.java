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
	private DualFunction predictor;

	public Main() {

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
		this.predictor = generatePredictor();
		evaluatePredictor();
	}

	/**
	 * Outputs into the console a rating of success using the given predictor
	 * function and the test points.
	 */
	private void evaluatePredictor() {
		// TODO evaluatePredictor
		// Goal here is to sum the square of residuals.

		float sum = 0;
		float resid;
		float a;
		float b;
		for (int i = testLinData.getNumDataPoints(); i > 0; i--) {
			a = testLinData.getXPoint(0, i);
			b = testLinData.getXPoint(1,i);
			resid = predictor.apply(a, b);
			sum += resid*resid;
		}

		System.out.println("Sum of residuals squared:" + sum);
	}

	/**
	 * Generates a predictor function using the established RegressionAlgorithm
	 */
	private DualFunction generatePredictor() {
		// TODO generatePredictor
		final PolynomialFunction algo = (PolynomialFunction) regressionAlgo
				.generatePredictor(0, linData);

		System.out.println("finished, algorithm produced is: "
				+ algo.toString());
		return algo;
	}

	/**
	 * Cuts the size of linData, taking about 20% of it and storing it in (by
	 * means of replacing) testLinData
	 */
	private void seperateTestData() {
		// Lets separate about 20% of our points

		SimpleMatrix dataM = linData.convertToSingleMatrix();

		final int n = dataM.numRows() / 5;
		System.out.println("n = " + n);

		testLinData = new LinearData(dataM.extractMatrix(0, n, 0,
				dataM.numCols())); // Save to test data
		dataM = dataM.extractMatrix(n, dataM.numRows() - 1, 0, dataM.numCols()); // Save
		// to
		// dataM

		linData = new LinearData(dataM); // Convert Matrix to field
	}

	/**
	 * Sets the value of the private field regressionAlgo
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
		final String test = l.getResource("dat/PolynomialData.csv").getPath();
		return test;
	}

	/**
	 * Runs a hello message on the console, and runs Main.
	 */
	public static void main(final String[] args) {
		System.out.println(String.format("Running CrossValidation version %s",
				VERSION));

		/*
		 * The following section of code only needs to be run once, and will
		 * generate new data to use.
		 */
		if ("a".equals("b")) {
			final LinearData l = new TestingDataGenerator().getData();
			final DataConverter d = new DataConverter();
			d.setData(l);
			d.writeData("/srcdat/PolynomialDataComplete.csv");
		}

		@SuppressWarnings("unused")
		final Main m = new Main(); // Execute algorithm
	}
}
