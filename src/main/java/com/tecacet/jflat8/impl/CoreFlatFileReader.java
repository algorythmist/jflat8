package com.tecacet.jflat8.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.tecacet.jflat8.FlatFileReaderCallback;

public interface CoreFlatFileReader<T> {

	/**
	 * Read an input stream, processing each row with a callback
	 * 
	 * @param is
	 * @param callback
	 * @throws IOException
	 */
	void read(InputStream is, FlatFileReaderCallback<T> callback) throws IOException;
	
	/**
	 * Read an input strem into a list
	 * 
	 * @param is
	 * @return
	 * @throws IOException
	 */
	public default List<T> read(InputStream is) throws IOException {
		List<T> list = new ArrayList<>();
		read(is, (record, bean) -> list.add(bean));
		return list;
	}

}