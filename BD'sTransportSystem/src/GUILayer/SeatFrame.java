package GUILayer;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import DaoLayer.SessionDao;
import DaoLayer.SessionDetailsDao;
import DaoLayer.SessionSeatDao;
import EntityLayer.Session;
import EntityLayer.SessionSeat;
import HelperLayer.LogInHelper;


public class SeatFrame extends JFrame{
	
	//int j=1;
	int SessionID;
	private JButton btnBook;
	SessionDetailsDao dao = new SessionDetailsDao();
	JPanel pnlLeft,pnlRight;
	JLabel lblBooks;
	ArrayList<String> seatBookings = new ArrayList<String>();
	ArrayList<JButton>btnSeatBookings=new ArrayList<>();
	ArrayList<String>btnSeatBooked=new ArrayList<>();
	
	SessionSeatDao sessionSeatDao;
	SessionDao sessionDao;
	
	public SeatFrame(int session)
	{
		sessionSeatDao=new SessionSeatDao();
		sessionDao = new SessionDao();
		
		SessionID = session;
		this.setSize(370,350);
		ImageIcon icon=new ImageIcon("C:\\Users\\User\\Downloads\\PICTURE\\Dream-Bike\\Yamaha R15 V3  2.jpg");
		this.setIconImage(icon.getImage());
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		
		pnlLeft = new JPanel(new GridLayout(5,2,2,2)); 
		pnlLeft.setBorder(new LineBorder(Color.BLACK));
		pnlLeft.setBounds(5, 5, 160, 250);
		this.add(pnlLeft);
		
		pnlRight = new JPanel(new GridLayout(5,2,2,2));
		pnlRight.setBorder(new LineBorder(Color.green));
		pnlRight.setBounds(175, 5, 160, 250);
		this.add(pnlRight);
		
		lblBooks = new JLabel("");
		lblBooks.setBounds(5, 270, 150, 20);
		this.add(lblBooks);
		
		btnBook=new JButton("BOOK");
		btnBook.setBounds(175, 265, 120, 30);
		btnBook.setBackground(Color.GREEN);
		btnBook.setForeground(Color.black);
		btnBook.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				
				boolean r=false;
				
				SessionSeat ss = new SessionSeat();
				
				for(String i:btnSeatBooked)
				{
					//ss.ID=LogInHelper.CurrentUsers.ID;
					ss.SessionID=SessionID;
					ss.SeatNO=i;
					ss.UserID=LogInHelper.CurrentUsers.ID;
					//j++;
					r=sessionSeatDao.Insert(ss);
				}
				
				SessionSeatManagerFrame ssmf=new SessionSeatManagerFrame();
				ssmf.setVisible(true);
				setVisible(false);
				
//				ss.ID=LogInHelper.CurrentUsers.ID;
//				ss.SessionID=SessionID;
//				ss.SeatNO="1";
//				ss.UserID=LogInHelper.CurrentUsers.ID;
//				
//				
//				boolean r=false;
//				r=sessionSeatDao.Insert(ss);
		
//				JOptionPane.showMessageDialog(null, LogInHelper.CurrentUsers.Name+" ("+btnSeatBooked+")");
			}
		});
		btnBook.setFont(new Font("serif", Font.BOLD+Font.ITALIC, 20));
		add(btnBook);
		
		this.AddSeat();
	}
	private void AddSeat() {
		
		for(int i=1;i<=20;i++)
		{
			JPanel pnl = pnlLeft;
			if(i>10)
				pnl=pnlRight;
			
			JButton btn = new JButton(i+"");
			btnSeatBookings.add(btn);
			btn.setEnabled(!dao.SeatBook(SessionID, i));
			
			pnl.add(btn);
			
			btn.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					JButton btn = (JButton)e.getSource();
					
					//JOptionPane.showMessageDialog(null, e.getActionCommand());
					//btnSeatBooked.add(e.getActionCommand());
					
					for(String s:seatBookings)
					{
						if(s.equals(btn.getText()))
						{
							btn.setBackground(Color.white);
							seatBookings.remove(btn.getText());
							PopulateBookings();
							btnSeatBooked.remove(e.getActionCommand());
							return;
						}
					}
					
					btn.setBackground(Color.green);
					seatBookings.add(btn.getText());
					btnSeatBooked.add(e.getActionCommand());
					PopulateBookings();
				}
			});
		}
		
		
	}
	
	public void PopulateBookings()
	{
		String seat="";
		for(String s:seatBookings)
		{
			seat+=s+", ";
		}
		lblBooks.setText(seat);
	}
	
	

}
