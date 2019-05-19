package iskanje;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;



import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;

import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;


import projekt.Knjiga;

public class Iskanje {
	
	
	public static List<Integer> isci(List <Knjiga> knjige,String iscem,String cat) throws IOException, ParseException {
		
		
		List<Integer> koncna = new ArrayList<Integer>();
		
		StandardAnalyzer analyzer = new StandardAnalyzer();

		
		Directory index = new RAMDirectory();

		IndexWriterConfig config = new IndexWriterConfig( analyzer);

		IndexWriter w = new IndexWriter(index, config);
		for (Knjiga k:knjige) {
			String id=k.getId()+"";
			Document doc = new Document();
			doc.add(new TextField("id", id, Field.Store.YES));
			doc.add(new TextField("naslov", k.getNaslov(), Field.Store.YES));
			doc.add(new TextField("avtor", k.getAvtor(), Field.Store.YES));
			doc.add(new TextField("vrsta", k.getVrsta(), Field.Store.YES));
			
			w.addDocument(doc);
		}
		
	
		w.close();

		
		Query q = null;
		try {
			q = new QueryParser( cat, analyzer).parse(iscem);
		} catch (org.apache.lucene.queryparser.classic.ParseException e) {
			e.printStackTrace();
		}

		// 3. search
		int hitsPerPage = 10;
		IndexReader reader = DirectoryReader.open(index);
		IndexSearcher searcher = new IndexSearcher(reader);
		TopScoreDocCollector collector = TopScoreDocCollector.create(hitsPerPage);
		searcher.search(q, collector);
		ScoreDoc[] hits = collector.topDocs().scoreDocs;

		// 4. display results
		
		System.out.println("Found " + hits.length + " hits.");
		for (int i = 0; i < hits.length; ++i) {
			int docId = hits[i].doc;
			Document d = searcher.doc(docId);
			int idIsci=Integer.parseInt(d.get("id"));
			System.out.println("ID "+idIsci);
			
			koncna.add(idIsci);
		}

		// reader can only be closed when there
		// is no need to access the documents any more.
		reader.close();
		return koncna;
	}

	/*private  void addDoc(IndexWriter w, String id,String title,String avtor,String vrsta) throws IOException {
		Document doc = new Document();
		doc.add(new TextField("id", title, Field.Store.YES));
		doc.add(new TextField("naslov", title, Field.Store.YES));
		doc.add(new TextField("avtor", avtor, Field.Store.YES));
		doc.add(new TextField("vrsta", vrsta, Field.Store.YES));
		
		w.addDocument(doc);
	}*/
}
