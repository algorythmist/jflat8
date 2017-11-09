package com.tecacet.jflat8;

import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;

public class CSVFileFormat implements FileFormat {

	private CSVFormat csvFormat;
	private int skipLines = 0;
	
	public CSVFileFormat(CSVFormat csvFormat) {
		super();
		this.csvFormat = csvFormat;
	}

	@Override
	public CSVFileFormat skipLines(int lines) {
		this.skipLines = lines;
		return this;
	}

	@Override
	public int getSkipLines() {
		return skipLines;
	}

	@Override
	public void skipEmptyLines(boolean v) {
		csvFormat = csvFormat.withIgnoreEmptyLines();
	}

	@Override
	public boolean getSkipEmptyLines() {
		return csvFormat.getIgnoreEmptyLines();
	}

	@Override
	public void trimWhitespace(boolean v) {
		csvFormat = csvFormat.withTrim(v);
	}

	@Override
	public boolean getTrimWhitespace() {
		return csvFormat.getTrim();
	}

	@Override
	public void setCommentMarker(String marker) {
		csvFormat = csvFormat.withCommentMarker(marker.charAt(0));
	}

	@Override
	public String getCommentMarker() {
		return String.valueOf(csvFormat.getCommentMarker());
	}

	public CSVParser parse(InputStreamReader reader) throws IOException {
		return csvFormat.parse(reader);
	}
	
	public void setQuoteChar(char quoteChar) {
		csvFormat = csvFormat.withQuote(quoteChar);
	}
	
	public void setDelimiter(char delimiter) {
		csvFormat = csvFormat.withDelimiter(delimiter);
	}
	
	public static CSVFileFormat defaultFormat() {
		return new CSVFileFormat(CSVFormat.DEFAULT);
	}
	
	public static CSVFileFormat defaultIndexFormat() {
		CSVFormat csvFormat = CSVFormat.DEFAULT.withFirstRecordAsHeader().withSkipHeaderRecord();
		return new CSVFileFormat(csvFormat);
	}

	public static CSVFileFormat defaultHeaderFormat() {
		CSVFormat csvFormat = CSVFormat.DEFAULT.withFirstRecordAsHeader();
		return new CSVFileFormat(csvFormat);
	}
}
