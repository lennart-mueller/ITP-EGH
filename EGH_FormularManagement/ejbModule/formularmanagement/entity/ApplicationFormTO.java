package formularmanagement.entity;

import java.util.ArrayList;
import java.util.List;

import formularmanagement.entity.impl.AnswerNormal;
import formularmanagement.entity.impl.ApplicationForm;
import formularmanagement.entity.impl.QuestionIndividuell;

public class ApplicationFormTO {

	private static final long serialVersionUID = -1965005798780312432L;
	int formNr;
	int userId;
	String description;
	private List<AnswerNormalTO> normalAnswers;
	private List<QuestionIndividuellTO> individualQuestions;

	public ApplicationFormTO() {

		this.individualQuestions = new ArrayList<QuestionIndividuellTO>();
		this.normalAnswers = new ArrayList<AnswerNormalTO>();
		this.description = "EGH";

	}

	public ApplicationForm toApplicationForm() {

		ApplicationForm aForm = new ApplicationForm(this.getFormNr(), this.getUserId(), this.getDescription());

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String bezeichnung) {
		this.description = bezeichnung;
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
