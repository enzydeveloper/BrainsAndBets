package com.bb.game.model;

/**
 * Define what methods that affects the game model. 
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
