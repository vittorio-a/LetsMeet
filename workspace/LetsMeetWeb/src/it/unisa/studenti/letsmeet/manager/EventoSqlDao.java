package it.unisa.studenti.letsmeet.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;

import it.unisa.studenti.letsmeet.model.EventoBean;

/**
 * 
 * Implementazione dell'interfaccia SqlDao della classe EventoBean
 *
 */
public class EventoSqlDao extends SqlDao<EventoBean> implements EventoDao{
	
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
	private static final String IS_VISIBLE_FIELD = "isVisibile";
	private static final String DESCRIZIONE_FIELD = "descrizione";
	
	
	private static final String GET_EVENT_BY_ID = "SELECT * FROM Evento WHERE idEvento = ?";

	private static final String GET_ALL_EVENTS = "SELECT * FROM Evento";
	
	private static final String UPDATE_EVENTO = "UPDATE Evento SET nome = ?, oraInizio = ?, oraFine = ?,"
			+ "idUtente = ?, idTipo = ?, idPosizione = ?, isVisibile = ?, feedback = ?,descrizione = ? WHERE idEvento = ? ";
	
	
	private static final String INSERT_EVENTO = "INSERT Evento(nome, oraInizio, oraFine, idUtente, idTipo, idPosizione, descrizione, feedback, isVisibile) VALUE(?,?,?,?,?,?,?,?,?)";
	
	private static final String DELETE_EVENTO_BY_ID ="DELETE FROM Evento WHERE idEvento = ?";
	
	
	private static final String GET_ALL_BY_USER_ID = "SELECT * FROM Evento WHERE idUtente = ?";
	
	private PosizioneSqlDao posizioneSqlDao;
	private TipoSqlDao tipoSqlDao;
	

	public EventoSqlDao(Connection connection) {
		super(connection);
		posizioneSqlDao = new PosizioneSqlDao(connection);
		tipoSqlDao = new TipoSqlDao(connection);
	}

	@Override
	protected int getKey(EventoBean item) {
		return item.getIdEvento();
	}

	@Override
	protected EventoBean getItemFromResultSet(ResultSet rs) throws SQLException, DaoException {
		boolean isVisibile = rs.getBoolean(IS_VISIBLE_FIELD);
		if(!isVisibile) {
			return null;
		}
		EventoBean evento = new EventoBean();
		evento.setFeedback(rs.getBigDecimal(FEEDBACK_FIELD));
		evento.setIdEvento(rs.getInt(ID_EVENTO_FIELD));
		evento.setIdUtente(rs.getInt(ID_CREATORE_FIELD));
		evento.setNome(rs.getString(NOME_FIELD));
		evento.setnPartecipanti(rs.getInt(N_PARTECIPANTI_FIELD));
		evento.setnVerificati(rs.getInt(N_VERIFICATI_FILED));
		evento.setOraInizio(rs.getTimestamp(ORA_INIZIO_FIELD).toInstant());
		evento.setOraFine(rs.getTimestamp(ORA_FINE_FIELD).toInstant());
		evento.setPosizione(posizioneSqlDao.get(rs.getInt(ID_POSIZIONE)));
		evento.setTipo(tipoSqlDao.get(rs.getInt(ID_TIPO_FIELD)));
		evento.setVisible(rs.getBoolean(IS_VISIBLE_FIELD));
		evento.setDescrizione(rs.getString(DESCRIZIONE_FIELD));
		
		return evento;
	}

	@Override
	protected PreparedStatement getPreparedGetById(int id) throws SQLException {
		PreparedStatement st = connection.prepareStatement(GET_EVENT_BY_ID);
		st.setInt(1, id);
		return st;
	}

	@Override
	protected PreparedStatement getPreparedGetAll() throws SQLException {
		return connection.prepareStatement(GET_ALL_EVENTS);
	}

	@Override
	protected PreparedStatement getPreparedDeleteById(int id) throws SQLException {
		PreparedStatement st = connection.prepareStatement(DELETE_EVENTO_BY_ID);
		st.setInt(1, id);
		return st;
	}

	@Override
	protected PreparedStatement getPreparedUpdateItem(EventoBean item) throws SQLException {
		PreparedStatement st = connection.prepareStatement(UPDATE_EVENTO);
		st.setString(1,  item.getNome());
		st.setTimestamp(2, Timestamp.from(item.getOraInizio()));
		st.setTimestamp(3, Timestamp.from(item.getOraFine()));
		st.setInt(4, item.getIdUtente());
		st.setInt(5, item.getTipo().getIdTipo());
		st.setInt(6, item.getPosizione().getId());
		st.setBoolean(7, item.isVisible());
		st.setBigDecimal(8, item.getFeedback());
		st.setString(9, item.getDescrizione());
		st.setInt(10, item.getIdEvento());
		return st;
	}
	
	/*
	private static final String UPDATE_EVENTO = "UPDATE Evento SET nome = ?, oraInizio = ?, oraFine = ?,"
			+ "idUtente = ?, idTipo = ?, idPosizione = ?, isVisibile = ?, descrizione = ? WHERE idEvento = ? ";*/

	@Override
	protected PreparedStatement getPreparedInsertItem(EventoBean item) throws SQLException {
		PreparedStatement st = connection.prepareStatement(INSERT_EVENTO,Statement.RETURN_GENERATED_KEYS);
		st.setString(1,  item.getNome());
		st.setTimestamp(2, Timestamp.from(item.getOraInizio()));
		st.setTimestamp(3, Timestamp.from(item.getOraFine()));
		st.setInt(4, item.getIdUtente());
		st.setInt(5, item.getTipo().getIdTipo());
		st.setInt(6, item.getPosizione().getId());
		st.setString(7, item.getDescrizione());
		st.setBigDecimal(8, item.getFeedback());
		st.setBoolean(9, item.isVisible());
		return st;
	}
	
	@Override
	public int saveOrUpdate(EventoBean item) throws DaoException {
		TipoSqlDao tipoDao = new TipoSqlDao(connection);
		int idTipo = 0;
		if((idTipo = tipoDao.saveOrUpdate(item.getTipo())) > 0) {
			item.getTipo().setIdTipo(idTipo);
		}
		
		PosizioneSqlDao posizioneDao = new PosizioneSqlDao(connection);
		int idPosizione = 0;
		if((idPosizione = posizioneDao.saveOrUpdate(item.getPosizione())) > 0) {
			item.getPosizione().setId(idPosizione);
		}
				
		return super.saveOrUpdate(item);
	}
	
	public List<EventoBean> getAllByUserId(int id) throws DaoException{
		PreparedStatement st = null;
		ResultSet rs = null;
		List<EventoBean> eventi = new ArrayList<>();
		try{
			st = connection.prepareStatement(GET_ALL_BY_USER_ID);
			st.setInt(1, id);
			rs = st.executeQuery();
			while(rs.next()) eventi.add(getItemFromResultSet(rs));
		}catch (SQLException e) {
			throw new DaoException("Unable to get event from user id", e, DaoExceptionType.SQLException);
		}finally {
			try{
				if(rs != null) rs.close();
				if(st != null) st.close();
			}catch (SQLException e) {
				//do nothing
			}
		}
		return eventi;
	}
}
