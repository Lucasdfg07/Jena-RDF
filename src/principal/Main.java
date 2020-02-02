package principal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import org.apache.jena.datatypes.xsd.XSDDatatype;
import org.apache.jena.rdf.model.InfModel;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.reasoner.Reasoner;
import org.apache.jena.reasoner.ReasonerRegistry;
import org.apache.jena.reasoner.ValidityReport;
import org.apache.jena.sparql.vocabulary.FOAF;
import org.apache.jena.util.FileManager;
import org.apache.jena.util.PrintUtil;
import org.apache.jena.vocabulary.OWL;
import org.apache.jena.vocabulary.RDFS;
import verificador.LerRDF;

public class Main {
	
	public static final String RDF_FILE = "http://dbpedia.org/data/Roger_Federer.rdf";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Model model = ModelFactory.createDefaultModel();
		model.read(RDF_FILE);
		
		StmtIterator statements = model.listStatements((Resource)null, OWL.sameAs, (RDFNode)null);
		
		LerRDF lerRDF = new LerRDF();
		lerRDF.verifica(statements);
	}

}
