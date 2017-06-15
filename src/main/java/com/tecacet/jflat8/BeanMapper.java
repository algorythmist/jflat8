package com.tecacet.jflat8;

import java.util.function.Function;

/**
 * Map a Record to a Java Bean of type T 
 * 
 * @author dimitri
 *
 * @param <T>
 */
@FunctionalInterface
public interface BeanMapper<T> extends Function<RowRecord, T> {

}
