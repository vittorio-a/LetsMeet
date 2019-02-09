package it.unisa.studenti.letsmeet.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import it.unisa.studenti.letsmeet.model.CredentialsBean;
import it.unisa.studenti.letsmeet.model.StatoUtente;
import it.unisa.studenti.letsmeet.model.UtenteBean;

public class UtenteSqlDao extends SqlDao<UtenteBean> {
	
	
	private static final String GET_USER_BY_ID = "SELECT * FROM Utente WHERE idUtente = ?";
	private static final String GET_ALL_USERS = "SELECT * FROM Utente";
	private static final String UPDATE_USER = "UPDATE INTO Utente(idUtente, username, passwordUtente, email, feedback, reactivationDay, stato)"+
			" VALUE(?,?,?,?,?,?,?)";
	private static final String INSERT_USER = "INSERT INTO Utente(username, passwordUtente, email, feedback, reactivationDay, stato)"+
			" VALUE(?,?,?,?,?,?)";
	private static final String DELETE_USER_BY_ID = "DELETE FROM Utente WHERE idUtente = ?";
	
	
	private static final String ID_FILED = "idUtente";
	private static final String USERNAME_FILED = "username";
	private static final String PASSWORD_FILED = "passwordUtente";
	private static final String EMAIL_FIELD = "email";
	private static final String FEEDBACK_FILED = "feedback";
	private static final String REACTIVATION_DATE_FIELD = "reactivationDay";
	private static final String STATO_FIELD = "stato";
	

	public UtenteSqlDao(Connection connection) {
		super(connection);
	}

	@Override
	protected int getKey(UtenteBean item) {
		return item.getIdUtente();
	}

	@Override
	protected UtenteBean getItemFromResultSet(ResultSet rs) throws SQLException {
		UtenteBean utente = new UtenteBean();
		StatoUtente stato = StatoUtente.valueOf(rs.getString(STATO_FIELD));
		if(stato.equals(StatoUtente.INVISIBILE)) {
			return utente;
		}
		utente.setIdUtente(rs.getInt(ID_FILED));
		utente.setReactivationDate(rs.getTimestamp(REACTIVATION_DATE_FIELD).toInstant());
		utente.setFeedbackUtente(rs.getFloat(FEEDBACK_FILED));
		utente.setEmail(rs.getString(EMAIL_FIELD));
		CredentialsBean credes = new CredentialsBean();
		credes.setPassword(rs.getBytes(PASSWORD_FILED));
		credes.setUsername(USERNAME_FILED);
		credes.setState(stato);
		utente.setCredentials(credes);
		return utente;
	}

	@Override
	protected PreparedStatement getPreparedGetById(int id) throws SQLException {
		PreparedStatement st = connection.prepareStatement(GET_USER_BY_ID);
		st.setInt(1, (int) id);
		return st;
	}

	@Override
	protected PreparedStatement getPreparedGetAll() throws SQLException {
		return connection.prepareStatement(GET_ALL_USERS);
	}

	@Override
	protected PreparedStatement getPreparedDeleteById(int id) throws SQLException {
		PreparedStatement st = connection.prepareStatement(DELETE_USER_BY_ID);
		st.setInt(1, id);
		return st;
	}

	@Override
	protected PreparedStatement getPreparedUpdateItem(UtenteBean item) throws SQLException {
		PreparedStatement st = connection.prepareStatement(UPDATE_USER);
		st.setInt(1, item.getIdUtente());
		CredentialsBean creds = item.getCredentials();
		st.setString(2, creds.getUsername());
		st.setBytes(3, creds.getPassword());
		st.setString(4, item.getEmail());
		st.setString(5, creds.getState().name());
		st.setFloat(6, item.getFeedbackUtente());
		st.setTimestamp(7, Timestamp.from(item.getReactivationDate()));
		return st;
	}

	@Override
	protected PreparedStatement getPreparedInsertItem(UtenteBean item) throws SQLException {
		PreparedStatement st = connection.prepareStatement(INSERT_USER);
		CredentialsBean creds = item.getCredentials();
		st.setString(1, creds.getUsername());
		st.setBytes(2, creds.getPassword());
		st.setString(3, item.getEmail());
		st.setString(4, creds.getState().name());
		st.setFloat(5, item.getFeedbackUtente());
		st.setTimestamp(6, Timestamp.from(item.getReactivationDate()));
		return st;
	}

}
