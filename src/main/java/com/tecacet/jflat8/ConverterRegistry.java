package com.tecacet.jflat8;

import java.util.function.Function;

public interface ConverterRegistry {

	<C> void registerConverter(Class<C> toType, Function<String, C> converter);

}