package it.unisa.studenti.letsmeet.manager;

import java.util.List;

import javax.naming.NamingException;
import javax.sql.DataSource;

import it.unisa.studenti.letsmeet.model.DataSourceSingleton;
import it.unisa.studenti.letsmeet.model.SegnalazioneBean;

//Non implementato
public class SegnalazioneDao implements Dao<SegnalazioneBean> {
	
	private DataSource ds;
	
	private static final String GET_SEGN_EVENTO_BY_ID = "SELECT * FROM SegnalazioneEvento WHERE idSegnalazione = ?";
	private static final String GET_ALL_SEGNS_EVENTO = "SELECT * FROM SegnalazioneEvento";
	private static final String GET_SEGN_COMMENTO_BY_ID = "SELECT * FROM SegnalazioneCommento WHERE idSegnalazione = ?";
	private static final String GET_ALL_SEGNS_COMMENTO = "SELECT * FROM SegnalazioneCommento";
	private static final String UPDATE_SEGN_EVENTO = "UPDATE INTO SegnalazioneEvento(idSegnalazione, idUtente, idEvento)"+
			" VALUE(?,?,?,?,?,?,?)";
	private static final String UPDATE_SEGN_COMMENTO = "UPDATE INTO SegnalazioneCommento(idSegnalazione, idUtente, idCommento)"+
			" VALUE(?,?,?,?,?,?,?)";
	private static final String INSERT_SEGN_EVENTO = "INSERT INTO SegnalazioneEvento(idSegnalazione, idUtente, idEvento)"+
			" VALUE(?,?,?,?,?,?)";
	private static final String INSERT_SEGN_COMMENTO = "INSERT INTO SegnalazioneEvento(idSegnalazione, idUtente, idCommento)"+
			" VALUE(?,?,?,?,?,?)";
	private static final String DELETE_SEGN_BY_ID = "DELETE FROM Segnalazione WHERE idSegnalazione = ?";
	
	private static final String ID_FILED = "idSegnalazione";
	private static final String USER_FILED = "idUtente";
	private static final String EVENT_FILED = "idEvento";
	private static final String COMMENT_FILED = "idCommento";
	
	
	
	public SegnalazioneDao() throws NamingException{
		ds = DataSourceSingleton.getDataSource();
	}

	@Override
	public SegnalazioneBean get(long id) throws DaoException {
		throw new DaoException("Questo metodo non è ancora implementato per la bassa priorità di cui gode", new UnsupportedOperationException("Non verrà mai implementato") , DaoExceptionType.SQLException);
		
	}

	@Override
	public List<SegnalazioneBean> getAll() throws DaoException {
		throw new DaoException("Questo metodo non è ancora implementato per la bassa priorità di cui gode", new UnsupportedOperationException("Non verrà mai implementato") , DaoExceptionType.SQLException);
	
	}

	@Override
	public boolean saveOrUpdate(SegnalazioneBean t) throws DaoException {
		throw new DaoException("Questo metodo non è ancora implementato per la bassa priorità di cui gode", new UnsupportedOperationException("Non verrà mai implementato") , DaoExceptionType.SQLException);

	}

	@Override
	public boolean delete(SegnalazioneBean t) throws DaoException {
		throw new DaoException("Questo metodo non è ancora implementato per la bassa priorità di cui gode", new UnsupportedOperationException("Non verrà mai implementato") , DaoExceptionType.SQLException);

	}
	
	

}
