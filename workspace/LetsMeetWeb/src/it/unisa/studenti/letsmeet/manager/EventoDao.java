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
	
	private static final String UPDATE_EVENTO = "UPDATE Evento SET nome = ?, oraInizio = ?, oraFine = ?,"
			+ "idUtente = ?, idTipo = ?, idPosizione = ?, isVisible = ? WHERE id = ? ";
	
	
	private static final String INSERT_EVENTO = "INSERT Evento(nome, oraInizio, oraFine, idUtente, idTipo, idPosizione) VALUE(?,?,?,?,?,?)";
	
	private static final String DELETE_EVENTO_BY_ID ="DELETE FROM Evento WHERE idEvento = ?";
	
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
					PreparedStatement st = conn.prepareStatement(UPDATE_EVENTO);
					st.setString(1,  eventoBean.getNome());
					st.setTimestamp(2, Timestamp.from(eventoBean.getOraInizio()));
					st.setTimestamp(3, Timestamp.from(eventoBean.getOraFine()));
					st.setInt(4, eventoBean.getIdUtente());
					st.setInt(5, eventoBean.getTipo().getIdTipo());
					st.setInt(6, eventoBean.getPosizione().getId());
					st.setBoolean(7, eventoBean.isVisible());
					st.executeUpdate();
					st.close();
					conn.close();
					return true;
				}catch (SQLException e) {
					throw new DaoException("evento update: " + (eventoBean != null ? eventoBean.toString() : "null"), e, DaoExceptionType.SQLException);
				}
			}
		}
		else {
			try {
				Connection conn = ds.getConnection();
				PreparedStatement st = conn.prepareStatement(INSERT_EVENTO);
				st.setString(1,  eventoBean.getNome());
				st.setTimestamp(2, Timestamp.from(eventoBean.getOraInizio()));
				st.setTimestamp(3, Timestamp.from(eventoBean.getOraFine()));
				st.setInt(4, eventoBean.getIdUtente());
				st.setInt(5, eventoBean.getTipo().getIdTipo());
				st.setInt(6, eventoBean.getPosizione().getId());
				st.executeUpdate();
				st.close();
				conn.close();
				return true;
			}catch (SQLException e) {
				throw new DaoException("evento insert: " + (eventoBean != null ? eventoBean.toString() : "null"), e, DaoExceptionType.SQLException);
			}
		}
			
	}

	@Override
	public boolean delete(EventoBean t) throws DaoException {
		int id = t.getIdEvento();
		int result = 0;
		if(id != 0) {
			try {
				Connection conn = ds.getConnection();
				PreparedStatement st = conn.prepareStatement(DELETE_EVENTO_BY_ID);
				st.setInt(1, id);
				result = st.executeUpdate();
				st.close();
				conn.close();
			}catch (SQLException e) {
				throw new DaoException("evento delete: " + t.getIdEvento(), e, DaoExceptionType.SQLException);
			}
			return (result > 0);
			
		}
		else {
			return false;
		}
	}

}
