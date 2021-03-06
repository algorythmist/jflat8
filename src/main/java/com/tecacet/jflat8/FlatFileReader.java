package com.tecacet.jflat8;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.function.Function;

public interface FlatFileReader<T> extends CoreFlatFileReader<T> {

	List<T> readToList(String resourceName) throws IOException;
	
	List<T> readToList(InputStream is) throws IOException;
	
	void read(String resourceName, FlatFileReaderCallback<T> callback) throws IOException;
	
	<C> void registerConverter(Class<C> toType, Function<String, C> converter);

}
