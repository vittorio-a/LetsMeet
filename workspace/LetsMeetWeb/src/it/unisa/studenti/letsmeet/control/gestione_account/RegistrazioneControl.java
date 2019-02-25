package it.unisa.studenti.letsmeet.control.gestione_account;


import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.time.Instant;
import java.util.Enumeration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;


import it.unisa.studenti.letsmeet.manager.DaoException;
import it.unisa.studenti.letsmeet.manager.UtenteSqlDao;
import it.unisa.studenti.letsmeet.model.CredentialsBean;
import it.unisa.studenti.letsmeet.model.DataSourceSingleton;
import it.unisa.studenti.letsmeet.model.StatoUtente;
import it.unisa.studenti.letsmeet.model.UtenteBean;

/**
 * Servlet implementation class registrazioneControl
 */
@WebServlet("/registrazione")
public class RegistrazioneControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	private static final String USER = "username";
	private static final String PASS = "password";
	private static final String EMAIL = "email";
	
	
	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
		    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
	
	private static DataSource dataSource = null;
	
    /**
     * Default constructor. 
     * @throws NamingException 
     */
    public RegistrazioneControl() throws NamingException {
    	if(dataSource == null) {
    		dataSource = DataSourceSingleton.getDataSource();
    	}
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Enumeration<String> parNames = request.getParameterNames();
		boolean[] params = new boolean[3];
		while(parNames.hasMoreElements()) {
			switch (parNames.nextElement()) {
			case USER:
				params[0] = true;
				break;
			case PASS:
				params[1] = true;
				break;
			case EMAIL:
				params[2] = true;
				break;
			default:
				break;	
			}
		}
		
		
		if(!(params[0] && params[1] && params[2])) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Mancano uno o più parametri richiesti");
			return;
		}
		
		String username, password, email;
		username = request.getParameter(USER);
		password = request.getParameter(PASS);
		email = request.getParameter(EMAIL);
		java.sql.Connection conn = null;
		try {

			conn = dataSource.getConnection();
			UtenteSqlDao utenteDao = new UtenteSqlDao(dataSource.getConnection());
			if(!utenteDao.checkUsername(username)) {
				PrintWriter out = new PrintWriter(response.getOutputStream());
				out.print("{'error':'Username già in uso', 'errorcode':1, data:null}");
				out.close();
				return;
			}
			if(password.length() < 4 || password.length() > 20) {
				PrintWriter out = new PrintWriter(response.getOutputStream());
				out.print("{'error':'dimensioni password non corrette', 'errorcode':2, data:null}");
				out.close();
				return;
			}
			Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
			if(!matcher.find()) {
				PrintWriter out = new PrintWriter(response.getOutputStream());
				out.print("{'error':'formato email non corretto', 'errorcode':3, data:null}");
				out.close();
				return;
			}
			UtenteBean newUser = new UtenteBean();
			CredentialsBean creds = new CredentialsBean();
			creds.setUsername(username);
			MessageDigest digest = null;
			try {
				digest = MessageDigest.getInstance("SHA-256");
			} catch (NoSuchAlgorithmException e) {
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "impossibile hash");
			}
			byte[] hashedPsw = digest.digest(password.getBytes(StandardCharsets.UTF_8));
			creds.setPassword(hashedPsw);
			creds.setState(StatoUtente.ATTIVO);
			newUser.setCredentials(creds);
			newUser.setEmail(email);
			newUser.setReactivationDate(Instant.now());
			newUser.setFeedbackUtente(new BigDecimal(0));
			utenteDao.saveOrUpdate(newUser);
			
			
			PrintWriter out = new PrintWriter(response.getOutputStream());
			out.print("{\"error\":\"\", \"errorcode\":0, \"data\":null}");
			out.close();
			
		} catch (SQLException|DaoException e) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "impossibile controllare username");
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
