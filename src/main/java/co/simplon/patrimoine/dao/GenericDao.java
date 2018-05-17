package co.simplon.patrimoine.dao;

import javax.persistence.EntityManager;

import co.simplon.patrimoine.model.City;

public abstract class GenericDao<T> implements IGenericDao<T>{
	// attributs
	// type de classe de l'instance manipulée (final car elle ne doit pas changer après l'init)
	private final Class<T> classOfInstance;
	// entity manager pour les méthodes de manipulation de données
	private EntityManager entityMgr;
	
	// constructeur
	public GenericDao(Class<T> classOfInstance, EntityManager entityMgr) {
		this.classOfInstance = classOfInstance;
		this.entityMgr = entityMgr;
	}
	
	// création d'enregistrement
	public T create(T instance) {
		// début transaction
		entityMgr.getTransaction().begin();
		// l'instance est managée et persistée
		entityMgr.persist(instance);
		// commit de la transaction
		entityMgr.getTransaction().commit();
		return instance;
	}
	
	// récupération d'enregistrement par identifiant
	public T getById(Long id) {
		return entityMgr.find(classOfInstance, id);
	}
	
	// mise à jour d'enregistrement
	public T update(T instance) {
		/*
	    EntityManager em= factory.createEntityManager();
	    em.getTransaction().begin();
	    city = em.merge(city);
	    em.getTransaction().commit();
	    em.close();
	    return city;
	    */
		
	}
	
	// suppression d'enregistement
	public void deleteById(Long id) {
		
	}

}

