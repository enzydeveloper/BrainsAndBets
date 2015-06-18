/**
 * 
 */
package com.bb.network;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Enzo
 * 
 *         <p>
 *         The normal java Observer pattern uses just the object type as the
 *         data type. This would require that you do type checking every single
 *         time using 'instance of' This class allows us to create an
 *         observer/observable pattern with specific object types
 * 
 *         <p>
 *         <a href=
 *         "http://stackoverflow.com/questions/13362636/a-generic-observer-pattern-in-java"
 *         >Source</a>
 * 
 */
public class GenericObservable<ObservedType> {
	private List<GenericObserver<ObservedType>> observers = new LinkedList<GenericObserver<ObservedType>>();

	public void addObserver(GenericObserver<ObservedType> obs) {
		        if (obs == null) {
		            throw new IllegalArgumentException("Tried to add a null observer");
		        }
		        if (observers.contains(obs)) {
		            return;
		        }
		        observers.add(obs);
		    }

	public void notifyObservers(ObservedType data) {
		for (GenericObserver<ObservedType> obs : observers) {
			obs.update(this, data);
		}
	}
}
