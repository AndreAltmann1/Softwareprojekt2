package Model;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.eclipse.persistence.exceptions.DatabaseException;

public class ActivityModel {

//Ausgeben einer Liste mit allen Aktivit�ten aus der Datenbank
	public static List<Activity> getAllActivities() throws DatabaseException {
		Query q = DBConnector.getEM().createQuery("SELECT a FROM TBL_ACTIVITY a");
		return q.getResultList();
	}

	public static Activity createActivity() {
		return new Activity();
	}

// Hinzuf�gen einer Aktivit�t in die Datenbank Tabelle
	public static void addActivity(Activity newActivity) throws DatabaseException {
		EntityManager em = DBConnector.getEM();
		em.getTransaction().begin();
		em.persist(newActivity);
		em.flush();
		em.getTransaction().commit();
	}

//L�schen einer Aktivit�t aus der Datenbank Tabelle
	public static void deleteActivity(Activity entry) throws DatabaseException {
		EntityManager em = DBConnector.getEM();
		em.getTransaction().begin();
		em.remove(entry);
		em.flush();
		em.getTransaction().commit();
	}

//�ndern einer Aktivit�t in der Datenbank Tabelle
	public static void changeActivity(Activity entry, String name, Projekt projekt, Date date, int zeit)
			throws DatabaseException {

		EntityManager em = DBConnector.getEM();
		em.getTransaction().begin();
		entry.setName(name);
		entry.setProjekt(projekt);
		entry.setDate(date);
		entry.setZeit(zeit);

		em.getTransaction().commit();
	}
}
