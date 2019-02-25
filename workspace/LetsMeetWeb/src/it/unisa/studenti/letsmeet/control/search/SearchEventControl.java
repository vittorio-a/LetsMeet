package it.unisa.studenti.letsmeet.control.search;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unisa.studenti.letsmeet.filter.Filter;
import it.unisa.studenti.letsmeet.filter.FilterFactory;
import it.unisa.studenti.letsmeet.filter.TipoFiltro;
import it.unisa.studenti.letsmeet.model.EventoBean;

/**
 * Servlet implementation class SearchEventControl
 */
@WebServlet("/SearchEventControl")
public class SearchEventControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public SearchEventControl() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Filter<EventoBean> filter = null;		
		try {
			filter = FilterFactory.getFilter(TipoFiltro.DISTANZA, request.getParameterMap());
			//filter.check()	
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
