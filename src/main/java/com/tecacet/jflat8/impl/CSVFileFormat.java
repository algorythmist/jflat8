package com.tecacet.jflat8.impl;

import java.io.IOException;
import java.io.Reader;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;

import com.tecacet.jflat8.FileFormat;

public class CSVFileFormat implements FileFormat {

	private CSVFormat format;
	
	public CSVFileFormat(CSVFormat format) {
		this.format = format;
	}

	@Override
	public void skipLines(int lines) {
		//TODO
	}

	@Override
	public int getSkipLines() {
		// TODO 
		return 0;
	}

	@Override
	public void skipEmptyLines(boolean v) {
		format = format.withIgnoreEmptyLines(v);
	}

	@Override
	public boolean getSkipEmptyLines() {
		return format.getIgnoreEmptyLines();
	}

	@Override
	public void trimWhitespace(boolean v) {
		format = format.withTrim();
	}

	@Override
	public boolean getTrimWhitespace() {
		return format.getTrim();
	}


	@Override
	public void setCommentMarker(String marker) {
		//TODO how to support more than 1 char
		format = format.withCommentMarker(marker.charAt(0));
	}

	@Override
	public String getCommentMarker() {
		return format.getCommentMarker().toString();
	}
	
	public CSVParser parse(Reader reader) throws IOException {
		return format.parse(reader);
	}
	
	public CSVFormat getCSVFormat() {
		return format;
	}

}
