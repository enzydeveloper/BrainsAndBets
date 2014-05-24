package com.bb.game.model;

/**
 * Define what methods will be used to modify the GameModel
 * @author Enzo
 *
 */
public interface GameControllerInterface {
	void setNextQuestion();
	void displayQuestionState();
	void waitOnQuestionState();
	void displayGuessState();
	void waitOnGuessState();
	void displayAnswerState();
	void waitOnAnswerState();
	void displayEndRoundState();
	void waitOnEndRoundState();
	void displayEndGameState();
}
