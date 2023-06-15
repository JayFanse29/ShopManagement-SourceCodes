import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.border.Border;

public class HelpAccountFrame extends JFrame{
	
    JLabel a;
    JTextArea text;

    void create(HelpAccountFrame haf) {
        this.setLayout(null);
        this.setSize(1200, 700);
        this.setLocationRelativeTo(null);
        Border Blackline = BorderFactory.createLineBorder(Color.black, 3);
        this.setResizable(false);
        this.getContentPane().setBackground(new Color(135 , 206 , 236));

        a = new JLabel("The Overview of Accounts Window is shown as below.");
        a.setBounds(10,80,1000,22);
        a.setLayout(null);
        a.setFont(new Font("Arial",Font.BOLD,20));

        text = new JTextArea("-->Accounts window shows the current balance of the user's bank account.\n-->User can deposit and withdraw money manually in his account from here.\n-->All the transactions while re-stocking and selling the products occur from this account.\n--> Here you can see two options which are used for depositing and withdrawing money.\n--> As the name suggests, the 'Deposit Money' option lets you deposit money in the user's account.\n--> 'Withdraw Money' option lets you withdraw money from the user's account.");
        text.setBounds(10, 110,1164,540);
        text.setFont(new Font("Arial",Font.PLAIN,20));
        text.setLineWrap(true);
        text.setEditable(false);
        text.setBorder(Blackline);

        this.add(a);
        this.add(text);
        HelpAccountUpPanel up1=new HelpAccountUpPanel(haf);
        up1.create();
        
        add(up1.up);
        this.setVisible(true);
    }
    public static void main(String[] args) {
        HelpAccountFrame haf=new HelpAccountFrame();
        haf.create(haf);
    }
}

class HelpAccountUpPanel implements ActionListener
{
	JPanel up;
	JLabel info;
	JButton help,back;
	Border Blackline = BorderFactory.createLineBorder(Color.black,3);
	Border Grayline = BorderFactory.createLineBorder(Color.gray,3);
    HelpAccountFrame haf;
    
	HelpAccountUpPanel(HelpAccountFrame haf)
	{
		this.haf=haf;
	}
	void create()
	{
		up=new JPanel();
		up.setBackground(Color.lightGray);
		up.setPreferredSize(new Dimension(1200,50));
		up.setBounds(-2,0,1202,70);
		up.setLayout(null);
        up.setBorder(Blackline);
        backButton();
        info();
        up.add(back);
        up.add(info);
	}
	void info()
	{
		info = new JLabel("Accounts Help Window");
        info.setBounds(480, 23, 1000, 24);
        info.setLayout(null);
        info.setFont(new Font("Arial", Font.BOLD, 24));

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
//			AccountFrame af=new AccountFrame();
//			af.create(af);
			haf.dispose();
			}
			catch(Exception e) { }
		}
	}
}