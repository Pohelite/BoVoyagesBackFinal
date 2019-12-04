package fr.bovoyages.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="commerciaux")
public class Commercial {
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
@Column(name="pk_commercial")
private long id;
@Column(name="username")
private String username;

public Commercial() {}

public Commercial(long id, String username) {
	super();
	this.id = id;
	this.username = username;
}


public Commercial(String username) {
	super();
	this.username = username;
}

public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + (int) (id ^ (id >>> 32));
	result = prime * result + ((username == null) ? 0 : username.hashCode());
	return result;
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Commercial other = (Commercial) obj;
	if (id != other.id)
		return false;
	if (username == null) {
		if (other.username != null)
			return false;
	} else if (!username.equals(other.username))
		return false;
	return true;
}


}
