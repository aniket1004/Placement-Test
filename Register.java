////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
//	Class		:	Register
//	Working 	: 	It contains fileds where student fill information about itself.
//	Link with 	:	<-Login(if click cancel),->Exam(if click Register);
//
//
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Register extends JFrame implements ActionListener
{
	public ImageIcon img;
	public JLabel wallpaper;
	public JLabel personal,lfname,dob,gender,lemail,lmobile,laadhar,laddress,lcountry,lstate,ldistrict,lcity;
	public JLabel educational,lssc,lsscp,lhsc,lhscp,lgrad,lgradp;
	public JLabel warn;
	public JTextField tfname,temail,tmobile,taadhar,taddress,tstate,tdistrict,tcity;
	public JTextField tssc,tsscp,thsc,thscp,tgrad,tgradp;	
	public JComboBox day,month,year;
	public String d[],m[],y[];
	public JRadioButton male,female;
	public ButtonGroup bg;
	public JButton register,cancel;
	public Connection con=null;
	public PreparedStatement pst=null;
	public String emailRegex="^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
	public String mobileRegex="(0/91)?[7-9][0-9]{9}";
	public String percentRegex="\\d+(?:\\.\\d+)?";
	public String username="";

	public Register()
	{}
	public Register(String username)
	{
		this.username=username;

		setLayout(null);

		img=new ImageIcon("photo/c.jpg");

		wallpaper=new JLabel(img);
		wallpaper.setBounds(0,0,1400,900);

		personal=new JLabel("Personal Information");
		personal.setLayout(null);
		personal.setBounds(230,25,280,25);


		lfname=new JLabel("Full name");
		lfname.setBounds(80,100,80,25);

		tfname=new JTextField(); 
		tfname.setBounds(230,100,305,25);

		dob=new JLabel("Date of Birth");
		dob.setBounds(80,150,140,25);

		d=new String[] {"Day","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
		
		day=new JComboBox(d);
		day.setBounds(230,150,80,25);
		
		m=new String[] {"Month","JANUARY","FEBRUARY","MARCH","APRIL","MAY","JUNE","JULY","AUGUST","SEPTEMBER","OCTOBER","NOVEMBER","DECEMBER"};

		month=new JComboBox(m);
		month.setBounds(330,150,105,25);

		y=new String[] {"Year","1981","1982","1983","1984","1985","1986","1987","1988","1989","1990","1991","1992","1993","1994","1995","1996","1997","1998","1999","2000","2001","2002","2003","2004","2005","2006","2007","2008","2009"};

		year=new JComboBox(y);
		year.setBounds(455,150,80,25);

		gender=new JLabel("Gender");
		gender.setBounds(80,200,80,25);

		male=new JRadioButton("Male");
		male.setBounds(230,200,130,25);
		male.setBackground(new Color(255,178,102));

		female=new JRadioButton("Female");
		female.setBounds(380,200,150,25);
		female.setBackground(new Color(255,178,102));

		bg=new ButtonGroup();
		bg.add(male);
		bg.add(female);

		lemail=new JLabel("Email");
		lemail.setBounds(80,250,150,25);

		temail=new JTextField();
		temail.setBounds(230,250,305,25);

		lmobile=new JLabel("Mobile ");
		lmobile.setBounds(80,300,150,25);

		tmobile=new JTextField();
		tmobile.setBounds(230,300,305,25);

		laadhar=new JLabel("Aadhar no.");
		laadhar.setBounds(80,350,150,25);

		taadhar=new JTextField();
		taadhar.setBounds(230,350,305,25);

		laddress=new JLabel("address");
		laddress.setBounds(80,400,150,25);

		taddress=new JTextField();
		taddress.setBounds(230,400,305,25);
		
		lstate=new JLabel("State");
		lstate.setBounds(80,450,150,25);

		tstate=new JTextField();
		tstate.setBounds(230,450,305,25);
	
		ldistrict=new JLabel("District");
		ldistrict.setBounds(80,500,150,25);

		tdistrict=new JTextField();
		tdistrict.setBounds(230,500,305,25);

		lcity=new JLabel("City");
		lcity.setBounds(80,550,150,25);

		tcity=new JTextField();
		tcity.setBounds(230,550,305,25);	

		educational=new JLabel("Educational Information");
		educational.setLayout(null);
		educational.setBounds(840,25,280,25);

		lssc=new JLabel("SSC (Percentage)");
		lssc.setBounds(670,100,150,25);

		tssc=new JTextField();
		tssc.setBounds(870,100,205,25);

		lsscp=new JLabel("SSC (Passing Year)");
		lsscp.setBounds(670,150,150,25);

		tsscp=new JTextField();
		tsscp.setBounds(870,150,205,25);

		lhsc=new JLabel("HSC (Percentage)");
		lhsc.setBounds(670,200,150,25);

		thsc=new JTextField();
		thsc.setBounds(870,200,205,25);

		lhscp=new JLabel("HSC (Passing Year)");
		lhscp.setBounds(670,250,150,25);

		thscp=new JTextField();
		thscp.setBounds(870,250,205,25);

		lgrad=new JLabel("Graduation (Percentage)");
		lgrad.setBounds(670,300,180,25);

		tgrad=new JTextField();
		tgrad.setBounds(870,300,205,25);

		lgradp=new JLabel("Graduation (Passing Year)");
		lgradp.setBounds(670,350,190,25);

		tgradp=new JTextField();
		tgradp.setBounds(870,350,205,25);
		
		warn=new JLabel("All fields are mandatory");
		warn.setForeground(Color.red);
		warn.setFont(new Font("Arial",Font.PLAIN|Font.BOLD,15));
		warn.setBounds(830,450,205,35);

		register=new JButton("REGISTER");
		register.setBounds(680,550,130,30);
		register.setBackground(Color.green);

		cancel=new JButton("CANCEL");
		cancel.setBounds(910,550,130,30);
		cancel.setBackground(Color.red);

		wallpaper.add(personal);
		wallpaper.add(lfname);
		wallpaper.add(tfname);
		wallpaper.add(dob);
		wallpaper.add(day);		
		wallpaper.add(month);
		wallpaper.add(year);
		wallpaper.add(gender);
		wallpaper.add(male);
		wallpaper.add(female);
		wallpaper.add(lemail);
		wallpaper.add(temail);
		wallpaper.add(lmobile);
		wallpaper.add(tmobile);
		wallpaper.add(laadhar);
		wallpaper.add(taadhar);
		wallpaper.add(laddress);
		wallpaper.add(taddress);
		wallpaper.add(lstate);
		wallpaper.add(tstate);
		wallpaper.add(ldistrict);
		wallpaper.add(tdistrict);
		wallpaper.add(lcity);
		wallpaper.add(tcity);

		wallpaper.add(educational);
		wallpaper.add(lssc);
		wallpaper.add(tssc);
		wallpaper.add(lsscp);
		wallpaper.add(tsscp);
		wallpaper.add(lhsc);
		wallpaper.add(thsc);
		wallpaper.add(lhscp);
		wallpaper.add(thscp);
		wallpaper.add(lgrad);
		wallpaper.add(tgrad);
		wallpaper.add(lgradp);
		wallpaper.add(tgradp);
		wallpaper.add(warn);
		wallpaper.add(register);
		wallpaper.add(cancel);

		add(wallpaper);

		register.addActionListener(this);
		cancel.addActionListener(this);
		
		setTitle("REGISTRATION");
		setBounds(0,0,1400,800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	public boolean isValidEmail(String email)
	{
		Pattern pat=Pattern.compile(emailRegex);
		
		return pat.matcher(email).matches();
	}
	public boolean isValidMobile(String mobile)
	{
		Pattern pat=Pattern.compile(mobileRegex);
		Matcher m=pat.matcher(mobile);
		return (m.find() && m.group().equals(mobile));
	}
	public boolean isValidPercent(String percent)
	{
		Pattern pat=Pattern.compile(percentRegex);
		Matcher m=pat.matcher(percent);
		return (m.find());
	}
	public void actionPerformed(ActionEvent ae)
	{
		boolean a,b,c,d,e;
		String str=ae.getActionCommand();
		
		
		if (str.equals("REGISTER"))
		{
			String fname=tfname.getText().toString(),email=temail.getText().toString(),mobile=tmobile.getText().toString();
			String aadhar=taadhar.getText().toString(),address=taddress.getText().toString(),state=tstate.getText().toString();
			String district=tdistrict.getText().toString(),city=tcity.getText().toString();
			String ssc=tssc.getText().toString(),sscp=tsscp.getText().toString(),hsc=thsc.getText().toString();
			String hscp=thscp.getText().toString(),grad=tgrad.getText().toString(),gradp=tgradp.getText().toString();
			String days=day.getSelectedItem().toString(),months=month.getSelectedItem().toString();
			String years=year.getSelectedItem().toString();

			if (fname.equals(""))
			{
				JOptionPane.showMessageDialog(null,"Name should be mandatory");
			}
			else if (email.equals(""))
			{
				JOptionPane.showMessageDialog(null,"Email should be mandatory");
			}
			else if (mobile.equals(""))
			{
				JOptionPane.showMessageDialog(null,"Mobile number should be mandatory");
			}
			else if (aadhar.equals(""))
			{
				JOptionPane.showMessageDialog(null,"Aadhar number should be mandatory");
			}
			else if (address.equals(""))
			{
				JOptionPane.showMessageDialog(null,"address should be mandatory");
			}
			else if (state.equals(""))
			{
				JOptionPane.showMessageDialog(null,"State should be mandatory");
			}
			else if (district.equals(""))
			{
				JOptionPane.showMessageDialog(null,"District should be mandatory");
			}
			else if (city.equals(""))
			{
				JOptionPane.showMessageDialog(null,"City should be mandatory");
			}
			else if (ssc.equals(""))
			{
				JOptionPane.showMessageDialog(null,"SSC percentage should be mandatory");
			}
			else if (sscp.equals(""))
			{
				JOptionPane.showMessageDialog(null,"SSC passing year should be mandatory");
			}
			else if (hsc.equals(""))
			{
				JOptionPane.showMessageDialog(null,"HSC percentage should be mandatory");
			}
			else if (hscp.equals(""))
			{
				JOptionPane.showMessageDialog(null,"HSC passing year should be mandatory");
			}
			else if (grad.equals(""))
			{
				JOptionPane.showMessageDialog(null,"Graduation percentage should be mandatory");
			}
			else if (gradp.equals(""))
			{
				JOptionPane.showMessageDialog(null,"Graduation passing year should be mandatory");
			}
			else if (days.equals("Day"))
			{
				JOptionPane.showMessageDialog(null,"Select valid birth date (CHECK DAY)");
			}
			else if (months.equals("Month"))
			{
				JOptionPane.showMessageDialog(null,"Select valid birth date (CHECK MONTH)");
			}
			else if (years.equals("Year"))
			{
				JOptionPane.showMessageDialog(null,"Select valid birth date (CHECK YEAR)");
			}
			else if (bg.getSelection()==null)
			{
				JOptionPane.showMessageDialog(null,"Select gender");
			}
			else if (!isValidEmail(email))
			{
				JOptionPane.showMessageDialog(null,"Invalid email id");
			}
			else if (!isValidMobile(mobile))
			{
				JOptionPane.showMessageDialog(null,"Invalid mobile number");
			}
			else if (!isValidPercent(ssc))
			{
				JOptionPane.showMessageDialog(null,"Enter valid percentage of SSC ");
			}
			else if (!isValidPercent(hsc))
			{
				JOptionPane.showMessageDialog(null,"Enter valid percentage HSC");
			}
			else if (!isValidPercent(grad))
			{
				JOptionPane.showMessageDialog(null,"Enter valid percentage of graduation");
			}			
			else
			{
				try
				{
					
					Class.forName("org.postgresql.Driver");
					con=DriverManager.getConnection("jdbc:postgresql:placement","postgres","Aniket1004");
					
					pst=con.prepareStatement("Insert into Register values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
					pst.setString(1,username);
					pst.setString(2,fname);
					pst.setString(3,email);
					pst.setString(4,mobile);
					String birthdate=(days+"/"+months+"/"+years);
					pst.setString(5,birthdate);
					if (male.isSelected())
						pst.setString(6,male.getText());
					else if (female.isSelected())
						pst.setString(6,female.getText());
					pst.setString(7,aadhar);
					pst.setString(8,address);
					pst.setString(9,state);
					pst.setString(10,district);
					pst.setString(11,city);
					pst.setString(12,ssc);
					pst.setString(13,sscp);
					pst.setString(14,hsc);
					pst.setString(15,hscp);
					pst.setString(16,grad);
					pst.setString(17,gradp);

					pst.execute();
					con.close();
					
					new Exam(username);
					setVisible(false);
				}
				catch (Exception ex){System.out.println(ex);}
			}
		}
		else if (str.equals("CANCEL"))
		{
			new Login();
			setVisible(false);
		}		
		
			
	}

	public static void main(String args[])	
	{
		new Register("hi");
	}
}
