package com.wipro.sales.util;

import java.sql.Connection;
import java.sql.DriverManager;
//import java.sql.PreparedStatement;

public class DBUtil {
public static Connection getDBConnection()
{
	Connection con=null;

try {
	Class.forName("oracle.jdbc.driver.OracleDriver");
	  con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","SYSTEM","root");
	
}
catch(Exception e)
{
	System.out.println(e);
}
return con;
}
}
