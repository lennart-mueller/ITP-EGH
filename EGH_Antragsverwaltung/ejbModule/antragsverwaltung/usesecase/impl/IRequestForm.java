package antragsverwaltung.usesecase.impl;

import javax.ejb.Local;

import Peristence.AnwendungskernException;

@Local
public interface IRequestForm {
	
	public int requestForm() throws AnwendungskernException;

}
