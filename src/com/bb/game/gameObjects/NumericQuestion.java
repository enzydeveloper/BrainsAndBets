/**
 * 
 */
package com.bb.game.gameObjects;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Enzo
 *
 */
@XmlRootElement(name = "numericQuestion")
public class NumericQuestion extends AbstractQuestion {
	/**
	 * Class variables
	 */
	private int numericAnswer;

	public NumericQuestion(){
		
	}
	
	/**
	 * 
	 * @param questionString
	 * @param numericAnswer
	 * @param baseRewardPoints
	 */
	public NumericQuestion(String questionString, int numericAnswer, int baseRewardPoints) {
		super();
		this.setQuestionString(questionString);
		this.setNumericAnswer(numericAnswer);
		this.answerString = String.valueOf(numericAnswer);
		this.baseRewardPoints = baseRewardPoints;
	}

	/**
	 * @return the numericAnswer
	 */
	public int getNumericAnswer() {
		return numericAnswer;
	}

	/**
	 * @param numericAnswer the numericAnswer to set
	 */
	@XmlElement
	public void setNumericAnswer(int numericAnswer) {
		this.numericAnswer = numericAnswer;
	}
}
