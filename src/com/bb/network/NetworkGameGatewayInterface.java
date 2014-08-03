package com.bb.network;

import java.util.List;

import com.bb.game.gameObjects.Bet;
import com.bb.game.gameObjects.Guess;
import com.bb.game.gameObjects.Question.AbstractQuestion;


/**
 * 
 * Define what methods the game needs to interface with the server
 * 
 * Game <---> networkPacket <-----> Network Gateway <-----> networkPacket <-----> server
 * 
 * @author Enzo
 *
 */

public interface NetworkGameGatewayInterface{
	
	void createLobby();
	void sendJoinGameRequest();
	
	
	void sendBets(List<Bet> betList);
	void sendGuess(List<Guess> guessList);
	
	void handleMessage(NetworkPacketInterface networkPacket);
	void sendMessage(NetworkPacketInterface networkPacket);
		
	
	//When the client gets the other players bets
	abstract void receiveBetInformation();
	
	//When the client gets the other players guesses
	abstract void receiveGuessesInformation();
	
}

