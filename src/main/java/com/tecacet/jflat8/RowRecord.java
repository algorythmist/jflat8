package com.tecacet.jflat8;

/**
 * Represents a row in a flat file
 * 
 * @author dimitri
 *
 */
public interface RowRecord {

	/**
	 * Access token in the row by index
	 * 
	 * @param index
	 * @return
	 */
	String get(int index);
	
	/**
	 * Access token in the row by name
	 * 
	 * @param name
	 * @return
	 */
	String get(String name);
	
	/**
	 * Number of tokens
	 * 
	 * @return
	 */
	int size();
	
	/**
	 * The row number in the file (0 based)
	 * @return
	 */
	long getRowNumber();
}
