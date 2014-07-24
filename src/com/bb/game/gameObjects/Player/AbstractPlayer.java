/**
 * 
 */
package com.bb.game.gameObjects.Player;

import java.util.UUID;

import com.bb.game.gameObjects.Guess;

/**
 * @author Enzo
 *
 */
public abstract class AbstractPlayer implements PlayerActions {
	protected String playerName;
	protected UUID playerUUID;
	protected int playerScore;
	protected int permanentGameChips;
	protected int volatileGameChips;
	
	protected Guess guess;
	
	protected AbstractPlayer()
	{
		this("Default Name");
	}
	protected AbstractPlayer(String playerName)
	{
		this(playerName, 2, 0);
	}

	protected AbstractPlayer(String playerName, int permanentGameChips, int volatileGameChips) {
		this.playerName = playerName;
		this.permanentGameChips = permanentGameChips;
		this.volatileGameChips = volatileGameChips;
		this.playerUUID = UUID.randomUUID();
	}
	public UUID getPlayerUUID() {
		return playerUUID;
	}
	public abstract boolean placeBet();
	
	//Setters and Getters
	/**
	 * @return the guess
	 */
	public Guess getGuess() {
		return guess;
	}
	/**
	 * @param guess the guess to set
	 */
	public void setGuess(Guess guess) {
		this.guess = guess;
	}
	
	public void updatePlayerWinnings(){
		volatileGameChips = volatileGameChips + 1;
		playerScore = volatileGameChips + permanentGameChips;
	}
	public void updatePlayerLosings(){
		volatileGameChips = volatileGameChips - 1;
		playerScore = volatileGameChips + permanentGameChips;
	}
	
}
