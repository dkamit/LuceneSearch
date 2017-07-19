package amith.test.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import amith.test.models.Language;

/**
 * Language Dao is the data access layer to data.json.
 * Dao is initialized by the inputstream of data.json.
 * @author Amith
 *
 */
@Component
public class LanguageDao {

	private InputStream in;

	/**
	 * Initialize the Dao
	 * @param in Inputstream of data.json
	 */
	public void init(InputStream in) {
		this.in = in;
	}

	/**
	 * Reads the json file to array of objects
	 * @return
	 * @throws IOException
	 */
	public List<Language> getLanguages() throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		List<Language> languages = mapper.readValue(this.in, new TypeReference<List<Language>>() {
		});
		return languages;
	}
}
