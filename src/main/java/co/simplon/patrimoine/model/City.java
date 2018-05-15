package co.simplon.patrimoine.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="CITIES")

public class City {
	@Id
	@Column(name="ID")
	//@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "city_generator")
	@SequenceGenerator(name="city_generator", allocationSize=1)
	private Long id;
	
	@Column(name="NAME", nullable=false, length=255)
	private String name;
	
	@Column(name="LATITUDE", nullable=false)
	private Double latitude;
	
	@Column(name="LONGITUDE", nullable=false)
	private Double longitude;
	
	@OneToMany(mappedBy = "fk_city")	
	private List<Monument> monuments = new ArrayList<Monument>();

	public City() {
	}
	
	public City(String name, double latitude, double longitude) {
		this(null, name, latitude, longitude);
	}
	public City(Long id, String name, double latitude, double longitude) {
		this.id= id;
		this.name= name;
		this.latitude= latitude;
		this.longitude= longitude;
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String nom) {
		this.name = nom;
	}

	public Double getLongitude() {
		return this.longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return this.latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	
	public List<Monument> getMonuments() {
		return monuments;
	}

	public void setMonuments(List<Monument> monuments) {
		this.monuments = monuments;
	}

	@Override
	public String toString() {
		return "City [id=" + id + ", name=" + name + ", latitude=" + latitude + ", longitude=" + longitude
				+ ", monuments=" + monuments + "]";
	}

}