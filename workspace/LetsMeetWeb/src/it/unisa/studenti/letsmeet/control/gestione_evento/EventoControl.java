package it.unisa.studenti.letsmeet.control.gestione_evento;

import java.io.IOException;
import java.math.BigDecimal;

import java.sql.SQLException;
import java.time.Instant;
import java.time.format.DateTimeParseException;
import java.util.Enumeration;


import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.google.gson.Gson;

import it.unisa.studenti.letsmeet.control.gestione_account.LoginControl;
import it.unisa.studenti.letsmeet.manager.DaoException;
import it.unisa.studenti.letsmeet.manager.EventoSqlDao;
import it.unisa.studenti.letsmeet.manager.TipoSqlDao;
import it.unisa.studenti.letsmeet.model.DataSourceSingleton;
import it.unisa.studenti.letsmeet.model.EventoBean;
import it.unisa.studenti.letsmeet.model.PosizioneBean;
import it.unisa.studenti.letsmeet.model.TipoBean;
import it.unisa.studenti.letsmeet.model.UtenteBean;

/**
 * Servlet implementation class eventoControl
 */
@WebServlet("/auth/eventi/eventoControl")
public class EventoControl extends HttpServlet {
	private static final long serialVersionUID = 1L;


	private static final String NOME_EVENTO = "nome";
	private static final String ORA_INIZIO = "ora_inizio";
	private static final String ORA_FINE = "ora_fine";
	private static final String DESCRIZIONE = "descrizione";
	private static final String POSIZIONE = "posizione";
	private static final String TIPO = "tipo_evento";
	public static final String ATTRIBUTO_UTENTE = "id_utente";

	
	private static final int NUM_PARAM = 6;


	
	private static DataSource ds = null;
	private static Gson gson = new Gson();
	
	
    /**
     * Default constructor. 
     * @throws NamingException 
     */
    public EventoControl() throws NamingException {
    	ds = DataSourceSingleton.getDataSource();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Enumeration<String> parNames = request.getParameterNames();
		boolean[] params = new boolean[NUM_PARAM];
		for(int i = 0; i < NUM_PARAM; i++) {
			params[i] = false;
		}
		while(parNames.hasMoreElements()) {
			switch (parNames.nextElement()) {
			case NOME_EVENTO:
				params[0] = true;
				break;
			case ORA_INIZIO:
				params[1] = true;
				break;
			case ORA_FINE:
				params[2] = true;
				break;
			case DESCRIZIONE:
				params[3] = true;
				break;
			case TIPO:
				params[4] = true;
				break;
			case POSIZIONE:
				params[5] = true;
				break;
			default:
				break;	
			}
		}
		
		boolean ciStannoTuttiIParametri = true;
		for(int i = 0; i < NUM_PARAM; i++) {
			if(!params[i]) {
				ciStannoTuttiIParametri = false;
				break;
			}
		}
		if(!ciStannoTuttiIParametri) {
			response.getWriter().append("{\"error\":\"Mancano uno o più parametri\", \"errorcode\":1, \"data\":null}");
			return;
		}
		
		PosizioneBean posizione = new PosizioneBean();
		posizione = gson.fromJson(request.getParameter(POSIZIONE), PosizioneBean.class);
		
		String indirizzo, comune, provincia, sigla, regione, nazione, nome, oraInizioString, oraFineString, descrizione, tipoString;
		indirizzo = posizione.getFormattedAdress();
		comune = posizione.getNomeComune();
		provincia = posizione.getNomeProvincia();
		sigla = posizione.getSigla();
		regione = posizione.getNomeRegione();
		nazione = posizione.getNomeNazione();
		nome = request.getParameter(NOME_EVENTO);
		oraInizioString = request.getParameter(ORA_INIZIO);
		oraFineString = request.getParameter(ORA_FINE);
		descrizione = request.getParameter(DESCRIZIONE);
		tipoString = request.getParameter(TIPO);
		
		Instant oraInizio = null, oraFine = null;
		int idTipo = 0;
		try {
			oraInizio = Instant.parse(oraInizioString);
			oraFine = Instant.parse(oraFineString);
			idTipo = Integer.parseInt(tipoString);
		}
		catch (DateTimeParseException e) {
			response.getWriter().append("{\"error\":\"Errore nel parsing delle date\", \"errorcode\":2, \"data\":null}");
			return;
		}catch (NumberFormatException e) {
			response.getWriter().append("{\"error\":\"Errore nel parsing dell'id del tipo\", \"errorcode\":3, \"data\":null}");
			return;
		}
		
		
		if(indirizzo == null ||
				indirizzo.equals("") ||
				comune == null ||
				comune.equals("") ||
				provincia == null ||
				provincia.equals("") ||
				sigla == null ||
				sigla.equals("") ||
				regione == null ||
				regione.equals("") ||
				nazione == null ||
				nazione.equals("") ||
				nome.equals("") ||
				descrizione.equals("")) {
			
			response.getWriter().append("{\"error\":\"Uno o più parametri non corretti\", \"errorcode\":4, \"data\":null}");
			return;
		
		}
		
		
		
		int idUtente = (int)(request.getSession().getAttribute(LoginControl.ID_IN_SESSION));
		
		java.sql.Connection conn = null;
		EventoBean eventoBean = new EventoBean();
		eventoBean.setNome(nome);
		eventoBean.setDescrizione(descrizione);
		eventoBean.setFeedback(new BigDecimal("0"));
		eventoBean.setIdUtente(idUtente);

		eventoBean.setnPartecipanti(0);
		eventoBean.setnVerificati(0);
		eventoBean.setOraInizio(oraInizio);
		eventoBean.setOraFine(oraFine);
		eventoBean.setPosizione(posizione);
		eventoBean.setVisible(true);
		
	
		try {
			conn = ds.getConnection();
			TipoSqlDao tipoDao = new TipoSqlDao(conn);
			TipoBean tipo = tipoDao.get(idTipo);
			if(tipo == null) {

				return;
			}
			
			eventoBean.setTipo(tipo);
			EventoSqlDao dao = new EventoSqlDao(conn);
			dao.saveOrUpdate(eventoBean);
			response.getWriter().append("{\"error\":\"\", \"errorcode\":0, \"data\":null}");
			
		}catch (DaoException e) {
			
			
		} catch (SQLException e) {

		}finally {
			if(conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					//do nothing
				}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
