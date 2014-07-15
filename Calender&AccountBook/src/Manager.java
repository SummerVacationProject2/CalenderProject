import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class Manager 
{
	CategoryDBManager cdb = new CategoryDBManager();

	public void getCategory(ArrayList<String> incomeCategory,ArrayList<String> expenseCategory)
	{
		cdb.categorydbManager();
		ResultSet rs = null;
		try 
		{
			cdb.stmt = cdb.con.createStatement();
			rs = cdb.stmt.executeQuery("select DISTINCT incomeCategory from category;");
			while (rs.next())
			{
				if(!rs.getString("incomeCategory").equals(""))
					incomeCategory.add(toUnicode(rs.getString("incomeCategory")));
	        }
			
	        rs = cdb.stmt.executeQuery("select DISTINCT expenseCategory from category;");
	        while(rs.next())
	        {
	        	if(!rs.getString("expenseCategory").equals(""))
	        		expenseCategory.add(toUnicode(rs.getString("expenseCategory")));
	        }
		}
		catch(SQLException | UnsupportedEncodingException se)
		{
			System.out.println(se.getMessage());
		}
	}
	 private String toUnicode(String str)
	         throws java.io.UnsupportedEncodingException {
	            return new String(str.getBytes("ISO-8859-1"));
	         }
}
