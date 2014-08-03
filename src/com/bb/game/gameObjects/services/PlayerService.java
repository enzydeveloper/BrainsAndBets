/**
 * 
 */
package com.bb.game.gameObjects.services;

import java.util.Map;
import java.util.UUID;

import com.bb.game.gameObjects.Guess;
import com.bb.game.gameObjects.Player.AbstractPlayer;
import com.bb.game.gameObjects.Question.AbstractQuestion;

/**
 * @author Enzo
 *
 */
public interface PlayerService {
	/**
	 * Adding a player to the game with a UUID to keep track of each player
	 * 
	 * @param absPlayer
	 * @param gamePlayers
	 * @return
	 */
	public Map<UUID, AbstractPlayer> addPlayerToGame(final AbstractPlayer absPlayer, final Map<UUID, AbstractPlayer> gamePlayersMapping);
	
	/**
	 * Remove the a player from game using the UUID 
	 * @param playerUUID
	 * @param gamePlayers
	 * @return
	 */
	public Map<UUID, AbstractPlayer> kickPlayerFromGame(final UUID playerUUID, final Map<UUID, AbstractPlayer> gamePlayers);
	
	/**
	 * Update the players guess, this one takes in a guess object
	 * @param player
	 * @param guess
	 * @return
	 */
	public AbstractPlayer updatePlayerGuess(AbstractPlayer player, Guess guess);
	
	/**
	 * Update the players guess, this one takes in a string
	 * @param player
	 * @param guess
	 * @return
	 */
	public AbstractPlayer updatePlayerGuess(AbstractPlayer player, String guessString);
	
	/**
	 * Update player winning based on what the player guessed on and how many possible answers there were
	 * @param numberOfPlayerGuesses
	 * @param numberOfPossibleAnswers
	 * @return
	 */
	public AbstractPlayer updatePlayerWinnings(final AbstractPlayer player, final AbstractQuestion question, final int playerBetAmount, final int numberOfPlayerGuesses, final int numberOfPossibleAnswers);
}
