package GUILayer;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

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

import DaoLayer.SessionDao;
import DaoLayer.TransportDao;
import EntityLayer.Session;
import EntityLayer.transport;
import HelperLayer.UtilHelper;
import HelperLayer.ValidationHelper;
import datechooser.beans.DateChooserCombo;
import datechooser.controller.DateChooseController;

public class SessionDetailFrame extends JFrame{
	
	private int ID;
	
	private JLabel lblID,lblTransportationID,lblSource,lblDestination,lblDate,lblTime,lblPrice,lblStatus,lblDesh,lblColon;
	private JTextField txtID,txtTransportationID,txtTime,txtPrice,txtStatus,txtDate;
	private DateChooserCombo dateChoose;
	
	private JButton btnSave;
	
	private String[] transportationNames;
	private int[] transportationIDs;
	private String[] hour= UtilHelper.GetHour();
	private String[] sec= UtilHelper.GetSec();
	private String[] AmPm= {"AM","PM"};
	
	private TransportDao transportDao;
	
	private JComboBox ddlFrom,ddlTo,ddlStatus,ddlTransportation,ddlHour1,ddlHour2,ddlSec1,ddlSec2,ddlAmPm1,ddlAmPm2;
	
	SessionDao sessionDao;
	SessionManagerFrame Parent;
	
	String[] Sources=UtilHelper.GetDivisionName();
	String[] Destinations=UtilHelper.GetDivisionName();
	String[] CheckBooks= {"Booking","Running"};
	
	public SessionDetailFrame(int id ,SessionManagerFrame parent)
	{
		Parent= parent;
		if(id==-1)
			this.setTitle("New");
		else
			this.setTitle("Detail");
		
		this.setSize(800, 700);
		ImageIcon icon=new ImageIcon("C:\\Users\\User\\Downloads\\PICTURE\\Dream-Bike\\Yamaha R15 V3  2.jpg");
		this.setIconImage(icon.getImage());
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.ID=id;
		
		sessionDao=new SessionDao();
		transportDao = new TransportDao();
		
		this.AddComponent();
		
		if(ID!=-1)
			this.LoadSession();
	}
		
	private void LoadSession() {
		
		Session s =sessionDao.GetByID(ID);
		
		if(s==null)
		{
			return;
		}
		else
		{
			txtID.setText(s.ID+"");
			
			int i;
			
			for(i=0;i<transportationIDs.length;i++)
			{
				if(transportationIDs[i]==s.TransporationID)
					break;
			}
			ddlTransportation.setSelectedItem(transportationNames[i]);
			ddlFrom.setSelectedItem(s.Source);
			ddlTo.setSelectedItem(s.Destination);
			ddlStatus.setSelectedItem(s.Status);
			//txtDate.setText(s.Date);
			//txtTime.setText(s.Time);
			txtPrice.setText(s.Price+"");
			
			txtID.setEnabled(false);
		}
	
	}

	public SessionDetailFrame myRefarence()
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

		
		lblTransportationID= new JLabel("Transport ID :");
		lblTransportationID.setBounds(10, 40, 150, 25);
		add(lblTransportationID);
		
		ArrayList<transport> transports = transportDao.GetByAll("");
		
		transportationNames = new String[transports.size()];
		transportationIDs = new int[transports.size()];
		
		for(int i=0;i<transports.size();i++)
		{
			transportationNames[i]=transports.get(i).Name+"-"+transports.get(i).Registration;
			transportationIDs[i]=transports.get(i).ID;
		}
		
		ddlTransportation= new JComboBox(transportationNames);
		ddlTransportation.setBounds(165, 40, 150, 25);
		add(ddlTransportation);
		
		lblSource= new JLabel("From :");
		lblSource.setBounds(10, 70, 150, 25);
		add(lblSource);
		
		ddlFrom=new JComboBox(Sources);
		ddlFrom.setBounds(165, 70, 120, 35);	
		ddlFrom.setFont(new Font("serif", Font.BOLD, 20));
		add(ddlFrom);
		
		
		lblDestination= new JLabel("To :");
		lblDestination.setBounds(10, 115, 150, 25);
		add(lblDestination);
		
		ddlTo=new JComboBox(Destinations);
		ddlTo.setBounds(165, 115, 120, 35);	
		ddlTo.setFont(new Font("serif", Font.BOLD, 20));
		add(ddlTo);
		
		
		lblDate= new JLabel("Date :");
		lblDate.setBounds(10, 165, 150, 25);
		add(lblDate);
		
		txtDate=new JTextField();
		txtDate.setBounds(165, 600, 150, 25);
		txtDate.setEnabled(false);
		add(txtDate);
		
		dateChoose = new DateChooserCombo();
		dateChoose.setBounds(165, 165, 150, 25);
		dateChoose.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
		add(dateChoose);
		
//		String d= dateChoose.getText();
		//Date date=Date.valueOf(d);
		//txtDate.setText(d);
		
		lblTime= new JLabel("Time :");
		lblTime.setBounds(10, 195, 150, 25);
		add(lblTime);
		
		txtTime=new JTextField();
		txtTime.setBounds(165, 700, 150, 25);
		//add(txtTime);
		
		ddlHour1=new JComboBox(hour);
		ddlHour1.setBounds(165, 195, 80, 20);
		add(ddlHour1);
		
		String selectedFirstHour=ddlHour1.getSelectedItem().toString();
		
		lblColon=new JLabel(":");
		lblColon.setBounds(250, 195, 10, 20);
		add(lblColon);
		
		ddlSec1=new JComboBox(sec);
		ddlSec1.setBounds(265, 195, 70, 20);
		add(ddlSec1);
		
		String selectedFirstSec=ddlSec1.getSelectedItem().toString();
		
		ddlAmPm1=new JComboBox(AmPm);
		ddlAmPm1.setBounds(345, 195, 70, 20);
		add(ddlAmPm1);
		
		String selectedFirstAmPm=ddlAmPm1.getSelectedItem().toString();
		
		lblDesh=new JLabel("-");
		lblDesh.setBounds(420, 195, 25, 20);
		add(lblDesh);
		
		ddlHour2=new JComboBox(hour);
		ddlHour2.setBounds(450, 195, 70, 20);
		add(ddlHour2);
		
		String selectedSecondHour=ddlHour2.getSelectedItem().toString();
		
		lblColon=new JLabel(":");
		lblColon.setBounds(525, 195, 25, 20);
		add(lblColon);
		
		ddlSec2=new JComboBox(sec);
		ddlSec2.setBounds(555, 195, 70, 20);
		add(ddlSec2);
		
		String selectedSecondSec=ddlSec2.getSelectedItem().toString();
		
		ddlAmPm2=new JComboBox(AmPm);
		ddlAmPm2.setBounds(635, 195, 70, 20);
		add(ddlAmPm2);
		
		String selectedSecondAmpm=ddlAmPm2.getSelectedItem().toString();
		
		//String t=selectedFirstHour +":"+ selectedFirstSec + selectedFirstAmPm +"-"+ selectedSecondHour +":"+ selectedSecondSec + selectedSecondAmpm;
		
//		String t=ddlHour1.getSelectedItem().toString() +":"+ ddlSec1.getSelectedItem().toString() + ddlAmPm1.getSelectedItem().toString() +"-"+ ddlHour2.getSelectedItem().toString() +":"+ ddlSec2.getSelectedItem().toString() + ddlAmPm2.getSelectedItem().toString();
//		
		
		//txtTime.setText(t);
		 
		lblStatus= new JLabel("Status :");
		lblStatus.setBounds(10, 225, 150, 25);
		add(lblStatus);
		
		ddlStatus=new JComboBox(CheckBooks);
		ddlStatus.setBounds(165, 225, 120, 35);	
		ddlStatus.setFont(new Font("serif", Font.BOLD, 20));
		add(ddlStatus);
		
		lblPrice= new JLabel("Price :");
		lblPrice.setBounds(10, 265, 150, 25);
		add(lblPrice);
		
		txtPrice=new JTextField();
		txtPrice.setBounds(165, 265, 150, 25);
		add(txtPrice);
		
		btnSave= new JButton("Save");
		btnSave.setBounds(180, 320, 150, 35);
		btnSave.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				String d= dateChoose.getText();
				//JOptionPane.showMessageDialog(null, d);
				
				String t=ddlHour1.getSelectedItem().toString() +":"+ ddlSec1.getSelectedItem().toString() + ddlAmPm1.getSelectedItem().toString() +"-"+ ddlHour2.getSelectedItem().toString() +":"+ ddlSec2.getSelectedItem().toString() + ddlAmPm2.getSelectedItem().toString();
				
				//JOptionPane.showMessageDialog(null, t);
				
//				if(!ValidationHelper.IsValidInt(txtID.getText()))
//				{
//					txtID.setFocusable(true);
//					//txtID.setBackground(Color.RED);
//					JOptionPane.showMessageDialog(null, "Invalid ID");
//					return;
//				}
				
				float p=Float.parseFloat(txtPrice.getText());
				if((!ValidationHelper.IsValidInt(txtPrice.getText()))&&(p<0))
				{
					//txtID.setFocusable(true);
					//txtID.setBackground(Color.RED);
					JOptionPane.showMessageDialog(null, "Invalid Price");
					return;
				}
				
				Session s = new Session();
				s.ID=Integer.parseInt(txtID.getText());
				
				String name = ddlTransportation.getSelectedItem().toString();
				int i;
				
				for(i=0;i<transportationNames.length;i++)
				{
					if(transportationNames[i].equals(name))
						break;
				}
				
				s.TransporationID=transportationIDs[i];
				
				s.Source=ddlFrom.getSelectedItem().toString();
				s.Destination=ddlTo.getSelectedItem().toString();
				s.Date= d;/*txtDate.getText();*/
				s.Time= t;/*txtTime.getText();*/
				s.Price=Float.parseFloat(txtPrice.getText());
				s.Status=ddlStatus.getSelectedItem().toString();
				
				//boolean r=false;
				boolean r;
				if(ID==-1)
					 r=sessionDao.Insert(s);
				else
					r=sessionDao.Update(s);
				
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

