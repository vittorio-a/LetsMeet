package it.unisa.studenti.letsmeet.manager;

import java.util.List;

import it.unisa.studenti.letsmeet.model.EventoBean;

public interface EventoDao extends Dao<EventoBean> {
	
	/**
	 * Permette di poter prendere gli eventi creati da un utente in particolare
	 * @param id
	 * @return Una lista degli eventi creati dall'utente con l'id uguale ad id
	 * @throws DaoException
	 */
	
	public List<EventoBean> getAllByUserId(int id) throws DaoException;
	
}
