package it.unisa.studenti.letsmeet.control.search;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.google.gson.Gson;

import it.unisa.studenti.letsmeet.filter.Filter;
import it.unisa.studenti.letsmeet.filter.FilterFactory;
import it.unisa.studenti.letsmeet.filter.FilterFactroyException;
import it.unisa.studenti.letsmeet.filter.TipoFiltro;
import it.unisa.studenti.letsmeet.manager.DaoException;
import it.unisa.studenti.letsmeet.manager.EventoSqlDao;
import it.unisa.studenti.letsmeet.model.DataSourceSingleton;
import it.unisa.studenti.letsmeet.model.EventoBean;

/**
 * Servlet implementation class SearchEventControl
 */
@WebServlet("/SearchEventControl")
public class SearchEventControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String TIPO_FILTRO = "tipo_filtro";
	
	DataSource ds;
	Gson gson = new Gson();

    /**
     * Default constructor. 
     */
    public SearchEventControl() throws NamingException{
		ds = DataSourceSingleton.getDataSource();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tipo = request.getParameter(TIPO_FILTRO);
		if(tipo == null) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Non è presente il paramtero" + TIPO_FILTRO);
		}
		TipoFiltro tipoFiltro = null;
		Connection conn = null;
		try{
			tipoFiltro = TipoFiltro.valueOf(tipo);
		}catch (IllegalArgumentException e) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Paramtero " + TIPO_FILTRO + " non valido");
		}
		try {
			final Filter<EventoBean> filter = FilterFactory.getFilter(tipoFiltro, request.getParameterMap());
			conn = ds.getConnection();
			EventoSqlDao dao = new EventoSqlDao(conn);
			List<EventoBean> eventi = dao.getAll();
			List<EventoBean> eventiFiltrati = new ArrayList<>();
			eventi.forEach((evento)->{
				if(filter.check(evento)) eventiFiltrati.add(evento);
			});
			String jsonResponse =  gson.toJson(eventiFiltrati);
			response.getWriter().append(jsonResponse);
		}catch (FilterFactroyException e) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Unable to load filter");
		} catch (SQLException e) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Unable to get db connection");
		} catch (DaoException e) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Dao execp");
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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
