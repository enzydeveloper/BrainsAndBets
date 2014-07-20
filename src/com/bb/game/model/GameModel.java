/**
 * 
 */
package com.bb.game.model;

import java.util.ArrayList;
import java.util.Map;
import java.util.Observable;
import java.util.UUID;

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
public abstract class GameModel extends Observable implements GameModelControllerInterface{
	Logger log = LoggerFactory.getLogger(GameModel.class);

	//Timer variables
	CountDownTimer gameTimer;
	
	// Players in the game
	Map<UUID, AbstractPlayer> gamePlayers;

	// Game variables
	GameQuestions gameQuestions;
	AbstractQuestion currentQuestion;
	int numberOfRounds;
	boolean isGameFinished = false;
	
	//Communication between the model and observers
	GameState gameState;
	
	public GameModel(Map<UUID, AbstractPlayer> gamePlayers,
			GameQuestions gameQuestions, int numberOfRounds) {

		this.gameState = GameState.STATE_GAME_UNINITIALIZED;
		this.gamePlayers = gamePlayers;
		this.gameQuestions = gameQuestions;
		this.numberOfRounds = numberOfRounds;
	}
	
	/**
	 * @return the playerList
	 */
	public Map<UUID, AbstractPlayer> getGamePlayers() {
		return gamePlayers;
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
	

	abstract void setNextQuestion();
	abstract void displayQuestionState();
	abstract void waitOnQuestionState();
	abstract void displayGuessState();
	abstract void waitOnGuessState();
	abstract void displayAnswerState();
	abstract void waitOnAnswerState();
	abstract void displayEndRoundState();
	abstract void waitOnEndRoundState();
	abstract void displayEndGameState();

	
	
	
}
