package antragsverwaltung.usesecase;

import javax.ejb.Stateless;

import Peristence.AnwendungskernException;
import antragsverwaltung.usesecase.impl.IRequestForm;

@Stateless
public class RequestForm implements IRequestForm {

	
	public int requestForm() throws AnwendungskernException {
		
		
			
		
			FormManager aFormManager = FormManager.getFormManager();
	
			
			int userNr = aFormManager.getUserNr();
			
//			int userNr = 1254888;
		
			
			return userNr;
		
	}

}
