package Beans;


import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.PrimeFaces;
import org.primefaces.event.CloseEvent;
import org.primefaces.event.MoveEvent;

import Peristence.DatenhaltungsException;
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
	
	



	public FormMB() {
	}

	private int quantityQuestions = 5;
	private int quantityIndiQuestions = 3;
	private String userNr = "TesteForm123";
	private String [] alleFragen = new String [9];
	private String [] allAnswers = new String [quantityQuestions];
	private String [] alleQuestionsIndi = new String [30];
	private String [] allAnswersIndi = new String [quantityIndiQuestions];
	private int FragenCounter = 0;
	static boolean firstInvoke= true;
	static int latestForm;
	static int searchedUserForm = 2;
	static ApplicationFormTO aFormLocal;
	
	@EJB(beanName = "ActiveUser")
	IActiveUser statelessActiveUser;
	
	
	
	
	



	 @PostConstruct
	    public void init() {
		

		 int actualForm = statelessActiveUser.getUserFormId();
		 if(actualForm==0) {
			 createEmptyFormular();
		 }else {
			 
			 int i = 0;
			 int i2= 0;
			 for(ApplicationFormTO aForm : requestFormular.getAllForms()) {
				 System.out.println(aForm.getFormNr()+" form nr "+actualForm);
					if(aForm.getFormNr()==actualForm) {
						System.out.println(aFormLocal);

						if(aFormLocal == null) {
							aFormLocal = aForm;
						}
						


						for (Integer fnr:aForm.getFormAnswer()) {		//fuellt alle Antworten aus
							allAnswers[i] = Integer.toString(fnr);
							
							i++;
							
							
						}
						
						for (QuestionIndividuellTO aQuestionTO: aForm.getIndividualQuestions()) {	////fuellt alle Antworten  und Fragen aus
							allAnswersIndi[i2] = Integer.toString(aQuestionTO.getAnswer());
							alleQuestionsIndi[i2] = aQuestionTO.getQuestion();
							i2++;
					
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
	
	public String logout() {
		  FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Ausloggen erfolgreich"));
		  updateForm();							//speichert alle Aenderungen beim Logout
		  saveForm.updateForm(aFormLocal);
		  
		return "logout";
		
	}
	
	public String backToForm1() {

		  
		return "form1";
		
	}
	
	
	
	public String getIndiQuestions() {
		
		FragenCounter++;
		System.out.println("FragenIndi");
	
		return alleQuestionsIndi[FragenCounter-1];
		
	}
	
	//speichern der vom Nutzer eingegebenen Fragen
	public void saveIndiQuestions() {
	
		
		ApplicationFormTO aFormTO = new ApplicationFormTO();
		aFormTO = aFormLocal;

					int i = 0;
					aFormTO.getIndividualQuestions().clear();
					while(i < allAnswersIndi.length && allAnswersIndi[i] != null) {
						
						aFormTO.getIndividualQuestions().add(new QuestionIndividuellTO(alleQuestionsIndi[i],Integer.parseInt(allAnswersIndi[i])));
						
		
						i ++;

					}
			

				saveForm.updateForm(aFormTO);

		
		
	}
	
	//Ausführung beim Aufruf der Seite
	public void iniForm() {
		iniFragen();

	
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
		System.out.println("create empty");
		
		int i = 0;
		int formNr = 0; //formNr = 0 wenn noch nichts ausgefüllt
		int userNr = statelessActiveUser.getUserid();  //aktive UserID
		String eMail = statelessActiveUser.getEmail(); //aktiver User/Email

		
		ApplicationFormTO aFormTO = new ApplicationFormTO();
		aFormTO.setUserId(userNr);		//aktive UserID wird Formular zugewiesen
		while(i < allAnswers.length) {
			
			
			aFormTO.addAnswer(0);		//allen Antworten 0 zuweisen
			i ++;
		}
		
		saveForm.createForm(aFormTO);
		
		 for(ApplicationFormTO aForm : requestFormular.getAllForms()) {
				if(aForm.getUserId()==userNr) {	

					//FormularNr aus Datenbank abfragen (effizientere Methode wo nur ein das eine Formular abgerufen wird sinnvoll)
					formNr = aForm.getFormNr();
					aFormLocal = aForm;
					
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
	

	
	public String updateForm() {
		

		int i = 0;
		int answerNr;

		ApplicationFormTO aFormTO = new ApplicationFormTO();
		aFormTO = aFormLocal;		
		aFormTO.getFormAnswer().clear();
		
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
		
	
		aFormLocal = aFormTO;
		//info();
		return "extra";
		
		//saveForm.updateForm(aFormTO);
		
		
		//addMessage("System Error", " 11Fragebogen erfolgreich verschickt");
	//	showMessage();
		//https://www.primefaces.org/showcase/ui/df/nested.xhtml
		//FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warnung!", output+" Frage(n) wurden nicht beantwortet. Trotzdem fortfahren?"));
	//https://www.primefaces.org/showcase/ui/overlay/confirmDialog.xhtml
	
	}
	
	
	
	public String dialogMessage() {
		System.out.println("testdialog");
		int i = 0;
		int notAnswered = 0;

		while(i < allAnswers.length) {
			System.out.println("Your answered with dialog: "+allAnswers[i]);
			if(allAnswers[i].equals("0") || allAnswers[i]==null ) {
				notAnswered ++;
				
			}else {
			
			}
			
			i ++;
		}
		if(notAnswered>0) {
			return notAnswered+" Frage(n) wurden nicht beantwortet. Trotzdem fortfahren?";
		}else {
			return "Alle Fragen wurden beantwortet. Formular Absenden?";
		}
		
		
		
	}
	

	
	//speichert die Nummer einer Formulars
	public void saveNumber() throws DatenhaltungsException {
		int formNr  = requestFormular.getFormNumber(2);
		
		System.out.println("aktuelle formNr ist "+formNr);
		

		
		
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



	

    private String message;
 
    public String getMessage() {
        return message;
    }
 
    public void setMessage(String message) {
        this.message = message;
    }
     
    public void saveMessage() {
        FacesContext context = FacesContext.getCurrentInstance();
         
        context.addMessage(null, new FacesMessage("Successful",  "Your message: " + message) );
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
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Fragenbogen erfolgreich versendet!"));
    }
    
    public void showMessage() {
//        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "What we do in life", "Echoes in eternity.");
//         
//        PrimeFaces.current().dialog().showMessageDynamic(message);
    	
    	addMessage("System Error", "Please try again later.");
    }

	public ApplicationFormTO getaFormLocal() {
		return aFormLocal;
	}

	public void setaFormLocal(ApplicationFormTO aFormLocal) {
		this.aFormLocal = aFormLocal;
	}
    
    


}

	

