package verificador;

import java.util.ArrayList;

import encoder.Encoder;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.vocabulary.OWL;

public class LerRDF extends Encoder { 
		ArrayList<String> visited_uris;
		
		public LerRDF() {
			this.visited_uris = new ArrayList<String>();
		}

		public void verifica(StmtIterator statements) {
			hasNextStatement(statements);
		}
		
		private void hasNextStatement(StmtIterator statements) {
			while(statements.hasNext()) {
				Statement statement = statements.nextStatement();
				
				getSubject(statement);
				
				getPredicate(statement);
				
				getObject(statement);
				
				System.out.println();
				
				Resource object = (Resource) statement.getObject();
					
				entraObjeto(object, visited_uris);
			}
		}
		
		private void getSubject(Statement statement) {
			Resource subject = statement.getSubject();
			
			if (subject.isAnon()) {
				System.out.print(" ("+ subject.getId() +") ");
			} else {
				System.out.print(" ("+ subject.getURI() +") ");
			}
		}
		
		private void getPredicate(Statement statement) {
			Property predicate = statement.getPredicate();
			System.out.print("("+ predicate.getURI() +")");
		}
		
		private void getObject(Statement statement) {
			Resource object = (Resource) (statement.getObject());

			if (object.isAnon()) {
				System.out.print(" ("+ object +") ");
			} else if (object.isLiteral()) {
				System.out.print(" ("+ object.toString() +") ");
			} else if (object.isResource()) {
				System.out.print(" ("+ object.getURI() +") ");
			}
		}
		
		private void entraObjeto(RDFNode statement, ArrayList<String> visited_uris) {
			String uri = statement.asResource().getURI();
			
			Encoder enc = new Encoder();
			if (enc.charsetEncoder(uri)) {
				if (visited_uris.contains(uri)) {
					return;
				} else {
					if(statement.isResource()) {
						visited_uris.add(uri);
						
						try {
							Model model = ModelFactory.createDefaultModel();
                            model.read(uri);
                            
                            StmtIterator statement_novo = model.listStatements((Resource) statement, OWL.sameAs, (RDFNode) null);
                            
                            while(statement_novo.hasNext()) {
                            	System.out.println();
                                
                            	statementNovo(statement_novo, visited_uris);
                            }
                            
						} catch (Exception e) {
							return;
						}
					}
				}
			}
		}
		
		private void statementNovo(StmtIterator statement_novo, ArrayList<String> visited_uris) {
			Statement statement = statement_novo.nextStatement();
			
			getSubject(statement);
			
			getPredicate(statement);
			
			getObject(statement);
			
			Resource object = (Resource) statement.getObject();
			Resource subject = statement.getSubject();
			
			entraObjeto(object, visited_uris);
			entraObjeto(subject, visited_uris);
		}
}
