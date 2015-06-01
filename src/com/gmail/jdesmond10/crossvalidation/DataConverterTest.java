package com.gmail.jdesmond10.crossvalidation;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Before;
import org.junit.Test;

/**
 * This is also acting as the test for LinearData
 * 
 * @author Josh Desmond
 *
 */
public class DataConverterTest {

	private LinearData testData;
	private final String path = "C:/CMD/test.csv";

	@Before
	public void setUp() throws Exception {
		// Set up writes a textFile in C:/CMD/test that will be used for these
		// tests. Windows only tests, sorry- I'm lazy.

		// Load test's file name

		// The following block of text is stolen from:
		// http://java-buddy.blogspot.com/2012/05/write-string-to-file-using-java.html
		// It writes a text file with the csv I want.
		{
			FileWriter fileWriter = null;
			try {
				final String content = new StringBuilder().append("9 3 real\n")
						.append("3 4 9\n").append("-2 3 -3\n")
						.append("4 6 8\n").append("4 5 5\n").append("20 1 8\n")
						.append("7 -6 6\n").append("9 30 70\n")
						.append("8 14 30\n").append("17 26 45\n").toString();

				final File newTextFile = new File(path);
				fileWriter = new FileWriter(newTextFile);
				fileWriter.write(content);
				fileWriter.close();
			} catch (final IOException ex) {
				Logger.getLogger(DataConverter.class.getName()).log(
						Level.SEVERE, null, ex);
			} finally {
				try {
					fileWriter.close();
				} catch (final IOException ex) {
					Logger.getLogger(DataConverter.class.getName()).log(
							Level.SEVERE, null, ex);
				}
			}
		}
	}

	@Test
	public void testRead() {
		final DataConverter converter = new DataConverter();

		converter.read(path);
		testData = converter.getData();
		assertTrue("testData shouldn't be null", testData != (null));

		assertTrue(testData.getNumDataPoints() == 9);
		assertTrue(testData.getNumInputVariables() == 2);
		// Gets the fifth element, second row in X, checks if one.
		assertTrue(testData.getX().get(4, 1) == 1);
	}


}
