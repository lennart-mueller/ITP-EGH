package antragsverwaltung.usesecase;

import javax.ejb.Stateless;

import Peristence.AnwendungskernException;

@Stateless
public class RequestForm implements IRequestForm {

	
	public int requestForm() throws AnwendungskernException {
		
		
			
		
//			FormManager aFormManager = FormManager.getFormManager();
	
			
//			int userNr = aFormManager.getUserNr();
			
			int userNr = 12549;
		
			
			return userNr;
		
	}

}
