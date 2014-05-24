package com.bb.view.java.gui;

import java.util.Timer;
import java.util.TimerTask;










import org.slf4j.Logger;
import org.slf4j.LoggerFactory;





import com.bb.game.model.CountDownTimer;

/**
 * This class will be used within the Activity classes to define what happens during 
 * onTick() and onFinish()
 * @author Enzo
 *
 */
public abstract class CountDownTimerJava extends CountDownTimer{

	Logger log = LoggerFactory.getLogger(CountDownTimerJava.class);
	Timer timer;
	
	public CountDownTimerJava(long millisInFuture, long countDownInterval) {
		super(millisInFuture, countDownInterval);
	}

	/* (non-Javadoc)
	 * @see com.bb.gameLogic.CountDownTimer#cancel()
	 */
	@Override
	public void cancel() {
    	timer.cancel();
    	timer.purge();
        mCancelled = true;
	}

	/* (non-Javadoc)
	 * @see com.bb.gameLogic.CountDownTimer#start()
	 */
	@Override
	public synchronized CountDownTimer start() {
        if (mMillisInFuture <= 0) {
        	finishTimer();
            return this;
        }
		timer = new Timer();
		timer.scheduleAtFixedRate(
				new CountTimeTask(),
				0,
				1*1000);
        return this;
	}
	
	private void finishTimer(){
		log.debug("finishTimer()");
       	timer.cancel();
    	timer.purge();
        onFinish();
	}
	

    // handles counting down
	class CountTimeTask extends TimerTask{
		@Override
		public void run() {
			//1 second has run, tick and increment
			mMillisInFuture = mMillisInFuture - mCountdownInterval;
            final long millisLeft = mMillisInFuture;
            
            if(millisLeft >= 0){
                log.debug("run()" +millisLeft);
    			onTick(millisLeft);
            }else{
            	finishTimer();
            }
            
		}
	}


}
