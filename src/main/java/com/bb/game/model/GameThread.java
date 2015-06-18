package com.bb.game.model;

public class GameThread extends Thread {
//
//	static Logger LOGGER_MAIN = LoggerFactory.getLogger(GameThread.class);
//	
//	// List of players
//	ArrayList<AbstractPlayer> playerList;
//
//	// Game variables
//	GameQuestions gameQuestions;
//	AbstractQuestion currentQuestion;
//	int numberOfRounds;
//	boolean isGameFinished = false;
//
//	/**
//	 * @param playerList
//	 * @param gameQuestions
//	 */
//	public GameThread(ArrayList<AbstractPlayer> playerList,
//			GameQuestions gameQuestions) {
//		super();
//		this.playerList = playerList;
//		this.gameQuestions = gameQuestions;
//	}
//
//	@Override
//	public void run() {
//		while (!isGameFinished && numberOfRounds > 0) {
//			setNextQuestion();
//			displayQuestion();
//			waitForGuessRound();
////			displayGuesses();
////			waitForBetsRound();
////			calculateScore();
////			displayGameResults();
////			waitForNextRound();			
//		}
//	}
//
//	private void setNextQuestion() {
//		currentQuestion = gameQuestions.popQuestionFromList();
//	}
//
//	private void displayQuestion() {
//		String question = currentQuestion.getQuestionString();
//		System.out.print("Question: " + question);
//	}
//
//	private void displayAnswer() {
//		String answer = currentQuestion.getAnswerString();
//		System.out.print("Answer: " + answer);
//	}
//
//	private boolean waitForGuessRound() {
//		startTimer();
//		return isGameFinished;
//		// Check if everyone has already placed their guess
//
//		// also check for timer
//	}
//
//	private boolean waitForBetRound() {
//		return isGameFinished;
//
//	}
//	
//	/**
//	 * Create an abstract timer and implement actions to take while timer is active
//	 */
//	public void startTimer(){
//		CountDownTimerJava t = (CountDownTimerJava) new CountDownTimerJava(26000, 1000) {
//
//			@Override
//			public void onTick(long millisUntilFinished) {
//				// TODO Auto-generated method stub
//				LOGGER_MAIN.debug("onTick() " +millisUntilFinished);
//			}
//			@Override
//			public void onFinish() {
//				// TODO Auto-generated method stub
//				LOGGER_MAIN.debug("onFinish() ");
//			}
//		}.start();
//	}
//	
	
}


