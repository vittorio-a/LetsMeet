package it.unisa.studenti.letsmeet.model;

public class TipoBean {
	
	
	private String nomeTipo;
	private String descrizione;
	
	
	public TipoBean(String nomeTipo, String descrizione) {
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
	@Override
	public String toString() {
		return "TipoBean [nomeTipo=" + nomeTipo + ", descrizione=" + descrizione + "]";
	}
	
	
	
	
	
}
