package com.tecacet.jflat8.impl;

import com.tecacet.jflat8.RowRecord;

public class ArrayRowRecord implements RowRecord {

	private final String[] tokens;
	private final long lineNumber;

	public ArrayRowRecord(long lineNumber, String[] tokens) {
		super();
		this.tokens = tokens;
		this.lineNumber = lineNumber;
	}

	@Override
	public String get(int index) {
		return tokens[index];
	}

	@Override
	public String get(String name) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int size() {
		return tokens.length;
	}

	@Override
	public long getRowNumber() {
		return lineNumber;
	}

}
