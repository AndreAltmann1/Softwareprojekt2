package Model;

import java.io.Serializable;
import java.lang.String;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.*;

/* Erzeugt Datenbank Tabelle für Aktivitäten
 * 
 */

@Entity(name = "TBL_ACTIVITY")

public class Activity implements Serializable {

	@Column(name = "Name", nullable = false)
	private String name;
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "prjNr", nullable = false)
	private Projekt projekt;

	@Column(name = "Datum", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date date;
	@Column(name = "Zeit")
	private int zeit;
	@Id
	@Column(name = "Activitynummer")
	@GeneratedValue
	private Long aktNr;
	private static final long serialVersionUID = 1L;

	public Activity(String name, Projekt projekt, Date date, int zeit) {
		super();
		this.name = name;
		this.projekt = projekt;

		this.date = date;
		this.zeit = zeit;
	}

	public Activity() {
		super();
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Projekt getProjekt() {
		return projekt;
	}

	public void setProjekt(Projekt projekt) {
		this.projekt = projekt;
	}

	public Date getDate() {

		return date;
	}

	public String getStringDate() {
		SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
		return format.format(date);
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Long getAktNr() {
		return aktNr;
	}

	public void setAktNr(Long aktNr) {
		this.aktNr = aktNr;
	}

	public int getZeit() {
		return zeit;
	}

	public void setZeit(int zeit) {
		this.zeit = zeit;
	}

	@Override
	public String toString() {
		return name + " " + projekt + " " + date + " " + zeit;
	}

}
