import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
public class Login extends JFrame {
	Connection conn;
	ResultSet rs;
	PreparedStatement pst;
	Statement st;


	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public Login() {
		super("Login");
		conn=Connect.connectDB();
		try{
		String s="Create table IF NOT EXISTS Employee(code int(10) PRIMARY KEY,name char(100) NOT NULL,email char(40) NOT NULL,password varchar(30) NOT NULL,sec_q varchar(50) NOT NULL, answer varchar(30) NOT NULL)";
		st=conn.createStatement();
		st.executeUpdate(s);
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null,e);
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 200, 450, 283);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEmployeeCode = new JLabel("Employee Code");
		lblEmployeeCode.setBounds(48, 51, 117, 15);
		contentPane.add(lblEmployeeCode);
		
		textField = new JTextField();
		textField.setBounds(208, 49, 190, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Password");
		lblNewLabel.setBounds(47, 96, 117, 15);
		contentPane.add(lblNewLabel);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(208, 94, 190, 19);
		contentPane.add(passwordField);
		
		JButton btnL = new JButton("Login");
		btnL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String sql="select * from Employee where code=? and password=?";
				try
				{
					if(textField.getText().equals("") || new String(passwordField.getPassword()).equals(""))
					{
						throw new Exception();
					}
					pst=conn.prepareStatement(sql);
					pst.setInt(1,Integer.parseInt(textField.getText()));
					pst.setString(2,new String(passwordField.getPassword()));
					rs=pst.executeQuery();
					if(rs.next())
					{
						pst.close();
						rs.close();
						setVisible(false);
						Home ob=new Home();
						ob.setter(Integer.parseInt(textField.getText()));
						ob.setVisible(true);
						
						
					}
					else
						JOptionPane.showMessageDialog(null,"Incorrect username or password, try again");
					
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(null,e);	
				}
				
				
			}
		});
		btnL.setBounds(69, 156, 117, 25);
		contentPane.add(btnL);
		
		JButton btnForgotPassword = new JButton("Forgot Password");
		btnForgotPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				ForgotPassword ob=new ForgotPassword();
				ob.setVisible(true);
			}
		});
		btnForgotPassword.setBounds(225, 156, 155, 25);
		contentPane.add(btnForgotPassword);
		
		JButton btnB = new JButton("Register");
		btnB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				SignUp ob=new SignUp();
				ob.setVisible(true);
			}
		});
		btnB.setBounds(161, 204, 117, 25);
		contentPane.add(btnB);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Enter Your Credentials", TitledBorder.LEADING, TitledBorder.TOP, null, Color.ORANGE));
		panel.setBounds(12, 12, 412, 237);
		contentPane.add(panel);
	}
}
