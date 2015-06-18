/**
 * 
 */
package com.bb.game.controller;

import java.awt.Button;
import java.awt.event.ActionEvent;

/**
 * @author Enzo
 *
 */
public class GameControllerImpl extends AbstractGameController implements GameController{

	/* (non-Javadoc)
	 * @see com.bb.game.controller.AbstractGameController#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		e.getActionCommand();
		Button button = new Button();
	}
	
	

	@Override
	public void createGame() {
//		this.gameModel.
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
