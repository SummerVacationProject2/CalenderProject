package kr.ac.jbnu.accmgr.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import kr.ac.jbnu.accmgr.persistent.Manager;

public class DeleteCategoryFrame extends JFrame implements ActionListener
{
	Manager m = new Manager();
	
	JButton btnDeleteIncome,btnDeleteExpense,btnBack;
	JComboBox incomeBox,expenseBox;
	
	public ArrayList<String> incomeCategory = new ArrayList<String>();
	public ArrayList<String> expenseCategory = new ArrayList<String>();
	String incomeStr[],expenseStr[];
	String incomeCate,expenseCate;
	
	int result,num;
	
	public DeleteCategoryFrame()
	{
		this.setBounds(150, 150, 200, 150);
		setTitle("ī�װ� ����");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setSize(200, 150);
		panel.setLayout(null);
		panel.setOpaque(false);
		
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
		
		panel.add(incomeBox = new JComboBox<String>(incomeStr));
		incomeBox.setBounds(5, 10, 110, 30);
		panel.add(btnDeleteIncome = new JButton("����"));
		btnDeleteIncome.setBounds(120, 10, 60, 30);
		btnDeleteIncome.addActionListener(this);
		panel.add(expenseBox = new JComboBox<String>(expenseStr));
		expenseBox.setBounds(5, 47, 110, 30);
		panel.add(btnDeleteExpense = new JButton("����"));
		btnDeleteExpense.setBounds(120, 47, 60, 30);
		btnDeleteExpense.addActionListener(this);
		panel.add(btnBack = new JButton("�ڷ�"));
		btnBack.setBounds(65, 82, 60, 25);
		btnBack.addActionListener(this);
		
		add(panel);
		setVisible(true);
	}
	public void deleteCategory()
	{
		for(int i=1; i<incomeStr.length; i++)
		{
			if(i == incomeBox.getSelectedIndex())
			{
				incomeCate = incomeBox.getSelectedItem().toString();
				num = 0;
			}
			else
				continue;
		}
		for(int i=1; i<expenseStr.length; i++)
	    {
			if(i == expenseBox.getSelectedIndex())
			{
				expenseCate = expenseBox.getSelectedItem().toString();
				num = 1;
			}
			else
				continue;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(btnDeleteIncome))
		{
			result = JOptionPane.showConfirmDialog(null, "���� �׸��� �����Ͻðڽ��ϱ�?","���Ժз�����",JOptionPane.YES_NO_CANCEL_OPTION);
			// YES = 0, NO = 1, CANCEL = 2, exit icon = -1 
			if(result == 0)
			{
				deleteCategory();
				m.deleteCategory(incomeCate, num);//��ɿϷ�
				setVisible(false);
				new SwingCalender();
			}
		}
		else if(e.getSource().equals(btnDeleteExpense))
		{
			result = JOptionPane.showConfirmDialog(null, "���� �׸��� �����Ͻðڽ��ϱ�?","����з�����",JOptionPane.YES_NO_CANCEL_OPTION);
			if(result == 0)
			{
				deleteCategory();
				m.deleteCategory(expenseCate, num);
				setVisible(false);
				new SwingCalender();
			}
		}
		else if(e.getSource().equals(btnBack))
		{
			new AccountSettingFrame();
			setVisible(false);
		}
	}

}
