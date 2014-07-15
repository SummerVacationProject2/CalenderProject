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
			String TBName = "category";
			String dbURL = "jdbc:mysql://localhost:3306/"; // URL 지정
			String sqlCT = "CREATE TABLE IF NOT EXISTS category (" +
					"incomeCategory varchar(20) NOT NULL, " +
					"expenseCategory varchar(20) NOT NULL" +
					");";

			Class.forName(driverName);
			con  = DriverManager.getConnection(dbURL,"root","1412"); // 데이터베이스 연결
			stmt = con.createStatement();
			stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS categorydb");
		    con  = DriverManager.getConnection(dbURL+DBName,"root","1412");
		    stmt = con.createStatement();
		    stmt.executeUpdate(sqlCT);
		    stmt.executeUpdate("INSERT INTO category (incomeCategory,expenseCategory)"
		    		+ "SELECT * FROM (SELECT '"+toLatin1("식비")+"', '"+toLatin1("주수입")+"') AS tmp WHERE NOT EXISTS ("
		    		+ "SELECT incomeCategory FROM category WHERE incomeCategory = '"+toLatin1("식비")+"'"
		    		+ ") LIMIT 1;");
		    stmt.executeUpdate("INSERT INTO category (incomeCategory,expenseCategory)"
		    		+ "SELECT * FROM (SELECT '"+toLatin1("주거/통신")+"', '"+toLatin1("부수입")+"') AS tmp WHERE NOT EXISTS ("
		    		+ "SELECT incomeCategory FROM category WHERE incomeCategory = '"+toLatin1("주거/통신")+"'"
		    		+ ") LIMIT 1;");
		    stmt.executeUpdate("INSERT INTO category (incomeCategory,expenseCategory)"
		    		+ "SELECT * FROM (SELECT '"+toLatin1("미용")+"','') AS tmp WHERE NOT EXISTS ("
		    		+ "SELECT * FROM category WHERE incomeCategory = '"+toLatin1("미용")+"'"
		    		+ ") LIMIT 1;");
		    stmt.executeUpdate("INSERT INTO category (incomeCategory,expenseCategory)"
		    		+ "SELECT * FROM (SELECT '"+toLatin1("생활용품")+"','') AS tmp WHERE NOT EXISTS ("
		    		+ "SELECT incomeCategory FROM category WHERE incomeCategory = '"+toLatin1("생활용품")+"'"
		    		+ ") LIMIT 1;");
		    stmt.executeUpdate("INSERT INTO category (incomeCategory,expenseCategory)"
		    		+ "SELECT * FROM (SELECT '"+toLatin1("교육")+"','') AS tmp WHERE NOT EXISTS ("
		    		+ "SELECT incomeCategory FROM category WHERE incomeCategory = '"+toLatin1("교육")+"'"
		    		+ ") LIMIT 1;");
		}
		catch(Exception e)
		{
			System.out.println("Mysql Server Not Connection.");
			e.printStackTrace();
		}
	}
	public static void roadCategory()
	{
		
	}
	public static void insertCategory()
	{
		
	}
	public static void deleteCategory()
	{
		
	}
	
	 private static String toLatin1(String str)
	         throws java.io.UnsupportedEncodingException {
	            return new String(str.getBytes(), "ISO-8859-1");
	         }

}
