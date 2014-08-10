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
	AccountDBManager ac = new AccountDBManager();

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
	
	public ArrayList<String> getExpenseCategory()
	{
		cdb.categorydbManager();
		ResultSet rs = null;
		ArrayList<String> expenseCategory = new ArrayList<String>();
		
		try 
		{
			cdb.stmt = cdb.con.createStatement();
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
		
		return expenseCategory;
	}
	
	public double[] getExpenseNumber(String date,ArrayList<String> expenseCategory)
	{
		ArrayList<String> category = new ArrayList<String>();
		category.addAll(expenseCategory);
		ResultSet rs = null;
		String str = "";
		double count = 0;
		double[] num = new double[category.size()];
		
		try
		{
			ac.stmt = ac.con.createStatement();
			rs = ac.stmt.executeQuery("select * from account where date Regexp '^"+date+"';");
			
			for(int i=0; i < category.size(); i++)
			{
				while(rs.next())
				{
					if(!rs.getString("expenseCategory").equals(""))
					{
						if(category.get(i).equals(rs.getString("expenseCategory")))
						{
							count = Double.parseDouble(rs.getString("expense"));
							num[i] += count;
						}
					}
					count = 0;
				}
				rs.beforeFirst();
			}
		}
		catch (SQLException e1) 
        {
	           e1.printStackTrace();
	    }
		
		return num;
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

	public void deleteAccount(String[] str)
	{
		try {
			ac.stmt.executeUpdate("delete from account where "
                 + "date = '" + str[0] + "' and " 
                 + "incomeCategory = '" + str[1] + "' and " 
                 + "expenseCategory = '" + str[2] + "' and "
                 + "cash = '" + str[3] + "' and "
                 + "income = '" + str[4] + "' and "
                 + "expense = '" + str[5] + "' and "
                 + "incomeBreakdown = '" + str[6] + "' and "
                 + "expenseBreakdown = '" + str[7] + "';");
        } catch (SQLException e1) {
           e1.printStackTrace();
        }

	}
	public void insertCategory(String category,int num)
	{
		if(num == 0)//incomeCategory老 锭
		{
			try 
			{
				cdb.stmt = cdb.con.createStatement();
				cdb.stmt.executeUpdate("insert into category(incomeCategory,expenseCategory)"
						+ "values('"+category+"','');");
			}
			catch(SQLException se)
			{
				System.out.println(se.getMessage());
			}
		}
		else if(num == 1)//expenseCategory老 锭
		{
			try 
			{
				cdb.stmt = cdb.con.createStatement();
				cdb.stmt.executeUpdate("insert into category(incomeCategory,expenseCategory)"
						+ "values('','"+category+"');");
			}
			catch(SQLException se)
			{
				System.out.println(se.getMessage());
			}
		}
		
	}
	public void deleteCategory(String category,int num)
	{
		if(num == 0)//incomeCategory老 锭
		{
			try 
			{
				cdb.stmt = cdb.con.createStatement();
				cdb.stmt.executeUpdate("delete from category where "
	                 + "incomeCategory = '" + category + "';");
	        } catch (SQLException e1) 
	        {
	           e1.printStackTrace();
	        }
		}
		else if(num == 1)//expenseCategory老 锭
		{
			try 
			{
				cdb.stmt = cdb.con.createStatement();
				cdb.stmt.executeUpdate("delete from category where "
	                 + "incomeCategory = '" + "" + "' and " 
	                 + "expenseCategory = '" + category + "';");
	        } catch (SQLException e1) 
	        {
	           e1.printStackTrace();
	        }
		}
	}
	public void resetAccount()
	{
		try
		{
			ac.stmt.executeUpdate("drop table account;");
		}
		catch (SQLException e1) 
        {
	           e1.printStackTrace();
	    }
	}
	public int getIncome(String date)
	{
		ResultSet rs = null;
		ArrayList<Integer> income = new ArrayList<Integer>();
		int totalIncome = 0;
		
		try
		{
			
			ac.stmt = ac.con.createStatement();
			rs = ac.stmt.executeQuery("select * from account where "
					+ "date like '" + date + "%';");
			
			while(rs.next())
			{
				if(!rs.getString("income").equals(""))
				{
					income.add(Integer.parseInt(rs.getString("income")));
				}
			}
		}
		catch (SQLException e1) 
        {
	           e1.printStackTrace();
	    }
		
		for(int i=0; i<income.size(); i++)
			totalIncome += income.get(i);
		
		return totalIncome;
	}
	public int getExpense(String date)
	{
		ResultSet rs = null;
		ArrayList<Integer> expense = new ArrayList<Integer>();
		int totalExpense = 0;
		
		try
		{
			
			ac.stmt = ac.con.createStatement();
			rs = ac.stmt.executeQuery("select * from account where "
					+ "date like '" + date + "%';");
			
			while(rs.next())
			{
				if(!rs.getString("expense").equals(""))
				{
					expense.add(Integer.parseInt(rs.getString("expense")));
				}
			}
		}
		catch (SQLException e1) 
        {
	           e1.printStackTrace();
	    }
		
		for(int i=0; i<expense.size(); i++)
			totalExpense += expense.get(i);
		
		return totalExpense;
	}
	public int getMonthIncome(String date)
	{
		ResultSet rs = null;
		int totalIncome = 0;
		
		try
		{
			ac.stmt = ac.con.createStatement();
			rs = ac.stmt.executeQuery("select sum(income) from account where date Regexp '^"+date+"';");
			
			while(rs.next())
			{
				rs.getString("sum(income)");
				if(rs.wasNull())
				{
					totalIncome = 0;
				}
				else
				{
					if(!rs.getString("sum(income)").equals(""))
						totalIncome = Integer.parseInt(rs.getString("sum(income)"));
				}
			}
		}
		catch (SQLException e1) 
        {
	           e1.printStackTrace();
	    }
		
		return totalIncome;
	}
	public int getMonthExpense(String date)
	{
		ResultSet rs = null;
		int totalExpense = 0;
		
		try
		{
			ac.stmt = ac.con.createStatement();
			rs = ac.stmt.executeQuery("select sum(expense) from account where date Regexp '^"+date+"';");
			
			while(rs.next())
			{
				rs.getString("sum(expense)");
				if(rs.wasNull())
					totalExpense = 0;
				else
				{
					if(!rs.getString("sum(expense)").equals(""))
						totalExpense = Integer.parseInt(rs.getString("sum(expense)"));
				}
			}
		}
		catch (SQLException e1) 
        {
	           e1.printStackTrace();
	    }
		
		return totalExpense;
	}
}
        

	
	

