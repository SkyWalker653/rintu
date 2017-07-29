/*package tools.automation.utility;


//STEP 1. Import required packages
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.Scanner;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

*//**
 * @author sheetam.j
 *
 *	This program generates an excel file which contains list of bugzilla application and the users associated to bugzilla accounts
 *
 *
 *//*
public class UsersBugzila {
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  

	static final String DB_URL = "jdbc:mysql://10.98.103.34:80/";

	//  Database credentials
	static final String USER = "root";
	static final String PASS = "tbrootadmin@123";

	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
	    
		JSONObject userobj = new JSONObject();
		
		try{
			//STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			//STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);

			//STEP 4: Execute a query
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			
			String sql = "SHOW DATABASES";
			
			ResultSet rs = stmt.executeQuery(sql);
			JSONArray dbList = new JSONArray(); 
			
			//STEP 5: Extract data from result set
			while(rs.next()){
				dbList.put(rs.getString(1));
			}
			
			rs.close();
			
			
			for(int i = 0 ; i < dbList.length() ; i++){
				
				try{

					String sqlquery = "SELECT login_name FROM "+dbList.getString(i)+".profiles";

					rs = stmt.executeQuery(sqlquery);
					JSONArray userList = null;
					userList = new JSONArray();

					while(rs.next()){
						
						userList.put(rs.getString(1));
						
					}
					
					userobj.put(dbList.getString(i), userList);
					
				}
				catch(Exception e){
					System.out.println(e.getMessage());
				}



			}
			//STEP 6: Clean-up environment
			rs.close();
			stmt.close();
			conn.close();
			
		}catch(ClassNotFoundException se){
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			
			XSSFWorkbook workbook = new XSSFWorkbook(); 
			XSSFSheet sheet = workbook.createSheet("User List");;

			Row row = null;
			Cell cell = null;
			
			Iterator<String> keys = userobj.keys();

			row = sheet.createRow(1);
			cell = row.createCell(1);
			cell.setCellValue("DATABASE");
			
			XSSFCellStyle cellStyle = workbook.createCellStyle();
			cellStyle.setFillForegroundColor(HSSFColor.GOLD.index);
			cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			cell.setCellStyle(cellStyle);
			
			cell = row.createCell(2);
			cell.setCellValue("USER");

			XSSFCellStyle cellStyles = workbook.createCellStyle();
			cellStyles.setFillForegroundColor(HSSFColor.BRIGHT_GREEN.index);
			cellStyles.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			cell.setCellStyle(cellStyles);
			
			int n = 2;
			while( keys.hasNext() ) {
			    String key = (String)keys.next();
				JSONArray users = userobj.getJSONArray(key);
				
				for(int i=0 ; i < users.length() ; i++){
					
					row = sheet.createRow(n);
					
					cell = row.createCell(1);
					cell.setCellValue(key);
					
					cell = row.createCell(2);
					cell.setCellValue(users.getString(i));
				    
					n++;
				}
			    
			}
			
			
			FileOutputStream out = null;
			try 
			{
				//Write the workbook in file system
				File file = new File("UsersTestLink.xlsx");
				out = new FileOutputStream(file);
				workbook.write(out);
				out.close();

				System.out.println("Completed ....");
				System.out.println("File path : "+file.getAbsolutePath());

			} 
			catch (Exception e) 
			{
				e.printStackTrace();

			}finally{
				try {
					out.close();
					
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			 
			
			
			
		} 
		catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}finally{
			//finally block used to close resources
			try{
				if(stmt!=null)
					stmt.close();
			}catch(SQLException se2){
			}// nothing we can do
			try{
				if(conn!=null)
					conn.close();
			}catch(SQLException se){
				se.printStackTrace();
			}//end finally try
		}//end try
		System.out.println("*********************************");
		System.out.println("*********	#	#	#	*********");
		System.out.println("    *     					*	 ");
		System.out.println("*********#		#		*********");
		System.out.println("*********************************");
	}//end main
}//end FirstExample*/