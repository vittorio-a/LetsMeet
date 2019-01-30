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
import it.unisa.studenti.letsmeet.model.SuperAdminBean;



public class SuperAdminDao implements Dao<SuperAdminBean> {
	
	private DataSource ds;
	
	private static final String GET_SUPER_ADMIN_BY_ID = "SELECT * FROM SuperAdmin WHERE idSuperAdmin = ?";
	private static final String GET_ALL_SUPER_ADMIN = "SELECT * FROM SuperAdmin";
	private static final String UPDATE_SUPER_ADMIN = "UPDATE INTO SuperAdmin(idSuperAdmin, username, passwordAdmin)"+
			" VALUE(?,?,?,?,?,?,?)";
	private static final String INSERT_SUPER_ADMIN = "INSERT INTO SuperAdmin(idSuperAdmin, username, passwordAdmin)"+
			" VALUE(?,?,?,?,?,?)";
	private static final String DELETE_SUPER_ADMIN_BY_ID = "DELETE FROM SuperAdmin WHERE idSuperAdmin = ?";
	
	private static final String ID_FILED = "idSuperAdmin";
	private static final String USERNAME_FILED = "username";
	private static final String PASSWORD_FILED = "passwordAdmin";
	
	public SuperAdminDao ()  throws NamingException{
		ds = DataSourceSingleton.getDataSource();
	}
	
	@Override
	public SuperAdminBean get(long id) throws DaoException {
		SuperAdminBean super_admin = null;
		try {
			Connection cn = ds.getConnection();
			PreparedStatement ps = cn.prepareStatement(GET_SUPER_ADMIN_BY_ID);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				super_admin = getSuperAdminFromResult(rs); 
			}
			ps.close();
			rs.close();
			cn.close();
		}catch (SQLException e) {
			throw new DaoException("SQLException in get", e, DaoExceptionType.SQLException);
		}
		
		return super_admin;
	}

	
	private SuperAdminBean getSuperAdminFromResult(ResultSet rs) throws SQLException {
		SuperAdminBean super_admin = new SuperAdminBean();
		super_admin.setIdSuperAdmin(rs.getInt(ID_FILED));
		super_admin.setPassword(rs.getBytes(PASSWORD_FILED));
		super_admin.setUsername(rs.getString(USERNAME_FILED));
		return super_admin;
	}
	
	
	
	@Override
	public List<SuperAdminBean> getAll() throws DaoException {
		List<SuperAdminBean> super_admins = new ArrayList<>();
		try {
			Connection conn = ds.getConnection();
			PreparedStatement st = conn.prepareStatement(GET_ALL_SUPER_ADMIN);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				super_admins.add(getSuperAdminFromResult(rs));
			}
			st.close();
			rs.close();
			conn.close();
		}catch (SQLException e) {
			throw new DaoException("SQLException in getAll", e, DaoExceptionType.SQLException);
		}
		return super_admins;
	}

	@Override
	public boolean saveOrUpdate(SuperAdminBean superAdmin) throws DaoException {
		int idSuperAdmin = superAdmin.getIdSuperAdmin();
		if(idSuperAdmin != 0) {
			SuperAdminBean otherAdmin = get(idSuperAdmin);
			if(otherAdmin.equals(superAdmin)) {
				return false;
			}else {
				try {
				Connection con = ds.getConnection();
				PreparedStatement ps = con.prepareStatement(UPDATE_SUPER_ADMIN);
				ps.setInt(0, idSuperAdmin);
				ps.setString(1, superAdmin.getUsername());
				ps.setBytes(2, superAdmin.getPassword());
				ps.executeUpdate();
				ps.close();
				con.close();
				}catch(SQLException e){
					throw new DaoException("utente update: " + superAdmin.toString(), e, DaoExceptionType.SQLException);
					
				}
			}
		}else {
			try {
				Connection con = ds.getConnection();
				PreparedStatement ps = con.prepareStatement(INSERT_SUPER_ADMIN);
				ps.setInt(0, idSuperAdmin);
				ps.setString(1, superAdmin.getUsername());
				ps.setBytes(2, superAdmin.getPassword());
				ps.executeUpdate();
				ps.close();
				con.close();
			}catch(SQLException e) {
				throw new DaoException("utente update: " + superAdmin.toString(), e, DaoExceptionType.SQLException);

			}
		}
		return true;
	}

	@Override
	public boolean delete(SuperAdminBean superAdmin) throws DaoException {
		int idSuperAdmin = superAdmin.getIdSuperAdmin();
		if(idSuperAdmin != 0) {
			try {
				Connection con = ds.getConnection();
				PreparedStatement ps = con.prepareStatement(DELETE_SUPER_ADMIN_BY_ID);
				ps.setInt(1, idSuperAdmin);
				ps.executeQuery();
				ps.close();
				con.close();
			}catch (SQLException e) {
				throw new DaoException("utente update: " + superAdmin.toString(), e, DaoExceptionType.SQLException);
			}
			return true;
		}else {
			return false;
		}
	}

}
