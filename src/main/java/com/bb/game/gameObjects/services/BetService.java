package com.bb.game.gameObjects.services;

import java.util.UUID;

import com.bb.game.gameObjects.Bet;
import com.bb.game.gameObjects.Player.AbstractPlayer;

/**
 * 
 * @author Enzo
 *
 */
public interface BetService {
	
	public boolean validateBet(AbstractPlayer player, Bet bet);

	public Bet createBet(UUID playerUUID, String choice, int numberOfChips);
}
