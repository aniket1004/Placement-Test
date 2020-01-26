////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
//	Class		:	Login
//	Working 	:	It contains username and password field.If student or admin fill incorrect information thrice then 
//				system exit automatically else it moves to Exam or AdminPage.
//				Here for student login there is password key which is provided by admin and set that in AdminPage.
//				That key considered as student password.
//	Link with 	:	<-HomePage(if click cancel),->Exam,AdminPage. 
//
//
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener
{
	public JLabel login,user,nuser,pass,confirm,image1,image2;
	public JTextField username,cpassword;
	public JPasswordField password;
	public JButton submit,register,cancel;
	public JLabel warn,welcome;
	//public JRadioButton admin,student;
	JPanel jp1;
	ButtonGroup bg;
	Connection con;
	Statement stmt;
	PreparedStatement pst;
	ResultSet rs;
	public int countS=0,countA=0;
	public Login()
	{
		setLayout(null);

		jp1=new JPanel();
		jp1.setLayout(null);
		login=new JLabel("LOGIN");
		user=new JLabel("USERNAME");
		pass=new JLabel("PASSWORD");
		warn=new JLabel("You have only 3 attempts...");
		welcome=new JLabel("Welcome");
		username=new JTextField();
		password=new JPasswordField(15);
		cpassword=new JTextField(15);
		
		submit=new JButton("SUBMIT");
		register=new JButton("REGISTER");

		login.setLayout(null);
		login.setFont(new Font("Serif",Font.BOLD|Font.ITALIC,25));
		login.setBounds(145,5,100,100);	
		
		user.setLayout(null);
		user.setFont(new Font("Arial",Font.ITALIC,17));
		user.setBounds(60,90,130,100);

		pass.setLayout(null);
		pass.setFont(new Font("Arial",Font.ITALIC,17));
		pass.setBounds(60,140,100,100);	

		warn.setFont(new Font("Arial",Font.PLAIN|Font.BOLD,17));
		warn.setBounds(850,40,260,30);
		
		welcome.setFont(new Font("serif",Font.PLAIN,32));
		welcome.setBounds(501,100,430,47);
		welcome.setForeground(new Color(0,0,0));
		welcome.setBackground(new Color(255,255,255));

		username.setLayout(null);
		username.setBounds(200,130,150,25);	
		
		password.setLayout(null);
		password.setBounds(200,180,150,25);
		

		submit=new JButton("Login");
		submit.setLayout(null);
		submit.setBackground(Color.GREEN);
		submit.setBounds(90,255,100,25);

		cancel=new JButton("cancel");
		cancel.setLayout(null);
		cancel.setBackground(Color.red);
		cancel.setBounds(230,255,100,25);

	/*	student=new JRadioButton("Student");
		student.setBounds(140,100,100,25);

		admin=new JRadioButton("Admin");
		admin.setBounds(270,100,100,25);
	
		bg=new ButtonGroup();
		bg.add(student);
		bg.add(admin);
	*/
		ImageIcon img =new ImageIcon("photo/d.jpg");
		image1=new JLabel(img);
		image1.setBounds(0,0,1370,700);
		
		ImageIcon img1 =new ImageIcon("photo/a.jpg");
		image2=new JLabel(img1);
 		image2.setBounds(0,0,430,450);
		
		image2.add(login);
		image2.add(user);
		image2.add(pass);
		image2.add(username);
		image2.add(password);
		image2.add(submit);
		
		image2.add(cancel);
		
		jp1.add(image2);
		jp1.setBounds(501,147,430,450);
		image1.add(warn);
		image1.add(welcome);		
		image1.add(jp1);
		
	//	add(admin);
	//	add(student);

		add(image1);
		submit.addActionListener(this);
		cancel.addActionListener(this);
		register.addActionListener(this);
		
		try
		{
			Class.forName("org.postgresql.Driver");
			con=DriverManager.getConnection("jdbc:postgresql:placement","postgres","Aniket1004");
			stmt=con.createStatement();		
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null,e);
		}
		
		setBounds(0,0,1400,700);
		setTitle("Login");
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void actionPerformed(ActionEvent ae)
	{
		String str=ae.getActionCommand();
		
		try
		{
		if (str.equals("Login"))
		{
			
			rs=stmt.executeQuery("Select * from Register");
			if ((username.getText().equals("")) && (password.getText().equals("")))
			{
				JOptionPane.showMessageDialog(null,"Enter username and password");
			}
			else if ((username.getText().equals("")))
			{
				JOptionPane.showMessageDialog(null,"Enter username");
			}
			else if ((password.getText().equals("")))
			{
				JOptionPane.showMessageDialog(null,"Enter password");
			}
			else
			{
				int flag=0;
						while(rs.next())
						{
							if(rs.getString(1).equals(username.getText()))
							{
								flag=1;
							}
						}
						
					if (username.getText().equals("Aniket"))
					{
						if (password.getText().equals("Aniket1004"))
						{						
							con.close();
							new AdminPage();
							setVisible(false);
						}
						else 
						{
							
							countA++;
							int attempt=3-countA;
							if (attempt==0)
							{
								JOptionPane.showMessageDialog(null,"TOO MANY LOGIN ATTEMPTS. PLEASE TRY LATER");
								System.exit(0);
							}
							String warn=(Integer.toString(attempt))+" attempt remaining";
							JOptionPane.showMessageDialog(null,"Invalid username or password",warn,JOptionPane.ERROR_MESSAGE);
						
						}
							
					}
					else
					{
						if (flag==1)
						{
							
							JOptionPane.showMessageDialog(null,"Username already exist enter another username");
						}
						else
						{
						String key="";
						//stmt=con.createStatement();
						rs=stmt.executeQuery("Select * from TestSetting");
						while(rs.next())
						{
							key=rs.getString(5);
						}
						if (key.equals(password.getText()))
						{
							pst=con.prepareStatement("Insert into student_login values(?,?)");
							pst.setString(1,username.getText());
							pst.setString(2,password.getText());
							pst.execute();
							con.close();
											
							new Register(username.getText());
							setVisible(false);
						}
						else	
						{
							countS++;
							int attempt=3-countS;
							if (attempt==0)
							{
								JOptionPane.showMessageDialog(null,"TOO MANY LOGIN ATTEMPTS. PLEASE TRY LATER");
								System.exit(0);
							}
							String warn=(Integer.toString(attempt))+" attempt remaining";
							JOptionPane.showMessageDialog(null,"Invalid username or pass key",warn,JOptionPane.ERROR_MESSAGE);
						}
						}
						
						
					}
			}
		}
		
		if (str.equals("cancel"))
		{
			con.close();
			new HomePage();
			setVisible(false);
		}
	}
	catch (Exception e)
	{
		JOptionPane.showMessageDialog(null,e);			
	}
		
}
	public static void main(String arg[])
	{
		new Login();
	}

}
