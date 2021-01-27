package formularmanagement.usesecase;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import Peristence.AnwendungskernException;
import Peristence.DatenhaltungsException;
import formularmanagement.dao.FormDAO;
import formularmanagement.dao.QuestionDAO;
import formularmanagement.entity.ApplicationFormTO;
import formularmanagement.entity.QuestionTO;
import formularmanagement.entity.impl.ApplicationForm;
import formularmanagement.entity.impl.Question;
import formularmanagement.usesecase.impl.IRequestForm;

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

	// Laden aller Frragen
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
