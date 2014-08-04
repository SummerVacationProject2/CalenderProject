package kr.ac.jbnu.accmgr.persistent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;


public class CalenderDBManager
{
	public static Connection con = null;
	public static Statement stmt = null;
	
	public static void scheduledbManager()
	{
		try
	    {
			String driverName = "com.mysql.jdbc.Driver";
			String DBName = "calenderdb";
			String dbURL = "jdbc:mysql://localhost:3306/"; // URL 지정
			String sql = "select * from student;";
			String sqlCT = "CREATE TABLE IF NOT EXISTS calender (" +
					"idx int primary key auto_increment, " + 
					"date varchar(20) character set utf8 NOT NULL, " +
					"startTime varchar(20) character set utf8 NOT NULL, " +
					"endTime varchar(20) character set utf8 NOT NULL, " +
					"schedule varchar(80) character set utf8 NOT NULL, " +
					"start int NOT NULL "+
					")default charset = utf8;";

			Class.forName(driverName);
			con  = DriverManager.getConnection(dbURL,"root","1412"); // 데이터베이스 연결
			stmt = con.createStatement();
			stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS " + DBName);
		    con  = DriverManager.getConnection(dbURL+DBName,"root","1412");
		    stmt = con.createStatement();
		    stmt.executeUpdate(sqlCT);
		}
		catch(Exception e)
		{
			System.out.println("Mysql Server Not Connection.");
			e.printStackTrace();
		}
	}
}