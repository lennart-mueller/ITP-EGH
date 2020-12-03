package Peristence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ApplicationFormDatabase implements IApplicationFormDatabase {
	



	
	public int getUserNr() throws DatenhaltungsException {
		int testnr = 55;
		Connection aConnection = PersistenceOracle.getConnection();
		ResultSet resultSet;
		try {
			resultSet = PersistenceOracle.executeQueryStatement(aConnection,
					"SELECT * " + "FROM egh_test " + "WHERE formnr = 22");

		
//				return resultSet.getInt("usernr");
				return testnr;
			
		

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DatenhaltungsException();
		}
		
//		int id = 555;
//		return id;
	}


}
