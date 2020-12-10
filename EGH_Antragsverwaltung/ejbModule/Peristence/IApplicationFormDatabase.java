package Peristence;

import antragsverwaltung.entity.ApplicationFormTO;

public interface IApplicationFormDatabase {
	
	public int getUserNr() throws DatenhaltungsException;
	public void saveForm(int nr, String bezeichnung) throws DatenhaltungsException;
	public ApplicationFormTO getForm(int formNr);
	public void saveAnswer(int formNr, int questionNr, int answerNr)throws DatenhaltungsException;

}
