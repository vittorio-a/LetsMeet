package it.unisa.studenti.letsmeet.filter;

import it.unisa.studenti.letsmeet.model.EventoBean;

public class AllFilter implements Filter<EventoBean>{

	@Override
	public boolean check(EventoBean item) {
		return true;
	}
	
}
