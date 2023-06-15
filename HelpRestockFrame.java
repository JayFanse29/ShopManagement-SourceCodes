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

public class HelpRestockFrame extends JFrame implements ActionListener{

	JPanel up;
    JButton back;
    JLabel info, a;
    JTextArea text;


    void create(HelpRestockFrame hrf){
        this.setLayout(null);
        this.setSize(1200, 700);
        this.setLocationRelativeTo(null);
        Border Blackline = BorderFactory.createLineBorder(Color.black, 3);

        this.setResizable(false);
        this.getContentPane().setBackground(new Color(135, 206, 236));
        a = new JLabel("The Overview of Re-stock Window is shown as below.");
        a.setBounds(10, 80, 1000, 22);
        a.setLayout(null);
        a.setFont(new Font("Arial", Font.BOLD, 20));
        
        text = new JTextArea("--> This window here shows the restocking of the products.\n--> There is data of the current stock of products in the piechart.\n--> There are 2 options given below: \n--><Manual restocking & suggested restocking\n--> Select the format of the restocking accordingly.\n--> 1] Manual Restocking :- You can enter the date and the table at the bottom will give the information of the stock available and manually select the product for the restock and press 'Add' and it will be shown in the upmost table of the restocking.\n--> 2] Suggested Restocking :- You can enter the date and budget for the restocking and press 'Show data' and it will be shown in the table of the restocking.\n--> Finish your restocking after pressing 'Confirm'. ");
        text.setBounds(10, 110, 1160, 540);
        text.setFont(new Font("Arial", Font.PLAIN, 20));
        text.setWrapStyleWord(true);
        text.setLineWrap(true);
        text.setEditable(false);
        text.setBorder(Blackline);

        this.add(a);
        this.add(text);
//        up.add(info);
//        up.add(back);
        HelpRestockUpPanel up1=new HelpRestockUpPanel(hrf);
        up1.create();
        add(up1.up);
        this.setVisible(true);
        
    }
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == back)
			dispose();
	}
	public static void main(String[] args) {
		HelpRestockFrame hrf=new HelpRestockFrame();
		hrf.create(hrf);
	}

}

class HelpRestockUpPanel implements ActionListener
{
	JPanel up;
	JLabel info;
	JButton help,back;
	Border Blackline = BorderFactory.createLineBorder(Color.black,3);
	Border Grayline = BorderFactory.createLineBorder(Color.gray,3);
    HelpRestockFrame hrf;
    
	HelpRestockUpPanel(HelpRestockFrame hrf)
	{
		this.hrf=hrf;
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
		info = new JLabel("Restock Help Window");
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
//			StockFrame stf=new StockFrame();
//			stf.create(stf);
			hrf.dispose();
			}
			catch(Exception e) { }
		}
	}
}
