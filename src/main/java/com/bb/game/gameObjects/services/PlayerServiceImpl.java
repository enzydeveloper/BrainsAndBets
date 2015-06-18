package com.bb.game.gameObjects.services;

import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import com.bb.game.gameObjects.Guess;
import com.bb.game.gameObjects.Player.AbstractPlayer;
import com.bb.game.gameObjects.Question.AbstractQuestion;
import com.bb.game.gameObjects.Question.MultipleChoiceQuestion;
import com.bb.game.gameObjects.Question.NumericQuestion;

public class PlayerServiceImpl implements PlayerService {

	@Override
	public Map<UUID, AbstractPlayer> addPlayerToGame(AbstractPlayer absPlayer,
			Map<UUID, AbstractPlayer> gamePlayers) {

		Map<UUID, AbstractPlayer> gamePlayersToReturn = gamePlayers;
		gamePlayersToReturn.put(absPlayer.getPlayerUUID(), absPlayer);
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
	
	@Override
	public Map<UUID, AbstractPlayer> updatePlayerGuess(UUID playerKey,
			Map<UUID, AbstractPlayer> gamePlayers, Guess guess) {
		gamePlayers.get(playerKey).setGuess(guess);
		return gamePlayers;
	}

	//TODO: need to implement logic on how to calculate score
	@Override
	public AbstractPlayer updatePlayerWinnings(AbstractPlayer player,
			AbstractQuestion question, int playerBetAmount,
			int numberOfPlayerGuesses, int numberOfPossibleAnswers) {
						
		player.setVolatileGameChips(player.getVolatileGameChips() + 1);
		
//		if(question instanceof NumericQuestion){
//			
//		}else if(question instanceof MultipleChoiceQuestion){
//			MultipleChoiceQuestion multipleChoiceQuestion = (MultipleChoiceQuestion) question;
//			
//			Map<String, String> potentialAnswers = multipleChoiceQuestion.getPotentialAnswers();
//			
//			int incorrectAnswers = 0;
//			int correctAnswers = 0;
//			
//			for(int i = 0; i < potentialAnswers.size(); i++ ){
//				player.getGuess();
//			}
//		}
		return player;
	}

	@Override
	public AbstractPlayer updatePlayerWinnings(
			Map<UUID, AbstractPlayer> gamePlayers, AbstractQuestion question) {
		for(Entry<UUID, AbstractPlayer> entry : gamePlayers.entrySet()){
			AbstractPlayer player = entry.getValue();
		}
		
		return null;
	}

	
	
}
