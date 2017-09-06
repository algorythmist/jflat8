package com.tecacet.jflat8.impl;

import org.apache.commons.csv.CSVFormat;

public class DefaultCSVReader extends CSVFlatFileReader<String[]> {

	public DefaultCSVReader() {
		this(CSVFormat.DEFAULT);
	}
	
	public DefaultCSVReader(CSVFormat csvFormat) {
		super(new ArrayBeanMapper(), csvFormat);
	}

}
