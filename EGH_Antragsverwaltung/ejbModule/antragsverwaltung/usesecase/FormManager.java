package antragsverwaltung.usesecase;

import Peristence.AnwendungskernException;
import Peristence.ApplicationFormDatabase;
import Peristence.DatenhaltungsException;
import Peristence.IApplicationFormDatabase;
import antragsverwaltung.entity.ApplicationFormTO;

public class FormManager {
	
	private IApplicationFormDatabase aDatabase = new ApplicationFormDatabase();
	private static FormManager formManager;
	
	public static FormManager getFormManager() throws AnwendungskernException {
		if (formManager == null) {
			formManager = new FormManager();
		}

		return formManager;
	}
	
	public int getUserNr() throws AnwendungskernException {
		
		
		try {
			return this.aDatabase.getUserNr();
		} catch (DatenhaltungsException e) {
			e.printStackTrace();
			throw new AnwendungskernException();
		}

		
//		int id = 777;
//		return id;

	}
	
	public void saveForm(int nr, String bezeichnung) throws DatenhaltungsException {
		this.aDatabase.saveForm(nr, bezeichnung);
	}
	
	
	public ApplicationFormTO getForm(int formNr) throws DatenhaltungsException {
		 return this.aDatabase.getForm(formNr);
	}
	

}
