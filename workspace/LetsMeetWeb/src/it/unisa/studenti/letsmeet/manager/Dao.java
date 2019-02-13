package it.unisa.studenti.letsmeet.manager;

import java.util.List;

/**Fornisce un interfaccia globale per tutte le classi che dovranno manipolare dati
 *  attraverso una singola chiave oppure un item
 * preciso.
 *
 * @param <T>
 */
public interface Dao<T> {
	
	/**Permette di poter prendere un item T dato un id preciso
	 * @param id chiave dell'item che si desidera ricevere
	 * @return l'item cercato
	 * @throws DaoException
	 */
	T get(int id) throws DaoException;
	
	/**Permette di poter prendere tutti gli item del tipo richiesto
	 * @return una lista degli item
	 * @throws DaoException
	 */
	List<T> getAll() throws DaoException;
		
	/**Permette di poter aggiornare un item preciso oppure salvarlo come nuovo
	 * @param t item da aggiornare oppure da salvare
	 * @return 0 se non sono state effettuate modifiche altrimenti l'id dell'item modificato
	 * @throws DaoException
	 */
	int saveOrUpdate(T t) throws DaoException;
	
	/**Permette di poter cancellare un preciso item
	 * @param t item da cancellare
	 * @return true = avvenuta transazione oppure false = transazione fallita
	 * @throws DaoException
	 */
	boolean delete(T t) throws DaoException;
	

}
