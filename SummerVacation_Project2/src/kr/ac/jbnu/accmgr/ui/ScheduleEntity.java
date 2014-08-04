package kr.ac.jbnu.accmgr.ui;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import kr.ac.jbnu.accmgr.model.ScheduleItem;
import kr.ac.jbnu.accmgr.persistent.CalenderDBManager;

public class ScheduleEntity extends AbstractTableModel {

	private String[] scheduleRow = {"시작시간","종료시간","일정"};
	private ArrayList<ScheduleItem> items = new ArrayList<ScheduleItem>();
	CalenderDBManager cd = new CalenderDBManager();
	
	public ScheduleEntity(String date) {
		ResultSet rs = null;
		
		String arr[] = new String[3];
		int start = 0;
		
		try
		{
	        cd.stmt = cd.con.createStatement();

	        rs = cd.stmt.executeQuery("select * from calender where "
	        		+ "date like '" + date + "%' order by start asc;");
	         
	         while (rs.next())
	         {
	             arr[0] = rs.getString("startTime");
	             arr[1] = rs.getString("endTime");
	             arr[2] = rs.getString("schedule");
	             items.add(new ScheduleItem(arr[0],arr[1],arr[2]));
	         }
	     }
		catch(SQLException se) 
		{
			System.out.println(se.getMessage());
	    }
	}
	
	@Override
	public int getRowCount() {
		return items.size();
	}

	@Override
	public int getColumnCount() {
		return scheduleRow.length;
	}
	public String getColumnName(int col){
		 return scheduleRow[col];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if( rowIndex >= items.size())
		{
			return "";
		}
		if( columnIndex >= scheduleRow.length)
		{
			return "";
		}
		
		ScheduleItem si = items.get(rowIndex);

		
	      switch (columnIndex) {
	      case 0:
	        return si.getStartDate();
	      case 1:
	    	  return si.getEndDate();
	      case 2:
	    	  return si.getContents();
	      default:
	      }
		return "";
	}
	public void insertData(String[] values){
		items.add(new ScheduleItem(values[0],values[1],values[2]));
		fireTableDataChanged();
	}
	public void removeRow(int row){
		items.remove(row);
		fireTableDataChanged();
	}
}
