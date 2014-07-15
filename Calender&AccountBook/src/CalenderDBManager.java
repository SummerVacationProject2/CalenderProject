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
			String sqlCT = "CREATE TABLE IF NOT EXISTS calender(" +
					"date varchar(20) NOT NULL, " +
					"startTime varchar(20) NOT NULL, " +
					"endTime varchar(20) NOT NULL, " +
					"schedule text NOT NULL, " +
					"count int NOT NULL" +
					");";

			Class.forName(driverName);
			con  = DriverManager.getConnection(dbURL,"root","1412"); // 데이터베이스 연결
			stmt = con.createStatement();
			stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS calenderdb");
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
	public static void insertSchedule()
	{
		
	}
	public static void deleteSchedule()
	{
		
	}
	public static void modifySchedule()
	{
		
	}
}