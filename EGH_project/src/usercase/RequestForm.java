package usercase;

import javax.ejb.Stateless;

import Persistence.AnwendungskernException;
import usecase.impl.IRequestForm;

@Stateless
public class RequestForm implements IRequestForm {

	
	public int requestForm() throws AnwendungskernException {
		
		
			
		
//			FormManager aFormManager = FormManager.getFormManager();
	
			
//			int userNr = aFormManager.getUserNr();
			
			int userNr = 123;
		
			
			return userNr;
		
	}

}
