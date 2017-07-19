package amith.test.config;

import java.io.IOException;
import java.nio.file.Paths;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Provides the java objects at runtime using springboot configuration
 * @author Amith
 *
 */
@Configuration
public class LuceneConfig {

	@Autowired
	private ConfigurationHandler config;

	@Bean
	public IndexWriter getIndexWriter() throws IOException {
		Analyzer analyzer = new StandardAnalyzer();
		IndexWriterConfig iwc = new IndexWriterConfig(analyzer);
		iwc.setOpenMode(OpenMode.CREATE_OR_APPEND);
		Directory indexDirectory = FSDirectory.open(Paths.get(config.getIndexFolder()));
		return new IndexWriter(indexDirectory, iwc);
	}
}
