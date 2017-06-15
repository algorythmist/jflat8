package com.tecacet.jflat8.converters;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Test;

public class LocalDateConverterTest {

	@Test
	public void builtInFormats() {
		LocalDateConverter converter = new LocalDateConverter();
		assertEquals(LocalDate.of(2013, 7, 15), converter.apply("2013-7-15"));
		assertEquals(LocalDate.of(2013, 7, 15), converter.apply("7-15-2013"));
		assertEquals(LocalDate.of(2013, 7, 15), converter.apply("7/15/2013"));
	}
	
	@Test
	public void suppliedFormats() {
		LocalDateConverter converter = new LocalDateConverter("M+d%yy");
		assertEquals(LocalDate.of(2013, 7, 15), converter.apply("7+15%13"));
	}

}
