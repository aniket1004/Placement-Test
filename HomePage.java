//////////////////////////////////////////////////////////////////////////////////////////
//
//	Class	:	HomePage
//	Working 	:	It contains Panel which have login,about-us.
//			After click Login select Admin or Student type 
//			and then it move to next page which is Login page.
//	Link with 	:	Login ,AboutUs. 
//
//
///////////////////////////////////////////////////////////////////////////////////////////

import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.awt.*;

public class HomePage extends JFrame implements MenuListener
{
	JMenuBar mb1;
	JMenu m1,m2,m3,m4;
	JMenuItem mi1,mi2;
	JLabel j1,back1,back;
	public HomePage()
	{		
		//ImageIcon img1 =new ImageIcon("Home.png");
		
		
		mb1=new JMenuBar();
		mb1.setLayout(null);
		mb1.setBounds(0,0,1400,28);
		mb1.setBackground(Color.LIGHT_GRAY);
		m1=new JMenu("    HOME");
		m1.setLayout(null);
		m1.setBounds(0,0,100,28);
		m1.setFont(new Font("Arial",Font.BOLD,14));
		
		m2=new JMenu("     LOGIN");
		m2.setLayout(null);
		m2.setBounds(100,0,100,28);
		m2.setFont(new Font("Arial",Font.ITALIC,14));
	
		m3=new JMenu("  ABOUT US");
		m3.setBounds(200,0,100,28);
		m3.setFont(new Font("Arial",Font.ITALIC,14));

		m4=new JMenu("      EXIT");
		m4.setBounds(300,0,100,28);
		m4.setFont(new Font("Arial",Font.ITALIC,14));


		/*mi1=new JMenuItem("Student");
		mi1.setBackground(Color.LIGHT_GRAY);
		mi1.setFont(new Font("Arial",Font.ITALIC,12));

		
		mi2=new JMenuItem("Admin");
		mi2.setBackground(Color.LIGHT_GRAY);
		mi2.setFont(new Font("Arial",Font.ITALIC,12));*/
		
		//m2.add(mi1);
		//m2.add(mi2);
		mb1.add(m1);
		mb1.add(m2);
		mb1.add(m3);
		mb1.add(m4);
		//mb1.setBounds(0,10,1200,80);
		add(mb1);
		j1=new JLabel("PLACEMENT TEST");
		//j1.setOpaque(true);
		
		j1.setFont(new Font("Serif",Font.BOLD|Font.ITALIC,38));
		j1.setLayout(null);
		j1.setBounds (150,180,450,40);
		
		ImageIcon img1 =new ImageIcon("photo/Homeimg.jpeg");
		back1=new JLabel(img1);
		back1.setBounds(0,0,1400,700);
		
		back1.add(j1);
		add(back1);	
		
		m4.addMenuListener(this);
		m3.addMenuListener(this);
		m2.addMenuListener(this);				
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setBounds(0,0,1200,700);
		setTitle("Home");
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void menuSelected(MenuEvent me)
	{
		
		if (me.getSource()==m3)
		{
			new AboutUs();
			setVisible(false);
		}
		else if (me.getSource()==m2)
		{
			new Login();
			setVisible(false);
		}
		else if (me.getSource()==m4)
		{
			int input=JOptionPane.showConfirmDialog(null,"Are you really want to exit ?","Exit",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,new ImageIcon("icon/666201.png"));
			if (input==0)
			{
				setVisible(false);
				System.exit(0);
			}
		}		
	}
	public void menuCanceled(MenuEvent me) {}
	public void menuDeselected(MenuEvent me) {}	
	public static void main(String args[])
	{
		new HomePage();
	}
}
	
