package it.unisa.studenti.letsmeet.model;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DataSourceSingleton {
	private static DataSource dataSource = null;
	
	private DataSourceSingleton() {
		
	}
	
	
	public static DataSource getDataSource() throws NamingException {
		if(dataSource == null) {
			dataSource = getDataSourceInternal();
		}
		return dataSource;
	}
	
	
	private static DataSource getDataSourceInternal() throws NamingException {
		Context initCtx = new InitialContext();
		Context envCtx = (Context)initCtx.lookup("java:comp/env");
		
		DataSource ds = (DataSource)envCtx.lookup("jdbc/letsmeet");
		if(ds == null) {
			throw new NamingException();
		}
		return ds;
	}
}
