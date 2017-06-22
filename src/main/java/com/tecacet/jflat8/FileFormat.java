package com.tecacet.jflat8;

public interface FileFormat {

	void skipLines(int lines);
	
	int getSkipLines();
	
	void skipEmptyLines(boolean v);
	
	boolean getSkipEmptyLines();
	
	void trimWhitespace(boolean v);
	
	boolean getTrimWhitespace();
	
	void setCommentMarker(String marker);
	
	String getCommentMarker();
	
	public default void skipHeader() {
		skipLines(1);
	}
	
	public default boolean getIgnoreComments() {
		return getCommentMarker() != null;
	}
	
}
