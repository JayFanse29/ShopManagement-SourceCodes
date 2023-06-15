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
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.border.Border;

public class AccountFrame extends JFrame{
	
	
	
	Color bgColor=new Color(135,206,236);
	 
	void create(AccountFrame af)
	{
		this.setLayout(null);
        this.setSize(1200,700);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(bgColor);
        this.setResizable(false);
       
        AccountUpPanel up1=new AccountUpPanel(af);
        AccountMain am1=new AccountMain(af);
        
        up1.create();
        am1.create();
        
        add(am1.cen);
        add(up1.up);
        add(am1.balance);	add(am1.deposit);	add(am1.withdraw);		
        this.setVisible(true);
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AccountFrame af=new AccountFrame();
		af.create(af);
	}

}

class AccountUpPanel implements ActionListener
{
	JLabel info;
	JPanel up;
	JButton help,back;
	Border Blackline = BorderFactory.createLineBorder(Color.black,3);
	Border Grayline = BorderFactory.createLineBorder(Color.gray,3);
    AccountFrame af;
    
	AccountUpPanel(AccountFrame af)
	{
		this.af=af;
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
		info = new JLabel("Accounts Window");
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
			af.dispose();
			}
			catch(Exception e) { }
		}
		if(ae.getSource().equals(help))
		{
			try {
			HelpAccountFrame haf=new HelpAccountFrame();
			haf.create(haf);
			//af.dispose();
			}
			catch(Exception e) { }
		}
	}
}

class AccountMain implements ActionListener, KeyListener
{
	JPanel cen;
	JLabel balance,enter;
	JToggleButton deposit,withdraw;
	JButton ok;
	JTextField amount;
	FileReader fr;
	Scanner sc;
	FileWriter fw;
	BufferedReader br;
	float balnc;
	File acc=new File("C:\\ShopManagement\\Accounts");
	Font f=new Font("Arial",Font.BOLD,36);
    Font f1=new Font("Arial",Font.BOLD,18);
    float bal=0;
    Border Blackline = BorderFactory.createLineBorder(Color.black,3); 
	
AccountFrame af;
    
	AccountMain(AccountFrame af)
	{
		this.af=af;
	}
	void create()
	{
		
		
		
		balance();
		depositButton();
		withdrawButton();
		centerPanel();
		amountLabel();
		amountField();
		okButton();
		
		cen.add(amount);
		cen.add(enter);
		cen.add(ok);
	}
	void balance()
	{
		try
        {
        String name[]=acc.list();
        File F1=new File(acc+"\\" + name[0] + "\\balance.txt");
        if(F1.exists())
        {
        fr=new FileReader(F1);
        Scanner fsc=new Scanner(fr);
        balnc=fsc.nextFloat();
        fsc.close();
        }
        else
        balnc=0;
        
        balance = new JLabel();
        balance.setBounds(350,75,600,100);
        balance.setText("Current Balance : ₹ "+balnc);
        balance.setFont(f);
        }
        catch(Exception e) { }
	}
	void depositButton()
	{
		deposit=new JToggleButton("Deposit Money");
		deposit.setBounds(100,200,200,50);
		deposit.setFont(f1);
		deposit.setFocusable(false);
		deposit.addActionListener(this);
	}
	void withdrawButton()
	{
		withdraw=new JToggleButton("Withdraw Money");
        withdraw.setBounds(900,200,200,50);
        withdraw.setFont(f1);
        withdraw.setFocusable(false);
        withdraw.addActionListener(this);
	}
	void centerPanel()
	{
		cen=new JPanel();
        cen.setBackground(Color.LIGHT_GRAY);
        cen.setBounds(400,200,400,400);
        cen.setVisible(false);
        cen.setLayout(null);
        cen.setBorder(Blackline);
	}
	void amountLabel()
	{
		 enter=new JLabel();
		 enter.setBounds(75,50,350,50);
		 enter.setFont(new Font("Arial",Font.BOLD,22));

	}
	void amountField()
	{
		amount=new JTextField();
        amount.setBounds(100,125,200,50);
        amount.setFont(new Font("Arial",Font.BOLD,22));
        amount.addKeyListener(this);
        amount.setBorder(Blackline);

	}
	void okButton()
	{
		ok=new JButton();
        ok.setBounds(130,300,125,50);
        ok.addActionListener(this);
        ok.setFont(new Font("Arial",Font.BOLD,16));
        ok.setFocusable(false);
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		
		char ch=e.getKeyChar();
		if(ch=='\n')
		{
			String amt=amount.getText();
			boolean check=numCheck(amt);
			String nm[]=acc.list();
			File nf=new File(acc+"\\"+nm[0]+"\\balance.txt");
			
			if(check==false || amt.equals(""))
			{
				JOptionPane.showMessageDialog(null, "Enter valid amount", "Invalid amount", JOptionPane.WARNING_MESSAGE);
			}	
			else if(deposit.isSelected())
			{
				String con[]=acc.list();
				File f=new File(acc+"\\"+con[0]+"\\balance.txt");
				if(f.exists()==false)
				{
				try
				{
				fw=new FileWriter(f);
				fw.write(0.00+"");
				fw.close();
				}
				catch(Exception ee) { }
				}
				try
				{
				fr=new FileReader(nf);
				br=new BufferedReader(fr);
				bal=Float.parseFloat(br.readLine());
				bal+= Float.parseFloat(amt);
				fr.close();
				fw=new FileWriter(nf);
				fw.write(""+bal);
				fw.close();
				balnc=bal;
				balance.setText("Current Balance : ₹ "+balnc);
				}
				catch(Exception ae) { } 
				JOptionPane.showMessageDialog(null, "Amount Deposited successfully!","Deposition successful", JOptionPane.INFORMATION_MESSAGE);
			}
			else if(withdraw.isSelected())
			{
				String con[]=acc.list();
				File f=new File(acc+"\\"+con[0]+"\\balance.txt");
				if(f.exists()==false)
				{
				JOptionPane.showMessageDialog(null, "Please deposit amount in the account first.","Withdrawal failed!", JOptionPane.INFORMATION_MESSAGE);
				}
				else
				{
			try
			{
				fr=new FileReader(nf);
				br=new BufferedReader(fr);
				bal=Float.parseFloat(br.readLine());
				fr.close();
				if(Float.parseFloat(amt) > bal)
				{
					JOptionPane.showMessageDialog(null, "Insufficient balance!!", "Withdrawal failed", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					bal -= Float.parseFloat(amt);	
					fw=new FileWriter(nf);
					fw.write(""+bal);
					fw.close();
					balnc=bal;
					balance.setText("Current Balance : ₹ "+balnc);
					JOptionPane.showMessageDialog(null, "Amount Withdrawn successfully!","Withdrawal successful", JOptionPane.INFORMATION_MESSAGE);
				}
			}
			catch(Exception ae) { }
			}
		}
			amount.setText("");
		
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
		
		if(ae.getSource().equals(ok))
		{
			String amt=amount.getText();
			boolean check=numCheck(amt);
			String nm[]=acc.list();
			File nf=new File(acc+"\\"+nm[0]+"\\balance.txt");
			
			if(check==false || amt.equals(""))
			{
				JOptionPane.showMessageDialog(null, "Enter valid amount", "Invalid amount", JOptionPane.WARNING_MESSAGE);
			}	
			else if(deposit.isSelected())
			{
				String con[]=acc.list();
				File f=new File(acc+"\\"+con[0]+"\\balance.txt");
				if(f.exists()==false)
				{
				try
				{
				fw=new FileWriter(f);
				fw.write(0.00+"");
				fw.close();
				}
				catch(Exception e) { }
				}
				try
				{
				fr=new FileReader(nf);
				br=new BufferedReader(fr);
				bal=Float.parseFloat(br.readLine());
				bal+= Float.parseFloat(amt);
				fr.close();
				fw=new FileWriter(nf);
				fw.write(""+bal);
				fw.close();
				balnc=bal;
				balance.setText("Current Balance : ₹ "+balnc);
				}
				catch(Exception e) { } 
				JOptionPane.showMessageDialog(null, "Amount Deposited successfully!","Deposition successful", JOptionPane.INFORMATION_MESSAGE);
			}
			else if(withdraw.isSelected())
			{
				String con[]=acc.list();
				File f=new File(acc+"\\"+con[0]+"\\balance.txt");
				if(f.exists()==false)
				{
					JOptionPane.showMessageDialog(null, "Please deposit amount in the account first.","Withdrawal failed!", JOptionPane.INFORMATION_MESSAGE);
							
				}
				else
				{
				
			try
			{
				fr=new FileReader(nf);
				br=new BufferedReader(fr);
				bal=Float.parseFloat(br.readLine());
				fr.close();
				if(Float.parseFloat(amt) > bal)
				{
					JOptionPane.showMessageDialog(null, "Insufficient balance!!", "Withdrawal failed", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					bal -= Float.parseFloat(amt);	
					fw=new FileWriter(nf);
					fw.write(""+bal);
					fw.close();
					balnc=bal;
					balance.setText("Current Balance : ₹ "+balnc);
					JOptionPane.showMessageDialog(null, "Amount Withdrawn successfully!","Withdrawal successful", JOptionPane.INFORMATION_MESSAGE);
				}
			}
			catch(Exception e) { }
			}
			}
		}
		if(deposit.isSelected())
		{
			if(enter.getText().equals("Enter amount to withdraw"))
			{
				withdraw.setSelected(false);
			}
			enter.setText("Enter amount to deposit");
			cen.setVisible(true);
			ok.setText("Deposit");
			amount.setText("");
		}
		if(withdraw.isSelected())
		{
			if(enter.getText().equals("Enter amount to deposit"))
			{
				deposit.setSelected(false);
			}
			enter.setText("Enter amount to withdraw");
			cen.setVisible(true);
			ok.setText("Withdraw");
			amount.setText("");
		}	
		if(withdraw.isSelected()==false && deposit.isSelected()==false)
		{
			cen.setVisible(false);
			amount.setText("");
		}
	}
	boolean numCheck(String str)
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
}

