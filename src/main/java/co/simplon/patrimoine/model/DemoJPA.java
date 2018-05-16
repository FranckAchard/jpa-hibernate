package co.simplon.patrimoine.model;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DemoJPA implements AutoCloseable {
	private static final String PERSISTENCE_UNIT_NAME = "demo-jpa-1";
	private EntityManagerFactory factory;

	// constructor, to set EntityManagerFactory
	public DemoJPA() {
		Map<String, String> env = System.getenv();
		Map<String, Object> configOverrides = new HashMap<String, Object>();
		for (String envName : env.keySet()) {
			if (envName.contains("DB_USER")) {
				configOverrides.put("javax.persistence.jdbc.user", env.get(envName));
			}
			if (envName.contains("DB_PASS")) {
				configOverrides.put("javax.persistence.jdbc.password", env.get(envName));
			}
			if (envName.contains("DB_URL")) {
				configOverrides.put("javax.persistence.jdbc.url", env.get(envName));    
			}
		}
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME, configOverrides);
	}
	
	// create city
	public City createCity() {
		EntityManager em= factory.createEntityManager();
		City city= new City("Atlantis", 0, 0.5);
		city= create(em, city);
		em.close();
		return city;
	}
	
	public City create(EntityManager em, City city) {
		em.getTransaction().begin();
		//System.out.println("city avant persist : " + city);
		em.persist(city);
		//System.out.println("city apres persist : " + city);
		em.getTransaction().commit();
		return city;
	}

	public City createCityAndUpdate() {
		  EntityManager em= factory.createEntityManager();
		  City city= new City("Paris", 0, 0.5);
		  em.getTransaction().begin();
		  em.persist(city);
		  city.setLatitude(1000.);
		  em.getTransaction().commit();// MAGIC HAPPENS HERE !
		  em.close();
		  return city;
	}
	
	// create monument
	public Monument createMonument() {
		EntityManager em= factory.createEntityManager();
		City city1= em.find(City.class, 5L);
		Monument monument= new Monument("Pont romain", city1);
		monument= create(em, monument);
		em.close();
		return monument;
	}
	
	public Monument create(EntityManager em, Monument monument) {
		em.getTransaction().begin();
		em.persist(monument);
		em.getTransaction().commit();
		return monument;
	}
	
	// create user
	public User createUser() {
		EntityManager em= factory.createEntityManager();
		/* user:
		 * - id
		 * - name
		 * - hashset of monuments
		 */
		// set user
		
		City city1= new City("Vers-Pont-du-Gard", 43.9473, 4.5355);
		create(em, city1);
		
		City city2= new City("Groix", 47.63769, -3.46300);
		create(em, city2);
		
		Set<Monument> monuSet= new HashSet<Monument>();
		
		Monument monu1= new Monument("Pont du Gard", city1);
		monuSet.add(monu1);
		create(em, monu1);
		
		Monument monu2= new Monument("Fort", city2);
		monuSet.add(monu2);
		create(em, monu2);	
		
		User user= new User("Ahmed", monuSet);
		user= create(em, user);
		em.close();
		return user;
	}
	
	public User create(EntityManager em, User user) {
		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();
		return user;
	}
	
	// read city
	public City readCity() {
	    EntityManager em= factory.createEntityManager();
	    City city= em.find(City.class, 5L);
	    em.close();
	    return city;
	}
	
	// read monument
	public Monument readMonument() {
	    EntityManager em= factory.createEntityManager();
	    Monument monument= em.find(Monument.class, 5L);
	    em.close();
	    return monument;
	}	
	
	// update city
	public City updateCity() {
	    //return update(new City(4L,"PaRiS", -1., -2., null));
		return update(new City(10L,"PaRiS", -1.3, -2.));
	}
	
	public City update(City city) {
	    EntityManager em= factory.createEntityManager();
	    em.getTransaction().begin();
	    city = em.merge(city);
	    em.getTransaction().commit();
	    em.close();
	    return city;
	}

	// update monument
	public Monument updateMonument() {
		return update(new Monument(13L, "Tour Eiffel", new City(10L,"PaRiS", -1.3, -2.)));
	}
	
	public Monument update(Monument monu) {
	    EntityManager em= factory.createEntityManager();
	    em.getTransaction().begin();
	    monu = em.merge(monu);
	    em.getTransaction().commit();
	    em.close();
	    return monu;
	}
	
	// delete city
	public void deleteCity() {
		City cityTmp= createCity();
		System.out.println(cityTmp);
		deleteCityByObject(cityTmp);
		deleteCityById(11L);
	}

	public void deleteCityByObject(City city) {
		EntityManager em= factory.createEntityManager();
	    em.getTransaction().begin();
	    em.remove(em.merge(city));
	    em.getTransaction().commit();
	    em.close();
	}
	
	public void deleteCityById(Long id) {
		EntityManager em= factory.createEntityManager();
	    em.getTransaction().begin();
	    em.remove(em.find(City.class, id));
	    em.getTransaction().commit();
	    em.close();
	}

	// delete monument
	public void deleteMonument() {
		deleteMonumentById(8L);
	}
	
	public void deleteMonumentById(Long id) {
		EntityManager em= factory.createEntityManager();
	    em.getTransaction().begin();
	    em.remove(em.find(Monument.class, id));
	    em.getTransaction().commit();
	    em.close();
	}	
	
	// close factory
	public void close() throws Exception {
		// TODO Auto-generated method stub
		factory.close();
	}

	/******************************************************
	 ********************    main    **********************
	 ******************************************************/
	public static void main(String[] args) {
		// TODO Auto-generated method stub		
		try (DemoJPA demoJpa = new DemoJPA()) {
			//City city1= demoJpa.createCity();
			//System.out.println(city1);
			//demoJpa.createCityAndUpdate();
			//System.out.println(demoJpa.readCity());
			//System.out.println(demoJpa.updateCity());
			//demoJpa.deleteCity();
			System.out.println(demoJpa.readCity());
			
			//Monument monument1= demoJpa.createMonument();
			//System.out.println(monument1);
			//demoJpa.deleteMonument();
			//demoJpa.createUser();
			
		} catch (Exception e) {
			System.out.println("Y a un problème!!! " + e);
			e.printStackTrace();
		}
	}

}
