package Model;

import java.io.Serializable;
import java.lang.Boolean;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Projekt
 *
 */
@Entity(name="TBL_PROJECT")


public class Projekt implements Serializable {

	@Column(name="Name", nullable = false)
	private String name;
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="Kunde", nullable = false)
	private Customer customer;
	
	
	@Column(name="Zweck", nullable = false)
	private String zweck;
	
	@Id
	@Column(name="Projektnummer")
	@GeneratedValue
	private Long prjNr;
	private static final long serialVersionUID = 1L;
	
	
	public Projekt(String name, Customer customer, String zweck) {
		super();
		this.name=name;
		this.customer=customer;
		this.zweck=zweck;
	}
		
	public Projekt() {
		super();
	}   
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}   
	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}   
	public String getZweck() {
		return this.zweck;
	}

	public void setZweck(String zweck) {
		this.zweck = zweck;
	}
	
	public Long getPrjNr() {
		return this.prjNr;
	}

	public void setPrjNr(Long prjNr) {
		this.prjNr = prjNr;
	}
   
}
