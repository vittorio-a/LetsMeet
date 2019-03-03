package it.unisa.studenti.letsmeet.manager;

import java.util.List;

import it.unisa.studenti.letsmeet.model.CommentoBean;

public interface CommentoDao extends Dao<CommentoBean> {
	public List<CommentoBean> getAllCommentsByEventId(int eventId) throws DaoException;
}
