package com.tecacet.jflat8.util;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

public class ResourceLoaderTest {

	private ResourceLoader resourceLoader = new ResourceLoader();

	@Test(expected = IOException.class)
	public void missingResource() throws IOException {
		resourceLoader.loadResource("missing");
	}

	@Test
	public void fileResource() throws IOException {
		InputStream is = resourceLoader.loadResource("src/test/data/resource2.txt");
		assertNotNull(is);
	}
	
	@Test
	public void classpathResource() throws IOException {
		InputStream is = resourceLoader.loadResource("resource1.txt");
		assertNotNull(is);
	}
}
