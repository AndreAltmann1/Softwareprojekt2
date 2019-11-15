package Model;

import java.io.Serializable;
import java.lang.Boolean;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Projekt
 *
 */
@Entity
@Table(name="tbl_Projekt")

public class Projekt implements Serializable {

	@Column(name="Name", nullable = false)
	private String name;
	@ManyToOne
	@JoinColumn(name="Kunde", nullable = false)
	private Customer customer;
	
	
	@Column(name="isPrivate", nullable = false)
	private Boolean isPrivate;
	
	@Id
	@Column(name="Projektnummer")
	@GeneratedValue
	private Long prjNr;
	private static final long serialVersionUID = 1L;

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
	public Boolean getIsPrivate() {
		return this.isPrivate;
	}

	public void setIsPrivate(Boolean isPrivate) {
		this.isPrivate = isPrivate;
	}
	
	public Long getPrjNr() {
		return this.prjNr;
	}

	public void setPrjNr(Long prjNr) {
		this.prjNr = prjNr;
	}
   
}
