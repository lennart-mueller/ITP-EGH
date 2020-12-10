package antragsverwaltung.usesecase.impl;

import javax.ejb.Local;

import Peristence.AnwendungskernException;
import Peristence.DatenhaltungsException;
import antragsverwaltung.entity.ApplicationFormTO;

@Local
public interface IRequestForm {
	
	public int requestForm() throws AnwendungskernException;
	public ApplicationFormTO getApplicationForm(int formNr) throws AnwendungskernException, DatenhaltungsException;

}
