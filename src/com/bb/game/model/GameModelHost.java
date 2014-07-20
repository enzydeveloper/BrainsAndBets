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

import com.bb.game.gameObjects.AbstractPlayer;
import com.bb.game.gameObjects.GameQuestions;
import com.bb.game.gameObjects.GameState;
import com.bb.game.gameObjects.GameStateWaitTime;
import com.bb.view.java.gui.CountDownTimerJava;

/**
 * This thread runs through the whole game. All logic is contained in this
 * thread. GUI should never do game logic, any methods to get/set game variables
 * should be defined in the game object to access the thread.
 * 
 * @author Enzo
 * 
 */
public class GameModelHost extends GameModel implements Runnable{
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
			// displayGameResults();
			numberOfRounds--;
		}
		displayEndGameState();
		log.debug("run() Game finished!");
	}
	
	
	/**
	 * We can continue with the game if all players are ready for the next round
	 */
	private boolean checkIfPlayersAreReady() {
		// TODO Need to actually implement the check instead of returning false as default
		return false;
	}
	
	
	@Override
	public void setNextQuestion() {
		log.debug("setNextQuestion:");
		currentQuestion = gameQuestions.popQuestionFromList();
	}

	@Override
	public void displayQuestionState() {
		log.debug("displayQuestionState:");
		this.gameState = GameState.STATE_QUESTION;
		this.setChanged();
		this.notifyObservers(gameState);
	}

	@Override
	public void waitOnQuestionState() {
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

	@Override
	public void displayGuessState() {
		log.debug("displayGuessState:");
		this.gameState = GameState.STATE_GUESS;
		this.setChanged();
		this.notifyObservers(gameState);
	}

	@Override
	public void waitOnGuessState() {
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

	@Override
	public void displayAnswerState() {
		log.debug("displayAnswerState:");
		this.gameState = GameState.STATE_ANSWER;
		this.setChanged();
		this.notifyObservers(gameState);
	}

	@Override
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

	@Override
	public void displayEndRoundState() {
		log.debug("displayEndRoundState:");
		this.gameState = GameState.STATE_ROUND_FINISHED;
		this.setChanged();
		this.notifyObservers(gameState);
	}
	
	@Override
	public void waitOnEndRoundState() {
		log.debug("waitOnAnswerState()");
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

	@Override
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
				log.debug("onTick() " + millisUntilFinished);
			}

			@Override
			public void onFinish() {
				log.debug("onFinish() ");
				setFinished(true);
			}
		}.start();
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
