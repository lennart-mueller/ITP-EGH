package antragsverwaltung.usesecase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.Persistence;

import Peristence.AnwendungskernException;
import Peristence.DatenhaltungsException;
import antragsverwaltung.dao.FormDAO;
import antragsverwaltung.dao.QuestionDAO;
import antragsverwaltung.entity.ApplicationFormTO;
import antragsverwaltung.entity.QuestionTO;
import antragsverwaltung.entity.impl.ApplicationForm;
import antragsverwaltung.entity.impl.Question;
import antragsverwaltung.usesecase.impl.IRequestForm;

@Stateless
public class RequestForm implements IRequestForm {

	@Inject
	FormDAO formDAO;

	@Inject
	QuestionDAO questionDAO;

	// Laden einer Form
	public ApplicationFormTO getApplicationForm(int formNr) throws AnwendungskernException, DatenhaltungsException {
		System.out.println("Request Form - getApplicationFromdb");
		return formDAO.find(formNr).toFormTO();
	}

	
	public int getFormNumber(int userNr) throws DatenhaltungsException {
		
	return 0;
	
		
	}

	// Laden aller Formulare
	@Override
	public List<ApplicationFormTO> getAllForms() {
		List<ApplicationForm> aList = formDAO.findAll();
		List<ApplicationFormTO> returnList = new ArrayList<ApplicationFormTO>();
		for (ApplicationForm aForm : aList) {
			returnList.add(aForm.toFormTO());

		}

		return returnList;
	}

	// Laden alelr Frragen
	@Override
	public List<QuestionTO> getAllQuestions() {
		List<Question> aList = questionDAO.findAll();
		List<QuestionTO> returnList = new ArrayList<QuestionTO>();
		for (Question aQuestion : aList) {
			returnList.add(aQuestion.toQuestionTO());

		}

		return returnList;
	}

}
