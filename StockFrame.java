import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.*;
import java.awt.event.FocusEvent.Cause;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.text.MaskFormatter;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;

public class StockFrame extends JFrame implements ActionListener, MouseListener {
	
	int rowIndex;
	static int count=0;
	JButton sgst,rstk;
	FileWriter fw;
	JToggleButton mr,sr;
    FileReader fr;
    BufferedReader br;
	Color c = new Color(135,206,236);
    Scanner sc;
    JTable listItem,sgstList = new JTable();
	int listCount = 0;
	int total = 0;

	DefaultTableModel tm,D1;
	File pro = new File("C:\\ShopManagement\\products");
    File mk = new File("C:\\ShopManagement\\date");
    File acc=new File("C:\\ShopManagement\\Accounts");
	String[] ac = acc.list();
	File exp = new File("C:\\ShopManagement\\earnings_expense");
    String[] list =pro.list();
    String cop="",sop="",sto="";
	String heading[]= {"Product","Cost Price","Stock (units)"};
    String data[][];
	public String sgtData[][];
    static JScrollPane js,scPane;

	JTextField bdgt,finc;
	JFormattedTextField date;
	JFormattedTextField date2;
	MaskFormatter mf;
	JLabel product,price,dt7,bgt,FinCost;
    Account a1=new Account();
    EarningsAndExpense e1=new EarningsAndExpense();

    Font f,A=new Font("Arial",Font.BOLD,20);
	Border Blackline = BorderFactory.createLineBorder(Color.black,3);
	Border empty = BorderFactory.createLineBorder(Color.black,5);

   // StockFrame(String appTitle,String chartTitle)
	StockUpPanel sp;
	ManualPanel mp;
	SuggestPanel sgp;
	pieChart p1;

	void create(StockFrame sf)
    {
    	f=new Font("Arial",Font.PLAIN,30);
    	this.setLayout(null);
        this.setSize(1200,700);
        this.setLocationRelativeTo(null);
		this.getContentPane().setBackground(c);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);

		mr=new JToggleButton(new ImageIcon(getClass().getClassLoader().getResource("mr.png")));
		mr.setBounds(10,85,200,270);
		mr.setBorder(Blackline);
		mr.addActionListener(this);
		sr=new JToggleButton(new ImageIcon(getClass().getClassLoader().getResource("sr.png")));
		sr.setBounds(10,370,200,270);
		sr.setBorder(Blackline);
		sr.addActionListener(this);

		mr.addMouseListener(new MouseAdapter()
		{
			public void mouseEntered(MouseEvent me)
			{
				mr.setBorder(empty);
			}
			public void mouseExited(MouseEvent me)
			{
				mr.setBorder(Blackline);
			}

		});
		sr.addMouseListener(new MouseAdapter()
		{
			public void mouseEntered(MouseEvent me)
			{
				sr.setBorder(empty);
			}
			public void mouseExited(MouseEvent me)
			{
				sr.setBorder(Blackline);
			}

		});









//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	sp = new StockUpPanel(sf);
	sp.create();
	this.add(sp.up);

	mp = new ManualPanel(sf);
	mp.create();
	mp.listTable();
		try {
			mp.stockTable();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	this.add(mp.manual);

	sgp = new SuggestPanel(sf);
	sgp.create();
	this.add(sgp.suggested);

	p1 = new pieChart(sf);
	p1.create();
	this.add(p1.crt);
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////






//		crt.add(chartPanel);
		add(mr);	add(sr);
//		add(crt);
//		add(manual);
//		add(suggested);

///////////////////////////////////////////////////////////////////////////////////////////////////////


///////////////////////////////////////////////////////////////////////////////////////////////////////

        this.setVisible(true);
        
            }


    public void actionPerformed(ActionEvent ae)
   	{


   		if(mr.isSelected())
   		{
   			sp.info.setText("  Manual Re-Stock window");
   			
   			mr.setIcon(new ImageIcon(getClass().getClassLoader().getResource("mr_selected.png")));
   			sr.setIcon(new ImageIcon(getClass().getClassLoader().getResource("sr.png")));
   			
   			if(sgp.suggested.isVisible()==true)
   			{
   				sr.setSelected(false);	
   			}
   			mp.manual.setVisible(true);
   			sgp.suggested.setVisible(false);
   			p1.crt.setVisible(false);
   		}
   		if(sr.isSelected())
   		{
   			sp.info.setText("Suggested Re-Stock window");
   			
   			sr.setIcon(new ImageIcon(getClass().getClassLoader().getResource("sr_selected.png")));
   			mr.setIcon(new ImageIcon(getClass().getClassLoader().getResource("mr.png")));
   			
   			if(mp.manual.isVisible()==true)
   			{
   				mr.setSelected(false);
   			}
   			mp.manual.setVisible(false);
   			sgp.suggested.setVisible(true);
   			p1.crt.setVisible(false);
   		}
   		if(sr.isSelected()==false && mr.isSelected()==false)
   		{
   			sp.info.setText("Product Re-Stocking Window");
   			
   			mr.setIcon(new ImageIcon(getClass().getClassLoader().getResource("mr.png")));
   			sr.setIcon(new ImageIcon(getClass().getClassLoader().getResource("sr.png")));
   			
   			p1.crt.setVisible(true);
			mp.manual.setVisible(false);
			sgp.suggested.setVisible(false);
		}
   	}



	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StockFrame sf=new StockFrame();
		sf.create(sf);
	}

	@Override
	public void mouseClicked(MouseEvent me) {
		// TODO Auto-generated method stub
		try
		{		
		rowIndex=listItem.getSelectedRow();
		}
		catch(Exception e) { System.out.print("\nError : "+e.getMessage());}
	
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
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

}

class StockUpPanel implements ActionListener
{
	JPanel up;
	JLabel info;
	JButton help,back;
	Border Blackline = BorderFactory.createLineBorder(Color.black,3);
	Border Grayline = BorderFactory.createLineBorder(Color.gray,3);
	StockFrame sf = new StockFrame();

	StockUpPanel(StockFrame sf){ this.sf = sf; }

	void create() {
		up = new JPanel();
		up.setBackground(Color.lightGray);
		up.setPreferredSize(new Dimension(1200, 50));
		up.setBounds(0, 0, 1200, 70);
		up.setBorder(Blackline);
		up.setLayout(null);

		help = new JButton("HELP");
		help.setFocusable(false);
		help.setBounds(1070, 20, 80, 30);
		help.setBackground(Color.DARK_GRAY);
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

		info = new JLabel("Product Re-Stocking Window");
		info.setBounds(400, 23, 1000, 30);
		info.setLayout(null);
		info.setFont(new Font("Arial", Font.BOLD, 30));

		back = new JButton("Go Back");
		back.setBounds(40, 20, 80, 30);
		back.setFocusable(false);
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

		up.add(help); up.add(back); up.add(info);
//		sf.add(up);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(back))
		{
			sf.dispose();
			HomeFrame hf=new HomeFrame();
			hf.create(hf);
			hf.setVisible(true);
		}
		if(e.getSource().equals(help)){
			HelpRestockFrame hrf = new HelpRestockFrame();
			hrf.create(hrf);
			//sf.dispose();;

		}
	}
}

class ManualPanel implements ActionListener, KeyListener
{
	JPanel manual;
	MaskFormatter mf;
	JFormattedTextField date;
	JLabel dt,lis,product,uni,purchase,price,un,colon;
	JTable rstList,prodList;
	JScrollPane js;
	DefaultTableModel model;
	JButton addList,conf,clr;
	JTextField J1;
	File pro = new File("C:\\ShopManagement\\products");
	File acc = new File("C:\\ShopManagement\\Accounts");
	File mk = new File("C:\\ShopManagement\\date");
	File exp = new File("C:\\ShopManagement\\earnings_expense");
	String[] ac = acc.list();
	FileReader fr; BufferedReader br; FileWriter fw;
	Color c = new Color(135,206,236);
	Font A=new Font("Arial",Font.BOLD,20),f=new Font("Arial",Font.PLAIN,30);
	Border Blackline = BorderFactory.createLineBorder(Color.black,3);
	
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    LocalDateTime now = LocalDateTime.now();
    String currDt=""; 
    String dateInput="";
    
	StockFrame sf;
	int total = 0, listCount = 0;
	ManualPanel(StockFrame sf) {this.sf = sf;}
	void currDate()
	{
//		System.out.println(dtf.format(now));
		currDt=dtf.format(now);
		dateInput = dateInput + currDt.substring(0,2) + currDt.substring(3,5) + currDt.substring(6,10);
//		System.out.println(dateInput);
		
	}
	void create() {
		manual = new JPanel();
		manual.setLayout(null);
		manual.setBounds(220, 80, 956, 570);
		manual.setVisible(false);
		manual.setBackground(c);

		try {
			mf = new MaskFormatter("##-##-####");
		} catch (Exception e) {
		}
		date = new JFormattedTextField(mf);
		date.setBounds(815, 25, 110, 40);
		date.setFont(A);
		currDate();
		date.setText(dateInput);
		

		dt = new JLabel();
		dt.setText("Date (DD-MM-YYYY) :");
		dt.setBounds(575, 30, 230, 30);
		dt.setFont(new Font("Arial", Font.BOLD, 22));
		lis = new JLabel();
		lis.setText("Re-Stock List");
		lis.setBounds(10, 10, 200, 20);
		lis.setFont(A);

		product = new JLabel();
		product.setText("<Select product>");
		product.setFont(new Font("Arial", Font.BOLD, 20));
		product.setBounds(575, 100, 170, 40);
		product.setBackground(Color.white);
		product.setOpaque(true);
//		product.setBorder(Blackline);
		
		colon=new JLabel();
		colon.setText(":");
		colon.setFont(new Font("Arial", Font.BOLD, 20));
		colon.setBounds(760, 100, 10, 40);
		
		uni = new JLabel("Units");
		uni.setBounds(890, 110, 100, 20);
		uni.setFont(new Font("Arial", Font.BOLD, 20));

		addList = new JButton("Add to List");
		addList.setBounds(615, 165, 130, 50);
		addList.setFont(new Font("Arial", Font.PLAIN, 20));
		addList.setFocusable(false);
		addList.setEnabled(false);
		addList.addActionListener(this);
		
		clr = new JButton("Clear");
		clr.setBounds(780, 165, 100, 50);
		clr.setFont(new Font("Arial", Font.PLAIN, 20));
		clr.setFocusable(false);
		clr.setEnabled(false);
		clr.addActionListener(this);

		conf = new JButton("Confirm re-stock");
		conf.setBounds(575, 260, 190, 45);
		conf.setFont(new Font("Arial", Font.PLAIN, 20));
		conf.setFocusable(false);
		conf.addActionListener(this);
		conf.setEnabled(false);

		purchase = new JLabel();
		purchase.setText("Total cost :");
		purchase.setFont(new Font("Arial", Font.BOLD, 24));
		purchase.setBounds(230, 260, 190, 40);

		price = new JLabel();
		price.setOpaque(true);
		price.setBackground(Color.WHITE);
		price.setBounds(370, 260, 170, 40);
		price.setFont(new Font("Arial", Font.BOLD, 24));

		J1 = new JTextField();
		J1.setBounds(780, 100, 100, 40);
		J1.setFont(new Font("Arial", Font.BOLD, 18));
		J1.addKeyListener(this);


		un = new JLabel();
		un.setText("Empty list");
		un.setBounds(200, 120, 200, 40);
		un.setFont(new Font("Arial", Font.BOLD, 25));

		deleteOldDate();
		deleteOldExp();
		
		manual.add(dt); manual.add(J1); manual.add(price); manual.add(conf); manual.add(uni);
		manual.add(addList); manual.add(lis); manual.add(purchase); manual.add(product);
		manual.add(un); manual.add(date); manual.add(colon); manual.add(clr);

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


	void listTable(){

		String[][] names = new String[0][3];
		String[] columnHead = {"Product","Units","Cost"};
		model = new DefaultTableModel(names,columnHead);
		rstList = new JTable(model);

		JTableHeader header=rstList.getTableHeader();
		header.setFont(f);
		rstList.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		rstList.setRowHeight(40);

		JScrollPane js=new JScrollPane(rstList,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		js.setBounds(10,30,550,220);
		js.setVisible(true);
		rstList.setFont(f);
		js.setViewportView(rstList);
		rstList.validate();
		js.validate();
		manual.add(js);
	}

	void stockTable() throws IOException {
		String[] List = pro.list();
		int ll = List.length;
		String[][] names = new String[ll][3];

		for(int i = 0; i < ll; i++)
		{
			File product = new File(pro+"\\"+List[i]+"\\stock.txt");
			fr = new FileReader(product);
			br = new BufferedReader(fr);
			String stk = br.readLine();
			br.close();

			product = new File(pro+"\\"+List[i]+"\\Cost_price.txt");
			fr = new FileReader(product);
			br = new BufferedReader(fr);
			float a = Float.parseFloat(br.readLine());
			int b = (int) a;
			br.close();
			String sp = "" + b;

			names[i][0] = List[i];
			names[i][1] = sp;
			names[i][2] = stk;
		}
		String[] columnHead={"Product name","Cost Price","Stock"};
		DefaultTableModel model = new DefaultTableModel(names,columnHead);
		prodList=new JTable(model);

		JTableHeader header=prodList.getTableHeader();
		header.setFont(f);

		prodList.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		prodList.setRowHeight(40);

		js=new JScrollPane(prodList,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		js.setBounds(0,320,956,250);
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
		manual.add(js);
		manual.validate();
		manual.setVisible(false);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(addList))
		{
			conf.setEnabled(true);
			clr.setEnabled(true);
			un.setVisible(false);
			String d1 = date.getText();
			boolean n =true;

			for(int i=0;i<10;i++)
			{
				if(d1.charAt(i)!='-')
				{
					if(d1.charAt(i)<'0' || d1.charAt(i)>'9')
					{
						n=false;
						break;
					}
				}
			}

			if(n) {
				boolean n1 = numCheck2(J1.getText());
				if (Objects.equals(J1.getText(), ""))
					n1 = false;
				int ind = prodList.getSelectedRow();
				if (!n1) {
					J1.setText("");
					JOptionPane.showMessageDialog(null, "Enter proper details.", "Invalid Units", JOptionPane.WARNING_MESSAGE);
				} else {
					String p1 = product.getText();
					int u1 = Integer.parseInt(J1.getText());
					int c1 = u1 * Integer.parseInt(prodList.getValueAt(ind, 1).toString());
					Object[] newRow = {p1, u1, c1};
					model.addRow(newRow);
					total += c1;
					price.setText("" + total);
					product.setFont(new Font("Arial", Font.BOLD, 20));
					product.setText("<Select product>");
					J1.setText("");
					addList.setEnabled(false);
					listCount++;
				}
			}
			else{
				date.setText("");
				JOptionPane.showMessageDialog(null,"Invalid Date..","Invalid date..",JOptionPane.WARNING_MESSAGE);
			}
		}
		
		if(e.getSource().equals(clr))
		{
			while(listCount>0)
			{
				model.removeRow(listCount-1);
				listCount--;
			}
			clr.setEnabled(false);
			un.setVisible(true);
		}

		if(e.getSource().equals(conf))
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

			if(total > bal)
			{
				System.out.println(bal);
				System.out.println(total);
				JOptionPane.showMessageDialog(null,"Not Sufficient balance for this restock","Insufficient balance",JOptionPane.WARNING_MESSAGE);
			}
			else {
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
							int confirm=JOptionPane.showConfirmDialog(null,"Total cost of this re-stock is :"+total+
									"\nYour balance is :" + bal +
									"\nDo you want to confirm purchase ?", "Confirm Purchase?", JOptionPane.YES_NO_OPTION);

							if(confirm == 0) {
								int r = model.getRowCount();
								for (int k = 0; k < r; k++) {
									System.out.println(k);

									int st1 = Integer.parseInt(model.getValueAt(k, 1).toString());
									int st2 = st1;
									System.out.println("new " + st1);
									File F1 = new File(mk + "\\" + aDate);
									F1.mkdir();
									F1 = new File(mk + "\\" + aDate + "\\" + model.getValueAt(k, 0).toString());
									F1.mkdir();
									F1 = new File(mk + "\\" + aDate + "\\" + model.getValueAt(k, 0).toString() + "\\stock.txt");
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

										File F2 = new File(pro + "\\" + model.getValueAt(k, 0).toString() + "\\stock.txt");
										fr = new FileReader(F2);
										br = new BufferedReader(fr);
										st2 += Integer.parseInt(br.readLine());
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
										bal1 -= total;
										fw = new FileWriter(F3);
										fw.write("" + bal1);
										fw.close();
									} catch (Exception ae) {
										System.out.println(ae.getMessage());

									}

									String m2 = aDate.substring(3, 10);
									System.out.println(m2);
									try {
										File F4 = new File(exp + "\\" + m2);
										F4.mkdir();
										File F5 = new File(exp + "\\" + m2 + "\\expense.txt");
										Float bal2=0.0f;
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

									sf.dispose();
									StockFrame Sn = new StockFrame();
									Sn.create(Sn);
									Sn.p1.crt.setVisible(true);
									Sn.mp.manual.setVisible(false);
									

								
							}
							if(confirm == 1) {
								JOptionPane.showMessageDialog(null,"Purchase canelled!","Purchase cancelled",JOptionPane.WARNING_MESSAGE);
							}
					}
					}
				}
			}
		}

	}

	boolean numCheck2(String str)
	{
		if(str.equals(""))
			return false;
		for(int i=0;i<str.length();i++)
		{
			if(str.charAt(i)<'0' || str.charAt(i)>'9' )
			{
				return false;
			}
		}
		return true;
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		char ch=e.getKeyChar();
		if(ch=='\n')
		{
			conf.setEnabled(true);
			clr.setEnabled(true);
			un.setVisible(false);
			String d1 = date.getText();
			boolean n =true;

			for(int i=0;i<10;i++)
			{
				if(d1.charAt(i)!='-')
				{
					if(d1.charAt(i)<'0' || d1.charAt(i)>'9')
					{
						n=false;
						break;
					}
				}
			}

			if(n) {
				boolean n1 = numCheck2(J1.getText());
				if (Objects.equals(J1.getText(), ""))
					n1 = false;
				int ind = prodList.getSelectedRow();
				if (!n1) {
					J1.setText("");
					JOptionPane.showMessageDialog(null, "Enter proper details.", "Invalid Units", JOptionPane.WARNING_MESSAGE);
				} else {
					String p1 = product.getText();
					int u1 = Integer.parseInt(J1.getText());
					int c1 = u1 * Integer.parseInt(prodList.getValueAt(ind, 1).toString());
					Object[] newRow = {p1, u1, c1};
					model.addRow(newRow);
					total += c1;
					price.setText("" + total);
					product.setFont(new Font("Arial", Font.BOLD, 20));
					product.setText("<Select product>");
					J1.setText("");
					addList.setEnabled(false);
					listCount++;
				}
			}
			else{
				date.setText("");
				JOptionPane.showMessageDialog(null,"Invalid Date..","Invalid date..",JOptionPane.WARNING_MESSAGE);
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

class SuggestPanel implements ActionListener, KeyListener
{
	JPanel suggested;
	JLabel dt7,bgt,FinCost,tbl;
	JTable sgstList;
	DefaultTableModel D1;
	JScrollPane scPane;
	int count = 0;
	JTextField bdgt,finc;
	JButton sgst,rstk;
	JFormattedTextField date2;
	MaskFormatter mf;
	File pro = new File("C:\\ShopManagement\\products");
	File acc = new File("C:\\ShopManagement\\Accounts");
	File mk = new File("C:\\ShopManagement\\date");
	File exp = new File("C:\\ShopManagement\\earnings_expense");
	String[] ac = acc.list();
	FileReader fr; BufferedReader br; FileWriter fw;
	String[][] sgtData;
	Color c = new Color(135,206,236);
	Font A=new Font("Arial",Font.BOLD,20),f=new Font("Arial",Font.PLAIN,30);
	StockFrame sf;
	
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    LocalDateTime now = LocalDateTime.now();
    String currDt=""; 
    String dateInput="";
    
	float bill = 0f;
	SuggestPanel(StockFrame sf) {this.sf = sf;}

	void create() {
		suggested = new JPanel();
		suggested.setBounds(220, 80, 956, 570);
		suggested.setVisible(false);
		suggested.setLayout(null);
		suggested.setBackground(c);

		dt7 = new JLabel("Enter Date (DD-MM-YYYY) :");
		dt7.setBounds(20,20,300,40);
		dt7.setFont(new Font("Arial",Font.BOLD,22));

//		System.out.println(dtf.format(now));
		currDt=dtf.format(now);
		dateInput = dateInput + currDt.substring(0,2) + currDt.substring(3,5) + currDt.substring(6,10);
//		System.out.println(dateInput);
			
		
		try {
			mf = new MaskFormatter("##-##-####");
		} catch (Exception e) {
		}
		date2 = new JFormattedTextField(mf);
		date2.setFont(A);
		date2.setBounds(320,20,110,40);
		date2.setText(dateInput);

		bgt = new JLabel("Enter Budget :");
		bgt.setBounds(20,70,160,40);
		bgt.setFont(new Font("Arial",Font.BOLD,22));

		bdgt = new JTextField();
		bdgt.setBounds(190,70,160,40);
		bdgt.setFont(new Font("Arial",Font.BOLD,22));
		bdgt.addKeyListener(this);
		
		tbl = new JLabel("Suggested Re-Stock list");
		tbl.setBounds(0,165,310,40);
		tbl.setFont(new Font("Arial",Font.BOLD,22));
		tbl.setVisible(false);

		sgst = new JButton("Show data");
		sgst.setBounds(380,71,150,40);
		sgst.setFocusable(false);
		sgst.addActionListener(this);
		sgst.setFont(new Font("Arial",Font.BOLD,22));

		FinCost = new JLabel();
		FinCost.setText("Total cost :");
		FinCost.setBounds(550,460,180,40);
		FinCost.setFont(f);
		FinCost.setVisible(false);

		finc = new JTextField();
		finc.setBounds(710,460,190,40);
		finc.setFont(f);
		finc.setEditable(false);

		rstk = new JButton("Confirm");
		rstk.setBounds(710,520,190,50);
		rstk.setFont(f);
		rstk.setFocusable(false);
		rstk.addActionListener(this);
		rstk.setVisible(false);

		deleteOldDate();
		deleteOldExp();
		
		suggested.add(dt7);
		suggested.add(date2);
		suggested.add(bgt);
		suggested.add(bdgt);
		suggested.add(tbl);
		suggested.add(sgst);
		suggested.add(FinCost);
		suggested.add(rstk);
		
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

	void sgtTable(float budget,String date4)
	{
		Stock s = new Stock();
		s.prevStock(date4);
		sgtData = s.suggestedRestock(budget);
//		for(int i = 0;i<13;i++)
//		{
//			System.out.println();
//			for(int j = 0;j<4;j++)
//				System.out.print(sgtData[i][j]+"  ");
//		}
		String[] col={"Product","Price","Units","Cost"};
		D1 = new DefaultTableModel(sgtData,col);
		sgstList = new JTable(D1);
		JTableHeader head1=sgstList.getTableHeader();
		head1.setFont(f);
		sgstList.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		sgstList.setRowHeight(40);
		if(count==1)
		{
			scPane.setVisible(false);
		}
		scPane=new JScrollPane(sgstList,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		count=1;
		scPane.setBounds(0,200,956,250);
		scPane.setVisible(true);
		sgstList.setFont(f);
		scPane.setViewportView(sgstList);
		sgstList.validate();
		scPane.validate();
		suggested.add(scPane);


		FinCost.setVisible(true);
		finc.setText(""+Stock.finalCost);
		bill = Stock.finalCost;
		Stock.finalCost = 0f;
		suggested.add(finc);
		rstk.setVisible(true);


		suggested.validate();
	}

	@Override
	public void actionPerformed(ActionEvent a) {
		if(a.getSource().equals(sgst))
		{

			String Date2=date2.getText();
			if(Date2.equals(""))
			{

				JOptionPane.showMessageDialog(null,"Please enter Date.","Invalid Data",JOptionPane.WARNING_MESSAGE);

			}
			else {
				boolean n2 = true;

				for (int i = 0; i < 7; i++) {
					if (Date2.charAt(i) != '-') {
						if (Date2.charAt(i) < '0' || Date2.charAt(i) > '9') {
							n2 = false;
							break;
						}
					}
				}

				if (n2 == false) {
					JOptionPane.showInternalMessageDialog(null, "Invalid Date! Please try again.", "Invalid Date", JOptionPane.WARNING_MESSAGE);
					date2.setText("");
				}
				else
				{
					n2 = Date.properDate(Date2);
					if (n2 == false) {
						JOptionPane.showInternalMessageDialog(null, "Invalid Date! Please try again.", "Invalid Date", JOptionPane.WARNING_MESSAGE);
						date2.setText("");
					}
				}

				boolean n3 = false;
				float b1 = 0f;
				System.out.print("|");
				System.out.print(bdgt.getText());
				System.out.print("|");
				boolean t = false;
				if(Objects.equals(bdgt.getText(),""))
				{
					t=true;
					JOptionPane.showMessageDialog(null,"please enter budget","Enter Budget",JOptionPane.WARNING_MESSAGE);
				}
				else if(numCheck1(bdgt.getText())){
					JOptionPane.showMessageDialog(null,"please enter budget properly","Invalid data",JOptionPane.WARNING_MESSAGE);
				}
				else {

					try {
						b1 = Float.parseFloat(bdgt.getText());
						System.out.println(b1);
						File F5 = new File(acc + "\\" + ac[0] + "\\balance.txt");
						fr = new FileReader(F5);
						br = new BufferedReader(fr);
						float bal2 = Float.parseFloat(br.readLine());
						br.close();
						if (bal2 >= b1) {
							System.out.println("here");n3 = true;}
					} catch (Exception e) {

					}
					if (n3 == false) {
						JOptionPane.showMessageDialog(null, "Not Sufficient balance for this budget", "Insufficient balance", JOptionPane.WARNING_MESSAGE);
					}
				}
				if(n3 == true && n2 ==true)
				{
					tbl.setVisible(true);
					sgtTable(b1,Date2);
				}
			}

		}

		if(a.getSource().equals(rstk))
		{
			float bal3 = 0f;
			File F5 = new File(acc + "\\" + ac[0] + "\\balance.txt");
			try {
				fr = new FileReader(F5);
				br = new BufferedReader(fr);
				bal3 = Float.parseFloat(br.readLine());
				br.close();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			int confirm=JOptionPane.showConfirmDialog(null,"Total cost of this re-stock is :"+bill+
					"\nYour balance is :" + bal3 +
					"\nDo you want to confirm purchase ?", "Confirm Purchase?", JOptionPane.YES_NO_OPTION);
//			Stock.finalCost=0.0f;
			if(confirm == 0) {
				String aDate = date2.getText();
				int r = D1.getRowCount();
				System.out.println("\nrows in d1 " + r);
				for (int k = 0; k < r; k++) {
					System.out.println("iteration :" + (k + 1));

					int st1 = Integer.parseInt(D1.getValueAt(k, 2).toString());
					int st2 = st1;
					System.out.println("new " + st1);
					File F1 = new File(mk + "\\" + aDate);
					F1.mkdir();
					F1 = new File(mk + "\\" + aDate + "\\" + D1.getValueAt(k, 0).toString());
					F1.mkdir();
					F1 = new File(mk + "\\" + aDate + "\\" + D1.getValueAt(k, 0).toString() + "\\stock.txt");
					try {
						if (F1.exists()) {
							fr = new FileReader(F1);
							br = new BufferedReader(fr);
							st1 += Integer.parseInt(br.readLine());
							br.close();
						}
						fw = new FileWriter(F1);
						System.out.println("old + new (same day):" + st1);
						fw.write("" + st1);
						fw.close();

						File F2 = new File(pro + "\\" + D1.getValueAt(k, 0).toString() + "\\stock.txt");
						fr = new FileReader(F2);
						br = new BufferedReader(fr);
						st2 += Integer.parseInt(br.readLine());
						br.close();
						fw = new FileWriter(F2);
						System.out.println("total stock " + st2);
						fw.write("" + st2);
						fw.close();

					} catch (Exception e) {
						throw new RuntimeException(e);
					}
				}
				try {
					System.out.println(ac[0]);
					File F3 = new File(acc + "\\" + ac[0] + "\\balance.txt");
					fr = new FileReader(F3);
					br = new BufferedReader(fr);
					float bal1 = Float.parseFloat(br.readLine());
					br.close();
					System.out.println("current bal " + bal1);
					System.out.println("Debited " + bill);
					bal1 -= bill;
					fw = new FileWriter(F3);
					fw.write("" + bal1);
					fw.close();
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				String m2 = aDate.substring(3, 10);
				System.out.println("Expance month " + m2);
				try {
					File F4 = new File(exp + "\\" + m2 + "\\expense.txt");
					float bal2 = 0f;
					if (F4.exists()) {
						fr = new FileReader(F4);
						br = new BufferedReader(fr);
						bal2 = Float.parseFloat(br.readLine());
						br.close();
					}
					bal2 += bill;
					System.out.println("Monthly expanse " + bal2);
					fw = new FileWriter(F4);
					fw.write("" + bal2);
					fw.close();
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				sf.dispose();
				StockFrame Sn = new StockFrame();
				Sn.create(Sn);
				Sn.p1.crt.setVisible(true);
				Sn.sr.setSelected(true);
				Sn.sgp.suggested.setVisible(false);

			}
			if(confirm == 1)
			{
				JOptionPane.showMessageDialog(null, "Purchase cancelled!", "Purchase cancelled", JOptionPane.WARNING_MESSAGE);
			}

		}

	}

	boolean numCheck1(String str)
	{
		int t = 0;
		if(str.equals(""))
			return false;
		for(int i=0;i<str.length();i++)
		{
			if(str.charAt(i)<'0' || str.charAt(i)>'9' || str.charAt(i)!='.')
			{
				if(str.charAt(i)=='.') t++;

				return false;
			}
			if(t>1) return false;
		}
		return true;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		char ch=e.getKeyChar();
		if(ch=='\n')
		{
			String Date2=date2.getText();
			if(Date2.equals(""))
			{

				JOptionPane.showMessageDialog(null,"Please enter Date.","Invalid Data",JOptionPane.WARNING_MESSAGE);

			}
			else {
				boolean n2 = true;

				for (int i = 0; i < 7; i++) {
					if (Date2.charAt(i) != '-') {
						if (Date2.charAt(i) < '0' || Date2.charAt(i) > '9') {
							n2 = false;
							break;
						}
					}
				}

				if (n2 == false) {
					JOptionPane.showInternalMessageDialog(null, "Invalid Date! Please try again.", "Invalid Date", JOptionPane.WARNING_MESSAGE);
					date2.setText("");
				}
				else
				{
					n2 = Date.properDate(Date2);
					if (n2 == false) {
						JOptionPane.showInternalMessageDialog(null, "Invalid Date! Please try again.", "Invalid Date", JOptionPane.WARNING_MESSAGE);
						date2.setText("");
					}
				}

				boolean n3 = false;
				float b1 = 0f;
				System.out.print("|");
				System.out.print(bdgt.getText());
				System.out.print("|");
				boolean t = false;
				if(Objects.equals(bdgt.getText(),""))
				{
					t=true;
					JOptionPane.showMessageDialog(null,"please enter budget","Enter Budget",JOptionPane.WARNING_MESSAGE);
				}
				else if(numCheck1(bdgt.getText())){
					JOptionPane.showMessageDialog(null,"please enter budget properly","Invalid data",JOptionPane.WARNING_MESSAGE);
				}
				else {

					try {
						b1 = Float.parseFloat(bdgt.getText());
						System.out.println(b1);
						File F5 = new File(acc + "\\" + ac[0] + "\\balance.txt");
						fr = new FileReader(F5);
						br = new BufferedReader(fr);
						float bal2 = Float.parseFloat(br.readLine());
						br.close();
						if (bal2 >= b1) {
							System.out.println("here");n3 = true;}
					} catch (Exception ae) { }
					if (n3 == false) {
						JOptionPane.showMessageDialog(null, "Not Sufficient balance for this budget", "Insufficient balance", JOptionPane.WARNING_MESSAGE);
					}
				}
				if(n3 == true && n2 ==true)
				{
					tbl.setVisible(true);
					sgtTable(b1,Date2);
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

class pieChart
{
	JPanel crt;
	File pro = new File("C:\\ShopManagement\\products");
	String[] list =pro.list();
	Color c = new Color(135,206,236);
	StockFrame sf = new StockFrame();
	pieChart(StockFrame sf) { this.sf = sf;}
	void create() {
		crt = new JPanel();
		crt.setBounds(220, 80, 956, 570);
		crt.setBackground(c);


		PieDataset pieset = createDataset();
		JFreeChart chart = createChart(pieset);
		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setLayout(null);
		chartPanel.setLocation(0, 0);
		chartPanel.setBackground(c);
		chartPanel.setPreferredSize(new Dimension(960, 570));
		crt.add(chartPanel);

	}
	PieDataset createDataset()
	{
		DefaultPieDataset result = new DefaultPieDataset();
		for(int i=0;i<list.length;i++)
		{
			File stock=new File(pro+"\\"+list[i]+"\\stock.txt");
			try
			{
				FileReader fr=new FileReader(stock);
				BufferedReader br=new BufferedReader(fr);
				int units=Integer.parseInt(br.readLine());

				result.setValue(list[i], units);
			}
			catch(Exception e) { System.out.print("\nError : "+e.getMessage()); }
		}

		return result;
	}

	JFreeChart createChart(PieDataset pieset)
	{
		JFreeChart chart=ChartFactory.createPieChart3D("Current stock of products(units)", pieset, true, true, false);
		PiePlot3D plot= (PiePlot3D) chart.getPlot();
		chart.setBackgroundPaint(c);
		plot.setStartAngle(0);
		plot.setShadowPaint(null);
		plot.setDirection(Rotation.CLOCKWISE);
		return chart;
	}

}


class PreviousStock
{
	Scanner sc = new Scanner(System.in);
	String dt="";
	float budget=0.00f;
	File pro = new File("C:\\ShopManagement\\products");
	String product[] = pro.list();

	int sale[]=new int[product.length];

	void prevStock(String date3)
	{
		try
		{
			budget=0.00f;
			FileReader fr;
			FileWriter fw;
			BufferedReader br;
			Scanner scn;
			StringBuffer sb;
			Date date = new Date();
			dt=date3;

			sb=new StringBuffer(dt);
			String currm=dt.substring(3,5);
			if(currm.equals("01"))
			{
				dt=""+sb.delete(3,5);
				dt=""+sb.insert(3,"12");
			}
			else
			{
				char ch=dt.charAt(4);
				if(ch=='0')
				{
					dt=""+sb.delete(3,5);
					dt=""+sb.insert(3,"09");
				}
				else
				{
					String m2=""+dt.charAt(4);
					int im2=Integer.parseInt(m2);
					im2--;
					dt=""+sb.delete(4,5);
					dt=""+sb.insert(4,""+im2);
				}
			}
			String prevm=dt.substring(3,5);
			File saledate = new File("C:\\ShopManagement\\date");
			String reqdate[] = saledate.list();
			for(int i=0;i<product.length;i++)
			{


				sale[i]=0;
				for(int j=0;j<reqdate.length;j++)
				{
					String temp=reqdate[j].substring(3,5);
					String reqday=reqdate[j].substring(0,2);
					String day=dt.substring(0,2);
					if((temp.equals(currm) && Integer.parseInt(reqday)<=Integer.parseInt(day)) || temp.equals(prevm))
					{

						File nfile = new File(saledate+"\\"+reqdate[j]+"\\"+product[i]+"\\sale.txt");
						if(nfile.exists())
						{
							fr=new FileReader(nfile);
							br=new BufferedReader(fr);

							String singlesale = br.readLine();
							sale[i]+=Integer.parseInt(singlesale);
							fr.close();
						}

					}

				}
				File nfile2 = new File("C:\\ShopManagement\\products\\"+product[i]+"\\Cost_price.txt");
				fr=new FileReader(nfile2);
				br=new BufferedReader(fr);

				String cost=br.readLine();
				fr.close();
				float costf=Float.parseFloat(cost);
				budget+=(sale[i]*costf);
			}
		}
		catch(Exception e)
		{
			System.out.print("\nError : "+e.getMessage());
		}
	}

}

class Stock extends PreviousStock
{
	Scanner sc = new Scanner(System.in);
	FileReader fr;
	FileWriter fw;
	public static float finalCost = 0.00f;
	BufferedReader br;
	String[][] suggestedRestock(float bd) {
		String[][] preData = new String[0][];
		try {
				float budget = 0.00f, minCostPrice;
				int k;
				float ratio = 0f;
				budget = bd;
				preData = new String[product.length][4];
				ratio = (budget / super.budget);
				File ff = new File("C:\\ShopManagement\\products\\" + product[0] + "\\Cost_price.txt");
				fr = new FileReader(ff);
				br = new BufferedReader(fr);
				String mincost = br.readLine();
				minCostPrice = Float.parseFloat(mincost);
				k = 0;
				char ans;
				for (int i = 0; i < product.length; i++) {
					int sgUnit = (int) (ratio * sale[i]);
					preData[i][2] = "" + 0;
					preData[i][2] = "" + sgUnit;
					preData[i][0] = product[i];

					File nf = new File("C:\\ShopManagement\\products\\" + product[i] + "\\Cost_price.txt");
					fr = new FileReader(nf);
					br = new BufferedReader(fr);
					preData[i][1] = br.readLine();
					float newcostf = Float.parseFloat(preData[i][1]);
					preData[i][3] = "" + Float.parseFloat(preData[i][2]) * (newcostf);
					finalCost += Float.parseFloat(preData[i][3]);
					if (newcostf < minCostPrice) {
						minCostPrice = newcostf;
						k = i;
					}

				}
				int additional = (int) ((budget - finalCost) / minCostPrice);
			System.out.println("additonal "+additional);
				File fff = new File("C:\\ShopManagement\\products\\" + product[k] + "\\Cost_price.txt");
				fr = new FileReader(fff);
				br = new BufferedReader(fr);
				String addprice = br.readLine();
				float additionalprice = Float.parseFloat(addprice);
				int addUnit = (int)(Float.parseFloat(preData[k][2]) + additional);
				preData[k][2] = "" + addUnit;
				preData[k][3] = "" + addUnit*additionalprice;
				finalCost += additional * additionalprice;
		} catch (Exception e) {
			System.out.print("\nError : " + e.getMessage());
		}
		return preData;
	}
}