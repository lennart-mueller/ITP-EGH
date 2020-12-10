package antragsverwaltung.usesecase;

import Peristence.AnwendungskernException;
import Peristence.DatenhaltungsException;
import antragsverwaltung.usesecase.impl.ISaveForm;

public class SaveForm implements ISaveForm {
	
	//speichert die Antworten in die Datenbank
	public void fillForm(int nr, String bezeichnung) throws AnwendungskernException, DatenhaltungsException {
		
		
		
				FormManager aFormManager = FormManager.getFormManager();
	
				aFormManager.saveForm(nr, bezeichnung);
				


}

}
