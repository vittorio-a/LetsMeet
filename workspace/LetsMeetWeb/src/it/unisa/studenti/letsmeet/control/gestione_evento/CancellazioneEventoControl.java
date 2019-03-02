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

import it.unisa.studenti.letsmeet.manager.DaoException;
import it.unisa.studenti.letsmeet.manager.EventoDao;
import it.unisa.studenti.letsmeet.manager.EventoSqlDao;
import it.unisa.studenti.letsmeet.model.DataSourceSingleton;
import it.unisa.studenti.letsmeet.model.EventoBean;

/**
 * Servlet implementation class CancellazioneEventoControl
 */
@WebServlet("/CancellazioneEventoControl")
public class CancellazioneEventoControl extends HttpServlet {
	private static final long serialVersionUID = 1L;


	private static final String ID_EVENTO = "idEvento";

   
	DataSource ds;
	
    public CancellazioneEventoControl() throws NamingException {
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
		
		Connection conn = null;
		
		try {
			conn = ds.getConnection();
			EventoDao dao = new EventoSqlDao(conn);
			EventoBean evento = new EventoBean();
			evento.setIdEvento(idEvento);
			boolean status = dao.delete(evento);
			if(status) {
				response.getWriter().append("{\"error\":\"\", \"errorcode\":0, \"data\":null}");
			}else {
				response.getWriter().append("{\"error\":\"Non è stato possibile eliminare l'evento\", \"errorcode\":3, \"data\":null}");

			}
			return;
		}catch (DaoException e) {
			response.getWriter().append("{\"error\":\"Errore nella gestione della persistenza\", \"errorcode\":4, \"data\":null}");
			return;
		} catch (SQLException e) {
			response.getWriter().append("{\"error\":\"Impossibile stabilire connesione con il db\", \"errorcode\":5, \"data\":null}");
			return;	
		}finally {
			if(conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					//do nothing
				}
		}
	
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
