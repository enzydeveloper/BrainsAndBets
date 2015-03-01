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

import com.bb.game.gameObjects.Bet;
import com.bb.game.gameObjects.GameQuestions;
import com.bb.game.gameObjects.GameQuestionsUtil;
import com.bb.game.gameObjects.Guess;
import com.bb.game.gameObjects.Player.AbstractPlayer;
import com.bb.game.gameObjects.Player.HumanPlayer;
import com.bb.game.gameObjects.services.BetService;
import com.bb.game.gameObjects.services.BetServiceImpl;
import com.bb.game.gameObjects.services.PlayerService;
import com.bb.game.gameObjects.services.PlayerServiceImpl;
import com.bb.game.gameObjects.services.QuestionService;
import com.bb.game.gameObjects.services.QuestionServiceImpl;
import com.bb.game.model.GameModelClient;
import com.bb.game.model.GameModelHost;
import com.bb.network.gateway.AbstractNetworkGateway;
import com.bb.network.gateway.NetworkGameGatewayImplJwebsocket;
import com.bb.network.gateway.NetworkGameGatewayImplLocal;
import com.bb.view.AbstractGameView;

/**
 * @author Enzo
 * 
 */
public class GameCliView extends AbstractGameView implements Observer, Runnable{
	//Global game variables
	protected ArrayList<String> availableCategories = new ArrayList<String>();
	protected HashMap<UUID, AbstractPlayer> gamePlayers = new HashMap<UUID, AbstractPlayer>();
	
	QuestionService questionService = new QuestionServiceImpl();
	
	PlayerService playerService = new PlayerServiceImpl();
	
	BetService betService = new BetServiceImpl();

	Scanner keyboard = new Scanner(System.in);
	
	UUID currentPlayerUUID;
	
	public static void main(String[] args) {
		GameCliView game = new GameCliView();
		Thread gameViewThread = new Thread(game);
		gameViewThread.setName(game.getClass().getName());
		gameViewThread.start();
	}
	
	
	private boolean isHosting = true;
	private boolean isLocalGame = true;

	/**
	 * Default constructor
	 */
	public GameCliView() {
//		this.promptForGameInfo();
	}

	@Override
	public void run() {
		this.printGameMainMenu();
//		this.promptForGameInfo();
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
	private void printGameMainMenu(){
		String menuPrompt = 
				"=== Main Menu ==="
				+ "\n 1) Launch game"
				+ "\n 2) Create question xml";
		String keyboardInput = "";
		do{
			System.out.println(menuPrompt);
			keyboardInput = keyboard.next();
		}
		while(!keyboardInput.equalsIgnoreCase("1") &&
				!keyboardInput.equalsIgnoreCase("2") );
		
		switch (keyboardInput) {
		case "1":
			launchGame();
			break;
		case "2":
			GameQuestionsUtil.main(null); // quick dirty way of creating questions
			break;
		default:
			printGameMainMenu();
			break;
		}
	}
	
	private void launchGame() {
		promptForGameInfo();
	}
	private void promptForGameInfo() {
		initializeTestVariables();

		String questionCategoryInput = promptForQuestionCategoryInfo();
		int numOfQuestions = promptForNumberOfQuestions();
		
		//Ask for player information
		AbstractPlayer player = promptForPlayerInfo();
		currentPlayerUUID = player.getPlayerUUID();
		this.playerService.addPlayerToGame(player, this.gamePlayers);

		//Ask what type of game we are going to play (ie: network/local)
		this.promptForTypeOfGame();

		/*
		 * Gather the number of questions needed for the game
		 */
		GameQuestions gameQuestions = new GameQuestions(questionCategoryInput,
				numOfQuestions);

		if(isHosting && isLocalGame){
			log.debug("We are hosting the server locally");
			
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
			((NetworkGameGatewayImplLocal) networkGameGatewayInterface).addObserver(this);
		}else if(isHosting && !isLocalGame){
			log.debug("We are hosting the server on the network");

			//Specify what kind of implementation of network we want to use to play the game on.
//			networkGameGatewayInterface = new NetworkGameGatewayImplJwebsocket();
//			((NetworkGameGatewayImplJwebsocket) networkGameGatewayInterface).addObserver(this);
//			networkGameGatewayInterface.createLobby();			

			AbstractNetworkGateway abstractNetworkGateway = new NetworkGameGatewayImplJwebsocket();
			
			// Define the game object that will run the game
			gameModel = new GameModelHost(abstractNetworkGateway, this.gamePlayers, gameQuestions,
					numOfQuestions);
//			gameModel = new GameModelHost(this.gamePlayers, gameQuestions,
//					numOfQuestions);

			//Test
//			abstractNetworkGateway.createLobby();
			
			// Tell the model that this GUI is observing changes in the model
			gameModel.addObserver(this);
			gameModel.startGame();
			
			// TODO: instead of starting game right away, we want to launch a game
			// lobby on the server
			// GameJavaDesktop gameJavaDesktop = new GameJavaDesktop(game);
			
			
		}else if(!isHosting && isLocalGame){
			log.debug("We are being a client to the server");

			// TODO: As a client, we don't need to run the game, just need to listen to Server

			// Specify what kind of implementation of network we want to use to
			// play the game on.
			networkGameGatewayInterface = new NetworkGameGatewayImplJwebsocket();
			((NetworkGameGatewayImplLocal) networkGameGatewayInterface)
					.addObserver(this);

			// Open a connection to the server to host on
//			networkGameGatewayInterface.createLobby();
			
			// Define the game object type that will run the game
			gameModel = new GameModelClient(this.gamePlayers, gameQuestions,
					numOfQuestions);
			// Jamie loves gui
			gameModel.addObserver(this);
			
			
		}
	}
	private int promptForNumberOfQuestions(){
		int numOfQuestions = 2;
		String keyboardInput = "0";
		/* **************************************
		 * Ask how many questions they want to play
		 * **************************************
		 */
		System.out.println("Please enter in number of questions to play");
		keyboardInput = keyboard.next();
		
		while (numOfQuestions == 0) {
			try {
				keyboardInput = keyboard.next();
				numOfQuestions = Integer.parseInt(keyboardInput);
			} catch (NumberFormatException ex) {
				System.out
						.println("You did not enter a valid number of questions");
			}
		}		
		return numOfQuestions;
	}
	
	private AbstractPlayer promptForPlayerInfo(){
		String playerNameInput;
		AbstractPlayer abstractPlayer = null;
		
		/* 
		 * ******************************
		 * Ask the player for their name
		 * *******************************
		 */
		System.out.println("Please enter your name");

		playerNameInput = keyboard.next();
		if (playerNameInput.isEmpty()) {
			System.out.print("Your name was not valid, defaulting your player name");
		} else {
			abstractPlayer = new HumanPlayer(playerNameInput);
		}
		return abstractPlayer;
	}
	
	private String promptForQuestionCategoryInfo(){
		String questionCategoryInput;
		
		/* 
		 * ******************************************
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
		return questionCategoryInput;

	}
	
	private void promptForTypeOfGame(){
		/*
		 * ********************************************************
		 * Ask if the game is going to be a local or a network game
		 * ********************************************************s
		 */
		System.out.println("Is this a network game? y/n");

		String networkGameQuestionAnswer = keyboard.next();

		while (networkGameQuestionAnswer.isEmpty()
				|| !networkGameQuestionAnswer.equalsIgnoreCase("y")
				&& !networkGameQuestionAnswer.equalsIgnoreCase("n")) {
			System.out.println("You have entered: " +networkGameQuestionAnswer);
			System.out.println("Invalid choice, please enter 'y' or 'n'");
			networkGameQuestionAnswer = keyboard.next();
		}

		if (networkGameQuestionAnswer.equalsIgnoreCase("y")) {
			isLocalGame = false;
		} else {
			isLocalGame = true;
		}
	}
	
	/*
	 * TODO implement
	 */
	private void printOutCurrentLobby(){
	
	}
	
	private void printOutEndRoundResults(){
		log.debug("printOutEndRoundResults()");
		for (UUID key : gamePlayers.keySet()) {
			AbstractPlayer player = gamePlayers.get(key);
			System.out.println("Player: " + player.getPlayerName());
			System.out.println(" Player Perm Chips: "
					+ player.getPermanentGameChips());
			System.out.println(" Player Volitile Chips: "
					+ player.getVolatileGameChips());
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
		
		String keyboardInput;
		String guess;
		Bet bet = null;
		
		System.out.println("::::::::::::::::::Place your Guess now::::::::::::::::::");
		Scanner keyboard = new Scanner(System.in);
		
		try {
			keyboardInput = keyboard.next();
		} catch (NoSuchElementException ex) {
			log.debug("Couldn't retrieve guess input, defaulting to none, closing scanner");
			keyboard.close();
			keyboardInput = "";
		}
		guess = keyboardInput;
		System.out.println("You guessed: " +guess);
		
		
		int betAmount = 0;		
		boolean isValidBet = false;

		
		do {
			System.out.println("Please enter how much you would like to bet");
			try {
				keyboardInput = keyboard.next();
				betAmount = Integer.parseInt(keyboardInput);

				bet = betService.createBet(currentPlayerUUID, guess,
						betAmount);
				AbstractPlayer currentPlayer = gamePlayers
						.get(currentPlayerUUID);

				if (betService.validateBet(currentPlayer, bet) == true) {
					isValidBet = true;
				} else {
					System.out.println("Invalid bet");
				}
			} catch (NumberFormatException ex) {
				System.out.println("You did not enter a valid bet");
			}
		} while (betAmount == 0 || isValidBet == false);

		System.out.println("You Bet: " +betAmount);
		
		//this causes memory leak?
//		do{
//			if(guessInput.isEmpty()){
//				System.out.print("Enter in a valid guess");
//			}
//		} while (guessInput.isEmpty());
		
		playerService.updatePlayerGuess(currentPlayerUUID, gamePlayers, new Guess(guess, bet));
		
		
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
		this.printOutEndRoundResults();
	}

	@Override
	protected void handleStateGameFinished() {
		log.debug("handleStateGameFinished()");
		System.out.println("::::::::::::::::::END GAME::::::::::::::::::");
	}


}
