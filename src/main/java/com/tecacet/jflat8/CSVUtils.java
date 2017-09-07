package com.tecacet.jflat8;

import java.util.Map;

import com.tecacet.jflat8.impl.CSVFlatFileReader;

public class CSVUtils {

	public static <T> CSVFlatFileReader<T> createWithIndexMapping(Class<T> type, String[] properties) {
		return new CSVFlatFileReader<T>(type, properties);
	}

	public static <T> CSVFlatFileReader<T> createWithHeaderMapping(Class<T> type, String[] header, String[] properties) {
		return new CSVFlatFileReader<T>(type, header, properties);
	}
	
	public static <T> CSVFlatFileReader<T> createWithHeaderMapping(Class<T> type, Map<String, String> properties) {
		return new CSVFlatFileReader<T>(type, properties);
	}

}
