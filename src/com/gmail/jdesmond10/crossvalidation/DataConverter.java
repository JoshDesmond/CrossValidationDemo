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

			this.data = Optional.of(new LinearData(B));

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
			try {
				System.out.println("Writing to " + fileName);
				data.get().convertToSingleMatrix()
				.saveToFileCSV(fileName);
			} catch (final IOException e) {
				e.printStackTrace();
			}
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

	public void setData(final LinearData da) {
		this.data = Optional.of(da);
	}
}
