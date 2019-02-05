package it.unisa.studenti.letsmeet.manager;

import java.util.List;

/**Fornisce un interfaccia globale per tutte le classi che dovranno manipolare dati
 *  attraverso due chiavi o una singola chiave un item
 * preciso.
 *
 * @param <T>
 */
public interface DaoDoubleKey<T> {
	
	/**Permette di poter prendere un item attraverso la prima chiave
	 * @param id chiave dell'item
	 * @return una lista degli item che hanno quella chiave
	 * @throws DaoException
	 */
	List<T> getAllForFirstKey(int id) throws DaoException;
	
	/**Permette di poter prendere un item attraverso la prima chiave
	 * @param id chiave dell'item
	 * @return una lista degli item che hanno quella chiave
	 * @throws DaoException
	 */
	List<T> getAllForSecondKey(int id)  throws DaoException;
	
	
	/**Permette di poter prendere un item attraverso due chiavi
	 * @param id1 prima chiave dell'item
	 * @param id2 seconda chiave dell'item
	 * @return l'item cercato
	 * @throws DaoException
	 */
	T getFromBothKeys(int id1, int id2) throws DaoException;
	
	/**Permette di aggiornare un item già esistente oppure di salvarne di nuovo
	 * @param t l'item da aggiornare o salvare
	 * @return true = avvenuta transazione oppure false = transazione fallita
	 * @throws DaoException
	 */
	boolean saveOrUpdate(T t) throws DaoException;
	
	/**Permette di poter cancellare un item specifico
	 * @param t item da cancellare
	 * @return true = avvenuta transazione oppure false = transazione fallita
	 * @throws DaoException
	 */
	boolean delete(T t) throws DaoException;

}
