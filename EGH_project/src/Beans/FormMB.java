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

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.primefaces.event.CloseEvent;
import org.primefaces.event.MoveEvent;

import antragsverwaltung.entity.AnswerNormalTO;
import antragsverwaltung.entity.ApplicationFormTO;
import antragsverwaltung.entity.QuestionIndividuellTO;
import antragsverwaltung.entity.QuestionTO;
import antragsverwaltung.usesecase.impl.IFillForm;
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
	IFillForm fillFormular;

	@Inject
	ISaveForm saveForm;

	@Inject
	IRequestForm requestFormular;

	@SuppressWarnings("cdi-ambiguous-dependency")
	@Inject
	ILoginUser loginUser;



	private int quantityQuestions = 70;			//ANzahl der Fragen
	private int quantityIndiQuestions = 3;
	private String[] alleFragen = new String[quantityQuestions];
	private String[] allAnswers = new String[quantityQuestions];
	private String[] alleQuestionsIndi = new String[5];
	private String[] allAnswersIndi = new String[quantityIndiQuestions];
	private int FragenCounter = 0;
	private int FragenCounter2 = 30;
	private int AnswerCounter = 0;
	static boolean firstInvoke = true;
	static int latestForm;
	static int searchedUserForm = 2;
	static ApplicationFormTO aFormLocal;

	@EJB(beanName = "ActiveUser")
	IActiveUser statelessActiveUser;

	// ausfuehrung beim aufruf der seite
	@PostConstruct
	public void init() {

		
		int actualForm = statelessActiveUser.getUserFormId();
		if (actualForm == 0) {
			createEmptyFormular();
		} else {

			int i = 0;
			int i2 = 0;
			for (ApplicationFormTO aForm : requestFormular.getAllForms()) {
				System.out.println(aForm.getFormNr() + " form nr " + actualForm);
				if (aForm.getFormNr() == actualForm) {
					System.out.println("this form" + aFormLocal);

					if (aFormLocal == null) {
						aFormLocal = aForm;
					}

					for (AnswerNormalTO aAnswerTO : aForm.getNormalAnswers()) { // fuellt alle normalen Antworten aus
						allAnswers[i] = Integer.toString(aAnswerTO.getOneAnswerNormal());

						i++;

					}
					//fuelltalel selbst  erstellten Antworten
					for (QuestionIndividuellTO aQuestionTO : aForm.getIndividualQuestions()) { 
						allAnswersIndi[i2] = Integer.toString(aQuestionTO.getAnswer());
						alleQuestionsIndi[i2] = aQuestionTO.getQuestion();
						i2++;

					}

				}
			}

		}

	}

	public String goToExtraQuestions() {
		updateForm();
		return "extra";
	}

	// lädt alle Fragen die in iniFragen() initialisiert wurden
	public String getFragen() {

		FragenCounter++;

		return alleFragen[FragenCounter - 1];

	}

	public String getFragen2() {

		FragenCounter2++;

		return alleFragen[FragenCounter2 - 1];

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

		return "logout";

	}

	public String logout2() {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Ausloggen erfolgreich"));
		updateForm(); // speichert alle Aenderungen beim Logout
		saveForm.updateForm(aFormLocal);

		return "logout2";

	}
	
	public String logout3() {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Ausloggen erfolgreich"));
		updateForm(); // speichert alle Aenderungen beim Logout
		saveForm.updateForm(aFormLocal);

		return "logout3";

	}
	public String backToForm1() {
		saveIndiQuestions();
		return "form1";

	}

	public String goToFormular2() {
		updateForm();
		return "form2";

	}

	public String goToFormular3() {
		updateForm();
		return "form3";
	}

	public String backToForm1From3() {
		updateForm();
		return "form3ToForm1";
	}

	// ruft alle selbst erstellten antworten auf
	public String getIndiQuestions() {

		FragenCounter++;
		System.out.println("FragenIndi");

		return alleQuestionsIndi[FragenCounter - 1];

	}

	// speichern der vom Nutzer eingegebenen Fragen
	public void saveIndiQuestions() {

		ApplicationFormTO aFormTO = new ApplicationFormTO();
		aFormTO = aFormLocal;

		int i = 0;
		aFormTO.getIndividualQuestions().clear();
		while (i < allAnswersIndi.length && allAnswersIndi[i] != null) {

			aFormTO.getIndividualQuestions()
					.add(new QuestionIndividuellTO(alleQuestionsIndi[i], Integer.parseInt(allAnswersIndi[i])));

			i++;

		}

		saveForm.updateForm(aFormTO);

	}

	// Ausführung beim Aufruf der Seite
	public void iniForm() {
		iniFragen();

	}

	// setzt beim ersten Aufruf standardmaessig alle Antworten auf 0
	public void iniAnswers() {

		if (firstInvoke == true) {
			createEmptyFormular();
			firstInvoke = false;
		} else {

		}

	}

	// initialisiert die Fragen
	public void iniFragen() {

//		alleFragen[0] = "Dummy-Frage";
//		alleFragen[1] = "Dummy-Frage";
//		alleFragen[2] = "Dummy-Frage";
//		alleFragen[3] = "Dummy-Frage";
//		alleFragen[4] = "Dummy-Frage";
//		alleFragen[5] = "Dummy-Frage";
//		alleFragen[6] = "Dummy-Frage";
//		alleFragen[7] = "Dummy-Frage";
//		alleFragen[8] = "Dummy-Frage";

		int i = 0;
		for (QuestionTO aQuestion : requestFormular.getAllQuestions()) { // ruft alle Fragen aus Datenbank ab
			alleFragen[i] = aQuestion.getQuestion();
			i++;

		}

	}

	// Beim ersten Aufruf der Seite wird ein Formular erstellt mit allen Antworten = 0
	
	public void createEmptyFormular() {
		System.out.println("create empty");

		int i = 0;
		int formNr = 0; // formNr = 0 wenn noch nichts ausgefüllt
		int userNr = statelessActiveUser.getUserid(); // aktive UserID
		String eMail = statelessActiveUser.getEmail(); // aktiver User/Email

		ApplicationFormTO aFormTO = new ApplicationFormTO();
		aFormTO.setUserId(userNr); // aktive UserID wird Formular zugewiesen
		while (i < allAnswers.length) {

//			aFormTO.addAnswer(0); // allen Antworten 0 zuweisen
			aFormTO.addAnswerNormal(new AnswerNormalTO(i + 1,0)); //// allen Antworten 0 und die AntwortNr zuweisen
//i + 1,0 
			i++;
		}

		saveForm.createForm(aFormTO);

		for (ApplicationFormTO aForm : requestFormular.getAllForms()) {
			if (aForm.getUserId() == userNr) {

				// FormularNr aus Datenbank abfragen (effizientere Methode wo nur ein das eine
				// Formular abgerufen wird sinnvoll)
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

		while (i < allAnswers.length) {
			System.out.println("Your answered with: " + allAnswers[i]);
			if (allAnswers[i] == null) {
				answerNr = 0;
			} else {
				answerNr = Integer.parseInt(allAnswers[i]);
			}

//			aFormTO.addAnswer(answerNr);

			aFormTO.getNormalAnswers().add(new AnswerNormalTO( i + 1, (answerNr)));
			System.out.println("added Normal Answer  " + aFormLocal.getNormalAnswers().get(0).getAnswerNr()
					+ " mit Antwort: " + aFormLocal.getNormalAnswers().get(0).getOneAnswerNormal());

			i++;
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

	public void redirectAllAnswers() throws IOException {
		// Verlinkung zur Restful-URL welche im Hauptmenue aufgerufen werden kann
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		externalContext.redirect("http://localhost:8080/EGH_project/JS_Client_REST.html");
	}

	public int getAnswerCounter() {
		return AnswerCounter;
	}

	public void setAnswerCounter(int answerCounter) {
		AnswerCounter = answerCounter;
	}

	public static int getLatestForm() {
		return latestForm;
	}

	public static void setLatestForm(int latestForm) {
		FormMB.latestForm = latestForm;
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

	public int getQuantityIndiQuestions() {
		return quantityIndiQuestions;
	}

	public void setQuantityIndiQuestions(int quantityIndiQuestions) {
		this.quantityIndiQuestions = quantityIndiQuestions;
	}

	public String[] getAlleQuestionsIndi() {
		return alleQuestionsIndi;
	}

	public void setAlleQuestionsIndi(String[] alleQuestionsIndi) {
		this.alleQuestionsIndi = alleQuestionsIndi;
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

	public String goToTest() {
		return "testpage";
	}

	public void handleClose(CloseEvent event) {
		addMessage(event.getComponent().getId() + " closed", "So you don't like nature?");
	}

	public void handleMove(MoveEvent event) {
		addMessage(event.getComponent().getId() + " moved", "Left: " + event.getLeft() + ", Top: " + event.getTop());
	}

	public void destroyWorld() {
		addMessage("System Error", "Please try again later.");
	}

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

	public int getFragenCounter2() {
		return FragenCounter2;
	}

	public void setFragenCounter2(int fragenCounter2) {
		FragenCounter2 = fragenCounter2;
	}

}
