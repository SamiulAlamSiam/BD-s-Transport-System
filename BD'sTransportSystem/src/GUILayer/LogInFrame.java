package GUILayer;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.attribute.UserPrincipalLookupService;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import DaoLayer.UserDao;
import EntityLayer.User;
import HelperLayer.LogInHelper;

public class LogInFrame extends JFrame{

	private JLabel lblusername,lblpass;
	private JTextField txtusername;
	private JPasswordField txtpass;
	private JButton btnlogin,btnback;
	UserDao userdao;
	JFrame Parent;
	
	public LogInFrame(JFrame parent)
	{
		this.setTitle("Log In"); 
		this.setSize(450, 450);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		ImageIcon icon=new ImageIcon("C:\\Users\\User\\Downloads\\PICTURE\\Dream-Bike\\Yamaha R15 V3  2.jpg");
		this.setIconImage(icon.getImage());
		Parent=parent;
		userdao = new UserDao();
		this.AddComponen();
	}
	public void AddComponen()
	{
		lblusername= new JLabel("User Nmae : ");
		lblusername.setBounds(50, 80, 120, 20);
		lblusername.setFont(new Font("serif", Font.BOLD, 20));
		lblusername.setForeground(Color.BLACK);
		add(lblusername);
		
		txtusername= new JTextField("Name"); 
		txtusername.setBounds(180, 80, 120, 30);
		//txtusername.setFont(new Font("serif", Font.BOLD, 20));
		//txtusername.setForeground(Color.LIGHT_GRAY); 
		add(txtusername);
		
		
		lblpass= new JLabel("Password : ");
		lblpass.setBounds(50, 140, 120, 20);
		lblpass.setFont(new Font("serif", Font.BOLD, 20));
		lblpass.setForeground(Color.BLACK);
		add(lblpass);
		 
		txtpass= new JPasswordField();  
		txtpass.setBounds(180, 140, 120, 30); 
		add(txtpass);
		
		btnback= new JButton("Back");
		btnback.setBounds(150, 200, 80, 30);
		btnback.addActionListener(new ActionListener() {
			 
			public void actionPerformed(ActionEvent arg0) {
				Parent.setVisible(true);
				setVisible(false);
				
			}
		});
		add(btnback);
		
		btnlogin= new JButton("Log In");
		btnlogin.setBounds(250, 200, 80, 30);
		btnlogin.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
				User u = userdao.LogIn(txtusername.getText(),txtpass.getText());
				
				if(u==null)
				{
					JOptionPane.showMessageDialog(null, "Invalid User Name OR Password");
					return ;
				}
				
				else if(u.Status!=0)
				{
					JOptionPane.showMessageDialog(null, "Your Registration Is Not accepted Yet By ADMIN");
					return;
				}
				
				else
				{
					LogInHelper.CurrentUsers=u;
					MenuFrame mf=new MenuFrame();
					mf.setVisible(true);
					setVisible(false);
				}
				
			}
		});
		add(btnlogin);
	}

}
