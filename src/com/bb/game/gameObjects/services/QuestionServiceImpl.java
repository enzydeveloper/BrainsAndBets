package com.bb.game.gameObjects.services;

import java.io.File;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bb.game.gameObjects.GameQuestions;
import com.bb.game.gameObjects.GameQuestionsUtil;
import com.bb.game.gameObjects.Question.AbstractQuestion;

/**
 * Handling of how questions are extracted and stored 
 * 
 * @author Enzo
 *
 */
public class QuestionServiceImpl implements QuestionService{

	private static final String questionsFileDirectory = "D:\\";
	static Logger log = LoggerFactory.getLogger(QuestionServiceImpl.class);

	@Override
	public ArrayList<AbstractQuestion> gatherQuestions(String questionCategory,
			int numOfQuestions) {

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

	@Override
	public void storeQuestionsFile(GameQuestions gameQuestionsFile) {

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

}
