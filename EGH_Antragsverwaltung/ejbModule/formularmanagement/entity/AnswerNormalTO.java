package formularmanagement.entity;

public class AnswerNormalTO {
	
	private int answerNr; // Nr der Antwort, also Frage Nr.1 hat Antwort Nr 1
	private int oneAnswerNormal; // Antwort der Frage, Skala 0-8

	public AnswerNormalTO(int answerNr, int oneAnswerNormal) {
		super();
		this.answerNr = answerNr;
		this.oneAnswerNormal = oneAnswerNormal;

	}

	public AnswerNormalTO() {
		super();
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

	public void setOneAnswerNormal(int OneAnswerNormal) {
		this.oneAnswerNormal = OneAnswerNormal;
	}

}
