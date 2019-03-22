package it.unisa.studenti.letsmeet.manager.unit;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.sql.Connection;
import java.time.Instant;
import java.util.List;

import it.unisa.studenti.letsmeet.manager.EventoSqlDao;
import it.unisa.studenti.letsmeet.manager.SqlDao;
import it.unisa.studenti.letsmeet.model.EventoBean;
import it.unisa.studenti.letsmeet.model.PosizioneBean;
import it.unisa.studenti.letsmeet.model.TipoBean;

class EventoSqlDaoTest extends SqlDaoTest<EventoBean> {

	private static final int SIZE = 47;
	
	@Override
	protected SqlDao<EventoBean> getDao(Connection conn) {
		return new EventoSqlDao(conn);
	}

	@Override
	protected void assertsGet(EventoBean test) {
		assertEquals(1, test.getIdEvento());
		assertEquals("Such tendency may approximately originate from the base configuration.", test.getNome());
		assertTrue(new BigDecimal(4f).compareTo(test.getFeedback()) == 0);
		assertEquals(1, test.getnPartecipanti());
		assertEquals(1, test.getnVerificati());
		assertEquals(Instant.parse("2019-02-07T13:22:40Z"), test.getOraInizio());
		assertEquals(Instant.parse("2019-02-07T22:33:38Z"), test.getOraFine());
		assertEquals(3, test.getIdUtente());
		assertEquals(6, test.getTipo().getIdTipo());
		assertEquals(44, test.getPosizione().getId());
		assertEquals(true, test.isVisible());
		
		
	}

	@Override
	protected void assertsGetAll(List<EventoBean> test) {
		assertEquals(SIZE, test.size());
	}

	@Override
	protected EventoBean getInsertObject() {
		EventoBean bean = new EventoBean();
		bean.setIdUtente(1);
		bean.setFeedback(new BigDecimal("5.0"));
		PosizioneBean posizione = getNewPosition();
		bean.setPosizione(posizione);
		bean.setnPartecipanti(0);
		bean.setnVerificati(0);
		bean.setOraInizio(Instant.parse("2019-02-07T14:22:40Z"));
		bean.setOraFine(Instant.parse("2019-02-07T14:22:41Z"));
		bean.setNome("Un bello evento");
		bean.setDescrizione("Na bella descrizione overamente");
		bean.setTipo(getNewTipo());
		bean.setVisible(true);
		return bean;
		
	}
	
	private PosizioneBean getNewPosition() {		
		return PosizioneSqlDaoTest.getInsertPosition();
	}
	
	private TipoBean getNewTipo() {		
		return TipoSqlDaoTest.getInsertTipo();
	}

	@Override
	protected EventoBean getUpdateObject() {
		EventoBean bean = getInsertObject();
		bean.setIdEvento(1);
		bean.setnPartecipanti(1);
		bean.setnVerificati(1);
		return bean;
	}

	@Override
	protected void assertsUpdate(EventoBean test) {
		
	}

	@Override
	protected void assertsInsert(List<EventoBean> test) {
		assertEquals(SIZE + 1, test.size());
		boolean cesta = false;
		EventoBean resultBean = null;
		EventoBean inserted = getInsertObject();
		int i;
		for(i = 0; i < test.size(); i++) {
			resultBean = test.get(i);
			if(resultBean.getNome().equals(inserted.getNome())) {
				cesta = true;
				break;
			}
			
		}
		assertTrue(cesta);
		
		EventoBean eventoInDb = test.get(i);
		inserted.setIdEvento(eventoInDb.getIdEvento());
		inserted.getTipo().setIdTipo(eventoInDb.getTipo().getIdTipo());
		PosizioneBean posizioneInserted = inserted.getPosizione();
		PosizioneBean posizioneInDb = eventoInDb.getPosizione();
		posizioneInserted.setId(posizioneInDb.getId());
		posizioneInserted.setIdComune(posizioneInDb.getIdComune());
		posizioneInserted.setIdProvincia(posizioneInDb.getIdProvincia());
		posizioneInserted.setIdRegione(posizioneInDb.getIdRegione());
		posizioneInserted.setIdNazione(posizioneInDb.getIdNazione());
		assertEquals(inserted,eventoInDb);		
	}

	@Override
	protected EventoBean getDeleteObject() {
		EventoBean bean = new EventoBean();
		bean.setIdEvento(1);
		return bean;	
	}

	@Override
	protected void assertsDelete(List<EventoBean> test) {
		assertEquals(SIZE - 1, test.size());

	}

	@Override
	protected EventoBean getDeleteFalse() {
		EventoBean bean = new EventoBean();
		bean.setIdEvento(Integer.MAX_VALUE);
		return bean;		}

	@Override
	protected int getKey(EventoBean item) {
		return item.getIdEvento();
	}

	//TODO add testig per nuova funzione

}
