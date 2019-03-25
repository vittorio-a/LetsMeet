package it.unisa.studenti.letsmeet.model.filter;


public class AllFilter<T> implements Filter<T>{

	@Override
	public boolean check(T item) {
		return true;
	}
	
}
