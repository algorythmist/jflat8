package com.tecacet.jflat8.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.function.BiPredicate;

import com.tecacet.jflat8.BeanMapper;
import com.tecacet.jflat8.CoreFlatFileReader;
import com.tecacet.jflat8.FlatFileReaderCallback;
import com.tecacet.jflat8.LineMapper;
import com.tecacet.jflat8.RowRecord;

public class LineBasedCoreFlatFileReader<T> implements CoreFlatFileReader<T> {

	private final LineMapper lineMapper;
	private final BeanMapper<T> beanMapper;
	private final FlatFileFormat fileFormat;

	private BiPredicate<Long, String> linePredicate;

	public LineBasedCoreFlatFileReader(LineMapper lineMapper, BeanMapper<T> beanMapper, FlatFileFormat fileFormat) {
		super();
		this.lineMapper = lineMapper;
		this.beanMapper = beanMapper;
		this.fileFormat = fileFormat;
	}

	@Override
	public void read(InputStream inputStream, FlatFileReaderCallback<T> callback) throws IOException {
		InputStreamReader isReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
		BufferedReader reader = new BufferedReader(isReader);

		String line;
		long lineNumber = 0;

		while ((line = reader.readLine()) != null) {
			if (lineNumber < fileFormat.getSkipLines()) {
				lineNumber++;
				continue;
			}
			if (linePredicate != null && !linePredicate.test(lineNumber, line)) {
				continue;
			}
			RowRecord record = lineMapper.apply(lineNumber++, line);
			T bean = beanMapper.apply(record);
			callback.accept(record, bean);
		}
		isReader.close();
		reader.close();
	}

	public LineBasedCoreFlatFileReader<T> withSkipLineFilter(BiPredicate<Long, String> filter) {
		this.linePredicate = filter;
		return this;
	}

}
