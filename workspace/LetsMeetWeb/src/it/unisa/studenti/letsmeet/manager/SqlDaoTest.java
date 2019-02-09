package it.unisa.studenti.letsmeet.manager;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unisa.studenti.letsmeet.model.TipoBean;

abstract class SqlDaoTest<T> {

	protected Connection conn;
	protected SqlDao<T> dao;
	
	protected abstract SqlDao<T> getDao(Connection conn);
	protected abstract void assertsGet(T test);
	protected abstract void assertsGetAll(List<T> test);
	protected abstract T getInsertObject();
	protected abstract T getUpdateObject();
	protected abstract void assertsUpdate(T test);
	protected abstract void assertsInsert(List<T> test);
	protected abstract T getDeleteObject();
	protected abstract void assertsDelete(List<T> test);
	protected abstract T getDeleteFalse();
	protected abstract int getKey(T item);
	
	@BeforeEach
	void setUp() throws Exception {
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/letsmeet", "root", "Nc3Dug2SKVofEBJrhE3x");
		conn.setAutoCommit(false);
		dao = getDao(conn);
	}

	@AfterEach
	void tearDown() throws Exception {
		conn.rollback();
		conn.close();
	}

	@Test
	void testGet() throws DaoException{
		T object = dao.get(1);
		assertNotNull(object);
		assertsGet(object);
	}

	@Test
	void testGetAll() throws DaoException{
		List<T> objects = dao.getAll();
		assertNotNull(objects);
		assertsGetAll(objects);
	}

	@Test
	void testSaveOrUpdate() throws DaoException{
		T object = getInsertObject();
		boolean ret = dao.saveOrUpdate(object);
		assertTrue(ret);
		
		List<T> result = dao.getAll();
		assertNotNull(result);
		assertsInsert(result);
		
		object = getUpdateObject();
		ret = dao.saveOrUpdate(object);
		T res = dao.get(getKey(object));
		assertNotNull(res);
		assertsUpdate(res);
	}

	@Test
	void testDelete() throws DaoException{
		T object = getDeleteObject();
		boolean ret = dao.delete(object);
		assertTrue(ret);
		
		object = getDeleteFalse();
		ret = dao.delete(object);
		assertFalse(ret);
		
		
	}

}
