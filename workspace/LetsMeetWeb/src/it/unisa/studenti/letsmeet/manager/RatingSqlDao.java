package it.unisa.studenti.letsmeet.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

import it.unisa.studenti.letsmeet.model.RatingBean;

public class RatingSqlDao extends SqlDaoDoubleKey<RatingBean> {
	
	
	private static final String GET_RATING_BY_EVENT_ID = "SELECT * FROM Rating WHERE idEvento = ?";
	private static final String DELETE_RATING = "DELETE FROM Rating WHERE idUtente = ? AND idEvento = ? ";
	private static final String GET_RATING_BY_USER_EVENT_ID = "SELECT * FROM Rating WHERE idUtente = ? AND idEvento = ?";
	private static final String GET_RATING_BY_USER_ID = "SELECT * FROM Rating WHERE idUtente = ?";
	private static final String INSERT_RATING = "INSERT Rating(idEvento, idUtente, voto) VALUE(?,?,?)";
	private static final String UPDATE_RATING = "UPDATE Rating SET voto = ? WHERE idUtente = ? AND idEvento = ?";
	
	private static final String EVENT_ID_FIELD = "idEvento";
	private static final String USER_ID_FILED = "idUtente";
	private static final String RATING_FILED = "voto";
	

	public RatingSqlDao(Connection connection) {
		super(connection);
	}

	@Override
	protected PreparedStatement getPreparedGetAllFirstKey(int id) throws SQLException{
		PreparedStatement st = connection.prepareStatement(GET_RATING_BY_USER_ID);
		st.setInt(1, id);
		return st;
	}

	@Override
	protected PreparedStatement getPreparedGetAllSecondKey(int id) throws SQLException{
		PreparedStatement st = connection.prepareStatement(GET_RATING_BY_EVENT_ID);
		st.setInt(1, id);
		return st;
	}

	@Override
	protected PreparedStatement getPreparedGetBothKey(int id1, int id2) throws SQLException{
		PreparedStatement st = connection.prepareStatement(GET_RATING_BY_USER_EVENT_ID);
		st.setInt(1, id1);
		st.setInt(2, id2);
		return st;
	}

	@Override
	protected PreparedStatement getPreparedDelete(RatingBean item) throws SQLException{
		PreparedStatement st = connection.prepareStatement(DELETE_RATING);
		st.setInt(1, getFirstKey(item));
		st.setInt(2, getSecondKey(item));
		return st;
	}

	@Override
	protected PreparedStatement getPreparedInsert(RatingBean item) throws SQLException{
		PreparedStatement st = connection.prepareStatement(INSERT_RATING, Statement.RETURN_GENERATED_KEYS);
		st.setInt(1, item.getEvento());
		st.setInt(2, item.getIdutente());
		st.setBoolean(3, item.isVoto());
		return st;
	}

	@Override
	protected PreparedStatement getPreparedUpdate(RatingBean item) throws SQLException{
		PreparedStatement st = connection.prepareStatement(UPDATE_RATING);
		st.setBoolean(1, item.isVoto());
		st.setInt(2, item.getIdutente());
		st.setInt(3, item.getEvento());
		return st;
	}

	@Override
	protected RatingBean getItemFromResulSet(ResultSet rs) throws SQLException, DaoException{
		RatingBean rating = new RatingBean();
		rating.setEvento(rs.getInt(EVENT_ID_FIELD));
		rating.setIdutente(rs.getInt(USER_ID_FILED));
		rating.setVoto(rs.getBoolean(RATING_FILED));
		return rating;
	}

	@Override
	protected int getFirstKey(RatingBean item) {
		return item.getIdutente();
	}

	@Override
	protected int getSecondKey(RatingBean item) {
		return item.getEvento();
	}

}
