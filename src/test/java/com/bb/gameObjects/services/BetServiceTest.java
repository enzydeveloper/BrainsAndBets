/**
 * 
 */
package com.bb.gameObjects.services;

import static org.junit.Assert.*;

import org.junit.Test;

import com.bb.game.gameObjects.Bet;
import com.bb.game.gameObjects.Player.AbstractPlayer;
import com.bb.game.gameObjects.Player.HumanPlayer;
import com.bb.game.gameObjects.services.BetService;
import com.bb.game.gameObjects.services.BetServiceImpl;

/**
 * @author Enzo
 * 
 */
public class BetServiceTest extends ServiceTestBase {
	private BetService betService = new BetServiceImpl();

	/**
	 * Validate both perm and volatile game chips in bet
	 */
	@Test
	public void testBetValdationSimplePass() {
		int permanentGameChips = 2;
		int volatileGameChips = 10;
		boolean expected = true;
		
		AbstractPlayer player = new HumanPlayer();
		player.setPermanentGameChips(permanentGameChips);
		player.setVolatileGameChips(volatileGameChips);
		
		Bet bet = new Bet(null, null, 2, 10);
		boolean actual = betService.validateBet(player, bet);

		assertEquals("Valdation of bet failed", expected, actual);
	}
	
	/**
	 * Validate both perm and volatile game chips in bet
	 */
	@Test
	public void testBetValdationSimpleFail() {
		int permanentGameChips = 2;
		int volatileGameChips = 10;
		boolean expected = false;
		
		AbstractPlayer player = new HumanPlayer();
		player.setPermanentGameChips(permanentGameChips);
		player.setVolatileGameChips(volatileGameChips);
		
		Bet bet = new Bet(null, null, 2, 199);
		boolean actual = betService.validateBet(player, bet);

		assertEquals("Valdation of bet passed but should not have", expected, actual);
	}
}
