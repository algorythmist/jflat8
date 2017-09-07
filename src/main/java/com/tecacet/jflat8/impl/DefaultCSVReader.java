package com.tecacet.jflat8.impl;

import com.tecacet.jflat8.CSVFileFormat;

public class DefaultCSVReader extends CSVFlatFileReader<String[]> {

	public DefaultCSVReader() {
		this(CSVFileFormat.defaultFormat());
	}
	
	public DefaultCSVReader(CSVFileFormat csvFormat) {
		super(new ArrayBeanMapper(), csvFormat);
	}

}
