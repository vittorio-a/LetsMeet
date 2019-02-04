package it.unisa.studenti.letsmeet.manager;

import java.util.List;

public interface Dao<T> {
	
	T get(int id) throws DaoException;
	
	List<T> getAll() throws DaoException;
		
	boolean saveOrUpdate(T t) throws DaoException;
	
	boolean delete(T t) throws DaoException;
	

}
