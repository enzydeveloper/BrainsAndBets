package com.bb.game.model;

import java.util.ArrayList;

import com.bb.game.gameObjects.AbstractPlayer;
import com.bb.game.gameObjects.GameQuestions;

/**
 * The client game model will only serve as place holder for game changes that
 * are sent by the GameModelHost
 * 
 * @author Enzo
 * 
 */
public class GameModelClient extends GameModel{

	public GameModelClient(ArrayList<AbstractPlayer> playerList,
			GameQuestions gameQuestions, int numberOfRounds) {
		super(playerList, gameQuestions, numberOfRounds);
	}

	/**
	 * Get the questionString from the message sent by server
	 */
	@Override
	public void setNextQuestion() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayQuestionState() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void waitOnQuestionState() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayGuessState() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void waitOnGuessState() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayAnswerState() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void waitOnAnswerState() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayEndRoundState() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void waitOnEndRoundState() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayEndGameState() {
		// TODO Auto-generated method stub
		
	}

	
	
	
	@Override
	public void startGame() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stopGame() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void kickPlayer() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void voteQuestionUp() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void voteQuestionDown() {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	

}
