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
	
	public SqlDao(Connection connection) {
		this.connection = connection;
	}
	
	@Override
	public T get(int id) throws DaoException{
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = getPreparedGetById(id);
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
			ps = getPreparedGetById(getKey(item));
			rs = ps.executeQuery();
			boolean itemExists = rs.next();
						
			
			if(itemExists && item.equals(getItemFromResultSet(rs))) {
				return false;
			}
			else if(itemExists){
				rs.close();
				ps.close();
				ps = getPreparedUpdateItem(item);
				return (ps.executeUpdate() > 0);	
			}else {
				rs.close();
				ps.close();
				ps = getPreparedInsertItem(item);
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
