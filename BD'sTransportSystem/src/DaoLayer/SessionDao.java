package DaoLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.mysql.jdbc.Statement;

import EntityLayer.Session;

public class SessionDao {
	
	private Connection myConnection;
	private Statement myStatement;
	private ResultSet myResult;
	
	public SessionDao()
	{
		try
		{
			myConnection= DriverManager.getConnection("jdbc:mysql://localhost:3306/transportation","root", "");
			myStatement=(Statement) myConnection.createStatement();
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
	}
	
	
	
	public boolean Insert(Session s)
	{	
		try 
		{
			//String sql="insert into session values("+s.ID+","+s.TransporationID+",'"+s.Source+"','"+s.Destination+"','"+s.Date+"','"+s.Time+"','"+s.Price+"','"+s.Status+"')";
			
			String sql="insert into session(TransporationID,Source,Destination,Date,Time,Price,Status) values("+s.TransporationID+",'"+s.Source+"','"+s.Destination+"','"+s.Date+"','"+s.Time+"','"+s.Price+"','"+s.Status+"')";
			
			int row=myStatement.executeUpdate(sql);
			return row>0;
		}
		catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage()); 
			return false;
		}
		
	}
	
	public Session GetByID(int id) 
	{
		try
		{
			String sql="select * from session where ID="+id;
			myResult= myStatement.executeQuery(sql);
			
			if(myResult.next()) 
			{
				Session s=new Session();
				s.ID=myResult.getInt("ID");
				s.TransporationID=myResult.getInt("TransporationID");
				s.Source=myResult.getString("Source");
				s.Destination=myResult.getString("Destination");
				s.Date=myResult.getString("Date");
				s.Time=myResult.getString("Time");
				s.Price=myResult.getFloat("Price");
				s.Status=myResult.getString("Status");
				
				return s;
				
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
	
	public boolean Update(Session s)
	{	
		try 
		{
			String sql="update session set TransporationID="+s.TransporationID+",Source='"+s.Source+"',Destination='"+s.Destination+"',Date='"+s.Date+"',Time='"+s.Time+"',Price="+s.Price+",Status= '"+s.Status+"' where ID="+s.ID;
			int row=myStatement.executeUpdate(sql);
			return row>0;
		}
		catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			return false;
		}
		
	}

	public ArrayList<Session> GetByAll(String key,String key1,String TransportType) 
	{
		ArrayList<Session> list = new ArrayList<Session>();
		try
		{
			String sql="SELECT s.`ID`, s.`TransporationID`, s.`Source`, s.`Destination`, s.`Date`, s.`Time`, s.`Price`, s.`Status` FROM `session` s,transport t WHERE s.`TransporationID`=t.`ID`";
			if((!key.equals(""))&&(!key1.equals(""))&&(!TransportType.equals("")))
			{
				//sql="select * from session where Source like '"+key+"' or Destination like '"+key+"' or Date like '"+key+"'";
				
				//sql="select * from session where Status like'Booking' and Source like '"+key+"' and Destination like '"+key1+"'and TransporationID like (select ID from transport where TransportType like '"+TransportType+"')";
		
				//sql="select * from session where Source like '"+key+"' ";
				
				sql="SELECT s.ID, s.TransporationID, s.Source, s.Destination, s.Date, s.Time, s.Price, s.Status FROM session s,transport t WHERE s.TransporationID=t.ID and t.TransportType='"+TransportType+"' and s.Status like'Booking' and s.Source like '"+key+"' and s.Destination like '"+key1+"'";
				
			
			}
			myResult= myStatement.executeQuery(sql);
			
			while(myResult.next()) 
			{
				Session s=new Session();
				s.ID=myResult.getInt("ID");
				s.TransporationID=myResult.getInt("TransporationID");
				s.Source=myResult.getString("Source");
				s.Destination=myResult.getString("Destination");
				s.Date=myResult.getString("Date");
				s.Time=myResult.getString("Time");
				s.Price=myResult.getFloat("Price");
				s.Status=myResult.getString("Status");
				
				list.add(s);
				
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
			String sql="delete from session where ID="+id;
			int row= myStatement.executeUpdate(sql);
			return row>0;
		}
		catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
			return false;
		}
	}
}
