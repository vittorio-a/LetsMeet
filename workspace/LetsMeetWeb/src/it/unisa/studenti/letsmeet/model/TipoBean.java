package it.unisa.studenti.letsmeet.model;

/**
 *Descriive la tipologia di un evento
 *
 */
public class TipoBean {
	
	

	private int idTipo;
	private String nomeTipo;
	private String descrizione;
	
	
	/**
	 * Costruttore vuoto
	 */
	public TipoBean() {
		this.idTipo = 0;
		this.descrizione = null;
		this.nomeTipo = null;
	}
	
	
	/**Costruttore 
	 * @param idTipo id del Tipo
	 * @param nomeTipo nome del Tipo
	 * @param descrizione descrizione del Tipo
	 */
	public TipoBean(int idTipo , String nomeTipo, String descrizione) {
		this.idTipo = idTipo;
		this.nomeTipo = nomeTipo;
		this.descrizione = descrizione;
	}
	
	
	public String getNomeTipo() {
		return nomeTipo;
	}
	public void setNomeTipo(String nomeTipo) {
		this.nomeTipo = nomeTipo;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	


	public int getIdTipo() {
		return idTipo;
	}
	





	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TipoBean other = (TipoBean) obj;
		if (descrizione == null) {
			if (other.descrizione != null)
				return false;
		} else if (!descrizione.equals(other.descrizione))
			return false;
		if (idTipo != other.idTipo)
			return false;
		if (nomeTipo == null) {
			if (other.nomeTipo != null)
				return false;
		} else if (!nomeTipo.equals(other.nomeTipo))
			return false;
		return true;
	}


	public void setIdTipo(int idTipo) {
		this.idTipo = idTipo;
	}
	
	@Override
	public String toString() {
		return "TipoBean [idTipo=" + idTipo + ", nomeTipo=" + nomeTipo + ", descrizione=" + descrizione + "]";
	}

	
	
	
	
	
}
