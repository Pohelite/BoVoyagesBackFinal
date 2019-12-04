package fr.bovoyages.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "images")
public class Image {
	@Id
	@Column(name = "image")
	private String image;
	@Column(name = "fk_destination")
	private long fkDestination;
	public Image() {}
	public Image(String image,long fkDestination) {
		this.image=image;
		this.fkDestination=fkDestination;
	}
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public long getFkDestination() {
		return fkDestination;
	}

	public void setFkDestination(long fkDestination) {
		this.fkDestination = fkDestination;
	}


}