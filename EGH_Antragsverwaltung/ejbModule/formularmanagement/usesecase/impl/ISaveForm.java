package formularmanagement.usesecase.impl;

import javax.ejb.Local;

import Peristence.AnwendungskernException;
import Peristence.DatenhaltungsException;
import formularmanagement.entity.ApplicationFormTO;

@Local
public interface ISaveForm{
	
	public void saveAnswers (int formNr, int userId, String bezeichnung) throws AnwendungskernException;
	public void saveNumber(int formNr);
	public void createForm(ApplicationFormTO aFormTO);
	public void updateForm(ApplicationFormTO aFormTO);
}
