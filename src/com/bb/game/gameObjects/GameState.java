package com.bb.game.gameObjects;

/**
 * This enum describes the possible states that the Game could be in
 * 
 * <p>
 * <li> STATE_QUESTION_DISPLAY, 
 * <li> STATE_GUESS_DISPLAY, 
 * <li> STATE_BETS_DISPLAY,
 * <li> STATE_END_ROUND_DISPLAY;
 * 
 * @author Enzo
 * 
 */
public enum GameState {
	STATE_GAME_UNINITIALIZED, 
	STATE_QUESTION, 
	STATE_GUESS, 
	STATE_BET, 
	STATE_ANSWER, 
	STATE_ROUND_END,
	STATE_GAME_FINISHED;
}
