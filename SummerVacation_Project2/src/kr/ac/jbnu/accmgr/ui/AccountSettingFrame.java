package kr.ac.jbnu.accmgr.ui;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import kr.ac.jbnu.accmgr.persistent.Manager;

class AccountSettingFrame extends JFrame implements ActionListener
{
	Manager m = new Manager();
	
	JButton btnInsertIncome,btnInsertExpense,btnDeleteCategory,btnReset,btnBack;
	int result;
	
	public AccountSettingFrame()
	{
		this.setBounds(150, 150, 200, 214);
		setTitle("����� ����");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setSize(200, 214);
		panel.setLayout(null);
		panel.setOpaque(false);
		
		panel.add(btnInsertIncome = new JButton("���Ժз��߰�"));
		btnInsertIncome.setBounds(25, 10, 140, 25);
		btnInsertIncome.addActionListener(this);
		panel.add(btnInsertExpense = new JButton("����з��߰�"));
		btnInsertExpense.setBounds(25, 42, 140, 25);
		btnInsertExpense.addActionListener(this);
		panel.add(btnDeleteCategory = new JButton("ī�װ�����"));
		btnDeleteCategory.setBounds(25, 74, 140, 25);
		btnDeleteCategory.addActionListener(this);
		panel.add(btnReset = new JButton("������ʱ�ȭ"));
		btnReset.setBounds(25, 106, 140, 25);
		btnReset.addActionListener(this);
		panel.add(btnBack = new JButton("�ڷ� ����"));
		btnBack.setBounds(25, 138, 140, 25);
		btnBack.addActionListener(this);
		
		add(panel);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		// TODO Auto-generated method stub
		if(e.getSource().equals(btnInsertIncome))
		{
			new InsertIncomeCategoryFrame();
			setVisible(false);
		}
		else if(e.getSource().equals(btnInsertExpense))
		{
			new InsertExpenseCategoryFrame();
			setVisible(false);
		}
		else if(e.getSource().equals(btnDeleteCategory))
		{
			new DeleteCategoryFrame();
			setVisible(false);
		}
		else if(e.getSource().equals(btnReset))
		{
			result = JOptionPane.showConfirmDialog(null, "�����Ͻðڽ��ϱ�?","������ʱ�ȭ",JOptionPane.YES_NO_CANCEL_OPTION);
			if(result == 0)
			{
				m.resetAccount();
				setVisible(false);
				new SwingCalender();
			}
		}
		else if(e.getSource().equals(btnBack))
		{
			setVisible(false);
			new SwingCalender();
		}
		
	}
	
}
