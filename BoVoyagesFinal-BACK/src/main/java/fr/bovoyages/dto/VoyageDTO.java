package fr.bovoyages.dto;

import java.util.ArrayList;
import java.util.List;

import fr.bovoyages.entities.DatesVoyage;
import fr.bovoyages.entities.Payeur;
import fr.bovoyages.entities.Voyage;
import fr.bovoyages.entities.Voyageur;

public class VoyageDTO {
private long id;
private String region;
private String descriptif;
private float prixTotal;
private Payeur client;
private DatesVoyage date;
private List<Voyageur> participants=new ArrayList<Voyageur>();

public VoyageDTO() {}

public VoyageDTO(long id, String region, String descriptif, float prixTotal, Payeur client, DatesVoyage date,
		List<Voyageur> participants) {
	super();
	this.id = id;
	this.region = region;
	this.descriptif = descriptif;
	this.prixTotal=prixTotal;
	this.client = client;
	this.date = date;
	this.participants = participants;
}
public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
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

public Payeur getClient() {
	return client;
}

public void setClient(Payeur client) {
	this.client = client;
}

public DatesVoyage getDate() {
	return date;
}

public void setDate(DatesVoyage date) {
	this.date = date;
}

public float getPrixTotal() {
	return prixTotal;
}

public void setPrixTotal(float prixTotal) {
	this.prixTotal = prixTotal;
}


}
