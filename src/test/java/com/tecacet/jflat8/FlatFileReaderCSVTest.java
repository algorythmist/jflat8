package com.tecacet.jflat8;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.tecacet.jflat8.objects.ClassicQuote;
import com.tecacet.jflat8.objects.ImmutableQuote;


public class FlatFileReaderCSVTest {

	@Test
	public void testClassicQuote() throws IOException {
		FlatFileReader<ClassicQuote> csvReader = CSVUtils.createWithIndexMapping(ClassicQuote.class,
				new String[] { "date", "open", null, null, "close", "volume", null });
		List<ClassicQuote> quotes = csvReader.readToList("GLD.csv");
		assertEquals(134, quotes.size());
	}

	@Test
	public void testImmutableQuote() throws IOException {
		FlatFileReader<ImmutableQuote> csvReader = CSVUtils.createWithIndexMapping(ImmutableQuote.class,
				new String[] { "date", "open", null, null, "close", "volume", null });
		List<ImmutableQuote> quotes = csvReader.readToList("GLD.csv");
		assertEquals(134, quotes.size());
	}

	@Test
	public void readAsStream() throws IOException {
		FlatFileReader<ImmutableQuote> csvReader = CSVUtils.createWithIndexMapping(ImmutableQuote.class,
				new String[] { "date", "open", null, null, "close", "volume", "adjustedClose" });
		LocalDate date = LocalDate.of(2015, 5, 1);
		ImmutableQuote quote = csvReader.readToList("GLD.csv").stream().filter(q -> q.getDate().equals(date)).findFirst()
				.orElse(null);
		assertEquals(date, quote.getDate());
	}

	@Test
	public void readWithCallback() throws IOException {
		FlatFileReader<ImmutableQuote> csvReader = CSVUtils.createWithIndexMapping(ImmutableQuote.class,
				new String[] { "date", "open", null, null, "close", "volume", "adjustedClose" });

		List<RowRecord> records = new ArrayList<>();
		csvReader.read("GLD.csv", new FlatFileReaderCallback<ImmutableQuote>() {

			@Override
			public void accept(RowRecord row, ImmutableQuote bean) {
				records.add(row);
			}
		});
		assertEquals(134, records.size());
	}

	@Test
	public void withHeaderMapping() throws IOException {
		Map<String, String> properties = new HashMap<>();

		properties.put("Date", "date");
		properties.put("Open", "open");
		properties.put("Volume", "volume");
		FlatFileReader<ImmutableQuote> csvReader = CSVUtils.createWithHeaderMapping(ImmutableQuote.class, properties);
		List<ImmutableQuote> quotes = csvReader.readToList("GLD.csv");
		assertEquals(134, quotes.size());
		ImmutableQuote quote = quotes.get(0);
		assertEquals(LocalDate.of(2015, 12, 1), quote.getDate());
		assertEquals(102.30, quote.getOpen().doubleValue(), 0.001);
		assertEquals(5800200L, quote.getVolume().longValue());
	}

}
