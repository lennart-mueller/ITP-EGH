package antragsverwaltung.usesecase;

import javax.ejb.Stateless;

import Peristence.AnwendungskernException;
import Peristence.DatenhaltungsException;
import antragsverwaltung.entity.ApplicationFormTO;
import antragsverwaltung.usesecase.impl.IRequestForm;

@Stateless
public class RequestForm implements IRequestForm {


	
	public int requestForm() throws AnwendungskernException {
		
		
			
		
//			FormManager aFormManager = FormManager.getFormManager();
//	
//			
//			int userNr = aFormManager.getUserNr();
						int userNr = 1254888;
//		
//			
		return userNr;
//		
	}

	
	public ApplicationFormTO getApplicationForm(int formNr) throws AnwendungskernException, DatenhaltungsException {
		FormManager aFormManager = FormManager.getFormManager();
		return aFormManager.getForm(formNr);
	}

}
