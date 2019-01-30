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
import it.unisa.studenti.letsmeet.model.TipoBean;

public class TipoDao implements Dao<TipoBean> {
	private DataSource ds;
	
	private static final String GET_TYPE_BY_ID = "SELECT * FROM Tipo WHERE idTipo = ?";
	private static final String GET_ALL_TYPES = "SELECT * FROM Tipo";
	
	
	
	public TipoDao() throws NamingException {
		ds = DataSourceSingleton.getDataSource();
	}
	

	@Override
	public TipoBean get(long id) throws DaoException {
		TipoBean tipo = null;
		try {
			Connection con = ds.getConnection();
			PreparedStatement ps = con.prepareStatement(GET_TYPE_BY_ID);
			ps.setInt(1, (int) id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				tipo = getTypeFromResult(rs);
			}
			rs.close();
			ps.close();
			con.close();
		}catch(SQLException e){
			throw new DaoException("SQLException in get", e, DaoExceptionType.SQLException);

		}
		return tipo;
	}
	
	private TipoBean getTypeFromResult(ResultSet rs) throws SQLException {
		TipoBean tipo =new TipoBean();
		tipo.setIdTipo(rs.getInt(1));
		tipo.setNomeTipo(rs.getString(2));
		tipo.setDescrizione(rs.getString(3));
		return tipo;
	}

	@Override
	public List<TipoBean> getAll() throws DaoException {
		List<TipoBean> tipi = new ArrayList<TipoBean>();
		try {
			Connection con = ds.getConnection();
			PreparedStatement ps = con.prepareStatement(GET_ALL_TYPES);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				tipi.add(getTypeFromResult(rs));
			}
		}catch(SQLException e){
			throw new DaoException("SQLException in get", e, DaoExceptionType.SQLException);

		}
		return tipi;
		
	}


	@Override
	public boolean saveOrUpdate(TipoBean t) throws DaoException {
		// TODO Auto-generated method stub
		throw new DaoException("Questo metodo non è implementato", new UnsupportedOperationException("Non verrà mai implementato") , DaoExceptionType.SQLException);
	}


	@Override
	public boolean delete(TipoBean t) throws DaoException {
		throw new DaoException("Questo metodo non è implementato", new UnsupportedOperationException("Non verrà mai implementato") , DaoExceptionType.SQLException);

	}

	
	

}
