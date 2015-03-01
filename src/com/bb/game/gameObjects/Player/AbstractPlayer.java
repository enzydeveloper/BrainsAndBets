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
	protected boolean isReady; //this variable will need to be reset as needed
	
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
	
	//Setters and Getters	
	public UUID getPlayerUUID() {
		return playerUUID;
	}
	public abstract boolean placeBet();
	/**
	 * @return the playerName
	 */
	public String getPlayerName() {
		return playerName;
	}
	/**
	 * @param playerName the playerName to set
	 */
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	/**
	 * @return the playerScore
	 */
	public int getPlayerScore() {
		return playerScore;
	}
	/**
	 * @param playerScore the playerScore to set
	 */
	public void setPlayerScore(int playerScore) {
		this.playerScore = playerScore;
	}
	/**
	 * @return the permanentGameChips
	 */
	public int getPermanentGameChips() {
		return permanentGameChips;
	}
	/**
	 * @param permanentGameChips the permanentGameChips to set
	 */
	public void setPermanentGameChips(int permanentGameChips) {
		this.permanentGameChips = permanentGameChips;
	}
	/**
	 * @return the volatileGameChips
	 */
	public int getVolatileGameChips() {
		return volatileGameChips;
	}
	/**
	 * @param volatileGameChips the volatileGameChips to set
	 */
	public void setVolatileGameChips(int volatileGameChips) {
		this.volatileGameChips = volatileGameChips;
	}
	/**
	 * @return the isReady
	 */
	public boolean isReady() {
		return isReady;
	}
	/**
	 * @param isReady the isReady to set
	 */
	public void setReady(boolean isReady) {
		this.isReady = isReady;
	}
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
	/**
	 * @param playerUUID the playerUUID to set
	 */
	public void setPlayerUUID(UUID playerUUID) {
		this.playerUUID = playerUUID;
	}
	
}
