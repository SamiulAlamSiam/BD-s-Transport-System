package GUILayer;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import DaoLayer.BookingDao;
import EntityLayer.Booking;
import TableModelLayer.BookingTableModel;

public class BookingManagerFrame extends JFrame {
	
	private JPanel pnlUpper,pnlLower;
	private JLabel lblSearch;
	private JTextField txtSraech;
	private JButton btnSearch,btnAdd,btnEdit,btnDelete,btnBack;
	private JTable tblBookings;
	private BookingDao bookingDao;
	
	public BookingManagerFrame()
	{
		this.setSize(600,450);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		bookingDao= new BookingDao();
		
		pnlUpper = new JPanel();
		pnlUpper.setLayout(new FlowLayout());
		this.pnlUpper.setBorder(new LineBorder(Color.BLACK,2));
		this.pnlUpper.setBounds(5, 5, 570, 40);
		
		add(this.pnlUpper);
		
		pnlLower= new JPanel();
		pnlLower.setLayout(null);
		this.pnlLower.setBorder(new TitledBorder(new LineBorder(Color.GREEN,2),"Data"));
		this.pnlLower.setBounds(5, 50, 570, 340);
		add(this.pnlLower);
		
		this.AddUpperComponent();
		this.AddLowerComponent();
	}
	
	private void ShowDetailFrame(int id)
	{
//		BookingDetailFrame udf = new BookingDetailFrame(id, this);
//		udf.setVisible(true);
	}

	private void AddLowerComponent() {
		tblBookings=new JTable();
		tblBookings.setBackground(Color.WHITE);
		
		JScrollPane sp= new JScrollPane();
		sp.setBounds(5, 15, 490, 320);
		this.pnlLower.add(sp);
		sp.setViewportView(tblBookings);
		
		this.PopulateTable();
		
	}

	public void PopulateTable() {
		ArrayList<Booking> bookingList= bookingDao.GetByAll(txtSraech.getText());
		
		BookingTableModel model= new BookingTableModel(bookingList);
		tblBookings.setModel(model);
		
	}

	private void AddUpperComponent() {
		lblSearch=new JLabel("Search");
		this.pnlUpper.add(lblSearch);
		
		txtSraech=new JTextField(10);
		this.pnlUpper.add(txtSraech);
		
		btnSearch=new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				PopulateTable();
				
			}
		});
		this.pnlUpper.add(btnSearch);
		
		btnAdd=new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ShowDetailFrame(-1);
				
			}
		});
		this.pnlUpper.add(btnAdd);
		
		btnEdit=new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row=tblBookings.getSelectedRow();
				if(row==-1)
				{
					JOptionPane.showMessageDialog(null, "Please Select The Row First");
					return;
				}
				else
				{
					int id=(int) tblBookings.getValueAt(row, 0);
					ShowDetailFrame(id);
				}
				
			}
		});
		this.pnlUpper.add(btnEdit);
		

		btnDelete=new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
	
			public void actionPerformed(ActionEvent e) {
				
				int row=tblBookings.getSelectedRow();
				if(row==-1)
				{
					JOptionPane.showMessageDialog(null, "Please Select The Row First");
					return;
				}
				else
				{
					int id=(int) tblBookings.getValueAt(row, 0);
					
					boolean r=bookingDao.Delete(id);
					
					if(r)
					{
						JOptionPane.showMessageDialog(null, "Succefully Delete");
						PopulateTable();
					}
					else
						JOptionPane.showMessageDialog(null, "Something Went Wrong");
				}
				
			}
		});
		this.pnlUpper.add(btnDelete);
		
		btnBack=new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MenuFrame mf=new MenuFrame();
				mf.setVisible(true);
				setVisible(false);
				
			}
		});
		this.pnlUpper.add(btnBack);	
	}

}
