package com.tecacet.jflat8.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.function.Function;

import com.tecacet.jflat8.ConverterRegistry;

import jodd.typeconverter.TypeConverterManager;

public class JoddConverterRegistry implements ConverterRegistry {

	public void registerDefaultConverters() {
		registerConverter(LocalDate.class, string -> LocalDate.parse(string));
		registerConverter(LocalDateTime.class, string -> LocalDateTime.parse(string));
		registerConverter(LocalTime.class, string -> LocalTime.parse(string));
	}

	/* (non-Javadoc)
	 * @see com.tecacet.jflat8.impl.ConvertedRegistry#registerConverter(java.lang.Class, java.util.function.Function)
	 */
	@Override
	public <C> void registerConverter(Class<C> toType, Function<String, C> converter) {
		TypeConverterManager.register(toType, Converters.fromFunction(converter));
	}
}
