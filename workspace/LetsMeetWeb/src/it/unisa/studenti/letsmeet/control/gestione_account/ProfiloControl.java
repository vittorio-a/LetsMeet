package it.unisa.studenti.letsmeet.control.gestione_account;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/auth/account/profiloControl")
public class ProfiloControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

   
    public ProfiloControl() {
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//passo la richiesta a profilo out 
		request.getRequestDispatcher("/auth/account/profiloEsterno").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
