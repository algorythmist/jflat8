package com.tecacet.jflat8.impl;

import static org.junit.Assert.*;

import org.junit.Test;

import com.tecacet.jflat8.LineMapper;
import com.tecacet.jflat8.RowRecord;

public class RegexLineMapperTest {

	@Test
	public void testDelimiter() {
		LineMapper lineMapper = new RegexLineMapper("\\|");
		RowRecord record = lineMapper.apply(1L, "ABCD|78|&%");
		assertEquals(3, record.size());
		assertEquals("ABCD", record.get(0));
		assertEquals("78", record.get(1));
		assertEquals("&%", record.get(2));
	}
	
	@Test
	public void testWords() {
		LineMapper lineMapper = new RegexLineMapper("\\s+");
		RowRecord record = lineMapper.apply(1L, "The quick brown fox jumped over the lazy dog.");
		assertEquals(9, record.size());
		assertEquals("[The, quick, brown, fox, jumped, over, the, lazy, dog.]", record.toString());
	}

}
