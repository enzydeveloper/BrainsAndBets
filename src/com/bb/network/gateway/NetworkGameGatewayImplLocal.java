package com.bb.network.gateway;

import java.util.List;





//import org.jwebsocket.api.WebSocketClientTokenListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bb.game.gameObjects.Bet;
import com.bb.game.gameObjects.Guess;
import com.bb.game.gameObjects.Question.AbstractQuestion;
import com.bb.network.packet.NetworkPacketInterface;

public class NetworkGameGatewayImplLocal  extends AbstractNetworkGateway implements NetworkGameGatewayInterface{
	protected Logger log = LoggerFactory.getLogger(NetworkGameGatewayImplLocal.class);

	
	
	
	/**
	 * Since this is a local lobby, we don't need to connect to the server to create a lobby
	 */
	@Override
	public void createLobby() {
		
	}
	
	@Override
	public void sendJoinGameRequest() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendBets(List<Bet> betList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendGuess(List<Guess> guessList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleMessage(NetworkPacketInterface networkPacket) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendMessage(NetworkPacketInterface networkPacket) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void receiveBetInformation() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void receiveGuessesInformation() {
		// TODO Auto-generated method stub
		
	}
}
