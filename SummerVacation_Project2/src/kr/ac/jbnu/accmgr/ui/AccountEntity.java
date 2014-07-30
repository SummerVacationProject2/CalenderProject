package kr.ac.jbnu.accmgr.ui;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import kr.ac.jbnu.accmgr.model.Account;
import kr.ac.jbnu.accmgr.persistent.AccountDBManager;

public class AccountEntity extends AbstractTableModel 
{
	private ArrayList<Account> accounts = new ArrayList<Account>();
	private String[] accountRow = {"수입분류","지출분류","현금","수입","지출","내역"};
	AccountDBManager ad = new AccountDBManager();
	
	public AccountEntity(String date) 
	{
		ResultSet rs = null;
		
		String arr[] = new String[6];
		
		try
		{
			ad.stmt = ad.con.createStatement();
			rs = ad.stmt.executeQuery("select DISTINCT * from account where "
					+ "date like '" + date + "%' and " 
					+ "incomeCategory like '" + "" + "%' and "
					+ "expenseCategory like '" + "" + "%' and "
					+ "cash like '" + "" + "%' and "
					+ "income like '" + "" + "%' and "
					+ "expense like '" + "" + "%' and "
					+ "breakdown like '" + "" + "%';"
					);
			
			while (rs.next())
			{
				arr[0] = rs.getString("incomeCategory");
				arr[1] = rs.getString("expenseCategory");
	            arr[2] = rs.getString("cash");
	            arr[3] = rs.getString("income");
	            arr[4] = rs.getString("expense");
	            arr[5] = rs.getString("breakdown");
	            accounts.add(new Account(arr[0],arr[1],arr[2],arr[3],arr[4],arr[5]));
			}
		}
		catch(SQLException se)
		{
			System.out.println(se.getMessage());
		}
	}
	
	@Override
	public int getRowCount() {
			return accounts.size();
	}
	
	@Override
	public int getColumnCount() {
			return accountRow.length;
	}
	
	public String getColumnName(int col){
			 return accountRow[col];
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if( rowIndex >= accounts.size())
		{
			return "";
		}
		if( columnIndex >= accountRow.length)
		{
			return "";
		}
		
		Account ac = accounts.get(rowIndex);
		
		switch (columnIndex)
		{
			case 0:
				return ac.getIncomeCategory();
			case 1:
				return ac.getExpenseCategory();
			case 2:
				return ac.getCash();
			case 3:
				return ac.getIncome();
			case 4:
				return ac.getExpense();
			case 5:
				return ac.getBreakdown();
			default:
		}
		
		return "";
	}
	
	public void insertData(String[] values){
		accounts.add(new Account(values[0],values[1],values[2],values[3],values[4],values[5]));
		fireTableDataChanged();
	}
	
	public void removeRow(int row){
		accounts.remove(row);
		fireTableDataChanged();
	}
}