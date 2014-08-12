package kr.ac.jbnu.accmgr.ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import kr.ac.jbnu.accmgr.persistent.Manager;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
//import org.jfree.ui.ApplicationFrame;

public class AccountChartFrame extends JFrame implements ActionListener
{
	static String expenseStr[];
	static double num[];
	static Manager m = new Manager();
	public static ArrayList<String> incomeCategory = new ArrayList<String>();
	public static ArrayList<String> expenseCategory = new ArrayList<String>();
	public static ArrayList<Double> expenseNumber = new ArrayList<Double>();
	static String expenseCate;
	static String date = "";
	JButton btnBack;
	
	public AccountChartFrame(String testDate) 
	{
		date = testDate;
		JPanel panel = new JPanel();
		panel.add(createDemoPanel());
		panel.setBounds(0, 0, 600, 400);
		//setContentPane(createDemoPanel());
		setSize(600,500);
		setTitle("Chart");
		btnBack = new JButton("µÚ·Î");
		btnBack.addActionListener(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(panel,"North");
		add(btnBack,"South");
		setVisible(true);
	}
	
	private static PieDataset createDataset() 
	{
		expenseCategory.addAll(m.getExpenseCategory());
	    expenseStr = new String[expenseCategory.size()];
	    num = new double[expenseCategory.size()];
	    num = m.getExpenseNumber(date, expenseCategory);
	    DefaultPieDataset dataset1 = new DefaultPieDataset();
	    
	    for(int i=0; i < expenseCategory.size(); i++)
	    {
	    	if(num[i] != 0.0)
	    	{
	    		expenseStr[i] = expenseCategory.get(i).toString();
	    		dataset1.setValue((Comparable)expenseStr[i],num[i]);
	    	}
	    }
	    return dataset1;        
	}
	
	private static JFreeChart createChart(PieDataset dataset1) 
	{
		JFreeChart chart = ChartFactory.createPieChart("Chart",
	            dataset1,             // data
	            true,               // include legend
	            false,
	            false
	    );
		
		PiePlot plot = (PiePlot) chart.getPlot();
	    plot.setLabelFont(new Font("SansSerif", Font.PLAIN, 12));
	    plot.setNoDataMessage("No data available");
	    plot.setCircular(false);
	    plot.setLabelGap(0.02);
	    return chart;
	}
	
	public static JPanel createDemoPanel() {
		JFreeChart chart = createChart(createDataset());
	    return new ChartPanel(chart);
	}
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource().equals(btnBack))
		{
			setVisible(false);
		}
			
	}
}
