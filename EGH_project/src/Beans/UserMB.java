package Beans;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import Persistence.AnwendungskernException;
import Persistence.DatenhaltungsException;
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

	PlainSHA512PasswordHash hash = new PlainSHA512PasswordHash();
	
	private String firstName = null;
	private String lastName = null;
	//private int age;
	private String pwd = null;
	private String email = null;
	private String msg = null;
	private String support = null; // person die den Ausfuellenden unterstuetzt
	private String registerSuccess = null;

	// User anhand eingegebener Werte in Datenbank speichern
	public String register() throws AnwendungskernException, DatenhaltungsException{
		

		if(!email.isBlank() && !pwd.isBlank() && !firstName.isBlank() && !lastName.isBlank() && !support.isBlank()  ) {

			System.out.println("Registrieren erfolgreich");
			
			UserTO aUser = new UserTO();
			aUser.setFormnr(0); // FormNr wird auf 0 gesetzt, da noch keine zugehoerige Form
			aUser.setVorname(firstName);
			aUser.setNachname(lastName);
			aUser.setEmail(email);
			aUser.setPassword(hash.generate(pwd.toCharArray()));
			aUser.setSupport(support);
			System.out.println("support: " + support);
			loginUser.updateUser(aUser);

			System.out.println("Registrieren erfolgreich");

			setRegisterSuccess("Die Registrierung war Erfolgreich. Sie können sich jetzt mit ihren Daten anmelden (E-Mail & Passwort).");
			return "backToLogin";
		}else{
			System.out.println("Registrieren nicht erfolgreich -> Keine User Daten");	
			setRegisterSuccess("Die Registrierung war Unerfolgreich! Bitte Überprüfen Sie ihre eingegeben Daten!");
			return "";
			
		
		
				}
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

	// https://www.journaldev.com/7252/jsf-authentication-login-logout-database-example

	// validate login
	public String validateUsernamePassword() throws AnwendungskernException, DatenhaltungsException {
		UserTO aUser = new UserTO();

		if (loginUser.getUser(email) == null) {
			System.out.println("Login NICHT erfolgreich (Falscher User)");
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Diese E-Mail Adresse ist nicht vorhanden", "Bitte richtige E-Mail eingeben"));

			return "login";
		} else {
			aUser = loginUser.getUser(email);
			System.out.println("my name is:  " + aUser.getEmail());
			System.out.println("my login is:  " + email);

//			boolean passwordCheck = aUser.getPassword().equals(pwd);
			boolean passwordCheck = hash.verify(pwd.toCharArray(), aUser.getPassword());
			
			if (passwordCheck == true) {
				System.out.println("Login succesfull");
				statelessActiveUser.setEmail(email);
				statelessActiveUser.setUserid(aUser.getId());

				statelessActiveUser.setFormId(aUser.getFormnr());

				return "formular";

			} else if (passwordCheck == false) {
				System.out.println("Login NICHT erfolgreich (Falsches Passwort)");
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
						"Falsches Passwort", "Bitte richtiges Passwort eingeben"));

				return "login";

			} else {
				System.out.println("Login NICHT erfolgreich");
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
						"Falsche E-Mail oder Passwort", "Bitte richtige E-Mail und Passwort eingeben"));

				return "login";
			}

		}
		
	}

	public String goToLogin() {
		return "toLogin";
	}

	public static HttpSession getSession() {
		return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
	}

	public static HttpServletRequest getRequest() {
		return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	}

	public static String getUserName() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		return session.getAttribute("username").toString();
	}

	public static String getUserId() {
		HttpSession session = getSession();
		if (session != null)
			return (String) session.getAttribute("userid");
		else
			return null;
	}

	// logout event, invalidate session
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

//  TODO: Is it in Use?
//	public int getAge() {
//		return age;
//	}
//
//	public void setAge(int age) {
//		this.age = age;
//	}

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

	public String getRegisterSuccess() {
		return registerSuccess;
	}

	public void setRegisterSuccess(String registerSuccess) {
		this.registerSuccess = registerSuccess;
	}

}
