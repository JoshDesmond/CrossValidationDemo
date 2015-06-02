package com.gmail.jdesmond10.crossvalidation;

import static org.junit.Assert.assertTrue;

import org.ejml.simple.SimpleMatrix;
import org.junit.Test;

public class LinearDataTest {

	@Test
	public void testShuffle() {
		final LinearData data = get3by3Test();

		int count1 = 0;
		int count4 = 0;
		int count7 = 0;

		// Try thirty times for each arrangement. Should happen basically every
		// time.
		for (int i = 0; i < 100; i++) {
			data.shuffle();
			if (testShuffleHelper(1, data)) {
				count1++;
			} else if (testShuffleHelper(4, data)) {
				count4++;
				assertTrue("data is in the right order",
						data.getX().get(1) == 5 && data.getY().get(0) == 6);
			} else if (testShuffleHelper(7, data)) {
				count7++;
			}
		}

		assertTrue("I was counting correctly", count1 + count4 + count7 == 100);
		// Making sure that everything is actually getting shuffled.
		assertTrue("It's pretty random", count1 < 70 && count1 > 10);
		assertTrue("It's pretty random", count4 < 70 && count4 > 10);
		assertTrue("It's pretty random", count7 < 70 && count7 > 10);
	}

	/**
	 * returns true if the first number in m is firstint
	 */
	private static boolean testShuffleHelper(final int firstInt,
			final LinearData l) {
		return (l.getX().get(0) == firstInt);
	}

	@Test
	public void testConvertToSingleMatrix() {
		final LinearData data = get3by3Test();

		final SimpleMatrix expected = new SimpleMatrix(3, 3, true, 1, 2, 3, 4,
				5, 6, 7, 8, 9);
		final SimpleMatrix single = data.convertToSingleMatrix();
		assertTrue("size is correct", single.getNumElements() == 9);
		// For further confirmation that they're the same just print them both
		// out. I wasn't able to make a perfect comparison.
		// System.out.println(expected.toString() + single.toString());
		assertTrue(
				"The matrix is the same as my test Matrix",
				single.extractVector(true, 1).get(2) == expected.extractVector(
						true, 1).get(2));
	}

	public static LinearData get3by3Test() {
		final SimpleMatrix X = new SimpleMatrix(3, 2, true, 1, 2, 4, 5, 7, 8);
		final SimpleMatrix Y = new SimpleMatrix(3, 1, true, 3, 6, 9);
		final LinearData data = new LinearData(X, Y);
		return data;
	}

}
