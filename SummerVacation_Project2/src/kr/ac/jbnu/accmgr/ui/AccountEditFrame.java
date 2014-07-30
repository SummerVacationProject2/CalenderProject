package kr.ac.jbnu.accmgr.ui;



import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import kr.ac.jbnu.accmgr.persistent.AccountDBManager;
import kr.ac.jbnu.accmgr.persistent.Manager;

public class AccountEditFrame extends JFrame implements ActionListener{

	AccountEditFrame aEF;
	AccountDBManager aDBM;
	AccountEntity accountModel;
	AccountTableEntity entity;
	InsertData insert = new InsertData();
	
	Manager m = new Manager();
	
	JButton btn_Edit, btn_Cancel;
	JComboBox comboBox_income, comboBox_expense, comboBox_cash;
	JTextField tf_income, tf_expense, tf_content;
	
	public ArrayList<String> incomeCategory = new ArrayList<String>();
	public ArrayList<String> expenseCategory = new ArrayList<String>();
	
	String[] str = new String[6];
	String incomeStr[],expenseStr[];
	String[] cashItems = {"현금"};
	String income, income_2, expense, expense_2, cash, content;
	
	int result;

	public AccountEditFrame() {
		setTitle("수정");
		setBounds(100, 100, 558, 371);
		getContentPane().setLayout(null);
		
		btn_Edit = new JButton("수정");
		btn_Edit.setBounds(98, 250, 105, 25);
		getContentPane().add(btn_Edit);
		btn_Edit.addActionListener(this);
		
		btn_Cancel = new JButton("취소");
		btn_Cancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				setVisible(false);
			}
		});
		btn_Cancel.addActionListener(this);
		btn_Cancel.setBounds(318, 250, 105, 25);
		getContentPane().add(btn_Cancel);
		
		m.getCategory(incomeCategory, expenseCategory);
	    
	    incomeStr = new String[incomeCategory.size()+1];
	    expenseStr = new String[expenseCategory.size()+1];
	    
	    for(int i=0; i<incomeCategory.size();i++)
	      {
	    	  if(i==0)
	    	  {
	    		  incomeStr[i] = "수입분류";
	    		  incomeStr[i+1] = incomeCategory.get(i);
	    	  }
	    	  else
	    		  incomeStr[i+1] = incomeCategory.get(i);
	      }
	      for(int i=0; i<expenseCategory.size();i++)
	      {
	    	  if(i==0)
	    	  {
	    		  expenseStr[i] = "지출분류";
	    		  expenseStr[i+1] = expenseCategory.get(i);
	    	  }
	    	  else
	    		  expenseStr[i+1] = expenseCategory.get(i);
	      }
		
		comboBox_income = new JComboBox(incomeStr);
		comboBox_income.setBounds(58, 60, 116, 23);
		getContentPane().add(comboBox_income);
		
		comboBox_expense = new JComboBox(expenseStr);
		comboBox_expense.setBounds(217, 60, 116, 23);
		getContentPane().add(comboBox_expense);
		
		comboBox_cash = new JComboBox(cashItems);
		comboBox_cash.setBounds(360, 60, 116, 23);
		getContentPane().add(comboBox_cash);
		
		tf_income = new JTextField();
		tf_income.setBounds(217, 126, 116, 23);
		getContentPane().add(tf_income);
		tf_income.setColumns(10);
		
		tf_expense = new JTextField("");
		tf_expense.setBounds(217, 161, 116, 23);
		getContentPane().add(tf_expense);
		tf_expense.setColumns(10);
		
		tf_content = new JTextField();
		tf_content.setBounds(217, 196, 116, 23);
		getContentPane().add(tf_content);
		tf_content.setColumns(10);
		
		JLabel ln_income = new JLabel("수입 :");
		ln_income.setHorizontalAlignment(SwingConstants.CENTER);
		ln_income.setBounds(141, 129, 62, 17);
		getContentPane().add(ln_income);
		
		JLabel lb_expense = new JLabel("지출 :");
		lb_expense.setHorizontalAlignment(SwingConstants.CENTER);
		lb_expense.setBounds(141, 164, 62, 17);
		getContentPane().add(lb_expense);
		
		JLabel lb_content = new JLabel("내역 :");
		lb_content.setHorizontalAlignment(SwingConstants.CENTER);
		lb_content.setBounds(141, 199, 62, 17);
		getContentPane().add(lb_content);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	
	public void setModifyAccountData()
	{
		for(int i = 1; i<incomeStr.length; i++)
		{
			if(i == comboBox_income.getSelectedIndex())
			{
				income = comboBox_income.getSelectedItem().toString();
			}
			else
				continue;
		}
		
		if(comboBox_income.getSelectedIndex()==0)
			income = " ";
		
		for(int i = 1; i<expenseStr.length; i++)
		{
			if(i == comboBox_expense.getSelectedIndex())
			{
				expense = comboBox_expense.getSelectedItem().toString();
			}
			else
				continue;
		}
		
		if(comboBox_expense.getSelectedIndex() == 0)
			expense = " ";
		
		cash = comboBox_cash.getSelectedItem().toString();
		income_2 = tf_income.getText().toString();
		expense_2 = tf_expense.getText().toString();
		content = tf_content.getText().toString();
	}
	
	public void setRow(AccountTableEntity entity)
	{
		this.entity = entity;
		str = entity.getString();
		accountModel = new AccountEntity(str[0]);
	}

	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource().equals(btn_Edit))
		{
			if(tf_content.getText().equals("") 
					|| (comboBox_income.getSelectedIndex() == 0 && comboBox_expense.getSelectedIndex() == 0)
					|| (tf_income.getText().equals("") && tf_expense.getText().equals("")))
			{
				JOptionPane.showMessageDialog(null, "모두 입력하시오.", "확인!", JOptionPane.WARNING_MESSAGE);
			}
			
			else
			{
				result = JOptionPane.showConfirmDialog(null, "수정하시겠습니까?","가계부수정",JOptionPane.YES_NO_CANCEL_OPTION);
				if(result == 0)
				{
					setModifyAccountData();
					m = new Manager();
					m.deleteAccount(str);
					insert.insertAccountData(str[0], income, expense, cash, income_2, expense_2, content);
					new InputFrame(str[0]);
					dispose();
				}
			}
		}
		else if(e.getSource().equals(btn_Cancel))
		{
			dispose();
			new InputFrame(str[0]);
		}
	}
}
	

