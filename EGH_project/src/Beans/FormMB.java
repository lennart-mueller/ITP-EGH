package Beans;


import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import Peristence.AnwendungskernException;
import antragsverwaltung.entity.impl.ApplicationForm;
import antragsverwaltung.usesecase.impl.IRequestForm;






@Named("formMB")
@RequestScoped
public class FormMB {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2526217096894280449L;

//	@Inject
//	IFillForm fillFormular;
	
	@Inject
	IRequestForm requestFormular;



	public FormMB() {
	}

	private int formNr;
	private String userNr = "TesteForm123";



	private ApplicationForm form;

//dhiskdhok


	//*****Aufruf des usescases "requestFormular.requestForm()" funktioniert nicht - Warum???****
	
	public String formUser() throws AnwendungskernException {
		System.out.println("formUser - Test");
//		int test;
//		
//		test = requestFormular.requestForm();
		
		System.out.println();
		//String userId = "123";
		String userId = Integer.toString(requestFormular.requestForm());
		
		
			return userId;
	
		
	}
	
	
	
	
	public String testeForm() throws AnwendungskernException {
		String lalal = "123";
		System.out.println("this is a test");
		return lalal;
		
		
	}
	
	public void testeForm2() throws AnwendungskernException {
		String lalal = "123";
		System.out.println("this is a test");
		
		
	}

	public String getUserNr() {
		return userNr;
	}

	public void setUserNr(String userNr) {
		this.userNr = userNr;
	}

}

	

