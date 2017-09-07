package com.tecacet.jflat8.impl;

import java.util.function.Function;

import com.tecacet.jflat8.ConverterRegistry;

import jodd.typeconverter.TypeConverterManager;

public class JoddConverterRegistry implements ConverterRegistry {
	
	@Override
	public <C> void registerConverter(Class<C> toType, Function<String, C> converter) {
		TypeConverterManager.register(toType, Converters.fromFunction(converter));
	}
}
