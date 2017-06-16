package com.tecacet.jflat8.predicates;

import java.util.function.Predicate;

public class NotNullPredicate<T> implements Predicate<T> {

	@Override
	public boolean test(T bean) {
		return bean != null;
	}

}
