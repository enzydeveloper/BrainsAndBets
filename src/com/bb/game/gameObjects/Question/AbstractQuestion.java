package com.bb.game.gameObjects.Question;

import javax.xml.bind.annotation.XmlTransient;

@XmlTransient //JaxB can't instantiate abstract classes during marshalling so we must specify
public abstract class AbstractQuestion {
	protected String questionString;
	protected String answerString;
	protected int baseRewardPoints;
	
	
	
	/**
	 * @return the questionString
	 */
	public String getQuestionString() {
		return questionString;
	}
	/**
	 * @param questionString the questionString to set
	 */
	public void setQuestionString(String questionString) {
		this.questionString = questionString;
	}
	/**
	 * @return the answerString
	 */
	public String getAnswerString() {
		return answerString;
	}
	/**
	 * @param answerString the answerString to set
	 */
	public void setAnswerString(String answerString) {
		this.answerString = answerString;
	}
	/**
	 * @return the baseRewardPoints
	 */
	public int getBaseRewardPoints() {
		return baseRewardPoints;
	}
	/**
	 * @param baseRewardPoints the baseRewardPoints to set
	 */
	public void setBaseRewardPoints(int baseRewardPoints) {
		this.baseRewardPoints = baseRewardPoints;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Question: "+questionString +", Answer: "+answerString +", Points:" +baseRewardPoints;
	}
	
	
}
