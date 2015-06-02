package com.gmail.jdesmond10.crossvalidation;


/**
 * An algorithm that predicts Y values given sets of X values. Two examples are
 * linear regression, and polynomial linear regression. Nearest Neighbor is
 * another example.
 */
public interface RegressionAlgorithm {

	/**
	 * Runs some type of regression, and returns any object that represents a
	 * function. A base case example will be a polynomial expression.
	 * 
	 * @param Complexity
	 *            a number between TODO Determine how to rep. complexity
	 * @return
	 */
	public DualFunction generatePredictor(int Complexity,
			LinearData data);

}
