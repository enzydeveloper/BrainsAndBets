/**
 * 
 */
package com.bb.game.model.services;

import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bb.game.gameObjects.Player.AbstractPlayer;
import com.bb.game.gameObjects.Question.AbstractQuestion;
import com.bb.game.gameObjects.services.PlayerService;
import com.bb.game.gameObjects.services.PlayerServiceImpl;
import com.bb.game.gameObjects.services.QuestionServiceImpl;

/**
 * @author Enzo
 *
 */
public class GameModelServiceImpl implements GameModelService {

	static Logger log = LoggerFactory.getLogger(GameModelServiceImpl.class);
	

	PlayerService playerService = new PlayerServiceImpl();

	/* (non-Javadoc)
	 * @see com.bb.game.model.services.GameModelService#calculateEndRoundResults()
	 */
	/**
	 * TODO: need to implment better
	 */
	@Override
	public void calculateEndRoundResults(Map<UUID, AbstractPlayer> gamePlayers, AbstractQuestion currentQuestion ) {
		String functionName = "calculateEndRoundResults";
		log.debug("function={}", functionName);
		
		for(UUID key : gamePlayers.keySet()){
			AbstractPlayer player = gamePlayers.get(key);
			if (player.getGuess() != null && player.getGuess().getGuessString().equals(currentQuestion
					.getAnswerString())) {
				int playerBetAmount = 0;
				int numberOfPlayerGuesses = 0;
				int numberOfPossibleAnswers = 0;
				playerService.updatePlayerWinnings(player, currentQuestion, playerBetAmount, numberOfPlayerGuesses, numberOfPossibleAnswers);
			}
		}
	}

}
