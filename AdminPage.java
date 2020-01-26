////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
//	Class		:	AdminPage
//	Working 	:	This page contains how many numbers of questions & what time set for exam.Also contain password key
//				for student login.This all are set by Admin.
//				Two buttons provided for move to update question page and view student information page.
//	Link with 	:	<-Login(if logout),->UpdateQ,ViewStudent.
//
//
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;

class AdminPage extends JFrame implements ActionListener
{
	public JLabel lab1,lab2;
	public JRadioButton rb1,rb2,rb3;
	public JButton update,studentinf,addq,examsetting,logout;
	public ButtonGroup bg;
	public JLabel ltime,lnoq,lformat,lpass,dbinfo;
	public JTextField ttime,tnoq,tpass;
	public JMenuBar menubar;
	public JMenu menu1,menu2;
	public ImageIcon img;
	public JLabel label;
	public Connection con=null;
	public Statement stmt=null;
	public PreparedStatement pst=null;
	public ResultSet rs=null;	
	public int noq_indb=0;
	public AdminPage()
	{
		setLayout(null);
		
		img=new ImageIcon("photo/Admin3.jpg");
		label=new JLabel(img);
		label.setBounds(0,0,1400,900);
		menubar=new JMenuBar();
		menu1=new JMenu("   HOME");
		menu2=new JMenu("   UPDATE");

		menubar.setLayout(null);
		menu1.setLayout(null);
		menu2.setLayout(null);

		menubar.setBounds(0,0,1400,32);
		menu1.setBounds(0,0,100,28);
		menu2.setBounds(100,0,100,28);
		
		menubar.setBackground(new Color(0,0,0));	
		menu1.setForeground(new Color(255,255,255));
		menu1.setFont(new Font("Arial",Font.BOLD,16));
	
		menu2.setForeground(new Color(255,255,255));
		menu2.setFont(new Font("Arial",Font.PLAIN,15));

		menubar.add(menu1);
		menubar.add(menu2);
		
		dbinfo=new JLabel();
		dbinfo.setBounds(280,300,400,20);			

		update=new JButton("EDIT QUESTION BANK");
		studentinf=new JButton("STUDENT INFORMATION");
		examsetting=new JButton("UPDATE");
		logout=new JButton("LOGOUT");		
		
		update.setBackground(new Color(255,255,255));
		studentinf.setBackground(new Color(255,255,255));
		examsetting.setBackground(new Color(255,255,255));
		logout.setBackground(new Color(255,0,0));
		

		update.setBounds(500,430,220,60);	
		studentinf.setBounds(800,430,220,60);

		examsetting.setBounds(210,340,140,30);
		logout.setBounds(1170,32,120,29);
		//addq.setBounds(750,350,150,30);
		
		ltime=new JLabel("TIME FOR TEST");
		ltime.setBounds(80,100,210,30);
		lnoq=new JLabel("NO OF QUESTIONS FOR TEST");
		lnoq.setBounds(80,180,210,30);
		lpass=new JLabel("PASS KEY FOR STUDENT LOGIN");
		lpass.setBounds(80,260,280,30);

	
		ttime=new JTextField();
		ttime.setBounds(320,100,250,30);
		tnoq=new JTextField();
		tnoq.setBounds(320,180,250,30);
		tpass=new JTextField();
		tpass.setBounds(320,260,250,30);


		lformat=new JLabel("Time should in 'HH:MM:SS'");
		lformat.setForeground(new Color(255,0,0));
		lformat.setBounds(380,132,190,30);
		
		label.add(dbinfo);
		label.add(ltime);
		label.add(lnoq);
		label.add(lpass);
		label.add(ttime);
		label.add(tnoq);
		label.add(tpass);
		label.add(lformat);

		label.add(update);
		label.add(studentinf);
		label.add(examsetting);
		label.add(logout);
		//add(addq);		

		label.add(menubar);
		
		add(label);
		try
		{
			String time="",noq="";

			Class.forName("org.postgresql.Driver");
			con=DriverManager.getConnection("jdbc:postgresql:placement","postgres","Aniket1004");
			stmt=con.createStatement();
			rs=stmt.executeQuery("select * from Questions");
			while (rs.next())
			{
				noq_indb++;
			}	
			con.close();
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		try
		{
			String time="",noq="",key="";

			Class.forName("org.postgresql.Driver");
			con=DriverManager.getConnection("jdbc:postgresql:placement","postgres","Aniket1004");
			stmt=con.createStatement();
			rs=stmt.executeQuery("select * from TestSetting");
			while (rs.next())
			{
				time=rs.getString(2);
				noq=Integer.toString(rs.getInt(3)); 
				key=rs.getString(5);
			}	
			
			ttime.setText(time);
			tnoq.setText(noq);
			tpass.setText(key);
		}
		catch (Exception e)
		{
			System.out.println(e);
		}

		dbinfo.setText("Total number of questions for exam in DB : "+Integer.toString(noq_indb));
		update.addActionListener(this);
		studentinf.addActionListener(this);
		logout.addActionListener(this);
		examsetting.addActionListener(this);
	
		setTitle("ADMIN PAGE");
		setBounds(0,0,1400,900);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);		
		
	}
	public void actionPerformed(ActionEvent ae)
	{
		String str=ae.getActionCommand();
		if (str.equals("EDIT QUESTION BANK"))
		{
			new UpdateQ();
			setVisible(false);
		}
		if (str.equals("STUDENT INFORMATION"))
		{
			new ViewStudent();
			setVisible(false);
		}
		if (str.equals("LOGOUT"))
		{
			new Login();
			setVisible(false);
		}
		if (str.equals("UPDATE"))
		{
			try
			{
			pst=con.prepareStatement("UPDATE TestSetting SET time=?,noq=?,noq_indb=?,key_stud_login=? where id=1");
			pst.setString(1,ttime.getText());
			pst.setInt(2,Integer.parseInt(tnoq.getText()));
			pst.setInt(3,noq_indb);
			pst.setString(4,tpass.getText());
			pst.executeUpdate();
			JOptionPane.showMessageDialog(null,"UPDATE SUCCUSSFULLY");
			}
			catch (Exception e)
			{
				JOptionPane.showMessageDialog(null,e);
			}
		}
	}
	public static void main(String args[])
	{
		new AdminPage();
	}
} 
