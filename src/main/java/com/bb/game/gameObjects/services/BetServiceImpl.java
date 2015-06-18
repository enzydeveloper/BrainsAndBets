/**
 * 
 */
package com.bb.game.gameObjects.services;

import java.util.UUID;

import com.bb.game.gameObjects.Bet;
import com.bb.game.gameObjects.Player.AbstractPlayer;

/**
 * @author Enzo
 *
 */
public class BetServiceImpl implements BetService {

	/* (non-Javadoc)
	 * @see com.bb.game.gameObjects.services.BetValidationService#validateBet(com.bb.game.gameObjects.Player.AbstractPlayer, com.bb.game.gameObjects.Bet)
	 */
	@Override
	public boolean validateBet(AbstractPlayer player, Bet bet) {
		boolean isValidBet = false;
		int betAmount = bet.getGameChips() + bet.getPermaChips();
		if(betAmount <= (player.getPermanentGameChips() + player.getVolatileGameChips())){
			isValidBet = true;
		}else{
			isValidBet = false;
		}
		return isValidBet;
	}

	/*
	 * (non-Javadoc)
	 * @see com.bb.game.gameObjects.services.BetService#createBet(java.util.UUID, java.lang.String, int)
	 */
	@Override
	public Bet createBet(UUID playerUUID, String choice, int numberOfChips) {
		int numberOfPermChipsToBet = 0;
		int numberOfGameChipsToBet = 0;

		//TODO OHGOD remove magic number!!!
		if (numberOfChips > 2) {
			numberOfPermChipsToBet = 2;
			numberOfGameChipsToBet = numberOfChips - 2;
		} else {
			numberOfPermChipsToBet = numberOfChips;
		}
		Bet bet = new Bet(playerUUID, null, numberOfPermChipsToBet, numberOfGameChipsToBet);
		return bet;
		
	}

}
