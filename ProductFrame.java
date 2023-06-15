import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.text.MaskFormatter;

public class ProductFrame extends JFrame {
	
	JPanel up,tab;
	Font f,f1;
    
    EarningsAndExpense e1=new EarningsAndExpense();
    Color bgColor=new Color(135,206,236);
    Color tabColor=new Color(85,206,255);
    Color tabColor2=new Color(185,235,255);
    Border Blackline = BorderFactory.createLineBorder(Color.black,3);
    Border Grayline = BorderFactory.createLineBorder(Color.gray,3);
    
	void create(ProductFrame pf)
	{
		this.setLayout(null);
        this.setSize(1200,700);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getContentPane().setBackground(bgColor);
        this.setResizable(false);
        
        
       ProductUpPanel up1=new ProductUpPanel(pf);
       ProductMain pm=new ProductMain(pf);
       
       up1.create();
       pm.create();
		
       
        
        
        
        
        
//        Table();
             
        
        
        
        
       add(pm.nm);	add(pm.sp);	add(pm.cp);	add(pm.st);	add(pm.t1);	add(pm.t2);	add(pm.t3);	add(pm.t4);
        add(pm.add);	add(pm.rem);	add(pm.upd);	add(pm.clr);	add(pm.date);	add(pm.dt);
        add(pm.js);
//        up.add(help);
//        up.add(back);
        add(up1.up);
     
//        validate();
        this.setVisible(true);
	}
	
	
		
		
	
	
		
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ProductFrame pf=new ProductFrame();
		pf.create(pf);

	}

	
	

}

class ProductUpPanel implements ActionListener
{
	JPanel up;
	JButton help,back;
	Border Blackline = BorderFactory.createLineBorder(Color.black,3);
	Border Grayline = BorderFactory.createLineBorder(Color.gray,3);
    ProductFrame pf;
    JLabel info;
    
	ProductUpPanel(ProductFrame pf)
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
		info = new JLabel("Products Window");
        info.setBounds(480, 23, 1000, 24);
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
			HelpProductFrame hpf=new HelpProductFrame();
			hpf.create(hpf);
			//pf.dispose();
			}
			catch(Exception e) { }
		}

	}
}

class ProductMain  implements ActionListener,MouseListener{
	
	int rowIndex;
	JLabel nm,cp,sp,st,dt;
	JTextField t1,t2,t3,t4;
	JButton add,rem,upd,clr;
	MaskFormatter mf;
	JFormattedTextField date;
	File pro = new File("C:\\ShopManagement\\products");
	FileWriter fw;
    FileReader fr;
    BufferedReader br;
    Scanner sc;
    File mk = new File("C:\\ShopManagement\\date");
    File acc=new File("C:\\ShopManagement\\Accounts");
	File exp = new File("C:\\ShopManagement\\earnings_expense");

    JTable listItem;
    DefaultTableModel tm;
    String product[];
	String cop="",sop="",sto="";
	String heading[]= {"Product","Cost Price","Selling Price","Stock (units)"};
    String data[][];
    JScrollPane js;
    Account a1=new Account();
    
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    LocalDateTime now = LocalDateTime.now();
    String currDt=""; 
    String dateInput="";
    
    Font f=new Font("Arial",Font.PLAIN,30);
	Font f1=new Font("Arial",Font.BOLD,20);
	
	ProductFrame pf;
	ProductMain(ProductFrame pf)
	{
		this.pf=pf;
	}
	void create()
	{
		labels();
		textFields();
		buttons();
		currDate();
		dateField();
		Table();
		
	}
	void currDate()
	{
//		System.out.println(dtf.format(now));
		currDt=dtf.format(now);
		dateInput = dateInput + currDt.substring(0,2) + currDt.substring(3,5) + currDt.substring(6,10);
//		System.out.println(dateInput);
		
	}

	void labels()
	{
		nm=new JLabel("Product name : ");
        cp=new JLabel("Cost price : ");
        sp=new JLabel("Selling price : ");
        st=new JLabel("Stock (units) : ");
        
        nm.setBounds(75,200,200,50);
        nm.setFont(f1);
        cp.setBounds(75,300,200,50);
        cp.setFont(f1);
        sp.setBounds(500,200,200,50);
        sp.setFont(f1);
        st.setBounds(500,300,200,50);
        st.setFont(f1);  
	}
	void textFields()
	{
		 t1=new JTextField();
	     t2=new JTextField();
	     t3=new JTextField();
	     t4=new JTextField();
	       
	     t1.setBounds(250,200,200,50);
	     t1.setFont(f1);
	     t2.setBounds(250,300,200,50);
	     t2.setFont(f1);
	     t3.setBounds(675,200,200,50);
	     t3.setFont(f1);
	     t4.setBounds(675,300,200,50);
	     t4.setFont(f1);
	}
	
	void buttons()
	{
		add=new JButton("ADD");
        rem=new JButton("REMOVE");
        upd=new JButton("UPDATE");
        clr=new JButton("CLEAR");
        rem.setEnabled(false);
        upd.setEnabled(false);
        
        add.setBounds(975,100,150,50);
        add.setFont(f1);
        add.setFocusable(false);
        add.addActionListener(this);
        upd.setBounds(975,175,150,50);
        upd.setFont(f1);
        upd.setFocusable(false);
        upd.addActionListener(this);
        rem.setBounds(975,250,150,50);
        rem.setFont(f1);
        rem.setFocusable(false);
        rem.addActionListener(this);
        clr.setBounds(975,325,150,50);
        clr.setFont(f1);
        clr.setFocusable(false);
        clr.addActionListener(this);
        }
	
	void dateField()
	{
		try
        {
        mf = new MaskFormatter("##-##-####");
        }
        catch(Exception e) { }
	
		date=new JFormattedTextField(mf);
		date.setText(dateInput);
        dt=new JLabel();
        dt.setText("Date (DD-MM-YYYY) : ");
        dt.setBounds(350,100,240,30);
        dt.setFont(f1);
        date.setBounds(580,100,110,40);
        date.setFont(f1);
	}
	boolean numCheck2(String str)
	{
		int flag=0;
		for(int i=0;i<str.length();i++)
		{
			if((str.charAt(i)<'0' || str.charAt(i)>'9') && str.charAt(i)!='.')
			{
				return false;
			}
			if(str.charAt(i)=='.')
			{
				flag++;
			}
			if(flag>1)
				return false;
		}
		return true;
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
	
	void Table()
	{
		String product[]=pro.list();
		int len=product.length;
		 data = new String[len][4];
			
			for(int i=0;i<len;i++)
			{
				File cp=new File(pro+"\\"+product[i]+"\\Cost_price.txt");
				File sp=new File(pro+"\\"+product[i]+"\\Selling_price.txt");
				File st=new File(pro+"\\"+product[i]+"\\stock.txt");
				
			try
			{
				fr=new FileReader(cp);
				br=new BufferedReader(fr);
				cop=br.readLine();
				fr.close();
				
				fr=new FileReader(sp);
				br=new BufferedReader(fr);
				sop=br.readLine();
				fr.close();
				
				fr=new FileReader(st);
				br=new BufferedReader(fr);
				sto=br.readLine();
				fr.close();
			}
			catch(Exception e) {
				System.out.print("\nError : "+ e.getMessage());
			}
				data[i][0]=product[i];
				data[i][1]=cop;
				data[i][2]=sop;
				data[i][3]=sto;				
			}
			
			tm=new DefaultTableModel(data,heading);
	        listItem=new JTable(tm);
	        JTableHeader header=listItem.getTableHeader();
	        header.setFont(f);
	        listItem.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
	        listItem.setRowHeight(40);
	       
	        js=new JScrollPane(listItem,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	        js.setBounds(0,400,1190,260);
	        js.setVisible(true);
	        listItem.setFont(f);
	        js.setViewportView(listItem);
	        listItem.validate();
		    js.validate();
//	        add(js);
	        listItem.addMouseListener(this);
//	        validate();			
	}
	
	void deleteOldDate()
	{
		String[] dates = mk.list();
		int Year=0;
		int DtLen = dates[0].length();
		int currYear = Integer.parseInt(currDt.substring(6,10));
		
		for(int i=0;i<dates.length;i++)
		{
			Year = Integer.parseInt(dates[i].substring(DtLen-4,DtLen));
			if(currYear - Year > 3)
			{
            	File f = new File("C:\\ShopManagement\\date\\" + dates[i]);
				deleteDirectory(f);
				f.delete();
//				System.out.println("deleted");
			}
		}
	}
	
	void deleteOldExp()
	{
		String[] dates = mk.list();
		int Year=0;
		int DtLen = dates[0].length();
		int currYear = Integer.parseInt(currDt.substring(6,10));
		
		for(int i=0;i<dates.length;i++)
		{
			Year = Integer.parseInt(dates[i].substring(DtLen-4,DtLen));
			if(currYear - Year > 3)
			{
            	File f = new File("C:\\ShopManagement\\earnings_expense\\" + dates[i]);
				deleteDirectory(f);
		        f.delete();

//				System.out.println("deleted");
			}
		}
	}

	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		try
		{
		upd.setEnabled(true);
		rem.setEnabled(true);
		
		rowIndex=listItem.getSelectedRow();
		t1.setText(""+tm.getValueAt(rowIndex, 0));
		t2.setText(""+tm.getValueAt(rowIndex, 1));
		t3.setText(""+tm.getValueAt(rowIndex, 2));
		t4.setText(""+tm.getValueAt(rowIndex, 3));
	    t4.setEditable(false);
		}
		catch(Exception ee) { System.out.print("\nError : "+ee.getMessage());}
	
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	void deleteDirectory(File f)
    {
        for(File subfile : f.listFiles())
        {
            if(subfile.isDirectory())
            {
                deleteDirectory(subfile);
            }
            subfile.delete();
        }
    }
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource().equals(add))
		{
			boolean n=true;
			String aDate=date.getText();
			
   			for(int i=0;i<10;i++)
   			{
   				if(aDate.charAt(i)!='-')
   				{
   					if(aDate.charAt(i)<'0' || aDate.charAt(i)>'9')
   					{
   						n=false;
   						break;
   					}
   				}
   			}
   			
   			if(n==false)
   			{
   				JOptionPane.showInternalMessageDialog(null, "Invalid Date! Please try again.", "Invalid Date", JOptionPane.WARNING_MESSAGE);
   				date.setText("");
   			
   			}
   			else
   			{
   				n = Date.properDate(aDate);

   				if(n==false)
   				{
   					date.setText("");
   					JOptionPane.showMessageDialog(null, "Enter proper Date.", "Invalid Date", JOptionPane.WARNING_MESSAGE);
   				}
   				else
   				{
   				boolean check1,check2,check3;
   				check1=numCheck2(t2.getText());
   				check2=numCheck2(t3.getText());
   				check3=numCheck1(t4.getText());
   				if(t1.getText().equals("")||t2.getText().equals("")||t3.getText().equals("")||t4.getText().equals("")||date.getText().equals(""))
   				{
   					JOptionPane.showMessageDialog(null, "Please fill all the details.", "Incomplete information", JOptionPane.WARNING_MESSAGE);
   				}
   				else if(check1==false||check2==false||check3==false)
   				{
   					JOptionPane.showMessageDialog(null, "Enter valid input", "Invalid input", JOptionPane.WARNING_MESSAGE);
   				}
   				else
   				{
   				
   				
   					String prdct=t1.getText();
   					String costP=t2.getText();
   					String sellP=t3.getText();
   					String stock=t4.getText();
   					String dd=date.getText();
				
   					File make = new File(pro+"\\"+prdct);
   					if(make.exists())
   					{
   						JOptionPane.showMessageDialog(null, "Product already exists", "Product exists", JOptionPane.WARNING_MESSAGE);
   					}
   					else
   					{
   						boolean ans=false;
   						try
   						{
   							make.mkdir();
   							File cp = new File(pro+"\\"+prdct+"\\Cost_price.txt");
   							File sp = new File(pro+"\\"+prdct+"\\Selling_price.txt");
   							File st = new File(pro+"\\"+prdct+"\\stock.txt");
						
   							fw=new FileWriter(cp);
   							fw.write(costP);
   							fw.close();
   							
   							fw=new FileWriter(sp);
   							fw.write(sellP);
   							fw.close();
			    
   							fw=new FileWriter(st);
   							fw.write("0");
   							fw.close();
   							ans=a1.stock(prdct,Integer.parseInt(stock),dd.substring(3,10));
   						}
   						catch(Exception e)
   						{
   							System.out.print("\nError : "+e.getMessage());
   						}
   						if(ans==true)
   						{
						mk.mkdir();
						File d1=new File(mk+"\\"+dd);
						d1.mkdir();
						File d=new File(mk+"\\"+dd+"\\"+prdct);
						d.mkdir();
						
						//to delete file from date folder older than 3 years
						deleteOldDate();
						deleteOldExp();
						
						File st = new File(pro+"\\"+prdct+"\\stock.txt");
						File open=new File(d+"\\stock.txt");
							try
							{
							fw=new FileWriter(st);
							fw.write(stock);
							fw.close();
							fw=new FileWriter(open);
							fw.write(stock);
							fw.close();
							}
							catch(Exception e) { 
							System.out.print("\nError : "+e.getMessage());
							}
//			    js.setVisible(false);
//			    this.Table();
							exp.mkdir();
							String m2 = aDate.substring(3, 10);
							System.out.println(m2);
							try {
								File F4 = new File(exp + "\\" + m2);
								F4.mkdir();
								File F5 = new File(exp + "\\" + m2 + "\\expense.txt");
								
								fw = new FileWriter(F5);
								float total = Float.parseFloat(stock)*Float.parseFloat(costP);
								fw.write("" + total);
								fw.close();
							} catch (Exception e4) {
								System.out.println(e4.getMessage());
							}
			  	
						JOptionPane.showMessageDialog(null, "Product added successfully!","Data updated", JOptionPane.INFORMATION_MESSAGE);
   						}
   						else
   						{
						deleteDirectory(make);
						make.delete();
   						}
   						t1.setText("");
   						t2.setText("");
   						t3.setText("");
   						t4.setText("");
//			    js.setVisible(false);
   					
   					}
   				}
   				}
   			}
			
		    ProductFrame pf1=new ProductFrame();
		    pf1.create(pf1);
		    pf.dispose();
   			}
   		
		if(ae.getSource().equals(upd))
		{
			boolean check1,check2,check3;
			check1=numCheck2(t2.getText());
			check2=numCheck2(t3.getText());
			check3=numCheck1(t4.getText());
			if(t1.getText().equals("")||t2.getText().equals("")||t3.getText().equals("")||t4.getText().equals(""))
			{
				JOptionPane.showMessageDialog(null, "Please fill all the details.", "Incomplete information", JOptionPane.WARNING_MESSAGE);
			}
			else if(check1==false||check2==false||check3==false)
			{
				JOptionPane.showMessageDialog(null, "Enter valid input", "Invalid input", JOptionPane.WARNING_MESSAGE);
			}
			else
			{
			String prdct=t1.getText();
			String costP=t2.getText();
			String sellP=t3.getText();
			String stock=t4.getText();
			
			File cp = new File(pro+"\\"+prdct+"\\Cost_price.txt");
		    File sp = new File(pro+"\\"+prdct+"\\Selling_price.txt");
		    File st = new File(pro+"\\"+prdct+"\\stock.txt");
		    try
		    {
		    fw=new FileWriter(cp);
		    fw.write(costP);
		    fw.close();
		    
		    fw=new FileWriter(sp);
		    fw.write(sellP);
		    fw.close();
		    
		    fw=new FileWriter(st);
		    fw.write(stock);
		    fw.close();
		    }
		    catch(Exception e) { }
			
//		    js.setVisible(false);
//		    listItem.setVisible(false);
//		    listItem.removeMouseListener(this);
		    
		    this.Table();
//		    revalidate();
		    JOptionPane.showMessageDialog(null, "Data updated successfully!","Data updated", JOptionPane.INFORMATION_MESSAGE);
		    t1.setText("");
		    t2.setText("");
		    t3.setText("");
		    t4.setText("");
			}
		   
			ProductFrame pf1=new ProductFrame();
		    pf1.create(pf1);
		    pf.dispose();
   			
	
		}
		
		
		if(ae.getSource().equals(rem))
		{
			String prdct=t1.getText();
			String product[]=pro.list();
			File del=new File(pro+"\\"+product[rowIndex]);
			deleteDirectory(del);
		    del.delete();
		   
		    
		    Table();
		   
		    JOptionPane.showMessageDialog(null, "Product removed successfully!","Data updated", JOptionPane.INFORMATION_MESSAGE);
		    js.setVisible(false);
		    t1.setText("");
		    t2.setText("");
		    t3.setText("");
		    t4.setText("");
		    ProductFrame pf1=new ProductFrame();
		    pf1.create(pf1);
		    pf.dispose();
   		}
		if(ae.getSource().equals(clr))
		{
			t1.setText("");
			t2.setText("");
			t3.setText("");
			t4.setText("");
			ProductFrame pf1=new ProductFrame();
		    pf1.create(pf1);
		    pf.dispose();
   			
		}
		Table();	
	}	
}

