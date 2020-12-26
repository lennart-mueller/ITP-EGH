package antragsverwaltung.usesecase;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;

import Peristence.AnwendungskernException;
import Peristence.DatenhaltungsException;
import antragsverwaltung.dao.FormDAO;
import antragsverwaltung.entity.ApplicationFormTO;
import antragsverwaltung.entity.impl.ApplicationForm;
import antragsverwaltung.usesecase.impl.ISaveForm;

@Stateless
public class SaveForm implements ISaveForm {
	
	@Inject
	FormDAO formDAO;
	

	
	public void saveAnswers(int formNr, int userId, String bezeichnung) throws AnwendungskernException{
		
		
		ApplicationForm aForm = new ApplicationForm(formNr, userId, bezeichnung);
		
		formDAO.save(aForm);
		

			
		
	}
	
	public void saveNumber(int formNr){
		
		
		ApplicationForm aForm = new ApplicationForm(formNr);
		System.out.println("speicher Nummer usecase");
		
		formDAO.save(aForm);
		

		
	}

	@Override
	public void createForm(ApplicationFormTO aFormTO) {
		
		ApplicationForm aForm = new ApplicationForm();
		aForm = aFormTO.toApplicationForm();
		formDAO.save(aForm);
		// TODO Auto-generated method stub
		
	}
	
public void updateForm(ApplicationFormTO aFormTO) {
		
		ApplicationForm aForm = new ApplicationForm();
		aForm = aFormTO.toApplicationForm();
		aForm.setFormNr(aFormTO.getFormNr());	//statt manuelle zuweisung in Application this.formNr = formNr??;
		formDAO.update(aForm);
		// TODO Auto-generated method stub
		
	}

		


}
