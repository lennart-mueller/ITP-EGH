package usecase.impl;

import javax.ejb.Local;

import Persistence.AnwendungskernException;

@Local
public interface IRequestForm {
	
	public int requestForm() throws AnwendungskernException;

}
