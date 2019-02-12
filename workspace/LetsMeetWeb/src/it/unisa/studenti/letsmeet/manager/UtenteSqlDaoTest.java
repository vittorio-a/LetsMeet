package it.unisa.studenti.letsmeet.manager;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.time.Instant;
import java.util.List;


import javax.xml.bind.DatatypeConverter;

import org.apache.catalina.tribes.util.Arrays;

import it.unisa.studenti.letsmeet.model.CredentialsBean;
import it.unisa.studenti.letsmeet.model.StatoUtente;
import it.unisa.studenti.letsmeet.model.UtenteBean;

class UtenteSqlDaoTest extends SqlDaoTest<UtenteBean> {


	//'1', 'Rhett696', ?, 'Rayford917@example.com', '0', 'BANNATO', '2019-02-28 22:02:59'
//'136AA268594E3A7754400C2AB9E042856287F006FE13078463006B2C7BE55ACF'

	private static final int SIZE = 50;
	
	
	@Override
	protected SqlDao<UtenteBean> getDao(Connection conn) {
		return new UtenteSqlDao(conn);
	}

	@Override
	protected void assertsGet(UtenteBean test) {
		assertEquals(1, test.getIdUtente());
		CredentialsBean creds = test.getCredentials();
		assertEquals("Rhett696", creds.getUsername());
		byte[] psw = DatatypeConverter.parseHexBinary("136AA268594E3A7754400C2AB9E042856287F006FE13078463006B2C7BE55ACF");
		assertTrue(Arrays.equals(psw, creds.getPassword()));
		assertEquals(StatoUtente.BANNATO, creds.getState());
		assertEquals("Rayford917@example.com", test.getEmail());
		assertEquals(0, test.getFeedbackUtente());
		Instant date = Instant.parse("2019-02-28T21:02:59Z");
		assertEquals(date, test.getReactivationDate());
	}

	@Override
	protected void assertsGetAll(List<UtenteBean> test) {
		assertEquals(test.size(), SIZE);		
		
	}

	@Override
	protected UtenteBean getInsertObject() {
		UtenteBean utente = new UtenteBean();
		CredentialsBean creds = new CredentialsBean();
		creds.setState(StatoUtente.ATTIVO);
		creds.setUsername("userTest");
		
		MessageDigest digest = null;
		try {
			digest = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			fail("Unable to compute hash");
		}
		byte[] hashedPsw = digest.digest("passwordTest".getBytes(StandardCharsets.UTF_8));
		creds.setPassword(hashedPsw);
		utente.setCredentials(creds);
		utente.setEmail("test@mail.test");
		utente.setFeedbackUtente(new BigDecimal("7"));
		utente.setReactivationDate(Instant.parse("2020-01-01T00:00:00Z"));
		return utente;
	}

	@Override
	protected UtenteBean getUpdateObject() {
		UtenteBean utente = getInsertObject();
		utente.setIdUtente(1);
		return utente;
	}

	@Override
	protected void assertsUpdate(UtenteBean test) {
	}

	@Override
	protected void assertsInsert(List<UtenteBean> test) {
		assertEquals(SIZE + 1, test.size());
		UtenteBean resultBean = null;
		UtenteBean utenteInsert = getInsertObject();
		boolean cesta = false;
		int i;
		for(i = 0; i < test.size(); i++) {
			resultBean = test.get(i);
			if(resultBean.getCredentials().getUsername().equals(utenteInsert.getCredentials().getUsername())) {
				cesta = true;
				break;
			}
			
		}
		assertTrue(cesta);
		assertEquals(utenteInsert, test.get(i));
	}

	@Override
	protected UtenteBean getDeleteObject() {
		UtenteBean utente = new UtenteBean();
		utente.setIdUtente(1);
		return utente;
	}

	@Override
	protected void assertsDelete(List<UtenteBean> test) {
		assertEquals(test.size(), SIZE - 1);		
	}

	@Override
	protected UtenteBean getDeleteFalse() {
		UtenteBean utente = new UtenteBean();
		utente.setIdUtente(Integer.MAX_VALUE);
		return utente;
	}

	@Override
	protected int getKey(UtenteBean item) {
		return item.getIdUtente();
	}

}
