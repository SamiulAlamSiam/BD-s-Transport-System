package TableModelLayer;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import DaoLayer.TransportDao;
import EntityLayer.Session;

public class SessionTableModel extends AbstractTableModel{

	public String[] colNames= {"ID","Transportation Name","From","To","Date","Time","Price","Status"};
	private ArrayList<Session> List=new ArrayList<Session>();
	
	public SessionTableModel(ArrayList<Session> list)
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
		
		Session s = List.get(row);
		switch(col)
		{
			case 0:
				return s.ID;
			case 1:
				return new TransportDao().GetByID(s.TransporationID).Name;
			case 2:
				return s.Source;
			case 3:
				return s.Destination;
			case 4:
				return s.Date;
			case 5:
				return s.Time;
			case 6:
				return s.Price;
			case 7:
				return s.Status;
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
