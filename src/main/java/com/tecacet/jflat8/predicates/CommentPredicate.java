package com.tecacet.jflat8.predicates;

import java.util.function.BiPredicate;

public class CommentPredicate implements BiPredicate<Long, String> {

	private final String commentStart;

	public CommentPredicate() {
		this("#");
	}

	public CommentPredicate(String commentStart) {
		super();
		this.commentStart = commentStart;
	}

	@Override
	public boolean test(Long lineNumber, String line) {
		return !line.startsWith(commentStart);
	}

}
