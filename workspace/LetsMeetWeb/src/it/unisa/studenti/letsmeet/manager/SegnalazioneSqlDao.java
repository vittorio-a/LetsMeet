package it.unisa.studenti.letsmeet.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import it.unisa.studenti.letsmeet.model.SegnalazioneBean;

public class SegnalazioneSqlDao extends SqlDao<SegnalazioneBean> {
	
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

	public SegnalazioneSqlDao(Connection connection) {
		super(connection);
	}

	@Override
	protected int getKey(SegnalazioneBean item) {
		return item.getIdItemSegnalato();
	}

	@Override
	protected SegnalazioneBean getItemFromResultSet(ResultSet rs) throws SQLException {
		throw new UnsupportedOperationException("Non ancora implementato: bassa priorità");
	}

	@Override
	protected PreparedStatement getPreparedGetById(int id) throws SQLException {
		throw new UnsupportedOperationException("Non ancora implementato: bassa priorità");
	}

	@Override
	protected PreparedStatement getPreparedGetAll() throws SQLException {
		throw new UnsupportedOperationException("Non ancora implementato: bassa priorità");
	}

	@Override
	protected PreparedStatement getPreparedDeleteById(int id) throws SQLException {
		throw new UnsupportedOperationException("Non ancora implementato: bassa priorità");
	}

	@Override
	protected PreparedStatement getPreparedUpdateItem(SegnalazioneBean item) throws SQLException {
		throw new UnsupportedOperationException("Non ancora implementato: bassa priorità");
	}

	@Override
	protected PreparedStatement getPreparedInsertItem(SegnalazioneBean item) throws SQLException {
		throw new UnsupportedOperationException("Non ancora implementato: bassa priorità");
	}

}
