package it.unisa.studenti.letsmeet.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import it.unisa.studenti.letsmeet.model.PartecipazioneBean;

public class PartecipazioneSqlDao extends SqlDaoDoubleKey<PartecipazioneBean> {
	
	private static final String GET_PARTECIPATION_BY_EVENT_ID = "SELECT * FROM Partecipazione WHERE idEvento = ?";
	private static final String DELETE_PARTECIPATION = "DELETE FROM Partecipazione WHERE idEvento = ? AND idUtente = ?";
	private static final String GET_PARTECIPATION_BY_EVENT_USER_ID = "SELECT * FROM Partecipazione WHERE idUtente = ?  AND idEvento = ?";
	private static final String GET_PARTECIPATION_BY_USER_ID = "SELECT * FROM Partecipazione WHERE idUtente = ?";
	private static final String INSERT_PARTECIPATION = "INSERT Partecipazione(idUtente, idEvento, isVerification) VALUE(?,?,?)";
	private static final String UPDATE_PARTECIPATION = "UPDATE Rating WHERE idUtente = ? AND idEvento = ? SET isVerificato = ?";
	
	private static final String EVENT_ID_FIELD = "idEvento";
	private static final String USER_ID_FILED = "idUtente";
	private static final String VERIFICATION_FILED = "isVerificato";

	public PartecipazioneSqlDao(Connection connection) {
		super(connection);
	}

	@Override
	protected PreparedStatement getPreparedGetAllFirstKey(int id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(GET_PARTECIPATION_BY_USER_ID);
		ps.setInt(1, id);
		return ps;
	}

	@Override
	protected PreparedStatement getPreparedGetAllSecondKey(int id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(GET_PARTECIPATION_BY_EVENT_ID);
		ps.setInt(1, id);
		return ps;
	}

	@Override
	protected PreparedStatement getPreparedGetBothKey(int id1, int id2) throws SQLException {
		PreparedStatement st = connection.prepareStatement(GET_PARTECIPATION_BY_EVENT_USER_ID);
		st.setInt(1, id1);
		st.setInt(2, id2);
		return st;
	}

	@Override
	protected PreparedStatement getPreparedDelete(PartecipazioneBean item) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(DELETE_PARTECIPATION);
		ps.setInt(1, getFirstKey(item));
		ps.setInt(2, getSecondKey(item));
		return ps;
	}

	@Override
	protected PreparedStatement getPreparedInsert(PartecipazioneBean item) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(INSERT_PARTECIPATION);
		ps.setInt(1, item.getIdUtente());
		ps.setInt(2, item.getIdEvento());
		ps.setBoolean(3, item.isVerificato());
		return ps;
	}

	@Override
	protected PreparedStatement getPreparedUpdate(PartecipazioneBean item) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(UPDATE_PARTECIPATION);
		ps.setInt(1, item.getIdUtente());
		ps.setInt(2, item.getIdEvento());
		ps.setBoolean(3, item.isVerificato());
		return ps;
	}

	@Override
	protected PartecipazioneBean getItemFromResulSet(ResultSet rs) throws SQLException, DaoException {
		PartecipazioneBean partecipation = new PartecipazioneBean();
		partecipation.setIdUtente(rs.getInt(USER_ID_FILED));
		partecipation.setIdEvento(rs.getInt(EVENT_ID_FIELD));
		partecipation.setVerificato(rs.getBoolean(VERIFICATION_FILED));
		return partecipation;
	}

	@Override
	protected int getFirstKey(PartecipazioneBean item) {
		return item.getIdUtente();
	}

	@Override
	protected int getSecondKey(PartecipazioneBean item) {
		return item.getIdEvento();
	}

}
