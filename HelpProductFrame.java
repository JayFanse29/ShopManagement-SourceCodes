import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.border.Border;

public class HelpProductFrame extends JFrame implements ActionListener{

    JPanel up;
    JButton back;
    JLabel info, a;
    JTextArea text;

    void create(HelpProductFrame hpf) {
        this.setLayout(null);
        this.setSize(1200, 700);
        this.setLocationRelativeTo(null);
        Border Blackline = BorderFactory.createLineBorder(Color.black, 3);
        this.setResizable(false);
        this.getContentPane().setBackground(new Color(135, 206, 236));
        a = new JLabel("The Overview of Products Window is shown as below.");
        a.setBounds(10, 80, 1000, 22);
        a.setLayout(null);
        a.setFont(new Font("Arial", Font.BOLD, 20));

        text = new JTextArea("--> The Product Window is the window for adding/deleting/updating details of a product in the shop.\n--> The user may enter the date a product is added to shop and then proceed to fill the details of the product.\n--> The user may enter the name of the product, their cost price, selling price and the number of units of the product he wants to        store in the stock initially.\n--> After filling every detail the user can add the product details to the shop by using 'Add' button.\n--> The table below will show all the details of the products that are added or products that are already added.\n--> The user may also delete a product from the table by selecting the product from the table and using 'Delete' button.\n--> If any mistake occurs in filling details the user may use the 'Clear' button to reset the details.\n--> Once the details are filled then by selecting the 'Update' button one can update the product details of any product.");
        text.setBounds(10, 110, 1160, 540);
        text.setFont(new Font("Arial", Font.PLAIN, 20));
        text.setWrapStyleWord(true);
        text.setLineWrap(true);
        text.setEditable(false);
        text.setBorder(Blackline);

        HelpProductUpPanel up1=new HelpProductUpPanel(hpf);
        up1.create();
        
        this.add(a);
        this.add(text);
        add(up1.up);
        this.setVisible(true);
    }


    public static void main(String[] args) {
        HelpProductFrame hpf=new HelpProductFrame();
        hpf.create(hpf);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == back)
            dispose();
    }
}

class HelpProductUpPanel implements ActionListener
{
	JPanel up;
	JLabel info;
	JButton help,back;
	Border Blackline = BorderFactory.createLineBorder(Color.black,3);
	Border Grayline = BorderFactory.createLineBorder(Color.gray,3);
    HelpProductFrame hpf;
    
	HelpProductUpPanel(HelpProductFrame hpf)
	{
		this.hpf=hpf;
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
		info = new JLabel("Products Help Window");
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
//			ProductFrame pf=new ProductFrame();
//			pf.create(pf);
			hpf.dispose();
			}
			catch(Exception e) { }
		}
	}
}