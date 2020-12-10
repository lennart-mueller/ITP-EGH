package antragsverwaltung.entity;

public class ApplicationForm {
	
	private int formNr;
	private int userId;
	private String description;
//	private Questionnaire questionnaire;
	
	public ApplicationForm() {	
	}
	
//	public ApplicationForm(int userNr){
//		this.userNr = userNr;
//		
//	}

//	public Questionnaire getQuestionnaire() {
//		return questionnaire;
//	}
//
//	public void setQuestionnaire(Questionnaire questionnaire) {
//		this.questionnaire = questionnaire;
//	}

	
	
	public int getFormNr() {
		return formNr;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

 

	

}
