package co.simplon.patrimoine.model;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DemoJPA implements AutoCloseable {
	private static final String PERSISTENCE_UNIT_NAME = "demo-jpa-1";
	private EntityManagerFactory factory;

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
	
	public Monument createMonument() {
		EntityManager em= factory.createEntityManager();
		Monument monument= new Monument("Pont romain", em.find(City.class, 1L));
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

	
	public City readCity() {
	    EntityManager em= factory.createEntityManager();
	    City city= read(em, 3L);
	    em.close();
	    return city;
	}
	
	public City read(EntityManager em, Long id) {
	    return em.find(City.class, id);
	}
	
	public City updateCity() {
	    return update(new City(4L,"PaRiS", -1., -2.));
	}
	
	public City update(City city) {
	    EntityManager em= factory.createEntityManager();
	    em.getTransaction().begin();
	    city = em.merge(city);
	    em.getTransaction().commit();
	    em.close();
	    return city;
	}

	public void deleteCity() {
		City cityTmp= createCity();
		System.out.println(cityTmp);
		delete(cityTmp);
		delete(11L);
	}

	public void delete(City city) {
		EntityManager em= factory.createEntityManager();
	    em.getTransaction().begin();
	    em.remove(em.merge(city));
	    em.getTransaction().commit();
	    em.close();
	}
	
	public void delete(Long id) {
		EntityManager em= factory.createEntityManager();
	    em.getTransaction().begin();
	    em.remove(em.find(City.class, id));
	    em.getTransaction().commit();
	    em.close();
	}

	public void close() throws Exception {
		// TODO Auto-generated method stub
		factory.close();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub		
		try (DemoJPA demoJpa = new DemoJPA()) {
			//City city1= demoJpa.createCity();
			//System.out.println(city1);
			//demoJpa.createCityAndUpdate();
			//System.out.println(demoJpa.readCity());
			//System.out.println(demoJpa.updateCity());
			//demoJpa.deleteCity();
			
			Monument monument1= demoJpa.createMonument();
			System.out.println(monument1);
			
		} catch (Exception e) {
			
		}
	}

}
