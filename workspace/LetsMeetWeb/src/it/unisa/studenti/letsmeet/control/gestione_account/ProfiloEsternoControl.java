package it.unisa.studenti.letsmeet.control.gestione_account;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
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
import it.unisa.studenti.letsmeet.model.CredentialsBean;
import it.unisa.studenti.letsmeet.model.DataSourceSingleton;
import it.unisa.studenti.letsmeet.model.EventoBean;
import it.unisa.studenti.letsmeet.model.UtenteBean;

/**
 * Servlet implementation class profiloEsternoControl
 */
@WebServlet("/auth/account/profiloEsterno")
public class ProfiloEsternoControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DataSource dataSource;
	public static final String ID_UTENETE_PAR = "idUtente";
	
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
		String id = request.getParameter(ID_UTENETE_PAR);
		if(id == null) id = ((Integer) request.getSession().getAttribute(LoginControl.ID_IN_SESSION)).toString();
		boolean isOk = false;
		int idUtente = 0;
		if(id != null) {
			try {
				idUtente = Integer.parseInt(id);
				isOk = true;
			}catch (NumberFormatException e) {
				response.getWriter().append("{\"error\":\"non è possibile parsare l'id\", \"errorcode\":1, \"data\":null}");
				return;
			}
		}
		if(!isOk) {
			response.getWriter().append("{\"error\":\"manca il parametro " + ID_UTENETE_PAR + "\", \"errorcode\":2, \"data\":null}");
			return;
		}
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			UtenteSqlDao utenteDao = new UtenteSqlDao(conn);
			EventoSqlDao eventoDao = new EventoSqlDao(conn);
			
			UtenteBean utente = utenteDao.get(idUtente);
			if(utente == null) {
				response.getWriter().append("{\"error\":\"utente non trovato\", \"errorcode\":3, \"data\":null}");
				return;
			}
			
			List<EventoBean> eventi = eventoDao.getAllByUserId(idUtente);
			CredentialsBean creds = utente.getCredentials();
			creds.setPassword(null);
			utente.setCredentials(creds);
			
			Gson gson = new Gson();
			String eventiJson = gson.toJson(eventi);
			String utenteJson = gson.toJson(utente);

			
			String res = "{\"error\":\"\", \"errorcode\":0, \"data\" : {\"utente\":" + utenteJson + ",\"eventi\":" + eventiJson + "}}";
			response.getWriter().append(res);
		}catch (Exception e) {
			response.getWriter().append("{\"error\":\"internal generic error\", \"errorcode\":4, \"data\":null}");
		}finally {
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					//do nothing
				}
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
