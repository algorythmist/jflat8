package com.tecacet.jflat8.impl;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.List;

import org.junit.Test;

import com.tecacet.jflat8.BeanMapper;
import com.tecacet.jflat8.LineMapper;
import com.tecacet.jflat8.converters.LocalDateConverter;
import com.tecacet.jflat8.objects.Contact;
import com.tecacet.jflat8.util.ResourceLoader;

import jodd.typeconverter.TypeConverterManager;

public class LineBasedFlatFileReaderTest {
	
	private final ResourceLoader resourceLoader = new ResourceLoader();
	
	@Test
	public void testRead() throws IOException {
		// TODO incorporate in framework
		TypeConverterManager.register(LocalDate.class, Converters.fromFunction(new LocalDateConverter("dd-MMM-yyyy")));

		BeanMapper<Contact> beanMapper = new IndexBeanMapper<>(Contact.class,
				new String[] { "name", "state", "telephone" });
		LineMapper lineMapper = new FixedWidthLineMapper(new int[] { 20, 10, 12 });
		LineBasedFlatFileReader<Contact> flatFileReader = new LineBasedFlatFileReader<>(lineMapper, beanMapper);
		flatFileReader.setSkipLines(1);
		InputStream is = resourceLoader.loadResource("src/test/data/directory.txt");
		List<Contact> contacts = flatFileReader.read(is);
		assertEquals(3, contacts.size());
		Contact contact = contacts.get(1);
		assertEquals("Mary Hartford", contact.getName());
		assertEquals("CA", contact.getState());
		assertEquals("319-519-4341", contact.getTelephone());
	}

}
