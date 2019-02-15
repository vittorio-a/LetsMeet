package it.unisa.studenti.letsmeet.init;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;

public class init implements ServletContextListener {
@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContextListener.super.contextInitialized(sce);
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Class.forName("com.google.gson.Gson");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("no driver mysql");
		}
}

@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		ServletContextListener.super.contextDestroyed(sce);
	}
}
