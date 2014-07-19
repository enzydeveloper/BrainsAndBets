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
 * TODO: do we need an "end state"?
 */
public enum GameState {
	STATE_GAME_UNINITIALIZED, 
	STATE_QUESTION,
//	STATE_QUESTION_END,
	STATE_GUESS, 
//	STATE_GUESS_END,
	STATE_BET, 
//	STATE_BET_END,
	STATE_ANSWER, 
//	STATE_ANSWER_END,
	STATE_ROUND_FINISHED,
	STATE_GAME_FINISHED;
}
