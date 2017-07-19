package amith.test.service;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import amith.test.config.ConfigurationHandler;
import amith.test.models.Language;

/**
 * Indexes data using lucene
 * 
 * @author Amith
 *
 */
@Service
public class IndexingService {

	private IndexWriter indexWriter;
	private ConfigurationHandler config;

	@Autowired
	public IndexingService(IndexWriter indexWriter, ConfigurationHandler config) {
		super();
		this.indexWriter = indexWriter;
		this.config = config;
	}

	/**
	 * Query's lucene index for searching.
	 * 
	 * @param query string from search box
	 * @return List<Language> object
	 * @throws IOException
	 * @throws ParseException
	 */
	public List<Language> query(String query) throws IOException, ParseException {
		IndexReader indexReader = DirectoryReader.open(FSDirectory.open(Paths.get(config.getIndexFolder())));
		IndexSearcher indexSearcher = new IndexSearcher(indexReader);
		MultiFieldQueryParser qp = new MultiFieldQueryParser(new String[] { "Name", "Type", "Designed by" },
				new StandardAnalyzer());
		qp.setDefaultOperator(MultiFieldQueryParser.Operator.AND);
		TopDocs results = indexSearcher.search(qp.parse(query), 100);
		ScoreDoc[] scoreDocs = results.scoreDocs;
		List<Language> languages = new ArrayList<>();
		for (ScoreDoc scoreDoc : scoreDocs) {
			Document doc = indexSearcher.doc(scoreDoc.doc);
			Language lang = new Language();
			lang.setDesignedBy(doc.get("Designed by"));
			lang.setName(doc.get("Name"));
			lang.setType(doc.get("Type"));
			languages.add(lang);
		}
		return languages;
	}

	/**
	 * Indexes the data using lucene
	 * 
	 * @param languages
	 * @throws IOException
	 */
	public void index(List<Language> languages) throws IOException {
		if(indexWriter.isOpen())
			indexWriter.deleteAll();
		try {
			for (Language language : languages) {
				Document doc = new Document();
				Field nameField = new TextField("Name", language.getName(), Field.Store.YES);
				doc.add(nameField);
				Field typeField = new TextField("Type", language.getType(), Field.Store.YES);
				doc.add(typeField);
				Field designedByField = new TextField("Designed by", language.getDesignedBy(), Field.Store.YES);
				doc.add(designedByField);
				indexWriter.addDocument(doc);
			}
		} finally {
			indexWriter.commit();
			indexWriter.close();
		}
	}
}
