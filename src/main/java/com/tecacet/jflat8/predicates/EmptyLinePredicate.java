package com.tecacet.jflat8.predicates;

import java.util.function.BiPredicate;

public class EmptyLinePredicate implements BiPredicate<Long, String> {

	@Override
	public boolean test(Long lineNumber, String line) {
		return line != null && !line.isEmpty();
	}

}
