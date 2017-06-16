package com.tecacet.jflat8.impl;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.junit.Test;

import com.tecacet.jflat8.BeanMapper;
import com.tecacet.jflat8.converters.LocalDateConverter;
import com.tecacet.jflat8.objects.Movie;
import com.tecacet.jflat8.util.ResourceLoader;

import jodd.typeconverter.TypeConverterManager;

public class CSVBasedFlatFileReaderTest {

	private final ResourceLoader resourceLoader = new ResourceLoader();

	@Test
	public void testRead() throws IOException {

		// TODO incorporate in framework
		TypeConverterManager.register(LocalDate.class, Converters.fromFunction(new LocalDateConverter("dd-MMM-yyyy")));

		// TODO incorporate in framework
		CSVFormat csvFormat = CSVFormat.DEFAULT.withDelimiter('|');
		BeanMapper<Movie> beanMapper = new IndexBeanMapper<>(Movie.class,
				new String[] { "id", "name", "releaseDate", "videoReleaseDate", "imdbURL" });

		CoreFlatFileReader<Movie> flatFileReader = new CSVBasedFlatFileReader<>(beanMapper, csvFormat);
		InputStream is = resourceLoader.loadResource("src/test/data/movies.txt");
		List<Movie> movies = flatFileReader.read(is);
		assertEquals(10, movies.size());
		
		Movie movie = movies.get(3);
		assertEquals(4, movie.getId());
		assertEquals("Get Shorty (1995)", movie.getName());
		assertEquals(LocalDate.of(1995, 1, 1), movie.getReleaseDate());
		assertNull(movie.getVideoReleaseDate());
		assertEquals("http://us.imdb.com/M/title-exact?Get%20Shorty%20(1995)", movie.getImdbURL());
	}

}
