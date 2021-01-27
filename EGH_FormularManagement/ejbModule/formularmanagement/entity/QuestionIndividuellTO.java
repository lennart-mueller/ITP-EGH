package formularmanagement.entity;

public class QuestionIndividuellTO {

	private String question;
	private int answer;

	public QuestionIndividuellTO(String question, int answer) {
		super();
		this.question = question;
		this.answer = answer;

	}

	public QuestionIndividuellTO() {
		super();
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
