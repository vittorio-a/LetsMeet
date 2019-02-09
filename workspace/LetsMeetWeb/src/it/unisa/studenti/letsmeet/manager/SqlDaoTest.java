package it.unisa.studenti.letsmeet.manager;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


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
	
	
	private void cutConnection() throws SQLException{
		conn.rollback();
		conn.close();
	}
	
	
	@BeforeEach
	void setUp() throws Exception {
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/letsmeet", "root", "Nc3Dug2SKVofEBJrhE3x");
		conn.setAutoCommit(false);
		dao = getDao(conn);
	}

	@AfterEach
	void tearDown() throws Exception {
		//cutConnection();
	}

	@Test
	void testGet() throws DaoException{
		T object = dao.get(1);
		assertNotNull(object);
		assertsGet(object);
		
		object = dao.get(Integer.MAX_VALUE);
		assertNull(object);
		
		try {
			cutConnection();

		}catch (SQLException e) {
			fail("Not able to cut connection");
		}
		assertThrows(DaoException.class, ()->{
			dao.get(1);
		});
	}

	@Test
	void testGetAll() throws DaoException{
		List<T> objects = dao.getAll();
		assertNotNull(objects);
		assertsGetAll(objects);
		
		try {
			cutConnection();

		}catch (SQLException e) {
			fail("Not able to cut connection");
		}
		assertThrows(DaoException.class, ()->{
			dao.getAll();
		});
	}

	@Test
	void testSaveOrUpdate() throws DaoException{
		List<T> before = dao.getAll();
		
		T object = getInsertObject();
		boolean ret = dao.saveOrUpdate(object);
		assertTrue(ret);
		
		List<T> afterInsert = dao.getAll();
		assertNotNull(afterInsert);
		assertEquals(1, afterInsert.size() - before.size());
		assertsInsert(afterInsert);
		
		
		
		object = getUpdateObject();
		ret = dao.saveOrUpdate(object);
		T res = dao.get(getKey(object));
		assertNotNull(res);
		assertEquals(object, res);
		assertsUpdate(res);

		
		List<T> afterUpdate = dao.getAll();
		
		object = afterUpdate.get(1);
		ret = dao.saveOrUpdate(object);
		assertFalse(ret);
		
		final T testCutObj = object;
		try {
			cutConnection();

		}catch (SQLException e) {
			fail("Not able to cut connection");
		}
		assertThrows(DaoException.class, ()->{
			dao.saveOrUpdate(testCutObj);
		});
	}

	@Test
	void testDelete() throws DaoException{
		T object = getDeleteObject();
		boolean ret = dao.delete(object);
		assertTrue(ret);
		
		List<T> afterDelete = dao.getAll();
		assertsDelete(afterDelete);
		
		object = getDeleteFalse();
		ret = dao.delete(object);
		assertFalse(ret);
		
		
		final T testCutObj = object;
		try {
			cutConnection();

		}catch (SQLException e) {
			fail("Not able to cut connection");
		}
		assertThrows(DaoException.class, ()->{
			dao.delete(testCutObj);
		});
		
		
	}

}
