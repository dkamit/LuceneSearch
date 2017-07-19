package amith.test.service;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

import amith.test.dao.LanguageDao;

public class LanguageServiceTest {
	
	@Test
	public void testLanguageServiceWorking() throws IOException {
		InputStream file = getClass().getClassLoader().getResourceAsStream("data/data.json");
		LanguageDao dao = new LanguageDao();
		LanguageService service = new LanguageService(dao);
		service.init(file);
		assertEquals("Number of languages are different", 97, service.getData().size());
	}
}
