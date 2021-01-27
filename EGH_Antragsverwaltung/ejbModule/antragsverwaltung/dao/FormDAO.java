package antragsverwaltung.dao;

import javax.ejb.Stateless;

import antragsverwaltung.entity.impl.ApplicationForm;

@Stateless
public class FormDAO extends GenericDAO<ApplicationForm> {
	
	public FormDAO() {
		super(ApplicationForm.class);
	}

	public void delete(ApplicationForm aForm) {
		super.delete(aForm.getFormNr(), ApplicationForm.class);
	}

}
