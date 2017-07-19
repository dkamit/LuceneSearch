package amith.test.controller;

import java.io.IOException;
import java.util.List;

import org.apache.lucene.queryparser.classic.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import amith.test.models.Language;
import amith.test.service.IndexingService;

/**
 * Search Language Controller is hit to get the json response from
 * lucene index
 * @author Amith
 *
 */
@RestController
public class SearchLanguageController {

	private IndexingService indexService;

	@Autowired
	public SearchLanguageController(IndexingService indexService) {
		this.indexService = indexService;
	}

	@RequestMapping("/search")
	public List<Language> getLanguages(@RequestParam(value = "query") String query) throws IOException, ParseException {
		List<Language> results = indexService.query(query);
		return results;
	}
}
