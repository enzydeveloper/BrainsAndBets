/**
 * 
 */
package com.bb.view.java.cli;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Observer;
import java.util.Scanner;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.bb.game.gameObjects.GameQuestions;
import com.bb.game.gameObjects.Player.AbstractPlayer;
import com.bb.game.gameObjects.Player.HumanPlayer;
import com.bb.game.gameObjects.services.PlayerServiceImpl;
import com.bb.game.model.GameModelClient;
import com.bb.game.model.GameModelHost;
import com.bb.network.NetworkGameGatewayImplJwebsocket;
import com.bb.network.NetworkGameGatewayImplLocal;
import com.bb.view.AbstractGameView;

/**
 * @author Enzo
 * 
 */
public class GameCliView extends AbstractGameView implements Observer, Runnable{
	//Global game variables
	protected ArrayList<String> availableCategories = new ArrayList<String>();
	protected HashMap<UUID, AbstractPlayer> gamePlayers = new HashMap<UUID, AbstractPlayer>();
	
	public static void main(String[] args) {
		GameCliView game = new GameCliView();
		Thread gameViewThread = new Thread(game);
		gameViewThread.setName(game.getClass().getName());
		gameViewThread.start();
	}
	
	
	private boolean isHosting = true;

	/**
	 * Default constructor
	 */
	public GameCliView() {
//		this.promptForGameInfo();
	}

	@Override
	public void run() {
		this.promptForGameInfo();
//		try {
//			log.debug("Waiting...");
//			this.wait();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

	private void initializeTestVariables() {
		availableCategories.add("Math");
		availableCategories.add("Biology");
		availableCategories.add("Chemistry");
		availableCategories.add("Computer Science");
		availableCategories.add("NullGameQuestionsCategory");
	}

	private void promptForGameInfo() {
		initializeTestVariables();
		Scanner keyboard = new Scanner(System.in);
		String playerNameInput;
		String questionCategoryInput;
		int numOfQuestions = 0;

		/* ******************************
		 * Ask the player for their name*******************************
		 */
		System.out.println("Please enter your name");

		playerNameInput = keyboard.next();
		if (playerNameInput.isEmpty()) {
			System.out
					.print("Your name was not valid, defaulting your player name");
		} else {
			HumanPlayer player = new HumanPlayer(playerNameInput);
			this.playerService.addPlayerToGame(player, this.gamePlayers);
		}

		/* ******************************************
		 * Display a menu of categories of questions
		 * *******************************************
		 */
		System.out.println("Please choose a question category");
		System.out.println("------------Question Categories------------");

		for (int i = 0; i < availableCategories.size(); i++) {
			System.out.println(availableCategories.get(i));
		}

		questionCategoryInput = keyboard.next();
		while (!availableCategories.contains(questionCategoryInput)
				|| questionCategoryInput.isEmpty()) {
			System.out.println("Unrecognizable category, please enter another");
			questionCategoryInput = keyboard.next();
		}
		System.out.println("Choosing category: " + questionCategoryInput);

		/* **************************************
		 * Ask how many questions they want to play
		 * **************************************
		 */
		while (numOfQuestions == 0) {
			try {
				System.out
						.println("Please enter in number of questions to play");
				numOfQuestions = Integer.parseInt(keyboard.next());
			} catch (NumberFormatException ex) {
				System.out
						.println("You did not enter a valid number of questions");
			}
		}

		/*
		 * Gather the number of questions needed for the game
		 */
		GameQuestions gameQuestions = new GameQuestions(questionCategoryInput,
				numOfQuestions);

		if(isHosting ){
			log.debug("We are hosting the server");
			
			// Define the game object that will run the game
			gameModel = new GameModelHost(this.gamePlayers, gameQuestions,
					numOfQuestions);
						
			// Tell the model that this GUI is observing changes in the model
			gameModel.addObserver(this);
			gameModel.startGame();
	
			// TODO: instead of starting game right away, we want to launch a game
			// lobby on the server
			// GameJavaDesktop gameJavaDesktop = new GameJavaDesktop(game);
			
			//Specify what kind of implementation of network we want to use to play the game on.
			networkGameGatewayInterface = new NetworkGameGatewayImplLocal();
			((NetworkGameGatewayImplLocal)networkGameGatewayInterface).addObserver(this);
		}else{
			log.debug("We are being a client to the server");
			
			// Define the game object that will run the game
			gameModel = new GameModelClient(this.gamePlayers, gameQuestions,
					numOfQuestions);
			// Tell the model that this GUI is observing changes in the model
			gameModel.addObserver(this);
			
			// TODO: As a client, we don't need to run the game, just need to listen to Server
			 
			//Specify what kind of implementation of network we want to use to play the game on.
			networkGameGatewayInterface = new NetworkGameGatewayImplLocal();
			((NetworkGameGatewayImplLocal)networkGameGatewayInterface).addObserver(this);
		}
	}

	@Override
	protected void handleStateQuestion() {
		log.debug("handleStateQuestion()");
		System.out.println("::::::::::::::::::The question::::::::::::::::::");
		System.out.println(gameModel.getCurrentQuestion());
	}
	@Override
	protected void handleStateGuess() {
		log.debug("handleStateGuess()");
		
//		String guessInput;
		
		System.out.println("::::::::::::::::::Place your Guess now::::::::::::::::::");
		Scanner keyboard = new Scanner(System.in);
		String guessInput = "";
		
		try {
			guessInput = keyboard.next();
		} catch (NoSuchElementException ex) {
			log.debug("Couldn't retrieve guess input, defaulting to none");
			guessInput = "";
		}
		
		do{
			System.out.print("Enter in a valid guess");
		} while (guessInput.isEmpty());
		
		System.out.print("You guessed: " +guessInput);
		keyboard.close();
		
	}
	@Override
	protected void handleStateBet() {
		log.debug("handleStateBet()");
		System.out.println("::::::::::::::::::Place your Bet now::::::::::::::::::");
	}

	@Override
	protected void handleStateAnswer() {
		log.debug("handleStateAnswer()");
		System.out.println("::::::::::::::::::THE ANSWER!::::::::::::::::::");
		System.out.println(gameModel.getCurrentQuestion().getAnswerString());
	}

	@Override
	protected void handleStateRoundEnd() {
		log.debug("handleStateRoundEnd()");
		System.out.println("::::::::::::::::::END ROUND::::::::::::::::::");
	}

	@Override
	protected void handleStateGameFinished() {
		log.debug("handleStateGameFinished()");
		System.out.println("::::::::::::::::::END GAME::::::::::::::::::");
	}


}
