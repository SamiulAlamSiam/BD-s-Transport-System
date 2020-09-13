package TableModelLayer;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import EntityLayer.User;

public class PendingTableModel extends AbstractTableModel{

	public String[] colNames= {"ID","Name","User Name","Password","User Type","Contact No"};
	private ArrayList<User> List=new ArrayList<User>();
	
	public PendingTableModel(ArrayList<User> list)
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
		
		User u = List.get(row);
		switch(col)
		{
			case 0:
				return u.ID;
			case 1:
				return u.Name;
			case 2:
				return u.UserName;
			case 3:
				return u.Password;
			case 4:
				return u.UserType;
			case 5:
				return u.ContactNo;
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
