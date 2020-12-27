package antragsverwaltung.dao;

import javax.ejb.Stateless;

import antragsverwaltung.entity.impl.Question;

@Stateless
public class QuestionDAO extends GenericDAO<Question> {
	
	public QuestionDAO() {
		super(Question.class);
	}


}
