import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;

public class HelpBillFrame extends JFrame implements ActionListener{
	JPanel up;
    JButton back;
    JLabel info, a;
    JTextArea text;


    void create(HelpBillFrame hbf) {
        this.setLayout(null);
        this.setSize(1200, 700);
        this.setLocationRelativeTo(null);
        Border Blackline = BorderFactory.createLineBorder(Color.black, 3);
        this.setResizable(false);
        this.getContentPane().setBackground(new Color(135, 206, 236));

        a = new JLabel("The Overview of this Window is shown as below.");
        a.setBounds(10, 80, 1000, 22);
        a.setLayout(null);
        a.setFont(new Font("Arial", Font.BOLD, 20));
        
        text = new JTextArea("--> This Window is for Checking bills of past purchases.\n--> The user needs to enter the date of purchase when the bill was generated.\n--> Then user has to add bill no. which is unique for each bill generated on that date.\n--> Then if such bill exists it will be displayed on Bill Area.");
        text.setBounds(10, 110, 1160, 540);
        text.setFont(new Font("Arial", Font.PLAIN, 20));
        text.setWrapStyleWord(true);
        text.setLineWrap(true);
        text.setEditable(false);
        text.setBorder(Blackline);

        HelpBillUpPanel up1=new HelpBillUpPanel(hbf);
        up1.create();
        
        this.add(a);
        this.add(text);
        
        add(up1.up);
        this.setVisible(true);
    }


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(back))
			dispose();
	}
    public static void main(String[] args) {
    	HelpBillFrame hbf=new HelpBillFrame();
    	hbf.create(hbf);
    }

}
class HelpBillUpPanel implements ActionListener
{
	JPanel up;
	JLabel info;
	JButton help,back;
	Border Blackline = BorderFactory.createLineBorder(Color.black,3);
	Border Grayline = BorderFactory.createLineBorder(Color.gray,3);
    HelpBillFrame hbf;
    
	HelpBillUpPanel(HelpBillFrame hbf)
	{
		this.hbf=hbf;
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
		info = new JLabel("View Bill Help Window");
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
			//BillFrame bf=new BillFrame();
			//bf.create(bf);
			hbf.dispose();
			}
			catch(Exception e) { }
		}
	}
}