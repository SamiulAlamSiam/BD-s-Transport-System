package DaoLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.mysql.jdbc.Statement;

import EntityLayer.User;

public class UserDao {
	
	private Connection myConnection;
	private Statement myStatement;
	private ResultSet myResult;
	
	public UserDao()
	{
		try
		{
			//Class.forName("com.mysql.jdbc.Driver");
			myConnection= DriverManager.getConnection("jdbc:mysql://localhost:3306/transportation","root", "");
			myStatement=(Statement) myConnection.createStatement();
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
	}
	
//	public void LogIn(String userName, String password)
//	{	
//		try
//		{
//			String sql ="select * from user where UserName='"+userName+"' and pass='"+password+"'";
//			myResult= myStatement.executeQuery(sql);
//			
//			if(myResult.next())
//			{
//				JOptionPane.showMessageDialog(null, "Successfully Log In");
//				return;
//			}
//			
//			else
//			{
//				JOptionPane.showMessageDialog(null, "Invalid Id OR Password");
//				return;
//			}
//		} 
//		catch (SQLException ex)
//		{
//			JOptionPane.showMessageDialog(null, ex.getMessage());
//		}
//		
//	}
	
	public User LogIn(String userName, String passWord) 
	
	{
		try
		{
			//Class.forName("com.mysql.jdbc.Driver");
			String sql="select * from user where username='"+userName+"' and Pass='"+passWord+"'";
			myResult= myStatement.executeQuery(sql);
			
			if(myResult.next()) 
			{
				User u=new User();
				u.ID=myResult.getInt("ID");
				u.ContactNo=myResult.getString("ContactNo");
				u.Name=myResult.getString("Name");
				u.UserName=myResult.getString("UserName");
				u.Password=myResult.getString("Pass");
				u.UserType=myResult.getString("UserType");
				u.Status=myResult.getInt("Status");
				
				return u;
				
			}
			else
				return null;
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, ex.getMessage());
			return null;
		}
	}
	
	public boolean Insert(User u)
	{	
		try 
		{
			
			String sql="insert into user(Name,UserName,Pass,UserType,ContactNo,Status) values('"+u.Name+"','"+u.UserName+"','"+u.Password+"','"+u.UserType+"','"+u.ContactNo+"',"+u.Status+")";
			
			//String sql="insert into user values("+u.ID+",'"+u.Name+"','"+u.UserName+"','"+u.Password+"','"+u.UserType+"','"+u.ContactNo+"',"+u.Status+")";
			int row=myStatement.executeUpdate(sql);
			return row>0;
		}
		catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage()); 
			return false;
		}
		
	}
	
	public User GetByID(int id) 
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			String sql="select * from user where ID="+id;
			myResult= myStatement.executeQuery(sql);
			
			if(myResult.next()) 
			{
				User u=new User();
				u.ID=myResult.getInt("ID");
				u.ContactNo=myResult.getString("ContactNo");
				u.Name=myResult.getString("Name");
				u.UserName=myResult.getString("UserName");
				u.Password=myResult.getString("Pass");
				u.UserType=myResult.getString("UserType");
				u.Status=myResult.getInt("Status");
				
				return u;
				
			}
			else
				return null;
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, ex.getMessage());
			return null;
		}
	}
	
	public boolean Update(User u)
	{	
		try 
		{	
			String sql="update user set Name='"+u.Name+"',UserName='"+u.UserName+"',Pass='"+u.Password+"',UserType='"+u.UserType+"',ContactNo='"+u.ContactNo+"',Status="+u.Status+"  where ID="+u.ID;
			int row=myStatement.executeUpdate(sql);
			
			return true;
			//return row>0;
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			return false;
		}
		
	}

	public ArrayList<User> GetByAll(String key) 
	{
		ArrayList<User> list = new ArrayList<User>();
		try
		{
			//Class.forName("com.mysql.jdbc.Driver");
			String sql="select * from user ";
			if(!key.equals(""))
			{
				sql="select * from user where Name like '"+key+"' or UserName like '"+key+"' or ContactNo like '"+key+"' or UserType like '"+key+"'";
			}
			myResult= myStatement.executeQuery(sql);
			
			while(myResult.next()) 
			{
				User u=new User();
				u.ID=myResult.getInt("ID");
				u.ContactNo=myResult.getString("ContactNo");
				u.Name=myResult.getString("Name");
				u.UserName=myResult.getString("UserName");
				u.Password=myResult.getString("Pass");
				u.UserType=myResult.getString("UserType");
				u.Status=myResult.getInt("Status");
				
				list.add(u);
				
			}
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
		
		return list;
	}
	
	public ArrayList<User> GetByStatus(int key) 
	{
		ArrayList<User> list = new ArrayList<User>();
		try
		{
			//Class.forName("com.mysql.jdbc.Driver");
			String sql="select * from user where Status="+key;
			
			myResult= myStatement.executeQuery(sql);
			
			while(myResult.next()) 
			{
				User u=new User();
				u.ID=myResult.getInt("ID");
				u.ContactNo=myResult.getString("ContactNo");
				u.Name=myResult.getString("Name");
				u.UserName=myResult.getString("UserName");
				u.Password=myResult.getString("Pass");
				u.UserType=myResult.getString("UserType");
				u.Status=myResult.getInt("Status");
				
				list.add(u);
				
			}
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
		
		return list;
	}
	
	public boolean Delete(int id)
	{
		try
		{
			//Class.forName("com.mysql.jdbc.Driver");
			String sql="delete from user where ID="+id;
			int row= myStatement.executeUpdate(sql);
			return row>0;
		}
		catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
			return false;
		}
	}
}
