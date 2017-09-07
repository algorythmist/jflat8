package com.tecacet.jflat8.impl;


import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.junit.Test;

import com.tecacet.jflat8.CSVFileFormat;
import com.tecacet.jflat8.FlatFileReader;

public class DefaultCSVReaderTest {

	@Test
	public void readToList() throws IOException {
		FlatFileReader<String[]> csvReader = new DefaultCSVReader();
		List<String[]> quotes = csvReader.readToList("GLD.csv");
		assertEquals(135, quotes.size());
	}
	
	  /**
     * Tests option to skip the first few lines of a file.
     * 
     * @throws IOException
     *             if bad things happen
     */
    @Test
    public void testSkippingLines() throws IOException {

        StringBuffer sb = new StringBuffer();
        sb.append("Skip this line\t with tab").append("\n"); // should skip this
        sb.append("And this line too").append("\n"); // and this
        sb.append("a\t'b\tb\tb'\tc").append("\n"); // single quoted elements
        CSVFileFormat fileFormat = CSVFileFormat.defaultFormat();
        
        fileFormat.setQuoteChar('\'');
        fileFormat.setDelimiter('\t');
        fileFormat.skipLines(2);
        CSVFlatFileReader<String[]> reader = new DefaultCSVReader(fileFormat);

        InputStream stream = new ByteArrayInputStream(sb.toString().getBytes(StandardCharsets.UTF_8.name()));
        List<String[]> lines = reader.readToList(stream);
        String[] nextLine = lines.get(0);
        assertEquals(3, nextLine.length);
        assertEquals("a", nextLine[0]);
    }
}
