package it.unisa.studenti.letsmeet.filter;

import it.unisa.studenti.letsmeet.model.EventoBean;

public class AllFilter<T> implements Filter<T>{

	@Override
	public boolean check(T item) {
		return true;
	}
	
}
