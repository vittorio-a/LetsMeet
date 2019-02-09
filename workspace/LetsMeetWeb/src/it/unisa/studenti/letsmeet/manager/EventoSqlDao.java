package it.unisa.studenti.letsmeet.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import it.unisa.studenti.letsmeet.model.EventoBean;

public class EventoSqlDao extends SqlDao<EventoBean> {
	
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
		EventoBean evento = new EventoBean();
		evento.setFeedback(rs.getFloat(FEEDBACK_FIELD));
		evento.setIdEvento(rs.getInt(ID_EVENTO_FIELD));
		evento.setIdUtente(rs.getInt(ID_CREATORE_FIELD));
		evento.setNome(rs.getString(NOME_FIELD));
		evento.setnPartecipanti(rs.getInt(N_PARTECIPANTI_FIELD));
		evento.setnVerificati(rs.getInt(N_VERIFICATI_FILED));
		evento.setOraInizio(rs.getDate(ORA_INIZIO_FIELD).toInstant());
		evento.setOraFine(rs.getDate(ORA_FINE_FIELD).toInstant());
		evento.setPosizione(posizioneSqlDao.get(rs.getInt(ID_POSIZIONE)));
		evento.setTipo(tipoSqlDao.get(rs.getInt(ID_TIPO_FIELD)));
		evento.setVisible(rs.getBoolean(IS_VISIBLE_FIELD));
		
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
		return st;
	}

	@Override
	protected PreparedStatement getPreparedInsertItem(EventoBean item) throws SQLException {
		PreparedStatement st = connection.prepareStatement(INSERT_EVENTO);
		st.setString(1,  item.getNome());
		st.setTimestamp(2, Timestamp.from(item.getOraInizio()));
		st.setTimestamp(3, Timestamp.from(item.getOraFine()));
		st.setInt(4, item.getIdUtente());
		st.setInt(5, item.getTipo().getIdTipo());
		st.setInt(6, item.getPosizione().getId());
		return st;
	}

}
