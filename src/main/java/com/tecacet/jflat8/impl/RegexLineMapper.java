package com.tecacet.jflat8.impl;

import com.tecacet.jflat8.LineMapper;
import com.tecacet.jflat8.RowRecord;

public class RegexLineMapper implements LineMapper {

	private final String regex;
	
	public RegexLineMapper(String delimiter) {
		super();
		this.regex = delimiter;
	}

	@Override
	public RowRecord apply(Long lineNumber, String line) {
		return new ArrayRowRecord(lineNumber, line.split(regex));
	}

}
