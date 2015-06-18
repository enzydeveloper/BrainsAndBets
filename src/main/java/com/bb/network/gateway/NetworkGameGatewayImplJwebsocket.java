package com.bb.network.gateway;



import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import org.json.JSONException;
import org.json.JSONObject;
import org.jwebsocket.api.WebSocketClientEvent;
import org.jwebsocket.api.WebSocketClientTokenListener;
import org.jwebsocket.api.WebSocketPacket;
import org.jwebsocket.client.token.BaseTokenClient;
import org.jwebsocket.kit.IsAlreadyConnectedException;
import org.jwebsocket.kit.WebSocketException;
import org.jwebsocket.token.MapToken;
import org.jwebsocket.token.Token;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bb.game.gameObjects.Bet;
import com.bb.game.gameObjects.Guess;
import com.bb.network.packet.NetworkPacketInterface;
import com.bb.view.AbstractGameView;

/**
 * 
 * @author Enzo
 *
 */
public class NetworkGameGatewayImplJwebsocket extends AbstractNetworkGateway implements WebSocketClientTokenListener{
	protected Logger log = LoggerFactory.getLogger(NetworkGameGatewayImplJwebsocket.class);
	
	// Initialize a new BaseTokenClient for the later connection
	BaseTokenClient baseTokenClient = new BaseTokenClient();	
	
	String PORT_NUMBER = "8787";
	
	//---------------- USER DEFINED ---------------------
	/**
	 * @throws IsAlreadyConnectedException 
	 */
	public void openConnection(String ip, String name) throws IsAlreadyConnectedException{
		// Adds an action Listener to the BaseTokenClient
		baseTokenClient.addListener(this);
		
		// Starts the connection to to server by the login information
		String loginString = "ws://" + ip +":"+PORT_NUMBER +"/;unid=" + name;
		try {
			baseTokenClient.open(loginString);
		} catch (WebSocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Checkes it the client is connected
		if (baseTokenClient.isConnected()) {
			log.debug("Connected to " +loginString);
		}else{
			log.error("Couldn't connect to " +loginString);
		}

		this.sendMessage(null);
	}
	
	@Override
	public void createLobby(String ip, String lobbyName, String userName) {
		log.debug("Attempting to open connection to create lobby");

		try {
			openConnection(ip, userName);

			this.sendMessage(null);
		} catch (IsAlreadyConnectedException e1) {
			log.error(e1.getLocalizedMessage());
			e1.printStackTrace();
		}
		
	}
	
	public void displayAllLobbies() throws IsAlreadyConnectedException{
		openConnection("localhost", "LookingForLobbies");
		try {
			baseTokenClient.getConnections();
		} catch (WebSocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			log.error(e.getLocalizedMessage());
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
		String methodName = "processToken";
		log.debug("{} : ++++++++++++++++++++++++++++++++++", methodName);
		log.debug("{} : token toString={}", methodName, arg1.toString());
		log.debug("{} : token service response = {}", methodName, arg1.getCode());
		log.debug("{} : token type = {}", methodName,arg1.getType());
		
		if(arg1 instanceof MapToken){
			Iterator<String> tokenIterator = arg1.getKeyIterator();
			
			while(tokenIterator.hasNext()){
				String key = tokenIterator.next();
				log.debug("+++++++++++{} : tokenIterator.next = {}", methodName, key);
				log.debug("+++++++++++key={} getObject={}", key, arg1.getObject(key));
			}
		}
		log.debug("{} : Grabbing connections={}", methodName, arg1.getObject("connections"));
		
		log.debug("{} : ++++++++++++++++++++++++++++++++++", methodName);
		
//		log.debug("processToken : "
//				+ "\n token={},"
//				+ "\n Token service response={}"
//				+ "\n Token Type={}", 
//				arg1.toString(), arg1.getCode(), arg1.getType()
//				);
		
		
		//TODO: Send text through a json text parser to translate into java objects
		//http://theoryapp.com/parse-json-in-java/
		String str = "{ \"name\": \"Alice\", \"age\": 20 }";
		
		try {
			JSONObject obj = new JSONObject(str);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		String name = "name";
		String text = "text";

		try {
			baseTokenClient.broadcastText(name + ": " + text);
		} catch (WebSocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.debug("text" + name + ": " + text);
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
	