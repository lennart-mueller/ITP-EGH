package antragsverwaltung.dao;

import java.util.Map;

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
	
//	public int findMaxForm(int userNr){
//		Map<String, Object> parameters = new HashMap<String, Object>();
//		parameters.put("formNr", userNr);
////		parameters.put("nachname", lastName);
//		
//		return super.findListResult(ApplicationForm.FIND_BY_MAXNR, parameters);
//		
//	}

}
