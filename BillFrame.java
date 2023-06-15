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
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.border.Border;
import javax.swing.text.MaskFormatter;

public class BillFrame extends JFrame{

	
	Color bgColor=new Color(135,206,236);
	
	 
	void create(BillFrame bf)
	{
		this.setLayout(null);
        this.setSize(1200,700);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.getContentPane().setBackground(bgColor);

        
        
       
        BillUpPanel up1=new BillUpPanel(bf);
        InputPanel ip1 = new InputPanel(bf);
        up1.create();
        ip1.create();
        add(up1.up);
        add(ip1.left);
        add(ip1.js);
		this.setVisible(true);

	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BillFrame bf=new BillFrame();
		bf.create(bf);
	}
	

}

class BillUpPanel implements ActionListener
{
	JPanel up;
	JLabel info;
	JButton help,back;
	Border Blackline = BorderFactory.createLineBorder(Color.black,3);
	Border Grayline = BorderFactory.createLineBorder(Color.gray,3);
    BillFrame bf;
    
	BillUpPanel(BillFrame bf)
	{
		this.bf=bf;
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
        up.add(help);
        up.add(back);
        up.add(info);
	}
	void info()
	{
		info = new JLabel("View Bill  Window");
        info.setBounds(480, 23, 1000, 24);
        info.setLayout(null);
        info.setFont(new Font("Arial", Font.BOLD, 24));

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
			SaleFrame sf=new SaleFrame();
			sf.create(sf);
			bf.dispose();
			}
			catch(Exception e) { }
		}
		if(ae.getSource().equals(help))
		{
			try {
			HelpBillFrame hbf=new HelpBillFrame();
			hbf.create(hbf);
			//bf.dispose();
			}
			catch(Exception e) { }
		}
	}
}

class InputPanel implements ActionListener,KeyListener
{
	JPanel left; 
	JTextArea billArea;
	JLabel purDt,billNo;
	JButton disp;
	JTextField tfbillNo;
	JFormattedTextField tfpurDt;
	JScrollPane js;
	MaskFormatter mf;
	Border panelBorder=BorderFactory.createLineBorder(Color.black,3);
	Font f=new Font("Arial",Font.PLAIN,24);
	Font b=new Font("Arial",Font.PLAIN,30);
	Font A=new Font("Arial",Font.PLAIN,28);
	Color bgColor=new Color(135,206,236);
	BillFrame bf;
	File bills=new File("C:\\ShopManagement\\Bills");
	FileInputStream fis;
	
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    LocalDateTime now = LocalDateTime.now();
    String currDt=""; 
    String dateInput="";
    
	InputPanel(BillFrame bf)
	{
		this.bf=bf;
	}
	void create()
	{
		left=new JPanel();
		left.setBackground(bgColor);
		left.setBounds(-2,67,352,633);
        left.setLayout(null);
        left.setBorder(panelBorder);
        
        billArea=new JTextArea("\n\n\n\n\n\n\n\t                    No bills to display!");
        billArea.setFont(f);
        billArea.setBounds(0,10,740,500);
        billArea.setLayout(null);
        billArea.setEditable(false);
        
        js=new JScrollPane(billArea,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        js.setBounds(400,120,740,500);
        js.setBorder(panelBorder);
        js.setViewportView(billArea);
        js.setVisible(true);
        js.validate();
        
        
        fields();
        currDate();
        date();
        heading();
        buttons();
		tfpurDt.requestFocus();

	}
	void currDate()
	{
//		System.out.println(dtf.format(now));
		currDt=dtf.format(now);
		dateInput = dateInput + currDt.substring(0,2) + currDt.substring(3,5) + currDt.substring(6,10);
//		System.out.println(dateInput);
		
	}
	void date()
	{
		try {
			mf = new MaskFormatter("##-##-####");
		} 
		catch (Exception e) { }
		tfpurDt = new JFormattedTextField(mf);
		tfpurDt.setBounds(90, 115, 150, 50);
		tfpurDt.setFont(A);
        tfpurDt.addKeyListener(this);
		tfpurDt.setText(dateInput);
		left.add(tfpurDt);
	}
	void fields()
	{
		tfbillNo = new JTextField();
		tfbillNo.setBounds(90, 315, 150, 50);
		tfbillNo.setFont(A);
        tfbillNo.addKeyListener(this);
        left.add(tfbillNo);
	}
	void heading()
	{
		purDt = new JLabel();
		purDt.setText("Enter Purchase Date : ");
		purDt.setBounds(20, 55, 300, 24);
        purDt.setLayout(null);
        purDt.setFont(new Font("Arial", Font.BOLD, 28));
		left.add(purDt);
		
		billNo = new JLabel("Enter Bill No. : ");
		billNo.setBounds(20, 250, 300, 24);
        billNo.setLayout(null);
        billNo.setFont(new Font("Arial", Font.BOLD, 28));
		left.add(billNo);
	}
	void buttons()
	{
		disp = new JButton("Display Bill");
		disp.setFocusable(false);
        disp.setBounds(80,450,180,50);
        disp.addActionListener(this);
        disp.setFont(f);
        left.add(disp);
		
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
	public void keyTyped(KeyEvent ke) {
		
		char ch=ke.getKeyChar();
		if(ch=='\n')
		{
			String aDate=tfpurDt.getText().toString();
            if(aDate.equals("") || tfbillNo.getText().equals(""))
            {

                JOptionPane.showMessageDialog(null,"Please enter all required details.","Invalid Data",JOptionPane.WARNING_MESSAGE);

            }
            else
            {
                boolean alfa = numCheck1(tfbillNo.getText());
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
       				tfpurDt.setText("");
       			
       			}
       			else
       			{
                n = Date.properDate(aDate);
                if(n==false)
                {
                	tfpurDt.setText("");
                	JOptionPane.showMessageDialog(null, "Enter proper Date.", "Invalid Date", JOptionPane.WARNING_MESSAGE);
                }
                if(alfa==false)
                {
                    tfbillNo.setText("");
                    JOptionPane.showMessageDialog(null, "Enter proper Sale details.", "Invalid Sale", JOptionPane.WARNING_MESSAGE);
                }

                if(n==true && alfa==true)
                {
                	
                	File billDt = new File("C:\\ShopManagement\\Bills\\" + aDate + "\\" + tfbillNo.getText() + ".txt");
                	if(billDt.exists())
                	{
                		String temp="";
                		try
                		{
                			billArea.setText("");
                			billArea.setFont(f);
                			FileInputStream fis=new FileInputStream(billDt);
            				int n1=0;
            				char c;
            				while(n1!=-1)
            				{
            					n1=fis.read();
            					if(n1!=-1)
            					{
            					c=(char)n1;
            					billArea.append(""+c);
            					}
            				}
            				fis.close();
            				js.validate();
                		}
                		catch(Exception e)
                		{
                            JOptionPane.showMessageDialog(null, e.getMessage(), "Unexpected error", JOptionPane.WARNING_MESSAGE);
                		}
                	}
                	else
                	{
                        JOptionPane.showMessageDialog(null, "Required bill doesn't exist!", "Invalid input", JOptionPane.WARNING_MESSAGE);
                        billArea.setText("\n\n\n\n\n\n\n\t                    No bills to display!");
                	}
                	
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
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		
		if(ae.getSource().equals(disp))
		{
			String aDate=tfpurDt.getText().toString();
            if(aDate.equals("") || tfbillNo.getText().equals(""))
            {

                JOptionPane.showMessageDialog(null,"Please enter all required details.","Invalid Data",JOptionPane.WARNING_MESSAGE);

            }
            else
            {
                boolean alfa = numCheck1(tfbillNo.getText());
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
       				tfpurDt.setText("");
       			
       			}
       			else
       			{
                n = Date.properDate(aDate);
                if(n==false)
                {
                	tfpurDt.setText("");
                	JOptionPane.showMessageDialog(null, "Enter proper Date.", "Invalid Date", JOptionPane.WARNING_MESSAGE);
                }
                if(alfa==false)
                {
                    tfbillNo.setText("");
                    JOptionPane.showMessageDialog(null, "Enter proper Sale details.", "Invalid Sale", JOptionPane.WARNING_MESSAGE);
                }

                if(n==true && alfa==true)
                {
                	
                	File billDt = new File("C:\\ShopManagement\\Bills\\" + aDate + "\\" + tfbillNo.getText() + ".txt");
                	if(billDt.exists())
                	{
                		String temp="";
                		try
                		{
                			billArea.setText("");
                			billArea.setFont(f);
                			FileInputStream fis=new FileInputStream(billDt);
            				int n1=0;
            				char c;
            				while(n1!=-1)
            				{
            					n1=fis.read();
            					if(n1!=-1)
            					{
            					c=(char)n1;
            					billArea.append(""+c);
            					}
            				}
            				fis.close();
            				js.validate();
                		}
                		catch(Exception e)
                		{
                            JOptionPane.showMessageDialog(null, e.getMessage(), "Unexpected error", JOptionPane.WARNING_MESSAGE);
                		}
                	}
                	else
                	{
                        JOptionPane.showMessageDialog(null, "Required bill doesn't exist!", "Invalid input", JOptionPane.WARNING_MESSAGE);
                        billArea.setText("\n\n\n\n\n\n\n\t                    No bills to display!");
                	}
                	
                }
       			}
            }
		}
		
	}
}
