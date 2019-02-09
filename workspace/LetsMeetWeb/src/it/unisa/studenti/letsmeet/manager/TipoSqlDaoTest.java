package it.unisa.studenti.letsmeet.manager;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.util.List;


import it.unisa.studenti.letsmeet.model.TipoBean;

class TipoSqlDaoTest extends SqlDaoTest<TipoBean>{

	private static final int SIZE = 7;
	
	@Override
	protected SqlDao<TipoBean> getDao(Connection conn) {
		return new TipoSqlDao(conn);
	}

	@Override
	protected void assertsGet(TipoBean test) {
		assertEquals(1, test.getIdTipo());
		assertEquals("Epioxevir", test.getNomeTipo());
		assertEquals("It should rather be regarded as an integral part of the development sequence.", test.getDescrizione());

	}

	@Override
	protected void assertsGetAll(List<TipoBean> test) {
		assertEquals(SIZE, test.size());		
	}

	@Override
	protected TipoBean getInsertObject() {
		TipoBean tipo = new TipoBean();
		tipo.setDescrizione("Nu bellu tipo");
		tipo.setNomeTipo("Frischezza");
		return tipo;
	}

	@Override
	protected TipoBean getUpdateObject() {
		TipoBean tipo = getInsertObject();
		tipo.setIdTipo(2);
		return tipo;
	}

	@Override
	protected void assertsUpdate(TipoBean test) {
	}


	@Override
	protected void assertsInsert(List<TipoBean> test) {
		assertEquals(test.size(), SIZE + 1);
		boolean cesta = false;
		TipoBean resultBean = null;
		for(int i = 0; i < test.size(); i++) {
			resultBean = test.get(i);
			if(resultBean.getNomeTipo().equals("Frischezza")) {
				cesta = true;
				break;
			}
			
		}
		assertTrue(cesta);
		TipoBean tipo = getInsertObject();
		assertEquals(tipo.getDescrizione(), resultBean.getDescrizione());
		assertEquals(tipo.getNomeTipo(), resultBean.getNomeTipo());
	}

	@Override
	protected TipoBean getDeleteObject() {
		TipoBean tipo = new TipoBean();
		tipo.setIdTipo(1);
		return tipo;
	}

	@Override
	protected void assertsDelete(List<TipoBean> test) {
		assertEquals(test.size(), SIZE - 1);
	}

	@Override
	protected TipoBean getDeleteFalse() {
		TipoBean tipo = new TipoBean();
		tipo.setIdTipo(5555);
		return tipo;
	}

	@Override
	protected int getKey(TipoBean item) {
		return item.getIdTipo();
	}

}
