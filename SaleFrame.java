import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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


class SaleFrame extends JFrame{

	JPanel up;
	//    DefaultTableCellRenderer tcr;
    
    Color bgColor=new Color(135,206,236);
    
    
    void create(SaleFrame sf){
    	this.setLayout(null);
        this.setSize(1200,700);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(bgColor);
        this.setResizable(false);
        
        SaleMain sm=new SaleMain(sf);
        SaleUpPanel up1=new SaleUpPanel(sf);
        
        up1.create();
        sm.create();



    
        add(up1.up); 
        
        add(sm.dt); 
        add(sm.date); 
//        add(sm.product); 
//        add(sm.saleNo); 
//        add(sm.addSale); 
//        add(sm.psale); 
//        add(sm.pname);
//        add(pic1);
        
        		add(sm.un);
                add(sm.js);
                add(sm.js1);
                add(sm.lis);
                add(sm.product);
                add(sm.uni);
                add(sm.colon);
                add(sm.J1);
                add(sm.purchase);
                add(sm.price);
                add(sm.addList);
                add(sm.clr);
                add(sm.conf);
                add(sm.billsbt);

//        validate();
        this.setVisible(true);
//        sm.addSale.setEnabled(false);


    }




    public static void main(String[] args) throws IOException {
		SaleFrame sf=new SaleFrame();
		sf.create(sf);
	}

    
   
}

class SaleUpPanel implements ActionListener
{
	JPanel up;
	JLabel info;
	JButton help,back;
	Border Blackline = BorderFactory.createLineBorder(Color.black,3);
	Border Grayline = BorderFactory.createLineBorder(Color.gray,3);
    SaleFrame sf;
    
	SaleUpPanel(SaleFrame sf)
	{
		this.sf=sf;
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
		info = new JLabel("Sales Window");
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
			sf.dispose();
			}
			catch(Exception e) { }
		}
		if(ae.getSource().equals(help))
		{
			try {
			HelpSaleFrame hsf=new HelpSaleFrame();
			hsf.create(hsf);
			//sf.dispose();
			}
			catch(Exception e) { }
		}
	}
}

class SaleMain implements KeyListener, ActionListener {
	
	JLabel dt,product,lis,un,colon,uni,purchase,price;
	Color bgColor=new Color(135,206,236);
	JButton addSale,addList,conf,clr,billsbt;
    JTextField J1;
    Font A=new Font("Arial",Font.BOLD,20),f=new Font("Arial",Font.PLAIN,30);
    JFormattedTextField date;
    MaskFormatter mf;
    JScrollPane js,js1;
    JTable prodList, listTable;
    FileReader fr;
    FileWriter fw;
    BufferedReader br;
    File pro = new File("C:\\ShopManagement\\Products");
    File mk = new File("C:\\ShopManagement\\date");
    File acc = new File("C:\\ShopManagement\\Accounts");

    File exp=new File("C:\\ShopManagement\\earnings_expense");
    File bills = new File("C:\\ShopManagement\\Bills\\");
    String []columnHead = {"Product Name","Selling Price","Stock"};
    DefaultTableModel model, modelList;
	String[] ac = acc.list();

	File acc1 = new File("C:\\ShopManagement\\shopname.txt");

	String shopn;

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    LocalDateTime now = LocalDateTime.now();
    String currDt=""; 
    String dateInput="";
    
    int listCount=0;
    float total=0;
    
    SaleFrame sf;
    
    
    
	SaleMain(SaleFrame sf)
	{
		this.sf=sf;
	}
	
	void currDate()
	{
//		System.out.println(dtf.format(now));
		currDt=dtf.format(now);
		dateInput = dateInput + currDt.substring(0,2) + currDt.substring(3,5) + currDt.substring(6,10);
//		System.out.println(dateInput);
		
	}
	void create()
	{
		labels();
		textField();
		button();
		currDate();
		date();
		TableGenerator();
		listTable();
		listHeading();
		emptyList();
		deleteOldDate();
		deleteOldExp();
		deleteOldBill();
	}
	
	
	
	void labels()
	{
		product = new JLabel();
		product.setText("<Select product>");
		product.setFont(new Font("Arial", Font.BOLD, 20));
		product.setBounds(690, 190, 170, 40);
		product.setBackground(Color.white);
		product.setOpaque(true);

		colon=new JLabel();
		colon.setText(":");
		colon.setFont(new Font("Arial", Font.BOLD, 20));
		colon.setBounds(875, 190, 10, 40);
		
		uni = new JLabel("Units");
		uni.setBounds(1005, 200, 100, 20);
		uni.setFont(new Font("Arial", Font.BOLD, 20));

		purchase = new JLabel();
		purchase.setText("Total amount :");
		purchase.setFont(new Font("Arial", Font.BOLD, 24));
		purchase.setBounds(220, 350, 190, 40);

		price = new JLabel();
		price.setOpaque(true);
		price.setBackground(Color.WHITE);
		price.setBounds(390, 350, 170, 40);
		price.setFont(new Font("Arial", Font.BOLD, 24));

	}
	
	void textField()
	{
		J1 = new JTextField();
		J1.setBounds(895, 190, 100, 40);
		J1.setFont(new Font("Arial", Font.BOLD, 18));
		J1.addKeyListener(this);
	}
	
	void button()
	{
		addList = new JButton("Add to List");
		addList.setBounds(700, 265, 160, 50);
		addList.setFont(new Font("Arial", Font.PLAIN, 20));
		addList.setFocusable(false);
		addList.setEnabled(false);
		addList.addActionListener(this);
		
		clr = new JButton("Clear");
		clr.setBounds(895, 265, 160, 50);
		clr.setFont(new Font("Arial", Font.PLAIN, 20));
		clr.setFocusable(false);
		clr.setEnabled(false);
		clr.addActionListener(this);

		conf = new JButton("Generate Bill");
		conf.setBounds(700, 335, 160, 45);
		conf.setFont(new Font("Arial", Font.PLAIN, 20));
		conf.setFocusable(false);
		conf.addActionListener(this);
		conf.setEnabled(false);
		
		billsbt = new JButton("View Bills");
		billsbt.setBounds(895, 335, 160, 45);
		billsbt.setFont(new Font("Arial", Font.PLAIN, 20));
		billsbt.setFocusable(false);
		billsbt.addActionListener(this);

	}
	
	void date()
	{
		try {
			mf = new MaskFormatter("##-##-####");
		} 
		catch (Exception e) { }
		date = new JFormattedTextField(mf);
		date.setBounds(935, 95, 110, 40);
		date.setFont(A);
		date.setText(dateInput);
		
		dt = new JLabel();
		dt.setText("Date (DD-MM-YYYY) :");
		dt.setBounds(690, 100, 230, 30);
		dt.setFont(new Font("Arial", Font.BOLD, 22));
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
//				System.out.println("deleted");
			}
		}
	}
	
	void deleteOldExp()
	{
		String[] dates = exp.list();
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
	
	void deleteOldBill ()
	{
		String[] dates = bills.list();
		int Year=0;
		int DtLen = 0;
		if (dates != null) {
				DtLen = dates[0].length();
			int currYear = Integer.parseInt(currDt.substring(6,10));

			for(int i=0;i<dates.length;i++)
			{
				Year = Integer.parseInt(dates[i].substring(DtLen-4,DtLen));
				if(currYear - Year > 1)
				{
					File f = new File("C:\\ShopManagement\\Bills\\" + dates[i]);
					deleteDirectory(f);
					f.delete();

		//				System.out.println("deleted");
				}
			}
		}
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
        f.delete();
    }

	void listTable(){

		String[][] names = new String[0][3];
		String[] columnHead = {"Product","Units","Cost"};
		modelList = new DefaultTableModel(names,columnHead);
		listTable = new JTable(modelList);

		JTableHeader header=listTable.getTableHeader();
		header.setFont(f);
		listTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		listTable.setRowHeight(40);

		js1=new JScrollPane(listTable,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		js1.setBounds(10,110,550,230);
		js1.setVisible(true);
		listTable.setFont(f);
		js1.setViewportView(listTable);
		listTable.validate();
		js1.validate();
//		manual.add(js);
		}
	
	void listHeading()
	{
		lis = new JLabel();
		lis.setText("Customer's purchase : ");
		lis.setBounds(10, 80, 400, 20);
		lis.setFont(A);
	}
	
	void emptyList()
	{
		un = new JLabel();
		un.setText("Empty list");
		un.setBounds(220, 200, 200, 40);
		un.setFont(new Font("Arial", Font.BOLD, 25));
	}
	
	
		

	
	void TableGenerator(){
		
        String[] List = pro.list();
        int ll = List.length;
        String[][] names = new String[ll][3];
try
{
        for(int i = 0; i < ll; i++)
        {
            File product = new File(pro+"\\"+List[i]+"\\stock.txt");
            fr = new FileReader(product);
            br = new BufferedReader(fr);
            String stk = br.readLine();
            br.close();

            product = new File(pro+"\\"+List[i]+"\\Selling_price.txt");
            fr = new FileReader(product);
            br = new BufferedReader(fr);
            String sp = br.readLine();
            br.close();

            names[i][0] = List[i];
            names[i][1] = sp;
            names[i][2] = stk;
        }
}
catch(Exception e) { }
        model = new DefaultTableModel(names,columnHead);
        prodList=new JTable(model);

        JTableHeader header=prodList.getTableHeader();
        header.setFont(f);
        prodList.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        prodList.setRowHeight(40);


        js=new JScrollPane(prodList,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        js.setBounds(0,400,1190,260);
        js.setVisible(true);
        prodList.setFont(f);
        js.setViewportView(prodList);
        prodList.validate();
        js.validate();
        
        prodList.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e)
            {
            	int index = prodList.getSelectedRow();
				if(index != -1)
				{
					addList.setEnabled(true);
				}
				String x = prodList.getValueAt(index, 0).toString();
				product.setText(x);
				product.setFont(new Font("Arial",Font.BOLD,22));
				
				J1.requestFocusInWindow();
            }
        });

        js.validate();
	}
	
	int printBill(String aDate,float totalAmtFloat) throws IOException {
		int count=0;
		bills.mkdir();
    	File billDt = new File("C:\\ShopManagement\\Bills\\" + aDate);
    	billDt.mkdir();

		fr = new FileReader(acc1);
		br = new BufferedReader(fr);
		shopn = br.readLine();

    	String[] billList = billDt.list();
    	count=billList.length;
    	File newBill = new File("C:\\ShopManagement\\Bills\\" + aDate + "\\" + (count+1) + ".txt");
    	
    	String doubleEquals = " =================================================";
    	String shopName = "	               "+shopn;
    	String minus = " -----------------------------------------------------------------------------------------";
    	String header = " No.         Products	                    Quantity             Cost ";
    	String totAmountLabel = "		                       Total amount : ";
    	String billDtFormat = "" + aDate.substring(0,2) +"/"+ aDate.substring(3,5) +"/"+ aDate.substring(6,10); 
    	String product="",quantity="",cost="";
    	String finalBillAmount = ""+totalAmtFloat;
    	
    	String completeBill = "" + doubleEquals +"\n"+ shopName +"\n"+ doubleEquals +"\n"+
    						" Bill No : " +(count+1)+" 	          		  Date : "+(billDtFormat) +"\n"+
    						minus +"\n"+ header +"\n"+ minus +"\n";
    	
    	int r = modelList.getRowCount();
		for (int k = 0; k < r; k++) {
			
			product = modelList.getValueAt(k, 0).toString();
			quantity = modelList.getValueAt(k, 1).toString();
			cost = modelList.getValueAt(k, 2).toString();
			
//			completeBill = completeBill + (k+1) + product +"\t"+ quantity +"\t"+ cost +"\n";
			
			completeBill = completeBill + String.format("  %-12s %-35s\t%-21s %s\n", k+1, product, quantity, cost);
		}
		
		completeBill = completeBill + minus +"\n"+ totAmountLabel + finalBillAmount +"\n"+ doubleEquals;
    	
		System.out.println(count+1);
		try {
    	fw=new FileWriter(newBill);
    	fw.write(completeBill);
    	fw.close();   	
    	}
    	catch(Exception e)
    	{
            JOptionPane.showMessageDialog(null,e.getMessage(),"Unexpected error",JOptionPane.WARNING_MESSAGE);
    	}
    	
    	return count+1;
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

	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource().equals(addList))
        {
			conf.setEnabled(true);
			clr.setEnabled(true);
			un.setVisible(false);
            String aDate=date.getText().toString();
            if(aDate.equals("") || J1.getText().equals(""))
            {

                JOptionPane.showMessageDialog(null,"Please enter all required details.","Invalid Data",JOptionPane.WARNING_MESSAGE);

            }
            else
            {
                boolean alfa = numCheck1(J1.getText());
                boolean n =true;
                
                for(int i=0;i<7;i++)
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
                if(alfa==false)
                {
                    J1.setText("");
                    JOptionPane.showMessageDialog(null, "Enter proper Sale details.", "Invalid Sale", JOptionPane.WARNING_MESSAGE);
                }

                if(n==true && alfa==true)
                {
    				int ind = prodList.getSelectedRow();

                	String p1 = product.getText();
					int u1 = Integer.parseInt(J1.getText());
					float c1 = u1 * Float.parseFloat(prodList.getValueAt(ind, 1).toString());
					Object[] newRow = {p1, u1, c1};
					modelList.addRow(newRow);
					total += c1;
					price.setText("" + total);
					product.setFont(new Font("Arial", Font.BOLD, 20));
					product.setText("<Select product>");
					J1.setText("");
					addList.setEnabled(false);
					listCount++;
                }
       			}
            }
        }
		if(ae.getSource().equals(clr))
		{
			while(listCount>0)
			{
				modelList.removeRow(listCount-1);
				listCount--;
			}
			clr.setEnabled(false);
			un.setVisible(true);
		}
		
		if(ae.getSource().equals(conf))
		{
			File b1 = new File(acc + "\\" + ac[0] + "\\balance.txt");
			float bal;
			try {
				fr = new FileReader(b1);
				br = new BufferedReader(fr);
				bal = Float.parseFloat(br.readLine());
				br.close();
			} catch (FileNotFoundException e2) {
				throw new RuntimeException(e2);
			} catch (IOException e1) {
				throw new RuntimeException(e1);
			}

			
				String aDate=date.getText();
				if(aDate.equals(""))
				{

					JOptionPane.showMessageDialog(null,"Please enter Date.","Invalid Data",JOptionPane.WARNING_MESSAGE);

				}
				else
				{
					boolean n =true;

					for(int i=0;i<7;i++)
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

					if(n == false)
					{
						JOptionPane.showInternalMessageDialog(null, "Invalid Date! Please try again.", "Invalid Date", JOptionPane.WARNING_MESSAGE);
						date.setText("");

					}
					else {
						n = Date.properDate(aDate);
						if (n == false) {
							date.setText("");
							JOptionPane.showMessageDialog(null, "Enter proper Date.", "Invalid Date", JOptionPane.WARNING_MESSAGE);
						}
						else
						{

								int r = modelList.getRowCount();
								for (int k = 0; k < r; k++) {
									System.out.println(k);

									int st1 = Integer.parseInt(modelList.getValueAt(k, 1).toString());
									int st2 = st1;
									System.out.println("new " + st1);
									File F1 = new File(mk + "\\" + aDate);
									F1.mkdir();
									F1 = new File(mk + "\\" + aDate + "\\" + modelList.getValueAt(k, 0).toString());
									F1.mkdir();
									F1 = new File(mk + "\\" + aDate + "\\" + modelList.getValueAt(k, 0).toString() + "\\sale.txt");
									try {
										if (F1.exists()) {
											fr = new FileReader(F1);
											br = new BufferedReader(fr);
											System.out.println(st1);
											st1 += Integer.parseInt(br.readLine());
											System.out.println(st1);
											br.close();
										}
										fw = new FileWriter(F1);
										System.out.println(st1);
										fw.write("" + st1);
										fw.close();

										File F2 = new File(pro + "\\" + modelList.getValueAt(k, 0).toString() + "\\stock.txt");
										fr = new FileReader(F2);
										br = new BufferedReader(fr);
										st2 = Integer.parseInt(br.readLine()) - st2;
										br.close();
										fw = new FileWriter(F2);
										System.out.println("total " + st2);
										fw.write("" + st2);
										fw.close();
										
									
									
									} catch (Exception e3) {
										throw new RuntimeException(e3);
									}
								}
									try {
										System.out.println(ac[0]);
										File F3 = new File(acc + "\\" + ac[0] + "\\balance.txt");
										fr = new FileReader(F3);
										br = new BufferedReader(fr);
										Float bal1 = Float.parseFloat(br.readLine());
										br.close();
										bal1 += total;
										fw = new FileWriter(F3);
										fw.write("" + bal1);
										fw.close();
									} catch (Exception e) {
										System.out.println(e.getMessage());

									}

									String m2 = aDate.substring(3, 10);
									System.out.println(m2);
									Float bal2=0.0f;
									try {
										File F4 = new File(exp + "\\" + m2);
										F4.mkdir();
										File F5 = new File(exp + "\\" + m2 + "\\earnings.txt");
										if(F5.exists())
										{
										fr = new FileReader(F5);
										br = new BufferedReader(fr);
										bal2 = Float.parseFloat(br.readLine());
										br.close();
										}
										bal2 += total;
										fw = new FileWriter(F5);
										fw.write("" + bal2);
										fw.close();
									} catch (Exception e4) {
										System.out.println(e4.getMessage());
									}

							int billNo = 0;
							try {
								billNo = printBill(aDate,total);
							} catch (IOException e) {
								throw new RuntimeException(e);
							}
							JOptionPane.showInternalMessageDialog(null, "Your purchase was successfull !!\nYour Bill No is "+billNo+".", "Purchase confirmed", JOptionPane.INFORMATION_MESSAGE);

								
								sf.dispose();
								sf.validate();
								SaleFrame Sf = new SaleFrame();
								Sf.create(Sf);
							
							
						}
					}
				}
			
		}
		if(ae.getSource().equals(billsbt))
		{
			sf.dispose();
			BillFrame bf = new BillFrame();
			bf.create(bf);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent ke) {
		char ch=ke.getKeyChar();
		if(ch=='\n')
		{
				conf.setEnabled(true);
				clr.setEnabled(true);
				un.setVisible(false);
			
	            String aDate=date.getText().toString();
	            if(aDate.equals("") || J1.getText().equals(""))
	            {

	                JOptionPane.showMessageDialog(null,"Please enter all required details.","Invalid Data",JOptionPane.WARNING_MESSAGE);

	            }
	            else
	            {
	                boolean alfa = numCheck1(J1.getText());
	                boolean n =true;
	                
	                for(int i=0;i<7;i++)
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
	                if(alfa==false)
	                {
	                    J1.setText("");
	                    JOptionPane.showMessageDialog(null, "Enter proper Sale details.", "Invalid Sale", JOptionPane.WARNING_MESSAGE);
	                }

	                if(n==true && alfa==true)
	                {
	                	int ind = prodList.getSelectedRow();

	                	String p1 = product.getText();
						int u1 = Integer.parseInt(J1.getText());
						float c1 = u1 * Float.parseFloat(prodList.getValueAt(ind, 1).toString());
						Object[] newRow = {p1, u1, c1};
						modelList.addRow(newRow);
						total += c1;
						price.setText("" + total);
						product.setFont(new Font("Arial", Font.BOLD, 20));
						product.setText("<Select product>");
						J1.setText("");
						addList.setEnabled(false);
						listCount++;
						
	                }
	       			}
	            }
	        }
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}