package principal;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import impl.SemanticCrawlerImpl;

public class Main {
	
	public static final String resourceURI = "http://dbpedia.org/data/Roger_Federer.rdf";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Model model = ModelFactory.createDefaultModel();
		model.read(resourceURI);
		
		SemanticCrawlerImpl lerRDF = new SemanticCrawlerImpl();
		lerRDF.search(model, resourceURI);
	}

}
