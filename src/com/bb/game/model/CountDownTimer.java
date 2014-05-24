package com.bb.game.model;

public abstract class CountDownTimer {

    /**
     * Millis since epoch when alarm should stop.
     */
    protected long mMillisInFuture;

    //The interval in millis that the user receives callbacks
    protected boolean isFinished = false;
    protected final long mCountdownInterval;
    protected long mStopTimeInFuture;
    protected boolean mCancelled = false;

    /**
     * @param millisInFuture The number of millis in the future from the call
     *   to {@link #start()} until the countdown is done and {@link #onFinish()}
     *   is called.
     * @param countDownInterval The interval along the way to receive
     *   {@link #onTick(long)} callbacks.
     */
    public CountDownTimer(long millisInFuture, long countDownInterval) {
        mMillisInFuture = millisInFuture;
        mCountdownInterval = countDownInterval;
    }
    
    /**
     * Cancel the countdown.
     *
     * Do not call it from inside CountDownTimer threads
     */
    public abstract void cancel();
    
    /**
     * Start the countdown. Should start a timer and have a 1 second 
     * interval onTick()
     */
    public abstract CountDownTimer start();
    
    /**
     * Callback fired on regular interval.
     * @param millisUntilFinished The amount of time until finished.
     */
    public abstract void onTick(long millisUntilFinished);

    /**
     * Callback fired when the time is up.
     */
    public abstract void onFinish();

	/**
	 * @return the isFinished
	 */
	public boolean isFinished() {
		return isFinished;
	}

	/**
	 * @param isFinished the isFinished to set
	 */
	public void setFinished(boolean isFinished) {
		this.isFinished = isFinished;
	}

}