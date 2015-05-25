package com.gmail.jdesmond10.crossvalidation;

import java.io.FileNotFoundException;

import com.csvreader.CsvReader;

/**
 * For use with Cross Validation only. Converts .CSV files to EJML matrices and
 * vice versa.
 * 
 * @author Josh Desmond
 *
 */
public class DataConverter {

	public DataConverter() {
		// TODO Auto-generated constructor stub
	}

	public void read(final String csvFile) {

		CsvReader r = null;
		try {
			r = new CsvReader(csvFile);
		} catch (final FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(r.getColumnCount());
	}

	/**
	 * Writes the current data into a new text file in csv format.
	 * 
	 * @param fileName
	 *            The name of the file in the dat folder. IE, "text" would
	 *            create a file called "text.txt"
	 */
	public void writeData(final String fileName) {

	}
}
