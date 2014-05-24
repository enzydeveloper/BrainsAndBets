/**
 * 
 */
package com.bb.game.gameObjects;

/**
 * This Enum defines how long the game will wait during each state
 * 
 * @author Enzo
 *
 */
public enum GameStateWaitTime {
	
	QUESTION_STATE_WAIT_TIME(1000), // 30 seconds
	BET_STATE_WAIT_TIME(10000), // 30 seconds
	GUESS_STATE_WAIT_TIME(10000),
	ANSWER_STATE_WAIT_TIME(5000), // 5 seconds
	END_ROUND_STATE_WAIT_TIME(5000); // 5 seconds
	
	private final int timeToWait;
	
	GameStateWaitTime(int timeToWait){
		this.timeToWait = timeToWait;
	}

	/**
	 * @return the timeToWait
	 */
	public int getTimeToWait() {
		return timeToWait;
	}
}
