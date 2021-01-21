package usermanagement.entity;

import usermanagement.entity.impl.User;

public class UserTO {
	
	private int id;
	private String email;
	private String password;
	private String vorname;
	private String nachname;
	private int formnr;
	private String support;
	
	
	public User toUser() {
		User aUser = new User(this.getId(), this.getEmail(), this.getPassword(), this.getVorname(), this.getNachname(), this.getFormnr(), this.getSupport());

		return aUser;
	}
	
	
	
	
	public int getFormnr() {
		return formnr;
	}




	public void setFormnr(int formnr) {
		this.formnr = formnr;
	}




	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getVorname() {
		return vorname;
	}
	public void setVorname(String vorname) {
		this.vorname = vorname;
	}
	public String getNachname() {
		return nachname;
	}
	public void setNachname(String nachname) {
		this.nachname = nachname;
	}




	public String getSupport() {
		return support;
	}




	public void setSupport(String support) {
		this.support = support;
	}
	





}
