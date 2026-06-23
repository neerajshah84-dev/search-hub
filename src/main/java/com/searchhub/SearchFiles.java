package com.searchhub;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.StoredFields;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.Fragmenter;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleHTMLEncoder;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.search.highlight.SimpleSpanFragmenter;
import org.apache.lucene.store.FSDirectory;

import com.searchhub.utilities.Utilities;

public class SearchFiles {
	  
		public SearchFiles() {
			
			while (true) {
				Scanner scanner = new Scanner(System.in);
				System.out.println("Enter Search Query::>>>>>>>>>> :: ");
				String searchQuery = scanner.nextLine();

				if(searchQuery.equalsIgnoreCase("exit")) {
					System.exit(0);
				}
				searchText(searchQuery);
				
				
			}
			
			
		}

		private void searchText(String searchQuery) {
			try {
				Path indexPath = Paths.get( Utilities.INSTANCE.get("INDEX_PATH"));
				
				
				
				Analyzer analyzer = new StandardAnalyzer();
				
				
				DirectoryReader reader = DirectoryReader.open( FSDirectory.open(indexPath));
				
				IndexSearcher searcher = new IndexSearcher(reader);
				
				QueryParser parser = new QueryParser("content", analyzer);
				
				Query query = parser.parse(searchQuery);
				
				
				TopDocs results = searcher.search(query , 10);
				
				System.out.println("total results are: "+ results.totalHits);
				StoredFields storedFields = searcher.storedFields();
				
				for (ScoreDoc scoreDoc : results.scoreDocs) {
					
					Document hit = storedFields.document(scoreDoc.doc);
					String content = hit.get("content");
							
					System.out.println("========");
					System.out.println("path is:::::: " + hit.get("path"));
					System.out.println("filename is:::::: " + hit.get("filename"));
					
					
//					System.out.println("content is:::::: " + hit.get("content"));
					
					
					String text = getHighlightedText(analyzer ,query , "content" , content );
					System.out.println(text);
					System.out.println("========");
//					System.out.println("Score: " + scoreDoc.score);
					
				}
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		private String getHighlightedText(Analyzer analyzer, Query query, String filedName, String text) throws Exception {
			
			QueryScorer scorer = new QueryScorer(query , filedName);
			
			SimpleHTMLFormatter htmlFormatter = new SimpleHTMLFormatter("[[", "]]");
			
			Highlighter highlighter = new Highlighter(htmlFormatter, scorer);
			
			Fragmenter fragmenter = new  SimpleSpanFragmenter(scorer, 150);
			
			highlighter.setTextFragmenter(fragmenter);;
			
			String highlightedText =  highlighter.getBestFragment(analyzer, filedName, text);
			
			if(highlightedText == null) {
				return text.length() > 200 
						? text.substring(0, 200) + "..."
						: text ;
			}
			return highlightedText ;
			
			
		}

		public static void main(String[] args) {
			
			new SearchFiles();
			
		}
		
		
}
