package antragsverwaltung.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import antragsverwaltung.entity.impl.AnswerNormal;
import antragsverwaltung.entity.impl.ApplicationForm;
import antragsverwaltung.entity.impl.QuestionIndividuell;

public class ApplicationFormTO {

	private static final long serialVersionUID = -1965005798780312432L;
	int formNr;
	int userId;
	String bezeichnung;
	private Collection<Integer> formAnswer;
	private List<AnswerNormalTO> normalAnswers;
	private List<QuestionIndividuellTO> individualQuestions;

	public ApplicationFormTO() {

		this.formAnswer = new ArrayList<Integer>();
		this.individualQuestions = new ArrayList<QuestionIndividuellTO>();
		this.normalAnswers = new ArrayList<AnswerNormalTO>();
		this.bezeichnung = "EGH";

	}

	public ApplicationForm toApplicationForm() {

		ApplicationForm aForm = new ApplicationForm(this.getFormNr(), this.getUserId(), this.getBezeichnung());

		for (Integer fnr : this.getFormAnswer()) {
			aForm.getFormAnswer().add(fnr);
		}
		System.out.println("grrrr" + individualQuestions.size());

		for (QuestionIndividuellTO questionIndividuellTO : this.individualQuestions) {
			aForm.getFormIndiAnswer().add(
					new QuestionIndividuell(questionIndividuellTO.getQuestion(), questionIndividuellTO.getAnswer()));
		}

		for (AnswerNormalTO answerNormalTO : this.normalAnswers) {
			aForm.getNormalAnswer()
					.add(new AnswerNormal(answerNormalTO.getAnswerNr(), answerNormalTO.getOneAnswerNormal()));

		}

		return aForm;

	}

	public List<QuestionIndividuellTO> getIndividualQuestions() {
		return individualQuestions;
	}

	public void setIndividualQuestions(List<QuestionIndividuellTO> individualQuestions) {
		this.individualQuestions = individualQuestions;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getFormNr() {
		return formNr;
	}

	public void setFormNr(int formNr) {
		this.formNr = formNr;
	}

	public String getBezeichnung() {
		return bezeichnung;
	}

	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}

	public Collection<Integer> getFormAnswer() {
		return formAnswer;
	}

	public void setFormAnswer(Collection<Integer> formAnswer) {
		this.formAnswer = formAnswer;
	}

	public void addAnswer(int fnr) {
		this.formAnswer.add(Integer.valueOf(fnr));
	}

	public void deleteTeilnehmer(int fnr) {
		this.formAnswer.remove(Integer.valueOf(fnr));
	}

	public void addIndiAnswer(QuestionIndividuellTO aQuestionTO) {
		this.individualQuestions.add(aQuestionTO);
	}

	public void addAnswerNormal(AnswerNormalTO aAnswerTO) {
		this.normalAnswers.add(aAnswerTO);
	}

	public List<AnswerNormalTO> getNormalAnswers() {
		return normalAnswers;
	}

	public void setNormalAnswers(List<AnswerNormalTO> normalAnswers) {
		this.normalAnswers = normalAnswers;
	}

}
