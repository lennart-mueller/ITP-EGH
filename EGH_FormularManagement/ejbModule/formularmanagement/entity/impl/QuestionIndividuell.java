package formularmanagement.entity.impl;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class QuestionIndividuell implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String question;
	private int answer;

	public QuestionIndividuell() {
		super();
	}

	public QuestionIndividuell(String question, int answer) {
		super();
		this.question = question;
		this.answer = answer;
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

}
