package GUILayer;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import DaoLayer.SessionDao;
import DaoLayer.UserDao;
import EntityLayer.Session;
import EntityLayer.User;
import HelperLayer.LogInHelper;
import HelperLayer.SessionHelper;
import HelperLayer.UtilHelper;

public class MenuFrame extends JFrame{
	private JButton btnProfile,btnGames,btnUsers,btnLogOut,btnBooking,btnTrain,btnTransport,btnPending;
	public JLabel lblChoose;
	SessionDao sessionDao;
	
    private UserDao userDao = new UserDao();
	public MenuFrame()
	{
		this.setTitle("Welcome ,"+LogInHelper.CurrentUsers.Name);
		this.setSize(450, 450);
		ImageIcon icon=new ImageIcon("C:\\Users\\User\\Downloads\\PICTURE\\Dream-Bike\\Yamaha R15 V3  2.jpg");
		this.setIconImage(icon.getImage());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		sessionDao=new SessionDao();
		
		//this.setLayout(new GridLayout(1, 3));
		this.setLayout(null);
		this.AddComponen();
	}
	
	public MenuFrame myRefarence()
	{
		return this;
	}
	
	public void AddComponen()
	{
		btnProfile=new JButton("Profile");
		btnProfile.setBounds(0, 0, 120, 35);
		btnProfile.setFont(new Font("serif", Font.BOLD, 20));
		btnProfile.setForeground(Color.BLUE);
		btnProfile.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				RegistrationFrame rf=new RegistrationFrame(LogInHelper.CurrentUsers.ID,myRefarence()); 
				rf.setVisible(true);
				setVisible(false);
				
			}
		});
		add(btnProfile); 
		
		btnGames=new JButton("Games");
		btnGames.setBounds(0, 180, 120, 25);
		btnGames.setFont(new Font("serif", Font.BOLD, 20));
		btnGames.setForeground(Color.BLUE);
		//add(btnGames);
		
		
		
		btnUsers=new JButton("Users");
		btnUsers.setBounds(125, 0, 120, 35);
		btnUsers.setFont(new Font("serif", Font.BOLD, 20));
		btnUsers.setForeground(Color.BLUE);
		btnUsers.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
//				UserListFrame uf=new UserListFrame();
//				uf.setVisible(true);
				
				
				UserManagerFrame umf=new UserManagerFrame();
				umf.setVisible(true);
				
				setVisible(false);
				
			}
		});
		
		if(LogInHelper.CurrentUsers.UserType.equals("Admin"))
			add(btnUsers);
		
		
		ArrayList<User> pendings = userDao.GetByStatus(-1);
		btnPending=new JButton("Pending ("+pendings.size()+")");
		btnPending.setBounds(125, 40, 170, 35);
		btnPending.setFont(new Font("serif", Font.BOLD, 20));
		btnPending.setForeground(Color.BLUE);
		btnPending.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {

//				UserManagerFrame umf=new UserManagerFrame();
//				umf.setVisible(true);
				
				PendingManagerFrame pnf=new PendingManagerFrame();
				pnf.setVisible(true);
				
				setVisible(false);
				
			}
		});
		
		if(LogInHelper.CurrentUsers.UserType.equals("Admin"))
			add(btnPending);
		
		btnLogOut=new JButton("Log Out");
		btnLogOut.setBounds(300, 0, 120, 35);
		btnLogOut.setFont(new Font("serif", Font.BOLD, 20));
		btnLogOut.setForeground(Color.BLUE);
		btnLogOut.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				LogInHelper.CurrentUsers=null;
				HomeFrame hf=new HomeFrame();
				hf.setVisible(true);
				setVisible(false);
				
			}
		});
		add(btnLogOut); 
		
		
		btnTransport=new JButton("Transport");
		btnTransport.setBounds(0, 40, 120, 35);
		btnTransport.setFont(new Font("serif", Font.BOLD, 20));
		btnTransport.setForeground(Color.BLUE);
		btnTransport.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				TransportManagerFrame tmf = new TransportManagerFrame();
				tmf.setVisible(true);
				setVisible(false);
				
			}
		});
		if(!LogInHelper.CurrentUsers.UserType.equals("User"))
			add(btnTransport);
		
		lblChoose=new JLabel("Choose Your Desire Transport ");
		lblChoose.setBounds(30, 120, 450, 100);
		lblChoose.setFont(new Font("serif", Font.BOLD, 30));
		lblChoose.setForeground(Color.RED);
		add(lblChoose);
		
		btnBooking=new JButton("Booking");
		btnBooking.setBounds(100, 250, 120, 100);
		btnBooking.setFont(new Font("serif", Font.BOLD, 20));
		btnBooking.setForeground(Color.BLUE);
		btnBooking.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
//				ArrayList<Session> s= sessionDao.GetByAll("", ""); 
//			    
//				SessionHelper.CurrentSession=s;
				SessionManagerFrame smf=new SessionManagerFrame();
				smf.setVisible(true);
				//setVisible(false);
				
			}
		});
		add(btnBooking);
		
		btnTrain=new JButton("Train");
		btnTrain.setBounds(250, 220, 120, 100);
		btnTrain.setFont(new Font("serif", Font.BOLD, 20));
		btnTrain.setForeground(Color.BLUE);
		btnTrain.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				//ArrayList<Session> s= sessionDao.GetByAll("", ""); 
			    
				//SessionHelper.CurrentSession=s;
				SessionManagerFrame smf=new SessionManagerFrame();
				smf.setVisible(true);
				setVisible(false);
				
			}
		});
		//add(btnTrain);
		
	}

}
