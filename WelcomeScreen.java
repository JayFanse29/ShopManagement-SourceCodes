import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JWindow;
import javax.swing.SwingConstants;

public class WelcomeScreen extends JFrame{
	
	JProgressBar load;
	JLabel image,title;
	JWindow welcome=new JWindow();
//	HomeFrame hf=new HomeFrame();
	WelcomeScreen()
	{
		welcome.getContentPane().setBackground(new Color(254,242,0));
		welcome.setSize(1200,700);
		welcome.setLocationRelativeTo(null);
		welcome.setLayout(null);
		
//		image=new JLabel(new ImageIcon("res\\shop1.jpg"));
		image=new JLabel(new ImageIcon(getClass().getClassLoader().getResource("shop1.jpg")));
		
		image.setBounds(225,125,750,500);
		
		titleBar();
		loadingBar();
		welcome.add(load); welcome.add(image);	welcome.add(title);
		welcome.setVisible(true);

		for(int i=0;i<=100;i++)
		{
		
		try 
		{ Thread.sleep(35); }
		catch(Exception e) { }
		load.setValue(i);
		load.setString("Loading... "+i+"%");

		}
		
		
		LoginFrame lf=new LoginFrame();
		lf.create(lf);
		welcome.setVisible(false);
		
		
		
	}	
	
	void loadingBar()
	{
		load=new JProgressBar();
		load.setBounds(0,660,1200,40);
		load.setBackground(Color.white);
		load.setForeground(Color.red);
		load.setStringPainted(true);
//		load.setOrientation(SwingConstants.HORIZONTAL);
//		load.setOpaque(true);
		load.setFont(new Font("Arial",Font.BOLD,20));
	}
	
	void titleBar()
	{
		title=new JLabel();
		title.setText("Shop Management Application");
		title.setFont(new Font("Arial",Font.BOLD,60));
		title.setBounds(200,25,900,100);
	}
	public static void main(String args[])
	{
		new WelcomeScreen();
	}
	
}
