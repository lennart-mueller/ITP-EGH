package Beans;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import Persistence.AnwendungskernException;
import Persistence.DatenhaltungsException;
import antragsverwaltung.usesecase.impl.IFillForm;
import usermanagement.entity.UserTO;
import usermanagement.usecase.impl.IActiveUser;
import usermanagement.usecase.impl.ILoginUser;
import usermanagement.usecase.impl.IRegisterUser;

@Named("userMB")
@SessionScoped
public class UserMB implements Serializable {
	
private static final long serialVersionUID = 1094801825228386363L;

	@Inject
	ILoginUser loginUser;
	
	@Inject
	IRegisterUser registerUser;
	
	@EJB(beanName = "ActiveUser")
	IActiveUser statelessActiveUser;
	
	private String firstName;
	private String lastName;
	private int age;
	private String pwd;
	private String email;  
	private String msg;		
	private String support;	//Person zur Unterstutzung
	
	
	
	//User anhand eingegebener Werte in Datenbank speichern
	public void register() {
		UserTO aUser = new UserTO();
		aUser.setFormnr(0);				//FormNr wird auf 0 gesetzt, da noch keine zugehoerige Form
		aUser.setVorname(firstName);
		aUser.setNachname(lastName);
		aUser.setEmail(email);
		aUser.setPassword(pwd);
		
		loginUser.updateUser(aUser);
		
		System.out.println("Registrieren erfolgreich");
		

		
	}
	
	public String registerMenue() {
		return "register";
	}
	
	public String loginMenue() {
		return "backToLogin";
	}
	
	public String goToWelcome() {
		return "goToWelcome";
	}



	//https://www.journaldev.com/7252/jsf-authentication-login-logout-database-example
	
	//validate login
	public String validateUsernamePassword() throws AnwendungskernException, DatenhaltungsException {
		UserTO aUser = new UserTO();
	
		aUser = loginUser.getUser(email);
		System.out.println("my name is:  "+aUser.getEmail());
		System.out.println("my login is:  "+email);
		
		if (aUser.getPassword().equals(pwd)) {
			System.out.println("Login succesfull");
			statelessActiveUser.setEmail(email);
			statelessActiveUser.setUserid(aUser.getId());

			statelessActiveUser.setFormId(aUser.getFormnr());

			return "formular";

			
		}else {
			System.out.println("Login not succesfull");

			return "login";
			
		}
		
		
	}
	
	public String goToLogin() {
		return "toLogin";
	}
	
	public static HttpSession getSession() {
		return (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(false);
	}

	public static HttpServletRequest getRequest() {
		return (HttpServletRequest) FacesContext.getCurrentInstance()
				.getExternalContext().getRequest();
	}

	public static String getUserName() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(false);
		return session.getAttribute("username").toString();
	}
	


	public static String getUserId() {
		HttpSession session = getSession();
		if (session != null)
			return (String) session.getAttribute("userid");
		else
			return null;
	}
	


	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String user) {
		this.email = user;
	}

	public String getSupport() {
		return support;
	}

	public void setSupport(String support) {
		this.support = support;
	}
	
	

}
