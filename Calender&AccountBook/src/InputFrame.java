import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class InputFrame extends JFrame implements ActionListener
{
	String[] incomeCategory = {"수입분류","식비","주거/통신","생활용품","의복","미용","문화","교육","육아","교통","회비","세금",};
	String[] expenseCategory = {"지출분류","주수입","부수입"};
	String[] cash = {"현금"};
	String[] scheduleRow = {"시작시간","종료시간","일정"};
	String[] accountRow = {"수입분류","지출분류","현금","수입","지출","내역"};
	String[] hour = {"시","1","2","3","4","5","6","7","8","9","10","11","12"};
	String[] minute = {"분","00","10","20","30","40","50"};
	String date;
	
	int totalIncome, totalExpense = 0;
	
	JPanel panSchedule,panAccountBook;
	JLabel labSchedule,labAccountBook,label,labelIncome,labelExpense,label2,label3,label4;
	JComboBox startHourBox,startMinuteBox,endHourBox,endMinuteBox,incomeBox,expenseBox,cashBox;
	JTextField txtSchedule,txtIncome,txtExpense,txtBreakdown;
	JTable scheduleTable,accountTable;
	JButton btnInput1,btnInput2,btnModify1,btnModify2,btnDelete1,btnDelete2,btnBack;
	DefaultTableModel scheduleModel = new DefaultTableModel(scheduleRow,0);
	DefaultTableModel accountModel = new DefaultTableModel(accountRow,0);
	
	public InputFrame(String date)
	{
		setSize(630,440);
		setTitle("Calender&Account Book");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    //this.setLayout(null);
		this.date = date;
	    
	    panSchedule = new JPanel();
	    panSchedule.setBounds(0, 0, 300, 350);
	    //panSchedule.setSize(250,350);
	    panSchedule.setLayout(null);
	    panSchedule.setOpaque(false);
	    panSchedule.add(labSchedule = new JLabel("일정   " + date));
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
	    panSchedule.add(txtSchedule = new JTextField());
	    txtSchedule.setBounds(7, 40, 225, 24);
	    panSchedule.add(btnInput1 = new JButton("입력"));
	    btnInput1.setBounds(236, 40, 60, 24);
	    panSchedule.add(scheduleTable = new JTable(scheduleModel));
	    scheduleTable.setBounds(7, 69, 290, 244);
	    JScrollPane jsp = new JScrollPane(scheduleTable);
	    jsp.setBounds(7, 69, 290, 244);
	    jsp.setVisible(true);
	    panSchedule.add(jsp);
	    panSchedule.add(btnModify1 = new JButton("수정"));
	    btnModify1.setBounds(58, 320, 60, 24);
	    panSchedule.add(btnDelete1 = new JButton("삭제"));
	    btnDelete1.setBounds(120, 320, 60, 24);
	    
	    panAccountBook = new JPanel();
	    panAccountBook.setBounds(350, 0, 350, 400);
	    panAccountBook.setLayout(null);
	    panAccountBook.setOpaque(false);
	    panAccountBook.add(labAccountBook = new JLabel("가계부   " + date));
	    labAccountBook.setBounds(308, 3, 250, 15);
	    panAccountBook.add(incomeBox = new JComboBox<String>(incomeCategory));
	    incomeBox.setBounds(308, 17, 93, 20);
	    panAccountBook.add(expenseBox = new JComboBox<String>(expenseCategory));
	    expenseBox.setBounds(405, 17, 93, 20);
	    panAccountBook.add(cashBox = new JComboBox<String>(cash));
	    cashBox.setBounds(503, 17, 93, 20);
	    panAccountBook.add(label2 = new JLabel("수입 : "));
	    label2.setBounds(307, 43, 50, 15);
	    panAccountBook.add(label3 = new JLabel("지출 : "));
	    label3.setBounds(455, 43, 50, 15);
	    panAccountBook.add(txtIncome = new JTextField());
	    txtIncome.setBounds(347, 40, 100, 24);
	    panAccountBook.add(txtExpense = new JTextField());
	    txtExpense.setBounds(497, 40, 100, 24);
	    panAccountBook.add(label4 = new JLabel("내역 : "));
	    label4.setBounds(307, 71, 50, 15);
	    panAccountBook.add(txtBreakdown = new JTextField());
	    txtBreakdown.setBounds(347, 69, 185, 24);
	    panAccountBook.add(btnInput2 = new JButton("입력"));
	    btnInput2.setBounds(536, 69, 60, 24);
	    panAccountBook.add(accountTable = new JTable(accountModel));
	    accountTable.setBounds(307, 97, 290, 216);
	    JScrollPane jsp2 = new JScrollPane(accountTable);
	    jsp2.setBounds(307, 97, 290, 216);
	    jsp2.setVisible(true);
	    panAccountBook.add(jsp2);
	    panAccountBook.add(btnModify2 = new JButton("수정"));
	    btnModify2.setBounds(334, 320, 60, 24);
	    panAccountBook.add(btnDelete2 = new JButton("삭제"));
	    btnDelete2.setBounds(400, 320, 60, 24);
	    panAccountBook.add(labelIncome = new JLabel("총 수입 : " + totalIncome + "원"));
	    labelIncome.setBounds(477, 318, 150, 15);
	    panAccountBook.add(labelExpense = new JLabel("총 지출 : " + totalExpense + "원"));
	    labelExpense.setBounds(477, 335, 150, 15);
	    Font f=new Font("Sherif",Font.BOLD,12);
	    labelIncome.setFont(f);
	    labelExpense.setFont(f);
	    
	    btnBack = new JButton("뒤로");
	    btnBack.setBounds(270, 360, 60, 30);
	    
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
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(btnBack))
		{
			setVisible(false);
		}
		
		
	}
}
