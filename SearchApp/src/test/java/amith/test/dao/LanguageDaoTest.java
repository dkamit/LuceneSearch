package amith.test.dao;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

public class LanguageDaoTest {

	/**
	 * Reads the file from classpath and check the count of language objects
	 * 
	 * @throws IOException
	 */
	@Test
	public void TestLangDao() throws IOException {
		ClassLoader classLoader = getClass().getClassLoader();
		InputStream file = classLoader.getResourceAsStream("data/data.json");
		LanguageDao dao = new LanguageDao();
		dao.init(file);
		assertEquals("Number of languages are different", 97, dao.getLanguages().size());
	}

}
