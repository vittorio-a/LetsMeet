package it.unisa.studenti.letsmeet.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import it.unisa.studenti.letsmeet.model.TipoBean;

/**
 * 
 * Implementazione dell'interfaccia SqlDao della classe TipoDao
 *
 */
public class TipoSqlDao extends SqlDao<TipoBean> implements TipoDao{


	private static final String GET_TYPE_BY_ID = "SELECT * FROM Tipo WHERE idTipo = ?";
	private static final String GET_ALL_TYPES = "SELECT * FROM Tipo";
	private static final String DELETE_BY_ID = "DELETE FROM Tipo WHERE idTipo = ?";
	private static final String INSERT_TIPO = "INSERT Tipo(nomeTipo, descrizione) VALUES (?,?)";
	private static final String UPDATE_TIPO = "UPDATE Tipo SET nomeTipo = ?, descrizione = ? WHERE idTipo = ?";

	

	public TipoSqlDao(Connection connection) {
		super(connection);
	}	
	
	@Override
	protected int getKey(TipoBean item) {
		return item.getIdTipo();
	}

	@Override
	protected TipoBean getItemFromResultSet(ResultSet rs) throws SQLException{
		TipoBean tipo = new TipoBean();
		tipo.setIdTipo(rs.getInt(1));
		tipo.setNomeTipo(rs.getString(2));
		tipo.setDescrizione(rs.getString(3));
		return tipo;	
	}

	@Override
	protected PreparedStatement getPreparedGetById(int id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(GET_TYPE_BY_ID);
		ps.setInt(1, id);
		return ps;
	}

	@Override
	protected PreparedStatement getPreparedGetAll() throws SQLException {
		PreparedStatement ps = connection.prepareStatement(GET_ALL_TYPES);
		return ps;
	}

	@Override
	protected PreparedStatement getPreparedDeleteById(int id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(DELETE_BY_ID);
		ps.setInt(1, id);
		return ps;
	}

	@Override
	protected PreparedStatement getPreparedUpdateItem(TipoBean item) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(UPDATE_TIPO);
		ps.setString(1, item.getNomeTipo());
		ps.setString(2, item.getDescrizione());
		ps.setInt(3, getKey(item));
		return ps;
	}

	@Override
	protected PreparedStatement getPreparedInsertItem(TipoBean item) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(INSERT_TIPO, Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, item.getNomeTipo());
		ps.setString(2, item.getDescrizione());
		return ps;
	}

}
