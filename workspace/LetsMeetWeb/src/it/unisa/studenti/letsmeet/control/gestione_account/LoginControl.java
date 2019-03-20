package it.unisa.studenti.letsmeet.control.gestione_account;


import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Map;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import it.unisa.studenti.letsmeet.manager.DaoException;
import it.unisa.studenti.letsmeet.manager.UtenteDao;
import it.unisa.studenti.letsmeet.manager.UtenteSqlDao;
import it.unisa.studenti.letsmeet.model.CredentialsBean;
import it.unisa.studenti.letsmeet.model.DataSourceSingleton;
import it.unisa.studenti.letsmeet.model.UtenteBean;

/**
 * Servlet implementation class loginControl
 */
@WebServlet("/account/loginControl")
public class LoginControl extends HttpServlet {
	private static final long serialVersionUID = 1L;


	private static final String USERNAME = "username";


	private static final String PASSWORD = "password";

    
	DataSource ds;

	public static final String ID_IN_SESSION = "idUtente";
	public static final String USERNAME_IN_SESSION = "username";
	public static final String IS_LOGGED_IN_SESSION = "isLogged";


	private static final String HOME_URL = "/homePage.html";


	
    public LoginControl() throws NamingException {
    	ds = DataSourceSingleton.getDataSource();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String[]> params = request.getParameterMap();
		if(!params.containsKey(USERNAME) || !params.containsKey(PASSWORD)) {
			response.getWriter().append("{\"error\":\"Manca qualche parametro\", \"errorcode\":1, \"data\":null}");
			return;
		}
		
		
		Connection conn = null;
		
		try {
			conn = ds.getConnection();
			UtenteDao dao = new UtenteSqlDao(conn);
			UtenteBean utente = dao.getByUsername(request.getParameter(USERNAME));
			
			if(utente == null) {
				response.getWriter().append("{\"error\":\"Username o password non validi\", \"errorcode\":3, \"data\":null}");
				return;
			}
			
			CredentialsBean creds = utente.getCredentials();
			
			String password = request.getParameter(PASSWORD);
			
			MessageDigest digest = null;
			try {
				digest = MessageDigest.getInstance("SHA-256");
			} catch (NoSuchAlgorithmException e) {
				response.getWriter().append("{\"error\":\"Impossibile controllare psw\", \"errorcode\":2, \"data\":null}");
				return;
			}
			
			byte[] hashedPsw = digest.digest(password.getBytes(StandardCharsets.UTF_8));
			byte[] hashedStoredPassword = creds.getPassword();
			
			if(Arrays.equals(hashedPsw,hashedStoredPassword)) {
				HttpSession session = request.getSession();
				
				//setta gli attributi per il login
				session.setAttribute(IS_LOGGED_IN_SESSION, true);
				session.setAttribute(ID_IN_SESSION, utente.getIdUtente());
				session.setAttribute(USERNAME_IN_SESSION, creds.getUsername());
				
				//prendi gli attributi per completare la richesta precedente al login se c'è stata
				Object boolObj = session.getAttribute(AuthFilter.IS_REDIRECT_LOGIN);
				boolean isRedirectLogin = (boolObj != null) ? (boolean) boolObj : false;
				if(isRedirectLogin) {
					session.setAttribute(AuthFilter.IS_REDIRECT_LOGIN, false);
					Map<String, String[]> exParams = (Map<String, String[]>) session.getAttribute(AuthFilter.INTENDED_PARAMS);
					String exUrl = (String) session.getAttribute(AuthFilter.INTENDED_URL);
					if(exUrl != null && exParams != null) {
						Enumeration<String> oldParams = request.getAttributeNames();
						while(oldParams.hasMoreElements()) {
							request.removeAttribute(oldParams.nextElement());
						}
						exParams.forEach((key, values)->{
							for(int i = 0; i < values.length; i++)
								request.setAttribute(key, values[i]);
						});
						request.getRequestDispatcher(exUrl).forward(request, response);
						return;
					}
					
				}
				
				response.getWriter().append("{\"error\":\"\", \"errorcode\":0, \"data\":null}");
				return;
			}
			response.getWriter().append("{\"error\":\"Username o password non validi\", \"errorcode\":3, \"data\":null}");
			return;
		}catch (DaoException e) {
			response.getWriter().append("{\"error\":\"Qualcosa è andato storto\", \"errorcode\":4, \"data\":null}");
			return;
		}catch (SQLException e) {
			response.getWriter().append("{\"error\":\"Qualcosa è andato storto con mysql\", \"errorcode\":5, \"data\":null}");
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

}
