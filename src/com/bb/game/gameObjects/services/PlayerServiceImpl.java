package com.bb.game.gameObjects.services;

import java.util.Map;
import java.util.UUID;

import com.bb.game.gameObjects.Guess;
import com.bb.game.gameObjects.Player.AbstractPlayer;

public class PlayerServiceImpl implements PlayerService {

	@Override
	public Map<UUID, AbstractPlayer> addPlayerToGame(AbstractPlayer absPlayer,
			Map<UUID, AbstractPlayer> gamePlayers) {

		Map<UUID, AbstractPlayer> gamePlayersToReturn = gamePlayers;
		gamePlayersToReturn.put(UUID.randomUUID(), absPlayer);
		return gamePlayersToReturn;
	}

	@Override
	public Map<UUID, AbstractPlayer> kickPlayerFromGame(UUID playerUUID,
			Map<UUID, AbstractPlayer> gamePlayers) {

		Map<UUID, AbstractPlayer> gamePlayersToReturn = gamePlayers;
		gamePlayersToReturn.remove(playerUUID);
		return gamePlayersToReturn;
		
	}

	@Override
	public AbstractPlayer updatePlayerGuess(AbstractPlayer player, String guessString) {
		return updatePlayerGuess(player, new Guess(guessString));
	}
	
	@Override
	public AbstractPlayer updatePlayerGuess(final AbstractPlayer player, final Guess guess) {
		AbstractPlayer thisPlayer = player;
		thisPlayer.setGuess(guess);
		return thisPlayer;
	}
	
}
