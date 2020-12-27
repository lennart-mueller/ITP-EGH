package Beans;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import Peristence.AnwendungskernException;
import Peristence.DatenhaltungsException;
import antragsverwaltung.entity.ApplicationFormTO;
import antragsverwaltung.entity.QuestionIndividuellTO;
import antragsverwaltung.entity.QuestionTO;
import antragsverwaltung.usesecase.impl.IFillForm;
import antragsverwaltung.usesecase.impl.IRequestForm;
import antragsverwaltung.usesecase.impl.ISaveForm;
import usermanagement.entity1.UserTO;
import usermanagement.usecase.impl.IActiveUser;
import usermanagement.usecase.impl.ILoginUser;



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
	
	@Inject
	ILoginUser loginUser;
	
	



	public FormMB() {
	}

	private int quantityQuestions = 5;
	private int quantityIndiQuestions = 3;
	private String userNr = "TesteForm123";
	private String [] alleFragen = new String [30];
	private String [] allAnswers = new String [quantityQuestions];
	private String [] alleQuestionsIndi = new String [30];
	private String [] allAnswersIndi = new String [quantityIndiQuestions];
	private int FragenCounter = 0;
	static boolean firstInvoke= true;
	static int latestForm;
	static int searchedUserForm = 2;
	
	@EJB(beanName = "ActiveUser")
	IActiveUser statelessActiveUser;
	
	
	
	
	


	//buttons werden selected, muss spaeter geändert werden auf get form von user id
	 @PostConstruct
	    public void init() {
		
//		 ApplicationFormTO aFormTO = new ApplicationFormTO();
		 int actualForm = statelessActiveUser.getUserFormId();
		 if(actualForm==0) {
			 createEmptyFormular();
		 }else {
			 
			 int i = 0;
			 for(ApplicationFormTO aForm : requestFormular.getAllForms()) {
					if(aForm.getFormNr()==actualForm) {
//						aFormTO = aForm;
					
						


						for (Integer fnr:aForm.getFormAnswer()) {
							allAnswers[i] = Integer.toString(fnr);
							i++;
							
							
						}

					}
				}
			 
		 }
		
		
		
	    }

	public String goToExtraQuestions() {
		return "extra";
	}
	 
	 
	//lädt alle Fragen die in iniFragen() initialisiert wurden
	public String getFragen() {

		 
		
	
		FragenCounter++;
		System.out.println("FragenCounter");
	
		return alleFragen[FragenCounter-1];
		
	}
	
	public String getIndiQuestions() {
		
		FragenCounter++;
		System.out.println("FragenIndi");
	
		return alleFragen[FragenCounter-1];
		
	}
	
	public void printIndiQuestions() {
		int i = 0;
		while(i < allAnswersIndi.length) {
			System.out.println("Your question: "+alleQuestionsIndi[i]+" with answer: "+allAnswersIndi[i]);
		
			i ++;
		}
		
		ApplicationFormTO aFormTO = new ApplicationFormTO();
//		ApplicationFormTO aFormTO = statelessActiveUser.getaForm();
		int userNr = statelessActiveUser.getUserid();  //aktive UserID
		String eMail = statelessActiveUser.getEmail(); //aktiver User/Email
		int formNr = statelessActiveUser.getUserFormId();
		String description = "EGH9-Form";
		aFormTO.setUserId(userNr);
		aFormTO.setFormNr(formNr);;
		aFormTO.setBezeichnung(description);

		 for(ApplicationFormTO aForm : requestFormular.getAllForms()) {
				if(aForm.getUserId()==userNr) {			//FormularNr aus Datenbank abfragen (effizientere Methode wo nur ein das eine Formular abgerufen wird sinnvoll)
					formNr = aForm.getFormNr();
		aFormTO.setFormAnswer(aForm.getFormAnswer());
					int counter = 0;
					while(counter < allAnswersIndi.length) {
						
						QuestionIndividuellTO aIndi = new QuestionIndividuellTO();
						aIndi.setAnswer(Integer.parseInt(allAnswersIndi[counter]));
						aIndi.setQuestion(alleQuestionsIndi[counter]);

						aFormTO.getIndividualQuestions().add(aIndi);
		
						counter ++;

					}
			
				
					
				}
			
				saveForm.updateForm(aFormTO);
//				statelessActiveUser.setaForm(aFormTO);
			}
		
		
	}
	
	//Ausführung beim Aufruf der Seite
	public void iniForm() {
		iniFragen();
//		iniAnswers();
	
	}
	


	//setzt beim ersten Aufruf standardmaessig alle Antworten auf 0
	public void iniAnswers() {
		
		if ( firstInvoke == true){
			createEmptyFormular();
			firstInvoke = false;
		} else {
			
		}
		
	}
	
	//initialisiert die Fragen
	public void iniFragen() {
		// Kategorie 1
		
		alleFragen[0] = "Wie gehts es Ihnen heute?";
		alleFragen[1] = "Was haben Sie heute vor";
		alleFragen[2] = "Wieso bist du hier?";
		alleFragen[3] = "Wieso liegt hier Stroh?";
		alleFragen[4] = "Wo ist der Bus?";
		alleFragen[5] = "Wie war die Nacht?";
		alleFragen[6] = "Was gabs zum Fruehstueck?";
		alleFragen[7] = "Wie ist das Wetter?";
		alleFragen[8] = "Wieso bist du hier?";
		
		int i = 0;
		 for(QuestionTO aQuestion : requestFormular.getAllQuestions()) {
			 alleFragen[i] = aQuestion.getQuestion();
			 i++;
	
			}
		

	}
	
	//Beim ersten Aufruf der Seite wird ein Formular erstellt mit allen Antworten = 0
	public void createEmptyFormular() {
		
		//ApplicationFormTO aFormTOSave = new ApplicationFormTO();
		int i = 0;
		int formNr = 0; //formNr = 0 wenn noch nichts ausgefüllt
		int userNr = statelessActiveUser.getUserid();  //aktive UserID
		String eMail = statelessActiveUser.getEmail(); //aktiver User/Email
		String description = "EGH-Form";
		
		ApplicationFormTO aFormTO = new ApplicationFormTO();
		aFormTO.setUserId(userNr);		//aktive UserID wird Formular zugewiesen

		aFormTO.setBezeichnung(description);
		while(i < allAnswers.length) {
			
			
			aFormTO.addAnswer(0);		//allen Antworten 0 zuweisen
			i ++;
		}
		
		saveForm.createForm(aFormTO);
		
		 for(ApplicationFormTO aForm : requestFormular.getAllForms()) {
				if(aForm.getUserId()==userNr) {	
					//aFormTOSave = aForm;
//					statelessActiveUser.setaForm(aFormTOSave);
					//FormularNr aus Datenbank abfragen (effizientere Methode wo nur ein das eine Formular abgerufen wird sinnvoll)
					formNr = aForm.getFormNr();
					
				}
			}
		
		UserTO aUser = new UserTO();
		try {
			aUser = loginUser.getUser(eMail);  //Abfrage der Objektes User anhand des eingeloggten usernamen
		} catch (Persistence.AnwendungskernException | Persistence.DatenhaltungsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		aUser.setFormnr(formNr);				//zuweisen des neu erstellten Formular an den eingeloggten User
		statelessActiveUser.setFormId(formNr);	//aktive FormId neu belegen
		loginUser.updateUser(aUser);			//User mit neu erstellten Formular speichern.
		
		
		
		
	}
	
	//ruft ein Formular auf
//	public void requestForm() throws AnwendungskernException, DatenhaltungsException {
//		int nr = statelessActiveUser.getUserFormId();
//		// Kategorie 1
//		ApplicationFormTO aForm = new ApplicationFormTO();
//		aForm = requestFormular.getApplicationForm(nr);
//		System.out.println("description: "+aForm.getBezeichnung());
//	
//	}
	
	
	//speichert ein Formular
//	public void saveForm() throws AnwendungskernException {
//		int i = 0;
//		int formNr = 1;
//		int userNr = 1;
//		String description = "EGH-Form";
//		
//		//startet fuer jede Antwort eine DB-Abfrage, sollte man alles in einer machen mit einer liste die man uebergibt
//		while(allAnswers[i]!= null) {
//			System.out.println("Your answered with: "+allAnswers[i]);
//			int answerNr = Integer.parseInt(allAnswers[i]);
//			saveForm.saveAnswers(formNr, userNr, description);
//			i ++;
//		}
//
//		
//		
//	}
	
//	public void createForm() {
//		int i = 0;
//		int formNr = 1;
//		int userNr = 1;
//		String description = "EGH-Form";
//		
//		ApplicationFormTO aFormTO = new ApplicationFormTO();
//		aFormTO.setUserId(userNr);
////		aFormTO.setFormId(formNr);
//		aFormTO.setBezeichnung(description);
//		while(i < allAnswers.length) {
//			System.out.println("Your answered with: "+allAnswers[i]);
//			int answerNr = Integer.parseInt(allAnswers[i]);
//			aFormTO.addAnswer(answerNr);
//			i ++;
//		}
//		saveForm.createForm(aFormTO);
//		
//		
//		
//	}
	
	public void updateForm() {
//		getNewestForm();
		int i = 0;
		
		int formNr = statelessActiveUser.getUserFormId();
		int userNr = statelessActiveUser.getUserid();
		String description = "EGH-Form";
		int answerNr;
//		ApplicationFormTO aFormTO = statelessActiveUser.getaForm();
		ApplicationFormTO aFormTO = new ApplicationFormTO();
		aFormTO.setUserId(userNr);
		aFormTO.setFormNr(formNr);;
	
		aFormTO.setBezeichnung(description);
		
		 for(ApplicationFormTO aForm : requestFormular.getAllForms()) {
				if(aForm.getUserId()==userNr) {			//FormularNr aus Datenbank abfragen (effizientere Methode wo nur ein das eine Formular abgerufen wird sinnvoll)
				aFormTO.setIndividualQuestions(aForm.getIndividualQuestions());
	
				}
			}
		 
		while(i < allAnswers.length) {
			System.out.println("Your answered with: "+allAnswers[i]);
			if(allAnswers[i]==null) {
				answerNr = 0;
			}else {
				answerNr = Integer.parseInt(allAnswers[i]);
			}
			
			aFormTO.addAnswer(answerNr);
			i ++;
		}
		saveForm.updateForm(aFormTO);
//		statelessActiveUser.setaForm(aFormTO);
	
	}
	
	//speichert die Nummer einer Formulars
	public void saveNumber() throws DatenhaltungsException {
		int formNr  = requestFormular.getFormNumber(2);
		
		System.out.println("aktuelle formNr ist "+formNr);
		
//		saveForm.saveNumber(formNr);
//		
		
		
	}
	
//	public void getNewestForm() {
//		//sucht die hoechste FormNr - muss spaeter ersetzt werden durch suche form nach userNr
////		for(ApplicationFormTO aForm : requestFormular.getAllForms()) {
////			if(aForm.getFormNr()>latestForm) {
////				latestForm = aForm.getFormNr();
////			}
////		}
////		
//		//Alternativ: Formular fuer bestimmte Formnr speichern:
//		latestForm= searchedUserForm;
//			
//		}
		
	
	
	

	
	

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

	public String getUserNr() {
		return userNr;
	}

	public void setUserNr(String userNr) {
		this.userNr = userNr;
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


	private void sendInfoMessageToUser(String message) {
		FacesContext context = getContext();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, message));
	}
	
	private FacesContext getContext() {
		FacesContext context = FacesContext.getCurrentInstance();
		return context;
	}


	
	
	
	
	
	
	
	
	

}

	

