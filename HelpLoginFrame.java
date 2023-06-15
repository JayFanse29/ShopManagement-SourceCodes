import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class HelpLoginFrame extends JFrame implements ActionListener {
    JPanel up;
    JButton back;
    JLabel info, a;
    JTextArea text;


    void create (HelpLoginFrame hlf) {
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

        text = new JTextArea("--> This here is the window which allows the user to log into their account.\n--> If the user does not have an account then they must create a new account.\n--> When creating a new account, the user should enter the username and password of their choice and then they can setup their account.\n--> If the user already have an account then by entering the username and password they can log into their account.\n--> The user may even change their passsword by selecting the 'Change Password' option.");
        text.setBounds(10, 110, 1160, 540);
        text.setFont(new Font("Arial", Font.PLAIN, 20));
        text.setWrapStyleWord(true);
        text.setLineWrap(true);
        text.setEditable(false);
        text.setBorder(Blackline);

        this.add(a);
        this.add(text);
        HelpLoginUpPanel up1=new HelpLoginUpPanel(hlf);
        up1.create();
        add(up1.up);
        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(back))
            dispose();
    }

    public static void main(String[] args) {
        HelpLoginFrame hlf=new HelpLoginFrame();
        hlf.create(hlf);
    }
}

class HelpLoginUpPanel implements ActionListener
{
	JPanel up;
	JLabel info;
	JButton help,back;
	Border Blackline = BorderFactory.createLineBorder(Color.black,3);
	Border Grayline = BorderFactory.createLineBorder(Color.gray,3);
    HelpLoginFrame hlf;
    
	HelpLoginUpPanel(HelpLoginFrame hlf)
	{
		this.hlf=hlf;
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
		info = new JLabel("Login Help Window");
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
//			LoginFrame lf=new LoginFrame();
//			lf.create(lf);
			hlf.dispose();
			}
			catch(Exception e) { }
		}
	}
}

