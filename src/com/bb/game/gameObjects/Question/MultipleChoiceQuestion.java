/**
 * 
 */
package com.bb.game.gameObjects.Question;

import java.util.Map;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Enzo
 *
 */
@XmlRootElement(name = "multipleChoiceQuestion")
public class MultipleChoiceQuestion extends AbstractQuestion {
	/**
	 * Class variables
	 */
	private char correctChoice;
	private Map<String, String> potentialAnswers;

	public MultipleChoiceQuestion(){
		
	}
	
	/**
	 * 
	 * @param questionString
	 * @param numericAnswer
	 * @param baseRewardPoints
	 */
	public MultipleChoiceQuestion(String questionString, Map<String, String> potentialAnswers, int baseRewardPoints) {
		super();
		this.setQuestionString(questionString);
		this.setPotentialAnswers(potentialAnswers);
		this.baseRewardPoints = baseRewardPoints;
	}

	/**
	 * @return the correctChoice
	 */
	public char getCorrectChoice() {
		return correctChoice;
	}

	/**
	 * @param correctChoice the correctChoice to set
	 */
	public void setCorrectChoice(char correctChoice) {
		this.correctChoice = correctChoice;
	}

	/**
	 * @return the potentialAnswers
	 */
	public Map<String, String> getPotentialAnswers() {
		return potentialAnswers;
	}

	/**
	 * @param potentialAnswers the potentialAnswers to set
	 */
	public void setPotentialAnswers(Map<String, String> potentialAnswers) {
		this.potentialAnswers = potentialAnswers;
	}
}
