package kr.ac.jbnu.accmgr.ui;

import java.awt.Button;
import java.awt.Color;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import kr.ac.jbnu.accmgr.model.ScheduleItem;
import kr.ac.jbnu.accmgr.persistent.CalenderDBManager;
import kr.ac.jbnu.accmgr.persistent.Manager;



public class EditFrame extends JFrame implements ActionListener
{
	EditFrame EF;
	JPanel panel;
	CalenderDBManager cd = new CalenderDBManager();
	Button edit_btn,cancle_btn;
	TextField textField_2;
	JLabel warning;
	JComboBox combostarthour, combostartmin, comboendhour ,comboendmin;
	String[] hours = new String[] {"시","1","2","3","4","5","6","7","8","9","10","11","12",
			"13","14","15","16","17","18","19","20","21","22","23","24"};
	String[] minutes = new String[] {"분","00","10","20","30","40","50"};
	String startTime,endTime,schedule;
	String[] str = new String[4];
	Manager m = new Manager();
	ScheduleTableEntity entity;
	ScheduleEntity scheduleModel;
	InsertData insert = new InsertData();
	
	public EditFrame()
	{
		setTitle("수정");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		panel = new JPanel();
		panel.setBounds(0, 0, 434, 261);
		panel.setLayout(null);
		getContentPane().add(panel);
		
		Label lb_StartTime = new Label("시작시간");
		lb_StartTime.setAlignment(Label.CENTER);
		lb_StartTime.setBounds(100, 33, 69, 23);
		panel.add(lb_StartTime);
		
		Label lb_EndTime = new Label("종료시간");
		lb_EndTime.setAlignment(Label.CENTER);
		lb_EndTime.setBounds(100, 94, 69, 23);
		panel.add(lb_EndTime);
		
		Label lb_Plan = new Label("일정");
		lb_Plan.setAlignment(Label.CENTER);
		lb_Plan.setBounds(100, 153, 69, 23);
		panel.add(lb_Plan);
		
		edit_btn = new Button("수정");
		edit_btn.addActionListener(this);
		
		edit_btn.setBounds(128, 211, 76, 23);
		panel.add(edit_btn);
		
		cancle_btn = new Button("취소");
		cancle_btn.addActionListener(this);
		
		cancle_btn.setBounds(243, 211, 76, 23);
		panel.add(cancle_btn);
		
		textField_2 = new TextField();
		textField_2.setBounds(195, 153, 143, 23);
		panel.add(textField_2);
		//panAccountBook.add(cashBox = new JComboBox<String>(cash));
		combostarthour = new JComboBox(hours);
		combostarthour.setBounds(195, 35, 54, 21);
		panel.add(combostarthour);
		
		combostartmin = new JComboBox(minutes);
		combostartmin.setBounds(277, 33, 61, 23);
		panel.add(combostartmin);
		
		comboendhour = new JComboBox(hours);
		comboendhour.setBounds(195, 96, 54, 21);
		panel.add(comboendhour);
		
		comboendmin = new JComboBox(minutes);
		comboendmin.setBounds(277, 94, 61, 23);
		panel.add(comboendmin);
		
		warning = new JLabel("모든 정보를 입력해주세요.");
		warning.setBounds(150, 240, 200, 15);
		warning.setForeground(new Color(255,0,0));
		warning.setVisible(false);
		panel.add(warning);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public void setModifyScheduleData()
	{
		for(int i=1; i<hours.length; i++)
	      {
	         if(i == combostarthour.getSelectedIndex())
	         {
	            startTime = combostarthour.getSelectedItem().toString() + " : ";
	         }
	         else
	            continue;
	      }
		for(int i=1; i<minutes.length; i++)
	      {
	         if(i == combostartmin.getSelectedIndex())
	         {
	            startTime += combostartmin.getSelectedItem().toString();
	         }
	         else
	            continue;
	      }
		for(int i=1; i<hours.length; i++)
	      {
	         if(i == comboendhour.getSelectedIndex())
	         {
	            endTime = comboendhour.getSelectedItem().toString() + " : ";
	         }
	         else
	            continue;
	      }
		for(int i=1; i<minutes.length; i++)
	      {
	         if(i == comboendmin.getSelectedIndex())
	         {
	            endTime += comboendmin.getSelectedItem().toString();
	         }
	         else
	            continue;
	      }
		
		schedule = textField_2.getText().toString();
	}
	
	public void setRow(ScheduleTableEntity entity)
	{
		this.entity = entity;
		str = entity.getString();
		System.out.println(str[0]+"***");
		scheduleModel = new ScheduleEntity(str[0]);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(edit_btn))
		{
			if(textField_2.getText().equals("") || combostarthour.getSelectedIndex()==0
					|| combostartmin.getSelectedIndex() == 0 
					|| comboendhour.getSelectedIndex() == 0
					|| comboendmin.getSelectedIndex() == 0)
			{
				warning.setVisible(true);
			}
			else
			{
				setModifyScheduleData();
				//String[] value = {str[1],str[2],str[3]};
				m.deleteSchedule(str);
				insert.insertScheduleData(str[0],startTime,endTime,schedule);
				new InputFrame(str[0]);
				dispose();
				//setVisible(false);
			}
		}
		else if(e.getSource().equals(cancle_btn))
		{
			//setVisible(false);
			dispose();
			new InputFrame(str[0]);
		}
	}

	public void setRow1(ScheduleTableEntity entity2) {
		// TODO Auto-generated method stub
		
	}

}
