package it.unisa.studenti.letsmeet.manager;

import java.util.List;

public interface DaoDoubleKey<T> {
	
	List<T> getAllForFirstKey(int id) throws DaoException;
	List<T> getAllForSecondKey(int id)  throws DaoException;
	T getFromBothKeys(int id1, int id2) throws DaoException;
	boolean saveOrUpdate(T t) throws DaoException;
	boolean delete(T t) throws DaoException;

}
