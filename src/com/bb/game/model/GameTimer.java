package com.bb.game.model;

import java.util.Timer;
import java.util.TimerTask;

/**
 * http://enos.itcollege.ee/~jpoial/docs/tutorial/essential/threads/timer.html
 * @author Enzo
 *
 */
public class GameTimer {
	Timer timer;
	
	public GameTimer(int currentCount, int endCount){
		if(currentCount < endCount){
			timer = new Timer();
			timer.schedule(new CountTimeTask(), 1*1000);
		}
	}
	
	class CountTimeTask extends TimerTask{
		@Override
		public void run() {
			//One second has run, return
			System.out.print("CountTimeTask complete");
		}
	}
}
