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

import DaoLayer.UserDao;
import EntityLayer.User;
import HelperLayer.LogInHelper;
import HelperLayer.ValidationHelper;

public class RegistrationFrame extends JFrame{
	
	private JLabel lblID,lblName,lblUserName,lblPass,lblCPass,lblPic,lblContact,lblUserType,lblUserType1;
	private JTextField txtID,txtName,txtUserName,txtContact;
	private JPasswordField txtPass,txtCPass;
	private JButton btnSave,btnBack;
	private JRadioButton rbtnMale,rbtnFemale;
	//private ImageIcon img;
	ButtonGroup grpmf;
	private JComboBox ddluserType;
	private int ID;
	UserDao userDao;
	JFrame Parent;
	
	private String[] typeOfUser = {"User","Manager"};
	private String[] typeOfUser1 = {"User","Manager","Admin"};
	public RegistrationFrame(int id,JFrame parent)
	{
		Parent=parent;
		if(id==-1)
			this.setTitle("Registration");
		else
			this.setTitle("Profile");
		this.setSize(500, 500);
		ImageIcon icon=new ImageIcon("C:\\Users\\User\\Downloads\\PICTURE\\Dream-Bike\\Yamaha R15 V3  2.jpg");
		this.setIconImage(icon.getImage());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.ID=id;
		userDao=new UserDao();
		this.AddComponent();
		if(ID!=-1)
			this.LoadUser();
	}
	
	
	
	private void LoadUser() {
		
		User u =userDao.GetByID(ID);
		if(u==null)
		{
			JOptionPane.showMessageDialog(null, "Invalid ID");
			btnSave.setEnabled(false);
			return;
		}
		else
		{
			txtID.setText(u.ID+"");
			txtName.setText(u.Name);
			txtContact.setText(u.ContactNo);
			txtUserName.setText(u.UserName);
			txtPass.setText(u.Password);
			txtCPass.setText(u.Password);
			
			String[] userTypes = LogInHelper.CurrentUsers != null && LogInHelper.CurrentUsers.UserType.equals("Admin")?typeOfUser1:typeOfUser;
//			ddluserType=new JComboBox(userTypes);
//			ddluserType.setBounds(155, 200, 120, 35);	
//			ddluserType.setFont(new Font("serif", Font.BOLD, 20));
//			add(ddluserType);
			
			ddluserType.setSelectedItem(userTypes);
//			userType=new JComboBox(typeOfUser);
//			userType.setBounds(155, 190, 120, 35);	
//			userType.setFont(new Font("serif", Font.BOLD, 20));
//			add(userType);
			txtID.setEnabled(false);
		}
	
	}



	public RegistrationFrame myRefarence()
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
		//add(rbtnMale);
		
		rbtnFemale= new JRadioButton("Female");
		rbtnFemale.setBounds(320, 45, 80, 30);
		rbtnFemale.setFont(new Font("serif", Font.BOLD, 15));
		//add(rbtnFemale);
		
		grpmf=new ButtonGroup();
		grpmf.add(rbtnMale);
		grpmf.add(rbtnFemale);
		

		
		lblName= new JLabel("NAME :");
		lblName.setBounds(10, 40, 150, 25);
		add(lblName);
		
		txtName= new JTextField();
		txtName.setBounds(155, 40, 150, 25);
		add(txtName);
		
		lblUserName= new JLabel("User Name :");
		lblUserName.setBounds(10, 70, 150, 25);
		add(lblUserName);
		
		txtUserName= new JTextField();
		txtUserName.setBounds(155, 70, 150, 25);
		add(txtUserName);
		
		lblPass= new JLabel("password :");
		lblPass.setBounds(10, 105, 150, 25);
		add(lblPass);
		
		txtPass= new JPasswordField();
		txtPass.setBounds(155, 105, 150, 25);
		add(txtPass);
		
		lblCPass= new JLabel("Confirm Password :");
		lblCPass.setBounds(10, 135, 150, 25);
		add(lblCPass);
		
		txtCPass= new JPasswordField();
		txtCPass.setBounds(155, 135, 150, 25);
		add(txtCPass);
		
		lblContact= new JLabel("Contact No. :");
		lblContact.setBounds(10, 165, 150, 25);
		add(lblContact);
		
		txtContact= new JTextField();
		txtContact.setBounds(155, 165, 150, 25);
		add(txtContact);

//		if(userType.equals("Admin"))
//		{
//			userType=new JComboBox(typeOfUser1);
//			userType.setBounds(155, 190, 120, 35);	
//			userType.setFont(new Font("serif", Font.BOLD, 20));
//			add(userType);
//		}
		
		 String[] userTypes = LogInHelper.CurrentUsers != null && LogInHelper.CurrentUsers.UserType.equals("Admin")?typeOfUser1:typeOfUser;
			ddluserType=new JComboBox(userTypes);
			ddluserType.setBounds(155, 200, 120, 35);	
			ddluserType.setFont(new Font("serif", Font.BOLD, 20));
			add(ddluserType);
		 
		lblUserType= new JLabel("User Type :");
		lblUserType.setBounds(10, 200, 150, 25);
		add(lblUserType);
		
		btnSave= new JButton("Save");
		btnSave.setBounds(210, 260, 100, 35);
		btnSave.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				if(!ValidationHelper.IsValidInt(txtID.getText()))
				{
					txtID.setFocusable(true);
					//txtID.setBackground(Color.RED);
					JOptionPane.showMessageDialog(null, "Invalid ID");
					return;
				}
				
				if(!txtPass.getText().equals(txtCPass.getText()))
				{
					txtPass.setFocusable(true);
					JOptionPane.showMessageDialog(null, "Password & Confirmed Passwoed should be Match");
					return;
				}
				
				User u = new User();
				u.ID=Integer.parseInt(txtID.getText());
				u.Name=txtName.getText();
				u.UserName=txtUserName.getText();
				u.Password=txtPass.getText();
				u.UserType= ddluserType.getSelectedItem().toString();
				u.ContactNo= txtContact.getText();
				if(u.UserType.equals("Manager"))
					u.Status=-1;
				else
					u.Status=0;
				
				boolean r=false;
				if(ID==-1)
					 r=userDao.Insert(u);
				else
					r=userDao.Update(u);
				
				if(r)
				{
					LogInFrame lf=new LogInFrame(myRefarence());
					lf.setVisible(true);
					setVisible(false);
				}
			}
		});
		add(btnSave);
		
		btnBack= new JButton("Back");
		btnBack.setBounds(105, 260, 100, 35);
		btnBack.addActionListener(new ActionListener() {
			 
			public void actionPerformed(ActionEvent arg0) {
				Parent.setVisible(true);
				setVisible(false);
				
			}
		});
		add(btnBack);
	}
}
