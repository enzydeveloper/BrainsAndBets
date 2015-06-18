/**
 * 
 */
package com.bb.main;

import com.bb.game.controller.AbstractGameController;
import com.bb.game.controller.GameControllerImpl;
import com.bb.game.model.GameModel;
import com.bb.game.model.GameModelHost;
import com.bb.view.AbstractGameView;
import com.bb.view.java.cli.GameCliView;

/**
 * @author Enzo
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		AbstractGameController gameController = new GameControllerImpl();

		AbstractGameView absGameView = new GameCliView();
		GameModel gameModel = new GameModelHost();
		
		gameModel.addObserver(absGameView);
		absGameView.addController(gameController);
		
		
	}

}
