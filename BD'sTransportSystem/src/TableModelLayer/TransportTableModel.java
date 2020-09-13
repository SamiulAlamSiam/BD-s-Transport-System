package TableModelLayer;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import EntityLayer.transport;

public class TransportTableModel extends AbstractTableModel{

	public String[] colNames= {"ID","Name","Transport Type","Registration"};
	private ArrayList<transport> List=new ArrayList<transport>();
	
	public TransportTableModel(ArrayList<transport> list)
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
		
		transport t = List.get(row);
		switch(col)
		{
			case 0:
				return t.ID;
			case 1:
				return t.Name;
			case 2:
				return t.TransportType;
			case 3:
				return t.Registration;
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
