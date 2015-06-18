/**
 * 
 */
package com.bb.game.gameObjects.Player;

/**
 * @author Enzo
 *
 */
public class BotPlayer extends AbstractPlayer {

	public BotPlayer() {
		super();
	}

	public BotPlayer(String playerName, int permanentGameChips,
			int volatileGameChips) {
		super(playerName, permanentGameChips, volatileGameChips);
	}

	public BotPlayer(String playerName) {
		super(playerName);
	}

	@Override
	public boolean placeBet() {
		// TODO Need to define how the bot player actually places a bet for the game
		return false;
	}

}
