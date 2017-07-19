package amith.test.service;

import static org.junit.Assert.assertArrayEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.junit.BeforeClass;
import org.junit.Test;

import amith.test.config.ConfigurationHandler;
import amith.test.dao.LanguageDao;
import amith.test.models.Language;

public class IndexingServiceSearchTest {

	private static IndexWriter indexWriter;
	private static ConfigurationHandler config;

	private static IndexingService service;

	@BeforeClass
	public static void setup() throws IOException {
		config = mock(ConfigurationHandler.class);
		when(config.getIndexFolder()).thenReturn("testIndex2");
		indexWriter = getIndexWriter();
		indexData();
	}

	public static IndexWriter getIndexWriter() throws IOException {
		Analyzer analyzer = new StandardAnalyzer();
		IndexWriterConfig iwc = new IndexWriterConfig(analyzer);
		iwc.setOpenMode(OpenMode.CREATE_OR_APPEND);
		Directory indexDirectory = FSDirectory.open(Paths.get(config.getIndexFolder()));
		return new IndexWriter(indexDirectory, iwc);
	}

	public static List<Language> getLanguageObjects() throws IOException {
		InputStream file = IndexingServiceSearchTest.class.getClassLoader().getResourceAsStream("data/data.json");
		LanguageDao dao = new LanguageDao();
		LanguageService service = new LanguageService(dao);
		service.init(file);
		return service.getData();
	}

	public static void indexData() throws IOException {
		service = new IndexingService(indexWriter, config);
		service.index(getLanguageObjects());
	}

	@Test
	public void testSearchLispCommon() throws IOException, ParseException {
		List<String> languages = query("Lisp Common");
		String[] expected = { "Common Lisp" };
		assertArrayEquals("Results interchanged words failed", expected, languages.toArray());
	}

	@Test
	public void testSearchThomasEugene() throws IOException, ParseException {
		List<String> languages = query("Thomas Eugene");
		String[] expected = { "BASIC"};
		assertArrayEquals("Results Description failed", expected, languages.toArray());
	}
	
	@Test
	public void testSearchScriptingMicrosoft() throws IOException, ParseException {
		List<String> languages = query("Scripting Microsoft");
		String[] expected = { "JScript", "VBScript", "Windows PowerShell"};
		assertArrayEquals("Results match in different fields", expected, languages.toArray());
	}
	
	@Test
	public void testSearchJohnArray() throws IOException, ParseException {
		List<String> languages = query("john -array");
		String[] expected = { "Lisp", "S-Lang", "BASIC", "Haskell"};
		assertArrayEquals("Results with - operator failed", expected, languages.toArray());
	}
	
	private List<String> query(String query) throws IOException, ParseException {
		List<String> languages = service.query(query).stream().map(language -> language.getName())
				.collect(Collectors.toList());
		return languages;
	}
}
