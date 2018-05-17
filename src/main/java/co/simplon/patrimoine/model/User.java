package co.simplon.patrimoine.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name= "USERS")

public class User {
	
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_generator")
	@SequenceGenerator(name="user_generator", allocationSize=1)
	private Long id;
	
	@Column(name= "NAME", nullable= false, length= 100)
	private String name;
	
	@ManyToMany
	@JoinTable(name= "USER_MONUMENT",
	joinColumns= {@JoinColumn(name= "FK_USER", referencedColumnName= "ID")},
	inverseJoinColumns= {@JoinColumn(name= "FK_MONUMENT", referencedColumnName= "ID")} )
	private Set<Monument> monuments= new HashSet<Monument>();
	
	public User() {
		// TODO Auto-generated constructor stub
	}

	public User(Long id, String name, Set<Monument> monuments) {
		super();
		this.id = id;
		this.name = name;
		this.monuments = monuments;
	}
	
	public User(String name) {
		this(null, name, null);
	}

	public User(String name, Set<Monument> monuments) {
		this(null, name, monuments);
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

	public Set<Monument> getMonuments() {
		return monuments;
	}

	public void setMonuments(Set<Monument> monuments) {
		this.monuments = monuments;
	}
	
	// method for adding a Monument to monuments attribute
	public void addMonument(Monument monu) {
		// add a monument to monuments set
		monuments.add(monu);
		// add user to set of users of monument parameter
		monu.getUsers().add(this);
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", nb monuments=" + monuments.size() + "]";
	}

}
