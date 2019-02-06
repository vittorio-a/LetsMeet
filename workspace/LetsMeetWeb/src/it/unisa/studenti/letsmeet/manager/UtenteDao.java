package it.unisa.studenti.letsmeet.manager;

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
import it.unisa.studenti.letsmeet.model.StatoUtente;
import it.unisa.studenti.letsmeet.model.UtenteBean;

/**
 * @author DinoB
 *
 */
public class UtenteDao implements Dao<UtenteBean>{
	private DataSource ds;
	
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

	
	
	/**
	 * @throws DaoException
	 */
	public UtenteDao() throws DaoException{
		try {
			ds = DataSourceSingleton.getDataSource();
		}catch (NamingException e) {
			throw new DaoException("Non è possibile recuperare data source", e, DaoExceptionType.SQLException);
		}	}
	
	
	@Override
	public UtenteBean get(int id) throws DaoException {
		UtenteBean utente = null;
		try {
			Connection conn = ds.getConnection();
			PreparedStatement st = conn.prepareStatement(GET_USER_BY_ID);
			st.setInt(1, (int) id);
			ResultSet rs = st.executeQuery();
			if(rs.next()) {
				utente = getUserFromResult(rs);
			}
			st.close();
			rs.close();
			conn.close();
		}catch (SQLException e) {
			throw new DaoException("SQLException in get", e, DaoExceptionType.SQLException);
		}
		
		return utente; 
	}
 
	private UtenteBean getUserFromResult(ResultSet rs) throws SQLException {
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
	public List<UtenteBean> getAll() throws DaoException {
		List<UtenteBean> utenti = new ArrayList<>();
		try {
			Connection conn = ds.getConnection();
			PreparedStatement st = conn.prepareStatement(GET_ALL_USERS);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				utenti.add(getUserFromResult(rs));
			}
			st.close();
			rs.close();
			conn.close();
		}catch (SQLException e) {
			throw new DaoException("SQLException in getAll", e, DaoExceptionType.SQLException);
		}
		return utenti;
	}



	/* (non-Javadoc)
	 * @see it.unisa.studenti.letsmeet.manager.Dao#saveOrUpdate(java.lang.Object)
	 */
	@Override
	public boolean saveOrUpdate(UtenteBean utenteBean) throws DaoException {
		int idUtente = utenteBean.getIdUtente();
		
		if(idUtente != 0) {
			UtenteBean otherUtente = get(idUtente);
			if(otherUtente.equals(utenteBean)) {
				return false;
			}
			else {
				try {
					
					Connection conn = ds.getConnection();
					PreparedStatement st = conn.prepareStatement(UPDATE_USER);
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
		return true;
	}

	@Override
	public boolean delete(UtenteBean utente) throws DaoException {
		int id = utente.getIdUtente();
		int ris = 0;
		if(id != 0) {
			try {
				Connection conn = ds.getConnection();
				PreparedStatement st = conn.prepareStatement(DELETE_USER_BY_ID);
				st.setInt(1, id);
				ris = st.executeUpdate();
				st.close();
				conn.close();
			}catch (SQLException e) {
				throw new DaoException("utente delete: " + utente.getIdUtente(), e, DaoExceptionType.SQLException);
			}
			return (ris > 0);
			
		}
		else {
			return false;
		}
	}
}
