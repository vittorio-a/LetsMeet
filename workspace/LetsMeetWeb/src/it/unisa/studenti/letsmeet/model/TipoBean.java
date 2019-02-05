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


	public void setIdTipo(int idTipo) {
		this.idTipo = idTipo;
	}
	
	@Override
	public String toString() {
		return "TipoBean [idTipo=" + idTipo + ", nomeTipo=" + nomeTipo + ", descrizione=" + descrizione + "]";
	}

	
	
	
	
	
}
