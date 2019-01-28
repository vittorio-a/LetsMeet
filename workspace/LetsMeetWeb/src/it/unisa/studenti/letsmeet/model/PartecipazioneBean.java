package it.unisa.studenti.letsmeet.model;

public class PartecipazioneBean {
	
	
	private int idUtente;
	private boolean verificato;
	private int idPartecipazione;
	
	public PartecipazioneBean(int idUtente, boolean verificato, int idPartecipazione) {
		this.idUtente = idUtente;
		this.verificato = verificato;
		this.idPartecipazione = idPartecipazione;
	}
	
	
	public int getIdUtente() {
		return idUtente;
	}
	public void setIdUtente(int idUtente) {
		this.idUtente = idUtente;
	}
	public boolean isVerificato() {
		return verificato;
	}
	public void setVerificato(boolean verificato) {
		this.verificato = verificato;
	}
	public int getIdPartecipazione() {
		return idPartecipazione;
	}
	public void setIdPartecipazione(int idPartecipazione) {
		this.idPartecipazione = idPartecipazione;
	}
	@Override
	public String toString() {
		return "PartecipazioneBean [idUtente=" + idUtente + ", verificato=" + verificato + ", idPartecipazione="
				+ idPartecipazione + "]";
	}
	
	
	
	
}
