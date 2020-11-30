package usercase;

import Persistence.AnwendungskernException;
import Persistence.ApplicationFormDatabase;
import Persistence.DatenhaltungsException;
import Persistence.IApplicationFormDatabase;

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
	}

}
