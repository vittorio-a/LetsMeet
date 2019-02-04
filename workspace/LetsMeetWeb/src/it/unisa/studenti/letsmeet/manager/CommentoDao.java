package it.unisa.studenti.letsmeet.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;
import javax.sql.DataSource;

import it.unisa.studenti.letsmeet.model.CommentoBean;
import it.unisa.studenti.letsmeet.model.DataSourceSingleton;

public class CommentoDao implements Dao<CommentoBean> {
	private DataSource ds;
	
	private static String GET_COMMENT_BY_ID = "SELECT * FROM Commento WHERE id = ?";
	private static String GET_COMMENTS = "SELECT * FROM Commento";
	private static String INSERT_COMMENT = "INSERT INTO Commento (idCommento,idMittente,contenuto,idEvento,creationTime) VALUES (?,?,?,?,?)";
	private static String DELETE_COMMENT = "DELETE FROM Commento WHERE idCommento = ?";
	private static String UPDATE_COMMENT = "INSERT INTO Utente(idCommento,idMittente,contenuto,idEvento,creationTime) VALUES(?,?,?,?,?)";
	//Parametri
	private static String ID_COMMENTO ="idCommento";
	private static String ID_MITTENTE = "idMittente";
	private static String CONTENUTO = "contenuto";
	private static String ID_EVENTO = "idCommento";
	private static String CREATION_TIME = "creationTime";
	
	
	
	public CommentoDao() throws NamingException{
		ds = DataSourceSingleton.getDataSource();
	}
	
	

	@Override
	public CommentoBean get(int id) throws DaoException {
		CommentoBean commento = new CommentoBean();
		try {
			Connection con = ds.getConnection();
			PreparedStatement ps = con.prepareStatement(GET_COMMENT_BY_ID);
			ps.setInt(1, (int) id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				commento = getCommentFromResult(rs);
			}
			ps.close();
			rs.close();
			con.close();
		} catch (SQLException e) {
			throw new DaoException("Errore nel get in CommentoDAO", e, DaoExceptionType.SQLException);
		}
		return commento;
	}
	
	private CommentoBean getCommentFromResult(ResultSet rs) throws SQLException{
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
	public List<CommentoBean> getAll() throws DaoException {
		List<CommentoBean> commenti = new ArrayList<CommentoBean>();
		
		try {
			Connection con = ds.getConnection();
			PreparedStatement ps = con.prepareStatement(GET_COMMENTS);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				commenti.add(getCommentFromResult(rs));
			}
			ps.close();
			rs.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DaoException("Errore nel get in CommentoDAO", e, DaoExceptionType.SQLException);
		}
		return commenti;
	}

	
	@Override
	public boolean saveOrUpdate(CommentoBean commento) throws DaoException {
		int idCommento = commento.getIdCommento();
		if(idCommento != 0) {
			CommentoBean otherComment = get(idCommento);
			if(commento.equals(otherComment)) {
				return false;
		}else {
			try {
			Connection con =  ds.getConnection();
			PreparedStatement ps = con.prepareStatement(INSERT_COMMENT);
			ps.setInt(1, commento.getIdCommento());
			ps.setInt(2, commento.getIdUtente());
			ps.setString(3, commento.getContenuto());
			ps.setInt(4, commento.getIdEvento());
			ps.setTimestamp(5, Timestamp.from(commento.getCreationTime()));
			ps.executeUpdate();
			ps.close();
			con.close();
			}catch (SQLException e){
				throw new DaoException("Errore nel Insert in CommentoDAO", e, DaoExceptionType.SQLException);
			}
		
		}	
	}else {
		try {
		Connection con = ds.getConnection();
		PreparedStatement ps = con.prepareStatement(UPDATE_COMMENT);
		ps.setInt(1, commento.getIdCommento());
		ps.setInt(2, commento.getIdUtente());
		ps.setString(3, commento.getContenuto());
		ps.setInt(4, commento.getIdEvento());
		ps.setTimestamp(5, Timestamp.from(commento.getCreationTime()));
		ps.executeUpdate();
		ps.close();
		con.close();
		}catch(SQLException e) {
			throw new DaoException("Errore nel Update in CommentoDAO", e, DaoExceptionType.SQLException);
		}
	}
		return true;
		
	}

	@Override
	public boolean delete(CommentoBean commento) throws DaoException {
		int idCommento = commento.getIdCommento();
		if(idCommento != 0){
			try {
				Connection con = ds.getConnection();
				PreparedStatement ps = con.prepareStatement(DELETE_COMMENT);
				ps.setInt(1, idCommento);
				ps.close();
				con.close();
			}catch(SQLException e){
				throw new DaoException("Errore nel delete in CommentoDAO", e, DaoExceptionType.SQLException);

			}
			return true;
		}else {
			return false;
		}
		
	}

}
