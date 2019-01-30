package it.unisa.studenti.letsmeet.manager;

public class DaoException extends Exception {

	private static final long serialVersionUID = -4408073516050168962L;
	private DaoExceptionType type;
	public DaoException(String message, Throwable exception, DaoExceptionType type) {
		super(message, exception);
		this.type = type;
	}
	
	public DaoExceptionType getType() {
		return type;
	}
}
