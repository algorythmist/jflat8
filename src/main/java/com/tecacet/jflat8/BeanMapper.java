package com.tecacet.jflat8;

import java.util.function.Function;
import java.util.function.Supplier;

public interface BeanMapper<T> extends Function<RowRecord, T>{

	BeanMapper<T> replaceBeanFactory(Supplier<T> beanFactory);
	
}
