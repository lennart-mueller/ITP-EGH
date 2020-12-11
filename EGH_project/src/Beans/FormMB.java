package Beans;


import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import antragsverwaltung.entity.AnswerChangeTO;
import antragsverwaltung.entity.impl.ApplicationForm;
import antragsverwaltung.usesecase.impl.IFillForm;
import antragsverwaltung.usesecase.impl.IRequestForm;
import antragsverwaltung.usesecase.impl.ISaveForm;






@Named("formMB")
@RequestScoped
public class FormMB {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2526217096894280449L;

	@Inject
	IFillForm fillFormular;
	
	@Inject
	ISaveForm saveForm;
	
	@Inject
	IRequestForm requestFormular;



	public FormMB() {
	}

	private int formNr;
	private String userNr = "TesteForm123";
	private String [] alleFragen = new String [30];
	private int FragenCounter = 0;
	private String answer = "4";
	private AnswerChangeTO aAnswerTO;
	private List<AnswerChangeTO> allquestions = new ArrayList<AnswerChangeTO>();
	private String Antworten;
	private String answer1 = "5";
	private String answer2;
	private String answer3;
	private String eingabe;


	private ApplicationForm form;




	
	
	public String getFragen() {
		FragenCounter++;
		System.out.println("FragenCounter");
	
		return alleFragen[FragenCounter-1];
		
	}
	
	public void iniFragen() {
		// Kategorie 1
		alleFragen[0] = "Wie gehts es Ihnen heute?";
		alleFragen[1] = "Was haben Sie heute vor";
	
	}
	
	
//	public void saveForm() throws AnwendungskernException, DatenhaltungsException {
//		int formNr = 2;
//		int questionNr = FragenCounter;
//		int answerNr = Integer.parseInt(answer);
//		
//		saveForm.saveAnswers(formNr, questionNr, answerNr);
//		
//		
//		
//	}
	
	
	
	

	public String getAnswer1() {
		return answer1;
	}

	public void setAnswer1(String answer1) {
		this.answer1 = answer1;
	}

	public String getAnswer2() {
		return answer2;
	}

	public void setAnswer2(String answer2) {
		this.answer2 = answer2;
	}

	public String getAnswer3() {
		return answer3;
	}

	public void setAnswer3(String answer3) {
		this.answer3 = answer3;
	}

	public String[] getAlleFragen() {
		return alleFragen;
	}

	public void setAlleFragen(String[] alleFragen) {
		this.alleFragen = alleFragen;
	}

	public int getFragenCounter() {
		return FragenCounter;
	}

	public void setFragenCounter(int fragenCounter) {
		FragenCounter = fragenCounter;
	}

	public String getUserNr() {
		return userNr;
	}

	public void setUserNr(String userNr) {
		this.userNr = userNr;
	}

	public AnswerChangeTO getaAnswerTO() {
		return aAnswerTO;
	}

	public void setaAnswerTO(AnswerChangeTO aAnswerTO) {
		this.aAnswerTO = aAnswerTO;
	}

	

	public String getAntworten() {
		return Antworten;
	}

	public void setAntworten(String antworten) {
		Antworten = antworten;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	private void sendInfoMessageToUser(String message) {
		FacesContext context = getContext();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, message));
	}
	
	private FacesContext getContext() {
		FacesContext context = FacesContext.getCurrentInstance();
		return context;
	}

	public String getEingabe() {
		return eingabe;
	}

	public void setEingabe(String eingabe) {
		this.eingabe = eingabe;
	}
	
	
	
	
	
	
	
	
	

}

	

