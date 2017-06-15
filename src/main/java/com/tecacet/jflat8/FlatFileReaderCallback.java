package com.tecacet.jflat8;

public interface FlatFileReaderCallback<T> {

	void processRow(RowRecord row, T bean);
}
