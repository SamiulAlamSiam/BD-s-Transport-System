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

import DaoLayer.TransportDao;
import EntityLayer.transport;
import HelperLayer.LogInHelper;
import HelperLayer.ValidationHelper;

public class TransportDetailFrame extends JFrame{
	
	private JLabel lblID,lblName,lblTransportType,lblRegistration;
	private JTextField txtID,txtName,txtTransportType,txtRegistration;
	private JPasswordField txtPass,txtCPass;
	private JButton btnSave;
	private JComboBox transportType;
	private int ID;
	TransportDao transportDao;
	TransportManagerFrame Parent;
	
	private String[] typeOfTransport = {"Bus","Train"};
	//private String[] typeOfTransport1 = {"Transport","Manager","Admin"};
	public TransportDetailFrame(int id ,TransportManagerFrame parent)
	{
		Parent= parent;
		if(id==-1)
			this.setTitle("New");
		else
			this.setTitle("Detail");
		this.setSize(500, 500);
		ImageIcon icon=new ImageIcon("C:\\Users\\User\\Downloads\\PICTURE\\Dream-Bike\\Yamaha R15 V3  2.jpg");
		this.setIconImage(icon.getImage());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.ID=id;
		transportDao=new TransportDao();
		this.AddComponent();
		if(ID!=-1)
			this.LoadTransport();
	}
		
	private void LoadTransport() {
		
		transport t =transportDao.GetByID(ID);
		if(t==null)
		{
			return;
		}
		else
		{
			txtID.setText(t.ID+"");
			txtName.setText(t.Name);
			
			//txtTransportType.setText(t.TransportType);
			txtRegistration.setText(t.Registration+"");
			
//			transportType=new JComboBox(typeOfTransport);
//			transportType.setBounds(155, 190, 120, 35);	
//			transportType.setFont(new Font("serif", Font.BOLD, 20));
			//add(transportType);
			txtID.setEnabled(false);
			if(LogInHelper.CurrentUsers.UserType.equals("Manager"))
				txtRegistration.setEnabled(false);
		}
	
	}

	public TransportDetailFrame myRefarence()
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

		
		lblName= new JLabel("NAME :");
		lblName.setBounds(10, 40, 150, 25);
		add(lblName);
		
		txtName= new JTextField();
		txtName.setBounds(155, 40, 150, 25);
		add(txtName);
		
		lblTransportType= new JLabel("Transport Type :");
		lblTransportType.setBounds(10, 70, 150, 25);
		add(lblTransportType);
		
//		txtName= new JTextField();
//		txtName.setBounds(155, 70, 150, 25);
//		add(txtName);
		
		
		transportType=new JComboBox(typeOfTransport);
		transportType.setBounds(155, 70, 120, 35);	
		transportType.setFont(new Font("serif", Font.BOLD, 20));
		add(transportType);
		 
		
		lblRegistration= new JLabel(" Registration No :");
		lblRegistration.setBounds(10, 200, 150, 25);
		add(lblRegistration);
		
		txtRegistration= new JTextField();
		txtRegistration.setBounds(155, 200, 150, 25);
		add(txtRegistration);
		
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
				
				transport t = new transport();
				t.ID=Integer.parseInt(txtID.getText());
				t.Name=txtName.getText();
				t.Registration=Integer.parseInt(txtRegistration.getText());
				
				t.TransportType= transportType.getSelectedItem().toString();
				
				boolean r=false;
				if(ID==-1)
					 r=transportDao.Insert(t);
				else
					r=transportDao.Update(t);
				
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
