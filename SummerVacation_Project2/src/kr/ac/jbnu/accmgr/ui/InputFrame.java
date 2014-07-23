package kr.ac.jbnu.accmgr.ui;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import kr.ac.jbnu.accmgr.persistent.Manager;



public class InputFrame extends JFrame implements ActionListener
{
	String incomeStr[],expenseStr[];
	public ArrayList<String> incomeCategory = new ArrayList<String>();
	public ArrayList<String> expenseCategory = new ArrayList<String>();
	public Manager m = new Manager();
	public InsertData insert = new InsertData();
	ScheduleTableEntity entity;
	AccountTableEntity aentity;
	
	String[] cash = {"����"};
	String[] scheduleRow = {"���۽ð�","����ð�","����"};
	String[] accountRow = {"���Ժз�","����з�","����","����","����","����"};
	String[] hour = {"��","1","2","3","4","5","6","7","8","9","10","11","12",
			"13","14","15","16","17","18","19","20","21","22","23","24"};
	String[] minute = {"��","00","10","20","30","40","50"};
	String date="";
	String startTime,endTime,schedule;
	String incomeCate,expenseCate,cashCate,income,expense,breakdown;
	
	int totalIncome, totalExpense = 0;
	
	JPanel panSchedule,panAccountBook;
	JLabel labSchedule,labAccountBook,label,labelIncome,labelExpense,label2,label3,label4;
	JComboBox startHourBox,startMinuteBox,endHourBox,endMinuteBox,incomeBox,expenseBox,cashBox;
	static JTextField txtSchedule = new JTextField();
	JTextField txtIncome,txtExpense,txtBreakdown;
	JTable scheduleTable,accountTable;
	static JButton btnInput1 = new JButton("�Է�");
	JButton btnInput2,btnModify1,btnModify2,btnDelete1,btnDelete2,btnBack;
	
//	DefaultTableModel scheduleModel = new DefaultTableModel(scheduleRow,0);
	ScheduleEntity scheduleModel; 
	AccountEntity accountModel;
	//DefaultTableModel accountModel = new DefaultTableModel(accountRow,0);
	
	public InputFrame(String date)
	{
		setSize(700,440);
		setTitle("Calender&Account Book");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    //this.setLayout(null);
		this.date = date;
		scheduleModel = new ScheduleEntity(date);
		accountModel = new AccountEntity(date);
		entity = new ScheduleTableEntity();
		aentity = new AccountTableEntity();
	    
	    panSchedule = new JPanel();
	    panSchedule.setBounds(0, 0, 300, 350);
	    panSchedule.setLayout(null);
	    panSchedule.setOpaque(false);
	    panSchedule.add(labSchedule = new JLabel("����   " + date));
	    labSchedule.setBounds(9, 3, 250, 15);
	    panSchedule.add(startHourBox = new JComboBox<String>(hour));
	    startHourBox.setBounds(7, 17, 65, 20);
	    panSchedule.add(startMinuteBox = new JComboBox<String>(minute));
	    startMinuteBox.setBounds(76, 17, 65, 20);
	    panSchedule.add(endHourBox = new JComboBox<String>(hour));
	    endHourBox.setBounds(163, 17, 65, 20);
	    panSchedule.add(endMinuteBox = new JComboBox<String>(minute));
	    endMinuteBox.setBounds(231, 17, 65, 20);
	    panSchedule.add(label = new JLabel("~"));
	    label.setBounds(147, 25, 10, 6);
	    panSchedule.add(txtSchedule);
	    txtSchedule.setBounds(7, 40, 225, 24);
	    panSchedule.add(btnInput1);
	    btnInput1.setBounds(236, 40, 60, 24);
	    panSchedule.add(scheduleTable = new JTable(scheduleModel));
	    scheduleTable.setBounds(7, 69, 290, 244);
	    JScrollPane jsp = new JScrollPane(scheduleTable);
	    jsp.setBounds(7, 69, 290, 244);
	    jsp.setVisible(true);
	    
//	    scheduleModel.setRowCount(0);
//		m.getSchedule(date, scheduleModel);
		scheduleTable.setModel(scheduleModel);
		scheduleModel.fireTableDataChanged();
        scheduleTable.requestFocusInWindow();
	    
	    panSchedule.add(jsp);
	    panSchedule.add(btnModify1 = new JButton("����"));
	    btnModify1.setBounds(58, 320, 60, 24);
	    panSchedule.add(btnDelete1 = new JButton("����"));
	    btnDelete1.setBounds(120, 320, 60, 24);
	    
	    panAccountBook = new JPanel();
	    panAccountBook.setBounds(350, 0, 400, 400);
	    panAccountBook.setLayout(null);
	    panAccountBook.setOpaque(false);
	    panAccountBook.add(labAccountBook = new JLabel("�����   " + date));
	    labAccountBook.setBounds(308, 3, 250, 15);
	    
 	    m.getCategory(incomeCategory, expenseCategory);
	    
	    incomeStr = new String[incomeCategory.size()+1];
	    expenseStr = new String[expenseCategory.size()+1];
	    
	    for(int i=0; i<incomeCategory.size();i++)
	      {
	    	  if(i==0)
	    	  {
	    		  incomeStr[i] = "���Ժз�";
	    		  incomeStr[i+1] = incomeCategory.get(i);
	    	  }
	    	  else
	    		  incomeStr[i+1] = incomeCategory.get(i);
	      }
	      for(int i=0; i<expenseCategory.size();i++)
	      {
	    	  if(i==0)
	    	  {
	    		  expenseStr[i] = "����з�";
	    		  expenseStr[i+1] = expenseCategory.get(i);
	    	  }
	    	  else
	    		  expenseStr[i+1] = expenseCategory.get(i);
	      }
	    
	    panAccountBook.add(incomeBox = new JComboBox<String>(incomeStr));
	    incomeBox.setBounds(308, 17, 116, 20);
	    panAccountBook.add(expenseBox = new JComboBox<String>(expenseStr));
	    expenseBox.setBounds(429, 17, 116, 20);
	    panAccountBook.add(cashBox = new JComboBox<String>(cash));
	    cashBox.setBounds(550, 17, 116, 20);
	    panAccountBook.add(label2 = new JLabel("���� : "));
	    label2.setBounds(307, 43, 50, 15);
	    panAccountBook.add(label3 = new JLabel("���� : "));
	    label3.setBounds(495, 43, 50, 15);
	    panAccountBook.add(txtIncome = new JTextField());
	    txtIncome.setBounds(347, 40, 130, 24);
	    panAccountBook.add(txtExpense = new JTextField());
	    txtExpense.setBounds(537, 40, 130, 24);
	    panAccountBook.add(label4 = new JLabel("���� : "));
	    label4.setBounds(307, 71, 50, 15);
	    panAccountBook.add(txtBreakdown = new JTextField());
	    txtBreakdown.setBounds(347, 69, 255, 24);
	    panAccountBook.add(btnInput2 = new JButton("�Է�"));
	    btnInput2.setBounds(606, 69, 60, 24);
	    panAccountBook.add(accountTable = new JTable(accountModel));
	    accountTable.setBounds(307, 97, 360, 216);
	    JScrollPane jsp2 = new JScrollPane(accountTable);
	    jsp2.setBounds(307, 97, 360, 216);
	    jsp2.setVisible(true);
	    panAccountBook.add(jsp2);
	    panAccountBook.add(btnModify2 = new JButton("����"));
	    btnModify2.setBounds(414, 320, 60, 24);
	    panAccountBook.add(btnDelete2 = new JButton("����"));
	    btnDelete2.setBounds(480, 320, 60, 24);
	    panAccountBook.add(labelIncome = new JLabel("�� ���� : " + totalIncome + "��"));
	    labelIncome.setBounds(557, 318, 150, 15);
	    panAccountBook.add(labelExpense = new JLabel("�� ���� : " + totalExpense + "��"));
	    labelExpense.setBounds(557, 335, 150, 15);
	    Font f=new Font("Sherif",Font.BOLD,12);
	    labelIncome.setFont(f);
	    labelExpense.setFont(f);
	    
	    btnBack = new JButton("�ڷ�");
	    btnBack.setBounds(310, 360, 60, 30);
	    
	    btnInput1.addActionListener(this);
	    btnInput2.addActionListener(this);
	    btnModify1.addActionListener(this);
	    btnModify2.addActionListener(this);
	    btnDelete1.addActionListener(this);
	    btnDelete2.addActionListener(this);
	    btnBack.addActionListener(this);
	    panAccountBook.add(panSchedule);
	    //add(panSchedule,"West");
	    add(btnBack);
	    add(panAccountBook);
	    setVisible(true);
	}
	
	public void setInsertScheduleData()
	{
		for(int i=1; i<hour.length; i++)
	      {
	         if(i == startHourBox.getSelectedIndex())
	         {
	            startTime = startHourBox.getSelectedItem().toString() + " : ";
	         }
	         else
	            continue;
	      }
		for(int i=1; i<minute.length; i++)
	      {
	         if(i == startMinuteBox.getSelectedIndex())
	         {
	            startTime += startMinuteBox.getSelectedItem().toString();
	         }
	         else
	            continue;
	      }
		for(int i=1; i<hour.length; i++)
	      {
	         if(i == endHourBox.getSelectedIndex())
	         {
	            endTime = endHourBox.getSelectedItem().toString() + " : ";
	         }
	         else
	            continue;
	      }
		for(int i=1; i<minute.length; i++)
	      {
	         if(i == endMinuteBox.getSelectedIndex())
	         {
	            endTime += endMinuteBox.getSelectedItem().toString();
	         }
	         else
	            continue;
	      }
		
		schedule = txtSchedule.getText().toString();
	}
	
	public void setInsertAccountData()
	{
		for(int i=1; i<incomeStr.length; i++)
	      {
	         if(i == incomeBox.getSelectedIndex())
	         {
	            incomeCate = incomeBox.getSelectedItem().toString();
	         }
	         else
	            continue;
	      }
		if(incomeBox.getSelectedIndex() == 0)
		{
			incomeCate = " ";
		}
		for(int i=1; i<expenseStr.length; i++)
	      {
	         if(i == expenseBox.getSelectedIndex())
	         {
	            expenseCate = expenseBox.getSelectedItem().toString();
	         }
	         else
	            continue;
	      }
		if(expenseBox.getSelectedIndex() == 0)
		{
			expenseCate = " ";
		}
		for(int i=0; i<cash.length; i++)
	      {
	         if(i == cashBox.getSelectedIndex())
	         {
	            cashCate = cashBox.getSelectedItem().toString();
	         }
	         else
	            continue;
	      }

		income = txtIncome.getText().toString();
		expense = txtExpense.getText().toString();
		breakdown = txtBreakdown.getText().toString();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnInput1))
		{
			if(txtSchedule.getText().equals("") || startHourBox.getSelectedIndex()==0
					|| startMinuteBox.getSelectedIndex() == 0 
					|| endHourBox.getSelectedIndex() == 0
					|| endMinuteBox.getSelectedIndex() == 0)
			{
				//�ƹ��͵����Ԥ���...
			}
			else
			{
			setInsertScheduleData();
			insert.insertScheduleData(date, startTime, endTime, schedule);
//			scheduleModel.setRowCount(0);
//			m.getSchedule(date, scheduleModel);
			String [] value = {startTime,endTime,schedule};
			scheduleTable.setModel(scheduleModel);
			scheduleModel.fireTableDataChanged();
			scheduleModel.insertData(value);
	        scheduleTable.requestFocusInWindow();
			txtSchedule.setText("");
			}
		}
		else if(e.getSource().equals(btnDelete1))
		{
			int row = scheduleTable.getSelectedRow();
	        String[] str = new String[4];
	        str[0] = date;
	        //System.out.println("str["+0+"]" + " : " + str[0]);
	        for(int i=1; i<str.length;i++)
	        {
	           str[i] = scheduleModel.getValueAt(row, i-1).toString();
	           //System.out.println("str["+i+"]" + " : " + str[i]);
	        }
			scheduleModel.removeRow(row);
			m.deleteSchedule(str);
		}
		else if(e.getSource().equals(btnModify1))
		{
			int row = scheduleTable.getSelectedRow();
	        String[] str = new String[4];
	        str[0] = date;
			
	        if(row > -1)
	        {
	        	for(int i=1; i<str.length;i++)
		        {
		           str[i] = scheduleModel.getValueAt(row, i-1).toString();
		        }
				//scheduleModel.removeRow(row); ���� �����ӿ� �ִ� ������ư�� ������ �� ���̺��� �ִ� ������ ����
	        	entity.setRow(row);
	        	entity.setString(str);
	        	EditFrame ef = new EditFrame();
	        	ef.setRow(entity);
	        	this.setVisible(false);
	        }
		}
		else if(e.getSource().equals(btnBack))
		{
			setVisible(false);
		}
		else if(e.getSource().equals(btnInput2))
		{
			if(txtBreakdown.getText().equals("") 
					|| (incomeBox.getSelectedIndex()==0 && expenseBox.getSelectedIndex() == 0)
					|| (txtIncome.getText().equals("") && txtExpense.getText().equals("")))
			{
				//�ƹ��͵����Ԥ���...
			}
			else
			{
				setInsertAccountData();
				insert.insertAccountData(date, incomeCate,expenseCate,cashCate,income,expense,breakdown);
				String [] value = {incomeCate,expenseCate,cashCate,income,expense,breakdown};

		        accountTable.setModel(accountModel);
		        accountModel.fireTableDataChanged();
		        accountModel.insertData(value);
		        accountTable.requestFocusInWindow();
				txtIncome.setText("");
				txtExpense.setText("");
				txtBreakdown.setText("");
			}
		}
	}
}