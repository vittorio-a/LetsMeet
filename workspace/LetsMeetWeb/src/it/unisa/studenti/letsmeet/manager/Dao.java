package it.unisa.studenti.letsmeet.manager;

import java.util.List;

public interface Dao<T> {
	
	T get(long id);
	
	List<T> getAll();
	
	void save(T t);
	
	void update(T t, String[] params);
	
	void delete(T t);
	

}
