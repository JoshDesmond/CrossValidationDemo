package com.gmail.jdesmond10.crossvalidation;

import java.util.function.Function;

/**
 * An algorithm that predicts Y values given sets of X values. Two examples are
 * linear regression, and polynomial linear regression. Nearest Neighbor is
 * another example.
 */
public interface RegressionAlgorithm {

	/**
	 * Runs some time of
	 * 
	 * @param Complexity
	 *            a number between TODO Determine how to rep. complexity
	 * @return
	 */
	public Function<Float, Float> generatePredictor(int Complexity,
			LinearData data);

}
