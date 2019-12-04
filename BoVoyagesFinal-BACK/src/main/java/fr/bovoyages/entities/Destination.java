package fr.bovoyages.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "destinations")
@NamedQueries({
		@NamedQuery(name = "Destination.getAllDestinations", query = "SELECT d FROM Destination d order by d.region asc"),
		@NamedQuery(name = "Destination.getDates", query = "SELECT d.dates FROM Destination d where d like:d")
})
public class Destination implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pk_destination")
	private long id;
	@Column(name = "region")
	private String region;
	@Column(name = "description")
	private String description;
	@Column(name = "deleted")
	private int deleted;
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true,fetch=FetchType.LAZY)
	@JoinColumn(name="fk_destination")
	private List<DatesVoyage> dates=new ArrayList<DatesVoyage>();
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true,fetch=FetchType.LAZY)
	@JoinColumn(name="fk_destination")
private List<Image> images=new ArrayList<Image>();
	
	public Destination() {
	}

	public Destination(String region, String description) {
		this.region = region;
		this.description = description;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getDeleted() {
		return deleted;
	}

	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}


	public List<DatesVoyage> getDates() {
		return dates;
	}

	public void setDates(List<DatesVoyage> dates) {
		this.dates = dates;
	}

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}


}
