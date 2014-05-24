/**
 * 
 */
package com.bb.game.gameObjects;

/**
 * Interface to provide different implementations of what players can do in a game
 * 
 * @author Enzo
 *
 */
public interface PlayerActions {
	
	/*
	 * A bot player will have special logic to place a bet
	 */
	boolean placeBet();
}
