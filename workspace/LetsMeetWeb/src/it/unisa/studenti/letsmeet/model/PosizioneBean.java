package it.unisa.studenti.letsmeet.model;

import java.math.BigDecimal;

public class PosizioneBean {
	
	
	private BigDecimal longitudine;
	private BigDecimal latitudine;
	private String formattedAdress;
	private String nomeComune;
	private int idComune;
	private String nomeProvincia;
	private int idProvincia;
	private String sigla;
	private String nomeRegione;
	private int idRegione;
	private String nomeNazione;
	private int idNazione;
	
	
	public PosizioneBean() {
		this.formattedAdress = null;
		this.idComune = 0;
		this.idNazione = 0;
		this.idProvincia = 0;
		this.latitudine = null;
		this.longitudine = null;
		this.nomeComune = null;
		this.nomeNazione = null;
		this.nomeProvincia = null;
		this.nomeRegione = null;
		this.sigla = null;
	}
	
	
	
	public PosizioneBean(BigDecimal longitudine, BigDecimal latitudine, String formattedAdress, String nomeComune,
			int idComune, String nomeProvincia, int idProvincia, String sigla, String nomeRegione, int idRegione,
			String nomeNazione, int idNazione) {
		super();
		this.longitudine = longitudine;
		this.latitudine = latitudine;
		this.formattedAdress = formattedAdress;
		this.nomeComune = nomeComune;
		this.idComune = idComune;
		this.nomeProvincia = nomeProvincia;
		this.idProvincia = idProvincia;
		this.sigla = sigla;
		this.nomeRegione = nomeRegione;
		this.idRegione = idRegione;
		this.nomeNazione = nomeNazione;
		this.idNazione = idNazione;
	}
	public BigDecimal getLongitudine() {
		return longitudine;
	}
	public void setLongitudine(BigDecimal longitudine) {
		this.longitudine = longitudine;
	}
	public BigDecimal getLatitudine() {
		return latitudine;
	}
	public void setLatitudine(BigDecimal latitudine) {
		this.latitudine = latitudine;
	}
	public String getFormattedAdress() {
		return formattedAdress;
	}
	public void setFormattedAdress(String formattedAdress) {
		this.formattedAdress = formattedAdress;
	}
	public String getNomeComune() {
		return nomeComune;
	}
	public void setNomeComune(String nomeComune) {
		this.nomeComune = nomeComune;
	}
	public int getIdComune() {
		return idComune;
	}
	public void setIdComune(int idComune) {
		this.idComune = idComune;
	}
	public String getNomeProvincia() {
		return nomeProvincia;
	}
	public void setNomeProvincia(String nomeProvincia) {
		this.nomeProvincia = nomeProvincia;
	}
	public int getIdProvincia() {
		return idProvincia;
	}
	public void setIdProvincia(int idProvincia) {
		this.idProvincia = idProvincia;
	}
	public String getSigla() {
		return sigla;
	}
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	public String getNomeRegione() {
		return nomeRegione;
	}
	public void setNomeRegione(String nomeRegione) {
		this.nomeRegione = nomeRegione;
	}
	public int getIdRegione() {
		return idRegione;
	}
	public void setIdRegione(int idRegione) {
		this.idRegione = idRegione;
	}
	public String getNomeNazione() {
		return nomeNazione;
	}
	public void setNomeNazione(String nomeNazione) {
		this.nomeNazione = nomeNazione;
	}
	public int getIdNazione() {
		return idNazione;
	}
	public void setIdNazione(int idNazione) {
		this.idNazione = idNazione;
	}
	
	@Override
	public String toString() {
		return "PosizioneBean [longitudine=" + longitudine + ", latitudine=" + latitudine + ", formattedAdress="
				+ formattedAdress + ", nomeComune=" + nomeComune + ", idComune=" + idComune + ", nomeProvincia="
				+ nomeProvincia + ", idProvincia=" + idProvincia + ", sigla=" + sigla + ", nomeRegione=" + nomeRegione
				+ ", idRegione=" + idRegione + ", nomeNazione=" + nomeNazione + ", idNazione=" + idNazione + "]";
	}
	
	
	
	
}
