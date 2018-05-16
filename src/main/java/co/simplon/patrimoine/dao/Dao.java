package co.simplon.patrimoine.dao;

public interface Dao<T> {
	T create(T object);
	T getById(Long id);
	T update(T object);
	void deleteById(Long id);
}
