package it.unisa.studenti.letsmeet.model;

import java.time.Instant;
import java.util.Date;

/**
 * 
 * Descrive l’utente registrazione
 *
 */

public class UtenteBean {
	
	private int idUtente;
	private float feedbackUtente;
	private Date reactivationDate;
	private CredentialsBean credentials;
	private boolean isVisible;
	
	public UtenteBean() {
		
	}
	
	/**
	 * Costruttore
	 * @param idUtente id univoco dell'utente
	 * @param email email dell'utente
	 * @param feedbackUtente feedback dell'utente
	 * @param reactivationDate data di riattivazione dell'utente
	 * @param credentials credenziali dell'utente 
	 * @param isVisible stato dell'utente 
	 */
	public UtenteBean(int idUtente, String email, float feedbackUtente, Date reactivationDate, CredentialsBean credentials,
			boolean isVisible) {
		this.idUtente = idUtente;
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

	public float getFeedbackUtente() {
		return feedbackUtente;
	}
	public void setFeedbackUtente(float feedbackUtente) {
		this.feedbackUtente = feedbackUtente;
	}
	public Date getReactivationDate() {
		return reactivationDate;
	}
	public void setReactivationDate(Date reactivationDate) {
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
		return "UtenteBean [idUtente=" + idUtente + ", feedbackUtente=" + feedbackUtente
				+ ", reactivationDate=" + reactivationDate + ", credentials=" + credentials.toString() + ", isVisible=" + isVisible
				+ "]";
	}
	
	
}
