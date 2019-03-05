package it.unisa.studenti.letsmeet.control.gestione_evento;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import it.unisa.studenti.letsmeet.control.gestione_account.LoginControl;
import it.unisa.studenti.letsmeet.manager.DaoException;
import it.unisa.studenti.letsmeet.manager.RatingDao;
import it.unisa.studenti.letsmeet.manager.RatingSqlDao;
import it.unisa.studenti.letsmeet.model.DataSourceSingleton;
import it.unisa.studenti.letsmeet.model.RatingBean;

/**
 * Servlet implementation class RatingControl
 */
@WebServlet("/auth/eventi/ratingControl")
public class RatingControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String ID_EVENTO = "idEvento";

	private static final String VOTO = "voto";

	DataSource ds;

	public RatingControl() throws NamingException {
    	ds = DataSourceSingleton.getDataSource();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String[]> params = request.getParameterMap();
		
		if(!params.containsKey(ID_EVENTO) || !params.containsKey(VOTO)) {
			response.getWriter().append("{\"error\":\"Manca qualche parametro\", \"errorcode\":1, \"data\":null}");
			return;
		}
		
		int idEvento = 0;
		try {
			idEvento = Integer.parseInt(params.get(ID_EVENTO)[0]);
		}catch (NumberFormatException e) {
			response.getWriter().append("{\"error\":\"Il parametro "+ ID_EVENTO + " deve essere un intero\", \"errorcode\":2, \"data\":null}");
			return;	
		}
		
		boolean voto = Boolean.parseBoolean(params.get(VOTO)[0]);
		
		Connection conn = null;
		RatingBean rating = new RatingBean();
		rating.setEvento(idEvento);
		rating.setIdutente((int) request.getSession().getAttribute(LoginControl.ID_IN_SESSION)); 
		rating.setVoto(voto);
		
		try {
			conn = ds.getConnection();
			RatingDao dao = new RatingSqlDao(conn);
			dao.saveOrUpdate(rating);
			response.getWriter().append("{\"error\":\"\", \"errorcode\":0, \"data\":null}");
			return;	
			
		}catch (DaoException e) {
			response.getWriter().append("{\"error\":\"Errore nella gestione della persistenza\", \"errorcode\":4, \"data\":null}");
			return;	
		}catch (SQLException e) {
			response.getWriter().append("{\"error\":\"Impossibile stabilire connesione con il db\", \"errorcode\":3, \"data\":null}");
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
