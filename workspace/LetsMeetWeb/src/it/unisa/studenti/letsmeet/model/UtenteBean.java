package it.unisa.studenti.letsmeet.model;

import java.time.Instant;

/**
 * 
 * Descrive l’utente registrazione
 *
 */

public class UtenteBean {
	
	private int idUtente;
	private float feedbackUtente;
	private Instant reactivationDate;
	private CredentialsBean credentials;
	private String email;
	
	/**
	 * Costruttore
	 * @param idUtente id univoco dell'utente
	 * @param email email dell'utente
	 * @param feedbackUtente feedback dell'utente
	 * @param reactivationDate data di riattivazione dell'utente
	 * @param credentials credenziali dell'utente 
	 * @param isVisible stato dell'utente 
	 */
	public UtenteBean(int idUtente, String email, float feedbackUtente, Instant reactivationDate, CredentialsBean credentials) {
		this.idUtente = idUtente;
		this.feedbackUtente = feedbackUtente;
		this.reactivationDate = reactivationDate;
		this.credentials = credentials;
		this.email = email;
	}
	
	
	public UtenteBean() {
		email = null;
		
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UtenteBean other = (UtenteBean) obj;
		if (credentials == null) {
			if (other.credentials != null)
				return false;
		} else if (!credentials.equals(other.credentials))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (Float.floatToIntBits(feedbackUtente) != Float.floatToIntBits(other.feedbackUtente))
			return false;
		if (idUtente != other.idUtente)
			return false;
		if (reactivationDate == null) {
			if (other.reactivationDate != null)
				return false;
		} else if (!reactivationDate.equals(other.reactivationDate))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "UtenteBean [idUtente=" + idUtente + ", email=" + email + ", feedbackUtente=" + feedbackUtente
				+ ", reactivationDate=" + reactivationDate + ", credentials=" + credentials + "]";
	}
	
	
}
