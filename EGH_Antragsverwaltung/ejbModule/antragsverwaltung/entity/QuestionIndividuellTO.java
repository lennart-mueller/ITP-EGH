package antragsverwaltung.entity;

public class QuestionIndividuellTO {
	
	private String question;
	private int answer;
//	private int formNr;
	
	
	
	
	public QuestionIndividuellTO(String question, int answer/*, int formNr*/) {
		super();
		this.question = question;
		this.answer = answer;
//		this.formNr = formNr;
	}
	
	
	
	public QuestionIndividuellTO() {
		super();
	}

	

//	public int getFormNr() {
//		return formNr;
//	}
//
//	public void setFormNr(int formNr) {
//		this.formNr = formNr;
//	}

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
