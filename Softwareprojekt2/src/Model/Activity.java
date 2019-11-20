package Model;

import java.io.Serializable;
import java.lang.String;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Activity
 *
 */
@Entity(name="TBL_ACTIVITY")

public class Activity implements Serializable {

	@Column(name="Name", nullable = false)
	private String name;
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="Projekt", nullable=false)
	private Projekt projekt;
	@Column(name="Datum", nullable=false) 
	@Temporal(TemporalType.TIMESTAMP)
	 private Date date;
	@Id
	@Column(name="Activitynummer")
	@GeneratedValue
	private Long aktNr;
	private static final long serialVersionUID = 1L;
	
	public Activity(String name, Projekt projekt, Date date) {
		super();
		this.name=name;
		this.projekt=projekt;
		this.date=date;
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

	public void setDate(Date date) {
		this.date = date;
	}

	public Long getAktNr() {
		return aktNr;
	}

	public void setAktNr(Long aktNr) {
		this.aktNr = aktNr;
	}
   
}
