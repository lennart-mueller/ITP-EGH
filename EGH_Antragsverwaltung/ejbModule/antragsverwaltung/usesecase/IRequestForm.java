package antragsverwaltung.usesecase;

import javax.ejb.Local;

import Peristence.AnwendungskernException;

@Local
public interface IRequestForm {
	
	public int requestForm() throws AnwendungskernException;

}
