package kr.ac.jbnu.accmgr.persistent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class CategoryDBManager 
{
	public static Connection con = null;
	public static Statement stmt = null;
	
	public static void categorydbManager()
	{
		try
	    {
			String driverName = "com.mysql.jdbc.Driver";
			String DBName = "categorydb";
			String dbURL = "jdbc:mysql://localhost:3306/"; // URL 지정
			String sqlCT = "CREATE TABLE IF NOT EXISTS category (" +
					"idx int primary key auto_increment, " + 
					"incomeCategory varchar(20) character set utf8 NOT NULL, " +
					"expenseCategory varchar(20) character set utf8 NOT NULL" +
					") default charset = utf8 ;";

			Class.forName(driverName);
			con  = DriverManager.getConnection(dbURL,"root","1412"); // 데이터베이스 연결
			stmt = con.createStatement();
			stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS " + DBName);
		    con  = DriverManager.getConnection(dbURL+DBName,"root","1412");
		    stmt = con.createStatement();
		    stmt.executeUpdate(sqlCT);
/*		    stmt.executeUpdate("INSERT INTO category (incomeCategory,expenseCategory)"
		    		+ "SELECT * FROM (SELECT '주수입', '식비') AS tmp WHERE NOT EXISTS ("
		    		+ "SELECT expenseCategory FROM category WHERE expenseCategory = '식비'"
		    		+ ") LIMIT 1;");
		    stmt.executeUpdate("INSERT INTO category (incomeCategory,expenseCategory)"
		    		+ "SELECT * FROM (SELECT '부수입', '주거/통신') AS tmp WHERE NOT EXISTS ("
		    		+ "SELECT expenseCategory FROM category WHERE expenseCategory = '주거/통신'"
		    		+ ") LIMIT 1;");
		    stmt.executeUpdate("INSERT INTO category (incomeCategory,expenseCategory)"
		    		+ "SELECT * FROM (SELECT '','미용') AS tmp WHERE NOT EXISTS ("
		    		+ "SELECT expenseCategory FROM category WHERE expenseCategory = '미용'"
		    		+ ") LIMIT 1;");
		    stmt.executeUpdate("INSERT INTO category (incomeCategory,expenseCategory)"
		    		+ "SELECT * FROM (SELECT '','생활용품') AS tmp WHERE NOT EXISTS ("
		    		+ "SELECT expenseCategory FROM category WHERE expenseCategory = '생활용품'"
		    		+ ") LIMIT 1;");
		    stmt.executeUpdate("INSERT INTO category (incomeCategory,expenseCategory)"
		    		+ "SELECT * FROM (SELECT '','교육') AS tmp WHERE NOT EXISTS ("
		    		+ "SELECT expenseCategory FROM category WHERE expenseCategory = '교육'"
		    		+ ") LIMIT 1;");*/
		}
		catch(Exception e)
		{
			System.out.println("Mysql Server Not Connection.");
			e.printStackTrace();
		}
	}
}
