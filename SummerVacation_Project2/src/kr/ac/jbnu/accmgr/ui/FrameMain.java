package kr.ac.jbnu.accmgr.ui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import kr.ac.jbnu.accmgr.persistent.AccountDBManager;
import kr.ac.jbnu.accmgr.persistent.CalenderDBManager;
import kr.ac.jbnu.accmgr.persistent.CategoryDBManager;
import kr.ac.jbnu.accmgr.persistent.Manager;

class SwingCalender extends JFrame implements ActionListener
{
	Manager m = new Manager();
	Date d = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");	
    String todayDate = sdf.format(d);
    String date = "";
    
	String [] days = {"��","��","ȭ","��","��","��","��"};
    int year ,month,day,todays,memoday,totalIncome,totalExpen,balance=0;
    Font f;
    Color bc,fc;
    Calendar today;
    Calendar cal;
    JButton btnBefore,btnAfter,btnSetting;
    JButton[] calBtn = new JButton[49];
    JLabel thing;
    JLabel time,labelIncome,labelExpen,labelBalance;
    JPanel panWest;
    JPanel panSouth;
    JPanel panNorth;
    JTextField txtMonth,txtYear;
    JTextField txtTime;
    BorderLayout bLayout= new BorderLayout();     
       ////////////////////////////////////////
    public SwingCalender()
    {
    	today = Calendar.getInstance(); //����Ʈ�� Ÿ�� �� �� �������� ����� �޷��� �����ɴϴ�.
    	cal = new GregorianCalendar(); //��´޷�
    	year = today.get(Calendar.YEAR);
    	month = today.get(Calendar.MONTH)+1;//1���� ���� 0 
    	panNorth = new JPanel();
    	panNorth.add(btnBefore = new JButton("Before"));
    	panNorth.add(txtYear = new JTextField(year+"��"));
    	panNorth.add(txtMonth = new JTextField( month+"��",3));
    	txtYear.setEnabled(false);
    	txtMonth.setEnabled(false);
    	panNorth.add(btnAfter = new JButton("After"));
    	panNorth.add(btnSetting = new JButton("����μ���"));
    	f=new Font("Sherif",Font.BOLD,18);
    	txtYear.setFont(f);
    	txtMonth.setFont(f);
    	add(panNorth,"North");
    	//�̳��� �޷¿� ���� �ش��ϴ� �κ�
    	panWest = new JPanel(new GridLayout(7,7));//���ڳ�,���������� ��ġ������
    	f=new Font("Sherif",Font.BOLD,12);
    	gridInit();
    	calSet();
    	hideInit();
    	add(panWest,"Center");
    	
    	panSouth = new JPanel();
    	panSouth.add(labelIncome = new JLabel("�� ���� : " + totalIncome+"��     "));
    	panSouth.add(labelExpen = new JLabel("�� ���� : " + totalExpen+"��     "));
    	panSouth.add(labelBalance = new JLabel("�� �� : " + balance+"��     "));
    	add(panSouth,"South");
    	f=new Font("Sherif",Font.BOLD,18);
    	labelIncome.setFont(f);
    	labelExpen.setFont(f);
    	labelBalance.setFont(f);
    	
    	btnBefore.addActionListener(this);
    	btnAfter.addActionListener(this);
    	btnSetting.addActionListener(this);
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setTitle("Calender&Account Book");
    	setBounds(100,100,645,550);
    	setVisible(true);
    }//end constuctor
    
    public void calSet()
    {
    	cal.set(Calendar.YEAR,year);
    	cal.set(Calendar.MONTH,(month-1));
    	cal.set(Calendar.DATE,1);
    	int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
    	/*
    	 * get �� set �� ���� �ʵ�ġ��, ������ ��Ÿ���ϴ�.
    	 * �� �ʵ��� ����,SUNDAY,MONDAY,TUESDAY,WEDNESDAY
    	 * ,THURSDAY,FRIDAY, �� SATURDAY �� �˴ϴ�.
    	 * get()�޼ҵ��� ���� ������ ���ڷ� ��ȯ
    	 */
    	int j=0;
    	int hopping=0;
    	calBtn[0].setForeground(new Color(255,0,0));//�Ͽ��� "��"
    	calBtn[6].setForeground(new Color(0,0,255));//����� "��"
    	
    	for(int i=cal.getFirstDayOfWeek();i<dayOfWeek;i++){  j++;  }
    	/*
    	 * �Ͽ��Ϻ��� �״��� ù���� ���ϱ��� ��ĭ���� �����ϱ� ���� 
    	 */
    	
    	hopping=j;
    	
    	for(int kk=0;kk<hopping;kk++)
    	{
    		calBtn[kk+7].setText("");
    	}
    	
    	for(int i=cal.getMinimum(Calendar.DAY_OF_MONTH);
    			i<=cal.getMaximum(Calendar.DAY_OF_MONTH);i++)
    	{
    		cal.set(Calendar.DATE,i);
    		
    		if(cal.get(Calendar.MONTH) !=month-1)
    		{
    			break;
    		}
    		
    		todays=i;
    		
    		if(memoday==1)
    		{
    			calBtn[i+6+hopping].setForeground(new Color(0,255,0));
    		}
    		else
    		{
    			calBtn[i+6+hopping].setForeground(new Color(0,0,0));
    			
    			if((i+hopping-1)%7==0)
    			{//�Ͽ���
    				calBtn[i+6+hopping].setForeground(new Color(255,0,0));
    			}
    			if((i+hopping)%7==0)
    			{//�����
    				calBtn[i+6+hopping].setForeground(new Color(0,0,255));
    			}
    		}
    		/*
    		 * ������ ���� �������� ����ؾ� �ϴ� ������ ���� ��ư�� ������ ���ϰ�
    		 * �ε����� 0���� �����̴� -1�� ���� ������ ������ ���ְ�
    		 * ��ư�� ������ �������ش�.
    		 */
    		
    		calBtn[i+6+hopping].setText((i)+"");
    	}//for
    }//end Calset()
    
    public void actionPerformed(ActionEvent ae)
    {
    	if(ae.getSource() == btnBefore)
    	{
    		this.panWest.removeAll();
    		calInput(-1);
    		gridInit();
    		panelInit();
    		calSet();
    		hideInit();
    		this.txtYear.setText(year+"��");
    		this.txtMonth.setText(month+"��");
    	}
    	else if(ae.getSource() == btnAfter)
    	{
    		this.panWest.removeAll();
    		calInput(1);
    		gridInit();
    		panelInit();
    		calSet();
    		hideInit();
    		this.txtYear.setText(year+"��");
    		this.txtMonth.setText(month+"��");
    	}
    	else if(ae.getSource().equals(btnSetting))
    	{
    		new AccountSettingFrame();
    		setVisible(false);
    	}
    	else if(Integer.parseInt(ae.getActionCommand()) >= 1 && 
    			Integer.parseInt(ae.getActionCommand()) <=31)
    	{
    		day = Integer.parseInt(ae.getActionCommand());
    		//��ư�� ��� �� 1,2,3.... ���ڸ� ���������� ��ȯ�Ͽ� Ŭ���� ��¥�� �ٲ��ش�.
    		
    		if(day > 9)
            {
            	if(month > 9 )
            		date = year+"-"+month+"-"+day;
            	else
            		date = year+"-0"+month+"-"+day;
            }
            else
            {
            	if(month > 9 )
            		date = year+"-"+month+"-"+day;
            	else
            		date = year+"-0"+month+"-0"+day;
            }
            
            if(todayDate.equals(date) ||todayDate.compareTo(date) < 0)
            {
                InputFrame.txtSchedule.setEditable(true);
            	InputFrame.btnInput1.setEnabled(true);
                new InputFrame(date);
                
                calSet();
            }
            else
            {
            	InputFrame.txtSchedule.setEditable(false);
            	InputFrame.btnInput1.setEnabled(false);
            	new InputFrame(date);
            	calSet();
            }
            //��ư�� ��� �� 1,2,3.... ���ڸ� ���������� ��ȯ�Ͽ� Ŭ���� ��¥�� �ٲ��ش�.
    	}
    }//end actionperformed()
    
    public void hideInit()
    {
    	for(int i = 0 ; i < calBtn.length;i++)
    	{
    		if((calBtn[i].getText()).equals(""))
    			calBtn[i].setEnabled(false);
    			//���� ������ ���� ������ ��ư�� ��Ȱ��ȭ ��Ų��. 
    	}//end for
    }//end hideInit()
    
    public void gridInit()
    {
    	//jPanel3�� ��ư ���̱�
    	for(int i = 0 ; i < days.length;i++)
    	{
    		panWest.add(calBtn[i] = new JButton(days[i]));
    		calBtn[i].setContentAreaFilled(false);
    		calBtn[i].setBorderPainted(false);
    	}
    	for(int i = days.length ; i < 49;i++)
    	{
    		panWest.add(calBtn[i] = new JButton(""));
    		calBtn[i].addActionListener(this);
    	}
    }//end gridInit()
    
    public void panelInit()
    {
    	GridLayout gridLayout1 = new GridLayout(7,7);
    	panWest.setLayout(gridLayout1);
    }//end panelInit()
    
    public void calInput(int gap)
    {
    	month+=(gap);
    	
    	if (month<=0)
    	{
    		month = 12;
    		year  =year- 1;
    	}
    	else if (month>=13)
    	{
    		month = 1;
    		year =year+ 1;
    	}
    }//end calInput()
}//end class

public class FrameMain
{
	public static void main(String[] args)
	{
		CalenderDBManager cdb = new CalenderDBManager();
		AccountDBManager adb = new AccountDBManager();
		CategoryDBManager tdb = new CategoryDBManager();
		cdb.scheduledbManager();
		adb.accountdbManager();
		tdb.categorydbManager();
		SwingCalender jdbc = new SwingCalender();
	}
}