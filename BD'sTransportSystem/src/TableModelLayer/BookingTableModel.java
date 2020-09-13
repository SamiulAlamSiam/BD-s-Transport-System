package TableModelLayer;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import EntityLayer.Booking;

public class BookingTableModel extends AbstractTableModel{

	public String[] colNames= {"ID","Booking ID","Total Price","Status","Payment Type"};
	private ArrayList<Booking> List=new ArrayList<Booking>();
	
	public BookingTableModel(ArrayList<Booking> list)
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
		
		Booking b = List.get(row);
		switch(col)
		{
			case 0:
				return b.ID;
			case 1:
				return b.UserID;
			case 2:
				return b.TotalPrice;
			case 3:
				return b.Status;
			case 4:
				return b.PaymentType;
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
