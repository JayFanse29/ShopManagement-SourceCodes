
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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

public class LoginFrame extends JFrame{
    JButton help,submit,changePass;
    JToggleButton show;
    JLabel name,pass,head;
    JTextField tf;
    JPasswordField pf;
    JPanel cen;
    Font f;
    File sm=new File("C:\\ShopManagement"); 
    File acc=new File("C:\\ShopManagement\\Accounts");
    File pro = new File("C:\\ShopManagement\\products");
    
    static boolean frame=true;
    Color bgColor=new Color(135,206,236);
    
    
    
    void create(LoginFrame lf){
        this.setLayout(null);
        this.setSize(1200,700);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
//        Border Grayline = BorderFactory.createLineBorder(Color.gray,3);
//        Border panelBorder=BorderFactory.createLineBorder(Color.black,3);
//        Border empty=BorderFactory.createRaisedBevelBorder();
//        f=new Font("Arial",Font.PLAIN,24);
       
        this.getContentPane().setBackground(bgColor);

        
        LoginUpPanel up1=new LoginUpPanel(lf);
        LoginCenterPanel cp1=new LoginCenterPanel(lf);
        

        

        

        
        up1.create();
        cp1.create();
        add(up1.up);
        add(cp1.head);
        add(cp1.cen);	
        this.setVisible(true);
        
        
    }
    
	
	


	
	public static void main(String args[])
    {
       LoginFrame lf=new LoginFrame();
       lf.create(lf);
    }
}

class LoginUpPanel implements ActionListener
{
	JPanel up;
	JLabel info;
	JButton help;
	Border Blackline = BorderFactory.createLineBorder(Color.black,3);
	Border Grayline = BorderFactory.createLineBorder(Color.gray,3);
    LoginFrame lf;
    File acc=new File("C:\\ShopManagement\\Accounts");

	LoginUpPanel(LoginFrame lf)
	{
		this.lf=lf;
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
	        info();
	        up.add(help);
	        up.add(info);
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
	void info()
	{
		info = new JLabel();
		if(acc.exists())
			info.setText("Log in Account");
		else
			info.setText("Create Account");
		info.setBounds(500, 23, 1000, 30);
        info.setLayout(null);
        info.setFont(new Font("Arial", Font.BOLD, 30));

	}
	@Override
	public void actionPerformed(ActionEvent ae) {
		
		if(ae.getSource().equals(help))
		{
			try {
			HelpLoginFrame hlf=new HelpLoginFrame();
			hlf.create(hlf);
			//lf.dispose();
			}
			catch(Exception e) { }
		}
	}
}

class LoginCenterPanel extends LoginFrame implements ActionListener, KeyListener
{
	JPanel cen;
	JButton submit,changePass;
	JToggleButton show;
	JTextField tf,sn;
	JPasswordField pf;
	JLabel head,name,pass,shop;
	Font f=new Font("Arial",Font.PLAIN,24);
	Font f1=new Font("Arial",Font.BOLD,34);
	Border Blackline = BorderFactory.createLineBorder(Color.black,3);
    File acc=new File("C:\\ShopManagement\\Accounts");
    File sm=new File("C:\\ShopManagement"); 
    File pro = new File("C:\\ShopManagement\\products");
    
    String nm,ps,shopname;
    FileWriter fw;
    FileReader fr;
    BufferedReader br;
    Scanner sc;
    LoginFrame lf;
	
    LoginCenterPanel(LoginFrame lf)
	{
		this.lf=lf;
	}
	void create()
	{
		cen=new JPanel();
        cen.setBackground(Color.lightGray);
        cen.setPreferredSize(new Dimension(1200,50));
        cen.setBounds(290,200,600,400);
        cen.setLayout(null);
        cen.setBorder(Blackline);
        
        submitButton();
        changePasswordButton();
        showPasswordIcon();
        textField();
        passwordField();
        labels();
        heading();
        displayOption();
        
	}
	void submitButton()
	{
		submit=new JButton();
        submit.setFont(new Font("Arial",Font.BOLD,18));
        submit.setBounds(240,320,100,50);
        submit.setFocusable(false);
        submit.addActionListener(this);
        cen.add(submit);
	}
	void changePasswordButton()
	{
		changePass=new JButton();
        changePass.setFont(new Font("Arial",Font.BOLD,16));
        changePass.setBounds(320,320,175,50);
        changePass.setFocusable(false);
        changePass.addActionListener(this);
        changePass.setText("Change Password");
	}
	void showPasswordIcon()
	{
		show=new JToggleButton(new ImageIcon(getClass().getClassLoader().getResource("show.jpg")));
        show.setBounds(510,220,50,50);
        show.addActionListener(this);
        cen.add(show);
	}
	void textField()
	{
		tf = new JTextField();
        tf.setFont(f);
        tf.setBounds(260,120,300,50);
        tf.addKeyListener(this);
        cen.add(tf);
	}
	void passwordField()
	{
		pf=new JPasswordField();
        pf.setFont(f);
        pf.setBounds(260,220,250,50);
        pf.addKeyListener(this);
        cen.add(pf);
	}
	void labels()
	{
		name=new JLabel("Account Name : ");
        pass=new JLabel("Password : ");
        name.setFont(f);
        pass.setFont(f);
        name.setBounds(40,120,200,50);
        pass.setBounds(40,220,200,50);
        cen.add(name);
        cen.add(pass);

	}
	void heading()
	{
		head=new JLabel();
		head.setBounds(290,60,600,150);
        head.setFont(new Font("Arial",Font.CENTER_BASELINE,40));
        
	}
	void displayOption()
	{
		if(acc.exists())
        {
			String name="";
        	try
        	{
        	File sn = new File(sm + "\\shopname.txt");
        	fr = new FileReader(sn);
        	br = new BufferedReader(fr);
        	name  = br.readLine();
        	br.close();		
        	fr.close();
        	}
        	catch(Exception e) { }
        	head.setText(name);
        	head.setHorizontalAlignment(SwingConstants.CENTER);
        	submit.setText("Login");
        	submit.setBounds(200,320,100,50);
        	cen.add(changePass);
        	
        }
        else
        {
			shop=new JLabel("Shop Name : ");
			shop.setFont(f);
			shop.setBounds(40,20,200,50);
			sn = new JTextField();
			sn.setFont(f);
			sn.setBounds(260,20,300,50);
			sn.addKeyListener(this);
			cen.add(shop);
			cen.add(sn);
        	head.setText("");
        	submit.setText("Create");
        }
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource().equals(submit))
		{
			sm.mkdir();
			
			nm=tf.getText();
			ps=pf.getText();

			if(nm.equals("")|| ps.equals(""))
			{
				JOptionPane.showMessageDialog(null, "Please fill all the details", "Missing details", JOptionPane.WARNING_MESSAGE);
			}
			else
			{
			if(acc.exists()==false)
			{
			shopname=sn.getText();
				try
				{
				acc.mkdir();
				File acc1=new File(acc+"\\"+nm);
                acc1.mkdir();
                
                File acc2=new File(acc+"\\"+nm+"\\password.txt");
//				File acc3=new File(acc+"\\"+nm+"\\balance.txt");
				File acc3=new File(sm+"\\shopname.txt");
				fw=new FileWriter(acc3);
				fw.write(shopname);
				fw.close();
                fw=new FileWriter(acc2);
                fw.write(ps);
                fw.close();
//				fw=new FileWriter(acc3);
//				fw.write(0.00+"");
//				fw.close();
                JOptionPane.showMessageDialog(null, "Account created successfully!!","Account created",JOptionPane.INFORMATION_MESSAGE );
                pro.mkdir();
//                LoginFrame.frame=false;
        		HomeFrame hf=new HomeFrame();
        		hf.create(hf);
        		lf.dispose();
				}
				catch(Exception e) { 
					JOptionPane.showMessageDialog(null, e+"");
				} 
		
			}
			else
			{
				String name,password;
				name=tf.getText();
				password=pf.getText();
				File account=new File(acc+"");
                String accname[]=account.list();
                
                if(name.equals(accname[0])==false)
                {
                    JOptionPane.showMessageDialog(null, "Invalid Account name, please try again.", "Invalid input",JOptionPane.WARNING_MESSAGE );
                }
                else
                {
                	try
                	{
                	fr=new FileReader(acc+"\\"+name+"\\password.txt");
                	BufferedReader br = new BufferedReader(fr);
                	String accpass=br.readLine();
                	fr.close();
                	if(accpass.equals(password)==false)
                	{
                		JOptionPane.showMessageDialog(null, "Invalid Password, please try again.", "Invalid input",JOptionPane.WARNING_MESSAGE );
                	}
                	else
                	{
                		
//                		LoginFrame.frame=false;
                		HomeFrame hf=new HomeFrame();
                		hf.create(hf);
                		lf.dispose();
                	}
                	}
                	catch(Exception e) { }
                }
			}
			}
		}
		if(show.isSelected())
		{
			show.setIcon(new ImageIcon(getClass().getClassLoader().getResource("hide.jpg")));
			pf.setEchoChar((char)0);
		}
		else
		{
			show.setIcon(new ImageIcon(getClass().getClassLoader().getResource("show.jpg")));
			pf.setEchoChar('\u2022');	
		}
		if(ae.getSource().equals(changePass))
		{
			try
			{
				PasswordFrame pf=new PasswordFrame();
				pf.create(pf);
				lf.dispose();
//				LoginFrame.frame=false;
			}
			catch(Exception e) { }
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		char ch=e.getKeyChar();
		if(ch=='\n')
		{
			sm.mkdir();
			
			nm=tf.getText();
			ps=pf.getText();
			if(nm.equals("")|| ps.equals(""))
			{
				JOptionPane.showMessageDialog(null, "Please fill all the details", "Missing details", JOptionPane.WARNING_MESSAGE);
			}
			else
			{
			if(acc.exists()==false)
			{
			shopname=sn.getText();
				try
				{	
				acc.mkdir();
				File acc1=new File(acc+"\\"+nm);
                acc1.mkdir();
                
                File acc2=new File(acc+"\\"+nm+"\\password.txt");
                fw=new FileWriter(acc2);
                fw.write(ps);
                fw.close();
				File acc3=new File(sm+"\\shopname.txt");
				fw=new FileWriter(acc3);
				fw.write(shopname);
				fw.close();
//                File bl=new File(acc+"\\"+nm+"\\balance.txt");
//                fw=new FileWriter(bl);
//                fw.write(""+0.00);
//                fw.close();
                JOptionPane.showMessageDialog(null, "Account created successfully!!","Account created",JOptionPane.INFORMATION_MESSAGE );
                pro.mkdir();
                HomeFrame hf=new HomeFrame();
                hf.create(hf);
                lf.dispose();
        		
				}
				catch(Exception eee) { 
					JOptionPane.showMessageDialog(null, e+"");
				} 
		
			}
			else
			{
				String name,password;
				name=tf.getText();
				password=pf.getText();
				File account=new File(acc+"");
                String accname[]=account.list();
                
                if(name.equals(accname[0])==false)
                {
                    JOptionPane.showMessageDialog(null, "Invalid Account name, please try again.", "Invalid input",JOptionPane.WARNING_MESSAGE );
                }
                else
                {
                	try
                	{
                	fr=new FileReader(acc+"\\"+name+"\\password.txt");
                	BufferedReader br = new BufferedReader(fr);
                	String accpass=br.readLine();
                	fr.close();
                	if(accpass.equals(password)==false)
                	{
                		JOptionPane.showMessageDialog(null, "Invalid Password, please try again.", "Invalid input",JOptionPane.WARNING_MESSAGE );
                	}
                	else
                	{
                		HomeFrame hf=new HomeFrame();
                		hf.create(hf);
                		lf.dispose();
                		
                	}
                	}
                	catch(Exception ee) { }
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


//class LoginActivity extends LoginFrame implements ActionListener
//{
//	
//	File acc=new File("C:\\ShopManagement\\Accounts");
//    
//	
//    
////    CenterPanel cp1=new CenterPanel();
////    UpPanel up1=new UpPanel();
//	
//	public void actionPerformed(ActionEvent ae)
//	{
//
//		
//		
//	}
//	
//}
