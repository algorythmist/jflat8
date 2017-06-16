package com.tecacet.jflat8.impl;

import org.apache.commons.csv.CSVFormat;

import com.tecacet.jflat8.BeanMapper;

public class CSVFlatFileReader<T> extends AbstractFlatFileReader<T> {

	public CSVFlatFileReader(BeanMapper<T> beanMapper, CSVFormat csvFormat) {
		super(new CSVBasedCoreFlatFileReader<>(beanMapper, csvFormat));
	}

}
