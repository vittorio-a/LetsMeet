package it.unisa.studenti.letsmeet.manager;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.util.List;


import it.unisa.studenti.letsmeet.model.PartecipazioneBean;

class PartecipazioneSqlDaoTest extends SqlDaoDoubleKeyTest<PartecipazioneBean> {

	public PartecipazioneSqlDaoTest() {
		super.firstKey = 24;
		super.secondKey = 13;
	}
	
	@Override
	protected SqlDaoDoubleKey<PartecipazioneBean> getDao(Connection conn) {
		return new PartecipazioneSqlDao(conn);
	}

	
	private static final int SIZE_1 = 2, SIZE_2 = 3;
	
	@Override
	protected void assertsGetBothKey(PartecipazioneBean test) {
		assertEquals(super.secondKey, test.getIdEvento());
		assertEquals(super.firstKey, test.getIdUtente());
		assertEquals(false, test.isVerificato());
	}

	@Override
	protected void assertsGetFirstKey(List<PartecipazioneBean> test) {
		assertEquals(SIZE_1, test.size());
	}

	@Override
	protected void assertsGetSecondKey(List<PartecipazioneBean> test) {
		assertEquals(SIZE_2, test.size());
		
	}

	@Override
	protected PartecipazioneBean getInsertObject() {
		PartecipazioneBean bean = new PartecipazioneBean();
		bean.setIdEvento(1);
		bean.setIdUtente(1);
		bean.setVerificato(false);
		return bean;
	}

	@Override
	protected PartecipazioneBean getUpdateObject() {
		PartecipazioneBean bean = new  PartecipazioneBean();
		bean.setIdEvento(super.secondKey);
		bean.setIdUtente(super.firstKey);
		bean.setVerificato(true);
		return bean;
	}

	@Override
	protected void assertsUpdate(PartecipazioneBean test) {
		
	}

	@Override
	protected void assertsInsert(List<PartecipazioneBean> test) {
		assertEquals(SIZE_1 + 1, test.size());
	}

	@Override
	protected PartecipazioneBean getDeleteObject() {
		PartecipazioneBean bean = new  PartecipazioneBean();
		bean.setIdEvento(super.secondKey);
		bean.setIdUtente(super.firstKey);
		return bean;	
	}

	@Override
	protected void assertsDelete(List<PartecipazioneBean> test) {
		assertEquals(SIZE_1 - 1, test.size());
	}

	@Override
	protected PartecipazioneBean getDeleteFalse() {
		PartecipazioneBean bean = new  PartecipazioneBean();
		bean.setIdEvento(Integer.MAX_VALUE);
		bean.setIdUtente(Integer.MAX_VALUE);
		return bean;		}

	@Override
	protected int getFirstKey(PartecipazioneBean item) {
		return item.getIdUtente();
	}

	@Override
	protected int getSecondKey(PartecipazioneBean item) {
		return item.getIdEvento();
	}

}
