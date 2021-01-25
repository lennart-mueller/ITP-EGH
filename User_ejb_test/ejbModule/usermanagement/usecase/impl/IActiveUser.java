package usermanagement.usecase.impl;

import antragsverwaltung.entity.ApplicationFormTO;


public interface IActiveUser {
	
	public String getEmail();

	public void setEmail(String Email);

	public int getUserid();

	public void setUserid(int userid); 
	
	public int getUserFormId();

	public void setFormId(int formId); 
	
	public ApplicationFormTO getaForm();

	public void setaForm(ApplicationFormTO aForm);
	
	public String getFirstName();
	
	public String getLastName();
	
	public void setFirstName(String firstName);
	
	public void setLastName(String lastName);

}
