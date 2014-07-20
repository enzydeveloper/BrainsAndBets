package com.bb.game.gameObjects.services;

import java.util.ArrayList;

import com.bb.game.gameObjects.AbstractQuestion;

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
}
