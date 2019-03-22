package it.unisa.studenti.letsmeet.manager.unit;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unisa.studenti.letsmeet.manager.DaoException;
import it.unisa.studenti.letsmeet.manager.SqlDaoDoubleKey;

abstract class SqlDaoDoubleKeyTest<T> {

	protected Connection conn;
	protected SqlDaoDoubleKey<T> dao;
	
	protected abstract SqlDaoDoubleKey<T> getDao(Connection conn);

	protected abstract void assertsGetBothKey(T test);
	protected abstract void assertsGetFirstKey(List<T> test);
	protected abstract void assertsGetSecondKey(List<T> test);
	protected abstract T getInsertObject();
	protected abstract T getUpdateObject();
	protected abstract void assertsUpdate(T test);
	protected abstract void assertsInsert(List<T> test);
	protected abstract T getDeleteObject();
	protected abstract void assertsDelete(List<T> test);
	protected abstract T getDeleteFalse();
	protected abstract int getFirstKey(T item);
	protected abstract int getSecondKey(T item);

	
	protected int firstKey = 1, secondKey = 1;

	
	
	private void cutConnection() throws SQLException{
		conn.rollback();
		conn.close();
	}
	
	
	@BeforeEach
	void setUp() throws Exception {
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/letsmeet", "root", "Nc3Dug2SKVofEBJrhE3x");
		conn.setAutoCommit(false);
		//conn.setT
		dao = getDao(conn);
	}
	
	@AfterEach
	void tearDown() throws Exception{
		if(conn != null && !conn.isClosed() && !conn.getAutoCommit()) {
			conn.rollback();	
		}
		if(conn != null && !conn.isClosed()) {
			conn.close();
		}
	}
	
	@Test
	void testGetAllForFirstKey() throws DaoException{
		List<T> objects = dao.getAllForFirstKey(firstKey);
		assertNotNull(objects);
		assertsGetFirstKey(objects);
	}
	
	@Test
	void testGetAllForFirstKeyCutConn() throws DaoException{
		try {
			cutConnection();

		}catch (SQLException e) {
			fail("Not able to cut connection");
		}
		assertThrows(DaoException.class, ()->{
			dao.getAllForFirstKey(secondKey);
		});
	}


		
	@Test
	void testGetAllForSecondKey() throws DaoException{
		List<T> objects = dao.getAllForSecondKey(secondKey);
		assertNotNull(objects);
		assertsGetSecondKey(objects);
	}
	
	@Test
	void testGetAllForSecondKeyCutConn() throws DaoException{	
		try {
			cutConnection();

		}catch (SQLException e) {
			fail("Not able to cut connection");
		}
		assertThrows(DaoException.class, ()->{
			dao.getAllForSecondKey(secondKey);
		});	
	}
	
	

	@Test
	void testGetFromBothKeys() throws DaoException{
		T object = dao.getFromBothKeys(firstKey,secondKey);
		assertNotNull(object);
		assertsGetBothKey(object);
		
		object = dao.getFromBothKeys(Integer.MAX_VALUE, Integer.MAX_VALUE);
		assertNull(object);
	}
	
	@Test
	void testGetFromBothKeysCutConn() throws DaoException{
		try {
			cutConnection();

		}catch (SQLException e) {
			fail("Not able to cut connection");
		}
		assertThrows(DaoException.class, ()->{
			dao.getFromBothKeys(firstKey, secondKey);
		});
	}
	

	@Test
	void testDelete() throws DaoException{
		T object = getDeleteObject();
		List<T> beforeDelete = dao.getAllForFirstKey(getFirstKey(object));

		boolean ret = dao.delete(object);
		assertTrue(ret);
		
		List<T> afterDelete = dao.getAllForFirstKey(getFirstKey(object));
		assertTrue(beforeDelete.size() - afterDelete.size() == 1);
		assertsDelete(afterDelete);
	}
	

	@Test
	void testDeleteNotCancel() throws DaoException{
		T object = getDeleteFalse();
		boolean ret = dao.delete(object);
		assertFalse(ret);
	}
	
	@Test
	void testDeleteCutConn() throws DaoException{
		try {
			cutConnection();

		}catch (SQLException e) {
			fail("Not able to cut connection");
		}
		assertThrows(DaoException.class, ()->{
			dao.delete(getDeleteObject());
		});
	}

	@Test
	void testSaveOrUpdateInsert() throws DaoException{
		T object = getInsertObject();

		List<T> before = dao.getAllForFirstKey(getFirstKey(object));
		
		boolean ret = dao.saveOrUpdate(object);
		assertTrue(ret);
		
		List<T> afterInsert = dao.getAllForFirstKey(getFirstKey(object));
		assertNotNull(afterInsert);
		assertEquals(1, afterInsert.size() - before.size());
		assertsInsert(afterInsert);
	}
	
	@Test
	void testSaveOrUpdateUpdate() throws DaoException{
		T object = getUpdateObject();
		boolean ret = dao.saveOrUpdate(object);
		T res = dao.getFromBothKeys(getFirstKey(object), getSecondKey(object));
		assertNotNull(res);
		assertEquals(object, res);
		assertsUpdate(res);

		
		List<T> afterUpdate = dao.getAllForFirstKey(getFirstKey(object));
		
		object = afterUpdate.get(1);
		ret = dao.saveOrUpdate(object);
		assertFalse(ret);
	}
	
	@Test
	void testSaveOrUpdateCutConn() throws DaoException{
		try {
			cutConnection();

		}catch (SQLException e) {
			fail("Not able to cut connection");
		}
		assertThrows(DaoException.class, ()->{
			dao.saveOrUpdate(getDeleteObject());
		});	
	}

}
