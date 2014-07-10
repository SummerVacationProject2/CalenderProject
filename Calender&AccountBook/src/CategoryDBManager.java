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
					"incomeCategory varchar(20) character set utf8 NOT NULL, " +
					"expenseCategory varchar(20) character set utf8 NOT NULL" +
					") default charset = utf8 ;";

			Class.forName(driverName);
			con  = DriverManager.getConnection(dbURL,"root","1412"); // 데이터베이스 연결
			stmt = con.createStatement();
			stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS categorydb");
		    con  = DriverManager.getConnection(dbURL+DBName,"root","1412");
		    stmt = con.createStatement();
		    stmt.executeUpdate(sqlCT);
		    stmt.executeUpdate("alter database categorydb default character set 'utf8'");
		    stmt.executeUpdate("alter table category default character set utf8 collate utf8_general_ci");
		    stmt.executeUpdate("INSERT INTO category (incomeCategory,expenseCategory)"
		    		+ "SELECT * FROM (SELECT '식비', '주수입') AS tmp WHERE NOT EXISTS ("
		    		+ "SELECT incomeCategory FROM category WHERE incomeCategory = '식비'"
		    		+ ") LIMIT 1;");
		    stmt.executeUpdate("INSERT INTO category (incomeCategory,expenseCategory)"
		    		+ "SELECT * FROM (SELECT '주거/통신', '부수입') AS tmp WHERE NOT EXISTS ("
		    		+ "SELECT incomeCategory FROM category WHERE incomeCategory = '주거/통신'"
		    		+ ") LIMIT 1;");
		    stmt.executeUpdate("INSERT INTO category (incomeCategory,expenseCategory)"
		    		+ "SELECT * FROM (SELECT '미용','') AS tmp WHERE NOT EXISTS ("
		    		+ "SELECT * FROM category WHERE incomeCategory = '미용'"
		    		+ ") LIMIT 1;");
		    stmt.executeUpdate("INSERT INTO category (incomeCategory,expenseCategory)"
		    		+ "SELECT * FROM (SELECT '생활용품','') AS tmp WHERE NOT EXISTS ("
		    		+ "SELECT incomeCategory FROM category WHERE incomeCategory = '생활용품'"
		    		+ ") LIMIT 1;");
		    stmt.executeUpdate("INSERT INTO category (incomeCategory,expenseCategory)"
		    		+ "SELECT * FROM (SELECT '교육','') AS tmp WHERE NOT EXISTS ("
		    		+ "SELECT incomeCategory FROM category WHERE incomeCategory = '교육'"
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

}
