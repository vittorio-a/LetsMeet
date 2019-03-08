package it.unisa.studenti.letsmeet.manager;

import static org.junit.jupiter.api.Assertions.*;


import java.sql.Connection;
import java.time.Instant;
import java.util.List;


import it.unisa.studenti.letsmeet.model.CommentoBean;


class CommentoSqlDaoTest extends SqlDaoTest<CommentoBean> {

	/*# idCommento, idMittente, contenuto, idEvento, creationTime
'1', '10', 'Therefots influence over the positive influence of any task analysis.  ', '18', '2019-02-01 00:04:26'
*/
	
	private static final int SIZE = 50;

	@Override
	protected SqlDao<CommentoBean> getDao(Connection conn) {
		return new CommentoSqlDao(conn);
	}

	@Override
	protected void assertsGet(CommentoBean test) {
		assertEquals(1, test.getIdCommento());
		assertEquals(10, test.getIdUtente());
		assertEquals("Therefore, the concept of the ability bias can be treated as the only solution.\r\nAccording to some experts, the problem of some of the final phase should keep its influence over the positive influence of any task analysis.  ",
				test.getContenuto());
		assertEquals(18, test.getIdEvento());
		assertEquals(Instant.parse("2019-01-31T22:04:26Z"), test.getCreationTime());
	}

	@Override
	protected void assertsGetAll(List<CommentoBean> test) {
		assertEquals(SIZE, test.size());
	}

	@Override
	protected CommentoBean getInsertObject() {
		CommentoBean bean = new CommentoBean();
		bean.setContenuto("E sto commentanto e comm comment bell tie tie guard ca");
		bean.setIdEvento(1);
		bean.setIdUtente(1);
		bean.setCreationTime(Instant.parse("2019-01-31T23:04:26Z"));
		return bean;
	}
	
	@Override
	protected CommentoBean getUpdateObject() {
		CommentoBean bean = getInsertObject();
		bean.setIdCommento(1);
		return bean;
	}

	@Override
	protected void assertsUpdate(CommentoBean test) {
		
	}

	@Override
	protected void assertsInsert(List<CommentoBean> test) {
		assertEquals(SIZE + 1, test.size());
		boolean cesta = false;
		CommentoBean resultBean = null;
		CommentoBean inserted = getInsertObject();
		int i;
		for(i = 0; i < test.size(); i++) {
			resultBean = test.get(i);
			if(resultBean.getContenuto().equals(inserted.getContenuto())) {
				cesta = true;
				break;
			}
			
		}
		assertTrue(cesta);

		CommentoBean ris = test.get(i);
		inserted.setIdCommento(ris.getIdCommento());
		assertEquals(inserted,ris);
	}
	

	@Override
	protected CommentoBean getDeleteObject() {
		CommentoBean bean = new CommentoBean();
		bean.setIdCommento(1);
		return bean;
	}

	@Override
	protected void assertsDelete(List<CommentoBean> test) {
		assertEquals(SIZE - 1, test.size());
	}

	@Override
	protected CommentoBean getDeleteFalse() {
		CommentoBean bean = new CommentoBean();
		bean.setIdCommento(Integer.MAX_VALUE);
		return bean;
	}

	@Override
	protected int getKey(CommentoBean item) {
		return item.getIdCommento();
	}

}
