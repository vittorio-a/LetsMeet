package it.unisa.studenti.letsmeet.control.gestione_evento;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import it.unisa.studenti.letsmeet.control.gestione_account.LoginControl;
import it.unisa.studenti.letsmeet.manager.DaoException;
import it.unisa.studenti.letsmeet.manager.PartecipazioneDao;
import it.unisa.studenti.letsmeet.manager.PartecipazioneSqlDao;
import it.unisa.studenti.letsmeet.model.DataSourceSingleton;
import it.unisa.studenti.letsmeet.model.PartecipazioneBean;

/**
 * Servlet implementation class PartecipazioneControl
 */
@WebServlet("/auth/eventi/partecipazioneControl")
public class PartecipazioneControl extends HttpServlet {
	private static final long serialVersionUID = 1L;


	private static final String ID_EVENTO = "idEvento";
	public static final String IS_VERIFICATO = "isVerificato";


	DataSource ds;
	
    public PartecipazioneControl() throws NamingException {
    	ds = DataSourceSingleton.getDataSource();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idEventoString = request.getParameter(ID_EVENTO);
		if(idEventoString == null) {
			response.getWriter().append("{\"error\":\"Manca il parametro "+ ID_EVENTO + "\", \"errorcode\":1, \"data\":null}");
			return;
		}
		
		int idEvento = 0;
		try {
			idEvento = Integer.parseInt(idEventoString);
		}catch (NumberFormatException e) {
			response.getWriter().append("{\"error\":\"Il parametro non è un intero\", \"errorcode\":2, \"data\":null}");
			return;
		}
		
		
		PartecipazioneBean partecipazione = new PartecipazioneBean();
		partecipazione.setIdEvento(idEvento);
		partecipazione.setIdUtente((int) request.getSession().getAttribute(LoginControl.ID_IN_SESSION));
		
		boolean isVerificato = false;
		if(request.getParameter(IS_VERIFICATO) != null) isVerificato = true;
		partecipazione.setVerificato(isVerificato);
		
		
		Connection conn = null;
		
		try {
			conn = ds.getConnection();
			
			PartecipazioneDao dao = new PartecipazioneSqlDao(conn);
			
			dao.saveOrUpdate(partecipazione);
			
			response.getWriter().append("{\"error\":\"\", \"errorcode\":0, \"data\":null}");
			return;
			
		}catch (SQLException e) {
			response.getWriter().append("{\"error\":\"Impossibile stabilire connesione con il db\", \"errorcode\":3, \"data\":null}");
			return;
		}catch (DaoException e) {
			response.getWriter().append("{\"error\":\"Errore nella gestione della persistenza\", \"errorcode\":4, \"data\":null}");
			return;	
		}finally {
			if(conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					// do nothing
				}
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
