////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
//	Class		:	AboutUs
//	Working 	: 	It contains information about comapany.
//	Link with 	:	->Login(if click on login),<-HomePage(if click on Home);
//
//
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

class AboutUs extends JFrame implements MenuListener
{
	ImageIcon img1,img2;
	JLabel imgback1,imgback2,slogan,addr,mail;	
	JMenuBar mb1;
	JMenu m1,m2,m3;
	JMenuItem mi1,mi2;
	JPanel p1;
	public AboutUs()
	{		
		img2=new ImageIcon("photo/default.png");
		imgback2=new JLabel(img2);
		imgback2.setLayout(null);
		imgback2.setBounds(0,-5,1400,750);
		
		mb1=new JMenuBar();
		mb1.setLayout(null);
		mb1.setBounds(0,0,1400,40);
		mb1.setBackground(Color.white);
		m1=new JMenu("        Home");
		m1.setLayout(null);
		m1.setBounds(0,9,100,28);
		m1.setFont(new Font("Serif",Font.ITALIC,14));

		m2=new JMenu("       Login");
		m2.setLayout(null);
		m2.setBounds(100,9,100,28);
		m2.setFont(new Font("Serif",Font.ITALIC,14));
	
		m3=new JMenu("    About us");
		m3.setLayout(null);
		m3.setBounds(200,9,100,28);
		m3.setFont(new Font("Serif",Font.BOLD,14));

		slogan=new JLabel("Begin with us....");
		slogan.setBounds(950,640,320,30);
		slogan.setFont(new Font("serif",Font.ITALIC|Font.BOLD,23));
		slogan.setForeground(new Color(0,255,0));

		addr=new JLabel("Address  : Dholepatil road,Pune-411001.");
		addr.setBounds(25,60,350,30);
		addr.setFont(new Font("serif",Font.ITALIC|Font.BOLD,13));
		
		mail=new JLabel("Email-id : mads@gmail.com");
		mail.setBounds(25,100,350,30);
		mail.setFont(new Font("serif",Font.ITALIC|Font.BOLD,13));
			

		mb1.add(m1);
		mb1.add(m2);
		mb1.add(m3);

		m1.addMenuListener(this);
		m2.addMenuListener(this);
		//mi1.addActionListener(this);
		//mi2.addActionListener(this);

		imgback2.add(mb1);
		imgback2.add(slogan);	
		imgback2.add(addr);
		imgback2.add(mail);
		add(imgback2);	
		
	
		setLayout(null);
		setBounds(0,0,1400,900);
		setBackground(Color.white);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	public void menuSelected(MenuEvent me)
	{
		if (me.getSource()==m1)
		{
			new HomePage();
			setVisible(false);
		}
		else if (me.getSource()==m2)
		{
			new Login();
			setVisible(false);
		}		
	}

	public void menuCanceled(MenuEvent me) {}
	public void menuDeselected(MenuEvent me) {}	

	public static void main(String args[])
	{	
		new AboutUs();
	}
}
