package com.tecacet.jflat8.impl;

import java.util.function.Function;

import jodd.typeconverter.TypeConverter;

public class Converters {

	static <C> TypeConverter<C> fromFunction(Function<String, C> function) {
		return new TypeConverter<C>() {

			@Override
			public C convert(Object o) {
				if (o == null) {
					return null;
				}
				return function.apply(o.toString().trim());
			}
		};
	}
}
