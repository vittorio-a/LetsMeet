package it.unisa.studenti.letsmeet.model;

import java.math.BigDecimal;

public class PosizioneBean {
	
	
	private BigDecimal longitudine;
	private BigDecimal latitudine;
	private String formattedAdress;
	private int idComune;
	
	public PosizioneBean(BigDecimal longitudine, BigDecimal latitudine, String formattedAdress, int idComune) {
		this.longitudine = longitudine;
		this.latitudine = latitudine;
		this.formattedAdress = formattedAdress;
		this.idComune = idComune;
	}
	
	
	public BigDecimal getLongituidine() {
		return longitudine;
	}
	public void setLongituidine(BigDecimal longituidine) {
		this.longitudine = longituidine;
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
	public int getIdComune() {
		return idComune;
	}
	public void setIdComune(int idComune) {
		this.idComune = idComune;
	}
	@Override
	public String toString() {
		return "PosizioneBean [longituidine=" + longitudine + ", latitudine=" + latitudine + ", formattedAdress="
				+ formattedAdress + ", idComune=" + idComune + "]";
	}
	
	
	
	
}
