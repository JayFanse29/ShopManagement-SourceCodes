import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.border.Border;

public class HelpSaleFrame extends JFrame{
    JButton back;
    JLabel a;
    JTextArea text;


    void create(HelpSaleFrame hsf) {
        this.setLayout(null);
        this.setSize(1200, 700);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        Border Blackline = BorderFactory.createLineBorder(Color.black, 3);

        this.getContentPane().setBackground(new Color(135 , 206 , 236));

        
        a = new JLabel("The Overview of Sales Window is shown as below.");
        a.setBounds(10, 80, 1000, 22);
        a.setLayout(null);
        a.setFont(new Font("Arial", Font.BOLD, 20));

        text = new JTextArea("--> The Sale Window is the window for adding sales of the products based on the day of their sale.\n--> The user has to enter the date on which the sale of the product occurred and add product and details to the list.\n--> Once the work stated above is done the user can enter the sale for the respective product.\n--> The table below shows the name of the products, their selling prices and their stock respectively.");
        text.setBounds(10, 110, 1160, 540);
        text.setFont(new Font("Arial", Font.PLAIN, 20));
        text.setWrapStyleWord(true);
        text.setLineWrap(true);
        text.setEditable(false);
        text.setBorder(Blackline);

        HelpSaleUpPanel up1=new HelpSaleUpPanel(hsf);
        up1.create();
        
        this.add(a);
        this.add(text);
       
        add(up1.up);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        HelpSaleFrame hsf=new HelpSaleFrame();
        hsf.create(hsf);
    }

}

class HelpSaleUpPanel implements ActionListener
{
	JPanel up;
	JLabel info;
	JButton help,back;
	Border Blackline = BorderFactory.createLineBorder(Color.black,3);
	Border Grayline = BorderFactory.createLineBorder(Color.gray,3);
    HelpSaleFrame hsf;
    
	HelpSaleUpPanel(HelpSaleFrame hsf)
	{
		this.hsf=hsf;
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
		info = new JLabel("Sales Help Window");
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
//			SaleFrame sf=new SaleFrame();
//			sf.create(sf);
			hsf.dispose();
			}
			catch(Exception e) { }
		}
	}
}
