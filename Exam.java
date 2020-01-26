////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
//	Class		:	Exam
//	Working 	:	It contains fields which contains question and its 4 options.Once he/she started exam timer will be move 
//				reverse order.Student select one of this choice and move next and also move to back(if needed).
//				If he/she move final question and click on submit button then result page will be display. 
//	Link with 	:	<-Login(if click cancel & not click on start),->Result(if click on submit);
//
//
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.NumberFormat;
import java.text.DecimalFormat;
import java.util.Random;

public class Exam extends JFrame implements ActionListener,Runnable
{
	public JTextArea ja;
	public JTextField op1,op2,op3,op4,searchbox;
	public JButton next,prev,update,start,cancel;
	public JLabel bg,header,Q,A,B,C,D,disp,qno;
	public ImageIcon img;
	public JRadioButton ch1,ch2,ch3,ch4;
	public ButtonGroup g;
	public int i,j,k,l,random;
	public Connection con=null;
	public Statement stmt=null;
	public ResultSet rs=null;
	public static int count,current=0;
	public int noq=0,marks=0,limit=0,examtime=0;
	public String ans[],correct[],username;
	Thread t;

	static
	{
		count=0;
	}	
	public Exam(){}
	public Exam(String username)
	{
		t=new Thread(this);
		this.username=username;
		img=new ImageIcon("photo/b.jpg");
		bg=new JLabel(img);
		bg.setLayout(null);
		bg.setBounds(-40,-65,1400,900);

		ja=new JTextArea();
		ja.setLayout(null);
		ja.setFont(new Font("Arial",Font.PLAIN,15));
		
		op1=new JTextField();
		op1.setLayout(null);
		op1.setBounds(300,350,600,30);
		op1.setFont(new Font("Arial",Font.PLAIN,15));
		
		op2=new JTextField();
		op2.setLayout(null);
		op2.setBounds(300,400,600,30);
		op2.setFont(new Font("Arial",Font.PLAIN,15));
		
		op3=new JTextField();
		op3.setLayout(null);
		op3.setBounds(300,450,600,30);
		op3.setFont(new Font("Arial",Font.PLAIN,15));
		
		op4=new JTextField();
		op4.setLayout(null);
		op4.setBounds(300,500,600,30);
		op4.setFont(new Font("Arial",Font.PLAIN,15));
		
		Q=new JLabel("Q.");
		Q.setLayout(null);
		Q.setBounds(180,195,50,30);
		Q.setFont(new Font("Arial",Font.PLAIN|Font.BOLD,18));

		A=new JLabel("A.");
		A.setLayout(null);
		A.setBounds(255,350,50,30);
		A.setFont(new Font("Arial",Font.PLAIN|Font.BOLD,15));
	
		B=new JLabel("B.");
		B.setLayout(null);
		B.setBounds(255,400,50,30);
		B.setFont(new Font("Arial",Font.PLAIN|Font.BOLD,15));

		C=new JLabel("C.");
		C.setLayout(null);
		C.setBounds(255,450,50,30);
		C.setFont(new Font("Arial",Font.PLAIN|Font.BOLD,15));

		D=new JLabel("D.");
		D.setLayout(null);
		D.setBounds(255,500,50,30);
		D.setFont(new Font("Arial",Font.PLAIN|Font.BOLD,15));

		prev=new JButton("<<  PREVIOUS");
		prev.setLayout(null);
		prev.setBounds(150,600,135,25);
		prev.setBackground(new Color(0,0,0));
		prev.setForeground(new Color(255,255,255));

		next=new JButton("NEXT  >>");
		next.setLayout(null);
		next.setBounds(375,600,120,25);
		next.setBackground(new Color(0,0,0));
		next.setForeground(new Color(255,255,255));

		start=new JButton("START");
		start.setLayout(null);
		start.setBounds(795,600,250,35);
		start.setBackground(new Color(0,0,0));
		start.setForeground(new Color(255,255,255));

		cancel=new JButton("CANCEL");
		cancel.setLayout(null);
		cancel.setBounds(585,600,120,25);
		cancel.setBackground(new Color(0,0,0));
		cancel.setForeground(new Color(255,255,255));


		disp=new JLabel();
  		disp.setFont(new Font("Helvetica",Font.PLAIN,20));
  		disp.setBackground(Color.cyan);
  		disp.setForeground(new Color(204,0,0));
  		disp.setLayout(null);
  		disp.setBounds(150,50,200,100);

		qno=new JLabel();
  		qno.setFont(new Font("Helvetica",Font.PLAIN,20));
  		qno.setForeground(new Color(204,0,0));
  		qno.setLayout(null);
  		qno.setBounds(1100,50,300,100);

		ch1=new JRadioButton("A");
		ch1.setLayout(null);
		ch1.setBounds(200,358,20,15);
		ch1.setBackground(new Color(255,255,153));

		ch2=new JRadioButton("B");
		ch2.setLayout(null);
		ch2.setBounds(200,408,20,15);
		ch2.setBackground(new Color(255,255,153));	

		ch3=new JRadioButton("C");
		ch3.setLayout(null);
		ch3.setBounds(200,458,20,15);
		ch3.setBackground(new Color(255,255,153));
		
		ch4=new JRadioButton("D");
		ch4.setLayout(null);
		ch4.setBounds(200,508,20,15);
		ch4.setBackground(new Color(255,255,153));

		g=new ButtonGroup();	
		g.add(ch1);
		g.add(ch2);
		g.add(ch3);
		g.add(ch4);


		try
		{
			String time[]=new String[] {"0","1","2"};
			Class.forName("org.postgresql.Driver");
			con=DriverManager.getConnection("jdbc:postgresql:placement","postgres","Aniket1004");
			stmt=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			rs=stmt.executeQuery("Select * from TestSetting");
			while (rs.next())
			{
				noq=rs.getInt(3);
				limit=(rs.getInt(4))-noq;
				time=(rs.getString(2)).split(":");
			}
			examtime=(Integer.parseInt(time[0])*60)+Integer.parseInt(time[1]);
			System.out.println(examtime+"exam");
			con.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}



		ans=new String[noq];
		correct=new String[noq];
		for(int i=0;i<noq;i++)
		{
			ans[i]="-";
			correct[i]="-";
		}

		ja.setEditable(false);
		JScrollPane pane=new JScrollPane(ja);
		pane.setBounds(250,200,900,80);
		
		op1.setEditable(false);
		op2.setEditable(false);
		op3.setEditable(false);
		op4.setEditable(false);

		bg.add(qno);
		bg.add(disp);
		bg.add(pane); //TextArea
		bg.add(op1);
		bg.add(op2);
		bg.add(op3);
		bg.add(op4);
		bg.add(ch1);
		bg.add(ch2);
		bg.add(ch3);
		bg.add(ch4);
		bg.add(Q);
		bg.add(A);
		bg.add(B);
		bg.add(C);
		bg.add(D);
		bg.add(prev);
		bg.add(next);
		bg.add(start);
		bg.add(cancel);

		


		try
		{
			Class.forName("org.postgresql.Driver");
			con=DriverManager.getConnection("jdbc:postgresql:placement","postgres","Aniket1004");
			stmt=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			rs=stmt.executeQuery("Select * from Questions");
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	

		add(bg);
		
		start.addActionListener(this);
		prev.addActionListener(this);
		next.addActionListener(this);

		ch1.addActionListener(this);
		ch2.addActionListener(this);
		ch3.addActionListener(this);
		ch4.addActionListener(this);

		setLayout(null);
		setTitle("TEST");
		setBounds(0,0,1400,900);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void DisplayQ()
	{
		try
		{
			if (ans[count-1].trim().equals("A"))
			{	
				g.setSelected(ch1.getModel(),true);
			}
			else if (ans[count-1].trim().equals("B"))
			{	
				g.setSelected(ch2.getModel(),true);
			}
			else if (ans[count-1].trim().equals("C"))
			{	
				g.setSelected(ch3.getModel(),true);
			}
			else if (ans[count-1].trim().equals("D"))
			{	
				g.setSelected(ch4.getModel(),true);
			}
			else
			{
				g.clearSelection();
			}
			qno.setText(count+"/"+noq);
 	
			if(rs.next())
			{
			correct[count-1]=rs.getString(7);
			ja.setText(rs.getString(2));
			op1.setText(rs.getString(3));
			op2.setText(rs.getString(4));
			op3.setText(rs.getString(5));
			op4.setText(rs.getString(6));
			}
			
			
		}
			catch (Exception e)
			{
				JOptionPane.showMessageDialog(null,e);
				
			}
	}
	public void DisplayPreviousQ()
	{
		try
		{
			
			if (ans[count-1].trim().equals("A"))
			{	
				g.setSelected(ch1.getModel(),true);
			}
			else if (ans[count-1].trim().equals("B"))
			{	
				g.setSelected(ch2.getModel(),true);
			}
			else if (ans[count-1].trim().equals("C"))
			{	
				g.setSelected(ch3.getModel(),true);
			}
			else if (ans[count-1].trim().equals("D"))
			{	
				g.setSelected(ch4.getModel(),true);
			}
			else
			{
				g.clearSelection();
			}
			qno.setText(count+"/"+noq);
	
			if(rs.previous())
			{
			ja.setText(rs.getString(2));
			op1.setText(rs.getString(3));
			op2.setText(rs.getString(4));
			op3.setText(rs.getString(5));
			op4.setText(rs.getString(6));
			}
			
			
		}
			catch (Exception e)
			{
				JOptionPane.showMessageDialog(null,e);
			}
	}
	public void run()
 	{
		
  		 	for(j=examtime-1;j>=0;j--)
   			{
    				for(k=59;k>=0;k--)
    				{
     					for(l=100;l>=0;l--)
     					{
      						
      						NumberFormat nf = new DecimalFormat("00");
      						disp.setText(nf.format(i)+":"+nf.format(j)+":"+nf.format(k)+":"+nf.format(l));
      						try
						{
       							Thread.sleep(10);
      						}catch(Exception e){}
     					}
    				}
   			}
				
 	}
	public void actionPerformed(ActionEvent ae)
	{
		String str=ae.getActionCommand();
		
		Random randomGenerator=new Random();
		
  		if(str.equals("START"))
  		{
   			t.start();
   			start.setText("BEST OF LUCK");
			random=randomGenerator.nextInt(limit);
			System.out.println("Random"+random);
			while(current<random-1)
			{
				
				current++;
				try
				{
					rs.next();
				}
				catch(Exception e){JOptionPane.showMessageDialog(null,e);}
			}
			
			count++;
			DisplayQ();
  		}
		else if (!t.isAlive())
		{
			new Result(username,correct,ans,random,noq);
			setVisible(false);		
		}
		else if ((str.equals("NEXT  >>"))&&(start.getText().equals("BEST OF LUCK")))
		{
							
			if (count<noq)
			{
				
				count++;
				DisplayQ();
				
			}
			if(count==noq)
			{
				start.setText("SUBMIT");
			}
		
		}
		else if ((str.equals("<<  PREVIOUS"))&&((start.getText().equals("BEST OF LUCK"))||(start.getText().equals("SUBMIT"))))
		{
			
			
			if ((count<=noq)&&(count>1))
			{	
				
				count--;
				DisplayPreviousQ();
				if(start.getText().equals("SUBMIT"))
				{
				start.setText("BEST OF LUCK");
				}	
			}
			
			
			
		}
		else if((str.equals("SUBMIT")))
		{
			
			try
			{
				con.close();
				new Result(username,correct,ans,random,noq);
				setVisible(false);
			}
			catch(Exception e){System.out.println(e);}
			//new Result(correct,ans,random,noq);
		}

			
			
		if (ch1.isSelected())
		{
			ans[count-1]="A";
		}
		if (ch2.isSelected())
		{
			ans[count-1]="B";
		}
		if (ch3.isSelected())
		{
			ans[count-1]="C";
		}
		if (ch4.isSelected())
		{
			ans[count-1]="D";
		}		
	}
	
	public static void main(String args[])
	{
		new Exam("hi");
	}
}
 
