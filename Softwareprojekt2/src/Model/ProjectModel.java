package Model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.eclipse.persistence.exceptions.DatabaseException;



public class ProjectModel {
//Gibt alle Projekte aus der Datenbank in einer Liste zurück
	public static List<Projekt> getAllProjects() throws DatabaseException {
		Query q = DBConnector.getEM().createQuery("SELECT g FROM TBL_PROJECT g");
		return q.getResultList();
	}

	public static Customer createProkect() {
		return new Customer();
	}
	
	//Hinzufügen eines Projekts in die Datenbanktabelle
	public static void addProject(Projekt newProject) throws DatabaseException {
		EntityManager em = DBConnector.getEM();
		em.getTransaction().begin();
		em.persist(newProject);
		em.flush();
		em.getTransaction().commit();
	}
//Löschen eines Projekts aus der Datenbanktabelle
	public static void deleteProject(Projekt entry) throws DatabaseException {
		EntityManager em = DBConnector.getEM();
		em.getTransaction().begin();
		em.remove(entry);
		em.flush();
		em.getTransaction().commit();
	}
//Ändern eines Projekts in der Datenbank Tabelle
	public static void changeProject(Projekt entry, String name, Customer customer, String zweck)
			throws DatabaseException {
		EntityManager em = DBConnector.getEM();
		em.getTransaction().begin();
		entry.setName(name);
		entry.setCustomer(customer);
		entry.setZweck(zweck);

		em.getTransaction().commit();
	}
}
