package com.bb.game.model;

/**
 * Define what methods that affects the game model. 
 * 
 * This can be used for View -------> Model changes
 * @author Enzo
 *
 */
public interface GameModelControllerInterface {
	void startGame();
	void stopGame();
	
	void kickPlayer();
	void voteQuestionUp();
	void voteQuestionDown();
}
