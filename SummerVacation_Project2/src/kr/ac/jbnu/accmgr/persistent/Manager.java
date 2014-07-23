package kr.ac.jbnu.accmgr.persistent;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import kr.ac.jbnu.accmgr.model.Account;


public class Manager 
{
	CategoryDBManager cdb = new CategoryDBManager();
	CalenderDBManager cd = new CalenderDBManager();

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
					incomeCategory.add(rs.getString("incomeCategory"));
	        }
			
	        rs = cdb.stmt.executeQuery("select DISTINCT expenseCategory from category;");
	        while(rs.next())
	        {
	        	if(!rs.getString("expenseCategory").equals(""))
	        		expenseCategory.add(rs.getString("expenseCategory"));
	        }
		}
		catch(SQLException se)
		{
			System.out.println(se.getMessage());
		}
	}
	public void getSchedule(String date,DefaultTableModel scheduleModel)
	{
		ResultSet rs = null;
		
		String arr[] = new String[3];
		
		Vector data = new Vector();
		Vector vector_col;
		Vector rows = new Vector();
		
		try
		{
	        cd.stmt = cd.con.createStatement();
	        rs = cd.stmt.executeQuery("select DISTINCT * from calender where "
	        		+ "date like '" + date + "%' and "
	        		+ "startTime like '" + "" + "%' and " 
	                + "endTime like '" + "" + "%' and "
	                + "schedule like '" + "" + "%'; " );
	          
	         while (rs.next())
	         {
	             arr[0] = rs.getString("startTime");
	             arr[1] = rs.getString("endTime");
	             arr[2] = rs.getString("schedule");
	             scheduleModel.addRow(arr);
	         }
	        }
			catch(SQLException se) {
	            System.out.println(se.getMessage());
	}
	}
	public void deleteSchedule(String[] str)
	{
		try {
			cd.stmt.executeUpdate("delete from calender where "
                 + "date = '" + str[0] + "' and " 
                 + "startTime = '" + str[1] + "' and " 
                 + "endTime = '" + str[2] + "' and "
                 + "schedule = '" + str[3] + "';");
        } catch (SQLException e1) {
           e1.printStackTrace();
        }
	}
	
	/*public void editSchedule(String[] str,String[] change)
	{
		try {
			cd.stmt.executeUpdate("UPDATE calender SET "
					+ "name = CASE"
					+ "WHEN id = 1 THEN "lion"
					+ "WHEN id = 2 THEN "cat"
					+ "END WHERE"
					+ "name IN (1, 2));"
					);
        } catch (SQLException e1) {
           e1.printStackTrace();
        }
	}*/
	public void getAccount(String date,DefaultTableModel accountModel)
	{
		ResultSet rs = null;
		
		String arr[] = new String[6];
		
		Vector data = new Vector();
		Vector vector_col;
		Vector rows = new Vector();
		
		try
		{
	        cd.stmt = cd.con.createStatement();
	        rs = cd.stmt.executeQuery("select DISTINCT * from account where "
					+ "date like '" + date + "%' and " 
					+ "incomeCategory like '" + "" + "%' and "
					+ "expenseCategory like '" + "" + "%' and "
					+ "cash like '" + "" + "%' and "
					+ "income like '" + "" + "%' and "
					+ "expense like '" + "" + "%' and "
					+ "breakdown like '" + "" + "%' and ");
	          
	         while (rs.next())
	         {
	             arr[0] = rs.getString("startTime");
	             arr[1] = rs.getString("endTime");
	             arr[2] = rs.getString("schedule");
	             arr[3] = rs.getString("income");
		         arr[4] = rs.getString("expense");
		         arr[5] = rs.getString("breakdown");
		         accountModel.addRow(arr);
	         }
	        }
			catch(SQLException se) {
	            System.out.println(se.getMessage());
	}
	}
}
        

	
	

