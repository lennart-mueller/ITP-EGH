package antragsverwaltung.entity;

import java.util.Collection;

public class AnswerTO {
	
	private Collection<AnswerChangeTO> answersChange;
	private Collection<AnswerIndividuellTO> answersIndividuell;
	
	
	public Collection<AnswerChangeTO> getAnswersChange() {
		return answersChange;
	}
	public void setAnswersChange(Collection<AnswerChangeTO> answersChange) {
		this.answersChange = answersChange;
	}
	public Collection<AnswerIndividuellTO> getAnswersIndividuell() {
		return answersIndividuell;
	}
	public void setAnswersIndividuell(Collection<AnswerIndividuellTO> answersIndividuell) {
		this.answersIndividuell = answersIndividuell;
	}
	
	

}
