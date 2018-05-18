package co.simplon.patrimoine.dao;

import javax.persistence.EntityManager;

public abstract class GenericDAO<T> implements IGenericDAO<T>{
	// type de classe de l'instance manipulée (final car elle ne doit pas changer après l'init)
	private final Class<T> classOfInstance;
	// entity manager pour les méthodes de manipulation de données
	private EntityManager entityMgr;
	
	// constructeur
	public GenericDAO(Class<T> classOfInstance, EntityManager entityMgr) {
		this.classOfInstance = classOfInstance;
		this.entityMgr = entityMgr;
	}
	
	// création d'enregistrement
	public T create(T instance) {
		// début de transaction
		entityMgr.getTransaction().begin();
		// l'instance est managée et persistée
		entityMgr.persist(instance);
		// commit de la transaction
		entityMgr.getTransaction().commit();
		return instance;
	}
	
	// récupération d'enregistrement par identifiant
	public T getById(Long id) {
		return entityMgr.find(this.classOfInstance, id);
	}
	
	// mise à jour d'enregistrement
	public T update(T instance) {
		// début de transaction
		entityMgr.getTransaction().begin();
		// l'instance est persistée + on récupère une instance managée
		instance= entityMgr.merge(instance);
		// commit de la transaction
		entityMgr.getTransaction().commit();
		return instance;
	}
	
	// suppression d'enregistement
	public void deleteById(Long id) {
		// début de transaction
		entityMgr.getTransaction().begin();
		// suppression de l'entité
		entityMgr.remove(this.getById(id));
		// commit de la transaction
		entityMgr.getTransaction().commit();
	}

}

