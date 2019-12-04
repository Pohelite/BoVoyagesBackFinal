package fr.bovoyages.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="clients")
public class Payeur implements Serializable{
@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name="pk_client")
private long id;
@Column(name="nom")
private String nom;
@Column(name="mail")
private String mail;


public Payeur() {}
public Payeur(String nom) {
	super();
	this.nom = nom;
}

public Payeur(long id, String nom) {
	super();
	this.id = id;
	this.nom = nom;
}
public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
}
public String getNom() {
	return nom;
}
public void setNom(String nom) {
	this.nom = nom;
}
public String getMail() {
	return mail;
}
public void setMail(String mail) {
	this.mail = mail;
}


}
