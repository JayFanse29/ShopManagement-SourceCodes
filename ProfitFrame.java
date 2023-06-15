import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.text.MaskFormatter;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class ProfitFrame extends JFrame{

	Color bgColor=new Color(135,206,236);
    
    void create(ProfitFrame pf)
    {
    	repaint();
    	this.setLayout(null);
        this.setSize(1200,700);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getContentPane().setBackground(bgColor);
        this.setResizable(false);
        
        ProfitUpPanel up1=new ProfitUpPanel(pf);
        ProfitMain pm1=new ProfitMain(pf);
        
        up1.create();
        pm1.create();
        add(pm1.mn);	add(pm1.yr);	add(pm1.mp);	add(pm1.yp);
        add(pm1.year);	add(pm1.dispm);	add(pm1.dispy);
        add(pm1.month);
        
        add(up1.up);
        this.setVisible(true);
    }
    
    
   
    public void paint(Graphics g)
    {
    	super.paint(g);
    	g.drawLine(600, 100, 600, 700);
    }
    
    
   		
    
   
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ProfitFrame pf=new ProfitFrame();
		pf.create(pf);

	}

}

class ProfitUpPanel implements ActionListener
{
	JPanel up;
	JLabel info;
	JButton help,back;
	Border Blackline = BorderFactory.createLineBorder(Color.black,3);
	Border Grayline = BorderFactory.createLineBorder(Color.gray,3);
    ProfitFrame pf;
    
	ProfitUpPanel(ProfitFrame pf)
	{
		this.pf=pf;
	}
	void create()
	{
		up=new JPanel();
		up.setBackground(Color.lightGray);
		up.setPreferredSize(new Dimension(1200,50));
		up.setBounds(-2,0,1202,70);
		up.setLayout(null);
        up.setBorder(Blackline);
        helpButton();
        backButton();
        info();
        up.add(info);
        up.add(help);
        up.add(back);
	}
	void info()
	{
		info = new JLabel("Profit-Loss analysis");
        info.setBounds(480, 23, 1000, 30);
        info.setLayout(null);
        info.setFont(new Font("Arial", Font.BOLD, 28));

	}
	void helpButton()
	{
		help=new JButton("HELP");
        help.setFocusable(false);
        help.setBounds(1070,20,80,30);
        help.setBackground(Color.darkGray);
        help.setBorder(Blackline);
        help.setForeground(Color.white);
        help.addActionListener(this);
        help.addMouseListener(new MouseAdapter()
		{
			public void mouseEntered(MouseEvent me)
			{
				help.setBorder(Grayline);
			}
			public void mouseExited(MouseEvent me)
			{
				help.setBorder(Blackline);
			}
			
		});
	}
	
	void backButton()
	{
		back = new JButton("Go Back");
		back.setFocusable(false);
		back.setBounds(60,20,80,30);
		back.setBackground(Color.DARK_GRAY);
		back.setBorder(Blackline);
		back.setForeground(Color.white);
		back.addActionListener(this);
		
		back.addMouseListener(new MouseAdapter()
		{
			public void mouseEntered(MouseEvent me)
			{
				back.setBorder(Grayline);
			}
			public void mouseExited(MouseEvent me)
			{
				back.setBorder(Blackline);
			}
			
		});
	}
	@Override
	public void actionPerformed(ActionEvent ae) {
		
		if(ae.getSource().equals(back))
		{
			try {
			HomeFrame hf=new HomeFrame();
			hf.create(hf);
			pf.dispose();
			}
			catch(Exception e) { }
		}
		if(ae.getSource().equals(help))
		{
			try {
			HelpProfitFrame hpf=new HelpProfitFrame();
			hpf.create(hpf);
			//pf.dispose();
			}
			catch(Exception e) { }
		}
	}
}

class ProfitMain implements ActionListener
{
	JPanel left,right;
	JLabel mp,yp,mn,yr; 
	JFormattedTextField year,month;
    MaskFormatter mfm,mfy;
    JTextArea mdata,ydata;
    Date d1=new Date();
    JButton dispm,dispy;
    CategoryDataset dataset;
    JFreeChart chart;
    String s3;
    FileWriter fw;
	FileReader fr;
    BufferedReader br;
    Scanner sc;
    File ee=new File("C:\\ShopManagement\\earnings_expense");
    ChartPanel chartPanel;
    
    
	Color bgColor=new Color(135,206,236);
	Font f=new Font("Arial",Font.BOLD,40);
	Font A=new Font("Arial",Font.BOLD,20);
	
	ProfitFrame pf;
    
	ProfitMain(ProfitFrame pf)
	{
		this.pf=pf;
	}
	void create()
	{
		leftPanel();
		rightPanel();
		labels();
		formattedTextFields();
		textArea();
		buttons();
	}
	void leftPanel()
	{
		left=new JPanel();
        left.setBackground(bgColor);
	}
	void rightPanel()
	{
		right=new JPanel();
        right.setBackground(bgColor);
	}
	void labels()
	{
		mp=new JLabel("MONTHLY  ANALYSIS");
        yp=new JLabel("YEARLY  ANALYSIS");
        mp.setBounds(75,85,450,50);
        yp.setBounds(700,85,400,50);
        mp.setFont(f);
        yp.setFont(f);
        
        mn=new JLabel("Set Month (MM-YYYY) : ");
        mn.setFont(A);
        yr=new JLabel("Set Year (YYYY) : ");
        yr.setFont(A);
        mn.setBounds(40,150,300,40);
        yr.setBounds(700,150,200,40);
	}
	void formattedTextFields()
	{
		try
        {
        mfm = new MaskFormatter("##-####");
        mfy = new MaskFormatter("####");
        year=new JFormattedTextField(mfy);
        month=new JFormattedTextField(mfm);
        
        }
        catch(Exception e) { }
        year.setFont(A);
        month.setFont(A);
        year.setBounds(880,150,70,40);
        month.setBounds(290,150,90,40);
        month.addKeyListener(new KeyAdapter()
		{
			public void keyTyped(KeyEvent ke)
			{
				char ch=ke.getKeyChar();
				if(ch=='\n')
				{
				String mm=month.getText();
	   			boolean check=true;
	   			for(int i=0;i<7;i++)
	   			{
	   				if(mm.charAt(i)!='-')
	   				{
	   					if(mm.charAt(i)<'0' || mm.charAt(i)>'9')
	   					{
	   						check=false;
	   						break;
	   					}
	   				}
	   			}
	   			
	   			if(check==false)
	   			{
	   				JOptionPane.showInternalMessageDialog(null, "Invalid Month! Please try again.", "Invalid Month", JOptionPane.WARNING_MESSAGE);
	   				month.setText("");
	   				mdata.setVisible(false);
	   				left.setVisible(false);
	   			}
	   			else
	   			{
	   			check=d1.properMonth(mm);
	   			if(check==false || mm.equals(""))
	   			{
	   				JOptionPane.showInternalMessageDialog(null, "Invalid Month! Please try again.", "Invalid Month", JOptionPane.WARNING_MESSAGE);
	   				month.setText("");
	   				mdata.setVisible(false);
	   				left.setVisible(false);
	   			}
	   			else
	   			{
	   				mdata.setVisible(false);
	   				left.setVisible(false);
	   				monthCalc(mm);
//	   				left.setVisible(true);
	   		    }
	   			
	   			}
				}
				
			}
		});
year.addKeyListener(new KeyAdapter()
		{
			public void keyTyped(KeyEvent ke)
			{
				char ch=ke.getKeyChar();
				if(ch=='\n')
				{
				String yy=year.getText();
	   			boolean check=true;
	   			for(int i=0;i<4;i++)
	   			{
	   				if(yy.charAt(i)<'0' || yy.charAt(i)>'9')
	   				{
	   					check=false;
	   					break;
	   				}
	   			}
	   			
	   			if(check==false)
	   			{
	   				JOptionPane.showInternalMessageDialog(null, "Invalid Year! Please try again.", "Invalid Year", JOptionPane.WARNING_MESSAGE);
	   				year.setText("");
	   				ydata.setVisible(false);
	   				right.setVisible(false);
	   			
	   			}
	   			else
	   			{
	   			check=d1.properYear(yy);
	   			if(check==false || yy.equals(""))
	   			{
	   				JOptionPane.showInternalMessageDialog(null, "Invalid Year! Please try again.", "Invalid Year", JOptionPane.WARNING_MESSAGE);
	   				year.setText("");
	   				ydata.setVisible(false);
	   				right.setVisible(false);
	   			}
	   			else
	   			{
	   				ydata.setVisible(false);
	   				right.setVisible(false);
	   				yearCalc(yy);
//	   				right.setVisible(true);
	   				}
	   			
	   			}
				}

			}
			
		});
	}
	
	void textArea()
	{
		mdata=new JTextArea();
        ydata=new JTextArea();
        mdata.setEditable(false);
        ydata.setEditable(false);
	}
	
	void buttons()
	{
		dispm=new JButton("Display");
        dispm.setFont(A);
        dispm.setFocusable(false);
        dispm.setBounds(410,150,120,40);
        dispm.addActionListener(this);
        
        dispy=new JButton("Display");
        dispy.setFont(A);
        dispy.setFocusable(false);
        dispy.setBounds(980,150,120,40);
        dispy.addActionListener(this);
       
	}
	
	void monthCalc(String mm)
    {
    	
    	 float totEarn=0.0f,totExp=0.0f,profit=0.0f;
    	 try
    	 {
    	 File f=new File(ee+"\\"+mm);
         if(f.exists())
         {
             File earn=new File(f+"\\"+"earnings.txt");
             File exp=new File(f+"\\"+"expense.txt");
             if(earn.exists())
             {
             fr=new FileReader(earn);
             br=new BufferedReader(fr);
             totEarn=Float.parseFloat(br.readLine());
             fr.close();
             }
             if(exp.exists())
             {
                 fr=new FileReader(exp);
                 br=new BufferedReader(fr);
                 totExp=Float.parseFloat(br.readLine());
                 fr.close();
             }
             
             profit=totEarn-totExp;
            
             if(profit<0)
             mdata.setText("  EARNINGS : \n  "+totEarn+" INR\n\n  EXPENSE : \n  "+totExp+" INR\n\n  LOSS FACED : \n  "+(-profit));
             else
           	 mdata.setText("  EARNINGS : \n  "+totEarn+" INR\n\n  EXPENSE : \n  "+totExp+" INR\n\n  PROFIT EARNED : \n  "+profit);
            	 
             mdata.setBounds(375,232,200,200);
             mdata.setFont(A);
             
             pf.add(mdata);
             left=new JPanel();
             left.setLayout(null);
             left.setBounds(0,200,375,600);
             graphGenerator(totEarn,totExp,profit);
             
//             chartPanel.setBackground(bgColor);
             
             left.add(chartPanel);
             pf.add(left);
             left.setBackground(bgColor);
             left.setVisible(false);
             left.setVisible(true);
             left.validate();
             mdata.setVisible(true);
             
           
         }
         else
         {
        	 JOptionPane.showInternalMessageDialog(null, "Data for given month doesn't exists", "Data not present", JOptionPane.WARNING_MESSAGE);
        	 left.setVisible(false);
        	 month.setText("");
        	 mdata.setVisible(false);
         }
         }
    	 catch(Exception e) { }
         
    }
    
    void yearCalc(String yy)
    {
    	int flag=0;
    	float totEarn=0.00f,totExp=0.00f,profit=0.00f;
        File f=new File(ee+"");
        if(f.exists())
        {
        String cont[]=f.list();
        for(int i=0;i<cont.length;i++)
        {
            String dt=cont[i].substring(3,7);
            if(dt.equals(yy))
            {
            	flag=1;
            	try
            	{
                File earn=new File(f+"\\"+cont[i]+"\\earnings.txt");
                File exp=new File(f+"\\"+cont[i]+"\\expense.txt");
                if(earn.exists())
                {
                    fr=new FileReader(earn);
                    br=new BufferedReader(fr);
                    totEarn+=Float.parseFloat(br.readLine());
                    fr.close();
                }
                if(exp.exists())
                {
                    fr=new FileReader(exp);
                    br=new BufferedReader(fr);
                    totExp+=Float.parseFloat(br.readLine());
                    fr.close();
                }
            	}
            	catch(Exception e) { }
            }
        }
        
        if(flag==1)
        {
        profit=totEarn-totExp;
        	if(profit<0)
        		ydata.setText("  EARNINGS : \n  "+totEarn+" INR\n\n  EXPENSE : \n  "+totExp+" INR\n\n  LOSS FACED : \n  "+(-profit));
            else
            	ydata.setText("  EARNINGS : \n  "+totEarn+" INR\n\n  EXPENSE : \n  "+totExp+" INR\n\n  PROFIT EARNED : \n  "+profit);
           	 
            ydata.setBounds(975,232,200,200);
            ydata.setFont(A);
            
            pf.add(ydata);
            right=new JPanel();
            right.setLayout(null);
            right.setBounds(600,200,375,600);
            graphGenerator(totEarn,totExp,profit);
            right.add(chartPanel);
            right.setBackground(bgColor);
            pf.add(right);
            
            right.setVisible(false);
            right.setVisible(true);
            right.validate();
            ydata.setVisible(true);
        }
        pf.repaint();

        }
        
        if(flag==0)
        {
        	JOptionPane.showInternalMessageDialog(null, "Data for given year doesn't exist", "Data not present", JOptionPane.WARNING_MESSAGE);
        	year.setText("");
       	 	ydata.setVisible(false);
       	 	right.setVisible(false);
        
        }
    
    }
    
    void graphGenerator(float totEarn,float totExp,float profit)
    {
    	 
     	 dataset = createDataset(totEarn,totExp,profit);
         chart= createChart(dataset);
         chartPanel=new ChartPanel(chart);
         chartPanel.setBounds(5,25,365,400);
         chartPanel.setBackground(bgColor);
	         
    }
//    
    CategoryDataset createDataset(float totEarn,float totExp,float profit)
    {
    	String s1="Earnings";
    	String s2="Expense";
    	s3="Profit";
    	
    	String c1="";
//    	String c2="Expense";
//    	String c3="Profit";
    	
    	DefaultCategoryDataset dataset=new DefaultCategoryDataset();
    	
    	dataset.addValue(totEarn, s1, c1);
//    	s="Expense";
    	
    	dataset.addValue(totExp, s2, c1);
//    	s="Profit";
    	if(profit<0)
    	{
    		s3="Loss";
    		profit=-profit;
    	}
    	dataset.addValue(profit, s3, c1);
    	
    	return dataset;
    }
    
    JFreeChart createChart(CategoryDataset dataset)
    {
    	JFreeChart chart=ChartFactory.createBarChart("", "", "Amount (INR)", dataset,PlotOrientation.VERTICAL, true, true, false);
//    	JFreeChart chart=ChartFactory.cre
//    	chart.getPlot().setBackground(bgColor);
    	CategoryPlot plot=chart.getCategoryPlot();
    	chart.setBackgroundPaint(bgColor);
    	BarRenderer br=(BarRenderer)plot.getRenderer();
    	
    	GradientPaint g1=new GradientPaint(0.0f,0.0f,Color.blue,0.0f,0.0f,new Color(130,202,250));
    	GradientPaint g2=new GradientPaint(0.0f,0.0f,new Color(255,132,0),0.0f,0.0f,Color.orange);
    	GradientPaint g3;
    	if(s3.equals("Loss"))
    	{
    		g3=new GradientPaint(0.0f,0.0f,new Color(200,20,60),0.0f,0.0f,Color.red);
    	}
    	else
    	{
    		g3=new GradientPaint(0.0f,0.0f,new Color(0,255,0),0.0f,0.0f,new Color(172,255,172));
		}
    	
    	br.setSeriesPaint(0, g1);
    	br.setSeriesPaint(1, g2);
    	br.setSeriesPaint(2, g3);
    	return chart;
    }
	@Override
	public void actionPerformed(ActionEvent ae) {
		
		if(ae.getSource().equals(dispm))
   		{
   			String mm=month.getText();
   			boolean check=true;
   			for(int i=0;i<7;i++)
   			{
   				if(mm.charAt(i)!='-')
   				{
   					if(mm.charAt(i)<'0' || mm.charAt(i)>'9')
   					{
   						check=false;
   						break;
   					}
   				}
   			}
   			
   			if(check==false)
   			{
   				JOptionPane.showInternalMessageDialog(null, "Invalid Month! Please try again.", "Invalid Month", JOptionPane.WARNING_MESSAGE);
   				month.setText("");
   				mdata.setVisible(false);
   				left.setVisible(false);
   			}
   			else
   			{
   			check=d1.properMonth(mm);
   			if(check==false || mm.equals(""))
   			{
   				JOptionPane.showInternalMessageDialog(null, "Invalid Month! Please try again.", "Invalid Month", JOptionPane.WARNING_MESSAGE);
   				month.setText("");
   				mdata.setVisible(false);
   				left.setVisible(false);
   			}
   			else
   			{
   				mdata.setVisible(false);
   				left.setVisible(false);
   				monthCalc(mm);
//   				left.setVisible(true);
   		    }
   			
   			}
   		}
   		
   		if(ae.getSource().equals(dispy))
   		{
   			String yy=year.getText();
   			boolean check=true;
   			for(int i=0;i<4;i++)
   			{
   				if(yy.charAt(i)<'0' || yy.charAt(i)>'9')
   				{
   					check=false;
   					break;
   				}
   			}
   			
   			if(check==false)
   			{
   				JOptionPane.showInternalMessageDialog(null, "Invalid Year! Please try again.", "Invalid Year", JOptionPane.WARNING_MESSAGE);
   				year.setText("");
   				ydata.setVisible(false);
   				right.setVisible(false);
   			
   			}
   			else
   			{
   			check=d1.properYear(yy);
   			if(check==false || yy.equals(""))
   			{
   				JOptionPane.showInternalMessageDialog(null, "Invalid Year! Please try again.", "Invalid Year", JOptionPane.WARNING_MESSAGE);
   				year.setText("");
   				ydata.setVisible(false);
   				right.setVisible(false);
   			}
   			else
   			{
   				ydata.setVisible(false);
   				right.setVisible(false);
   				yearCalc(yy);
//   				right.setVisible(true);
   				}
   			
   			}
   		}
   	}
    
    boolean numCheck1(String str)
    {
        for(int i=0;i<str.length();i++)
        {
            if(str.charAt(i)<'0' || str.charAt(i)>'9')
            {
                return false;
            }
        }
        return true;
    }
	
	
}