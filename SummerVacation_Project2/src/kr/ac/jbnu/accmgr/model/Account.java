package kr.ac.jbnu.accmgr.model;

public class Account {
	private String incomeCategory;
	private String expenseCategory;
	private String cash;
	private String income;
	private String expense;
	private String breakdown;
	private String[] tableEntity = new String[6];

	public Account(String incomeCategory, String expenseCategory, String cash, String income, String expense, String breakdown) {
		super();
		this.incomeCategory = incomeCategory;
		this.expenseCategory = expenseCategory;
		this.cash = cash;
		this.income = income;
		this.expense = expense;
		this.breakdown = breakdown;
	}
	
	public Account() {
		
	}

	public String getIncomeCategory() {
		return incomeCategory;
	}

	public void setIncomCategory(String incomeCategory) {
		this.incomeCategory = incomeCategory;
	}

	public String getExpenseCategory() {
		return expenseCategory;
	}

	public void setExpenseCategory(String expenseCategory) {
		this.expenseCategory = expenseCategory;
	}


	public String getCash() {
		return cash;
	}

	public void setCash(String cash) {
		this.cash = cash;
	}
	
	public String getIncome() {
		return income;
	}
	
	public void setIncome(String income) {
		this.income = income;
	}
	
	public String getExpense() {
		return expense;
	}
	
	public void setExpense(String expense) {
		this.expense = expense;
	}
	
	public String getBreakdown() {
		return breakdown;
	}
	
	public void setBreakdown(String breakdown) {
		this.breakdown = breakdown;
	}
	
	@Override
	public String toString() {
		return "Account [incomeCategory=" + incomeCategory + ", expenseCategory=" + expenseCategory
				+ ", cash=" + cash + ", income=" + income + ", expense=" + expense + ", breakdown=" + breakdown + "]";
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
