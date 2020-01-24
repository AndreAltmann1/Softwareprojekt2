package Model;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import javax.persistence.*;

//Erzeugt Tabelle Für Kunden in der Datenbank
@Entity(name = "TBL_CUSTOMER")

public class Customer implements Serializable {

	@Column(name = "Name", nullable = false)
	private String name;
	@Column(name = "Vorname", nullable = false)
	private String vorname;
	@Column(name = "Firma")
	private String firma;
	@Column(name = "Plz", nullable = false)
	private String plz;
	@Column(name = "Ort", nullable = false)
	private String ort;
	@Column(name = "Anschrift", nullable = false)
	private String anschrift;
	@Column(name = "Telefon")
	private String telefon;
	@Id
	@Column(name = "Kundennummer")
	@GeneratedValue
	private Long kuNr;
	private static final long serialVersionUID = 1L;

	public Customer(String name, String vorname, String firma, String plz, String ort, String anschrift,
			String telefon) {
		super();
		this.name = name;
		this.vorname = vorname;
		this.firma = firma;
		this.plz = plz;
		this.ort = ort;
		this.anschrift = anschrift;
		this.telefon = telefon;
	}

	public Customer() {
		super();
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVorname() {
		return this.vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public String getFirma() {
		return this.firma;
	}

	public void setFirma(String firma) {
		this.firma = firma;
	}

	public String getPlz() {
		return this.plz;
	}

	public void setPlz(String plz) {
		this.plz = plz;
	}

	public String getOrt() {
		return this.ort;
	}

	public void setOrt(String ort) {
		this.ort = ort;
	}

	public String getAnschrift() {
		return this.anschrift;
	}

	public void setAnschrift(String anschrift) {
		this.anschrift = anschrift;
	}

	public String getTelefon() {
		return this.telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public Long getKuNr() {
		return this.kuNr;
	}

	public void setKuNr(Long kuNr) {
		this.kuNr = kuNr;
	}

	@Override
	public String toString() {
		return name + " " + vorname + " - " + firma;
	}

}
