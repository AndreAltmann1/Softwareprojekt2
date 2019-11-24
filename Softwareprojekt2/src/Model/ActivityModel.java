package Model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.eclipse.persistence.exceptions.DatabaseException;

public class ActivityModel {
	public static List<Activity> getAllActivities() throws DatabaseException {
		Query q = DBConnector.getEM().createQuery("SELECT a FROM TBL_ACTIVITY a");
		return q.getResultList();
	}

	public static Activity createActivity() {
		return new Activity();
	}

	public static void addActivity(Activity newActivity) throws DatabaseException{
		EntityManager em = DBConnector.getEM();
		em.getTransaction().begin();
		em.persist(newActivity);
		em.flush();
		em.getTransaction().commit();
	}

	public static void deleteActivity(Activity entry) throws DatabaseException {
		EntityManager em = DBConnector.getEM();
		em.getTransaction().begin();
		em.remove(entry);
		em.flush();
		em.getTransaction().commit();
	}

	public static void changeActivity(Activity entry, String name, Projekt projekt, int zeit) throws DatabaseException{
		EntityManager em = DBConnector.getEM();
		em.getTransaction().begin();
		entry.setName(name);
		entry.setProjekt(projekt);
		entry.setZeit(zeit);
		
		
		em.getTransaction().commit();
	}
}
