package it.unisa.studenti.letsmeet.model;

public class RatingBean {
	
	
	private int idutente;
	private int idEvento;
	private boolean voto;
	
	
	public RatingBean(int idutente, int evento, boolean voto) {
		this.idutente = idutente;
		this.idEvento = evento;
		this.voto = voto;
	}
	
	public int getIdutente() {
		return idutente;
	}
	
	public void setIdutente(int idutente) {
		this.idutente = idutente;
	}
	public int getEvento() {
		return idEvento;
	}
	public void setEvento(int evento) {
		this.idEvento = evento;
	}
	public boolean isVoto() {
		return voto;
	}
	public void setVoto(boolean voto) {
		this.voto = voto;
	}
	@Override
	public String toString() {
		return "RatingBean [idutente=" + idutente + ", evento=" + idEvento + ", voto=" + voto + "]";
	}
	
	

}
