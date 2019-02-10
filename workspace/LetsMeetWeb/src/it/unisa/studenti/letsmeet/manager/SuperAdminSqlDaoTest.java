package it.unisa.studenti.letsmeet.manager;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.util.List;

import javax.xml.bind.DatatypeConverter;

import org.apache.catalina.tribes.util.Arrays;
import org.junit.jupiter.api.Test;

import it.unisa.studenti.letsmeet.model.SuperAdminBean;
import it.unisa.studenti.letsmeet.model.UtenteBean;

class SuperAdminSqlDaoTest extends SqlDaoTest<SuperAdminBean> {

//'1', 'Michael15',
	
	private static final int SIZE = 2;
	
	
	@Override
	protected SqlDao<SuperAdminBean> getDao(Connection conn) {
		return new SuperAdminSqlDao(conn);
	}

	@Override
	protected void assertsGet(SuperAdminBean test) {
		assertEquals(1, test.getIdSuperAdmin());
		byte[] psw = DatatypeConverter.parseHexBinary("B1A200533D3225D3001A80A24BD103BD3304B4D97E121F4AA607620845BEA798");
		assertTrue(Arrays.equals(psw, test.getPassword()));
		assertEquals("Michael15", test.getUsername());
	}

	@Override
	protected void assertsGetAll(List<SuperAdminBean> test) {
		assertEquals(test.size(), SIZE);				
	}

	@Override
	protected SuperAdminBean getInsertObject() {
		SuperAdminBean bean = new SuperAdminBean();
		bean.setUsername("adminTest");
		MessageDigest digest = null;
		try {
			digest = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			fail("Unable to compute hash");
		}
		
		byte[] psw = digest.digest("password admin".getBytes(StandardCharsets.UTF_8));
		bean.setPassword(psw);
		return bean;
	}

	@Override
	protected SuperAdminBean getUpdateObject() {
		SuperAdminBean bean = getInsertObject();
		bean.setIdSuperAdmin(1);
		return bean;
	}

	@Override
	protected void assertsUpdate(SuperAdminBean test) {		
	}

	@Override
	protected void assertsInsert(List<SuperAdminBean> test) {
		assertEquals(SIZE + 1, test.size());
		SuperAdminBean resultBean = null;
		SuperAdminBean utenteInsert = getInsertObject();
		boolean cesta = false;
		int i;
		for(i = 0; i < test.size(); i++) {
			resultBean = test.get(i);
			if(resultBean.getUsername().equals(utenteInsert.getUsername())) {
				cesta = true;
				break;
			}
			
		}
		assertTrue(cesta);
		assertEquals(utenteInsert, test.get(i));
	}
	

	@Override
	protected SuperAdminBean getDeleteObject() {
		SuperAdminBean bean = new SuperAdminBean();
		bean.setIdSuperAdmin(1);
		return bean;
	}

	@Override
	protected void assertsDelete(List<SuperAdminBean> test) {
		assertEquals(test.size(), SIZE - 1);				
	}

	@Override
	protected SuperAdminBean getDeleteFalse() {
		SuperAdminBean bean = new SuperAdminBean();
		bean.setIdSuperAdmin(Integer.MAX_VALUE);
		return bean;
	}

	@Override
	protected int getKey(SuperAdminBean item) {
		return item.getIdSuperAdmin();
	}

}
