package GUILayer;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class HomeFrame extends JFrame{
	private JButton  btnSignUp,btnLogIn;
	private JLabel lblTitle;
	private JPanel pnlUpper,pnlLower;
	
	public HomeFrame()
	{
		this.setTitle("Home");
		this.setSize(600, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		ImageIcon icon=new ImageIcon("C:\\Users\\User\\Downloads\\PICTURE\\Dream-Bike\\Yamaha R15 V3  2.jpg");
		this.setIconImage(icon.getImage());
		this.AddComponent();
	}
	
	public HomeFrame myRefarence()
	{
		return this;
	}
	

	public void AddComponent() {
		
		pnlUpper= new JPanel();
		pnlUpper.setBorder(new LineBorder(Color.RED,2));
		pnlUpper.setBounds(0, 0, 580, 120);
		pnlUpper.setLayout(null);
		add(pnlUpper);
		this.UComponent();
		
		pnlLower= new JPanel();
		pnlLower.setBorder(new LineBorder(Color.GREEN,2));
		pnlLower.setBounds(0, 125, 580, 350);
		pnlLower.setLayout(new FlowLayout());
		pnlLower.setBackground(Color.WHITE);
		add(pnlLower);
		this.LComponent();
		
		
	}

	private void LComponent() {
		
		lblTitle=new JLabel("Welcome To BD's Transport System");
		lblTitle.setFont(new Font("Serif", Font.BOLD+Font.ITALIC, 25));
		this.pnlLower.add(lblTitle);
	}

	private void UComponent() {
		btnSignUp=new JButton("Sign Up");
		btnSignUp.setBounds(300, 35, 100, 50);
//		btnSignUp.setForeground(Color.CYAN);
//		btnSignUp.setBackground(Color.BLACK);
		btnSignUp.setFont(new Font("serif", Font.BOLD, 20));
		btnSignUp.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				RegistrationFrame rf=new RegistrationFrame(-1,myRefarence());
				rf.setVisible(true);
				setVisible(false);
				
			}
		});
		this.pnlUpper.add(btnSignUp);
		
		btnLogIn=new JButton("Log In");
		btnLogIn.setBounds(460, 35, 100, 50);
//		btnLogIn.setForeground(Color.CYAN);
//		btnLogIn.setBackground(Color.BLACK);
		btnLogIn.setFont(new Font("serif", Font.BOLD, 20));
		btnLogIn.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				LogInFrame lf= new LogInFrame(myRefarence());
				lf.setVisible(true);
				setVisible(false);
				
			}
		});
		this.pnlUpper.add(btnLogIn);
		
	}

}
