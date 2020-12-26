package usermanagement.usecase;

import javax.ejb.Stateless;
import javax.inject.Inject;

import Persistence.AnwendungskernException;
import Persistence.DatenhaltungsException;
import usermanagement.dao.UserDAO;
import usermanagement.entity.impl.User;
import usermanagement.entity1.UserTO;
import usermanagement.usecase.impl.ILoginUser;



@Stateless
public class LoginUser implements ILoginUser{
	
	@Inject
	UserDAO userDAO;

	public UserTO getUser(String username)throws AnwendungskernException, DatenhaltungsException {
		System.out.println("get Login User");
		return userDAO.findUserByName(username).toUserTO();
		
	}
	
public void updateUser(UserTO aUserTO) {
		
		User aUser = new User();
		aUser = aUserTO.toUser();
		aUser.setId(aUserTO.getId());	//statt manuelle zuweisung in Application this.formNr = formNr??;
		userDAO.update(aUser);
		// TODO Auto-generated method stub
		
	}
}
