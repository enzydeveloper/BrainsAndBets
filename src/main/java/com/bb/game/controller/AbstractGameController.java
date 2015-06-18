/**
 * 
 */
package com.bb.game.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bb.game.model.GameModel;
import com.bb.view.AbstractGameView;

/**
 * @author Enzo
 *
 */
public abstract class AbstractGameController implements ActionListener{

	protected Logger log = LoggerFactory.getLogger(this.getClass());
	
	AbstractGameView gameView;
	GameModel gameModel;
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public abstract void actionPerformed(ActionEvent e);

	/**
	 * @return the gameView
	 */
	public AbstractGameView getGameView() {
		return gameView;
	}

	/**
	 * @param gameView the gameView to set
	 */
	public void setGameView(AbstractGameView gameView) {
		this.gameView = gameView;
	}

	/**
	 * @return the gameModel
	 */
	public GameModel getGameModel() {
		return gameModel;
	}

	/**
	 * @param gameModel the gameModel to set
	 */
	public void setGameModel(GameModel gameModel) {
		this.gameModel = gameModel;
	}
	
	
}
