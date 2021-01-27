package formularmanagement.entity.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import formularmanagement.entity.AnswerNormalTO;
import formularmanagement.entity.ApplicationFormTO;
import formularmanagement.entity.QuestionIndividuellTO;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Access(AccessType.FIELD)
@Table(name = "EGH_FORM_1")
@NamedQuery(name = "ApplicationForm.findMaxForm", query = "SELECT max(formNr) from ApplicationForm")
public class ApplicationForm {

	public static final String FIND_BY_MAXNR = "ApplicationForm.findMaxForm";

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FORM_NR")
	@SequenceGenerator(name = "FORM_NR", sequenceName = "SEQ_FORM_NR", allocationSize = 1)
	int formNr;

	int userId;

	String description;

	// https://en.wikibooks.org/wiki/Java_Persistence/ElementCollection
	@ElementCollection
	@CollectionTable(name = "INDI_ANSWER_QUESTION", joinColumns = @JoinColumn(name = "FORM_NR"))
	private List<QuestionIndividuell> formIndiAnswer;

	@ElementCollection
	@CollectionTable(name = "NORMAL_ANSWER", joinColumns = @JoinColumn(name = "FORM_NR"))
	private List<AnswerNormal> normalAnswer;

	public ApplicationForm() {
		super();
		this.description = "EGH";
	}

	// Methode zum Speichern einer neu angelegten Form
	public ApplicationForm(int formNr, int userId, String bezeichnung) {
		super();
		this.userId = userId;
		this.description = "EGH";
		this.formIndiAnswer = new ArrayList<QuestionIndividuell>();
		this.normalAnswer = new ArrayList<AnswerNormal>();
	}

	public ApplicationFormTO toFormTO() {
		ApplicationFormTO aFormTO = new ApplicationFormTO();

		aFormTO.setFormNr(this.formNr);
		aFormTO.setDescription(this.description);
		aFormTO.setUserId(this.userId);

		aFormTO.setIndividualQuestions(new ArrayList<QuestionIndividuellTO>());
		for (QuestionIndividuell aQuestion : this.getFormIndiAnswer()) {
			aFormTO.getIndividualQuestions()
					.add(new QuestionIndividuellTO(aQuestion.getQuestion(), aQuestion.getAnswer()));

		}

		aFormTO.setNormalAnswers(new ArrayList<AnswerNormalTO>());
		for (AnswerNormal aNormalAnswer : this.getNormalAnswer()) {
			aFormTO.getNormalAnswers()
					.add(new AnswerNormalTO(aNormalAnswer.getAnswerNr(), aNormalAnswer.getOneAnswerNormal()));

		}

		return aFormTO;
	}

	public ApplicationForm(ApplicationFormTO aFormTO) {
		ApplicationFormTO aApplicationFormTO = new ApplicationFormTO();
		this.formNr = aApplicationFormTO.getFormNr();
		this.userId = aApplicationFormTO.getUserId();
		this.description = aApplicationFormTO.getDescription();

	}

	public ApplicationForm(int formNr2) {
		ApplicationFormTO aApplicationFormTO = new ApplicationFormTO();
		this.formNr = aApplicationFormTO.getFormNr();
		this.description = "EGH";
	}

	public int getFormNr() {
		return formNr;
	}

	public void setFormNr(int formNr) {
		this.formNr = formNr;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String bezeichnung) {
		this.description = bezeichnung;
	}

	public List<QuestionIndividuell> getFormIndiAnswer() {
		return formIndiAnswer;
	}

	public void setFormIndiAnswer(List<QuestionIndividuell> formIndiAnswer) {
		this.formIndiAnswer = formIndiAnswer;
	}

	public void addIndiAnswer(QuestionIndividuell aQuestion) {
		this.formIndiAnswer.add(aQuestion);
	}

	public void addAnswerNormal(AnswerNormal aAnswer) {
		this.normalAnswer.add(aAnswer);
	}

	public List<AnswerNormal> getNormalAnswer() {
		return normalAnswer;
	}

	public void setNormalAnswer(List<AnswerNormal> normalAnswer) {
		this.normalAnswer = normalAnswer;
	}

}
