package com.bb.game.controller;

/**
 * Define what methods that affects the game model. 
 * 
 * This can be used for View -------> Model changes
 * @author Enzo
 *
 */
public interface GameController {
	
	void createGame();
	
	void startGame();
	void stopGame();
	
	void kickPlayer();
	void voteQuestionUp();
	void voteQuestionDown();
}
