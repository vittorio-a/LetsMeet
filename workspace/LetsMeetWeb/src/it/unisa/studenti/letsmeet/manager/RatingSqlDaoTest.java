package it.unisa.studenti.letsmeet.manager;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Connection;
import java.util.List;


import it.unisa.studenti.letsmeet.model.RatingBean;

class RatingSqlDaoTest extends SqlDaoDoubleKeyTest<RatingBean> {
	
	private static final int SIZE_1 = 2, SIZE_2 = 3;
	
	public RatingSqlDaoTest() {
		super.firstKey = 1;
		super.secondKey = 6;
	}

	@Override
	protected SqlDaoDoubleKey<RatingBean> getDao(Connection conn) {
		return new RatingSqlDao(conn);
	}

	@Override
	protected void assertsGetBothKey(RatingBean test) {
		assertEquals(true, test.isVoto());
		assertEquals(super.firstKey, test.getIdutente());
		assertEquals(super.secondKey,test.getEvento());
	}

	@Override
	protected void assertsGetFirstKey(List<RatingBean> test) {
		assertEquals(SIZE_1, test.size());
	}

	@Override
	protected void assertsGetSecondKey(List<RatingBean> test) {
		assertEquals(SIZE_2, test.size());
	}

	@Override
	protected RatingBean getInsertObject() {
		RatingBean bean = new RatingBean();
		bean.setIdutente(1);
		bean.setEvento(1);
		bean.setVoto(false);
		return bean;
	}

	@Override
	protected RatingBean getUpdateObject() {
		RatingBean bean = new RatingBean();
		bean.setIdutente(super.firstKey);
		bean.setEvento(super.secondKey);
		bean.setVoto(false);
		return bean;

	}

	@Override
	protected void assertsUpdate(RatingBean test) {		
	}

	@Override
	protected void assertsInsert(List<RatingBean> test) {
		assertEquals(SIZE_1 + 1, test.size());
	}

	@Override
	protected RatingBean getDeleteObject() {
		RatingBean bean = new RatingBean();
		bean.setIdutente(super.firstKey);
		bean.setEvento(super.secondKey);
		return bean;	
	}

	@Override
	protected void assertsDelete(List<RatingBean> test) {
		assertEquals(SIZE_1 - 1, test.size());
	}

	@Override
	protected RatingBean getDeleteFalse() {
		RatingBean bean = new RatingBean();
		bean.setIdutente(Integer.MAX_VALUE);
		bean.setEvento(Integer.MAX_VALUE);
		return bean;	
	}

	@Override
	protected int getFirstKey(RatingBean item) {
		return item.getIdutente();
	}

	@Override
	protected int getSecondKey(RatingBean item) {
		return item.getEvento();
	}

}
