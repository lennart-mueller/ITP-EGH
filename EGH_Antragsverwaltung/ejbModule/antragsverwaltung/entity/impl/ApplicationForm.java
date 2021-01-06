package antragsverwaltung.entity.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import antragsverwaltung.entity.ApplicationFormTO;
import antragsverwaltung.entity.QuestionIndividuellTO;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Access(AccessType.FIELD)
@Table(name="EGH_FORM_1")
@NamedQuery(name="ApplicationForm.findMaxForm", 
query="SELECT max(formNr) from ApplicationForm")
public class ApplicationForm {
	
	public static final String FIND_BY_MAXNR = "ApplicationForm.findMaxForm";
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="FORM_NR")
	@SequenceGenerator(name="FORM_NR", sequenceName="SEQ_FORM_NR", allocationSize = 1)
	int formNr;
	
	
	int userId;
	
	String bezeichnung;
	
	@ElementCollection
	@CollectionTable(name="EGH_Form_Answer",
		joinColumns = @JoinColumn(name="FORM_NR"))
	private Collection<Integer> formAnswer;
	
	//https://en.wikibooks.org/wiki/Java_Persistence/ElementCollection
//	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
//	@JoinColumn(name="FORM_NR")
	@ElementCollection
	@CollectionTable(name="INDI_ANSWER_QUESTION",
		joinColumns = @JoinColumn(name="FORM_NR"))
	private List<QuestionIndividuell> formIndiAnswer;

	
	
	
	

	
	public ApplicationForm() {	
		super();
		this.bezeichnung = "EGH";
	}
	
	//Methode zum Speichern einer neu angelegten Form
	public ApplicationForm(int formNr, int userId, String bezeichnung) {
		super();
		this.userId = userId;
		this.bezeichnung = "EGH";
//		this.formNr = formNr;	fuer update in saveForm stat manueller zwuesiung?
		this.formAnswer = new ArrayList<Integer>();
		this.formIndiAnswer = new ArrayList<QuestionIndividuell>();
	}
	
	
	public ApplicationFormTO toFormTO() {
		ApplicationFormTO aFormTO = new ApplicationFormTO();

	
		aFormTO.setFormNr(this.formNr);
		aFormTO.setBezeichnung(this.bezeichnung);  //auf englisch aendern
		aFormTO.setUserId(this.userId);

		for (Integer fnr:this.getFormAnswer()) {
			aFormTO.addAnswer(fnr);
			
		}
		
		aFormTO.setIndividualQuestions(new ArrayList<QuestionIndividuellTO>());
		for (QuestionIndividuell aQuestion:this.getFormIndiAnswer()) 
			aFormTO.getIndividualQuestions().add(new QuestionIndividuellTO(aQuestion.getQuestion(), aQuestion.getAnswer()/*, aQuestion.getFormNr()*/));
				

		return aFormTO;
	}
	
	public ApplicationForm (ApplicationFormTO aFormTO) {
		ApplicationFormTO aApplicationFormTO = new ApplicationFormTO();
		this.formNr = aApplicationFormTO.getFormNr();
		this.userId = aApplicationFormTO.getUserId();
		this.bezeichnung = aApplicationFormTO.getBezeichnung();
		for (Integer fnr : aApplicationFormTO.getFormAnswer()) {
			this.formAnswer.add(fnr);
		}
		
	}

	public ApplicationForm(int formNr2) {
		ApplicationFormTO aApplicationFormTO = new ApplicationFormTO();
		this.formNr = aApplicationFormTO.getFormNr();
		this.bezeichnung = "hdiuf";
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
	
	public void addAnswer(int fnr){
		this.formAnswer.add(Integer.valueOf(fnr));
	}
	
//	public void deleteTeilnehmer(int fnr){
//		this.formAnswer.remove(Integer.valueOf(fnr));
//	}

	public List<QuestionIndividuell> getFormIndiAnswer() {
		return formIndiAnswer;
	}

	public void setFormIndiAnswer(List<QuestionIndividuell> formIndiAnswer) {
		this.formIndiAnswer = formIndiAnswer;
	}
	
	public void addIndiAnswer(QuestionIndividuell aQuestion){
		this.formIndiAnswer.add(aQuestion);
	}

	
 

	

}
