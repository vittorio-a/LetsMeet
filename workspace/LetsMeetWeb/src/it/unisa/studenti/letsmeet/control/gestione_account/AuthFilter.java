package it.unisa.studenti.letsmeet.control.gestione_account;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthFilter implements Filter {

	private static final String LOGIN_PAGE = "login.html";

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		HttpSession session = ((HttpServletRequest) request).getSession();
		boolean isLogged = false;
		Object obj = session.getAttribute(LoginControl.IS_LOGGED_IN_SESSION);
		if(obj != null) {
			isLogged = (boolean) obj;
			if(isLogged) {
				filterChain.doFilter(request, response);
				return;
			}
		}
		((HttpServletResponse) response).sendRedirect(LOGIN_PAGE);
		
	}

}
