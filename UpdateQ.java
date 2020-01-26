////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
//	Class		:	UpdateQ
//	Working 	:	It contains fields which contains question,four options & correct option.
//				Some buttons are update,delete,add,next,previous,,refresh,cancel,logout.
//				Using those buttons we can edit questions for exam from database and also its options.
//	Link with 	:	<-AdminPage(if click cancel),<-Login(if click logout); 
//
//
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class UpdateQ extends JFrame implements ActionListener
{
	public JTextArea ja;
	public JTextField op1,op2,op3,op4,searchbox,corans;
	public JButton next,prev,newq,update,delete,cancel,searchbtn,logout,refre,clean;
	public JLabel bg,header,Q,A,B,C,D,correct;
	public ImageIcon img;
	public Connection conn=null,conn1=null;
	public Statement s=null,s1=null;
	public PreparedStatement pst=null,pst1=null;
	public ResultSet rs=null,rs1=null;
	public static int rows=0,current=0;
	public UpdateQ()
	{
		img=new ImageIcon("photo/images.jpeg");
		bg=new JLabel(img);
		bg.setLayout(null);
		bg.setBounds(0,0,1400,900);

		ja=new JTextArea("PRESS NEXT BUTTON FOR START VIEW QUESTIONS ");
		//ja.setLayout(null);
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
		A.setBounds(250,350,50,30);
		A.setFont(new Font("Arial",Font.PLAIN|Font.BOLD,15));
	
		B=new JLabel("B.");
		B.setLayout(null);
		B.setBounds(250,400,50,30);
		B.setFont(new Font("Arial",Font.PLAIN|Font.BOLD,15));

		C=new JLabel("C.");
		C.setLayout(null);
		C.setBounds(250,450,50,30);
		C.setFont(new Font("Arial",Font.PLAIN|Font.BOLD,15));

		D=new JLabel("D.");
		D.setLayout(null);
		D.setBounds(250,500,50,30);
		D.setFont(new Font("Arial",Font.PLAIN|Font.BOLD,15));

		correct=new JLabel("ANSWER");
		correct.setLayout(null);
		correct.setBounds(200,550,140,30);
		correct.setFont(new Font("Arial",Font.PLAIN|Font.BOLD,15));

		corans=new JTextField();
		corans.setLayout(null);
		corans.setBounds(300,550,600,30);
		corans.setFont(new Font("Arial",Font.PLAIN,15));


		prev=new JButton("<<  PREVIOUS");
		prev.setLayout(null);
		prev.setBounds(50,600,140,25);
		prev.setBackground(new Color(0,0,0));
		prev.setForeground(new Color(255,255,255));

		next=new JButton("NEXT  >>");
		next.setLayout(null);
		next.setBounds(290,600,120,25);
		next.setBackground(new Color(0,0,0));
		next.setForeground(new Color(255,255,255));

		newq=new JButton("NEW");
		newq.setLayout(null);
		newq.setBounds(510,600,100,25);
		newq.setBackground(new Color(0,0,0));
		newq.setForeground(new Color(255,255,255));

		update=new JButton("UPDATE");
		update.setLayout(null);
		update.setBounds(710,600,100,25);
		update.setBackground(new Color(0,0,0));
		update.setForeground(new Color(255,255,255));

		delete=new JButton("DELETE");
		delete.setLayout(null);
		delete.setBounds(910,600,100,25);
		delete.setBackground(new Color(0,0,0));
		delete.setForeground(new Color(255,255,255));

		cancel=new JButton("CANCEL");
		cancel.setLayout(null);
		cancel.setBounds(1110,600,100,25);
		cancel.setBackground(new Color(0,0,0));
		cancel.setForeground(new Color(255,255,255));
		
		/*searchbox=new JTextField("Enter question number");
		searchbox.setLayout(null);
		searchbox.setBounds(980,120,200,30);

		searchbtn=new JButton("SEARCH");
		searchbtn.setLayout(null);
		searchbtn.setBounds(1190,120,100,30);
		searchbtn.setBackground(new Color(51,255,51));
		searchbtn.setForeground(new Color(0,0,0));*/

		logout=new JButton("LOGOUT");
		logout.setLayout(null);
		logout.setBounds(1130,35,130,30);
		logout.setFont(new Font("Arial",Font.BOLD,16));
		logout.setBackground(new Color(255,0,0));
		logout.setForeground(new Color(255,255,255));

		refre=new JButton("REFRESH");
		refre.setBounds(100,80,130,30);
		refre.setFont(new Font("Arial",Font.BOLD,16));
		refre.setBackground(new Color(255,0,0));
		refre.setForeground(new Color(255,255,255));
		
		clean=new JButton("CLEAR");
		clean.setBounds(1100,500,130,30);
		clean.setFont(new Font("Arial",Font.BOLD,16));
		clean.setBackground(new Color(255,0,0));
		clean.setForeground(new Color(255,255,255));

		
		JScrollPane pane=new JScrollPane(ja);
		//ja.setBounds(250,200,900,80);
		//pane.setPreferredSize(new Dimension(900,80));
		pane.setBounds(250,200,900,80);		

		bg.add(pane);
		bg.add(op1);
		bg.add(op2);
		bg.add(op3);
		bg.add(op4);
		bg.add(Q);
		bg.add(A);
		bg.add(B);
		bg.add(C);
		bg.add(D);
		bg.add(correct);
		bg.add(corans);
		bg.add(prev);
		bg.add(next);
		bg.add(newq);
		bg.add(update);
		bg.add(delete);
		bg.add(cancel);
		bg.add(refre);
		bg.add(clean);
		//bg.add(searchbox);
		//bg.add(searchbtn);
		bg.add(logout);
		
		add(bg);
		
		next.addActionListener(this);
		prev.addActionListener(this);		
		update.addActionListener(this);
		delete.addActionListener(this);
		newq.addActionListener(this);
		cancel.addActionListener(this);
		logout.addActionListener(this);
		refre.addActionListener(this);
		clean.addActionListener(this);

		setLayout(null);
		setBounds(0,0,1400,900);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		try{
			Class.forName("org.postgresql.Driver");
			conn=DriverManager.getConnection("jdbc:postgresql:placement","postgres","Aniket1004");
			s=conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			rs=s.executeQuery("select *from Questions");
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null,e);
			System.exit(0);
		}
	}	
	public void clearRow()
	{
		ja.setText("");
		op1.setText("");
		op2.setText("");
		op3.setText("");
		op4.setText("");
		corans.setText("");
	}
	public void showRow()throws Exception
	{
		ja.setText(rs.getString(2));
		op1.setText(rs.getString(3));
		op2.setText(rs.getString(4));
		op3.setText(rs.getString(5));
		op4.setText(rs.getString(6));
		corans.setText(rs.getString(7));
	}
	public void refresh()
	{
		try
		{
			s=conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			rs=s.executeQuery("select *from Questions");
			JOptionPane.showMessageDialog(null,"Database refresh succussfully");
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null,e);

			System.exit(0);
		}
	}
	public void actionPerformed(ActionEvent ae)
	{
		if (ae.getActionCommand().equals("REFRESH"))
		{
			refresh();
			clearRow();
		}
		else if (ae.getActionCommand().equals("CLEAR"))
		{
			clearRow();
		}
		else if (ae.getActionCommand().equals("NEXT  >>"))
		{
			try 
			{
				if (rs.next())
				{
					showRow();
				}
				else
				{
					JOptionPane.showMessageDialog(null,"END");
				}
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(null,e);
			}
		}
		else if (ae.getActionCommand().equals("<<  PREVIOUS"))
		{
			try 
			{
				if (rs.previous())
				{
					showRow();
				}
				else
				{
					JOptionPane.showMessageDialog(null,"Enter NEXT to move");
				}			
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(null,e);
			}
		}
		else if (ae.getActionCommand().equals("UPDATE"))
		{ 
			try 
			{
				String q=ja.getText();
				String ch1=op1.getText();
				String ch2=op2.getText();
				String ch3=op3.getText();
				String ch4=op4.getText();
				String ans=corans.getText();
				pst=conn.prepareStatement("UPDATE Questions set question=?,op1=?,op2=?,op3=?,op4=?,correct=? where id=?");
				pst.setString(1,q);
				pst.setString(2,ch1);
				pst.setString(3,ch2);
				pst.setString(4,ch3);
				pst.setString(5,ch4);
				pst.setString(6,ans);
				pst.setInt(7,rs.getInt(1));
				//System.out.println(rs.getInt(1));
				pst.executeUpdate();
				JOptionPane.showMessageDialog(null,"Update Successfully");	
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(null,e);
			}
		}
		else if (ae.getActionCommand().equals("DELETE"))
		{ 
			try 
			{
				String q=ja.getText();
				String ch1=op1.getText();
				String ch2=op2.getText();
				String ch3=op3.getText();
				String ch4=op4.getText();
				String cans=corans.getText();
				pst=conn.prepareStatement("DELETE from Questions where id=?");
				pst.setInt(1,rs.getInt(1));
				//System.out.println(rs.getInt(1));
				pst.execute();
				JOptionPane.showMessageDialog(null,"Delete Successfully");
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(null,e);
			}
		}
		else if (ae.getActionCommand().equals("NEW"))
		{ 
			newq.setText("ADD");
			clearRow();
		}
		else if (ae.getActionCommand().equals("ADD"))
		{
			try 
			{
				String q=ja.getText();
				String ch1=op1.getText();
				String ch2=op2.getText();
				String ch3=op3.getText();
				String ch4=op4.getText();
				String corr=corans.getText();
				pst=conn.prepareStatement("insert into Questions (question,op1,op2,op3,op4,correct) values (?,?,?,?,?,?)");
				
				pst.setString(1,q);
				pst.setString(2,ch1);
				pst.setString(3,ch2);
				pst.setString(4,ch3);
				pst.setString(5,ch4);
				pst.setString(6,corr);

				pst.execute();
				JOptionPane.showMessageDialog(null,"Insert Sucessfully Successfully");
				clearRow();
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(null,e);
			}
		}
		else if (ae.getActionCommand().equals("CANCEL"))
		{
			
			new AdminPage();
			setVisible(false);
		}
		else if (ae.getActionCommand().equals("LOGOUT"))
		{
			
			new Login();
			setVisible(false);
		}
	}

	public static void main(String args[])
	{
		new UpdateQ();
	}
}
 
