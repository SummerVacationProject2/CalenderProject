package kr.ac.jbnu.accmgr.ui;

import java.sql.ResultSet;
import java.sql.SQLException;

import kr.ac.jbnu.accmgr.persistent.AccountDBManager;
import kr.ac.jbnu.accmgr.persistent.CalenderDBManager;

public class InsertData
{
	CalenderDBManager cd = new CalenderDBManager();
	AccountDBManager ad = new AccountDBManager();
	int count = 0;
	
	public void insertScheduleData(String date,String startTime,String endTime,String schedule,int start)
	{
		cd.scheduledbManager();;
		
		ResultSet rs = null;
		
		try 
		{
			cd.stmt = cd.con.createStatement();
			cd.stmt.executeUpdate("insert into calender(date,startTime,endTime,schedule,start)"
					+ "values('"+date+"','"+startTime+"','"+endTime+"','"+schedule+"',"+start+");");
		}
		catch(SQLException se)
		{
			System.out.println(se.getMessage());
		}
	}
	public void insertAccountData(String date,String incomeCate,String expenseCate,String cash, String income, String expense, String breakdown)
	{
		ad.accountdbManager();
		ResultSet rs = null;
		
		try
		{
			ad.stmt = ad.con.createStatement();
			ad.stmt.executeUpdate("insert into account(date,incomeCategory,expenseCategory,cash,income,expense,breakDown)"
					+ "values('"+date+"','"+incomeCate+"','"+expenseCate+"','"+cash+"','"+income+"','"+expense+"','"+breakdown+"');");
		}
		catch(SQLException se1)
		{
			System.out.println(se1.getMessage());
		}
	}
}
