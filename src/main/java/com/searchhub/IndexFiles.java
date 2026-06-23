package com.searchhub;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.FSDirectory;

import com.searchhub.utilities.Utilities;

public class IndexFiles {

	Path docsPath =  Path.of( Utilities.INSTANCE.get("BOOKS_PATH"));
	
	Path indexPath = Paths.get( Utilities.INSTANCE.get("INDEX_PATH"));
	
	
	
	public IndexFiles() {
		
		
		
		Analyzer analyzer = new StandardAnalyzer();
		
		
		try {
			
			FSDirectory indexFolder = FSDirectory.open(indexPath);
			
			IndexWriterConfig config = new IndexWriterConfig(analyzer);
			
			config.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
			
			IndexWriter indexWriter = new IndexWriter(indexFolder, config);
			
			indexDirectory(docsPath , indexWriter);
			
			indexWriter.close();
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}


	private void indexDirectory(Path path , IndexWriter writer) throws IOException {
		
		try(Stream<Path> paths  = Files.walk(path)) {
			
			paths.filter(Files::isRegularFile)
			     .forEach(file -> {
			    	 try {
						indexFiles(file , writer);
					 } catch (IOException e) {
						e.printStackTrace();
					 }
			     });
		}
		
		
		
	}
	
	
	private void indexFiles(Path file , IndexWriter writer) throws IOException {
		
		System.out.println("Reading: " + file);
		String fileName  = file.getFileName().toString().toLowerCase();
		
				
		if(!fileName.endsWith(".txt"))return;
			
		String content = Files.readString(file , StandardCharsets.UTF_8);
		
		Document doc = new Document();

		doc.add(new StringField("path", file.toAbsolutePath().toString() , Field.Store.YES));
		doc.add(new StringField("filename", file.getFileName().toString() , Field.Store.YES));
		doc.add(new TextField("content", content, Field.Store.YES));
		
		
		
		writer.addDocument(doc);
		
		
	}


	public static void main(String[] args) {
		
		new IndexFiles();
		
		
	}
}
