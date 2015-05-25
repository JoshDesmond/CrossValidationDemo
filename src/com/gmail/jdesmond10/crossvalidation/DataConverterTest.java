package com.gmail.jdesmond10.crossvalidation;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DataConverterTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testRead() {
		// Load test's file name
		final ClassLoader l = ClassLoader.getSystemClassLoader();
		final String test = l.getResource("dat/test.txt").getPath();
		assertTrue(test != null);

		final DataConverter converter = new DataConverter();




		fail("Not yet implemented");
	}

	@Test
	public void testWriteData() {
		fail("Not yet implemented");
	}

}
