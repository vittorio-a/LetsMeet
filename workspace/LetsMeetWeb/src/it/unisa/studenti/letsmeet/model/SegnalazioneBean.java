package it.unisa.studenti.letsmeet.model;

/**
 * Rappresenta una segnalazione di un item ritenuto inappropriato da un utente
 */
public class SegnalazioneBean {
	private int idSegnalazione;
	private int idItemSegnalato;
	private TipoSegnalazione tipoSegnalazione;
	
	
	public SegnalazioneBean() {
		this.idItemSegnalato = 0;
		this.idSegnalazione = 0;
		this.tipoSegnalazione = null;
	}
	
/**
 * Costruttore
 * @param idSegnalazione l'id della segnalazione
 * @param idItemSegnalato l'id dell'item segnalato
 * @param tipoSegnalazione il tipo di item segnalato
 */
	public SegnalazioneBean(int idSegnalazione, int idItemSegnalato, TipoSegnalazione tipoSegnalazione) {
		this.idSegnalazione = idSegnalazione;
		this.idItemSegnalato = idItemSegnalato;
		this.tipoSegnalazione = tipoSegnalazione;
	}
	
	public TipoSegnalazione getTipoSegnalazione() {
		return tipoSegnalazione;
	}
	public void setTipoSegnalazione(TipoSegnalazione tipoSegnalazione) {
		this.tipoSegnalazione = tipoSegnalazione;
	}
	public int getIdItemSegnalato() {
		return idItemSegnalato;
	}
	public void setIdItemSegnalato(int idItemSegnalato) {
		this.idItemSegnalato = idItemSegnalato;
	}
	public int getIdSegnalazione() {
		return idSegnalazione;
	}
	public void setIdSegnalazione(int idSegnalazione) {
		this.idSegnalazione = idSegnalazione;
	}
	
	
}
