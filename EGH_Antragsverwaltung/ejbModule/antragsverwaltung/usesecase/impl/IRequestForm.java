package antragsverwaltung.usesecase.impl;

import java.util.List;

import javax.ejb.Local;

import Peristence.AnwendungskernException;
import Peristence.DatenhaltungsException;
import antragsverwaltung.entity.ApplicationFormTO;
import antragsverwaltung.entity.QuestionTO;

@Local
public interface IRequestForm {
	
	
	public ApplicationFormTO getApplicationForm(int formNr) throws AnwendungskernException, DatenhaltungsException;
	public int getFormNumber(int userNr) throws DatenhaltungsException;
	public List <ApplicationFormTO> getAllForms();
	public List <QuestionTO> getAllQuestions();
	

}
