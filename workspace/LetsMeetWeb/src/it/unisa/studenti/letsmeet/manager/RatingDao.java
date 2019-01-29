package it.unisa.studenti.letsmeet.manager;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;
import javax.sql.DataSource;


import it.unisa.studenti.letsmeet.model.DataSourceSingleton;
import it.unisa.studenti.letsmeet.model.RatingBean;

public class RatingDao {
	private static final String GET_RATING_BY_EVENT_ID = "SELECT * FROM Rating WHERE idEvento = ?";
	private static final String DELETE_RATING = "DELETE FROM Rating WHERE idEvento = ? AND idUtente = ?";
	private static final String GET_RATING_BY_EVENT_USER_ID = "SELECT * FROM Rating WHERE idEvento = ? AND idUtente = ?";
	private static final String GET_RATING_BY_USER_ID = "SELECT * FROM Rating WHERE idUtente = ?";
	private static final String INSERT_RATING = "INSERT Rating(idEvento, idUtente, valutazione) VALUE(?,?,?)";
	private static final String UPDATE_RATING = "UPDATE Rating WHERE idEveto = ? AND idUtente = ? SET valutazione = ?";
	
	private static final String EVENT_ID_FIELD = "idEvento";
	private static final String USER_ID_FILED = "idUtente";
	private static final String RATING_FILED = "votazione";


	
	private DataSource ds;
	
	public RatingDao() throws DaoException {
		try {
			ds = DataSourceSingleton.getDataSource();
		} catch (NamingException e) {
			throw new DaoException("Errore recuperando il DataSource", e, DaoExceptionType.SQLException);
		}
	}
	
	
	public List<RatingBean> getAllRatingForEventId(int id) throws DaoException {
		try {
			Connection conn = ds.getConnection();
			PreparedStatement st = conn.prepareStatement(GET_RATING_BY_EVENT_ID);
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
			List<RatingBean> ratings = new ArrayList<>();
			while(rs.next()) {
				ratings.add(getRatingFromResultSet(rs));
			}
			rs.close();
			st.close();
			conn.close();
			return ratings;
		}catch(SQLException e) {
			throw new DaoException("sql exception", e, DaoExceptionType.SQLException);
		}
	}

	
	public RatingBean getFromEventAndUserId(int idEvento, int idUtente) throws DaoException {
		RatingBean rating = null;
		try {
			Connection conn = ds.getConnection();
			PreparedStatement st = conn.prepareStatement(GET_RATING_BY_EVENT_USER_ID);
			st.setInt(1, idEvento);
			st.setInt(2, idUtente);
			ResultSet rs = st.executeQuery();
			if(rs.next()) {
				rating = getRatingFromResultSet(rs);
			}
			rs.close();
			st.close();
			conn.close();
			return rating;
		}catch(SQLException e) {
			throw new DaoException("sql exception", e, DaoExceptionType.SQLException);
		}
	}
	
	public List<RatingBean> getAllRatingForUserId(int id) throws DaoException {
		try {
			Connection conn = ds.getConnection();
			PreparedStatement st = conn.prepareStatement(GET_RATING_BY_USER_ID);
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
			List<RatingBean> ratings = new ArrayList<>();
			while(rs.next()) {
				ratings.add(getRatingFromResultSet(rs));
			}
			rs.close();
			st.close();
			conn.close();
			return ratings;
		}catch(SQLException e) {
			throw new DaoException("sql exception", e, DaoExceptionType.SQLException);
		}
	}

	private RatingBean getRatingFromResultSet(ResultSet rs) throws SQLException {
		RatingBean rating = new RatingBean();
		rating.setEvento(rs.getInt(EVENT_ID_FIELD));
		rating.setIdutente(rs.getInt(USER_ID_FILED));
		rating.setVoto(rs.getBoolean(RATING_FILED));
		return rating;
	}


	public boolean saveOrUpdate(RatingBean t) throws DaoException {
		Connection conn = null;
		try {
			 conn = ds.getConnection();
		} catch (SQLException e) {
			throw new DaoException("error getting the connection", e, DaoExceptionType.SQLException);
		}
		if(getFromEventAndUserId(t.getEvento(), t.getIdutente()) != null) {
			try {
				PreparedStatement st = conn.prepareStatement(UPDATE_RATING);
				st.setInt(1, t.getEvento());
				st.setInt(2, t.getIdutente());
				st.setBoolean(3, t.isVoto());
				boolean isOkUpdate =  (st.executeUpdate() > 0);
				st.close();
				conn.close();
				return isOkUpdate;
			}catch (SQLException e) {
				throw new DaoException("update " + t.toString(), e, DaoExceptionType.SQLException);

			}
		}else {
			try {
				PreparedStatement st = conn.prepareStatement(INSERT_RATING);
				st.setInt(1, t.getEvento());
				st.setInt(2, t.getIdutente());
				st.setBoolean(3, t.isVoto());
				boolean isOkUpdate =  (st.executeUpdate() > 0);
				st.close();
				conn.close();		
				return isOkUpdate;
			}catch (SQLException e) {
				throw new DaoException("insert " + t.toString(), e, DaoExceptionType.SQLException);
			}
		}
	}
	

	public boolean delete(RatingBean t) throws DaoException {
		int idEvento = t.getEvento();
		int idUtente = t.getIdutente();
		if(idEvento != 0 && idUtente != 0) {
			try {
				Connection conn = ds.getConnection();
				PreparedStatement st = conn.prepareStatement(DELETE_RATING);
				st.setInt(1, idEvento);
				st.setInt(2, idUtente);
				return (st.executeUpdate() > 0);	
			}catch (SQLException e) {
				throw new DaoException("SQLException in RatingDao", e, DaoExceptionType.SQLException);
			}
		}
		return false;
	}

}
