import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

// import acc.Account;
public class HomeFrame extends JFrame
{
	Color bgColor=new Color(135,206,236);

	void create(HomeFrame hf)
	{
		this.setLayout(null);
		this.setSize(1200,700);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getContentPane().setBackground(bgColor);

		

		CenterPanel cp1=new CenterPanel(hf);
		UpPanel up1=new UpPanel(hf);
		
		cp1.create();
		up1.create();
		add(cp1.cen);
		add(up1.up);
		
		this.setVisible(true);
	}
	
	
	public static void main(String args[])
	{
		HomeFrame hf=new HomeFrame();
		hf.create(hf);
	}
}

class UpPanel implements ActionListener
{
	JPanel up;
	JLabel info;
	JButton help,logout;
	Border Blackline = BorderFactory.createLineBorder(Color.black,3);
	Border Grayline = BorderFactory.createLineBorder(Color.gray,3);
    HomeFrame hf;
    
	UpPanel(HomeFrame hf)
	{
		this.hf=hf;
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
        logoutButton();
        info();
        up.add(info);
        up.add(help);
        up.add(logout);
	}
	void info()
	{
		info = new JLabel("Home Window");
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
	
	void logoutButton()
	{
		logout = new JButton("Logout");
		logout.setFocusable(false);
		logout.setBounds(60,20,100,30);
		logout.setBackground(Color.DARK_GRAY);
		logout.setBorder(Blackline);
		logout.setForeground(Color.white);
		logout.addActionListener(this);
		
		logout.addMouseListener(new MouseAdapter()
		{
			public void mouseEntered(MouseEvent me)
			{
				logout.setBorder(Grayline);
			}
			public void mouseExited(MouseEvent me)
			{
				logout.setBorder(Blackline);
			}
			
		});
	}
	
	
	
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		
		if(ae.getSource()==logout)
		{
			LoginFrame lf=new LoginFrame();
			lf.create(lf);
			hf.dispose();
		}
		if(ae.getSource().equals(help))
		{
			try {
			HelpHomeFrame hhf=new HelpHomeFrame();
			hhf.create(hhf);
			//hf.dispose();
			}
			catch(Exception e) { }
		}
	}
}

class CenterPanel implements ActionListener
{
	JPanel cen;
	JButton b1,b2,b3,b4,b5,b6;
	Color bgColor=new Color(135,206,236);
	Border empty=BorderFactory.createLineBorder(Color.black,5);
	Border Blackline = BorderFactory.createLineBorder(Color.black,3);
	File f1=new File("C:\\ShopManagement\\Accounts");
	File pro = new File("C:\\ShopManagement\\products");
	HomeFrame hf;
    
	CenterPanel(HomeFrame hf)
	{
		this.hf=hf;
	}
	void create()
	{
		cen=new JPanel();
		cen.setBackground(bgColor);
		cen.setBounds(0,70,1200,600);
		cen.setLayout(null);
		cen.setPreferredSize(new Dimension(1000,600));
		
		createButtons();
		cen.add(b1);	cen.add(b2);	cen.add(b3);	cen.add(b4);	cen.add(b5);	cen.add(b6);
	}
	
	void createButtons()
	{
		b1=new JButton(new ImageIcon(getClass().getClassLoader().getResource("Account.jpeg")));
		b1.setBounds(50,35,350,250);
		b1.setBorder(Blackline);
		b2=new JButton(new ImageIcon(getClass().getClassLoader().getResource("pp.jpg")));
		b2.setBounds(420,35,350,250);
		b2.setBorder(Blackline);
		b3=new JButton(new ImageIcon(getClass().getClassLoader().getResource("sale.jpg")));
		b3.setBounds(790,35,350,250);
		b3.setBorder(Blackline);
		b4=new JButton(new ImageIcon(getClass().getClassLoader().getResource("Rs.jpeg")));
		b4.setBounds(50,305,350,250);
		b4.setBorder(Blackline);
		b5=new JButton(new ImageIcon(getClass().getClassLoader().getResource("pl.jpg")));
		b5.setBounds(420,305,350,250);
		b5.setBorder(Blackline);
		b6=new JButton(new ImageIcon(getClass().getClassLoader().getResource("analysis.jpg")));
		b6.setBounds(790,305,350,250);
		b6.setBorder(Blackline);
		
		b1.addMouseListener(new MouseAdapter()
				{
					public void mouseEntered(MouseEvent me)
					{
						b1.setBorder(empty);
					}
					public void mouseExited(MouseEvent me)
					{
						b1.setBorder(Blackline);
					}
					
				});
		b2.addMouseListener(new MouseAdapter()
		{
			public void mouseEntered(MouseEvent me)
			{
				b2.setBorder(empty);
			}
			public void mouseExited(MouseEvent me)
			{
				b2.setBorder(Blackline);
			}
			
		});
		b3.addMouseListener(new MouseAdapter()
		{
			public void mouseEntered(MouseEvent me)
			{
				b3.setBorder(empty);
			}
			public void mouseExited(MouseEvent me)
			{
				b3.setBorder(Blackline);
			}
			
		});
		b4.addMouseListener(new MouseAdapter()
		{
			public void mouseEntered(MouseEvent me)
			{
				b4.setBorder(empty);
			}
			public void mouseExited(MouseEvent me)
			{
				b4.setBorder(Blackline);
			}
			
		});
		b5.addMouseListener(new MouseAdapter()
		{
			public void mouseEntered(MouseEvent me)
			{
				b5.setBorder(empty);
			}
			public void mouseExited(MouseEvent me)
			{
				b5.setBorder(Blackline);
			}
			
		});
		b6.addMouseListener(new MouseAdapter()
		{
			public void mouseEntered(MouseEvent me)
			{
				b6.setBorder(empty);
			}
			public void mouseExited(MouseEvent me)
			{
				b6.setBorder(Blackline);
			}
			
		});
		
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		b5.addActionListener(this);
		b6.addActionListener(this);
		
		String accna[]=f1.list();
		File f2=new File(f1+"\\"+accna[0]+"\\balance.txt");
		String pr[]=pro.list();
		
		if(f2.exists()==false)
		{
			b2.setEnabled(false);
			b3.setEnabled(false);
			b4.setEnabled(false);
			b5.setEnabled(false);
			b6.setEnabled(false);
		}
		else if(pr.length==0)
		{
			b2.setEnabled(true);
			b3.setEnabled(false);
			b4.setEnabled(false);
			b5.setEnabled(false);
			b6.setEnabled(false);
		}

		
		
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		
		if(ae.getSource()==b1)
		{
			try
			{

			AccountFrame af=new AccountFrame();
			af.create(af);	
			hf.dispose();
			}
			catch(Exception e)
			{
				System.out.print("\n"+e.getMessage());
			}
		}
		else if(ae.getSource()==b2)
		{
			try
			{
			ProductFrame pf=new ProductFrame();
			pf.create(pf);
			hf.dispose();
			}
			catch(Exception e)
			{
			}
		}
		else if(ae.getSource()==b3)
		{
			try
			{
			SaleFrame sf=new SaleFrame();
			sf.create(sf);
			hf.dispose();
			}
			catch(Exception e)
			{
			}
		}
		else if(ae.getSource()==b4)
		{
			try
			{
			StockFrame stf=new StockFrame();
			stf.create(stf);
			hf.dispose();
			}
			catch(Exception e)
			{
			}
		}
		else if(ae.getSource()==b5)
		{
			try
			{
			ProfitFrame pf=new ProfitFrame();
			pf.create(pf);
			hf.dispose();
				
			}
			catch(Exception e)
			{
			}
		}
		else if(ae.getSource()==b6)
		{
			try
			{
			AnalysisFrame anf=new AnalysisFrame();
			anf.create(anf);
			hf.dispose();
			}
			catch(Exception e)
			{
			}
		}
	}
}

