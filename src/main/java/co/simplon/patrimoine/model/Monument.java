package co.simplon.patrimoine.model;

import javax.persistence.*;

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
    private City city;

	public Monument() {
		// TODO Auto-generated constructor stub
	}

	public Monument(String name, City city) {
		this(null, name, city);
	}
	
	public Monument(Long id, String name, City city) {
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
