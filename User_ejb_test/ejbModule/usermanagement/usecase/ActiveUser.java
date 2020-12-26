package usermanagement.usecase;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import antragsverwaltung.entity.ApplicationFormTO;
import usermanagement.usecase.impl.IActiveUser;

@Stateless
@Remote(IActiveUser.class)
public class ActiveUser implements IActiveUser {
	
	private String email;
	private int userid;
	private int formid;
	private ApplicationFormTO aForm;

	

	public String getEmail() {
		return email;
	}

	public void setEmail(String Email) {
		this.email = Email;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	@Override
	public int getUserFormId() {
		return formid;
	}

	@Override
	public void setFormId(int formId) {
		this.formid = formId;
		
	}

	public ApplicationFormTO getaForm() {
		return aForm;
	}

	public void setaForm(ApplicationFormTO aForm) {
		this.aForm = aForm;
	}
	
	
	
	
}
