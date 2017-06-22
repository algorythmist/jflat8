package com.tecacet.jflat8.impl;

import com.tecacet.jflat8.BeanMapper;
import com.tecacet.jflat8.LineMapper;

public class LineParsingFlatFileReader<T> extends AbstractFlatFileReader<T> {

	public LineParsingFlatFileReader(LineMapper lineMapper, BeanMapper<T> beanMapper) {
		this(lineMapper, beanMapper, new FlatFileFormat());
	}
	
	public LineParsingFlatFileReader(LineMapper lineMapper, BeanMapper<T> beanMapper, FlatFileFormat fileFormat) {
		super(new LineBasedCoreFlatFileReader<>(lineMapper, beanMapper, fileFormat));
	}

}
