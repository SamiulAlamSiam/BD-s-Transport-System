package TableModelLayer;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import DaoLayer.SessionDao;
import DaoLayer.TransportDao;
import DaoLayer.UserDao;
import EntityLayer.SessionSeat;
import EntityLayer.transport;

public class SessionSeatTableModel extends AbstractTableModel{

	public String[] colNames= {"ID","Transport ID","Seat NO","User Name"};
	private ArrayList<SessionSeat> List=new ArrayList<SessionSeat>();
	
	public SessionSeatTableModel(ArrayList<SessionSeat> list)
	{
		List=list;
	}
	
	public int getColumnCount() {
		
		return colNames.length;
	}

	
	public int getRowCount() {
		
		return List.size();
	}

	
	public Object getValueAt(int row, int col) {
		
		SessionSeat ss = List.get(row);
		switch(col)
		{
			case 0:
				return ss.ID;
			case 1:
				return ss.SessionID; 
			//	return new TransportDao().GetByID(t.ID).Name; 
			case 2:
				return ss.SeatNO;
			case 3:
				return new UserDao().GetByID(ss.UserID).Name; 
			default:
				return "-";
		}
		
		
		//return null;
	}
	
	public String getColumnName(int col)
	{
		return colNames[col];
	}

}
