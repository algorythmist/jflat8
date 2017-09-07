package com.tecacet.jflat8.impl;

import java.util.Map;

import com.tecacet.jflat8.BeanMapper;
import com.tecacet.jflat8.CSVFileFormat;

public class CSVFlatFileReader<T> extends AbstractFlatFileReader<T> {

	public CSVFlatFileReader(BeanMapper<T> beanMapper, CSVFileFormat csvFormat) {
		super(new CSVBasedCoreFlatFileReader<>(beanMapper, csvFormat));
	}

	public CSVFlatFileReader(Class<T> type, String[] properties) {
		super(new CSVBasedCoreFlatFileReader<>(new IndexBeanMapper<>(type, properties), CSVFileFormat.defaultIndexFormat()));
	}

	public CSVFlatFileReader(Class<T> type, String[] header, String[] properties) {
		super(new CSVBasedCoreFlatFileReader<>(new HeaderBeanMapper<>(type, header, properties), CSVFileFormat.defaultHeaderFormat()));
	}

	public CSVFlatFileReader(Class<T> type, Map<String, String> properties) {
		super(new CSVBasedCoreFlatFileReader<>(new HeaderBeanMapper<>(type, properties), CSVFileFormat.defaultHeaderFormat()));
	}

}
