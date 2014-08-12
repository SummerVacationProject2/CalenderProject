package kr.ac.jbnu.accmgr.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import kr.ac.jbnu.accmgr.persistent.Manager;

public class InsertIncomeCategoryFrame extends JFrame implements ActionListener
{
	Manager m = new Manager();
	
	JButton btnConfirm,btnBack;
	JTextField txtIncomeCategory;
	String txtInsert;
	
	int result,num;
	
	public InsertIncomeCategoryFrame()
	{
		this.setBounds(150, 150, 270, 90);
		setTitle("수입 분류 추가");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setSize(270, 90);
		panel.setLayout(null);
		panel.setOpaque(false);
		
		panel.add(txtIncomeCategory = new JTextField());
		txtIncomeCategory.setBounds(5, 10, 110, 30);
		panel.add(btnConfirm = new JButton("추가"));
		btnConfirm.setBounds(120, 10, 60, 30);
		btnConfirm.addActionListener(this);
		panel.add(btnBack = new JButton("뒤로"));
		btnBack.setBounds(185, 10, 60, 30);
		btnBack.addActionListener(this);
		
		add(panel);
		setVisible(true);
	}
	
	public void setIncome()
	{
		txtInsert = txtIncomeCategory.getText().toString();
		num = 0;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(btnConfirm))
		{
			result = JOptionPane.showConfirmDialog(null, "다음 항목을 추가하시겠습니까?","수입분류추가",JOptionPane.YES_NO_CANCEL_OPTION);
			if(result == 0)
			{
				setIncome();
				m.insertCategory(txtInsert, num);
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
