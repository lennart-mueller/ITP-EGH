package entityTO;

import java.util.Collection;

public class ApplicationFormTO {
	
	private static final long serialVersionUID = -1965005798780312432L;
	private int formNr;
	private int userId;
//	private double date;
//	private QuestionnaireTO questionnaire;
//	private AnswerTO answers;
//	private Collection<QuestionIndividuellTO> questionsIndividuell;
	
public ApplicationFormTO() {}
	
//	public ApplicationForm toApplicationForm()  {
//		
//		ApplicationForm aForm = new ApplicationForm(this.userId);
//		for (QuestionnaireTO questionnaireTO:this.questionnaire)
//			questionnaire.getKontobewegungen().add(
//					new Kontobewegung(konto, kontobewegungTO.getTyp(),kontobewegungTO.getBetrag()));
//		
//		aForm.setQuestionnaire(this.questionnaire);
//		for (AnswerTO AnswerTO:this.answers)
//			answers.getAnswers().add(
//					new Annswer(answer.getAnswer()));
//		
//		return konto;
//		
//	}
//	
	
	
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
	
//	public double getDate() {
//		return date;
//	}
//	public void setDate(double date) {
//		this.date = date;
//	}
//	public QuestionnaireTO getQuestionnaire() {
//		return questionnaire;
//	}
//	public void setQuestionnaire(QuestionnaireTO questionnaire) {
//		this.questionnaire = questionnaire;
//	}
//	public AnswerTO getAnswers() {
//		return answers;
//	}
//	public void setAnswers(AnswerTO answers) {
//		this.answers = answers;
//	}
//	public Collection<QuestionIndividuellTO> getQuestionsIndividuell() {
//		return questionsIndividuell;
//	}
//	public void setQuestionsIndividuell(Collection<QuestionIndividuellTO> questionsIndividuell) {
//		this.questionsIndividuell = questionsIndividuell;
//	}
//	
	
	

}
