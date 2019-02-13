package it.unisa.studenti.letsmeet.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;

import com.mysql.jdbc.Statement;

import it.unisa.studenti.letsmeet.model.CommentoBean;

public class CommentoSqlDao extends SqlDao<CommentoBean> {
	
	private static String GET_COMMENT_BY_ID = "SELECT * FROM Commento WHERE idCommento = ?";
	private static String GET_COMMENTS = "SELECT * FROM Commento";
	private static String INSERT_COMMENT = "INSERT INTO Commento (idCommento,idMittente,contenuto,idEvento,creationTime) VALUES (?,?,?,?,?)";
	private static String DELETE_COMMENT = "DELETE FROM Commento WHERE idCommento = ?";
	private static String UPDATE_COMMENT = "UPDATE Commento SET idMittente = ?, contenuto = ?, idEvento = ?, creationTime = ? WHERE idCommento = ?";
	//Parametri
	private static String ID_COMMENTO ="idCommento";
	private static String ID_MITTENTE = "idMittente";
	private static String CONTENUTO = "contenuto";
	private static String ID_EVENTO = "idEvento";
	private static String CREATION_TIME = "creationTime";
	

	public CommentoSqlDao(Connection connection) {
		super(connection);
	}

	@Override
	protected int getKey(CommentoBean item) {
		return item.getIdCommento();
	}

	@Override
	protected CommentoBean getItemFromResultSet(ResultSet rs) throws SQLException {
		CommentoBean commento = new CommentoBean();
		commento.setIdCommento(rs.getInt(ID_COMMENTO));
		commento.setContenuto(rs.getString(CONTENUTO));
		Timestamp data = rs.getTimestamp(CREATION_TIME);
		Instant dataInstant = data.toInstant();
		commento.setCreationTime(dataInstant);
		commento.setIdUtente(rs.getInt(ID_MITTENTE));
		commento.setIdEvento(rs.getInt(ID_EVENTO));
		return commento;
	}

	@Override
	protected PreparedStatement getPreparedGetById(int id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(GET_COMMENT_BY_ID);
		ps.setInt(1, (int) id);
		return ps;
	}

	@Override
	protected PreparedStatement getPreparedGetAll() throws SQLException {
		return connection.prepareStatement(GET_COMMENTS);
		
	}

	@Override
	protected PreparedStatement getPreparedDeleteById(int id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(DELETE_COMMENT);
		ps.setInt(1, id);
		return ps;
	}

	@Override
	protected PreparedStatement getPreparedUpdateItem(CommentoBean item) throws SQLException {		
		PreparedStatement ps = connection.prepareStatement(UPDATE_COMMENT);
		ps.setInt(1, item.getIdUtente());
		ps.setString(2, item.getContenuto());
		ps.setInt(3, item.getIdEvento());
		ps.setTimestamp(4, Timestamp.from(item.getCreationTime()));		
		ps.setInt(5, item.getIdCommento());

		return ps;
	}

	@Override
	protected PreparedStatement getPreparedInsertItem(CommentoBean item) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(INSERT_COMMENT, Statement.RETURN_GENERATED_KEYS);
		ps.setInt(1, item.getIdCommento());
		ps.setInt(2, item.getIdUtente());
		ps.setString(3, item.getContenuto());
		ps.setInt(4, item.getIdEvento());
		ps.setTimestamp(5, Timestamp.from(item.getCreationTime()));
		return ps;
	}

}
