/**
 * 
 */
package com.bb.game.model;

import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Observable;
import java.util.Set;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bb.game.gameObjects.GameQuestions;
import com.bb.game.gameObjects.GameState;
import com.bb.game.gameObjects.GameStateWaitTime;
import com.bb.game.gameObjects.Player.AbstractPlayer;
import com.bb.game.gameObjects.Question.AbstractQuestion;
import com.bb.game.gameObjects.services.PlayerService;
import com.bb.game.gameObjects.services.PlayerServiceImpl;
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
		GameModelControllerInterface, Runnable {
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
	PlayerService playerService = new PlayerServiceImpl();

	public GameModel(Map<UUID, AbstractPlayer> gamePlayers,
			GameQuestions gameQuestions, int numberOfRounds) {
		this.gameState = GameState.STATE_GAME_UNINITIALIZED;
		this.gamePlayers = gamePlayers;
		this.gameQuestions = gameQuestions;
		this.numberOfRounds = numberOfRounds;
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

	/**
	 * 
	 */
	public void calculateEndRoundResults() {
		String functionName = "calculateEndRoundResults";
		log.debug("function={}", functionName);

		for (Entry<UUID, AbstractPlayer> entry : gamePlayers.entrySet()) {
			System.out.println(entry.getKey() + "/" + entry.getValue());

			AbstractPlayer player = entry.getValue();
			if (player.getGuess().getGuessString() == currentQuestion
					.getAnswerString()) {
				playerService.updatePlayerWinnings(player, currentQuestion,
						numberOfRounds, numberOfRounds, numberOfRounds);
			}
		}
	}
	
	/**
	 * We can continue with the game if all players are ready for the next round
	 */
	private boolean checkIfPlayersAreReady() {
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
	

	public void setNextQuestion() {
		log.debug("setNextQuestion:");
		currentQuestion = gameQuestions.popQuestionFromList();
	}

	public void displayQuestionState() {
		log.debug("displayQuestionState:");
		this.gameState = GameState.STATE_QUESTION;
		this.setChanged();
		this.notifyObservers(gameState);
	}

	public void waitOnQuestionState() {
		log.debug("waitOnGuessState()");
		startTimer(GameStateWaitTime.GUESS_STATE_WAIT_TIME.getTimeToWait());
		while (!gameTimer.isFinished() || checkIfPlayersAreReady()) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
				// handle the exception...
				// For example consider calling
				// Thread.currentThread().interrupt(); here.
			}
		}
	}

	public void displayGuessState() {
		log.debug("displayGuessState:");
		this.gameState = GameState.STATE_GUESS;
		this.setChanged();
		this.notifyObservers(gameState);
	}

	public void waitOnGuessState() {
		log.debug("waitOnQuestionState()");
		startTimer(GameStateWaitTime.QUESTION_STATE_WAIT_TIME.getTimeToWait());
		while (!gameTimer.isFinished() || checkIfPlayersAreReady()) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
				// handle the exception...
				// For example consider calling
				// Thread.currentThread().interrupt(); here.
			}
		}
	}

	public void displayAnswerState() {
		log.debug("displayAnswerState:");
		this.gameState = GameState.STATE_ANSWER;
		this.setChanged();
		this.notifyObservers(gameState);
	};

	public void waitOnAnswerState() {
		log.debug("waitOnAnswerState()");
		startTimer(GameStateWaitTime.ANSWER_STATE_WAIT_TIME.getTimeToWait());
		while (!gameTimer.isFinished() || checkIfPlayersAreReady()) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
				// handle the exception...
				// For example consider calling
				// Thread.currentThread().interrupt(); here.
			}
		}
	}

	public void displayEndRoundState() {
		log.debug("displayEndRoundState:");
		this.gameState = GameState.STATE_ROUND_FINISHED;
		this.setChanged();
		this.notifyObservers(gameState);
	}

	public void waitOnEndRoundState() {
		log.debug("waitOnEndRoundState:");
		startTimer(GameStateWaitTime.END_ROUND_STATE_WAIT_TIME.getTimeToWait());
		while (!gameTimer.isFinished() || checkIfPlayersAreReady()) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
				// handle the exception...
				// For example consider calling
				// Thread.currentThread().interrupt(); here.
			}
		}
	}

	public void displayEndGameState() {
		log.debug("displayEndGameState:");
		this.gameState = GameState.STATE_GAME_FINISHED;
		this.setChanged();
		this.notifyObservers(gameState);
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
				// log.debug("onTick() " + millisUntilFinished);
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
