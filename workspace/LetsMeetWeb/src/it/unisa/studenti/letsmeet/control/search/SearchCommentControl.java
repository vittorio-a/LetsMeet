package it.unisa.studenti.letsmeet.control.search;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.Instant;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.google.gson.Gson;

import it.unisa.studenti.letsmeet.manager.CommentoDao;
import it.unisa.studenti.letsmeet.manager.CommentoSqlDao;
import it.unisa.studenti.letsmeet.manager.DaoException;
import it.unisa.studenti.letsmeet.model.CommentoBean;
import it.unisa.studenti.letsmeet.model.DataSourceSingleton;

/**
 * Servlet implementation class SearchCommentControl
 */
@WebServlet("/SearchCommentControl")
public class SearchCommentControl extends HttpServlet {
	private static final long serialVersionUID = 1L;


	private static final String ID_EVENTO = "idEvento";

    
	DataSource ds;
	Gson gson = new Gson();
	
    public SearchCommentControl() throws NamingException {
    	ds = DataSourceSingleton.getDataSource();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
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
			response.getWriter().append("{\"error\":\"Il parametro "+ ID_EVENTO + " deve essere un intero\", \"errorcode\":2, \"data\":null}");
			return;
		}
		
		
		Connection conn = null;
		try {
			conn = ds.getConnection();
			CommentoDao dao = new CommentoSqlDao(conn);
			List<CommentoBean> commenti = dao.getAllCommentsByEventId(idEvento);
			Collections.sort(commenti, new Comparator<CommentoBean>() {
				@Override
				public int compare(CommentoBean o1, CommentoBean o2) {
					Instant i1, i2;
					i1 = o1.getCreationTime();
					i2 = o2.getCreationTime();
					return i1.compareTo(i2);
				}
			});
			
			String commentiJson = gson.toJson(commenti);
			response.getWriter().append("{\"error\":\"\", \"errorcode\":0, \"data\":" + commentiJson + "}");
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
