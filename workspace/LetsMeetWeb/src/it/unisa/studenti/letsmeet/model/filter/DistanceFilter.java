package it.unisa.studenti.letsmeet.filter;

import java.math.BigDecimal;

import it.unisa.studenti.letsmeet.model.EventoBean;
import it.unisa.studenti.letsmeet.model.PosizioneBean;

public class DistanceFilter implements Filter<EventoBean> {
	
	
	private BigDecimal latitudineUtente;
	private BigDecimal longitudineUtente;
	private double distance;
	private DistanceComparator distanceComparator;
	
	public DistanceFilter(BigDecimal latidudine, BigDecimal longitudine, double distance) {
		latitudineUtente = latidudine;
		longitudineUtente = longitudine;
		this.distance = distance;
		distanceComparator = new DistanceComparator(latidudine, longitudine);
	}
	
	@Override
	public boolean check(EventoBean item) {
		BigDecimal lat1;
		BigDecimal lon1;
		PosizioneBean posizioneEvento = item.getPosizione();
		lat1 = posizioneEvento.getLatitudine();
		lon1 = posizioneEvento.getLongitudine();
		double distanceFromUser = distanceComparator.distanceBetweenTwoPoints(latitudineUtente.doubleValue(), longitudineUtente.doubleValue(),
				lat1.doubleValue(), lon1.doubleValue());
		
		return Double.compare(distanceFromUser, distance) <= 0;
		
	}

}
