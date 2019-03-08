package it.unisa.studenti.letsmeet.control.gestione_account;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthFilter implements Filter {

	private static final String LOGIN_PAGE = "/LetsMeetWeb/login.html";
	public static final String INTENDED_URL = "intended_url";
	public static final String IS_REDIRECT_LOGIN = "is_redirect_login";
	public static final String INTENDED_PARAMS = "intended_params";
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		HttpSession session = ((HttpServletRequest) request).getSession(true);
		boolean isLogged = false;
		Object obj = session.getAttribute(LoginControl.IS_LOGGED_IN_SESSION);
		if(obj != null) {
			isLogged = (boolean) obj;
			if(isLogged) {
				filterChain.doFilter(request, response);
				return;
			}
		}
		if(session != null) {
			HttpServletRequest httpRequest = (HttpServletRequest) request;
			Map<String, String[]> params = httpRequest.getParameterMap();
			session.setAttribute(INTENDED_URL, httpRequest.getServletPath());
			session.setAttribute(INTENDED_PARAMS, params);
			session.setAttribute(IS_REDIRECT_LOGIN, true);
		}
		((HttpServletResponse) response).sendRedirect(LOGIN_PAGE);
		
	}

}
