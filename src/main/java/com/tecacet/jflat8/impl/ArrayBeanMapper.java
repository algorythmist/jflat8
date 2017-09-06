package com.tecacet.jflat8.impl;

import java.util.stream.IntStream;

import com.tecacet.jflat8.BeanMapper;
import com.tecacet.jflat8.RowRecord;

class ArrayBeanMapper implements BeanMapper<String[]> {

	@Override
	public String[] apply(RowRecord record) {
		return IntStream.range(0, record.size()).mapToObj(i -> record.get(i)).toArray(String[]::new);
	}
}
