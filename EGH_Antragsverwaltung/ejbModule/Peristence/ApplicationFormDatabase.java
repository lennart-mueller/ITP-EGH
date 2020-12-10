
package Peristence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.jcraft.jsch.JSchException;

public class ApplicationFormDatabase implements IApplicationFormDatabase {
	



	
	public int getUserNr() throws DatenhaltungsException {
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
//		ResultSet resultSet;
//		try {
//			resultSet = Persistence.executeQueryStatement(aConnection,
//					"SELECT max(formnr) as max FROM egh_test");
//
//			if (resultSet.next())
//				return resultSet.getInt("max");
//			else
//				return 0;
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//			throw new DatenhaltungsException();
//		}
		return 0;
	}


}

