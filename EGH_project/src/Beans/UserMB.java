package Beans;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
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

@Named("userMB")
@RequestScoped
public class UserMB implements Serializable {
	
private static final long serialVersionUID = 1094801825228386363L;

	@Inject
	ILoginUser loginUser;
	
	@EJB(beanName = "ActiveUser")
	IActiveUser statelessActiveUser;
	
	private String firstName;
	private String lastName;
	private int age;
	private String pwd;
	private String email;  
	private String msg;		//wofuer?

	
	
	
	//User nahand eingegebener Werte in Datenbank speichern
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
		return "login";
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
		
		
//		boolean valid = LoginDAO.validate(user, pwd);
//		if (valid) {
//			HttpSession session = SessionUtils.getSession();
//			session.setAttribute("username", user);
//			return "formular";
//		} else {
//			FacesContext.getCurrentInstance().addMessage(
//					null,
//					new FacesMessage(FacesMessage.SEVERITY_WARN,
//							"Incorrect Username and Passowrd",
//							"Please enter correct username and Password"));
//			return "login";
//		}
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
	

	//logout event, invalidate session
//	public String logout() {
//		HttpSession session = SessionUtils.getSession();
//		session.invalidate();
//		return "login";
//	}
	
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

}
