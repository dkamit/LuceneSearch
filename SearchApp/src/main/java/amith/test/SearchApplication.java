package amith.test;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import amith.test.models.Language;
import amith.test.service.IndexingService;
import amith.test.service.LanguageService;


/**
 * Spring-Boot Application to perform search
 * @author Amith
 *
 */
@SpringBootApplication
public class SearchApplication implements CommandLineRunner{
	
	private static final Logger log = LoggerFactory.getLogger(SearchApplication.class);
	
	@Autowired
	private ResourceLoader loader;

	@Autowired
	private LanguageService languageService;
	
	@Autowired
	private IndexingService indexService;
	
    public static void main(String[] args) {
        SpringApplication.run(SearchApplication.class, args);
    }

    /**
     * Loads the data.json provided from classpath, all the 
     * json objects are indexed here 
     */
	@Override
	public void run(String... arg0) throws Exception {
		log.info("Loading the classpath respurce data.json");
		Resource resource = loader.getResource("classpath:data/data.json");
		languageService.init(resource.getInputStream());
		log.info("Successfully loaded the contents from data.json");
		List<Language> data = languageService.getData();
		indexService.index(data); // Indexes data here
	}
}