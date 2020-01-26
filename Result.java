////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
//	Class		:	Result
//	Working 	:	It contains table which display questions,selected answer,correct answer.
//				And display result of exam.
//	Link with 	:	<-Login(if click logout);
//
//
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.*;
import javax.swing.table.*;

public class Result extends JFrame implements ActionListener
{
	public JTable table;
	public JScrollPane pane;
	public Connection con=null;
	public Statement stmt=null;
	public PreparedStatement pst=null;
	public ResultSet rs=null;
	public int current=0,count=0;
	public String q[],username;
	public Vector data,col;
	public int marks=0;
	public JLabel res;
	public JButton back;
	public Result()
	{
	}
	public Result(String username,String correct[],String answer[],int random,int noq)
	{
		this.username=username;
		int i=0;
		try
		{
			Class.forName("org.postgresql.Driver");
			con=DriverManager.getConnection("jdbc:postgresql:placement","postgres","Aniket1004");
			stmt=con.createStatement();
			rs=stmt.executeQuery("Select * from Questions");

			data=new Vector();
				
			col=new Vector();
			
			while((current<random-1)&&(rs.next()))
			{
				current++;
				
			}
			
			while ((count<noq)&&(rs.next()))
			{
				i=count+1;
				Vector r=new Vector();	
				r.add(i);
				r.add(rs.getString(2));
				String c=this.ans(correct[count]);
				r.add(c);
				String a=this.ans(answer[count]);
				r.add(a);
				data.add(r);

				
				count++;
			}
			for(int a=0;a<noq;a++)
			{
				if (!correct[a].equals("-"))
				{
					if (correct[a].equals(answer[a]))
					{
						marks++;
					}
				}
			}
			col.add("No.");
			col.add("Question");
			col.add("Correct answer");
			col.add("Selected Answer");
			table=new JTable(data,col);
			
			table.setBackground(new Color(204,255,255));
			TableColumnModel columnmodel=table.getColumnModel();
			columnmodel.getColumn(0).setPreferredWidth(20);
			columnmodel.getColumn(1).setPreferredWidth(750);
			columnmodel.getColumn(2).setPreferredWidth(45);
			columnmodel.getColumn(3).setPreferredWidth(45);
			pane=new JScrollPane(table);
			pane.setBounds(0,150,1100,600);
			res=new JLabel();
			res.setForeground(new Color(0,255,0));
			res.setFont(new Font("Serif",Font.BOLD,24));
			res.setBounds(500,590,340,20);
			res.setText("Your result is "+marks+" out of "+noq);
			back=new JButton("LOGOUT");
			back.setBounds(1100,610,100,27);
			back.setBackground(new Color(255,0,0));
			back.setForeground(new Color(255,255,255));

			String result=(marks+"/"+noq);
			try
			{
				pst=con.prepareStatement("update Register set result=? where username=?");
				pst.setString(1,result);
				pst.setString(2,username);
				pst.executeUpdate();
			}
			catch (Exception e)
			{
				System.out.println("Exception occur");
			}
			add(res);
			add(back);
			add(pane);
			
			back.addActionListener(this);
			setSize(1400,800);
			setVisible(true);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setTitle("RESULT OF EXAM");
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null,e);
		}
		
	}
	public String ans(String a)
	{
		String r="-";
		try
		{
			if (a.equals("A"))
			{
				return (rs.getString(3));
			}
			else if (a.equals("B"))
			{
				return (rs.getString(4));
			}
			else if (a.equals("C"))
			{
				return (rs.getString(5));
			}
			else if (a.equals("D"))
			{
				return (rs.getString(6));
			}
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null,e);
		}
		return r;
	}
	public void actionPerformed(ActionEvent ae)
	{
			if (ae.getActionCommand().equals("LOGOUT"))
			{
				
				new Login();
				setVisible(false);
			}
	}
}
	
