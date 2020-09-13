package DaoLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.mysql.jdbc.Statement;

import EntityLayer.Booking;

public class BookingDao {
	
	private Connection myConnection;
	private Statement myStatement;
	private ResultSet myResult;
	
	public BookingDao()
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
	
	public Booking LogIn(String bookingName, String passWord) 
	
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			String sql="select * from booking where bookingname='"+bookingName+"' and Pass='"+passWord+"'";
			myResult= myStatement.executeQuery(sql);
			
			if(myResult.next()) 
			{
				Booking b=new Booking();
				b.ID=myResult.getInt("ID");
				b.UserID=myResult.getInt("UserID");
				b.TotalPrice=myResult.getDouble("TotalPrice");
				b.Status=myResult.getString("Status");
				b.PaymentType=myResult.getString("PaymentType");
				
				return b;
				
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
	
	public boolean Insert(Booking b)
	{	
		try 
		{
			
			//String sql="insert into booking(Name,BookingName,Password,BookingType,ContactNo,Status) values('"+u.Name+"','"+u.BookingName+"','"+u.Password+"','"+u.BookingType+"','"+u.ContactNo+"',"+u.Status+")";
			
			String sql="insert into booking values("+b.ID+","+b.UserID+","+b.TotalPrice+",'"+b.Status+"','"+b.PaymentType+"')";
			int row=myStatement.executeUpdate(sql);
			return row>0;
		}
		catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage()); 
			return false;
		}
		
	}
	
	public Booking GetByID(int id) 
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			String sql="select * from booking where ID="+id;
			myResult= myStatement.executeQuery(sql);
			
			if(myResult.next()) 
			{
				Booking b=new Booking();
				b.ID=myResult.getInt("ID");
				b.UserID=myResult.getInt("UserID");
				b.TotalPrice=myResult.getDouble("TotalPrice");
				b.Status=myResult.getString("Status");
				b.PaymentType=myResult.getString("PaymentType");
				
				return b;
				
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
	
	public boolean Update(Booking b)
	{	
		try 
		{
			
			String sql="update booking set UserID="+b.UserID+",TotalPrice="+b.TotalPrice+",Status='"+b.Status+"',PaymentType='"+b.PaymentType+"'  where ID="+b.ID;
			int row=myStatement.executeUpdate(sql);
			return row>0;
		}
		catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			return false;
		}
		
	}

	public ArrayList<Booking> GetByAll(String key) 
	{
		ArrayList<Booking> list = new ArrayList<Booking>();
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			String sql="select * from booking ";
			if(!key.equals(""))
			{
				sql="select * from booking where name like '"+key+"' or bookingname like '"+key+"' or ContactNo like '"+key+"' or BookingType like '"+key+"'";
			}
			myResult= myStatement.executeQuery(sql);
			
			while(myResult.next()) 
			{
				Booking b=new Booking();
				b.ID=myResult.getInt("ID");
				b.UserID=myResult.getInt("UserID");
				b.TotalPrice=myResult.getDouble("TotalPrice");
				b.Status=myResult.getString("Status");
				b.PaymentType=myResult.getString("PaymentType");
				
				list.add(b);
				
			}
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
		
		return list;
	}
	
	public ArrayList<Booking> GetByStatus(int key) 
	{
		ArrayList<Booking> list = new ArrayList<Booking>();
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			String sql="select * from booking where Status="+key;
			
			myResult= myStatement.executeQuery(sql);
			
			while(myResult.next()) 
			{
				Booking b=new Booking();
				b.ID=myResult.getInt("ID");
				b.UserID=myResult.getInt("UserID");
				b.TotalPrice=myResult.getDouble("TotalPrice");
				b.Status=myResult.getString("Status");
				b.PaymentType=myResult.getString("PaymentType");
				
				list.add(b);
				
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
			String sql="delete from booking where ID="+id;
			int row= myStatement.executeUpdate(sql);
			return row>0;
		}
		catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
			return false;
		}
	}
}
