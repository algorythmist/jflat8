package com.tecacet.jflat8;


import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.tecacet.jflat8.impl.DefaultCSVReader;
import com.tecacet.jflat8.objects.ImmutableQuote;


public class CSVWriterTest {

	private static final String TEST_CSV = "test.csv";

	@Test
	public void writeToFile() throws IOException {
		LocalDate date = LocalDate.of(2017, 2, 6);
		ImmutableQuote quote1 = new ImmutableQuote(date, BigDecimal.valueOf(1.0), BigDecimal.valueOf(2.0), BigInteger.valueOf(100), null);
		ImmutableQuote quote2 = new ImmutableQuote(date, BigDecimal.valueOf(10.0), BigDecimal.valueOf(20.0), BigInteger.valueOf(1000), null);
		String[] properties = { "date", "open", "close", "volume" };
		CSVWriter<ImmutableQuote> csvWriter = new CSVWriter<>(properties);
		
		final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		csvWriter.registerConverter(LocalDate.class, d -> d.format(dateFormat));
		csvWriter.writeToFile(TEST_CSV, Arrays.asList(quote1, quote2));
		
		DefaultCSVReader csvReader = new DefaultCSVReader();
		List<String[]> lines = csvReader.readToList(TEST_CSV);
		assertEquals(2, lines.size());
		String[] line1 = lines.get(0);
		assertEquals("[2017-02-06, 1.0, 2.0, 100]", Arrays.toString(line1));

		File file = new File(TEST_CSV);
		file.delete();
	}
}
