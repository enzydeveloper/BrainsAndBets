package com.bb.network;


import java.util.Observable;


import com.bb.game.gameObjects.GameState;

/**
 * Needed to allow the networkGateway to be 'observable'
 * 
 * Can't extend on the NetworkGatewayInterface
 * @author Enzo
 *
 */
public abstract class AbstractNetworkGateway extends Observable{
	GameState gameState = GameState.STATE_GAME_UNINITIALIZED;
	
	protected void updateState(final GameState gameState){
		this.gameState = gameState;
		this.setChanged();
		this.notifyObservers(this.gameState);
	}
}
