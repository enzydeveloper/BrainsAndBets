/**
 * 
 */
package com.bb.game.gameObjects.services;

import java.util.Map;
import java.util.UUID;

import com.bb.game.gameObjects.AbstractPlayer;

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
}
