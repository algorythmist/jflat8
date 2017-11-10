package com.tecacet.jflat8;

public interface FileFormat {

	FileFormat skipLines(int lines);
	
	int getSkipLines();
	
	FileFormat skipEmptyLines(boolean v);
	
	boolean getSkipEmptyLines();
	
	FileFormat trimWhitespace(boolean v);
	
	boolean getTrimWhitespace();
	
	FileFormat setCommentMarker(String marker);
	
	String getCommentMarker();
	
	public default FileFormat skipHeader() {
		return skipLines(1);
	}
	
	public default boolean getIgnoreComments() {
		return getCommentMarker() != null;
	}
	
}
