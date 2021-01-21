package usermanagement.usecase;

import java.util.Hashtable;
import java.util.Optional;

import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.naming.AuthenticationException;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

import Persistence.AnwendungskernException;
import Persistence.DatenhaltungsException;
import usermanagement.dao.UserDAO;
import usermanagement.entity.UserTO;
import usermanagement.entity.impl.User;
import usermanagement.usecase.impl.ILoginUser;

@Singleton
public class LoginUser implements ILoginUser {

	@Inject
	UserDAO userDAO;

	public UserTO getUser(String username) throws AnwendungskernException, DatenhaltungsException {
		System.out.println("get Login User");
		
		UserTO user = null;
		try {
			user = userDAO.findUserByName(username).toUserTO();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (user == null)
			return null;
		else
			return userDAO.findUserByName(username).toUserTO();

	}

	public void updateUser(UserTO aUserTO) {

		User aUser = new User();
		aUser = aUserTO.toUser();
		aUser.setId(aUserTO.getId()); // statt manuelle zuweisung in Application this.formNr = formNr??;
		userDAO.update(aUser);
		// TODO Auto-generated method stub

	}

	public Optional authentifiziereBenutzer(UserTO userTO) throws AuthenticationException {
		User aUser = new User();
		aUser = userTO.toUser();
		try {
			Hashtable<String, String> env = new Hashtable<>();
			env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
			env.put(Context.PROVIDER_URL, "ldap://ldap.example.de");
			env.put(Context.SECURITY_PRINCIPAL, aUser.getEmail());
			env.put(Context.SECURITY_CREDENTIALS, aUser.getPassword());
			DirContext ctx = new InitialDirContext(env);
			return Optional.of(aUser);
		} catch (NamingException e) {
			throw new AuthenticationException("Benutzername oder Passwort falsch");
		}
	}
}
