package amith.test.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * Reads the configuration document.index.dir from the application.properties provided.
 * You can override the document.index.dir property by passing it as argument to jar
 * @author Amith
 *
 */
@Component
public class ConfigurationHandler {
	@Autowired
	private Environment env;
	
	/**
	 * document.index.dir is where the indexed data will be present
	 */
	public String getIndexFolder() {
		return env.getProperty("document.index.dir");
	}
}
