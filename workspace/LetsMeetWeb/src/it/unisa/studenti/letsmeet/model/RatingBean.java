package it.unisa.studenti.letsmeet.model;

/**
 * Rappresenta il rating di un evento
 *
 */
public class RatingBean {
	
	
	private int idutente;
	private int idEvento;
	private boolean voto;
	
	
	/**
	 * Costruttore vuoto
	 */
	public RatingBean() {
		this.idEvento = 0;
		this.idutente = 0;
		this.voto = false;
	}
	
	
	/**Costruttore
	 * @param idutente id dell'utente che ha effettuato il rating
	 * @param evento id dll'evento soggeto al rating 
	 * @param voto  l'effettivo voto del rating 
	 */
	public RatingBean(int idutente, int evento, boolean voto) {
		this.idutente = idutente;
		this.idEvento = evento;
		this.voto = voto;
	}
	
	public int getIdutente() {
		return idutente;
	}
	
	public void setIdutente(int idutente) {
		this.idutente = idutente;
	}
	public int getEvento() {
		return idEvento;
	}
	public void setEvento(int evento) {
		this.idEvento = evento;
	}
	public boolean isVoto() {
		return voto;
	}
	public void setVoto(boolean voto) {
		this.voto = voto;
	}
	@Override
	public String toString() {
		return "RatingBean [idutente=" + idutente + ", evento=" + idEvento + ", voto=" + voto + "]";
	}
	
	

}
