package it.unisa.studenti.letsmeet.control.gestione_evento;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.Instant;
import java.util.Map;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import it.unisa.studenti.letsmeet.control.gestione_account.LoginControl;
import it.unisa.studenti.letsmeet.manager.CommentoDao;
import it.unisa.studenti.letsmeet.manager.CommentoSqlDao;
import it.unisa.studenti.letsmeet.manager.DaoException;
import it.unisa.studenti.letsmeet.model.CommentoBean;
import it.unisa.studenti.letsmeet.model.DataSourceSingleton;

/**
 * Servlet implementation class CommentoControl
 */
@WebServlet("/auth/eventi/commentoControl")
public class CommentoControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String ID_EVENTO = "idEvento";

	private static final String TEXT = "text";

	private static final int TEXT_LIMIT = 255;

	DataSource ds;
	
    public CommentoControl() throws NamingException {
    	ds = DataSourceSingleton.getDataSource();
    }

    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String[]> params = request.getParameterMap();
		
		if(!params.containsKey(ID_EVENTO) || !params.containsKey(TEXT)) {
			response.getWriter().append("{\"error\":\"Mancano dei parametri\", \"errorcode\":1, \"data\":null}");
			return;
		}
		
		int idEvento = 0;
		
		try {
			idEvento = Integer.parseInt(params.get(ID_EVENTO)[0]);
		}catch (NumberFormatException e) {
			response.getWriter().append("{\"error\":\"Il parametro "+ ID_EVENTO + " deve essere un intero\", \"errorcode\":2, \"data\":null}");
			return;		
		}
		
		
		String text = params.get(TEXT)[0];
		if(text.equals("") || text.length() > TEXT_LIMIT) {
			response.getWriter().append("{\"error\":\"Testo non valido\", \"errorcode\":3, \"data\":null}");
			return;	
		}
		
		
		CommentoBean commento = new CommentoBean();
		commento.setIdEvento(idEvento);
		commento.setIdUtente((int) request.getSession().getAttribute(LoginControl.ID_IN_SESSION));
		commento.setContenuto(text);
		commento.setCreationTime(Instant.now());
		
		
		Connection conn = null;
		try {
			conn = ds.getConnection();
			CommentoDao dao = new CommentoSqlDao(conn);
			dao.saveOrUpdate(commento);
			response.getWriter().append("{\"error\":\"\", \"errorcode\":0, \"data\":null}");
			return;
		}catch (DaoException e) {
			response.getWriter().append("{\"error\":\"Errore nella gestione della persistenza\", \"errorcode\":4, \"data\":null}");
			return;	
		}catch (SQLException e) {
			response.getWriter().append("{\"error\":\"Impossibile stabilire connessione al db\", \"errorcode\":5, \"data\":null}");
			return;			
		}finally {
			if(conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					//do nient
				}
		}
		
	}

}
