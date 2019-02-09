package it.unisa.studenti.letsmeet.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public abstract class SqlDaoDoubleKey<T> implements DaoDoubleKey<T>{
	
	protected abstract PreparedStatement getPreparedGetAllFirstKey(int id) throws SQLException;
	protected abstract PreparedStatement getPreparedGetAllSecondKey(int id) throws SQLException;
	protected abstract PreparedStatement getPreparedGetBothKey(int id1, int id2) throws SQLException;
	protected abstract PreparedStatement getPreparedDelete(T item) throws SQLException;
	protected abstract PreparedStatement getPreparedInsert(T item) throws SQLException;
	protected abstract PreparedStatement getPreparedUpdate(T item) throws SQLException;
	protected abstract T getItemFromResulSet(ResultSet rs) throws SQLException, DaoException;
	protected abstract int getFirstKey(T item);
	protected abstract int getSecondKey(T item);
	
	
	protected Connection connection;
	
	public SqlDaoDoubleKey(Connection connection) {
		this.connection = connection;
	}
	
	@Override
	public List<T> getAllForFirstKey(int id) throws DaoException{
		List<T> objects = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = getPreparedGetAllFirstKey(id);
			rs = ps.executeQuery();
			while(rs.next()) objects.add(getItemFromResulSet(rs));

		}catch(SQLException e) {
			throw new DaoException("sql exception in getAllForFirstKey, id: " + id, e, DaoExceptionType.SQLException);
		}finally {
			try {
				if(rs != null) rs.close();
				if(ps != null) ps.close();
			}catch (SQLException e) {
				//nothing
			}

		}
		return objects;	
	}
	
	@Override
	public List<T> getAllForSecondKey(int id) throws DaoException {
		List<T> objects = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = getPreparedGetAllSecondKey(id);
			rs = ps.executeQuery();
			while(rs.next()) objects.add(getItemFromResulSet(rs));

		}catch(SQLException e) {
			throw new DaoException("sql exception in getAllForSecondKey, id: " + id, e, DaoExceptionType.SQLException);
		}finally {
			try {
				if(rs != null) rs.close();
				if(ps != null) ps.close();
			}catch (SQLException e) {
				//nothing
			}

		}
		return objects;	
	}
	
	
	@Override
	public T getFromBothKeys(int id1, int id2) throws DaoException {
		T object = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = getPreparedGetBothKey(id1, id2);
			rs = ps.executeQuery();
			if(rs.next()) object = getItemFromResulSet(rs);

		}catch(SQLException e) {
			throw new DaoException("sql exception in getFromBothKey, id1: " + id1 + "  id2: " + id2, e, DaoExceptionType.SQLException);
		}finally {
			try {
				if(rs != null) rs.close();
				if(ps != null) ps.close();
			}catch (SQLException e) {
				//nothing
			}

		}
		return object;	
	}

	
	@Override
	public boolean delete(T item) throws DaoException {
		PreparedStatement ps = null;
		int result = 0;
		try {
			ps = getPreparedDelete(item);
			result = ps.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException("SQLException in delete PartecipationDao", e, DaoExceptionType.SQLException);
		}finally {
			try {
				if(ps != null) ps.close();
			}catch (SQLException e) {
				//nothing
			}
		}
		return (result > 0);
	}
	
	@Override
	public boolean saveOrUpdate(T item) throws DaoException {
		PreparedStatement ps = null;
		boolean isOk = false;
		try {
			if(getFromBothKeys(getFirstKey(item), getSecondKey(item)) != null) {
				//da fare update perchè è già presente nel db
				ps = getPreparedUpdate(item);
				isOk = (ps.executeUpdate() > 0);
			}else {
				ps = getPreparedInsert(item);
				isOk =  (ps.executeUpdate() > 0);
			}
		}catch(SQLException e) {
			throw new DaoException("update " + item.toString(), e, DaoExceptionType.SQLException);
		}finally {
			try {
				if(ps != null) ps.close();
			}catch (SQLException e) {
				//nothing
			}
		}
		return isOk;	
	}
}