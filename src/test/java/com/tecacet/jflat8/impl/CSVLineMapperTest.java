package com.tecacet.jflat8.impl;

import static org.junit.Assert.*;

import org.junit.Test;

import com.tecacet.jflat8.RowRecord;

public class CSVLineMapperTest {

    
    
	@Test
	public void test() {
        CSVLineMapper lineMapper = new CSVLineMapper();
		RowRecord record =lineMapper.apply(1L, "Hello,\"Bob\",Marley");
		assertEquals("Hello", record.get(0));
		assertEquals("Bob", record.get(1));
		assertEquals("Marley", record.get(2));
	}
	
	@Test
	public void testEmptyLines() {
	    CSVLineMapper lineMapper = new CSVLineMapper();
	    lineMapper.setDelimiter(";");
        RowRecord record =lineMapper.apply(1L, "1;2;;4;5;6;;");
        assertEquals(8, record.size());
	}

}
