package DaoLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.mysql.jdbc.Statement;

import EntityLayer.SessionSeat;
import HelperLayer.LogInHelper;

public class SessionSeatDao {
	
	private Connection myConnection;
	private Statement myStatement;
	private ResultSet myResult;
	
	public SessionSeatDao()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			myConnection= DriverManager.getConnection("jdbc:mysql://localhost:3306/transportation","root", "");
			myStatement=(Statement) myConnection.createStatement();
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
	}
	
	
	public SessionSeat LogIn(String sessionseatName, String passWord) 
	
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			String sql="select * from sessionseat where sessionseatname='"+sessionseatName+"' and Pass='"+passWord+"'";
			myResult= myStatement.executeQuery(sql);
			
			if(myResult.next()) 
			{
				SessionSeat ss=new SessionSeat();
				ss.ID=myResult.getInt("ID");
				ss.SessionID=myResult.getInt("SessionID");
				ss.SeatNO=myResult.getString("SeatNO");
				ss.UserID=myResult.getInt("UserID");
				
				return ss;
				
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
	
	public boolean Insert(SessionSeat ss)
	{	
		try 
		{
			
			String sql="insert into sessionseat(SessionID,SeatNO,UserID) values("+ss.SessionID+","+ss.SeatNO+","+ss.UserID+")";
			
			//String sql="insert into sessionseat values("+ss.ID+","+ss.SessionID+","+ss.SeatNO+","+ss.UserID+")";
			int row=myStatement.executeUpdate(sql);
			return row>0;
		}
		catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage()); 
			return false;
		}
		
	}
	
	public SessionSeat GetByID(int id) 
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			String sql="select * from sessionseat where ID="+id;
			myResult= myStatement.executeQuery(sql);
			
			if(myResult.next()) 
			{
				SessionSeat ss=new SessionSeat();
				ss.ID=myResult.getInt("ID");
				ss.SessionID=myResult.getInt("SessionID");
				ss.SeatNO=myResult.getString("SeatNO");
				ss.UserID=myResult.getInt("UserID");
				
				return ss;
				
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
	
	public boolean Update(SessionSeat ss)
	{	
		try 
		{
			
			String sql="update sessionseat set SessionID="+ss.SessionID+",SeatNO="+ss.SeatNO+",BookingID="+ss.UserID+"  where ID="+ss.ID;
			int row=myStatement.executeUpdate(sql);
			return row>0;
		}
		catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			return false;
		}
		
	}

	public ArrayList<SessionSeat> GetByAll(String key) 
	{
		ArrayList<SessionSeat> list = new ArrayList<SessionSeat>();
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			String sql="select * from sessionseat ";
			if(!key.equals(""))
			{
				sql="select * from sessionseat where Name like '"+key+"' or sessionseatname like '"+key+"', SessionID like '"+key+"',SeatNO like '"+key+"' UserID like '"+key+"'";
			}
			myResult= myStatement.executeQuery(sql);
			
			while(myResult.next()) 
			{
				SessionSeat ss=new SessionSeat();
				ss.ID=myResult.getInt("ID");
				ss.SessionID=myResult.getInt("SessionID");
				ss.SeatNO=myResult.getString("SeatNO");
				ss.UserID=myResult.getInt("UserID");
				
				list.add(ss);
				
			}
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
		
		return list;
	}
	
	public ArrayList<SessionSeat> GetByStatus(int key) 
	{
		ArrayList<SessionSeat> list = new ArrayList<SessionSeat>();
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			String sql="select * from sessionseat where Status="+key;
			
			myResult= myStatement.executeQuery(sql);
			
			while(myResult.next()) 
			{
				SessionSeat ss=new SessionSeat();
				ss.ID=myResult.getInt("ID");
				ss.SessionID=myResult.getInt("SessionID");
				ss.SeatNO=myResult.getString("SeatNO");
				ss.UserID=myResult.getInt("UserID");
				
				list.add(ss);
				
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
			Class.forName("com.mysql.jdbc.Driver");
			String sql="delete from sessionseat where ID="+id;
			int row= myStatement.executeUpdate(sql);
			return row>0;
		}
		catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
			return false;
		}
	}
	
	
	
	
	public ArrayList<SessionSeat> GetForUser() 
	{
		ArrayList<SessionSeat> list = new ArrayList<SessionSeat>();
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			String sql="select * from sessionseat where UserID="+LogInHelper.CurrentUsers.ID;
			
			myResult= myStatement.executeQuery(sql);
			
			while(myResult.next()) 
			{
				SessionSeat ss=new SessionSeat();
				ss.ID=myResult.getInt("ID");
				ss.SessionID=myResult.getInt("SessionID");
				ss.SeatNO=myResult.getString("SeatNO");
				ss.UserID=myResult.getInt("UserID");
				
				list.add(ss);
				
			}
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
		
		return list;
	}
	
	
	
}
