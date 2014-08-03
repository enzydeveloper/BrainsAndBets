package com.bb.game.gameObjects;

import java.util.ArrayList;
import java.util.Collections;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import com.bb.game.gameObjects.Question.AbstractQuestion;
import com.bb.game.gameObjects.Question.NumericQuestion;


/**
 * This object will be converted to and from XML
 */
@XmlRootElement(namespace = "gameObjects")
@XmlSeeAlso(NumericQuestion.class)//this tells what kind of <AbstractQuestion> that is is gameQuestionsList to store
public class GameQuestions {
	
	private ArrayList<AbstractQuestion> gameQuestionsList = new ArrayList<AbstractQuestion>();
	private String questionCategory;
	private int numOfQuestions = 0;

	//-----------Constructors---------------------
	public GameQuestions() {
//		this("NullGameQuestionsCategory");
	}
	
//	public GameQuestions(String questionCategory) {
//		//default to 10 questions for game
//		this(questionCategory, 10);
//	}
	
	public GameQuestions(String questionCategory, int numOfQuestions) {
		this.setQuestionCategory(questionCategory);
		this.setNumOfQuestions(numOfQuestions);
		this.populateGameQuestions(questionCategory, numOfQuestions);
	}
	//---------------------------------------------

	/**
	 * Fills up the gameQuestionsList with <code>AbstractQuestions</code>
	 * 
	 * @param questionClass
	 * @param numOfQuestions
	 */
	public void populateGameQuestions(String questionCategory, int numOfQuestions) {
		// Pull questions from the specified category
		gameQuestionsList = GameQuestionsUtil.gatherQuestions(questionCategory,
				numOfQuestions);
		Collections.shuffle(gameQuestionsList);
	}
	public void addToGameQuestions(AbstractQuestion absQuestion) {
		this.gameQuestionsList.add(absQuestion);
	}
	/**
	 * @return the numOfQuestions
	 */
	public int getNumOfQuestions() {
		return numOfQuestions;
	}
	/**
	 * @param numOfQuestions the numOfQuestions to set
	 */
	public void setNumOfQuestions(int numOfQuestions) {
		this.numOfQuestions = numOfQuestions;
	}

	
	
	@XmlElementWrapper(name = "gameQuestionsList")
	@XmlElement(name = "AbstractQuestion")
	public ArrayList<AbstractQuestion> getGameQuestionsList() {
		return gameQuestionsList;
	}
	public void setGameQuestionsList(ArrayList<AbstractQuestion> gameQuestionsList) {
		this.gameQuestionsList = gameQuestionsList;
	}
	public AbstractQuestion popQuestionFromList(){
		return gameQuestionsList.remove(0);
	}
	

	@XmlElement
	public String getQuestionCategory() {
		return questionCategory;
	}
	public void setQuestionCategory(String questionCategory) {
		this.questionCategory = questionCategory;
	}



}
