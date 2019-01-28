package it.unisa.studenti.letsmeet.model;

public class RatingBean {
	
	
	private int idutente;
	private EventoBean evento;
	private boolean voto;
	public int getIdutente() {
		return idutente;
	}
	
	public RatingBean(int idutente, EventoBean evento, boolean voto) {
		this.idutente = idutente;
		this.evento = evento;
		this.voto = voto;
	}
	
	
	public void setIdutente(int idutente) {
		this.idutente = idutente;
	}
	public EventoBean getEvento() {
		return evento;
	}
	public void setEvento(EventoBean evento) {
		this.evento = evento;
	}
	public boolean isVoto() {
		return voto;
	}
	public void setVoto(boolean voto) {
		this.voto = voto;
	}
	@Override
	public String toString() {
		return "RatingBean [idutente=" + idutente + ", evento=" + evento + ", voto=" + voto + "]";
	}
	
	

}
