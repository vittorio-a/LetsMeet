package it.unisa.studenti.letsmeet.model;

import java.math.BigDecimal;
import java.time.Instant;

/**Rappresneta un evento 
 *
 */
public class EventoBean {
	
	
	private int idEvento;
	private String nome;
	private BigDecimal feedback;
	private int nPartecipanti;
	private int nVerificati;
	private Instant oraInizio;
	private Instant oraFine;
	private int idUtente;
	private TipoBean tipo;
	private PosizioneBean posizione;
	private boolean isVisible;
	private String descrizione;
	
	/**
	 * Costryttore vuoto
	 */
	public EventoBean() {
		this.feedback = null;
		this.idEvento = 0;
		this.idUtente = 0;
		this.isVisible = false;
		this.nome =null;
		this.nPartecipanti = 0;
		this.nVerificati = 0;
		this.posizione = null;
		this.tipo = null;
	}
	
	
	/**Costruttore
	 * @param idEvento id dell'evento
	 * @param nome nome dell'evento
	 * @param feedback valutazione finale dell'evento
	 * @param nPartecipanti numero dei partecipanti all'evento
	 * @param nVerificati numero dei partecipaznti verifucati all'evento
	 * @param oraInizio orari di inizio dell'evento
	 * @param oraFine orario di fine dell'evento
	 * @param idUtente id dell'utente che ha creato l'evento
	 * @param tipo tipologia dell'evento
	 * @param posizione posizione dell'evento 
	 * @param isVisible stato dell'evento
	 */
	public EventoBean(int idEvento, String nome, BigDecimal feedback, int nPartecipanti, int nVerificati, Instant oraInizio,
			Instant oraFine, int idUtente, TipoBean tipo, PosizioneBean posizione,
			boolean isVisible, String descrizione) {
		this.idEvento = idEvento;
		this.nome = nome;
		this.feedback = feedback;
		this.nPartecipanti = nPartecipanti;
		this.nVerificati = nVerificati;
		this.oraInizio = oraInizio;
		this.oraFine = oraFine;
		this.idUtente = idUtente;
		this.tipo = tipo;
		this.posizione = posizione;
		this.isVisible = isVisible;
		this.descrizione = descrizione;
	}
	
	
	public String getDescrizione() {
		return descrizione;
	}


	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}


	public int getIdEvento() {
		return idEvento;
	}
	public void setIdEvento(int idEvento) {
		this.idEvento = idEvento;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public BigDecimal getFeedback() {
		return feedback;
	}
	public void setFeedback(BigDecimal feedback) {
		this.feedback = feedback;
	}
	public int getnPartecipanti() {
		return nPartecipanti;
	}
	public void setnPartecipanti(int nPartecipanti) {
		this.nPartecipanti = nPartecipanti;
	}
	public int getnVerificati() {
		return nVerificati;
	}
	public void setnVerificati(int nVerificati) {
		this.nVerificati = nVerificati;
	}
	public Instant getOraInizio() {
		return oraInizio;
	}
	public void setOraInizio(Instant oraInizio) {
		this.oraInizio = oraInizio;
	}
	public Instant getOraFine() {
		return oraFine;
	}
	public void setOraFine(Instant oraFine) {
		this.oraFine = oraFine;
	}
	public int getIdUtente() {
		return idUtente;
	}
	public void setIdUtente(int idUtente) {
		this.idUtente = idUtente;
	}
	public TipoBean getTipo() {
		return tipo;
	}
	public void setTipo(TipoBean tipo) {
		this.tipo = tipo;
	}
	public PosizioneBean getPosizione() {
		return posizione;
	}
	public void setPosizione(PosizioneBean posizione) {
		this.posizione = posizione;
	}
	
	public boolean isVisible() {
		return isVisible;
	}
	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EventoBean other = (EventoBean) obj;
		if (descrizione == null) {
			if (other.descrizione != null)
				return false;
		} else if (!descrizione.equals(other.descrizione))
			return false;
		if (feedback == null) {
			if (other.feedback != null)
				return false;
		} else if (!feedback.equals(other.feedback))
			return false;
		if (idEvento != other.idEvento)
			return false;
		if (idUtente != other.idUtente)
			return false;
		if (isVisible != other.isVisible)
			return false;
		if (nPartecipanti != other.nPartecipanti)
			return false;
		if (nVerificati != other.nVerificati)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (oraFine == null) {
			if (other.oraFine != null)
				return false;
		} else if (!oraFine.equals(other.oraFine))
			return false;
		if (oraInizio == null) {
			if (other.oraInizio != null)
				return false;
		} else if (!oraInizio.equals(other.oraInizio))
			return false;
		if (posizione == null) {
			if (other.posizione != null)
				return false;
		} else if (!posizione.equals(other.posizione))
			return false;
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "EventoBean [idEvento=" + idEvento + ", nome=" + nome + ", feedback=" + feedback + ", nPartecipanti="
				+ nPartecipanti + ", nVerificati=" + nVerificati + ", oraInizio=" + oraInizio + ", oraFine=" + oraFine
				+ ", idUtente=" + idUtente + ", tipo=" + tipo + ", posizione=" + posizione + ", partecipanti="
				+ ", isVisible=" + isVisible + "]";
	}
}
