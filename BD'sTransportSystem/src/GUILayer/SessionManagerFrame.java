package GUILayer;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import DaoLayer.SessionDao;
import EntityLayer.Session;
import EntityLayer.User;
import HelperLayer.LogInHelper;
import HelperLayer.SessionHelper;
import HelperLayer.UtilHelper;
import TableModelLayer.SessionTableModel;

public class SessionManagerFrame extends JFrame {
	
	private JPanel pnlUpper,pnlLower;
	private JLabel lblFrom,lblTo,lblDesireTransport;
	private JTextField txtSraech;
	private JButton btnSearch,btnAdd,btnEdit,btnDelete,btnBack,btnBooking,btnLogOut;
	private JTable tblSessions;
	private SessionDao sessionDao;
	
	private JComboBox ddlSource,ddlDestination,ddlDesireTransport;
	private String[] path=UtilHelper.GetDivisionName();
	private String[] DesireTransport= {"Bus","Train"};
	//User u=new User();
	
	public SessionManagerFrame()
	{
		this.setSize(800,450);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		ImageIcon icon=new ImageIcon("C:\\Users\\User\\Downloads\\PICTURE\\Dream-Bike\\Yamaha R15 V3  2.jpg");
		this.setIconImage(icon.getImage());
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		sessionDao= new SessionDao();
		
		pnlUpper = new JPanel();
		pnlUpper.setLayout(new FlowLayout());
		this.pnlUpper.setBorder(new LineBorder(Color.BLACK,2));
		this.pnlUpper.setBounds(5, 5, 770, 40);
		
		add(this.pnlUpper);
		
		pnlLower= new JPanel();
		pnlLower.setLayout(null);
		this.pnlLower.setBorder(new TitledBorder(new LineBorder(Color.GREEN,2),"Data"));
		this.pnlLower.setBounds(5, 50, 770, 340);
		add(this.pnlLower);
		
		this.AddUpperComponent();
		this.AddLowerComponent();
	}
	
	private void ShowDetailFrame(int id)
	{
		SessionDetailFrame udf = new SessionDetailFrame(id, this);
		udf.setVisible(true);
	}

	private void AddLowerComponent() {
		tblSessions=new JTable();
		tblSessions.setBackground(Color.WHITE);
		
		JScrollPane sp= new JScrollPane();
		sp.setBounds(5, 15, 690, 320);
		this.pnlLower.add(sp);
		sp.setViewportView(tblSessions);
		
		this.PopulateTable();
		
	}

	public void PopulateTable() {
		ArrayList<Session> sessionList= sessionDao.GetByAll(ddlSource.getSelectedItem().toString(),ddlDestination.getSelectedItem().toString(),ddlDesireTransport.getSelectedItem().toString());
		
		//ArrayList<Session> sessionList= sessionDao.GetByAll(ddlSource.getSelectedItem().toString(),ddlDestination.getSelectedItem().toString());
		
		SessionTableModel model= new SessionTableModel(sessionList);
		tblSessions.setModel(model);
	}
	

	private void AddUpperComponent() {
		
		lblFrom=new JLabel("From :");
		this.pnlUpper.add(lblFrom);
		
		ddlSource=new JComboBox(path);
		this.pnlUpper.add(ddlSource);
		
		lblTo=new JLabel("To :");
		this.pnlUpper.add(lblTo);
		
		ddlDestination=new JComboBox(path);
		this.pnlUpper.add(ddlDestination);
		
		lblDesireTransport=new JLabel("Type :");
		this.pnlUpper.add(lblDesireTransport);
		
		ddlDesireTransport=new JComboBox(DesireTransport);
		this.pnlUpper.add(ddlDesireTransport);
		
		txtSraech=new JTextField(10);
		//this.pnlUpper.add(txtSraech);
		
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
		if((LogInHelper.CurrentUsers.UserType.equals("Admin"))||(LogInHelper.CurrentUsers.UserType.equals("Manager")))
			this.pnlUpper.add(btnAdd);
		
		btnEdit=new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row=tblSessions.getSelectedRow();
				if(row==-1)
				{
					JOptionPane.showMessageDialog(null, "Please Select The Row First");
					return;
				}
				else
				{
					int id=(int) tblSessions.getValueAt(row, 0);
					ShowDetailFrame(id);
				}
				
			}
		});
		if((LogInHelper.CurrentUsers.UserType.equals("Admin"))||(LogInHelper.CurrentUsers.UserType.equals("Manager")))
			this.pnlUpper.add(btnEdit);
		

		btnDelete=new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
	
			public void actionPerformed(ActionEvent e) {
				
				int row=tblSessions.getSelectedRow();
				if(row==-1)
				{
					JOptionPane.showMessageDialog(null, "Please Select The Row First");
					return;
				}
				else
				{
					int id=(int) tblSessions.getValueAt(row, 0);
					
					boolean r=sessionDao.Delete(id);
					
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
		if((LogInHelper.CurrentUsers.UserType.equals("Admin"))||(LogInHelper.CurrentUsers.UserType.equals("Manager")))
			this.pnlUpper.add(btnDelete);
		
		btnBooking=new JButton("Booking");
		btnBooking.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				int row=tblSessions.getSelectedRow();
				if(row==-1)
				{
					JOptionPane.showMessageDialog(null, "Please Select The Row First");
					return;
				}
				else
				{
					int id=(int) tblSessions.getValueAt(row, 0);
					SeatFrame sf = new SeatFrame(id);
					sf.setVisible(true);
					setVisible(false);
				}
				
			}
		});
		this.pnlUpper.add(btnBooking);
		
		
		btnBack=new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				MenuFrame mf=new MenuFrame();
				mf.setVisible(true);
				setVisible(false);
				
			}
		});
		this.pnlUpper.add(btnBack);	
		
		
		btnLogOut=new JButton("Log Out");
		btnLogOut.setForeground(Color.RED);
		btnLogOut.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				LogInHelper.CurrentUsers=null;
				HomeFrame hf=new HomeFrame();
				hf.setVisible(true);
				setVisible(false);
				
			}
		});
		this.pnlUpper.add(btnLogOut); 
	}
	
	

}
