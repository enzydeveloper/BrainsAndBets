/**
 * 
 */
package com.bb.game.model;

import java.util.ArrayList;
import java.util.Map;
import java.util.Observable;
import java.util.UUID;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bb.game.gameObjects.GameQuestions;
import com.bb.game.gameObjects.GameState;
import com.bb.game.gameObjects.GameStateWaitTime;
import com.bb.game.gameObjects.Player.AbstractPlayer;
import com.bb.view.java.gui.CountDownTimerJava;

/**
 * This thread runs through the whole game. All logic is contained in this
 * thread. GUI should never do game logic, any methods to get/set game variables
 * should be defined in the game object to access the thread.
 * 
 * @author Enzo
 * 
 */
public class GameModelHost extends GameModel {
	Logger log = LoggerFactory.getLogger(GameModelHost.class);

	/**
	 * 
	 * @param gamePlayers
	 * @param gameQuestions
	 * @param numberOfRounds
	 */
	public GameModelHost(Map<UUID, AbstractPlayer> gamePlayers,
			GameQuestions gameQuestions, int numberOfRounds) {
		super(gamePlayers, gameQuestions, numberOfRounds);
	}

	
	
	/**
	 * Start the game in a separate thread from the View
	 */
	@Override
	public void startGame() {		
		//Launch the gameModel in a separate thread than the GUI
		this.gameModelThread = new Thread(this);
		gameModelThread.setName(this.getClass().getName());
		gameModelThread.start();
		
	}

	@Override
	public void stopGame() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void kickPlayer() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void voteQuestionUp() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void voteQuestionDown() {
		// TODO Auto-generated method stub
		
	}
}
