package com.gmail.jdesmond10.crossvalidation;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class Main {

	public static final String VERSION = "0.0";

	public Main() {
		chooseData();
	}

	/**
	 * Checks the /dat folder for .CSV files, and asks the user which file to
	 * use.
	 * 
	 * @return the full string of the location.
	 */
	private String chooseData() {
		// Create a list of all files in the dat folder.
		final List fileList = new LinkedList<File>();

		return null;

	}

	/**
	 * Runs a hello message on the console, and runs Main.
	 */
	public static void main(final String[] args) {
		System.out.println(String.format("Running CrossValidation version %s",
				VERSION));

		final DataConverter c = new DataConverter();
		final ClassLoader l = ClassLoader.getSystemClassLoader();
		final String test = l.getResource("dat/test.csv").getPath();
		c.read(test);

	}
}
