package formularmanagement.entity.impl;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class AnswerNormal implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int answerNr;
	private int oneAnswerNormal;

	public AnswerNormal() {
		super();
	}

	public AnswerNormal(int answerNr, int oneAnswerNormal) {
		super();
		this.answerNr = answerNr;
		this.oneAnswerNormal = oneAnswerNormal;

	}

	public int getAnswerNr() {
		return answerNr;
	}

	public void setAnswerNr(int answerNr) {
		this.answerNr = answerNr;
	}

	public int getOneAnswerNormal() {
		return oneAnswerNormal;
	}

	public void setOneAnswerNormal(int oneAnswerNormal) {
		this.oneAnswerNormal = oneAnswerNormal;
	}

}
