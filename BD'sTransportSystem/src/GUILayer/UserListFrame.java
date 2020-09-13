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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import DaoLayer.UserDao;
import EntityLayer.User;

public class UserListFrame extends JFrame{
	private JPanel pnlTable;
	private UserDao userDao;
	
	public UserListFrame()
	{
		this.setTitle("User List");
		this.setSize(500, 450);
		ImageIcon icon=new ImageIcon("C:\\Users\\User\\Downloads\\PICTURE\\Dream-Bike\\Yamaha R15 V3  2.jpg");
		this.setIconImage(icon.getImage());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		userDao=new UserDao();
		this.AddComponent();
	}

	public void AddComponent() {
		pnlTable = new JPanel();
		pnlTable.setBorder(new LineBorder(Color.CYAN,2));
		pnlTable.setBounds(5, 5, 470, 400);
		pnlTable.setLayout(null);
		add(pnlTable);
		
		JPanel pnlHeader = new JPanel();
		pnlHeader.setLayout(new GridLayout(1, 5, 2, 2));
		pnlHeader.add(new JLabel("ID"));
		pnlHeader.add(new JLabel("Name"));
		pnlHeader.add(new JLabel("UserName"));
		pnlHeader.add(new JLabel("UserType"));
		pnlHeader.add(new JLabel("ContactNo"));
		pnlHeader.setBorder(new LineBorder(Color.GRAY,2));
		pnlHeader.setBounds(7, 5, 450, 25);
		ArrayList<User> list = userDao.GetByAll("");
		pnlTable.add(pnlHeader);
		
		for (int i=0;i<list.size();i++)
		{
			JPanel pnlContent = new JPanel();
			pnlContent.setLayout(new GridLayout(1, 5, 2, 2));
			pnlContent.add(new JLabel(list.get(i).ID+""));
			pnlContent.add(new JLabel(list.get(i).Name+""));
			pnlContent.add(new JLabel(list.get(i).UserName+""));
			pnlContent.add(new JLabel(list.get(i).UserType+""));
			pnlContent.add(new JLabel(list.get(i).ContactNo+""));
			pnlContent.setBorder(new LineBorder(Color.GRAY,2));
			pnlContent.setBounds(7, (i+1)*30, 450, 25);
			pnlTable.add(pnlContent);
		}
		
	
	}

}
