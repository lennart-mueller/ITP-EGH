package antragsverwaltung.entity.impl;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

//@Entity
//@Access(AccessType.FIELD)
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@Table(name="egh_form_individual_questions")
@Embeddable
public class QuestionIndividuell implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

//	@Id
//	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="QUESTIONS_NR")
//	@SequenceGenerator(name="QUESTION_NR", sequenceName="EGH_SEQ_QUESTION_NR", allocationSize = 1)
//	private long id;
	
	private String question;
	private int answer;
	
	
	//@JoinColumn(name="FORM_NR")
	//private int formNr;
	
	
	
	
	public QuestionIndividuell() {
		super();
	}

	public QuestionIndividuell(String question, int answer/*, int formNr*/) {
		super();
		this.question = question;
		this.answer = answer;
		//this.formNr = formNr;
	}
	
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public int getAnswer() {
		return answer;
	}
	public void setAnswer(int answer) {
		this.answer = answer;
	}

//	public int getFormNr() {
//		return formNr;
//	}
//
//	public void setFormNr(int formNr) {
//		this.formNr = formNr;
//	}

//	public long getId() {
//		return id;
//	}
//
//	public void setId(long id) {
//		this.id = id;
//	}
	
	
	
	
	

}
