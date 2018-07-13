import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;

import javax.swing.JOptionPane;
public class SignUp extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JPasswordField passwordField;
	Connection conn;
	ResultSet rs;
	PreparedStatement pst;
	private JTextField textField_2;
	private JTextField textField_3;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignUp frame = new SignUp();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public SignUp() {
		super("Register");
		conn=Connect.connectDB();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 200, 450, 389);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Employee Code");
		lblNewLabel.setBounds(45, 49, 123, 22);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(216, 51, 205, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblE = new JLabel("Employee Name");
		lblE.setBounds(45, 83, 123, 15);
		contentPane.add(lblE);
		
		textField_1 = new JTextField();
		textField_1.setBounds(216, 83, 205, 19);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(45, 160, 123, 15);
		contentPane.add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(216, 156, 205, 19);
		contentPane.add(passwordField);
		
		JLabel lblS = new JLabel("Security Question");
		lblS.setBounds(45, 201, 134, 15);
		contentPane.add(lblS);
		
		final JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"What is your nickname ?", "What is your favourite picnic spot ?", "Who is your idol ?", "Which is your favourite book ?"}));
		comboBox.setBounds(216, 196, 205, 24);
		contentPane.add(comboBox);
		
		JLabel lblAnswer = new JLabel("Answer");
		lblAnswer.setBounds(50, 239, 95, 15);
		contentPane.add(lblAnswer);
		
		textField_2 = new JTextField();
		textField_2.setBounds(216, 237, 205, 19);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblEmailId = new JLabel("Email Id");
		lblEmailId.setBounds(45, 122, 70, 15);
		contentPane.add(lblEmailId);
		
		textField_3 = new JTextField();
		textField_3.setBounds(216, 120, 205, 19);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					
					if(textField.getText().equals("") || textField_1.getText().equals("") || textField_2.getText().equals("") || textField_3.getText().equals(""))
					throw new NumberFormatException();
					String sql="insert into Employee values(?,?,?,?,?,?)";
					pst=conn.prepareStatement(sql);
					pst.setInt(1,Integer.parseInt(textField.getText()));
					pst.setString(2,textField_1.getText());
					pst.setString(3,textField_3.getText());
					String passw=new String(passwordField.getPassword());
					if(passw.length()>=8)
					pst.setString(4,passw);
					else
						throw new ArithmeticException();
					pst.setString(5,(String)comboBox.getSelectedItem());
					pst.setString(6,textField_2.getText());
					pst.execute();
					JOptionPane.showMessageDialog(null,"New Account Created");
					setVisible(false);
					Login ob=new Login();
					ob.setVisible(true);
					
				}
				catch(NumberFormatException e)
				{
					JOptionPane.showMessageDialog(null,"Improper Entries, Try Again");
				}
				catch(com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException e)
				{
					JOptionPane.showMessageDialog(null,"Account already exist");
				}
				catch(ArithmeticException e)
				{
					JOptionPane.showMessageDialog(null,"Password should be of atleast 8 characters");
				}
				/*catch(java.sql.SQLException e)
				{
					JOptionPane.showMessageDialog(null,"Employee Code should be numeric");
				}*/
				
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(null,e);
				}
			}
		});
		btnSubmit.setBounds(87, 304, 117, 25);
		contentPane.add(btnSubmit);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				Login ob=new Login();
				ob.setVisible(true);
			}
		});
		btnBack.setBounds(223, 304, 117, 25);
		contentPane.add(btnBack);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "SignUp", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 51, 102)));
		panel.setBounds(23, 18, 413, 322);
		contentPane.add(panel);
		
		
	}
}
