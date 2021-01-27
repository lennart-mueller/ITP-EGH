package usermanagement.entity.impl;

import java.util.Hashtable;
import java.util.Optional;

import javax.naming.AuthenticationException;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import usermanagement.entity.UserTO;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Access(AccessType.FIELD)
@Table(name="EGH_USER")
@NamedQuery(name="User.findUserByName", query="select u from User u where u.email = :email")

public class User {
	
	public static final String FIND_BY_NAME = "User.findUserByName";
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(unique = true)
	private String email;
	private String password;
	
	private int formnr;
	private String vorname;
	private String nachname;
	private String support;
	
	public User() {}
	
	public User(int id, String vorname, String nachname) {
		super();
		this.id = id;
		this.vorname = vorname;
		this.nachname = nachname;
	}
	
	public User(int id, String email, String password, String vorname, String nachname, int formnr, String support) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.vorname = vorname;
		this.nachname = nachname;
		this.formnr = formnr;
		this.support = support;
	}
	
	public User(int id, String email, String password, String vorname, String nachname) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.vorname = vorname;
		this.nachname = nachname;
	}
	
	public UserTO toUserTO() {
		UserTO aUserTO = new UserTO();

		aUserTO.setId(id);
		aUserTO.setNachname(nachname);
		aUserTO.setVorname(vorname);
		aUserTO.setPassword(password);
		aUserTO.setEmail(email);
		aUserTO.setFormnr(formnr);
		aUserTO.setSupport(support);
	
		return aUserTO;
	}

	@Override
	public int hashCode() {
		return getId();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof User){
			User user = (User) obj;
			return user.email.equals(getEmail());
		}		
		return false;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String name) {
		this.email = name;
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

