package GUILayer;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
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

import DaoLayer.UserDao;
import EntityLayer.User;
import TableModelLayer.UserTableModel;

public class PendingManagerFrame extends JFrame {
	
	private JPanel pnlUpper,pnlLower;
	private JLabel lblSearch;
	private JTextField txtSraech;
	private JButton btnSearch,btnAccept,btnReject,btnBack;
	private JTable tblUsers;
	private UserDao userDao;
	
	public PendingManagerFrame()
	{
		this.setSize(600,450);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		ImageIcon icon=new ImageIcon("C:\\Users\\User\\Downloads\\PICTURE\\Dream-Bike\\Yamaha R15 V3  2.jpg");
		this.setIconImage(icon.getImage());
		userDao= new UserDao();
		
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
//		UserDetailFrame udf = new UserDetailFrame(id, this);
//		udf.setVisible(true);
	}

	private void AddLowerComponent() {
		tblUsers=new JTable();
		tblUsers.setBackground(Color.WHITE);
		
		JScrollPane sp= new JScrollPane();
		sp.setBounds(5, 15, 490, 320);
		this.pnlLower.add(sp);
		sp.setViewportView(tblUsers);
		
		this.PopulateTable();
		
	}

	public void PopulateTable() {
		ArrayList<User> userList= userDao.GetByStatus(-1);
		
		UserTableModel model= new UserTableModel(userList);
		tblUsers.setModel(model);
		
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
		
//		btnAdd=new JButton("Add");
//		btnAdd.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				ShowDetailFrame(-1);
//				
//			}
//		});
//		this.pnlUpper.add(btnAdd);
		
		btnAccept=new JButton("Accept");
		btnAccept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row=tblUsers.getSelectedRow();
				if(row==-1)
				{
					JOptionPane.showMessageDialog(null, "Please Select The Row First");
					return;
				}
				else
				{
					int id=(int) tblUsers.getValueAt(row, 0);
					
					User u = userDao.GetByID(id);
					
					u.Status=0;
					
					userDao.Update(u);
					PopulateTable();
					
				}
				
			}
		});
		this.pnlUpper.add(btnAccept);
		

		btnReject=new JButton("Delete");
		btnReject.addActionListener(new ActionListener() {
	
			public void actionPerformed(ActionEvent e) {
				
				int row=tblUsers.getSelectedRow();
				if(row==-1)
				{
					JOptionPane.showMessageDialog(null, "Please Select The Row First");
					return;
				}
				else
				{
					int id=(int) tblUsers.getValueAt(row, 0);
					
					boolean r=userDao.Delete(id);
					
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
		this.pnlUpper.add(btnReject);
		
		btnBack=new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				MenuFrame mf=new MenuFrame();
				mf.setVisible(true);
				setVisible(false);
				
			}
		});
		this.pnlUpper.add(btnBack);	
	}

}
