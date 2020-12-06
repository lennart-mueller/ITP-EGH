package Peristence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;


public class PersistenceMysql {
	
	private static void doSshTunnel(String strSshUser, String strSshPassword, String strSshHost, int nSshPort,
		    String strRemoteHost, int nLocalPort, int nRemotePort) throws JSchException {
		final JSch jsch = new JSch();
		Session session = jsch.getSession(strSshUser, strSshHost, 22);
		session.setPassword(strSshPassword);

		final Properties config = new Properties();
		config.put("StrictHostKeyChecking", "no");
		session.setConfig(config);

		session.connect();
		session.setPortForwardingL(nLocalPort, strRemoteHost, nRemotePort);
	    }
	
	public static void Connect() {
		String strSshUser = "mueller"; // SSH loging username
	    String strSshPassword = "ubuntu-egh"; // SSH login password
	    String strSshHost = "131.173.88.192"; // hostname or ip or SSH server
	    int nSshPort = 22; // remote SSH host port number
	    String strRemoteHost = "127.0.0.1"; // hostname or ip of your database server
	    int nLocalPort = 3366; // local port number use to bind SSH tunnel
	    int nRemotePort = 3306; // remote port number of your database
	    String strDbUser = "root"; // database loging username
	    String strDbPassword = "mysql"; // database login password

	    String query = "SHOW DATABASES;";

	    PersistenceMysql.doSshTunnel(strSshUser, strSshPassword, strSshHost, nSshPort, strRemoteHost, nLocalPort, nRemotePort);

	    Class.forName("com.mysql.cj.jdbc.Driver");
	    try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:" + nLocalPort, strDbUser,
		    strDbPassword); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(query)) {

		while (rs.next()) {
		    System.out.println(rs.getString(1));

		}
		con.close();
	    } catch (SQLException ex) {
		Logger lgr = Logger.getLogger(PersistenceMysql.class.getName());
		lgr.log(Level.SEVERE, ex.getMessage(), ex);
	    }

	} catch (Exception e) {

	    e.printStackTrace();
	} finally {

	    System.exit(0);
	}
}


