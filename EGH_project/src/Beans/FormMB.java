package Beans;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.CloseEvent;
import org.primefaces.event.MoveEvent;

import antragsverwaltung.entity.AnswerNormalTO;
import antragsverwaltung.entity.ApplicationFormTO;
import antragsverwaltung.entity.QuestionIndividuellTO;
import antragsverwaltung.entity.QuestionTO;
import antragsverwaltung.usesecase.impl.IRequestForm;
import antragsverwaltung.usesecase.impl.ISaveForm;
import usermanagement.entity.UserTO;
import usermanagement.usecase.impl.IActiveUser;
import usermanagement.usecase.impl.ILoginUser;

@Named("formMB")
@RequestScoped
public class FormMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2526217096894280449L;

	@Inject
	ISaveForm saveForm;

	@Inject
	IRequestForm requestFormular;

	@SuppressWarnings("cdi-ambiguous-dependency")
	@Inject
	ILoginUser loginUser;

	private int quantityQuestions = 70; // Anzahl der Fragen
	private int quantityIndiQuestions = 5; // Anzahl der extra Fragen
	private String[] allQuestions = new String[quantityQuestions];
	private String[] allAnswers = new String[quantityQuestions];
	private String[] allQuestionsIndi = new String[quantityQuestions];
	private String[] allAnswersIndi = new String[quantityIndiQuestions];
	private int QuestionCounter = 0;
	private int QuestionCounter2 = 30;
	private int AnswerCounter = 0;
	static boolean firstInvoke = true;
	static ApplicationFormTO aFormLocal;

	@EJB(beanName = "ActiveUser")
	IActiveUser statelessActiveUser;

	// ausfuehrung beim aufruf der seite
	@PostConstruct
	public void init() {

		int actualForm = statelessActiveUser.getUserFormId();
		
		//wenn Form bei null, dann neues Formular mit neuer Nr erstellen
		if (actualForm == 0) {
			createEmptyFormular();
			//wenn Form bereits aufgerufen in dieser Session dann Form antworten aus Bean abrufen
		} else if (aFormLocal!=null){
			System.out.println("form exisiting");
			int i = 0;
			int i2 = 0;
			
			// fuellt alle normalen Antworten aus
			for (AnswerNormalTO aAnswerTO : aFormLocal.getNormalAnswers()) { 
				allAnswers[i] = Integer.toString(aAnswerTO.getOneAnswerNormal());
				i++;

			}
			// fuellt alle selbst erstellten Antworten aus
			for (QuestionIndividuellTO aQuestionTO : aFormLocal.getIndividualQuestions()) {
				allAnswersIndi[i2] = Integer.toString(aQuestionTO.getAnswer());
				allQuestionsIndi[i2] = aQuestionTO.getQuestion();
				i2++;

			}
			
		}
		
		//wenn Form vorhanden aber noch nicht in dieser Session, dann aus Datenbank laden
		else {

			int i = 0;
			int i2 = 0;
			for (ApplicationFormTO aForm : requestFormular.getAllForms()) {

				if (aForm.getFormNr() == actualForm) {
				

					if (aFormLocal == null) {
						
						aFormLocal = aForm;
						
					}
					// fuellt alle normalen Antworten aus
					for (AnswerNormalTO aAnswerTO : aForm.getNormalAnswers()) { 
						allAnswers[i] = Integer.toString(aAnswerTO.getOneAnswerNormal());
						i++;

					}
					// fuellt alle selbst erstellten Antworten aus
					for (QuestionIndividuellTO aQuestionTO : aForm.getIndividualQuestions()) {
						allAnswersIndi[i2] = Integer.toString(aQuestionTO.getAnswer());
						allQuestionsIndi[i2] = aQuestionTO.getQuestion();
						i2++;

					}

				}
			}

		}

	}
	

	// Ausführung beim Aufruf der Seite
	public void iniForm() {
		iniQuestions();

	}
	
	// speichern der vom Nutzer eingegebenen Fragen
	public void saveIndiQuestions() {

		ApplicationFormTO aFormTO = new ApplicationFormTO();
		aFormTO = aFormLocal;

		int i = 0;
		aFormTO.getIndividualQuestions().clear();
		while (i < allAnswersIndi.length && allAnswersIndi[i] != null) {

			aFormTO.getIndividualQuestions()
					.add(new QuestionIndividuellTO(allQuestionsIndi[i], Integer.parseInt(allAnswersIndi[i])));

			i++;

		}

		saveForm.updateForm(aFormTO);

	}
	
	
	
	// ruft alle selbst erstellten antworten auf
	public String getIndiQuestions() {

		QuestionCounter++;
	

		return allQuestionsIndi[QuestionCounter - 1];

	}



	// lädt alle Fragen die in iniFragen() initialisiert wurden
	public String getQuestions() {

		QuestionCounter++;

		return allQuestions[QuestionCounter - 1];

	}

	public String getQuestions2() {

		QuestionCounter2++;

		return allQuestions[QuestionCounter2 - 1];

	}

	public int getAnswers() {
		AnswerCounter++;
		return AnswerCounter;

	}

	public String Answers() {
		return "1";
	}

	public String logout() {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Ausloggen erfolgreich"));
		updateForm(); // speichert alle Aenderungen beim Logout
		saveForm.updateForm(aFormLocal);
		clearForm();

		return "logout";

	}

	public String logout2() {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Ausloggen erfolgreich"));
		updateForm(); // speichert alle Aenderungen beim Logout
		saveForm.updateForm(aFormLocal);
		clearForm();

		return "logout2";

	}

	public String logout3() {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Ausloggen erfolgreich"));
//		updateForm(); // speichert alle Aenderungen beim Logout
		saveIndiQuestions();
		clearForm();
		

		return "logout3";

	}
	
	public String goToExtraQuestions() {
		return "extra";
	}

	public String backToForm1() {
//		saveIndiQuestions();
		updateForm();
		return "form1";

	}

	public String goToFormular2() {
		updateForm();
		System.out.println("Save Form (1)");
		return "form2";

	}

	public String goToFormular3() {
		updateForm();		
		System.out.println("Save Form (2)");
		return "form3";
	}

	public String backToForm1From3() {
//		saveIndiQuestions();
		updateForm();		
		System.out.println("Save Form (3)");
		return "form3ToForm1";
	}



	// initialisiert die Fragen
	public void iniQuestions() {

		// Dummy Fragen, falls es Probleme in der DB mit dem Ladden der Fragen geben sollte
		
//		int count = 0;
//		while (count < alleQuestions.length) {
//			alleFragen[count-1] = "Dummy-Frage";
//			count++;
//		}

		
		// ruft alle Fragen aus Datenbank ab
		int i = 0;
		for (QuestionTO aQuestion : requestFormular.getAllQuestions()) { 
			allQuestions[i] = aQuestion.getQuestion();
			i++;

		}

	}

	// Beim ersten Aufruf der Seite wird ein Formular erstellt mit allen Antworten =
	// 0

	public void createEmptyFormular() {
		System.out.println("create empty");

		int i = 0;
		int formNr = 0; // formNr = 0 wenn noch nichts ausgefüllt
		int userNr = statelessActiveUser.getUserid(); // aktive UserID
		String eMail = statelessActiveUser.getEmail(); // aktiver User/Email

		ApplicationFormTO aFormTO = new ApplicationFormTO();
		aFormTO.setUserId(userNr); // aktive UserID wird Formular zugewiesen
		while (i < allAnswers.length) {


			aFormTO.addAnswerNormal(new AnswerNormalTO(i + 1, 0)); //// allen Antworten 0 und die AntwortNr zuweisen

			i++;
		}

		saveForm.createForm(aFormTO);

		for (ApplicationFormTO aForm : requestFormular.getAllForms()) {
			if (aForm.getUserId() == userNr) {

				// FormularNr aus Datenbank abfragen
				formNr = aForm.getFormNr();
				aFormLocal = aForm;

			}
		}

		UserTO aUser = new UserTO();
		try {
			aUser = loginUser.getUser(eMail); // Abfrage der Objektes User anhand des eingeloggten usernamen
		} catch (Persistence.AnwendungskernException | Persistence.DatenhaltungsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		aUser.setFormnr(formNr); // zuweisen des neu erstellten Formular an den eingeloggten User
		statelessActiveUser.setFormId(formNr); // aktive FormId neu belegen
		loginUser.updateUser(aUser); // User mit neu erstellten Formular speichern.

	}

	// Methode wird ausgefuehrt beim Klicken auf "naechste Seite"
	// speichert Aenderungen im Fragebogen ab (vorerst Lokal ohne DB-Zugriff)
	public void updateForm() {

		int i = 0;
		int answerNr;

		ApplicationFormTO aFormTO = new ApplicationFormTO();
		aFormTO = aFormLocal;
//		aFormTO.getFormAnswer().clear();
		aFormTO.getNormalAnswers().clear();

//		for(String q: allAnswers) {
//			if(q == null) {
//
//			}else {
//
//			}
//				
//			
//			
//		}
		System.out.println("Laength ist: "+ allAnswers.length);
		while (i < allAnswers.length) {

			if (allAnswers[i] == null) {
				answerNr = 0;

			} else {

				answerNr = Integer.parseInt(allAnswers[i]);

			}
			

			
			aFormTO.getNormalAnswers().add(new AnswerNormalTO(i + 1, (answerNr)));

			i++;
		}

		int i2 = 0;
		aFormTO.getIndividualQuestions().clear();
		while (i2 < allAnswersIndi.length && allAnswersIndi[i2] != null) {

			aFormTO.getIndividualQuestions()
					.add(new QuestionIndividuellTO(allQuestionsIndi[i2], Integer.parseInt(allAnswersIndi[i2])));

			i2++;

		}
		aFormLocal = aFormTO;

	}

	// Ausgabe eines Dialog-Fensters
	public String dialogMessage() {
		System.out.println("testdialog");
		int i = 0;
		int notAnswered = 0;

		while (i < allAnswers.length) {
			System.out.println("Your answered with dialog: " + allAnswers[i]);
			if (allAnswers[i].equals("0") || allAnswers[i] == null) {
				notAnswered++;

			} else {

			}

			i++;
		}
		if (notAnswered > 0) {
			return notAnswered + " Frage(n) wurden nicht beantwortet. Trotzdem fortfahren?";
		} else {
			return "Alle Fragen wurden beantwortet. Formular Absenden?";
		}

	}
	
	
	//beim logout die aktuelle form wieder auf null setzen
	public void clearForm() {
		aFormLocal = null;
		
	}

	public void redirectAllAnswers() throws IOException {
		// Verlinkung zur Restful-URL
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		externalContext.redirect("http://localhost:8080/EGH_project/JS_Client_REST.html");
	}

	public int getAnswerCounter() {
		return AnswerCounter;
	}

	public void setAnswerCounter(int answerCounter) {
		AnswerCounter = answerCounter;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public static boolean isFirstInvoke() {
		return firstInvoke;
	}

	public static void setFirstInvoke(boolean firstInvoke) {
		FormMB.firstInvoke = firstInvoke;
	}

	public String[] getAllAnswers() {
		return allAnswers;
	}

	public void setAllAnswers(String[] allAnswers) {
		this.allAnswers = allAnswers;
	}

	public String[] getAllQuestions() {
		return allQuestions;
	}

	public void setAllQuestions(String[] allQuestions) {
		this.allQuestions = allQuestions;
	}

	public int getQuestionCounter() {
		return QuestionCounter;
	}

	public void setQuestionCounter(int questionCounter) {
		QuestionCounter = questionCounter;
	}

	public int getQuantityIndiQuestions() {
		return quantityIndiQuestions;
	}

	public void setQuantityIndiQuestions(int quantityIndiQuestions) {
		this.quantityIndiQuestions = quantityIndiQuestions;
	}

	public String[] getAlleQuestionsIndi() {
		return allQuestionsIndi;
	}

	public void setAlleQuestionsIndi(String[] alleQuestionsIndi) {
		this.allQuestionsIndi = alleQuestionsIndi;
	}

	public String[] getAllAnswersIndi() {
		return allAnswersIndi;
	}

	public void setAllAnswersIndi(String[] allAnswersIndi) {
		this.allAnswersIndi = allAnswersIndi;
	}

	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void saveMessage() {
		FacesContext context = FacesContext.getCurrentInstance();

		context.addMessage(null, new FacesMessage("Successful", "Your message: " + message));
		context.addMessage(null, new FacesMessage("Second Message", "Additional Message Detail"));
	}


	public void handleClose(CloseEvent event) {
		addMessage(event.getComponent().getId() + " closed", "So you don't like nature?");
	}

	public void handleMove(MoveEvent event) {
		addMessage(event.getComponent().getId() + " moved", "Left: " + event.getLeft() + ", Top: " + event.getTop());
	}

	//Info messages

	public void addMessage(String summary, String detail) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public void info() {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Fragenbogen erfolgreich versendet!"));
	}

	public void showMessage() {

		addMessage("System Error", "Please try again later.");
	}

	public ApplicationFormTO getaFormLocal() {
		return aFormLocal;
	}

	public void setaFormLocal(ApplicationFormTO aFormLocal) {
		this.aFormLocal = aFormLocal;
	}

	public int getQuestionCounter2() {
		return QuestionCounter2;
	}

	public void setQuestionCounter2(int questionCounter2) {
		QuestionCounter2 = questionCounter2;
	}

}
