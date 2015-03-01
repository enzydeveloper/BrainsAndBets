/**
 * 
 */
package com.bb.network;

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
public interface GenericObserver<ObservedType> {
	public void update(GenericObservable<ObservedType> object, ObservedType data);
}
