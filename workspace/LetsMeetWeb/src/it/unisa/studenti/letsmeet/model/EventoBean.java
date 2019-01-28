package it.unisa.studenti.letsmeet.model;

import java.time.Instant;
import java.util.ArrayList;

public class EventoBean {
	
	
	private int idEvento;
	private String nome;
	private float feedback;
	private int nPartecipanti;
	private int nVerificati;
	private Instant oraInizio;
	private Instant oraFine;
	private int idUtente;
	private TipoBean tipo;
	private PosizioneBean posizione;
	private ArrayList<PartecipazioneBean> partecipanti;
	private boolean isVisible;
	
	public EventoBean(int idEvento, String nome, float feedback, int nPartecipanti, int nVerificati, Instant oraInizio,
			Instant oraFine, int idUtente, TipoBean tipo, PosizioneBean posizione,
			ArrayList<PartecipazioneBean> partecipanti, boolean isVisible) {
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
		this.partecipanti = partecipanti;
		this.isVisible = isVisible;
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
	public float getFeedback() {
		return feedback;
	}
	public void setFeedback(float feedback) {
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
	public ArrayList<PartecipazioneBean> getPartecipanti() {
		return partecipanti;
	}
	public void setPartecipanti(ArrayList<PartecipazioneBean> partecipanti) {
		this.partecipanti = partecipanti;
	}
	public boolean isVisible() {
		return isVisible;
	}
	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}




	@Override
	public String toString() {
		return "EventoBean [idEvento=" + idEvento + ", nome=" + nome + ", feedback=" + feedback + ", nPartecipanti="
				+ nPartecipanti + ", nVerificati=" + nVerificati + ", oraInizio=" + oraInizio + ", oraFine=" + oraFine
				+ ", idUtente=" + idUtente + ", tipo=" + tipo + ", posizione=" + posizione + ", partecipanti="
				+ partecipanti + ", isVisible=" + isVisible + "]";
	}
}