/**
 * 
 */
package com.bb.game.model;

import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bb.game.controller.GameController;
import com.bb.game.gameObjects.GameQuestions;
import com.bb.game.gameObjects.GameState;
import com.bb.game.gameObjects.GameStateWaitTime;
import com.bb.game.gameObjects.Guess;
import com.bb.game.gameObjects.Player.AbstractPlayer;
import com.bb.game.gameObjects.Question.AbstractQuestion;
import com.bb.game.gameObjects.services.PlayerService;
import com.bb.game.gameObjects.services.PlayerServiceImpl;
import com.bb.game.model.services.GameModelService;
import com.bb.game.model.services.GameModelServiceImpl;
import com.bb.network.gateway.AbstractNetworkGateway;
import com.bb.network.gateway.NetworkGameGatewayInterface;
import com.bb.view.java.gui.CountDownTimerJava;

/**
 * Provide the groundwork for the Game model information that will be accessed
 * by the View
 * 
 * Shared logic gameModel for the host and the client
 * 
 * @author Enzo
 * 
 */
public abstract class GameModel extends Observable implements
		Runnable {
	Logger log = LoggerFactory.getLogger(GameModel.class);

	// Timer variables
	CountDownTimer gameTimer;

	// Players in the game
	Map<UUID, AbstractPlayer> gamePlayers;

	// Game variables
	GameQuestions gameQuestions;
	AbstractQuestion currentQuestion;
	int numberOfRounds;
	boolean isGameFinished = false;

	// Communication between the model and observers
	GameState gameState;

	// Thread that runs model separate from view thread
	Thread gameModelThread;

	// Services to use in Model
	GameModelService gameModelService = new GameModelServiceImpl();

	/**
	 * default constructor
	 */
	public GameModel(){
		
	}
	
	public GameModel(AbstractNetworkGateway abstractNetworkGateway,
			Map<UUID, AbstractPlayer> gamePlayers, GameQuestions gameQuestions, int numberOfRounds) {
		this.gameState = GameState.STATE_GAME_UNINITIALIZED;
		this.gamePlayers = gamePlayers;
		this.gameQuestions = gameQuestions;
		this.numberOfRounds = numberOfRounds;
//		this.abstractNetworkGateway = abstractNetworkGateway;
		
		//The gameModel will change based on what the NetworkGameGateway tells it
//		abstractNetworkGateway.addObserver(this);
	}
	
	/**
	 * Operates the entire game sequence
	 */
	@Override
	public void run() {
		log.debug("run()");
		while (!isGameFinished && numberOfRounds > 0) {
			setNextQuestion();
			displayQuestionState();
			waitOnQuestionState();
			displayGuessState();
			waitOnGuessState();
			displayAnswerState();
			waitOnAnswerState();
			displayEndRoundState();
			waitOnEndRoundState();
//			 displayGameResults();
			numberOfRounds--;
		}
		displayEndGameState();
		log.debug("run() Game finished!");
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
	

	/**
	 * 
	 */
	public void calculateEndRoundResults() {
		String functionName = "calculateEndRoundResults";
		log.debug("function={}", functionName);
		gameModelService.calculateEndRoundResults(gamePlayers, currentQuestion);
	}
	
	public void handleGuessUpdate(UUID playerKey, Guess guess){
		
	}
	
	/**
	 * We can continue with the game if all players are ready for the next round
	 */
	protected boolean checkIfPlayersAreReady() {
		boolean allPlayersAreReady = true;
		Set<UUID> playerKeySet = gamePlayers.keySet();
		for(UUID playerUUID : playerKeySet){
			if(!gamePlayers.get(playerUUID).isReady())
			{
				allPlayersAreReady = false;
			}
		}
		return allPlayersAreReady;
	}
	

	/**
	 * Create an abstract timer and implement actions to take while timer is
	 * active
	 */
	public void startTimer(final int waitTime) {
		log.debug("startTimer WaitTime: " + waitTime);
		gameTimer = (CountDownTimerJava) new CountDownTimerJava(waitTime, 1000) {

			@Override
			public void onTick(long millisUntilFinished) {
				log.trace("onTick() " + millisUntilFinished);
			}

			@Override
			public void onFinish() {
				log.debug("onFinish() ");
				setFinished(true);
			}
		}.start();
	}

	// ---SETTERS & GETTERS

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
}
