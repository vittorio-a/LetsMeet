package it.unisa.studenti.letsmeet.manager;

import it.unisa.studenti.letsmeet.model.UtenteBean;

public interface UtenteDao extends Dao<UtenteBean> {
	
	/**
	 * Permette di controllare se un username è utilizzato da un utente
	 * @param username String username dell'utente che bisogna controllare
	 * @return boolean true se non è presente l'username falso altrimenti 
	 * @throws DaoException
	 */
	public boolean checkUsername(String username) throws DaoException;
	
	/**
	 * Ritorna l'utente che ha la username specificata
	 * @param username String username dell'utente che bisogna ritornare
	 * @return lo {@link UtenteBean} con lo username specificato
	 * @throws DaoException
	 */
	public UtenteBean getByUsername(String username) throws DaoException;

}
