package kr.ac.jbnu.accmgr.model;

public class ScheduleItem {
	private String startDate;
	private String endDate;
	private String contents;
	private String[] tableEntity = new String[4];

	public ScheduleItem(String startDate, String endDate, String contents) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
		this.contents = contents;
	}
	
	public ScheduleItem() {
		// TODO Auto-generated constructor stub
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}


	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}
	
	@Override
	public String toString() {
		return "ScheduleItem [startDate=" + startDate + ", endDate=" + endDate
				+ ", contents=" + contents + "]";
	}
	
	public void setTableEntity(String str[])
	{
		for(int i=0;i<str.length;i++)
			this.tableEntity[i] = str[i];
	}
	public String[] getTableEntity()
	{
		return tableEntity;
	}


}
