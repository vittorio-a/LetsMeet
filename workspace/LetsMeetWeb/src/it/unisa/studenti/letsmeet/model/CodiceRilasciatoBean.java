package it.unisa.studenti.letsmeet.model;

import java.time.Instant;

public class CodiceRilasciatoBean {
	
	private int idUtente;
	private int codice;
	private Instant expirationDate;
	
	
	public CodiceRilasciatoBean() {
		this.codice = 0;
		this.expirationDate=null;
		this.idUtente = 0;
	}
	
	public CodiceRilasciatoBean(int idUtente, int codice, Instant expirationDate) {
		this.idUtente = idUtente;
		this.codice = codice;
		this.expirationDate = expirationDate;
	}
	
	
	public int getIdUtente() {
		return idUtente;
	}
	public void setIdUtente(int idUtente) {
		this.idUtente = idUtente;
	}
	public int getCodice() {
		return codice;
	}
	public void setCodice(int codice) {
		this.codice = codice;
	}
	public Instant getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(Instant expirationDate) {
		this.expirationDate = expirationDate;
	}
	
	@Override
	public String toString() {
		return "CodiceRilasciatoBean [idUtente=" + idUtente + ", codice=" + codice + ", expirationDate="
				+ expirationDate + "]";
	}
	
	
	
	
	
}
