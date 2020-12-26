package antragsverwaltung.usesecase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.Persistence;

import Peristence.AnwendungskernException;
import Peristence.DatenhaltungsException;
import antragsverwaltung.dao.FormDAO;
import antragsverwaltung.entity.ApplicationFormTO;
import antragsverwaltung.entity.impl.ApplicationForm;
import antragsverwaltung.usesecase.impl.IRequestForm;

@Stateless
public class RequestForm implements IRequestForm {

	@Inject
	FormDAO formDAO;

	
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
		System.out.println("Request Form - getApplicationFromdb");
		return formDAO.find(formNr).toFormTO();
	}
	
	//Momentan: auslesen der letzten gespeichterten Form nr, also der aktuellsten. Fuer spaeter: auslesen der formNr des eingeloggten Users
	public int getFormNumber(int userNr) throws DatenhaltungsException {
		
	return 0;
	
		
	}


	@Override
	public List<ApplicationFormTO> getAllForms() {
		List<ApplicationForm> aList = formDAO.findAll();
		List<ApplicationFormTO> returnList = new ArrayList<ApplicationFormTO>();
		for (ApplicationForm aForm : aList) {
			returnList.add(aForm.toFormTO());

		}

		return returnList;
	}
}
