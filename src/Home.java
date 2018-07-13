import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import java.awt.Color;

import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;

public class Home extends JFrame {

	private JPanel contentPane;
	JPanel panel;
    Connection conn;
    Statement st;
    ResultSet rs;
	int code;
	String nam,q;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home frame = new Home();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Home() {
		super("Home");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 100, 549, 561);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JLabel lblNewLabel = new JLabel("image");
		lblNewLabel.setIcon(new ImageIcon("images/registration.png"));
		lblNewLabel.setBounds(109, 96, 135, 147);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon("images/turn-notifications-on-button.png"));
		lblNewLabel_1.setBounds(309, 108, 135, 135);
		contentPane.add(lblNewLabel_1);
		
		JButton btnTaskRegistration = new JButton("New Task");
		btnTaskRegistration.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Registration ob=new Registration();
				setVisible(false);
				ob.setter(code);
				ob.setVisible(true);
			}
		});
		btnTaskRegistration.setBounds(85, 255, 159, 36);
		contentPane.add(btnTaskRegistration);
		
		JButton btnT = new JButton("View/Close Task");
		btnT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				Completion ob=new Completion(code);
				//ob.setter(code);
				ob.setVisible(true);
			}
		});
		btnT.setBounds(293, 255, 159, 36);
		contentPane.add(btnT);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon("images/if_task_completed_67344.png"));
		lblNewLabel_2.setBounds(204, 324, 128, 135);
		contentPane.add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("Recently Closed Tasks");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				Closed ob=new Closed(code);
				//ob.setter(code);
				ob.setVisible(true);
			}
		});
		btnNewButton.setBounds(169, 471, 192, 36);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Logout");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				Login ob=new Login();
				ob.setVisible(true);
			}
		});
		btnNewButton_1.setForeground(new Color(255, 102, 51));
		btnNewButton_1.setBounds(377, 56, 117, 25);
		contentPane.add(btnNewButton_1);
		
		panel = new JPanel();
		panel.setForeground(new Color(51, 153, 255));
		//panel.setBorder(new TitledBorder(null, "ss", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 69, 0)));
		panel.setBounds(27, 23, 499, 498);
		contentPane.add(panel);
	}
	
	public void setter(int code)
	{
		this.code=code;
		
		try{
		conn=Connect.connectDB();
	 q="select name from Employee where code="+code;
		st=conn.createStatement();
		rs=st.executeQuery(q);
		rs.next();
	    nam=rs.getString(1);
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e);
		}
		
		panel.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Welcome "+nam, TitledBorder.LEADING, TitledBorder.TOP, null, new Color(102, 51, 0)));
	}
}
