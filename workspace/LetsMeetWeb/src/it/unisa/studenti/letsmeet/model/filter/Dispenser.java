package it.unisa.studenti.letsmeet.model.filter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Dispenser <T>{
	private Filter<T> filter;
	private Comparator<T> comparator;
	
	
	public Dispenser(Filter<T> filter, Comparator<T> comparator) {
		this.filter = filter;
		this.comparator = comparator;
	}

	List<T> dispense(List<T> items){
		List <T> filteredItems = new ArrayList<>();
		for(T item : items) {
			if(filter.check(item)) filteredItems.add(item);
		}
		
		Collections.sort(filteredItems, comparator);
		return filteredItems;
	}
	
}
