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
	
	
	public int getIdComune() {
		return idComune;
	}


	public void setIdComune(int idComune) {
		this.idComune = idComune;
	}


	public int getIdProvincia() {
		return idProvincia;
	}


	public void setIdProvincia(int idProvincia) {
		this.idProvincia = idProvincia;
	}


	public int getIdRegione() {
		return idRegione;
	}


	public void setIdRegione(int idRegione) {
		this.idRegione = idRegione;
	}


	public int getIdNazione() {
		return idNazione;
	}


	public void setIdNazione(int idNazione) {
		this.idNazione = idNazione;
	}


	public PosizioneBean(BigDecimal longitudine, BigDecimal latitudine, String formattedAdress, String nomeComune,
			String nomeProvincia, String sigla, String nomeRegione, String nomeNazione) {
		this.longitudine = longitudine;
		this.latitudine = latitudine;
		this.formattedAdress = formattedAdress;
		this.nomeComune = nomeComune;
		this.nomeProvincia = nomeProvincia;
		this.sigla = sigla;
		this.nomeRegione = nomeRegione;
		this.nomeNazione = nomeNazione;
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
	public String getNomeProvincia() {
		return nomeProvincia;
	}
	public void setNomeProvincia(String nomeProvincia) {
		this.nomeProvincia = nomeProvincia;
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
	public String getNomeNazione() {
		return nomeNazione;
	}
	public void setNomeNazione(String nomeNazione) {
		this.nomeNazione = nomeNazione;
	}
	@Override
	public String toString() {
		return "PosizioneBean [longitudine=" + longitudine + ", latitudine=" + latitudine + ", formattedAdress="
				+ formattedAdress + ", nomeComune=" + nomeComune + ", nomeProvincia=" + nomeProvincia + ", sigla="
				+ sigla + ", nomeRegione=" + nomeRegione + ", nomeNazione=" + nomeNazione + "]";
	}
	
	
	
	
}
