package amith.test.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import amith.test.dao.LanguageDao;
import amith.test.models.Language;

/**
 * Service layer to access LanguageDao
 * 
 * @author Amith
 *
 */
@Service
public class LanguageService {

	private LanguageDao langDao;

	@Autowired
	public LanguageService(LanguageDao langDao) {
		super();
		this.langDao = langDao;
	}

	public void init(InputStream in) {
		langDao.init(in);
	}

	public List<Language> getData() throws IOException {
		return langDao.getLanguages();
	}

}
