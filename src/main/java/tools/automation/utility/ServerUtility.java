package tools.automation.utility;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;



import tools.automation.bean.SvnedgeServers;

public class ServerUtility {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://10.98.141.69:3306/tsms";
	static final String USER = "tsms_user";
	static final String PASS = "tsms_password";

	public static void updateServerUtilization() throws ClassNotFoundException, SQLException {

		List<SvnedgeServers> servers = SvnedgeServers.findAllSvnedgeServerses();

		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
		
		
		for (SvnedgeServers svnedgeServers : servers) {

			Statement statement = conn.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT `hdd_3_utilization` FROM `tass_server_inputs` WHERE `unique_name` = (SELECT `unique_name` FROM `server_metadata` WHERE `server_ip` = '"+svnedgeServers.getIpAddress()+"') ORDER BY `date_now` DESC LIMIT 1");
			
			int hddUtilization = 0;
			if(resultSet.next()) {
				hddUtilization = resultSet.getInt("hdd_3_utilization");
			}

			svnedgeServers.setUsedMemory(new BigDecimal(hddUtilization));
			svnedgeServers.merge();

		}

		conn.close();

	}

}
