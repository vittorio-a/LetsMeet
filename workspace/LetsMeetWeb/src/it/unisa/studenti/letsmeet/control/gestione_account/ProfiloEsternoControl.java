package it.unisa.studenti.letsmeet.control.gestione_account;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.google.gson.Gson;

import it.unisa.studenti.letsmeet.manager.EventoSqlDao;
import it.unisa.studenti.letsmeet.manager.UtenteSqlDao;
import it.unisa.studenti.letsmeet.model.DataSourceSingleton;
import it.unisa.studenti.letsmeet.model.EventoBean;
import it.unisa.studenti.letsmeet.model.UtenteBean;

/**
 * Servlet implementation class profiloEsternoControl
 */
@WebServlet("/profiloEsterno")
public class ProfiloEsternoControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DataSource dataSource;
	private static final String ID = "idUtente";
	
    /**
     * Default constructor. 
     * @throws NamingException 
     */
    public ProfiloEsternoControl() throws NamingException {
    	dataSource = DataSourceSingleton.getDataSource();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter(ID);
		boolean isOk = false;
		int idUtente = 0;
		if(id != null) {
			try {
				idUtente = Integer.parseInt(id);
				isOk = true;
			}catch (NumberFormatException e) {
				response.getWriter().append("{'error':'non è possibile parsare l'id', 'errorcode':1}");
				return;
			}
		}
		if(!isOk) {
			response.getWriter().append("{'error':'manca il parametro " + ID + "', 'errorcode':2}");
			return;
		}
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			UtenteSqlDao utenteDao = new UtenteSqlDao(conn);
			EventoSqlDao eventoDao = new EventoSqlDao(conn);
			
			UtenteBean utente = utenteDao.get(idUtente);
			if(utente == null) {
				response.getWriter().append("{'error':'utente non trovato', 'errorcode':3}");
				return;
			}
			
			List<EventoBean> eventi = eventoDao.getAllByUserId(idUtente);
			Gson gson = new Gson();
			String eventiJson = gson.toJson(eventi);
			String utenteJson = gson.toJson(utente);
			
			String res = "{'utente':" + utenteJson + ",'eventi':" + eventiJson + "}";
			response.getWriter().append(res);
		}catch (Exception e) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
