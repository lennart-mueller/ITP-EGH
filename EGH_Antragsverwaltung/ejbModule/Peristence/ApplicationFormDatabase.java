
package Peristence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.jcraft.jsch.JSchException;

import antragsverwaltung.entity.ApplicationFormTO;

public class ApplicationFormDatabase implements IApplicationFormDatabase {
	



	
	public int getUserNr() throws DatenhaltungsException {
		
		/*Code fuer das Auslesen ein Nr aus Datenbank hinzufuegen*/
		
		Connection aConnection;
		try {
			aConnection = PersistenceMysql.Connect();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (JSchException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return 0;
	}

	//speichert einen Antrag in die DB
	public void saveForm(int nr, String bezeichnung) throws DatenhaltungsException {
		
		
		/*Code fuer Speicherung in DB hinzufuegen*/
	}

	
	public ApplicationFormTO getForm(int formNr) {
		/* Code fuer Auslesen eines Antrags hinzufuegen*/
		
		int nrausDb = 22;
		String descriptionAusDb = "hallo";
		
		ApplicationFormTO aForm = null;
		aForm.setFormNr(nrausDb);
		aForm.setDescription(descriptionAusDb);
		return aForm;
	}

	@Override
	public void saveAnswer(int formNr, int questionNr, int answerNr) throws DatenhaltungsException {
		
		/* Code fuer Speichern einer Antwort hinzufuegen*/
		
		
	}


	
	
	
}

