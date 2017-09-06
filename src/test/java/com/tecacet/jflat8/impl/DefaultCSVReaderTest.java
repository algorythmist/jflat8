package com.tecacet.jflat8.impl;


import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

import com.tecacet.jflat8.FlatFileReader;

public class DefaultCSVReaderTest {

	@Test
	public void readToList() throws IOException {
		FlatFileReader<String[]> csvReader = new DefaultCSVReader();
		List<String[]> quotes = csvReader.readToList("GLD.csv");
		assertEquals(135, quotes.size());
	}
}
