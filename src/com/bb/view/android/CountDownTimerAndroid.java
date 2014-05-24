package com.bb.view.android;

import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;

import com.bb.game.model.CountDownTimer;

/**
 * This class will be used within the Activity classes to define what happens during 
 * onTick() and onFinish()
 * @author Enzo
 *
 */
public abstract class CountDownTimerAndroid extends CountDownTimer{

	//Part of Android timer?
    protected static final int MSG = 1;
	
    public CountDownTimerAndroid(long millisInFuture, long countDownInterval) {
    	super(countDownInterval, countDownInterval);
    }

	@Override
	public void cancel() {
        mHandler.removeMessages(MSG);
        mCancelled = true;
	}

	@Override
	public synchronized CountDownTimer start() {
        if (mMillisInFuture <= 0) {
            onFinish();
            return this;
        }
        mStopTimeInFuture = SystemClock.elapsedRealtime() + mMillisInFuture;
        mHandler.sendMessage(mHandler.obtainMessage(MSG));
        mCancelled = false;
        return this;
	}
	
    // handles counting down
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            synchronized (CountDownTimerAndroid.this) {
                final long millisLeft = mStopTimeInFuture - SystemClock.elapsedRealtime();

                if (millisLeft <= 0) {
                    onFinish();
                } else if (millisLeft < mCountdownInterval) {
                    // no tick, just delay until done
                    sendMessageDelayed(obtainMessage(MSG), millisLeft);
                } else {
                    long lastTickStart = SystemClock.elapsedRealtime();
                    onTick(millisLeft);

                    // take into account user's onTick taking time to execute
                    long delay = lastTickStart + mCountdownInterval - SystemClock.elapsedRealtime();

                    // special case: user's onTick took more than interval to
                    // complete, skip to next interval
                    while (delay < 0) delay += mCountdownInterval;

                    if (!mCancelled) {
                        sendMessageDelayed(obtainMessage(MSG), delay);
                    }
                }
            }
        }
    };

}
