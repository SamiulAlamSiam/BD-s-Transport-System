package DaoLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.mysql.jdbc.Statement;

import EntityLayer.transport;

public class TransportDao {
	
	private Connection myConnection;
	private Statement myStatement;
	private ResultSet myResult;
	
	public TransportDao()
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
	
	
	public transport LogIn(String transportName, String passWord) 
	
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			String sql="select * from transport where transportname='"+transportName+"' and Pass='"+passWord+"'";
			myResult= myStatement.executeQuery(sql);
			
			if(myResult.next()) 
			{
				transport t=new transport();
				t.ID=myResult.getInt("ID");
				
				t.Name=myResult.getString("Name");
				t.TransportType=myResult.getString("TransportType");
				t.Registration=myResult.getInt("Registration");
				
				
				return t;
				
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
	
	public boolean Insert(transport t)
	{	
		try 
		{
			
			//String sql="insert into transport values("+t.ID+",'"+t.Name+"','"+t.TransportType+"',"+t.Registration+")";
			
			String sql="insert into transport(Name,TransportType,Registration) values('"+t.Name+"','"+t.TransportType+"',"+t.Registration+")";
			
			int row=myStatement.executeUpdate(sql);
			return row>0;
		}
		catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage()); 
			return false;
		}
		
	}
	
	public transport GetByID(int id) 
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			String sql="select * from transport where ID="+id;
			myResult= myStatement.executeQuery(sql);
			
			if(myResult.next()) 
			{
				transport t=new transport();
				t.ID=myResult.getInt("ID");
				
				t.Name=myResult.getString("Name");
				t.TransportType=myResult.getString("TransportType");
				t.Registration=myResult.getInt("Registration");
				
				return t;
				
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
	
	public boolean Update(transport t)
	{	
		try 
		{
			
			String sql="update transport set Name='"+t.Name+"',transportType='"+t.TransportType+"',Registration="+t.Registration+"  where ID="+t.ID;
			int row=myStatement.executeUpdate(sql);
			return row>0;
		}
		catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			return false;
		}
		
	}

	public ArrayList<transport> GetByAll(String key) 
	{
		ArrayList<transport> list = new ArrayList<transport>();
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			String sql="select * from transport ";
			if(!key.equals(""))
			{
				sql="select * from transport where Name like '"+key+"' or TransportType like '"+key+"' or Registration like '"+key+"'";
			}
			myResult= myStatement.executeQuery(sql);
			
			while(myResult.next()) 
			{
				transport t=new transport();
				t.ID=myResult.getInt("ID");
				
				t.Name=myResult.getString("Name");
				t.TransportType=myResult.getString("TransportType");
				t.Registration=myResult.getInt("Registration");
				
				list.add(t);
				
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
			String sql="delete from transport where ID="+id;
			int row= myStatement.executeUpdate(sql);
			return row>0;
		}
		catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
			return false;
		}
	}
}
