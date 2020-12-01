package Peristence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ApplicationFormDatabase implements IApplicationFormDatabase {
	



	
	public int getUserNr() throws DatenhaltungsException {
		Connection aConnection = Persistence.getConnection();
		ResultSet resultSet;
		try {
			resultSet = Persistence.executeQueryStatement(aConnection,
					"SELECT max(formnr) as max FROM egh_test");

			if (resultSet.next())
				return resultSet.getInt("max");
			else
				return 0;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DatenhaltungsException();
		}
	}


}
