import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;

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
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME
				,configOverrides);
	}


	public void close() throws Exception {
		// TODO Auto-generated method stub
		factory.close();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
