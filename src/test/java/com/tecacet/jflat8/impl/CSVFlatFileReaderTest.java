package com.tecacet.jflat8.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.junit.Test;

import com.tecacet.jflat8.BeanMapper;
import com.tecacet.jflat8.FlatFileReader;
import com.tecacet.jflat8.converters.LocalDateConverter;
import com.tecacet.jflat8.objects.Movie;
import com.tecacet.jflat8.util.ResourceLoader;

public class CSVFlatFileReaderTest {

	private final ResourceLoader resourceLoader = new ResourceLoader();

	@Test
	public void testRead() throws IOException {

		// TODO incorporate in framework
		CSVFormat csvFormat = CSVFormat.DEFAULT.withDelimiter('|').withCommentMarker('#');
		BeanMapper<Movie> beanMapper = new IndexBeanMapper<>(Movie.class,
				new String[] { "id", "name", "releaseDate", "videoReleaseDate", "imdbURL" });

		FlatFileReader<Movie> flatFileReader = new CSVFlatFileReader<>(beanMapper, csvFormat);
		flatFileReader.registerConverter(LocalDate.class, new LocalDateConverter("dd-MMM-yyyy"));

		InputStream is = resourceLoader.loadResource("src/test/data/movies.txt");
		List<Movie> movies = flatFileReader.readToList(is);
		assertEquals(9, movies.size());

		Movie movie = movies.get(3);
		assertEquals(4, movie.getId());
		assertEquals("Get Shorty (1995)", movie.getName());
		assertEquals(LocalDate.of(1995, 1, 1), movie.getReleaseDate());
		assertNull(movie.getVideoReleaseDate());
		assertEquals("http://us.imdb.com/M/title-exact?Get%20Shorty%20(1995)", movie.getImdbURL());
	}

}
