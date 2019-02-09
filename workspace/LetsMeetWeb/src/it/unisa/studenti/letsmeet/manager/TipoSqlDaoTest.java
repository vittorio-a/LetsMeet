package it.unisa.studenti.letsmeet.manager;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import it.unisa.studenti.letsmeet.model.TipoBean;

class TipoSqlDaoTest {

	private Connection conn;
	private TipoSqlDao dao;
	
	
	private static final int SIZE = 7;

	@BeforeEach
	void setUp() throws Exception {
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/letsmeet", "root", "Nc3Dug2SKVofEBJrhE3x");
		conn.setAutoCommit(false);
		dao = new TipoSqlDao(conn);
	}

	@AfterEach
	void tearDown() throws Exception {
		conn.rollback();
		conn.close();
	}

	@Test
	void testGet() throws DaoException{
		TipoBean tipo = dao.get(1);
		assertNotNull(tipo);
		assertEquals(tipo.getIdTipo(), 1);
		assertEquals(tipo.getNomeTipo(), "Epioxevir");
		assertEquals(tipo.getDescrizione(), "It should rather be regarded as an integral part of the development sequence.");
	}

	@Test
	void testGetAll() throws DaoException{		
		List<TipoBean> tipos = dao.getAll();
		assertNotNull(tipos);
		assertEquals(tipos.size(), SIZE);
	}

	@Test
	void testSaveOrUpdate() throws DaoException {
		TipoBean tipo = new TipoBean();
		tipo.setDescrizione("Nu bellu tipo");
		tipo.setNomeTipo("Frischezza");
		boolean ret = dao.saveOrUpdate(tipo);
		assertTrue(ret);
		
		List<TipoBean> resultBeans = dao.getAll();
		assertNotNull(resultBeans);
		assertEquals(resultBeans.size(), SIZE + 1);
		boolean cesta = false;
		TipoBean resultBean = null;
		for(int i = 0; i < resultBeans.size(); i++) {
			resultBean = resultBeans.get(i);
			if(resultBean.getNomeTipo().equals("Frischezza")) {
				cesta = true;
				break;
			}
			
		}
		assertTrue(cesta);
		assertEquals(tipo.getDescrizione(), resultBean.getDescrizione());
		assertEquals(tipo.getNomeTipo(), resultBean.getNomeTipo());
		
		
		tipo.setIdTipo(2);
		dao.saveOrUpdate(tipo);
		resultBean = dao.get(2);
		assertEquals(tipo.getDescrizione(), resultBean.getDescrizione());
		assertEquals(tipo.getNomeTipo(), resultBean.getNomeTipo());
				
	}

	@Test
	void testDelete() throws DaoException{
		TipoBean tipo = new TipoBean();
		tipo.setIdTipo(1);
		dao.delete(tipo);
		List<TipoBean> tipi = dao.getAll();
		assertEquals(tipi.size(), SIZE - 1);
		
	}

}
