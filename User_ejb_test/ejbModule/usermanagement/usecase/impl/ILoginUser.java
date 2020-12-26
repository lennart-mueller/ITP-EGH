package usermanagement.usecase.impl;

import javax.ejb.Local;

import Persistence.AnwendungskernException;
import Persistence.DatenhaltungsException;
import usermanagement.entity1.UserTO;

@Local
public interface ILoginUser {
	
	public UserTO getUser(String username)throws AnwendungskernException, DatenhaltungsException;
	public void updateUser(UserTO aUserTO);

	
	
	

}
