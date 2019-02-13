package it.unisa.studenti.letsmeet.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public abstract class SqlDao<T> implements Dao<T> {

	
	protected Connection connection;
	
	protected abstract int getKey(T item);
	
	protected abstract T getItemFromResultSet(ResultSet rs) throws SQLException, DaoException;
	protected abstract PreparedStatement getPreparedGetById(int id) throws SQLException;
	protected abstract PreparedStatement getPreparedGetAll() throws SQLException;
	protected abstract PreparedStatement getPreparedDeleteById(int id) throws SQLException;
	protected abstract PreparedStatement getPreparedUpdateItem(T item) throws SQLException;
	protected abstract PreparedStatement getPreparedInsertItem(T item) throws SQLException;
	
	
	private static final int SQL_TIMEOUT = 30; //secondi

	/**
	 * Costruttore che restituisce l'instanza del Dao
	 * @param connection la connessione da utilizzare per le query
	 */
	public SqlDao(Connection connection) {
		this.connection = connection;
	}
	
	@Override
	public T get(int id) throws DaoException{
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = getPreparedGetById(id);
			ps.setQueryTimeout(SQL_TIMEOUT);
			rs = ps.executeQuery();
			if(rs.next()) {
				return getItemFromResultSet(rs);
			}
			else {
				return null;
			}
		}catch (SQLException e) {
			throw new DaoException("SQLException in get", e, DaoExceptionType.SQLException);
		}finally {
			try{
				if(rs != null) rs.close();
				if(ps != null) ps.close();
			}catch (SQLException e) {
				//do nothing
			}
		}
	}

	@Override
	public List<T> getAll() throws DaoException{
		List<T> items = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = getPreparedGetAll();
			ps.setQueryTimeout(SQL_TIMEOUT);
			rs = ps.executeQuery();
			while(rs.next()) items.add(getItemFromResultSet(rs));
		}catch (SQLException e) {
			throw new DaoException("SQLException in getAll", e, DaoExceptionType.SQLException);
		}finally {
			try {
				if(rs != null) rs.close();
				if(ps != null) ps.close();
			} catch (SQLException e) {
				//do nothign
			}
		}
		return items;
	}
	
	
	@Override
	public boolean saveOrUpdate(T item) throws DaoException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			
			T itemDb = this.get(getKey(item));
			
			if(itemDb != null && item.equals(getItemFromResultSet(rs))) {
				return false;
			}
			else if(itemDb != null){
				ps = getPreparedUpdateItem(item);
				ps.setQueryTimeout(SQL_TIMEOUT);
				return (ps.executeUpdate() > 0);	
			}else {
				ps = getPreparedInsertItem(item);
				ps.setQueryTimeout(SQL_TIMEOUT);
				return (ps.executeUpdate() > 0);
			}
		}catch (SQLException e) {
			throw new DaoException("SQLException in saveOrUpdate", e, DaoExceptionType.SQLException);
		}finally {
			try {
 				if(rs != null) rs.close();
 				if(ps != null) ps.close();
			}catch (SQLException e) {
				//do nothing
			}
		}
	}
	
	@Override
	public boolean delete(T item) throws DaoException {
		int itemId = getKey(item);
		if(itemId > 0) {
			PreparedStatement ps = null;
			try {
				ps = getPreparedDeleteById(itemId);
				ps.setQueryTimeout(SQL_TIMEOUT);
				return (ps.executeUpdate() > 0);
			}catch (SQLException e) {
				throw new DaoException("SQLException in delete", e, DaoExceptionType.SQLException);
			}finally {
				try {
					if(ps != null) ps.close();
				}catch (SQLException e) {
					//do nothing
				}
			}
		}else {
			return false;
		}
	}
}
