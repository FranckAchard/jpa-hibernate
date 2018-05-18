package co.simplon.patrimoine.dao;

import javax.persistence.EntityManager;

import co.simplon.patrimoine.model.City;

public class CityDAO extends GenericDAO<City> {

	public CityDAO(EntityManager entityMgr) {
		super(City.class, entityMgr);
	}

}
