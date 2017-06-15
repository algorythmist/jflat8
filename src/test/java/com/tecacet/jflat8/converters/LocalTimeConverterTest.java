package com.tecacet.jflat8.converters;

import static org.junit.Assert.assertEquals;

import java.time.LocalTime;

import org.junit.Test;

public class LocalTimeConverterTest {

	@Test
	public void test() {
		LocalTimeConverter converter = new LocalTimeConverter("HH:mm:ss");
		assertEquals(LocalTime.of(5, 30, 45), converter.apply("05:30:45"));
	}

}
