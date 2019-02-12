package it.unisa.studenti.letsmeet.manager;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.List;


import it.unisa.studenti.letsmeet.model.PosizioneBean;

class PosizioneSqlDaoTest extends SqlDaoTest<PosizioneBean> {

	private static final int SIZE = 50;
	
	@Override
	protected SqlDao<PosizioneBean> getDao(Connection conn) {
		return new PosizioneSqlDao(conn);
	}

	@Override
	protected void assertsGet(PosizioneBean test) {
		assertEquals(1, test.getId());
		assertEquals(1, test.getIdNazione());
		assertEquals(2, test.getIdRegione());
		assertEquals(20, test.getIdProvincia());
		assertEquals(24, test.getIdComune());
		assertEquals(new BigDecimal("66.52596"), test.getLongitudine());
		assertEquals(new BigDecimal("3.92754"), test.getLatitudine());
		assertEquals("2 Beaumont Court", test.getFormattedAdress());
		assertEquals("Warragul", test.getNomeComune());
		assertEquals("New Brunswick", test.getNomeProvincia());
		assertEquals("EB", test.getSigla());
		assertEquals("South Dakota", test.getNomeRegione());
		assertEquals("Italia", test.getNomeNazione());
	}

	@Override
	protected void assertsGetAll(List<PosizioneBean> test) {
		assertEquals(SIZE, test.size());				
	}

	static public PosizioneBean getInsertPosition() {
		return new PosizioneSqlDaoTest().getInsertObject();
	}

	@Override
	protected PosizioneBean getInsertObject() {
		PosizioneBean posizione = new PosizioneBean();
		posizione.setFormattedAdress("15 ComuneTest Roma Lazio Italia");
		posizione.setNomeComune("Roma");
		posizione.setNomeProvincia("Roma");
		posizione.setSigla("RO");
		posizione.setLatitudine(new BigDecimal("0.0"));
		posizione.setLongitudine(new BigDecimal("0.0"));
		posizione.setNomeRegione("Lazio");
		posizione.setIdNazione(1);
		posizione.setNomeNazione("Italia");
		
		return posizione;
	}
	
	
	@Override
	protected PosizioneBean getUpdateObject() {
		PosizioneBean posizione = getInsertObject();
		posizione.setId(1);
		posizione.setIdComune(1);
		posizione.setIdProvincia(1);
		posizione.setIdRegione(1);
		posizione.setNomeNazione("Itaglia");
		return posizione;
	}

	@Override
	protected void assertsUpdate(PosizioneBean test) {		
	}

	@Override
	protected void assertsInsert(List<PosizioneBean> test) {
		assertEquals(SIZE + 1, test.size());
		boolean cesta = false;
		PosizioneBean resultBean = null;
		PosizioneBean inserted = getInsertObject();
		int i;
		for(i = 0; i < test.size(); i++) {
			resultBean = test.get(i);
			if(resultBean.getFormattedAdress().equals(inserted.getFormattedAdress())) {
				cesta = true;
				break;
			}
			
		}
		assertTrue(cesta);
		assertEquals(inserted,test.get(i));
	}

	@Override
	protected PosizioneBean getDeleteObject() {
		PosizioneBean posizione = new PosizioneBean();
		posizione.setId(1);
		return posizione;
	}

	@Override
	protected void assertsDelete(List<PosizioneBean> test) {
		assertEquals(SIZE - 1, test.size());
	}

	@Override
	protected PosizioneBean getDeleteFalse() {
		PosizioneBean posizione = new PosizioneBean();
		posizione.setId(Integer.MAX_VALUE);
		return posizione;		
	}

	@Override
	protected int getKey(PosizioneBean item) {
		return item.getId();
	}

}
