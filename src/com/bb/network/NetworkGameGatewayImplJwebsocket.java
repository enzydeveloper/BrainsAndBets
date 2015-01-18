package com.bb.network;



import java.util.List;
import java.util.Observable;

import org.jwebsocket.api.WebSocketClientEvent;
import org.jwebsocket.api.WebSocketClientTokenListener;
import org.jwebsocket.api.WebSocketPacket;
import org.jwebsocket.client.token.BaseTokenClient;
import org.jwebsocket.kit.IsAlreadyConnectedException;
import org.jwebsocket.kit.WebSocketException;
import org.jwebsocket.token.Token;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bb.game.gameObjects.Bet;
import com.bb.game.gameObjects.Guess;
import com.bb.view.AbstractGameView;

/**
 * 
 * @author Enzo
 *
 */
public class NetworkGameGatewayImplJwebsocket extends AbstractNetworkGateway implements NetworkGameGatewayInterface, WebSocketClientTokenListener{
	protected Logger log = LoggerFactory.getLogger(NetworkGameGatewayImplJwebsocket.class);
	
	// Initialize a new BaseTokenClient for the later connection
	BaseTokenClient baseTokenClient = new BaseTokenClient();	
	
	
	//---------------- USER DEFINED ---------------------
	/**
	 * @throws IsAlreadyConnectedException 
	 */
	public void openConnection(String ip, String name) throws IsAlreadyConnectedException{
		// Adds an action Listener to the BaseTokenClient
		baseTokenClient.addListener(this);
		
		// Starts the connection to to server by the login information
		String loginString = "ws://" + ip + "/;unid=" + name;
		try {
			baseTokenClient.open(loginString);
		} catch (WebSocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Checkes it the client is connected
		if (baseTokenClient.isConnected()) {
			log.debug("Connected to " +loginString);
//			// If client is connected then this will be displayed in the logFile window
//			GraphicalUserInterface.inst.logFile.append("Connection established to: "
//					+ GraphicalUserInterface.inst.ipaddy.getText() + "\n");
		}else{
			log.error("Couldn't connect to " +loginString);
		}
	}
	
	public void openLocalConnection() throws IsAlreadyConnectedException{
		// Adds an action Listener to the BaseTokenClient
		baseTokenClient.addListener(this);
		
		// Starts the connection to to server by the login information
		String loginString = "ws://localhost:8787/jWebSocket/jWebSocket";
		try {
			baseTokenClient.open(loginString);
		} catch (WebSocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Checks it the client is connected
		if (baseTokenClient.isConnected()) {
			log.debug("Connected to " +loginString);
//			// If client is connected then this will be displayed in the logFile window
//			GraphicalUserInterface.inst.logFile.append("Connection established to: "
//					+ GraphicalUserInterface.inst.ipaddy.getText() + "\n");
		}else{
			log.debug("Couldn't connect to " +loginString);
		}
	}
	
	// This method call the JWC disconnect function to disable the connection 
	void disconnect() throws WebSocketException {
		// Checks if client is actually connected to the server
		if (baseTokenClient.isConnected()) {
			// If yes then it displays that its disconnected now in the logFile window
			log.debug("Connection disconnected");
		} else {
			// If no then it displays that its was not connected in the logFile window
			log.debug("not connected");
		}
		// Closes finally the connection between client and the server
		baseTokenClient.close();
	}
	
	// Method for adding two additional words to the text which shall
	// broadcasted. The initial text is requested by a text box in the
	// graphical user interface
//	void textsend(String text, String name) throws WebSocketException {
//		baseTokenClient.broadcastText("text" + name + ": " + text + "textende");
//		log.debug("text" + name + ": " + text + "textende");
//	}
	
	@Override
	public void processClosed(WebSocketClientEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void processOpened(WebSocketClientEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void processOpening(WebSocketClientEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void processPacket(WebSocketClientEvent arg0, WebSocketPacket arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void processReconnecting(WebSocketClientEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void processToken(WebSocketClientEvent arg0, Token arg1) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void createLobby() {
		try {
			log.debug("Attempting to open connection to create lobby");
			openLocalConnection();
		} catch (IsAlreadyConnectedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.debug("Create Lobby failed... " +e.getMessage());
		}
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
		baseTokenClient.broadcastText("text" + name + ": " + text + "textende");
		log.debug("text" + name + ": " + text + "textende");
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
	