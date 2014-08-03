package com.bb.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bb.game.gameObjects.GameState;
import com.bb.game.gameObjects.Player.AbstractPlayer;
import com.bb.game.gameObjects.services.PlayerService;
import com.bb.game.gameObjects.services.PlayerServiceImpl;
import com.bb.game.model.GameModel;
import com.bb.game.model.GameModelHost;
import com.bb.network.AbstractNetworkGateway;
import com.bb.network.NetworkGameGatewayInterface;

/**
 * The game view needs to support the functionality of getting updates from the
 * hostServer and update the GUI components accordingly.
 * 
 * 
 * @author Enzo
 * 
 */
public abstract class AbstractGameView implements Observer {
	protected Logger log = LoggerFactory.getLogger(AbstractGameView.class);
	

	//How the game model will be affected by Server, if there are any
	protected NetworkGameGatewayInterface networkGameGatewayInterface;
	
	// Define what observable/observe variables
	protected GameState gameState;
	protected GameModel gameModel;
	
	//Thread pool for view
	protected ExecutorService executorService = Executors.newCachedThreadPool();
	protected Thread gameViewThread;
	
	//Services for use View use
	protected PlayerService playerService = new PlayerServiceImpl();
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	/**
	 * Override how this Observer will react when there is an update
	 */
	@Override
	public void update(Observable observable, final Object data) {
		if(gameViewThread != null && gameViewThread.isAlive()){
			gameViewThread.interrupt();
		}
		
		
		if (observable == gameModel) {
			log.debug("update(), data=" + data);
			
			Runnable runnable = new Runnable() {
				@Override
				public void run() {
					if (data instanceof GameState) {
						GameState currentGameState = (GameState) data;
						handleGameStateChange(currentGameState);
					} else {
						log.warn("update(), unknown data update " + data);
					}
				}
			};
			gameViewThread = new Thread(runnable);
			gameViewThread.setName(this.getClass().getName());
			gameViewThread.start();
		}
		//TODO: do we need to also observe when the server sends messages to the client?
	}

	private void handleGameStateChange(GameState currentGameState) {
		switch (currentGameState) {
		case STATE_GAME_UNINITIALIZED:
			break;
		case STATE_QUESTION:
			handleStateQuestion();
			break;
		case STATE_GUESS:
			handleStateGuess();
			break;
		case STATE_BET:
			handleStateBet();
			break;
		case STATE_ANSWER:
			handleStateAnswer();
			break;
		case STATE_ROUND_FINISHED:
			handleStateRoundEnd();
			break;
		case STATE_GAME_FINISHED:
			handleStateGameFinished();
			break;
		}
	}

	protected abstract void handleStateQuestion();

	protected abstract void handleStateGuess();

	protected abstract void handleStateBet();

	protected abstract void handleStateAnswer();

	protected abstract void handleStateRoundEnd();

	protected abstract void handleStateGameFinished();
}
