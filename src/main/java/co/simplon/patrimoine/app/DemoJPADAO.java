package co.simplon.patrimoine.app;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import co.simplon.patrimoine.model.*;
import co.simplon.patrimoine.dao.*;

public class DemoJPADAO implements AutoCloseable {
	private static final String PERSISTENCE_UNIT_NAME = "demo-jpa-1";
	private EntityManagerFactory factory;

	// constructor, to set EntityManagerFactory
	public DemoJPADAO() {
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

	// close factory
	public void close() throws Exception {
		// TODO Auto-generated method stub
		factory.close();
	}

	public static void main(String[] args) {
		try (DemoJPADAO demoJpaDao = new DemoJPADAO()) {
			EntityManager em= demoJpaDao.factory.createEntityManager();
			CityDAO cityDAO= new CityDAO(em); 
			City juvignac= new City("Juvignac", 43.611297, 3.81235700000002);
			juvignac= cityDAO.create(juvignac);
			System.out.println(juvignac);
			em.close();
		} catch (Exception e) {
			System.out.println("Y a un problème!!! " + e);
			e.printStackTrace();
		}

	}
}
