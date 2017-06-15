package com.tecacet.jflat8.impl;

import static org.junit.Assert.*;

import org.junit.Test;

import com.tecacet.jflat8.LineMapper;
import com.tecacet.jflat8.RowRecord;

public class FixedWidthLineMapperTest {

	@Test
	public void test() {
		LineMapper lineMapper = new FixedWidthLineMapper(new int[] {4,3,2});
		RowRecord record = lineMapper.apply(1L, "ABCD78 &%");
		assertEquals(3, record.size());
		assertEquals("ABCD", record.get(0));
		assertEquals("78", record.get(1));
		assertEquals("&%", record.get(2));
	}

}
