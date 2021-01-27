package formularmanagement.entity.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import formularmanagement.entity.ApplicationFormTO;
import formularmanagement.entity.QuestionIndividuellTO;
import formularmanagement.entity.QuestionTO;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Access(AccessType.FIELD)
@Table(name="EGH_QUESTIONS")
public class Question {
	
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="QUEST_NR")
	@SequenceGenerator(name="QUEST_NR", sequenceName="SEQ_QUEST_NR", allocationSize = 1)
	int questNr;
	
	

	String question;
	



	
	public Question() {
		super();
	}

	

	
	
	public Question(int questNr, String question) {
		super();
		this.questNr = questNr;
		this.question = question;
	}





	public QuestionTO toQuestionTO() {
		QuestionTO aQuestionTO = new QuestionTO();

		aQuestionTO.setQuestion(this.question);
		aQuestionTO.setQuestNr(this.questNr);

	

		return aQuestionTO;
	}
	
	public Question (QuestionTO aQuestTO) {
		QuestionTO aQuestionTO = new QuestionTO();
		this.questNr = aQuestionTO.getQuestNr();
		this.question = aQuestionTO.getQuestion();

	
		
	}





	public int getQuestNr() {
		return questNr;
	}





	public void setQuestNr(int questNr) {
		this.questNr = questNr;
	}





	public String getQuestions() {
		return question;
	}





	public void setQuestions(String questions) {
		this.question = questions;
	}





	




 

	

}