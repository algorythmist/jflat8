package com.tecacet.jflat8.impl;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.function.Function;

import com.tecacet.jflat8.ConverterRegistry;
import com.tecacet.jflat8.CoreFlatFileReader;
import com.tecacet.jflat8.FlatFileReader;
import com.tecacet.jflat8.FlatFileReaderCallback;
import com.tecacet.jflat8.converters.LocalDateConverter;
import com.tecacet.jflat8.util.ResourceLoader;

public abstract class AbstractFlatFileReader<T> implements FlatFileReader<T> {

	private final ResourceLoader resourceLoader = new ResourceLoader();
	private final ConverterRegistry converterRegistry = new JoddConverterRegistry();
	private final CoreFlatFileReader<T> coreFlatFileReader;
	
	public AbstractFlatFileReader(CoreFlatFileReader<T> coreFlatFileReader) {
		super();
		this.coreFlatFileReader = coreFlatFileReader;
		registerDefaultConverters();
	}

	@Override
	public void read(InputStream is, FlatFileReaderCallback<T> callback) throws IOException {
		coreFlatFileReader.read(is, callback);
	}
	
	@Override
	public void read(String resourceName, FlatFileReaderCallback<T> callback) throws IOException {
		InputStream is = resourceLoader.loadResource(resourceName);
		read(is, callback);
	}

	@Override
	public List<T> readToList(String resourceName) throws IOException {
		InputStream is = resourceLoader.loadResource(resourceName);
		return coreFlatFileReader.readToList(is);
	}
	
	@Override
	public <C> void registerConverter(Class<C> toType, Function<String, C> converter) {
		converterRegistry.registerConverter(toType, converter);
	}
	
	private void registerDefaultConverters() {
		registerConverter(LocalDate.class,  new LocalDateConverter());
		registerConverter(LocalDateTime.class, string -> LocalDateTime.parse(string));
		registerConverter(LocalTime.class, string -> LocalTime.parse(string));
	}
}
