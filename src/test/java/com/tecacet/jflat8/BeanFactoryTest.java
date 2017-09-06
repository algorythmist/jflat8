package com.tecacet.jflat8;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.tecacet.jflat8.objects.ClassicQuote;
import com.tecacet.jflat8.objects.ImmutableQuote;

public class BeanFactoryTest {

	@Test
	public void testNormalConstructor() {
		BeanFactory<ClassicQuote> beanFactory = new BeanFactory<>(ClassicQuote.class);
		ClassicQuote bean = beanFactory.get();
		assertEquals(ClassicQuote.class, bean.getClass());
	}
	
	@Test
	public void testNoDefaultConstructor() {
		BeanFactory<ImmutableQuote> beanFactory = new BeanFactory<>(ImmutableQuote.class);
		ImmutableQuote bean = beanFactory.get();
		assertEquals(ImmutableQuote.class, bean.getClass());
	}

}
