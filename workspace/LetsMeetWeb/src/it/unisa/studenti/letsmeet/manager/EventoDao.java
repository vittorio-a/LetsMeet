package it.unisa.studenti.letsmeet.manager;

import java.awt.Event;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;
import javax.sql.DataSource;

import it.unisa.studenti.letsmeet.model.CredentialsBean;
import it.unisa.studenti.letsmeet.model.DataSourceSingleton;
import it.unisa.studenti.letsmeet.model.EventoBean;
import it.unisa.studenti.letsmeet.model.PosizioneBean;
import it.unisa.studenti.letsmeet.model.UtenteBean;



public class EventoDao implements Dao<EventoBean> {
	private DataSource ds;
	private PosizioneDao posizioneDao;
	TipoDao tipoDao;
	private static final String GET_EVENT_BY_ID = "SELECT * FORM Evento e WHERE id = ?";
	private static final String ID_EVENTO_FIELD = "idEvento";
	private static final String NOME_FIELD = "nome";
	private static final String FEEDBACK_FIELD = "feedback";
	private static final String N_PARTECIPANTI_FIELD = "npartecipanti";
	private static final String N_VERIFICATI_FILED = "nverificati";
	private static final String ORA_INIZIO_FIELD = "oraInizio";
	private static final String ORA_FINE_FIELD = "oraFine";
	private static final String ID_CREATORE_FIELD = "idUtente";
	private static final String ID_TIPO_FIELD = "idTipo";
	private static final String ID_POSIZIONE = "idPosizione";
	private static final String IS_VISIBLE_FIELD = "isVisible";
	
	
	private static final String GET_ALL_EVENTS = "SELECT * FROM Evento e";
	
	
	public EventoDao() throws DaoException{
		posizioneDao = new PosizioneDao();
		tipoDao = new TipoDao();
		try {
			ds = DataSourceSingleton.getDataSource();
		}catch (NamingException e) {
			throw new DaoException("Non è possibile recuperare data source", e, DaoExceptionType.SQLException);
		}
	}
	
	@Override
	public EventoBean get(int id) throws DaoException {
		try {
			Connection conn = ds.getConnection();
			PreparedStatement st = conn.prepareStatement(GET_EVENT_BY_ID);
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
			EventoBean evento = null;
			if(rs.next()) {
				evento = getEventoBeanFromResultSet(rs);
			}
			return evento;
		}catch (SQLException e) {
			throw new DaoException("SQLException in get evento", e, DaoExceptionType.SQLException);
		}
	}

	private EventoBean getEventoBeanFromResultSet(ResultSet rs) throws SQLException, DaoException {
		EventoBean evento = new EventoBean();
		evento.setFeedback(rs.getFloat(FEEDBACK_FIELD));
		evento.setIdEvento(rs.getInt(ID_EVENTO_FIELD));
		evento.setIdUtente(rs.getInt(ID_CREATORE_FIELD));
		evento.setNome(rs.getString(NOME_FIELD));
		evento.setnPartecipanti(rs.getInt(N_PARTECIPANTI_FIELD));
		evento.setnVerificati(rs.getInt(N_VERIFICATI_FILED));
		evento.setOraInizio(rs.getDate(ORA_INIZIO_FIELD).toInstant());
		evento.setOraFine(rs.getDate(ORA_FINE_FIELD).toInstant());
		evento.setPosizione(posizioneDao.get(rs.getInt(ID_POSIZIONE)));
		evento.setTipo(tipoDao.get(rs.getInt(ID_TIPO_FIELD)));
		evento.setVisible(rs.getBoolean(IS_VISIBLE_FIELD));
		
		return evento;
	}

	@Override
	public List<EventoBean> getAll() throws DaoException {
		List<EventoBean> eventi = new ArrayList<>();
		try {
			Connection conn = ds.getConnection();
			PreparedStatement st = conn.prepareStatement(GET_ALL_EVENTS);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				 eventi.add(getEventoBeanFromResultSet(rs));
			}
			return eventi;
		}catch (SQLException e) {
			throw new DaoException("SQLException in get evento", e, DaoExceptionType.SQLException);
		}
	}

	@Override
	public boolean saveOrUpdate(EventoBean eventoBean) throws DaoException {
		int eventoBeanId = eventoBean.getIdEvento();
		if(eventoBeanId != 0) {
			EventoBean otherEvento = get(eventoBeanId);
			if(otherEvento.equals(eventoBean)) {
				return false;
			}
			else {
				try {
					
					Connection conn = ds.getConnection();
					PreparedStatement st = conn.prepareStatement(UPDATE_EVENTO );
					st.setInt(1, idUtente);
					CredentialsBean creds = utenteBean.getCredentials();
					st.setString(2, creds.getUsername());
					st.setBytes(3, creds.getPassword());
					st.setString(4, utenteBean.getEmail());
					st.setString(5, creds.getState().name());
					st.setFloat(6, utenteBean.getFeedbackUtente());
					st.setTimestamp(7, Timestamp.from(utenteBean.getReactivationDate()));
					st.executeUpdate();
					st.close();
					conn.close();
				}catch (SQLException e) {
					throw new DaoException("utente update: " + (utenteBean != null ? utenteBean.toString() : "null"), e, DaoExceptionType.SQLException);
				}
			}
		}
		else {
			try {
				Connection conn = ds.getConnection();
				PreparedStatement st = conn.prepareStatement(INSERT_USER);
				CredentialsBean creds = utenteBean.getCredentials();
				st.setString(1, creds.getUsername());
				st.setBytes(2, creds.getPassword());
				st.setString(3, utenteBean.getEmail());
				st.setString(4, creds.getState().name());
				st.setFloat(5, utenteBean.getFeedbackUtente());
				st.setTimestamp(6, Timestamp.from(utenteBean.getReactivationDate()));
				st.executeUpdate();
				st.close();
				conn.close();
			}catch (SQLException e) {
				throw new DaoException("utente insert: " + (utenteBean != null ? utenteBean.toString() : "null"), e, DaoExceptionType.SQLException);
			}
			
	}

	@Override
	public boolean delete(EventoBean t) throws DaoException {
		// TODO Auto-generated method stub
		return false;
	}

}
