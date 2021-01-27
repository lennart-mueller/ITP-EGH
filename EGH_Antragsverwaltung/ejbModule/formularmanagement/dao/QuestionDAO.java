package formularmanagement.dao;

import javax.ejb.Stateless;

import formularmanagement.entity.impl.Question;

@Stateless
public class QuestionDAO extends GenericDAO<Question> {

	public QuestionDAO() {
		super(Question.class);
	}

}
