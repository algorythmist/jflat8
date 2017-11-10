package com.tecacet.jflat8.impl;

import com.tecacet.jflat8.FileFormat;

public class FlatFileFormat implements FileFormat {

	private int skipLines = 0;
	private boolean skipEmptyLines = true;
	private boolean trimWhitespace = true;
	private String commentMarker = null;
	
	@Override
	public FlatFileFormat skipLines(int lines) {
		this.skipLines = lines;
		return this;
	}

	@Override
	public int getSkipLines() {
		return skipLines;
	}

	@Override
	public FileFormat skipEmptyLines(boolean v) {
		this.skipEmptyLines = v;
		return this;
	}

	@Override
	public boolean getSkipEmptyLines() {
		return skipEmptyLines;
	}

	@Override
	public FileFormat trimWhitespace(boolean v) {
		trimWhitespace = v;
		return this;
	}

	@Override
	public boolean getTrimWhitespace() {
		return trimWhitespace;
	}

	@Override
	public FileFormat setCommentMarker(String marker) {
		commentMarker = marker;
		return this;
	}

	@Override
	public String getCommentMarker() {
		return commentMarker;
	}
	
}
