package usermanagement.usecase.impl;

import java.util.Optional;

import javax.ejb.Local;
import javax.naming.AuthenticationException;

import Persistence.AnwendungskernException;
import Persistence.DatenhaltungsException;
import usermanagement.entity.UserTO;
import usermanagement.entity.impl.User;

@Local
public interface ILoginUser {
	
	public UserTO getUser(String username)throws AnwendungskernException, DatenhaltungsException;
	public void updateUser(UserTO aUserTO);
//  Not in Use
//	public Optional authentifiziereBenutzer(UserTO userTO) throws AuthenticationException;
	
	
	

}
