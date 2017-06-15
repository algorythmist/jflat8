package com.tecacet.jflat8;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

public interface FlatFileReader<T> {

	List<T> readToList(String resourceName) throws IOException;

	List<T> readToList(InputStream is) throws IOException;

	Stream<T> readToStream(Reader reader) throws IOException;

	void readWithCallback(String resourceName, FlatFileReaderCallback<T> callback) throws IOException;

	void readWithCallback(InputStream is, FlatFileReaderCallback<T> callback) throws IOException;

	<C> void registerConverter(Class<C> toType, Function<String, C> converter);

}
