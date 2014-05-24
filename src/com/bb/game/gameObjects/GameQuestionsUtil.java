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

import com.bb.game.model.GameModelHost;

public class GameQuestionsUtil {
	private static final String questionsFileDirectory = "D:\\";
	static Logger log = LoggerFactory.getLogger(GameQuestionsUtil.class);

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
		storeQuestionsFile(gameQuestionsFile);
		
		System.out.println("Finished creating XML file");
		
		
		
		System.out.println("What category do you want to load?");
		keyboardInput = keyboard.next();
		String categoryForFile = keyboardInput;
		gatherQuestions(categoryForFile, 1231);
	}

	static void storeQuestionsFile(GameQuestions gameQuestionsFile) {
		try {
			File file = new File(questionsFileDirectory +gameQuestionsFile.getQuestionCategory() +".xml");
			JAXBContext jaxbContext = null;
			Marshaller jaxbMarshaller = null;
			
			jaxbContext = JAXBContext.newInstance(GameQuestions.class);
			jaxbMarshaller = jaxbContext.createMarshaller();
			if (jaxbContext != null && jaxbMarshaller != null) {
				// output the formatted XML
				jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);

				jaxbMarshaller.marshal(gameQuestionsFile, file);
				jaxbMarshaller.marshal(gameQuestionsFile, System.out);
			} else {
			}

		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	static ArrayList<AbstractQuestion> gatherQuestions(String questionCategory, int numOfQuestions) {
		// TODO: get a better method of gathering questions.... IE: from MySQL
		// DB or server
		GameQuestions resultingGameQuestions = null;
		try {
			File file = new File(questionsFileDirectory + questionCategory + ".xml");

			JAXBContext jaxbContext = JAXBContext.newInstance(GameQuestions.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			resultingGameQuestions = (GameQuestions) jaxbUnmarshaller.unmarshal(file);
			for(AbstractQuestion question : resultingGameQuestions.getGameQuestionsList()){
				log.debug(question.toString());
			}
			
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return resultingGameQuestions.getGameQuestionsList();
	}

}
