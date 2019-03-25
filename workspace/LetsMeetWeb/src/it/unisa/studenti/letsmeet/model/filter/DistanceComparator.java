package it.unisa.studenti.letsmeet.model.filter;

import java.math.BigDecimal;
import java.util.Comparator;

import it.unisa.studenti.letsmeet.model.EventoBean;
import it.unisa.studenti.letsmeet.model.PosizioneBean;

import java.lang.StrictMath;

public class DistanceComparator implements Comparator<EventoBean> {

	private static final BigDecimal RaggioTerrestre = new BigDecimal("6372.795477598"); //km
	
	private BigDecimal latitudineUtente;
	private BigDecimal longitudineUtente;
	
	public DistanceComparator(BigDecimal latidudine, BigDecimal longitudine) {
		latitudineUtente = latidudine;
		longitudineUtente = longitudine;
	}
	
	
	
	@Override
	public int compare(EventoBean arg0, EventoBean arg1) {
		BigDecimal dist0, dist1;
		PosizioneBean pos0 = arg0.getPosizione(), pos1 = arg1.getPosizione();
		dist0 = new BigDecimal(distanceBetweenTwoPoints(latitudineUtente.doubleValue(), longitudineUtente.doubleValue(),
				pos0.getLatitudine().doubleValue(), pos0.getLongitudine().doubleValue()));
		
		dist1 = new BigDecimal(distanceBetweenTwoPoints(latitudineUtente.doubleValue(), longitudineUtente.doubleValue(),
				pos1.getLatitudine().doubleValue(), pos1.getLongitudine().doubleValue()));
		
		return dist0.compareTo(dist1);
	}
	
	//distanza di due punti su coord terrestri
	//distanza (A,B) = R * arccos(sin(latA) * sin(latB) + cos(latA) * cos(latB) * cos(lonA-lonB))

	
	public double distanceBetweenTwoPoints(double latitude0, double longigute0, double latitude1, double longitude1) {
		double addend0 = StrictMath.sin(latitude0) * StrictMath.sin(latitude1);
		double addend1 = StrictMath.cos(latitude0) * StrictMath.cos(latitude1) * StrictMath.cos(longigute0 - longitude1);
		return (RaggioTerrestre.doubleValue() * StrictMath.acos(addend0 + addend1));
	}

}
