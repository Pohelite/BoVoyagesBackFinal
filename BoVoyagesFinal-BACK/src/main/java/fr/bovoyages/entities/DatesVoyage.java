package fr.bovoyages.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="dates_voyages")
@NamedQueries({
	//@NamedQuery(name = "DatesDeVoyage.getAllDatesVoyage",query = "SELECT v FROM DateDeVoyage v")
})

public class DatesVoyage implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="pk_date_voyage")
	private long id;
	@Column(name="date_depart")
	private Date dateAller;
	@Column(name="date_retour")
	private Date dateRetour;
	@Column(name="prixHT")
	private float prixHT;
	@Column(name="nb_places")
	private int nbrePlaces;
	@Column(name="deleted")
	private int deleted;
	@Column(name="promo")
	private float promo;
	
	public DatesVoyage() {}
	public DatesVoyage(Date dateAller, Date dateRetour, float prixHT, int nbrePlaces, int deleted, float promo) {
		super();
		this.dateAller = dateAller;
		this.dateRetour = dateRetour;
		this.prixHT = prixHT;
		this.nbrePlaces = nbrePlaces;
		this.deleted = deleted;
		this.promo = promo;
	}
	
	public DatesVoyage(long id, Date dateAller, Date dateRetour, float prixHT, int nbrePlaces, int deleted,
			float promo) {
		super();
		this.id = id;
		this.dateAller = dateAller;
		this.dateRetour = dateRetour;
		this.prixHT = prixHT;
		this.nbrePlaces = nbrePlaces;
		this.deleted = deleted;
		this.promo = promo;
	}
	public float getPromo() {
		return promo;
	}
	public void setPromo(float promo) {
		this.promo = promo;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Date getDateAller() {
		return dateAller;
	}
	public void setDateAller(Date dateAller) {
		this.dateAller = dateAller;
	}
	public Date getDateRetour() {
		return dateRetour;
	}
	public void setDateRetour(Date dateRetour) {
		this.dateRetour = dateRetour;
	}
	public float getPrixHT() {
		return prixHT;
	}
	public void setPrixHT(float prixHT) {
		this.prixHT = prixHT;
	}
	public int getNbrePlaces() {
		return nbrePlaces;
	}
	public void setNbrePlaces(int nbrePlaces) {
		this.nbrePlaces = nbrePlaces;
	}
	
	public int getDeleted() {
		return deleted;
	}
	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}
	@Override
	public String toString() {
		return "DatesVoyage [id=" + id + ", dateAller=" + dateAller + ", dateRetour=" + dateRetour + ", prixHT="
				+ prixHT + ", nbrePlaces=" + nbrePlaces + ", deleted=" + deleted + ", promo=" + promo + "]";
	}

}
