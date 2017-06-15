package com.tecacet.jflat8.converters;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.function.Function;

public class LocalTimeConverter implements Function<String, LocalTime> {

	private final DateTimeFormatter[] formatters;

	public LocalTimeConverter(String dateFormatString) {
		super();
		this.formatters = new DateTimeFormatter[1];
		this.formatters[0] = DateTimeFormatter.ofPattern(dateFormatString);
	}

	public LocalTimeConverter(String[] dateFormatStrings) {
		super();
		this.formatters = new DateTimeFormatter[dateFormatStrings.length];
		for (int i = 0; i < dateFormatStrings.length; i++) {
			formatters[i] = DateTimeFormatter.ofPattern(dateFormatStrings[i]);
		}
	}

	@Override
	public LocalTime apply(String from) {
		if (from == null) {
			return null;
		}
		for (DateTimeFormatter formatter : formatters) {
			try {
				return LocalTime.parse(from, formatter);
			} catch (Exception e) {
				//try the next formatter
			}
		}
		String message = String.format("Error converting %s to LocalTime", from);
		throw new IllegalArgumentException(message);
	}

}
