package Model;



import java.sql.Connection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;



// Aufbauen der Verbindung und Nutzen der Datenbank


public class DBConnector {

	private static EntityManagerFactory EMfactory;
	private static EntityManager em;
	private static final String PERSISTENCE_UNIT_NAME = "ProjektDB";

	private DBConnector() {
	}
//Gibt Entetymanager zurück
	public static EntityManager getEM() {
		
		
		if (em == null) {
			EMfactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
			em = EMfactory.createEntityManager();
		}
		return em;
	}
	
	
	
}