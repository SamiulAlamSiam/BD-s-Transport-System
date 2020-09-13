package GUILayer;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import DaoLayer.SessionSeatDao;
import EntityLayer.SessionSeat;
import HelperLayer.ValidationHelper;

public class SessionSeatDetailFrame extends JFrame{
	
	private JLabel lblID,lblSessionID,lblSeatNO,lblUserID;
	private JTextField txtID,txtSessionID,txtSeatNO,txtUserID;

	private JButton btnSave;
	private JRadioButton rbtnMale,rbtnFemale;
	//private ImageIcon img;
	ButtonGroup grpmf;
	
	private JComboBox userType;
	
	private int ID;
	
	SessionSeatDao userDao;
	SessionSeatManagerFrame Parent;
	
	private String[] typeOfSessionSeat = {"SessionSeat","Manager","Admin"};
	//private String[] typeOfSessionSeat1 = {"SessionSeat","Manager","Admin"};
	
	public SessionSeatDetailFrame(int id ,SessionSeatManagerFrame parent)
	{
		Parent= parent;
		if(id==-1)
			this.setTitle("New");
		else
			this.setTitle("Detail");
		this.setSize(500, 500);
		ImageIcon icon=new ImageIcon("C:\\Users\\User\\Downloads\\PICTURE\\Dream-Bike\\Yamaha R15 V3  2.jpg");
		this.setIconImage(icon.getImage());
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.ID=id;
		userDao=new SessionSeatDao();
		this.AddComponent();
		if(ID!=-1)
			this.LoadSessionSeat();
	}
		
	private void LoadSessionSeat() {
		
		SessionSeat ss =userDao.GetByID(ID);
		if(ss==null)
		{
			return;
		}
		else
		{
			txtID.setText(ss.ID+"");
			txtSessionID.setText(ss.SessionID+"");
			txtSeatNO.setText(ss.SeatNO);
			txtUserID.setText(ss.UserID+"");
			
//			userType=new JComboBox(typeOfSessionSeat);
//			userType.setBounds(155, 190, 120, 35);	
//			userType.setFont(new Font("serif", Font.BOLD, 20));
			//add(userType);
			txtID.setEnabled(false);
		}
	
	}

	public SessionSeatDetailFrame myRefarence()
	{
		return this;
	}
	 
	public void AddComponent()
	{
		lblID= new JLabel("ID :");
		lblID.setBounds(10, 10, 150, 25);
		//add(lblID);
		
		txtID= new JTextField("1");
		txtID.setBounds(155, 10, 150, 25);
		txtID.setEditable(false);
		add(txtID);
		
		rbtnMale= new JRadioButton("Male");
		rbtnMale.setBounds(320, 10, 80, 30);
		rbtnMale.setFont(new Font("serif", Font.BOLD, 20));
		add(rbtnMale);
		
		rbtnFemale= new JRadioButton("Female");
		rbtnFemale.setBounds(320, 45, 80, 30);
		rbtnFemale.setFont(new Font("serif", Font.BOLD, 15));
		add(rbtnFemale);
		
		grpmf=new ButtonGroup();
		grpmf.add(rbtnMale);
		grpmf.add(rbtnFemale);
		

		
		lblSessionID= new JLabel("SessionID :");
		lblSessionID.setBounds(10, 40, 150, 25);
		add(lblSessionID);
		
		txtSessionID= new JTextField();
		txtSessionID.setBounds(155, 40, 150, 25);
		add(txtSessionID);
		
		lblSeatNO= new JLabel("SeatNO :");
		lblSeatNO.setBounds(10, 70, 150, 25);
		add(lblSeatNO);
		
		txtSeatNO= new JTextField();
		txtSeatNO.setBounds(155, 70, 150, 25);
		add(txtSeatNO);
		
		lblUserID= new JLabel("UserID :");
		lblUserID.setBounds(10, 105, 150, 25);
		add(lblUserID);
		
		txtUserID= new JTextField();
		txtUserID.setBounds(155, 105, 150, 25);
		add(txtUserID);

		
		btnSave= new JButton("Save");
		btnSave.setBounds(190, 260, 150, 35);
		btnSave.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				if(!ValidationHelper.IsValidInt(txtID.getText()))
				{
					txtID.setFocusable(true);
					//txtID.setBackground(Color.RED);
					JOptionPane.showMessageDialog(null, "Invalid ID");
					return;
				}
				
				
				
				SessionSeat ss = new SessionSeat();
				ss.ID=Integer.parseInt(txtID.getText());
				ss.SessionID=Integer.parseInt(txtSessionID.getText());
				ss.SeatNO=txtSeatNO.getText();
				ss.UserID=Integer.parseInt(txtUserID.getText());
				
				
				boolean r=false;
				if(ID==-1)
					 r=userDao.Insert(ss);
				else
					r=userDao.Update(ss);
				
				if(r)
				{
					setVisible(false);
					Parent.PopulateTable();
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Something Went Wrong"); 
				}
			}
		});
		add(btnSave);
	}
}
