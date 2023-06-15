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

public class HelpPasswordFrame extends JFrame implements ActionListener{
	JPanel up;
    JButton back;
    JLabel info, a;
    JTextArea text;


    void create(HelpPasswordFrame hpf) {
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
        
        text = new JTextArea("--> This is the window for changing password of the account.\n--> The user must enter the current password and then only they can change the password.\n--> Once the details specified above are filled then by selecting 'Change Password' the user can change the password.");
        text.setBounds(10, 110, 1160, 540);
        text.setFont(new Font("Arial", Font.PLAIN, 20));
        text.setWrapStyleWord(true);
        text.setLineWrap(true);
        text.setEditable(false);
        text.setBorder(Blackline);

        HelpPasswordUpPanel up1=new HelpPasswordUpPanel(hpf);
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
    	HelpPasswordFrame hpf=new HelpPasswordFrame();
    	hpf.create(hpf);
    }

}
class HelpPasswordUpPanel implements ActionListener
{
	JPanel up;
	JLabel info;
	JButton help,back;
	Border Blackline = BorderFactory.createLineBorder(Color.black,3);
	Border Grayline = BorderFactory.createLineBorder(Color.gray,3);
    HelpPasswordFrame hpf;
    
	HelpPasswordUpPanel(HelpPasswordFrame hpf)
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
		info = new JLabel("Change password Help Window");
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
//			PasswordFrame pf=new PasswordFrame();
//			pf.create(pf);
			hpf.dispose();
			}
			catch(Exception e) { }
		}
	}
}