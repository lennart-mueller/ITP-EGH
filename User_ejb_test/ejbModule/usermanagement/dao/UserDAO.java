package usermanagement.dao;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.Stateless;

import usermanagement.entity.impl.User;

@Stateless
public class UserDAO extends GenericDAO<User> {
	
	public UserDAO() {
		super(User.class);
	}

	public void delete(User aUser) {
		super.delete(aUser.getId(), User.class);
	}
	
	 public User findUserByName(String username) {
	    	Map<String, Object> parameters = new HashMap<String, Object>();
	    	System.out.println("Username: "+username);
	    	parameters.put("email", username);

	    	return super.findOneResult(User.FIND_BY_NAME, parameters);
	    }
	



}
