package fr.bovoyages.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="voyages")
public class Voyage {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pk_voyage")
	private long id;
	@Column(name = "region")
	private String region;
	@Column(name = "descriptif")
	private String descriptif;
	@Column(name="prix_total")
	private float prixTotal;
	@JoinColumn(name = "fk_client")
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private Payeur client;
	@JoinColumn(name="fk_dates_voyage")
	@OneToOne
	private DatesVoyage dateVoyage;
	@OneToMany(fetch=FetchType.EAGER)
	@JoinTable(name="voyages_voyageurs",
	joinColumns = @JoinColumn(name="fk_voyage"),
	inverseJoinColumns = @JoinColumn(name="fk_voyageur"))
	private List<Voyageur> participants=new ArrayList<Voyageur>();

	public Voyage() {}
	public Voyage(String region, String descriptif, List<Voyageur> participants) {
		super();
		this.region = region;
		this.descriptif = descriptif;
		this.participants = participants;
	}
	public Voyage(long id, String region, String descriptif, float prixTotal, Payeur client, DatesVoyage dateVoyage,
			List<Voyageur> participants) {
		super();
		this.id = id;
		this.region = region;
		this.descriptif = descriptif;
		this.prixTotal=prixTotal;
		this.client = client;
		this.dateVoyage = dateVoyage;
		this.participants = participants;
	}
	public Voyage(long id, String region, String descriptif, Payeur client, DatesVoyage dateVoyage,
			List<Voyageur> participants) {
		super();
		this.id = id;
		this.region = region;
		this.descriptif = descriptif;
		this.prixTotal=dateVoyage.getPrixHT()*participants.size();
		this.client = client;
		this.dateVoyage = dateVoyage;
		this.participants = participants;
	}
	public String getRegion() {
		return region;
	}


	public void setRegion(String region) {
		this.region = region;
	}


	public String getDescriptif() {
		return descriptif;
	}


	public void setDescriptif(String descriptif) {
		this.descriptif = descriptif;
	}



	public List<Voyageur> getParticipants() {
		return participants;
	}


	public void setParticipants(List<Voyageur> participants) {
		this.participants = participants;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}
	public Payeur getClient() {
		return client;
	}
	public void setClient(Payeur client) {
		this.client = client;
	}
	public DatesVoyage getDateVoyage() {
		return dateVoyage;
	}
	public void setDateVoyage(DatesVoyage dateVoyage) {
		this.dateVoyage = dateVoyage;
	}
	@Override
	public String toString() {
		return "Voyage [id=" + id + ", region=" + region + ", descriptif=" + descriptif + ", client=" + client
				+ ", dateVoyage=" + dateVoyage + ", participants=" + participants + "]";
	}
	public float getPrixTotal() {
		return prixTotal;
	}
	public void setPrixTotal(float prixTotal) {
		this.prixTotal = prixTotal;
	}




}
