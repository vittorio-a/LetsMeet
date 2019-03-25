package it.unisa.studenti.letsmeet.model.filter;

import java.math.BigDecimal;
import java.util.Map;

public class FilterFactory {
	
	private static final String LATITUDINE_KEY = "latitudine";
	private static final String LONGITUDINE_KEY = "longitudine";
	private static final String DISTANZA_KEY = "distanza"; //km

	
	
	public static <T> Filter<T> getFilter(TipoFiltro tipoFiltro, Map<String, String[]> parameters) throws FilterFactroyException{
		switch (tipoFiltro) {
		case DISTANZA:
			if(!(parameters.containsKey(LATITUDINE_KEY) && parameters.containsKey(LONGITUDINE_KEY) && parameters.containsKey(DISTANZA_KEY))){
				throw new FilterFactroyException("Manca un parametro");
			}
			
			BigDecimal latitudine = null, longitudine = null;
			Double distanza = null;
			
			try {
				latitudine = new BigDecimal(parameters.get(LATITUDINE_KEY)[0]);
				longitudine = new BigDecimal(parameters.get(LONGITUDINE_KEY)[0]);
				distanza = Double.parseDouble(parameters.get(DISTANZA_KEY)[0]);
				
			}catch (Exception e) {
				throw new FilterFactroyException("Parametri non corretti");

			}
			return (Filter<T>) new DistanceFilter(latitudine, longitudine, distanza);
		case ALL:
			return new AllFilter<T>();
		default:
			return null;
		}
	}
}
