package amith.test.controller;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.queryparser.classic.ParseException;
import org.junit.BeforeClass;
import org.junit.Test;

import amith.test.models.Language;
import amith.test.service.IndexingService;

public class SearchLanguageControllerTest {

	private static IndexingService indexService;
	private static SearchLanguageController controller;

	@BeforeClass
	public static void setup() {
		indexService = mock(IndexingService.class);
		controller = new SearchLanguageController(indexService);
	}

	@Test
	public void TestGetLanguages() throws IOException, ParseException {
		List<Language> value = new ArrayList<>(
				asList(new Language("Lisp", "Lisp", "Lisp"), new Language("Lisp", "Lisp", "Common Lisp")));
		when(indexService.query("Lisp")).thenReturn(value);
		List<Language> languages = controller.getLanguages("Lisp");
		assertEquals("Number of languages should be 2", 2, languages.size());
	}

	@Test(expected = ParseException.class)
	public void TestThrowException() throws IOException, ParseException {
		when(indexService.query("*")).thenThrow(new ParseException());
		controller.getLanguages("*");
	}

}
