package com.bb.network.gateway;


import java.util.Observable;





import com.bb.game.gameObjects.GameState;

/**
 * Needed to allow the networkGateway to be 'observable'
 * 
 * Can't extend on the NetworkGatewayInterface
 * @author Enzo
 *
 */
public abstract class AbstractNetworkGateway extends Observable implements NetworkGameGatewayInterface{
	GameState gameState = GameState.STATE_GAME_UNINITIALIZED;
	
	protected void updateState(final GameState gameState){
		this.gameState = gameState;
		this.setChanged();
		this.notifyObservers(this.gameState);
	}
}
