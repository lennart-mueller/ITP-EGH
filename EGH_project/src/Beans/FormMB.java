<<<<<<< HEAD
package Beans;



import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

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
	
	public String formUser() {
		System.out.println("formUser - Test");
		
		try {
			System.out.println(requestFormular.requestForm());
		} catch (Peristence.AnwendungskernException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 String userId = "123";
	//	String userId = Integer.toString(requestFormular.requestForm());
		
		try {
			return Integer.toString(requestFormular.requestForm());
		} catch (Peristence.AnwendungskernException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	
		
	}
	
	
	
	
	public String testeForm() {
		String lalal = "123";
		System.out.println("this is a test");
		return lalal;
		
		
	}
	
	public void testeForm2() {
		try {
			System.out.println("today is a day"+requestFormular.requestForm());
		} catch (Peristence.AnwendungskernException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
=======
package Beans;



import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import Persistence.AnwendungskernException;
import entity.impl.ApplicationForm;
import usecase.impl.IRequestForm;


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
>>>>>>> branch 'main' of https://github.com/lennart-mueller/ITP-EGH.git
