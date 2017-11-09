package com.tecacet.jflat8;

public interface FileFormat {

	FileFormat skipLines(int lines);
	
	int getSkipLines();
	
	void skipEmptyLines(boolean v);
	
	boolean getSkipEmptyLines();
	
	void trimWhitespace(boolean v);
	
	boolean getTrimWhitespace();
	
	void setCommentMarker(String marker);
	
	String getCommentMarker();
	
	public default FileFormat skipHeader() {
		return skipLines(1);
	}
	
	public default boolean getIgnoreComments() {
		return getCommentMarker() != null;
	}
	
}
