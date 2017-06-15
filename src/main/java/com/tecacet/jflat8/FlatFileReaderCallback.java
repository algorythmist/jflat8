package com.tecacet.jflat8;

/**
 * Allows custom processing of mapped records 
 * 
 * @author dimitri
 *
 * @param <T>
 */
public interface FlatFileReaderCallback<T> {

	void processRow(RowRecord row, T bean);
}
