package com.tecacet.jflat8.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.tecacet.jflat8.BeanMapper;
import com.tecacet.jflat8.objects.ClassicQuote;

public class IndexBeanMapperTest {

	@Test
	public void test() {
		String[] properties = { "date", "open", "close", "volume" };
		BeanMapper<ClassicQuote> beanMapper = new IndexBeanMapper<>(ClassicQuote.class, properties);

		ClassicQuote quote = beanMapper
				.apply(new ArrayRowRecord(1, new String[] { "2015-04-09", "34.52", "36.87", "1001" }));
		assertEquals("Thu Apr 09 00:00:00 PDT 2015", quote.getDate().toString());
		assertEquals(34.52, quote.getOpen(), 0.001);
		assertEquals(36.87, quote.getClose(), 0.001);
		assertEquals(1001, quote.getVolume());
	}

}
