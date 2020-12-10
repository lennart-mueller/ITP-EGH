package antragsverwaltung.usesecase.impl;

import javax.ejb.Local;

import Peristence.AnwendungskernException;
import Peristence.DatenhaltungsException;

@Local
public interface ISaveForm{
	public void fillForm(int nr, String bezeichnung) throws AnwendungskernException, DatenhaltungsException;

}
