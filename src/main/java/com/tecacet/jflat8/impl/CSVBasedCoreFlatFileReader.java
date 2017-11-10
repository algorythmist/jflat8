package com.tecacet.jflat8.impl;

import com.tecacet.jflat8.BeanMapper;
import com.tecacet.jflat8.CSVFileFormat;
import com.tecacet.jflat8.CoreFlatFileReader;
import com.tecacet.jflat8.FlatFileReaderCallback;
import com.tecacet.jflat8.RowRecord;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.function.Predicate;

/*
 * Parsing implementation class
 */
class CSVBasedCoreFlatFileReader<T> implements CoreFlatFileReader<T> {

	private final BeanMapper<T> beanMapper;
	private final CSVFileFormat csvFormat;

	private Predicate<T> beanPredicate;

	public CSVBasedCoreFlatFileReader(BeanMapper<T> beanMapper, CSVFileFormat csvFormat) {
		super();
		this.beanMapper = beanMapper;
		this.csvFormat = csvFormat;
	}

	@Override
	public void read(InputStream is, FlatFileReaderCallback<T> callback) throws IOException {
		InputStreamReader reader = new InputStreamReader(is, "UTF-8");
		CSVParser parser = csvFormat.parse(reader);
		int line = 0;
		for (Iterator<CSVRecord> i = parser.iterator(); i.hasNext();) {
			CSVRecord csvRecord = i.next();
			line++;
			if (line <= csvFormat.getSkipLines()) {
				continue;
			}
			RowRecord rowRecord = new CSVRowRecord(csvRecord);
			T bean = beanMapper.apply(rowRecord);
			if (beanPredicate != null && !beanPredicate.test(bean)) {
				continue;
			}
			callback.accept(rowRecord, bean);
		}
		parser.close();
		reader.close();
	}

	public void setBeanPredicate(Predicate<T> beanPredicate) {
		this.beanPredicate = beanPredicate;
	}

}
