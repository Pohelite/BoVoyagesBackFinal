package fr.bovoyages.dto;

import java.io.Serializable;
import java.util.Date;

import fr.bovoyages.entities.DatesVoyage;

public class DatesVoyageDTO implements Serializable {
	private long id;
	private Date dateAller;
	private Date dateRetour;
	private double prixHT;
	private float promo;
	private int deleted;
	private int nbrePlaces;
	
	
	public DatesVoyageDTO() {}


	@Override
	public String toString() {
		return "DatesVoyageDTO [id=" + id + ", dateAller=" + dateAller + ", dateRetour=" + dateRetour + ", prixHT="
				+ prixHT + ", promo=" + promo + ", deleted=" + deleted + ", nbrePlaces=" + nbrePlaces + "]";
	}


	public DatesVoyageDTO(Date dateAller, Date dateRetour, double prixHT, float promo, int deleted, int nbrePlaces) {
		super();
		this.dateAller = dateAller;
		this.dateRetour = dateRetour;
		this.prixHT = prixHT;
		this.promo = promo;
		this.deleted = deleted;
		this.nbrePlaces = nbrePlaces;
	}


	public DatesVoyageDTO(DatesVoyage dates) {
		super();
		this.id = dates.getId();
		this.dateAller = dates.getDateAller();
		this.dateRetour = dates.getDateRetour();
		this.prixHT = dates.getPrixHT();
		this.promo = dates.getPromo();
		this.deleted = dates.getDeleted();
		this.nbrePlaces = dates.getNbrePlaces();
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


	public double getPrixHT() {
		return prixHT;
	}


	public void setPrixHT(double prixHT) {
		this.prixHT = prixHT;
	}


	public float getPromo() {
		return promo;
	}


	public void setPromo(float promo) {
		this.promo = promo;
	}


	public int getDeleted() {
		return deleted;
	}


	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}


	public int getNbrePlaces() {
		return nbrePlaces;
	}


	public void setNbrePlaces(int nbrePlaces) {
		this.nbrePlaces = nbrePlaces;
	}

	


	
}
