/**
 * 
 */
package com.bb.game.model.services;

import java.util.Map;
import java.util.UUID;

import com.bb.game.gameObjects.Player.AbstractPlayer;
import com.bb.game.gameObjects.Question.AbstractQuestion;

/**
 * @author Enzo
 *
 */
public interface GameModelService {
	
	void calculateEndRoundResults(Map<UUID, AbstractPlayer> gamePlayers,
			AbstractQuestion currentQuestion);
}
