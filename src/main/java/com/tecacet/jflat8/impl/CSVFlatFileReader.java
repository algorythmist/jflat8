package com.tecacet.jflat8.impl;

import java.util.Map;

import org.apache.commons.csv.CSVFormat;

import com.tecacet.jflat8.BeanMapper;

public class CSVFlatFileReader<T> extends AbstractFlatFileReader<T> {

	public CSVFlatFileReader(BeanMapper<T> beanMapper, CSVFormat csvFormat) {
		super(new CSVBasedCoreFlatFileReader<>(beanMapper, csvFormat));
	}

	public CSVFlatFileReader(Class<T> type, String[] properties) {
		super(new CSVBasedCoreFlatFileReader<>(new IndexBeanMapper<>(type, properties), CSVFormat.DEFAULT.withFirstRecordAsHeader().withSkipHeaderRecord()));
	}

	public CSVFlatFileReader(Class<T> type, String[] header, String[] properties) {
		super(new CSVBasedCoreFlatFileReader<>(new HeaderBeanMapper<>(type, header, properties), CSVFormat.DEFAULT));
	}

	public CSVFlatFileReader(Class<T> type, Map<String, String> properties) {
		super(new CSVBasedCoreFlatFileReader<>(new HeaderBeanMapper<>(type, properties), CSVFormat.DEFAULT.withFirstRecordAsHeader()));
	}

}
