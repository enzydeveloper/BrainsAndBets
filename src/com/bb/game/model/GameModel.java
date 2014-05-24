/**
 * 
 */
package com.bb.game.model;

import java.util.ArrayList;
import java.util.Observable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bb.game.gameObjects.AbstractPlayer;
import com.bb.game.gameObjects.AbstractQuestion;
import com.bb.game.gameObjects.GameQuestions;
import com.bb.game.gameObjects.GameState;

/**
 * Abstract class to provide the groundwork for the Game model information that
 * will be accessed by the View
 * 
 * @author Enzo
 * 
 */
public abstract class GameModel extends Observable implements GameControllerInterface{
	Logger log = LoggerFactory.getLogger(GameModel.class);

	//Timer variables
	CountDownTimer gameTimer;
	
	// List of players
	ArrayList<AbstractPlayer> playerList;

	// Game variables
	GameQuestions gameQuestions;
	AbstractQuestion currentQuestion;
	int numberOfRounds;
	boolean isGameFinished = false;
	
	//Communication between the model and observers
	GameState gameState;
	
	public GameModel(ArrayList<AbstractPlayer> playerList,
			GameQuestions gameQuestions, int numberOfRounds) {

		this.gameState = GameState.STATE_GAME_UNINITIALIZED;
		this.playerList = playerList;
		this.gameQuestions = gameQuestions;
		this.numberOfRounds = numberOfRounds;
	}
	
	/**
	 * @return the playerList
	 */
	public ArrayList<AbstractPlayer> getPlayerList() {
		return playerList;
	}
	/**
	 * @return the gameQuestions
	 */
	public GameQuestions getGameQuestions() {
		return gameQuestions;
	}
	/**
	 * @return the currentQuestion
	 */
	public AbstractQuestion getCurrentQuestion() {
		return currentQuestion;
	}
	/**
	 * @return the numberOfRounds
	 */
	public int getNumberOfRounds() {
		return numberOfRounds;
	}
	/**
	 * @return the isGameFinished
	 */
	public boolean isGameFinished() {
		return isGameFinished;
	}
	/**
	 * @return the gameState
	 */
	public GameState getGameState() {
		return gameState;
	}

	
	
	
	
//	/**
//	 * Need to override in sub-classes
//	 */
//	@Override
//	public void setNextQuestion() {
//	}
//	
//	/**
//	 * Need to override in sub-classes
//	 */
//	@Override
//	public void displayQuestionState() {
//	}
//	
//	/**
//	 * Need to override in sub-classes
//	 */
//	@Override
//	public void waitOnQuestionState() {
//	}
//	
//	/**
//	 * Need to override in sub-classes
//	 */
//	@Override
//	public void displayGuessState() {
//	}
//	
//	/**
//	 * Need to override in sub-classes
//	 */
//	@Override
//	public void waitOnGuessState() {
//	}
//
//	/**
//	 * Need to override in sub-classes
//	 */
//	@Override
//	public void displayAnswerState() {
//	}
//
//	/**
//	 * Need to override in sub-classes
//	 */
//	@Override
//	public void waitOnAnswerState() {
//	}
//
//	/**
//	 * Need to override in sub-classes
//	 */
//	@Override
//	public void displayEndRoundState() {
//	}
//
//	/**
//	 * Need to override in sub-classes
//	 */
//	@Override
//	public void waitOnEndRoundState() {
//	}
//
//	/**
//	 * Need to override in sub-classes
//	 */
//	@Override
//	public void displayEndGameState() {
//	}
	
	
}
