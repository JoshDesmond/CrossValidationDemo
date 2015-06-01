package com.gmail.jdesmond10.crossvalidation;

import java.io.IOException;
import java.util.Optional;

import org.ejml.simple.SimpleMatrix;

/**
 * For use with Cross Validation only. Converts .CSV files to EJML matrices and
 * vice versa.
 * 
 *
 */
public class DataConverter {

	/**
	 * DataConverter will operate on this data. It will convert it to csv
	 * formats and vice versa.
	 */
	private Optional<LinearData> data;

	public DataConverter() {

	}

	/**
	 * Reads a cvsFile and stores the data in the instance of this class. After
	 * calling read, calling (DataConverter).getData() will return the
	 * LinearData object.
	 * 
	 * @param csvFile
	 */
	public void read(final String csvFile) {

		final SimpleMatrix m = new SimpleMatrix(); // Needed in order to call
		// loadCSV

		try {

			final SimpleMatrix B = m.loadCSV(csvFile); // Load the matrix int B

			// Now convert SimpleMatrix B into a LinearData object. //
			// *****************************************************//
			final int nc = B.numCols(); // Just for convenience/efficiency
			final int nr = B.numRows();

			System.out.println(nc + nr);

			// Validation:
			if (nc < 2) {
				System.err.println("Need more than one row");
			} else if (nr < 2) {
				System.err.println("need more than 2 data points.");
			}

			// we want to split this into an N by p matrix called X and an N by
			// 1 matrix called Y.
			final SimpleMatrix Y = B.extractVector(false, nc - 1); // TODO This
			// might
			// need to
			// be nc -1
			final SimpleMatrix X = B.extractMatrix(0, nr, 0, nc - 1);
			this.data = Optional.of(new LinearData(X, Y));

		} catch (final IOException e) {
			if (e.getMessage() == "Unexpected number of words on first line.") {
				// If the file presented isn't in the format of an ejml matrix,
				// lets parse it:
				parseData(csvFile);
				System.out.println("Unexpected number of words on first line.");
			} else
				e.printStackTrace();
		}
	}

	/**
	 * Converts the given csv file into an EJML matrix (or a LinearData Object).
	 * //TODO rewrite this once you actually write the method.
	 * 
	 * @param csvFile
	 */
	private void parseData(final String csvFile) {
		// TODO parseData

	}

	/**
	 * Writes the current data into a new text file in csv format.
	 * 
	 * @param fileName
	 *            The name of the file in the dat folder. IE, "text" would
	 *            create a file called "text.txt"
	 */
	public void writeData(final String fileName) {
		if (data.isPresent()) {
			// TODO combine data into EJML matrices, and save those.
			// data.get().saveToFileCSV("matrix_file.csv");
		} else {
			System.out.println("Error: writeData messed up");
		}
	}

	/**
	 * Returns data loaded, otherwise sends an error into the console.
	 * 
	 * @return
	 */
	public LinearData getData() {
		if (data.isPresent()) {
			return data.get();
		} else {
			System.out.println("data isn't present in getData");
			System.out.println(Thread.currentThread().getStackTrace()
					.toString());
			return null;
		}
	}
}
