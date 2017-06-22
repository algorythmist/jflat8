package com.tecacet.jflat8.impl;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.junit.Test;

import com.tecacet.jflat8.BeanMapper;
import com.tecacet.jflat8.LineMapper;
import com.tecacet.jflat8.objects.Contact;
import com.tecacet.jflat8.util.ResourceLoader;

public class LineParsingFlatFileReaderTest {

	private final ResourceLoader resourceLoader = new ResourceLoader();

	@Test
	public void testRead() throws IOException {

		BeanMapper<Contact> beanMapper = new IndexBeanMapper<>(Contact.class,
				new String[] { "name", "state", "telephone" });
		LineMapper lineMapper = new FixedWidthLineMapper(new int[] { 20, 10, 12 });
		FlatFileFormat fileFormat = new FlatFileFormat();
		fileFormat.skipLines(1);
		LineBasedCoreFlatFileReader<Contact> flatFileReader = new LineBasedCoreFlatFileReader<>(lineMapper, beanMapper,
				fileFormat);

		InputStream is = resourceLoader.loadResource("src/test/data/directory.txt");
		List<Contact> contacts = flatFileReader.readToList(is);
		assertEquals(3, contacts.size());
		Contact contact = contacts.get(1);
		assertEquals("Mary Hartford", contact.getName());
		assertEquals("CA", contact.getState());
		assertEquals("319-519-4341", contact.getTelephone());
	}

}
