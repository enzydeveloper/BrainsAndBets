/**
 * 
 */
package com.bb.game.gameObjects;

import java.util.UUID;

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
	
}
