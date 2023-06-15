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

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

public class PasswordFrame extends JFrame{

	
	Color bgColor=new Color(135,206,236);
	
	 
	void create(PasswordFrame pf)
	{
		this.setLayout(null);
        this.setSize(1200,700);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.getContentPane().setBackground(bgColor);

        
        
       
        PasswordUpPanel up1=new PasswordUpPanel(pf);
        PasswordCenterPanel cp1=new PasswordCenterPanel(pf); 
        
        up1.create();
        cp1.create();
        add(up1.up);
		add(cp1.cen); add(cp1.head);
		this.setVisible(true);

	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PasswordFrame pf=new PasswordFrame();
		pf.create(pf);
	}
	

}

class PasswordUpPanel implements ActionListener
{
	JPanel up;
	JLabel info;
	JButton help,back;
	Border Blackline = BorderFactory.createLineBorder(Color.black,3);
	Border Grayline = BorderFactory.createLineBorder(Color.gray,3);
    PasswordFrame pf;
    
	PasswordUpPanel(PasswordFrame pf)
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
		info.setText("Set new Password");
		info.setBounds(470, 23, 1000, 30);
        info.setLayout(null);
        info.setFont(new Font("Arial", Font.BOLD, 30));

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
			LoginFrame lf=new LoginFrame();
			lf.create(lf);
			pf.dispose();
			}
			catch(Exception e) { }
		}
		if(ae.getSource().equals(help))
		{
			try {
			HelpPasswordFrame hpf=new HelpPasswordFrame();
			hpf.create(hpf);
			//pf.dispose();
			}
			catch(Exception e) { }
		}
	}
}

class PasswordCenterPanel implements ActionListener,KeyListener
{
	JPanel cen; 
	JLabel oldPass,newPass,head;
	JPasswordField op,np;
	JToggleButton show1,show2;
	JButton changePass;
	Border panelBorder=BorderFactory.createLineBorder(Color.black,3);
	Font f=new Font("Arial",Font.PLAIN,24);
	PasswordFrame pf;
	String oldPassText,newPassText;
	File acc=new File("C:\\ShopManagement\\Accounts");
    File sm=new File("C:\\ShopManagement"); 
	FileReader fr;
	BufferedReader br;
	FileWriter fw;
    
	PasswordCenterPanel(PasswordFrame pf)
	{
		this.pf=pf;
	}
	void create()
	{
		cen=new JPanel();
        cen.setBackground(Color.lightGray);
        cen.setPreferredSize(new Dimension(1200,50));
        cen.setBounds(290,200,600,400);
        cen.setLayout(null);
        cen.setBorder(panelBorder);
        
        fields();
        heading();
        showButtons();
        changePassButton();
        cen.add(show1);
        cen.add(show2);
        cen.add(changePass);
        cen.add(op);
        cen.add(np);
        cen.add(oldPass);
        cen.add(newPass);
//        cen.add(head);
	}
	void fields()
	{
		op = new JPasswordField();
        op.setFont(f);
        op.setBounds(260,80,250,50);
        np=new JPasswordField();
        np.setFont(f);
        np.setBounds(260,180,250,50);
        
        op.addKeyListener(this);
        np.addKeyListener(this);
        
        oldPass=new JLabel("Current Password : ");
        newPass=new JLabel("New Password : ");
        oldPass.setFont(f);
        newPass.setFont(f);
        oldPass.setBounds(40,80,220,50);
        newPass.setBounds(40,180,200,50);
        
	}
	@Override
	public void keyTyped(KeyEvent ke) {
		
		char ch=ke.getKeyChar();
		if(ch=='\n')
		{
			oldPassText=op.getText();
			newPassText=np.getText();
			if(oldPassText.equals("")|| newPassText.equals(""))
			{
				JOptionPane.showMessageDialog(null, "Please fill all the details", "Missing details", JOptionPane.WARNING_MESSAGE);	
			}
			else
			{
				try
            	{
				String name[]=acc.list();
            	fr=new FileReader(acc+"\\"+name[0]+"\\password.txt");
            	BufferedReader br = new BufferedReader(fr);
            	String accpass=br.readLine();
            	fr.close();
            	if(accpass.equals(oldPassText)==false)
            	{
            		JOptionPane.showMessageDialog(null, "Invalid Password, please try again.", "Invalid input",JOptionPane.WARNING_MESSAGE );
            		op.setText(null);
            		np.setText(null);
            	}
            	else
            	{
            		JOptionPane.showMessageDialog(null, "Password changed successfully!", "Password changed",JOptionPane.WARNING_MESSAGE );
            		fw=new FileWriter(acc+"\\"+name[0]+"\\password.txt");
            		fw.write(newPassText);
            		fw.close();
            		LoginFrame lf=new LoginFrame();
            		lf.create(lf);
        			pf.dispose();
            	}
            	}
				catch(Exception e) { }
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
		
		if(show1.isSelected())
		{
			show1.setIcon(new ImageIcon(getClass().getClassLoader().getResource("hide.jpg")));
			op.setEchoChar((char)0);
		}
		else
		{
			show1.setIcon(new ImageIcon(getClass().getClassLoader().getResource("show.jpg")));
			op.setEchoChar('\u2022');	
		}
		
		if(show2.isSelected())
		{
			show2.setIcon(new ImageIcon(getClass().getClassLoader().getResource("hide.jpg")));
			np.setEchoChar((char)0);
		}
		else
		{
			show2.setIcon(new ImageIcon(getClass().getClassLoader().getResource("show.jpg")));
			np.setEchoChar('\u2022');	
		}
		
		if(ae.getSource().equals(changePass))
		{
			oldPassText=op.getText();
			newPassText=np.getText();
			if(oldPassText.equals("")|| newPassText.equals(""))
			{
				JOptionPane.showMessageDialog(null, "Please fill all the details", "Missing details", JOptionPane.WARNING_MESSAGE);	
			}
			else
			{
				try
            	{
				String name[]=acc.list();
            	fr=new FileReader(acc+"\\"+name[0]+"\\password.txt");
            	BufferedReader br = new BufferedReader(fr);
            	String accpass=br.readLine();
            	fr.close();
            	if(accpass.equals(oldPassText)==false)
            	{
            		JOptionPane.showMessageDialog(null, "Invalid Password, please try again.", "Invalid input",JOptionPane.WARNING_MESSAGE );
            		op.setText(null);
            		np.setText(null);
            	}
            	else
            	{
            		JOptionPane.showMessageDialog(null, "Password changed successfully!", "Password changed",JOptionPane.WARNING_MESSAGE );
            		fw=new FileWriter(acc+"\\"+name[0]+"\\password.txt");
            		fw.write(newPassText);
            		fw.close();
            		LoginFrame lf=new LoginFrame();
            		lf.create(lf);
        			pf.dispose();
            	}
            	}
				catch(Exception e) { }
			}
		}
		
	}
	
	void heading()
	{
		head=new JLabel();
		head.setBounds(290,60,600,150);
		head.setFont(new Font("Arial",Font.CENTER_BASELINE,40));
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
	}
	void showButtons()
	{
		show1=new JToggleButton(new ImageIcon(getClass().getClassLoader().getResource("show.jpg")));
        show1.setBounds(510,80,50,50);
        show1.addActionListener(this);
        
        show2=new JToggleButton(new ImageIcon(getClass().getClassLoader().getResource("show.jpg")));
        show2.setBounds(510,180,50,50);
        show2.addActionListener(this);
	}
	void changePassButton()
	{
		changePass=new JButton();
        changePass.setFont(new Font("Arial",Font.BOLD,18));
        changePass.setBounds(200,280,200,50);
        changePass.setFocusable(false);
        changePass.addActionListener(this);
        changePass.setText("Change Password");
	}
}
