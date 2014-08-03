package com.bb.game.gameObjects;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bb.game.gameObjects.Question.NumericQuestion;
import com.bb.game.gameObjects.services.QuestionService;
import com.bb.game.gameObjects.services.QuestionServiceImpl;
import com.bb.game.model.GameModelHost;

public class GameQuestionsUtil {
	private static final String questionsFileDirectory = "D:\\";
	static Logger log = LoggerFactory.getLogger(GameQuestionsUtil.class);
	
	 //Define "services" to use
	static QuestionService questionService = new QuestionServiceImpl();

	public static void main(String[] args) {
		createXMLQuestionCLI();
	}

	/**
	 * Run this method to create an XML of questions for storage, TESTING
	 * PURPOSES
	 */
	static void createXMLQuestionCLI() {

		Scanner keyboard = new Scanner(System.in);
		String keyboardInput;
		String questionString = null;
		Integer numericAnswer = null;
		Integer baseRewardPoints = null;

		GameQuestions gameQuestionsFile = new GameQuestions();
		NumericQuestion nQuestion;

		boolean isFinishedWithQuestions = false;
		
		do{
			keyboardInput = null;
			
			System.out.println("Type in your question");
			keyboardInput = keyboard.next();
			questionString = keyboardInput;			
			
			do {
				System.out.println("Type in what the numeric answer is");
				keyboardInput = keyboard.next();
				try {
					numericAnswer = Integer.parseInt(keyboardInput);
				} catch (NumberFormatException ex) {
				}
			} while (numericAnswer == null);
	
			do {
				System.out
						.println("Type in how many points this question is worth");
				keyboardInput = keyboard.next();
				try {
					baseRewardPoints = Integer.parseInt(keyboardInput);
				} catch (NumberFormatException ex) {
				}
			} while (baseRewardPoints == null);

			nQuestion = new NumericQuestion(questionString, numericAnswer,baseRewardPoints);
			gameQuestionsFile.addToGameQuestions(nQuestion);
			
			do {
				System.out.println("Are you finished? y/n");
				keyboardInput = keyboard.next();
			} while (!keyboardInput.equalsIgnoreCase("y") && !keyboardInput.equalsIgnoreCase("n"));
			
			if(keyboardInput.equalsIgnoreCase("y")){
				isFinishedWithQuestions = true;
			}else if(keyboardInput.equalsIgnoreCase("n")){
				isFinishedWithQuestions = false;
			}else{System.out.println("ERROR");}
	
		} while (!isFinishedWithQuestions); 
		
		//send the file into the method to actually do the XML file creation
		questionService.storeQuestionsFile(gameQuestionsFile);
		
		System.out.println("Finished creating XML file");
		
		
		
		System.out.println("What category do you want to load?");
		keyboardInput = keyboard.next();
		String categoryForFile = keyboardInput;
		questionService.gatherQuestions(categoryForFile, 1231);
	}

}
