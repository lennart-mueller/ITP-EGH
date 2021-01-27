package formularmanagement.usesecase;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;

import Peristence.AnwendungskernException;
import Peristence.DatenhaltungsException;
import formularmanagement.dao.FormDAO;
import formularmanagement.entity.ApplicationFormTO;
import formularmanagement.entity.impl.ApplicationForm;
import formularmanagement.usesecase.impl.ISaveForm;

@Stateless
public class SaveForm implements ISaveForm {

	@Inject
	FormDAO formDAO;

	// speichert eine Antowrt
	public void saveAnswers(int formNr, int userId, String bezeichnung) throws AnwendungskernException {

		ApplicationForm aForm = new ApplicationForm(formNr, userId, bezeichnung);

		formDAO.save(aForm);

	}

	public void saveNumber(int formNr) {

		ApplicationForm aForm = new ApplicationForm(formNr);
		System.out.println("speicher Nummer usecase");

		formDAO.save(aForm);

	}

	// erstellt eine FOrm
	@Override
	public void createForm(ApplicationFormTO aFormTO) {

		ApplicationForm aForm = new ApplicationForm();
		aForm = aFormTO.toApplicationForm();
		formDAO.save(aForm);
		// TODO Auto-generated method stub

	}

	// aktualisiert eine Form
	public void updateForm(ApplicationFormTO aFormTO) {

		ApplicationForm aForm = new ApplicationForm();
		aForm = aFormTO.toApplicationForm();
		aForm.setFormNr(aFormTO.getFormNr()); // statt manuelle zuweisung in Application this.formNr = formNr??;
		formDAO.update(aForm);
		// TODO Auto-generated method stub

	}

}
