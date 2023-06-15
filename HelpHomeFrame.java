import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.border.Border;

public class HelpHomeFrame extends JFrame{

    JPanel up;
    JButton back;
    FileWriter fw;
    FileReader fr;
    BufferedReader br;
    Scanner sc;
    JLabel info, a;
    JTextArea text;


    void create(HelpHomeFrame hhf)
    {
        this.setLayout(null);
        this.setSize(1200,700);
        this.setLocationRelativeTo(null);
        Border Blackline = BorderFactory.createLineBorder(Color.black,3);
        this.getContentPane().setBackground(new Color(135 , 206 , 236));
        this.setResizable(false);
        a = new JLabel("The Overview of Home Window is shown as below.");
        a.setBounds(10,80,1000,22);
        a.setLayout(null);
        a.setFont(new Font("Arial",Font.BOLD,20));


        text = new JTextArea(" 1.] Accounts Window :- This option leads to the Account window for handling the account for money withdrawal & deposition.\n 2.] Product window :- This option leads to the Product window.This is for adding/deleting the products of the shop and editing             details like name, cost price, selling price, etc.\n 3.] Sale window :- This window is used for adding the sale of the product sequentially.\n 4.] Re-stock window :- This window is used for restocking the products as per requirement.\n 5.] Profit/Loss window :- This window is for getting monthly & yearly profit/loss as per the statistics of sale and re-stock.\n 6.] Analysis window :- This window is for getting monthly/yearly analysis of the products that are sold and the re-stocked.");
        text.setBounds(10, 110,1164,540);
        text.setFont(new Font("Arial",Font.PLAIN,20));
        text.setBorder(Blackline);
        text.setWrapStyleWord(true);
        text.setLineWrap(true);

        HelpHomeUpPanel up1=new HelpHomeUpPanel(hhf);
        up1.create();
        
        this.add(a);
        this.add(text);
        
        add(up1.up);

        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource().equals(back))
            dispose();
    }

    public static void main(String[] args) {
        HelpHomeFrame hhf=new HelpHomeFrame();
        hhf.create(hhf);
    }

}
class HelpHomeUpPanel implements ActionListener
{
	JPanel up;
	JLabel info;
	JButton help,back;
	Border Blackline = BorderFactory.createLineBorder(Color.black,3);
	Border Grayline = BorderFactory.createLineBorder(Color.gray,3);
    HelpHomeFrame hhf;
    
	HelpHomeUpPanel(HelpHomeFrame hhf)
	{
		this.hhf=hhf;
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
		info = new JLabel("Home Help Window");
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
//			HomeFrame hf=new HomeFrame();
//			hf.create(hf);
			hhf.dispose();
			}
			catch(Exception e) { }
		}
	}
}