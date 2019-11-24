package Model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.eclipse.persistence.exceptions.DatabaseException;

public class CustomerModel {

	private static ArrayList<Customer> customerList = new ArrayList<Customer>();

	public static List<Customer> getAllCustomer() throws DatabaseException {
		Query q = DBConnector.getEM().createQuery("select t from TBL_CUSTOMER t");
		return q.getResultList();
	}

	public static Customer createCustomer() {
		return new Customer();
	}

	public static void addCustomer(Customer newCustomer) throws DatabaseException{
		EntityManager em = DBConnector.getEM();
		em.getTransaction().begin();
		em.persist(newCustomer);
		em.flush();
		em.getTransaction().commit();
	}

	public static void deleteCustomer(Customer entry) throws DatabaseException {
		EntityManager em = DBConnector.getEM();
		em.getTransaction().begin();
		em.remove(entry);
		em.flush();
		em.getTransaction().commit();
	}

	public static void changeCustomer(Customer entry, String name, String vorname, String firma, String plz, String ort,
			String anschrift, String telefon) throws DatabaseException{
		EntityManager em = DBConnector.getEM();
		em.getTransaction().begin();
		entry.setName(name);
		entry.setVorname(vorname);
		entry.setFirma(firma);
		entry.setPlz(plz);
		entry.setOrt(ort);
		entry.setAnschrift(anschrift);
		entry.setTelefon(telefon);
		em.getTransaction().commit();
	}

}
