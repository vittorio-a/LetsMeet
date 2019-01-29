package it.unisa.studenti.letsmeet.model;

import java.time.Instant;

public class CommentoBean {
	
	private int idUtente;
	private String contenuto;
	private int idCommento;
	private int idEvento;
	private Instant creationTime;
	
	
	public CommentoBean() {
		this.contenuto = null;
		this.creationTime = null;
		this.idCommento = 0;
		this.idEvento = 0;
		this.idUtente = 0;
	};

	public CommentoBean(int idUtente, String contenuto, int idCommento, int idEvento, Instant creationTime) {
		this.idUtente = idUtente;
		this.contenuto = contenuto;
		this.idCommento = idCommento;
		this.idEvento = idEvento;
		this.creationTime = creationTime;
	}
	
	
	public int getIdUtente() {
		return idUtente;
	}
	public void setIdUtente(int idUtente) {
		this.idUtente = idUtente;
	}
	public String getContenuto() {
		return contenuto;
	}
	public void setContenuto(String contenuto) {
		this.contenuto = contenuto;
	}
	public int getIdCommento() {
		return idCommento;
	}
	public void setIdCommento(int idCommento) {
		this.idCommento = idCommento;
	}
	public int getIdEvento() {
		return idEvento;
	}
	public void setIdEvento(int idEvento) {
		this.idEvento = idEvento;
	}
	public Instant getCreationTime() {
		return creationTime;
	}
	public void setCreationTime(Instant creationTime) {
		this.creationTime = creationTime;
	}
	@Override
	public String toString() {
		return "CommentoBean [idUtente=" + idUtente + ", contenuto=" + contenuto + ", idCommento=" + idCommento
				+ ", idEvento=" + idEvento + ", creationTime=" + creationTime + "]";
	}
	
	
}
