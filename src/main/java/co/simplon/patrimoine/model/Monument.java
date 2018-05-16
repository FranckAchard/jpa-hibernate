package co.simplon.patrimoine.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="MONUMENTS")

public class Monument {
	@Id
	@Column(name="ID")
	//@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "monument_generator")
	@SequenceGenerator(name="monument_generator", allocationSize=1)
	private Long id;
	
	@Column(name="NAME", nullable=false, length=255)
    private String name;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_city")
    //@Transient
	private City city;
	
	@ManyToMany(mappedBy= "monuments")
	private Set<User> users= new HashSet<User>();

	public Monument() {
		// TODO Auto-generated constructor stub
	}

	public Monument(String name) {
		this(null, name, null);
	}

	public Monument(String name, City city) {
		this(null, name, city);
	}
	
	public Monument(Long id, String name, City city) {
		super();
		this.id = id;
		this.name = name;
		this.city = city;
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

	public void setName(String name) {
		this.name = name;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "Monument [id=" + id + ", name=" + name + ", city=" + city + "]";
	}

}
