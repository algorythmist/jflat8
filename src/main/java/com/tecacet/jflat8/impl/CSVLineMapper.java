package com.tecacet.jflat8.impl;

import java.util.Arrays;

import com.tecacet.jflat8.LineMapper;
import com.tecacet.jflat8.RowRecord;

public class CSVLineMapper implements LineMapper {

	private String delimiter = ",";
	//TODO specify quote char
	
	@Override
	public RowRecord apply(Long lineNumber, String line) {
		//TODO handle nested quotes and commas within quotes
		String[] tokens = Arrays.stream(line.split(delimiter, -1)).map(string -> string.replaceAll("^\"|\"$", "")).toArray(String[]::new);
		return new ArrayRowRecord(lineNumber, tokens);
	}

	public String getDelimiter() {
		return delimiter;
	}

	public void setDelimiter(String delimiter) {
		this.delimiter = delimiter;
	}

	
}
