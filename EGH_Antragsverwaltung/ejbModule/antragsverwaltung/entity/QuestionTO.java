package antragsverwaltung.entity;

import antragsverwaltung.entity.impl.Question;

public class QuestionTO {

	int questNr;
	String question;

	public Question toQuestion() {

		Question aQuestion = new Question(this.getQuestNr(), this.getQuestion());

		return aQuestion;

	}

	public QuestionTO() {
		super();
	}

	public int getQuestNr() {
		return questNr;
	}

	public void setQuestNr(int questNr) {
		this.questNr = questNr;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

}
