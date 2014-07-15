import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class AccountDBManager
{
	public static Connection con = null;
	public static Statement stmt = null;
	
	public static void accountdbManager()
	{
		try
	    {
			String driverName = "com.mysql.jdbc.Driver";
			String DBName = "accountdb";
			String dbURL = "jdbc:mysql://localhost:3306/"; // URL 지정
			String sqlCT = "CREATE TABLE IF NOT EXISTS account (" + 
					"date varchar(20) NOT NULL, " + 
					"incomeCategory varchar(20)  NOT NULL, " +
					"expenseCategory varchar(20) NOT NULL, " +
					"cash varchar(20) NOT NULL, " +
					"income int NOT NULL, " +
					"expense int NOT NULL, " +
					"breakDown text NOT NULL, " +
					"count int NOT NULL" +
					");";

			Class.forName(driverName);
			con  = DriverManager.getConnection(dbURL,"root","1412"); // 데이터베이스 연결
			stmt = con.createStatement();
			stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS accountdb");
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
	public static void insertAccount()
	{
		
	}
	public static void deleteAccount()
	{
		
	}
	public static void modifyAccount()
	{
		
	}

}
