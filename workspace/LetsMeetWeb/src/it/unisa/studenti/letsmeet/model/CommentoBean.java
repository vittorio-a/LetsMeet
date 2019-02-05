package it.unisa.studenti.letsmeet.model;

import java.time.Instant;

/**Rappresenta un commento ad un evento
 *
 */
public class CommentoBean {
	
	private int idUtente;
	private String contenuto;
	private int idCommento;
	private int idEvento;
	private Instant creationTime;
	
	
	
	/**
	 * Costruttore vuoto
	 */
	public CommentoBean() {
		this.contenuto = null;
		this.creationTime = null;
		this.idCommento = 0;
		this.idEvento = 0;
		this.idUtente = 0;
	};

	/**Costruttore
	 * @param idUtente id dell'utente che ha creato il commento
	 * @param contenuto contenuto del commento
	 * @param idCommento id del commento
	 * @param idEvento id dell'evento a cui è collegato il commento
	 * @param creationTime ora della creazione del commento
	 */
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


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CommentoBean other = (CommentoBean) obj;
		if (contenuto == null) {
			if (other.contenuto != null)
				return false;
		} else if (!contenuto.equals(other.contenuto))
			return false;
		if (creationTime == null) {
			if (other.creationTime != null)
				return false;
		} else if (!creationTime.equals(other.creationTime))
			return false;
		if (idCommento != other.idCommento)
			return false;
		if (idEvento != other.idEvento)
			return false;
		if (idUtente != other.idUtente)
			return false;
		return true;
	}
	
	
}
