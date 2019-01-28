package it.unisa.studenti.letsmeet.model;

import java.time.Instant;

/**
 * 
 * Descrive l’utente registrazione
 *
 */

public class UtenteBean {
	
	private int idUtente;
	private String email;
	private float feedbackUtente;
	private Instant reactivationDate;
	private CredentialsBean credentials;
	private boolean isVisible;
	
	/**
	 * Costruttore
	 * @param idUtente id univoco dell'utente
	 * @param email email dell'utente
	 * @param feedbackUtente feedback dell'utente
	 * @param reactivationDate data di riattivazione dell'utente
	 * @param credentials credenziali dell'utente 
	 * @param isVisible stato dell'utente 
	 */
	public UtenteBean(int idUtente, String email, float feedbackUtente, Instant reactivationDate, CredentialsBean credentials,
			boolean isVisible) {
		this.idUtente = idUtente;
		this.email = email;
		this.feedbackUtente = feedbackUtente;
		this.reactivationDate = reactivationDate;
		this.credentials = credentials;
		this.isVisible = isVisible;
	}
	
	
	
	public int getIdUtente() {
		return idUtente;
	}
	public void setIdUtente(int idUtente) {
		this.idUtente = idUtente;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public float getFeedbackUtente() {
		return feedbackUtente;
	}
	public void setFeedbackUtente(float feedbackUtente) {
		this.feedbackUtente = feedbackUtente;
	}
	public Instant getReactivationDate() {
		return reactivationDate;
	}
	public void setReactivationDate(Instant reactivationDate) {
		this.reactivationDate = reactivationDate;
	}
	public CredentialsBean getCredentials() {
		return credentials;
	}
	public void setCredentials(CredentialsBean credentials) {
		this.credentials = credentials;
	}
	public boolean isVisible() {
		return isVisible;
	}
	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}


	@Override
	public String toString() {
		return "UtenteBean [idUtente=" + idUtente + ", email=" + email + ", feedbackUtente=" + feedbackUtente
				+ ", reactivationDate=" + reactivationDate + ", credentials=" + credentials + ", isVisible=" + isVisible
				+ "]";
	}
	
	
}
