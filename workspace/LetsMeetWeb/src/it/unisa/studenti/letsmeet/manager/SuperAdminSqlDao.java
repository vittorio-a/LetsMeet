package it.unisa.studenti.letsmeet.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Statement;

import it.unisa.studenti.letsmeet.model.SuperAdminBean;

public class SuperAdminSqlDao extends SqlDao<SuperAdminBean> {

	private static final String GET_SUPER_ADMIN_BY_ID = "SELECT * FROM SuperAdmin WHERE idSuperAdmin = ?";
	private static final String GET_ALL_SUPER_ADMIN = "SELECT * FROM SuperAdmin";
	private static final String UPDATE_SUPER_ADMIN = "UPDATE SuperAdmin SET username = ?, passwordAdmin = ? WHERE idSuperAdmin = ?";
	private static final String INSERT_SUPER_ADMIN = "INSERT INTO SuperAdmin(username, passwordAdmin)"+
			" VALUE(?,?)";
	private static final String DELETE_SUPER_ADMIN_BY_ID = "DELETE FROM SuperAdmin WHERE idSuperAdmin = ?";
	
	private static final String ID_FILED = "idSuperAdmin";
	private static final String USERNAME_FILED = "username";
	private static final String PASSWORD_FILED = "passwordAdmin";
	
	public SuperAdminSqlDao(Connection connection) {
		super(connection);
	}

	@Override
	protected int getKey(SuperAdminBean item) {
		return item.getIdSuperAdmin();
	}

	@Override
	protected SuperAdminBean getItemFromResultSet(ResultSet rs) throws SQLException {
		SuperAdminBean super_admin = new SuperAdminBean();
		super_admin.setIdSuperAdmin(rs.getInt(ID_FILED));
		super_admin.setPassword(rs.getBytes(PASSWORD_FILED));
		super_admin.setUsername(rs.getString(USERNAME_FILED));
		return super_admin;
	}

	@Override
	protected PreparedStatement getPreparedGetById(int id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(GET_SUPER_ADMIN_BY_ID);
		ps.setInt(1, id);		
		return ps;
	}

	@Override
	protected PreparedStatement getPreparedGetAll() throws SQLException {
		return connection.prepareStatement(GET_ALL_SUPER_ADMIN);
	}

	@Override
	protected PreparedStatement getPreparedDeleteById(int id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(DELETE_SUPER_ADMIN_BY_ID);
		ps.setInt(1, id);
		return ps;
	}

	@Override
	protected PreparedStatement getPreparedUpdateItem(SuperAdminBean item) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(UPDATE_SUPER_ADMIN);
		ps.setString(1, item.getUsername());
		ps.setBytes(2, item.getPassword());
		ps.setInt(3, item.getIdSuperAdmin());
		return ps;
	}

	@Override
	protected PreparedStatement getPreparedInsertItem(SuperAdminBean item) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(INSERT_SUPER_ADMIN, Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, item.getUsername());
		ps.setBytes(2, item.getPassword());	
		return ps;
	}

}
