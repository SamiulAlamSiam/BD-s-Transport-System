package DaoLayer;


import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;


import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class SessionDetailsDao {
	
	private Connection myConnection;
	
	public SessionDetailsDao()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			myConnection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/transportation", "root", "");
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, "Connection Unsuccessful");
		}
	}
	
	
	public boolean SeatBook(int sessionID,int seat)
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			if(myConnection==null)
				throw(new Exception("Connection Unsuccesfull"));
			
			Statement myStatement = (Statement) myConnection.createStatement();
			String sql = "select * from sessionseat where SessionID="+sessionID+" and SeatNO="+seat;
			
			ResultSet myResultSet = myStatement.executeQuery(sql);
			
			if(myResultSet.next())
			{
				return true;
			}
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
		
		return false;
	}
}
