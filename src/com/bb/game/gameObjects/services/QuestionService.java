package com.bb.game.gameObjects.services;

import java.util.ArrayList;

import com.bb.game.gameObjects.AbstractQuestion;
import com.bb.game.gameObjects.GameQuestions;

/**
 * @author Enzo
 *
 */
public interface QuestionService {
	
	/**
	 * Retrieve questions for the current game based on the category and the number
	 * 
	 * @param questionCategory
	 * @param numOfQuestions
	 * @return
	 */
	public ArrayList<AbstractQuestion> gatherQuestions(String questionCategory, int numOfQuestions); 
	
	/**
	 * Record down the question into a file
	 * 
	 * @param gameQuestionsFile
	 */
	public void storeQuestionsFile(GameQuestions gameQuestionsFile);
}
