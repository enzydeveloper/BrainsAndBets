/**
 * 
 */
package com.bb.game.gameObjects;

/**
 * @author Enzo
 *
 */
public class HumanPlayer extends AbstractPlayer {

	/**
	 * 
	 */
	public HumanPlayer() {
		super();
	}

	/**
	 * @param playerName
	 * @param permanentGameChips
	 * @param volatileGameChips
	 */
	public HumanPlayer(String playerName, int permanentGameChips,
			int volatileGameChips) {
		super(playerName, permanentGameChips, volatileGameChips);
	}

	/**
	 * @param playerName
	 */
	public HumanPlayer(String playerName) {
		super(playerName);
	}

	@Override
	public boolean placeBet() {
		return false;
	}


}
