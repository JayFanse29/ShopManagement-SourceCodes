import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.Border;
import javax.swing.text.MaskFormatter;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;

public class AnalysisFrame extends JFrame{
	
	 Color bgColor=new Color(135,206,236);
    
    void create(AnalysisFrame anf)
    {
    	this.setLayout(null);
        this.setSize(1200,700);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getContentPane().setBackground(bgColor);
        this.setResizable(false);
        
        
        
        AnalysisUpPanel up1=new AnalysisUpPanel(anf);
        AnalysisLeftPanel alp=new AnalysisLeftPanel(anf);
        AnalysisDisplayChart adc=new AnalysisDisplayChart(anf);
        
        up1.create();
        alp.create(adc);
        adc.create();
        
       
        
        
        
        
        add(up1.up);
        
        	
        
       	add(alp.left);
        
        this.setVisible(true);
    }
    
 
    	
    
    
 

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AnalysisFrame anf=new AnalysisFrame();
		anf.create(anf);
	}

}

class AnalysisUpPanel implements ActionListener
{
	JPanel up;
	JLabel info;
	JButton help,back;
	Border Blackline = BorderFactory.createLineBorder(Color.black,3);
	Border Grayline = BorderFactory.createLineBorder(Color.gray,3);
    AnalysisFrame anf;
    
	AnalysisUpPanel(AnalysisFrame anf)
	{
		this.anf=anf;
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
		info = new JLabel("Sale-Restock analysis");
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
			anf.dispose();
			}
			catch(Exception e) { }
		}
		if(ae.getSource().equals(help))
		{
			try {
			HelpAnalysisFrame hanf=new HelpAnalysisFrame();
			hanf.create(hanf);
			//anf.dispose();
			}
			catch(Exception e) { }
		}
	}
}

class AnalysisLeftPanel extends JFrame implements ActionListener
{
	JPanel left;
	JLabel select,atype,amethod;
    Font f=new Font("Arial",Font.BOLD,24);
    Font f1=new Font("Arial",Font.BOLD,22);
    Font f2=new Font("Arial",Font.BOLD,20);
    JRadioButton sale,stock,monthly,yearly,overview;
    ButtonGroup type,method;
    JButton ok;
    AnalysisDisplayChart adc;
    
	Border Blackline = BorderFactory.createLineBorder(Color.black,3);
	
	AnalysisFrame anf;
	AnalysisLeftPanel(AnalysisFrame anf)
	{
		this.anf=anf;
	}
	void create(AnalysisDisplayChart adc)
	{
		this.adc=adc;
		left=new JPanel();
        left.setLayout(null);
        left.setBounds(-2,67,302,633);
        left.setBackground(Color.white);
        left.setBorder(Blackline);
        selectionPanel();
        analysisType();
        analysisMethod();
        button();
        buttonGroup();
        
        left.add(select);	left.add(atype);	left.add(sale);	left.add(stock);
        left.add(amethod);	left.add(monthly);	left.add(yearly);	left.add(overview);
        left.add(ok);
	}
	
	void selectionPanel()
	{
		select=new JLabel("SELECTION PANEL");
        select.setFont(f);
        select.setBounds(35,10,260,80);
	}
	void analysisType()
	{
		 atype=new JLabel("=> Type of analysis : ");
	        atype.setFont(f1);
	        atype.setBounds(10,110,280,50);
	        sale=new JRadioButton("Sale analysis");
	        stock=new JRadioButton("Re-Stock analysis");
	        sale.setBounds(10,160,200,50);
	        sale.setFont(f2);
	        sale.setBackground(Color.white);
	        sale.setFocusable(false);
	        stock.setBounds(10,200,200,50);
	        stock.setFont(f2);
	        stock.setBackground(Color.white);
	        stock.setFocusable(false); 
	        type=new ButtonGroup();
	        type.add(sale);
	        type.add(stock);
	}
	void analysisMethod()
	{
		amethod=new JLabel("=> Method of analysis : ");
        amethod.setFont(f1);
        amethod.setBounds(10,290,280,50);
        monthly=new JRadioButton("Monthly analysis");
        yearly=new JRadioButton("Yearly analysis");
        overview=new JRadioButton("Detailed overview");
        monthly.setBounds(10,340,200,50);
        monthly.setFont(f2);
        monthly.setBackground(Color.white);
        monthly.setFocusable(false);
        yearly.setBounds(10,380,200,50);
        yearly.setFont(f2);
        yearly.setBackground(Color.white);
        yearly.setFocusable(false); 
	}
	void buttonGroup()
	{
		 method=new ButtonGroup();
	     method.add(monthly);
	     method.add(yearly);   
	}
	void button()
	{
		ok=new JButton("OK");
        ok.setFont(f2);
        ok.setBounds(100,500,70,40);
        ok.setFocusable(false);
        ok.addActionListener(this);        
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource().equals(ok))
   		{
			adc.mm.removeKeyListener(adc);
			adc.yy.removeKeyListener(adc);
			adc.setNull();
   			
   			if(sale.isSelected())
   			{
   				if(monthly.isSelected())
   				{
   					adc.monthlySaleVisible();
   					adc.mm.requestFocus();
   					adc.mm.addKeyListener(adc);

   				}
   				else if(yearly.isSelected())
   				{
   					adc.yearlySaleVisible();
   					adc.yy.requestFocus();
   					adc.yy.addKeyListener(adc);
   				}
//   				else if(overview.isSelected())
//   				{
//   					
//   				}
   				else
   	   			{
   	   				JOptionPane.showInternalMessageDialog(null, "Please Select required checkboxes!", "Incomplete information", JOptionPane.WARNING_MESSAGE);
   	   			}
   					
   			}
   			else if(stock.isSelected())
   			{
   				
   				if(monthly.isSelected())
   				{
//   					System.out.print("\nhi");
   					adc.monthlyStockVisible();
   					adc.mm.requestFocus();
   					adc.mm.addKeyListener(adc);
   				}
   				else if(yearly.isSelected())
   				{
   					adc.yearlyStockVisible();
   					adc.yy.requestFocus();
   					adc.yy.addKeyListener(adc);
   				}
//   				else if(overview.isSelected())
//   				{
//   					
//   				}
   				else
   	   			{
   	   				JOptionPane.showInternalMessageDialog(null, "Please Select required checkboxes!", "Incomplete information", JOptionPane.WARNING_MESSAGE);
   	   			}
   			}
   			else
   			{
   				JOptionPane.showInternalMessageDialog(null, "Please Select required checkboxes!", "Incomplete information", JOptionPane.WARNING_MESSAGE);
   			}
//   			this.setVisible(true);
   		}
   		
	}
	
}

class AnalysisDisplayChart implements ActionListener, KeyListener{
	
	JButton mstb,mslb,ystb,yslb;
	JPanel crt,sm,sy;
	JLabel dt;
	Font f2=new Font("Arial",Font.BOLD,20);
	Color bgColor=new Color(135,206,236);
	JFormattedTextField mm,yy;
	MaskFormatter mf;
	FileWriter fw;
    FileReader fr;
    BufferedReader br;
    Scanner sc;
    String[] month = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    String title;
    int flag=1,exists=0;
    PieDataset pieset;
    File pro = new File("C:\\ShopManagement\\products");
    File mk = new File("C:\\ShopManagement\\date");
    Date d1=new Date(); 
	
	AnalysisFrame anf;
	AnalysisLeftPanel alp;
	AnalysisDisplayChart(AnalysisFrame anf)
	{
		this.anf=anf;
	}
	
	void create()
	{
		buttons();
		crt=new JPanel();
        monthlyPanel();
        yearlyPanel();
	}
	void buttons()
	{
		mstb=new JButton("Display");
        mstb.setBounds(560,20,90,40);
        mstb.setFont(new Font("Arial",Font.BOLD,16));
        mstb.setFocusable(false);
        mslb=new JButton("Display");
        mslb.setBounds(560,20,90,40);
        mslb.setFont(new Font("Arial",Font.BOLD,16));
        mslb.setFocusable(false);
        ystb=new JButton("Display");
        ystb.setBounds(560,20,90,40);
        ystb.setFont(new Font("Arial",Font.BOLD,16));
        ystb.setFocusable(false);  
        yslb=new JButton("Display");
        yslb.setBounds(560,20,90,40);
        yslb.setFont(new Font("Arial",Font.BOLD,16));
        yslb.setFocusable(false);
        
        mstb.addActionListener(this);
        mslb.addActionListener(this);
        ystb.addActionListener(this);
        yslb.addActionListener(this);
        
	}
	
	void monthlyPanel(){
        try {
        	
        	sm=new JPanel();
        	sm.setLayout(null);
        	sm.setBounds(300,70,900,80);
        	sm.setBackground(bgColor);
        	sm.add(mstb);	sm.add(mslb);
        	 try
             {
             mf = new MaskFormatter("##-####");
             }
             catch(Exception e) { }
             mm=new JFormattedTextField(mf);
             mm.setBounds(450,20,80,40);
             mm.setFont(f2);
//             mm.addKeyListener(this);
             
             dt = new JLabel();
             dt.setText("Enter Month (MM-YYYY) :");
             dt.setBounds(150,20,400,40);
             dt.setFont(new Font("Arial",Font.BOLD,22));
        	 dt.setVisible(true);
        	 
            
           }
        catch (Exception e){
            System.out.print("\nError: "+e.getMessage());
        }
        
        
        sm.add(dt);	sm.add(mm);
        anf.add(sm);
        sm.setVisible(false);
       
    }
    	
    void yearlyPanel()
    {
    	try
    	{
    	sy=new JPanel();
    	sy.setLayout(null);
    	sy.setBounds(300,70,900,80);
    	sy.setBackground(bgColor);
    	sy.add(ystb);	sy.add(yslb);
    	 try
         {
         mf = new MaskFormatter("####");
         }
         catch(Exception e) { }
         yy=new JFormattedTextField(mf);
         yy.setBounds(430,20,80,40);
         yy.setFont(f2);
//         yy.addKeyListener(this);
         
         dt = new JLabel();
         dt.setText("Enter Year (YYYY) :");
         dt.setBounds(190,20,400,40);
         dt.setFont(new Font("Arial",Font.BOLD,22));
    	 dt.setVisible(true);
    	 
    	}
    	catch (Exception e){
        System.out.print("\nError: "+e.getMessage());
    	}
    
    
    sy.add(dt);	sy.add(yy);
    anf.add(sy);
    sy.setVisible(false);
    }
    
    void createChartPanel(PieDataset pieset)
    {

    	crt=new JPanel();
        crt.setBounds(300,148,880,510);
        crt.setBackground(bgColor);
        JFreeChart chart=createChart(pieset);
        chart.setBackgroundPaint(bgColor);
		ChartPanel chartPanel = new ChartPanel(chart);
//		chartPanel.setBackground(bgColor);
		chartPanel.setLayout(null);
		chartPanel.setLocation(0,0);
		chartPanel.setPreferredSize(new Dimension(880,510));
		chartPanel.setBounds(300,140,850,510);
		
		if(flag==1 && exists==1)
		{
//		chartPanel.setBackground(bgColor);
		crt.add(chartPanel);
		anf.add(crt);
		}
	}
    PieDataset monthStockDataset()
   	{
    	exists=0;
    	flag=1;
   		DefaultPieDataset result = new DefaultPieDataset();
   		File d = new File(mk+"");
        String date[] = d.list();

        File p = new File(pro+"");
        String cont[] = p.list();

        int s[] = new int[cont.length];

        FileReader fr;
        BufferedReader br;
        int count = 0;

        try
        {
        for (int i = 0; i < date.length; i++) {
            if (date[i].contains(mm.getText())) {
                File pd = new File(mk+"\\" + date[i]);
                if(pd.exists())
                {
                String proDate[] = pd.list();
                for (int j = 0; j < cont.length; j++) {
                    for (int k = 0; k < proDate.length; k++) {
                        if (cont[j].contentEquals(proDate[k])) {
                            File sl = new File(pd + "\\" + proDate[k] + "\\" + "stock.txt");
                            if(sl.exists())
                            {
                            	
                            exists=1;
                            fr = new FileReader(sl);
                            br = new BufferedReader(fr);
                            int sold = Integer.parseInt(br.readLine());
                            s[j] += sold;
                            }
                        }
                    }
                   
                }
                count++;
                }
            }
        }	
        
        String[] month = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        String str1 = mm.getText().substring(0, 2);
        int mon = Integer.parseInt(str1);

        if(exists==0)
        {
        	JOptionPane.showMessageDialog(null, "Re-stock data of "+month[mon-1]+", "+mm.getText().substring(3,7)+" not found.", "Data not found.", JOptionPane.WARNING_MESSAGE);   
            mm.setText("");
        }
        else
        {
        if (count > 0) {
        	title="Re-Stock in " + month[mon - 1] + ", " + mm.getText().substring(3, 7);
//            System.out.print("\n\nStock of " + month[mon - 1] + ", " + mm.getText().substring(3, 7) + "\n");
//            System.out.print("\nSr. No.     Item            Stock(Units)");
            for (int i = 0; i < cont.length; i++) {
            	 result.setValue(cont[i],s[i]);
//                System.out.printf("\n%3d %13s %16d", (i + 1), cont[i], s[i]);
            }
        }
        else 
        {
        JOptionPane.showMessageDialog(null, "Re-stock data of "+month[mon-1]+", "+mm.getText().substring(3,7)+" not found.", "Data not found.", JOptionPane.WARNING_MESSAGE);   
        mm.setText("");
        flag=0;
        }
        }
        }
        catch(Exception e) { }
   		
   		return result;
   	}
    
    PieDataset monthSaleDataset()
   	{
    	exists=0;
    	flag=1;
   		DefaultPieDataset result = new DefaultPieDataset();
   		File d = new File(mk+"");
        String date[] = d.list();

        File p = new File(pro+"");
        String cont[] = p.list();

        int s[] = new int[cont.length];

        FileReader fr;
        BufferedReader br;
        int count = 0;

        try
        {
        for (int i = 0; i < date.length; i++) {
            if (date[i].contains(mm.getText())) {
                File pd = new File(mk+"\\" + date[i]);
                if(pd.exists())
                {
                String proDate[] = pd.list();
                for (int j = 0; j < cont.length; j++) {
                    for (int k = 0; k < proDate.length; k++) {
                        if (cont[j].contentEquals(proDate[k])) {
                            File sl = new File(pd + "\\" + proDate[k] + "\\" + "sale.txt");
                            if(sl.exists())
                            {
                            	
                            exists=1;
                            fr = new FileReader(sl);
                            br = new BufferedReader(fr);
                            int sold = Integer.parseInt(br.readLine());
                            s[j] += sold;
                            }
                        }
                    }
                   
                }
                count++;
                }
            }
        }	
        
        String[] month = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        String str1 = mm.getText().substring(0, 2);
        int mon = Integer.parseInt(str1);

        if(exists==0)
        {
        	JOptionPane.showMessageDialog(null, "Sale data of "+month[mon-1]+", "+mm.getText().substring(3,7)+" not found.", "Data not found.", JOptionPane.WARNING_MESSAGE);   
            mm.setText("");
        }
        else
        {
        if (count > 0) {
        	title="Sale in " + month[mon - 1] + ", " + mm.getText().substring(3, 7);
//            System.out.print("\n\nStock of " + month[mon - 1] + ", " + mm.getText().substring(3, 7) + "\n");
//            System.out.print("\nSr. No.     Item            Stock(Units)");
            for (int i = 0; i < cont.length; i++) {
            	 result.setValue(cont[i],s[i]);
//                System.out.printf("\n%3d %13s %16d", (i + 1), cont[i], s[i]);
            }
        }
        else 
        {
        JOptionPane.showMessageDialog(null, "Sale data of "+month[mon-1]+", "+mm.getText().substring(3,7)+" not found.", "Data not found.", JOptionPane.WARNING_MESSAGE);   
        mm.setText("");
        flag=0;
        }
        }
        }
        catch(Exception e) { }
   		
   		return result;
   	}
   	
    PieDataset yearStockDataset()
    {
    	exists=0;
    	flag=1;
   		DefaultPieDataset result = new DefaultPieDataset();
   		try
   		{
   		 File d = new File(mk+"");
         String date[] = d.list();

         File p = new File(pro+"");
         String cont[] = p.list();

         int s[] = new int[cont.length];

         FileReader fr;
         BufferedReader br;
         int count = 0;

         for (int i = 0; i < date.length; i++) {
             if (date[i].contains(yy.getText())) {
                 File pd = new File(mk+"\\" + date[i]);
                 if(pd.exists())
                 {
                	 String proDate[] = pd.list();
                 for (int j = 0; j < cont.length; j++) {
                     for (int k = 0; k < proDate.length; k++) {
                         if (cont[j].contentEquals(proDate[k])) {
                             File sl = new File(pd + "\\" + proDate[k] + "\\" + "stock.txt");
                             if(sl.exists())
                             {
                            	 exists=1;
                                 
                             fr = new FileReader(sl);
                             br = new BufferedReader(fr);
                             int sold = Integer.parseInt(br.readLine());
                             s[j] += sold;
                             }
                         }
                     }
                 }
                 count++;
                 }
             }
         }
         String str1 = yy.getText().substring(0, 2);
         int mon = Integer.parseInt(str1);

         if(exists==0)
         {
        	 JOptionPane.showMessageDialog(null, "Re-stock data of year "+yy.getText()+" not found.", "Data not found.", JOptionPane.WARNING_MESSAGE);   
        	 yy.setText("");
         }
         else
         {
         if (count > 0) {
             title="\n\nRe-stock of Year " + yy.getText();
//             System.out.print("\nSr. No.     Item            Stock(Units)");
             for (int i = 0; i < cont.length; i++) {
//                 System.out.printf("\n%3d %13s %16d", (i + 1), cont[i], s[i]);
            	 result.setValue(cont[i], s[i]);
             }
         }
         else 
         {
        	 JOptionPane.showMessageDialog(null, "Re-stock data of year "+yy.getText()+" not found.", "Data not found.", JOptionPane.WARNING_MESSAGE);   
             yy.setText("");
             flag=0;
             
         }
         }
         }
     catch (Exception e){
         System.out.print("\nError: "+e.getMessage());
     }
   		
    	
    	return result;
    }
    
    PieDataset yearSaleDataset()
    {
    	exists=0;
    	flag=1;
   		DefaultPieDataset result = new DefaultPieDataset();
   		try
   		{
   		 File d = new File(mk+"");
         String date[] = d.list();

         File p = new File(pro+"");
         String cont[] = p.list();

         int s[] = new int[cont.length];

         FileReader fr;
         BufferedReader br;
         int count = 0;

         for (int i = 0; i < date.length; i++) {
             if (date[i].contains(yy.getText())) {
                 File pd = new File(mk+"\\" + date[i]);
                 if(pd.exists())
                 {
                	 
                 String proDate[] = pd.list();
                 for (int j = 0; j < cont.length; j++) {
                     for (int k = 0; k < proDate.length; k++) {
                         if (cont[j].contentEquals(proDate[k])) {
                             File sl = new File(pd + "\\" + proDate[k] + "\\" + "sale.txt");
                             if(sl.exists())
                             {
                            	 exists=1;
                             fr = new FileReader(sl);
                             br = new BufferedReader(fr);
                             int sold = Integer.parseInt(br.readLine());
                             s[j] += sold;
                             }
                         }
                     }
                 }
                 count++;
                 }
             }
         }
         String str1 = yy.getText().substring(0, 2);
         int mon = Integer.parseInt(str1);

         if(exists==0)
         {
        	 JOptionPane.showMessageDialog(null, "Sale data of year "+yy.getText()+" not found.", "Data not found.", JOptionPane.WARNING_MESSAGE);   
        	 yy.setText("");
         }
         else
         {
         if (count > 0) {
             title="\n\nSale of Year " + yy.getText();
//             System.out.print("\nSr. No.     Item            Stock(Units)");
             for (int i = 0; i < cont.length; i++) {
//                 System.out.printf("\n%3d %13s %16d", (i + 1), cont[i], s[i]);
            	 result.setValue(cont[i], s[i]);
             }
         }
         else 
         {
        	 JOptionPane.showMessageDialog(null, "Sale data of year "+yy.getText()+" not found.", "Data not found.", JOptionPane.WARNING_MESSAGE);   
             yy.setText("");
             flag=0;
             
         }
         }
         }
     catch (Exception e){
         System.out.print("\nError: "+e.getMessage());
     }
   		
    	
    	return result;
    }
   	JFreeChart createChart(PieDataset pieset)
   	{
   		JFreeChart chart=ChartFactory.createPieChart(title, pieset, true, true, false);
   		PiePlot plot= (PiePlot) chart.getPlot();
   		chart.getPlot().setBackgroundPaint(Color.white);
   		plot.setStartAngle(0);
   		plot.setShadowPaint(null);
   		plot.setDirection(Rotation.CLOCKWISE);
//   		plot.setForegroundAlpha(0.8f);
   		return chart;
   	}
   	
   	void setNull()
   	{
   		mm.setText(null);
		yy.setText(null);	
   	}
 
  	void monthlySaleVisible()
   	{
   		sm.setVisible(true);
   		mslb.setVisible(true);
		mstb.setVisible(false);
		yslb.setVisible(false);
		ystb.setVisible(false);
		sy.setVisible(false);
   	}
   	void yearlySaleVisible()
   	{
		sy.setVisible(true);
		yslb.setVisible(true);
		ystb.setVisible(false);
		mslb.setVisible(false);
		mstb.setVisible(false);
		sm.setVisible(false);
   	}
   	void monthlyStockVisible()
   	{
		sm.setVisible(true);
		mstb.setVisible(true);
		mslb.setVisible(false);
		yslb.setVisible(false);
		ystb.setVisible(false);
		sy.setVisible(false);	
   	}
   	void yearlyStockVisible()
   	{
   		sy.setVisible(true);
		ystb.setVisible(true);
		yslb.setVisible(false);
		mslb.setVisible(false);
		mstb.setVisible(false);
		sm.setVisible(false);	
   	}
   	
    
	@Override
	public void actionPerformed(ActionEvent ae) {
		
		if(ae.getSource().equals(mstb))
   		{			
   			String mm1=mm.getText();
   			boolean check=true;
   			
   			for(int i=0;i<7;i++)
   			{
   				if(mm1.charAt(i)!='-')
   				{
   					if(mm1.charAt(i)<'0' || mm1.charAt(i)>'9')
   					{
   						check=false;
   						break;
   					}
   				}
   			}
   			
   			if(check==false)
   			{
   				JOptionPane.showInternalMessageDialog(null, "Invalid Month! Please try again.", "Invalid Month", JOptionPane.WARNING_MESSAGE);
   				mm.setText("");	
   			}
   			else
   			{
   			check=d1.properMonth(mm1);
   			if(check==false || mm1.equals(""))
   			{
   				JOptionPane.showInternalMessageDialog(null, "Invalid Month! Please try again.", "Invalid Month", JOptionPane.WARNING_MESSAGE);
   				mm.setText("");
   			}
   			else
   			{
   				crt.setVisible(false);
   				pieset = monthStockDataset();
   				createChartPanel(pieset);
//   				crt.setVisible(true);
				anf.validate();

   				sm.setVisible(true);
//   				setVisible(true);
   				System.out.print("\ncc");
   		    }
   			}
   		}
   		if(ae.getSource().equals(mslb))
   		{
   			String mm1=mm.getText();
   			boolean check=true;
   			for(int i=0;i<7;i++)
   			{
   				if(mm1.charAt(i)!='-')
   				{
   					if(mm1.charAt(i)<'0' || mm1.charAt(i)>'9')
   					{
   						check=false;
   						break;
   					}
   				}
   			}
   			
   			if(check==false)
   			{
   				JOptionPane.showInternalMessageDialog(null, "Invalid Month! Please try again.", "Invalid Month", JOptionPane.WARNING_MESSAGE);
   				mm.setText("");   				
   			}
   			else
   			{
   			check=d1.properMonth(mm1);
   			if(check==false || mm1.equals(""))
   			{
   				JOptionPane.showInternalMessageDialog(null, "Invalid Month! Please try again.", "Invalid Month", JOptionPane.WARNING_MESSAGE);
   				mm.setText("");	
   			}
   			else
   			{
   				crt.setVisible(false);
   				pieset = monthSaleDataset();
   				createChartPanel(pieset);
//   				crt.setVisible(true);
				anf.validate();

   				sm.setVisible(true);
//   				setVisible(true);
//   				validate();
   			}
   			}
   		}
   		if(ae.getSource().equals(ystb))
   		{
   			String yy1=yy.getText();
   			boolean check=true;
   			for(int i=0;i<4;i++)
   			{
   				if(yy1.charAt(i)<'0' || yy1.charAt(i)>'9')
   				{
   					check=false;
   					break;
   				}
   			}
   			
   			if(check==false)
   			{
   				JOptionPane.showInternalMessageDialog(null, "Invalid Year! Please try again.", "Invalid Year", JOptionPane.WARNING_MESSAGE);
   				yy.setText("");
   			}
   			else
   			{
   			check=d1.properYear(yy1);
   			if(check==false || yy1.equals(""))
   			{
   				JOptionPane.showInternalMessageDialog(null, "Invalid Year! Please try again.", "Invalid Year", JOptionPane.WARNING_MESSAGE);
   				yy.setText("");
   			}
   			else
   			{
   				crt.setVisible(false);
   				pieset = yearStockDataset();
   				createChartPanel(pieset);
				anf.validate();

//   				crt.setVisible(true);
   				sy.setVisible(true);
//   				setVisible(true);
//   				validate();
   			}
   			}
   		}
   		if(ae.getSource().equals(yslb))
   		{
   			String yy1=yy.getText();
   			boolean check=true;
   			for(int i=0;i<4;i++)
   			{
   				if(yy1.charAt(i)<'0' || yy1.charAt(i)>'9')
   				{
   					check=false;
   					break;
   				}
   			}
   			
   			if(check==false)
   			{
   				JOptionPane.showInternalMessageDialog(null, "Invalid Year! Please try again.", "Invalid Year", JOptionPane.WARNING_MESSAGE);
   				yy.setText("");
   			}
   			else
   			{
   			check=d1.properYear(yy1);
   			if(check==false || yy1.equals(""))
   			{
   				JOptionPane.showInternalMessageDialog(null, "Invalid Year! Please try again.", "Invalid Year", JOptionPane.WARNING_MESSAGE);
   				yy.setText("");
   			}
   			else
   			{
   				crt.setVisible(false);
   				pieset = yearSaleDataset();
   				createChartPanel(pieset);
				anf.validate();

//   				crt.setVisible(true);
   				sy.setVisible(true);
//   				setVisible(true);
//   				validate();
   			}
   			
   			}
   		}
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		char ch=e.getKeyChar();
		if(ch=='\n')
		{
			if(mstb.isVisible())
			{
//				System.out.println("hhh");
				String mm1=mm.getText();
	   			boolean check=true;
	   			
	   			for(int i=0;i<7;i++)
	   			{
	   				if(mm1.charAt(i)!='-')
	   				{
	   					if(mm1.charAt(i)<'0' || mm1.charAt(i)>'9')
	   					{
	   						check=false;
	   						break;
	   					}
	   				}
	   			}
	   			
	   			if(check==false)
	   			{
	   				JOptionPane.showInternalMessageDialog(null, "Invalid Month! Please try again.", "Invalid Month", JOptionPane.WARNING_MESSAGE);
	   				mm.setText("");	
	   			}
	   			else
	   			{
	   			check=d1.properMonth(mm1);
	   			if(check==false || mm1.equals(""))
	   			{
	   				JOptionPane.showInternalMessageDialog(null, "Invalid Month! Please try again.", "Invalid Month", JOptionPane.WARNING_MESSAGE);
	   				mm.setText("");
	   			}
	   			else
	   			{
	   				crt.setVisible(false);
	   				pieset = monthStockDataset();
	   				createChartPanel(pieset);
					anf.validate();

//	   				crt.setVisible(true);
	   				sm.setVisible(true);
//	   				setVisible(true);
//	   				validate();
	   				System.out.print("\ncc");
	   		    }
	   			}
			}
			else if(mslb.isVisible())
			{
				String mm1=mm.getText();
	   			boolean check=true;
	   			for(int i=0;i<7;i++)
	   			{
	   				if(mm1.charAt(i)!='-')
	   				{
	   					if(mm1.charAt(i)<'0' || mm1.charAt(i)>'9')
	   					{
	   						check=false;
	   						break;
	   					}
	   				}
	   			}
	   			
	   			if(check==false)
	   			{
	   				JOptionPane.showInternalMessageDialog(null, "Invalid Month! Please try again.", "Invalid Month", JOptionPane.WARNING_MESSAGE);
	   				mm.setText("");   				
	   			}
	   			else
	   			{
	   			check=d1.properMonth(mm1);
	   			if(check==false || mm1.equals(""))
	   			{
	   				JOptionPane.showInternalMessageDialog(null, "Invalid Month! Please try again.", "Invalid Month", JOptionPane.WARNING_MESSAGE);
	   				mm.setText("");	
	   			}
	   			else
	   			{
	   				crt.setVisible(false);
	   				pieset = monthSaleDataset();
	   				createChartPanel(pieset);
//	   				crt.setVisible(true);
					anf.validate();

	   				sm.setVisible(true);
//	   				setVisible(true);
//	   				validate();
	   			}
	   			}
			}
			else if(ystb.isVisible())
			{
				String yy1=yy.getText();
	   			boolean check=true;
	   			for(int i=0;i<4;i++)
	   			{
	   				if(yy1.charAt(i)<'0' || yy1.charAt(i)>'9')
	   				{
	   					check=false;
	   					break;
	   				}
	   			}
	   			
	   			if(check==false)
	   			{
	   				JOptionPane.showInternalMessageDialog(null, "Invalid Year! Please try again.", "Invalid Year", JOptionPane.WARNING_MESSAGE);
	   				yy.setText("");
	   			}
	   			else
	   			{
	   			check=d1.properYear(yy1);
	   			if(check==false || yy1.equals(""))
	   			{
	   				JOptionPane.showInternalMessageDialog(null, "Invalid Year! Please try again.", "Invalid Year", JOptionPane.WARNING_MESSAGE);
	   				yy.setText("");
	   			}
	   			else
	   			{
	   				crt.setVisible(false);
	   				pieset = yearStockDataset();
	   				createChartPanel(pieset);
//	   				crt.setVisible(true);
					anf.validate();

	   				sy.setVisible(true);
//	   				setVisible(true);
//	   				validate();
	   			}
	   			}
			}
			else if(yslb.isVisible())
			{
				String yy1=yy.getText();
	   			boolean check=true;
	   			for(int i=0;i<4;i++)
	   			{
	   				if(yy1.charAt(i)<'0' || yy1.charAt(i)>'9')
	   				{
	   					check=false;
	   					break;
	   				}
	   			}
	   			
	   			if(check==false)
	   			{
	   				JOptionPane.showInternalMessageDialog(null, "Invalid Year! Please try again.", "Invalid Year", JOptionPane.WARNING_MESSAGE);
	   				yy.setText("");
	   			}
	   			else
	   			{
	   			check=d1.properYear(yy1);
	   			if(check==false || yy1.equals(""))
	   			{
	   				JOptionPane.showInternalMessageDialog(null, "Invalid Year! Please try again.", "Invalid Year", JOptionPane.WARNING_MESSAGE);
	   				yy.setText("");
	   			}
	   			else
	   			{
	   				crt.setVisible(false);
	   				pieset = yearSaleDataset();
	   				createChartPanel(pieset);
//	   				crt.setVisible(true);
					anf.validate();

	   				sy.setVisible(true);
//	   				setVisible(true);
//	   				validate();
	   			}
	   			
	   			}
			}
		}
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
