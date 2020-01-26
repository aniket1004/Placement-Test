////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
//	Class		:	ViewStudent
//	Working 	:	It contains table which have student information whose give the exam.And also provide
//				delete button which give authority to admin to delete student information.
//	Link with 	:	<-AdminPage(if click cancel),<-Login(if click logout);
//
//
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
class ViewStudent extends JFrame implements ActionListener
{
	public JButton delete;
	public JScrollPane pane;
	public JTable detail;
	public JButton display,back;
	Vector column,data;
	ResultSet rs;
	PreparedStatement pst;
	Statement stmt;
	Connection con;
	JLabel label;
	ImageIcon img;
	public ViewStudent()
	{
		int i=0,j=0;
		
		try
		{ 
			Class.forName("org.postgresql.Driver"); 
			con = DriverManager.getConnection("jdbc:postgresql:placement", "postgres","Aniket1004"); 
			        String query = "Select * from Register";

        		
        	     	stmt = con.createStatement();
			rs=stmt.executeQuery(query);
			ResultSetMetaData rsmd=rs.getMetaData();
			int total=rsmd.getColumnCount();
			data=new Vector();
			column=new Vector();
			column.add("USERNAME");
			column.add("NAME");
			column.add("EMAIL");
			column.add("MOBILE");
			column.add("RESULT");
            		while (rs.next())
			{
				Vector r=new Vector();
				r.add(rs.getString(1));
				r.add(rs.getString(2));
				r.add(rs.getString(3));
				r.add(rs.getString(4));
				r.add(rs.getString(18));				
				data.add(r);
	
			}
			
			if (con!=null)
			{
				System.out.println("Connection establish");
			}
			else
			{		
				System.out.println("Connection not establish");
			}
			 
		} 
		catch(Exception ex) 
		{ 
			System.err.println(ex); 
		}
		ImageIcon img=new ImageIcon("photo/a.jpg");
		JLabel image=new JLabel(img);
		image.setBounds(0,0,1400,900);

		back=new JButton("BACK");
		back.setBounds(1140,30,100,27);
		back.setBackground(new Color(255,0,0));
		back.setForeground(new Color(255,255,255));
		back.setFont(new Font("Arial",Font.BOLD,15));

		delete=new JButton("DELETE");
		delete.setBounds(140,30,100,27);
		delete.setBackground(new Color(255,0,0));
		delete.setForeground(new Color(255,255,255));
		delete.setFont(new Font("Arial",Font.BOLD,15));

		detail=new JTable(data,column);
		detail.setBackground(new Color(255,255,204));
		//detail.setForeground(new Color());
		//detail.setFont(new Font("Arial",Font.PLAIN,15));		
		image.add(detail);
		pane=new JScrollPane(detail);
		pane.setBounds(40,100,1200,550);

		image.add(delete);
		image.add(back);
		image.add(pane);
		add(image);

		back.addActionListener(this);		
		delete.addActionListener(this);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("STUDENT DETAILS");
		setSize(1200,800);
		setVisible(true);
	}
	public void actionPerformed(ActionEvent ae)
	{
		if (ae.getActionCommand().equals("DELETE"))
		{
			int column=0;
			int row=detail.getSelectedRow();
			String value=detail.getModel().getValueAt(row,column).toString();
			try
			{
				pst=con.prepareStatement("delete from Register where username=?");
				pst.setString(1,value);
				pst.execute();
				setVisible(false);
				new ViewStudent();
		
			}
			catch (Exception e)
			{
				System.out.println(e);
			}
		}	
		else if (ae.getActionCommand().equals("BACK"))
		{
			new AdminPage();
			setVisible(false);
		}
	}
	public static void main(String args[])
	{
		new ViewStudent();
	}
}
