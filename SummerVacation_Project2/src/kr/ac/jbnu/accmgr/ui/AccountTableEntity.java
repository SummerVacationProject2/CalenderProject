package kr.ac.jbnu.accmgr.ui;

public class AccountTableEntity {
	int row;
	String[] str = new String[7];
	
	public void setRow(int row)
	{
		this.row = row;
	}
	public int getRow()
	{
		return row;
	}
	public void setString(String[] str)
	{
		this.str = str;
	}
	public String[] getString()
	{
		return str;
	}
}
