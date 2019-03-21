package it.unisa.studenti.letsmeet.control.gestione_evento;

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
import javax.swing.plaf.ButtonUI;

import com.google.gson.Gson;

import it.unisa.studenti.letsmeet.manager.DaoException;
import it.unisa.studenti.letsmeet.manager.EventoDao;
import it.unisa.studenti.letsmeet.manager.EventoSqlDao;
import it.unisa.studenti.letsmeet.manager.PartecipazioneDao;
import it.unisa.studenti.letsmeet.manager.PartecipazioneSqlDao;
import it.unisa.studenti.letsmeet.manager.UtenteDao;
import it.unisa.studenti.letsmeet.manager.UtenteSqlDao;
import it.unisa.studenti.letsmeet.model.DataSourceSingleton;
import it.unisa.studenti.letsmeet.model.EventoBean;
import it.unisa.studenti.letsmeet.model.PartecipazioneBean;
import it.unisa.studenti.letsmeet.model.UtenteBean;

/**
 * Servlet implementation class EventoInfoControl
 */
@WebServlet("/eventi/eventoInfoControl")
public class EventoInfoControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ID_PARAM_NAME = "idEvento";

	DataSource ds;
	Gson gson = new Gson();
   
	
    public EventoInfoControl() throws NamingException {
    	ds = DataSourceSingleton.getDataSource();
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idEventoString = request.getParameter(ID_PARAM_NAME);
		if(idEventoString == null) {
			response.getWriter().append("{\"error\":\"Manca il parametro "+ ID_PARAM_NAME + "\", \"errorcode\":1, \"data\":null}");
			return;
		}
		
		int id = 0;
		try {
			id = Integer.parseInt(idEventoString);
		}catch (NumberFormatException e) {
			response.getWriter().append("{\"error\":\"Il parametro non è un intero\", \"errorcode\":2, \"data\":null}");
			return;
		}
		
		Connection conn = null;
		try {
			conn = ds.getConnection();
			EventoDao dao = new EventoSqlDao(conn);
			EventoBean evento = dao.get(id);
			PartecipazioneDao daoPartecipazione = new PartecipazioneSqlDao(conn);
			List<PartecipazioneBean> partecipazioni = daoPartecipazione.getAllForSecondKey(id);
			UtenteDao utenteDao = new UtenteSqlDao(conn);

			StringBuilder builder = new StringBuilder();
			builder.append("{\"error\":\"\", \"errorcode\":0, \"data\":{\"evento\":");
			builder.append(gson.toJson(evento));
			builder.append(",\"creatorUsername\":\"" + utenteDao.get(evento.getIdUtente()).getCredentials().getUsername() + "\"");
			builder.append(",\"partecipazioni\":[");
			boolean isFirst = true;

			for(PartecipazioneBean partecipazione : partecipazioni) {
				if(!isFirst) builder.append(",{");
				else builder.append("{");
				isFirst = false;
				UtenteBean utente = utenteDao.get(partecipazione.getIdUtente());
				builder.append("\"username\":\""+  utente.getCredentials().getUsername() +"\"");
				builder.append(",\"idUtente\":" + partecipazione.getIdUtente() + ",\"verificato\":");
				builder.append(partecipazione.isVerificato());
				builder.append("}");
			}
			builder.append("]}}");
			response.getWriter().append(builder.toString());
		}catch (DaoException e) {
			response.getWriter().append("{\"error\":\"errore nella persistenza dei dati\", \"errorcode\":3, \"data\":null}");
			return;
		}catch (SQLException e) {
			response.getWriter().append("{\"error\":\"errore sql\", \"errorcode\":4, \"data\":null}");
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
