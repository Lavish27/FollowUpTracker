import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;

import java.awt.Color;

import javax.swing.border.LineBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;

public class ForgotPassword extends JFrame {
	Connection conn;
	ResultSet rs;
	PreparedStatement pst;

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ForgotPassword frame = new ForgotPassword();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public ForgotPassword() {
		super("Retrieve Password");
		conn=Connect.connectDB();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(450, 200, 568, 334);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Employee Code");
		lblNewLabel.setBounds(36, 54, 119, 15);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(185, 52, 222, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Security Question");
		lblNewLabel_1.setBounds(36, 95, 136, 15);
		contentPane.add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(185, 93, 222, 19);
		textField_1.setEditable(false);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblAnswer = new JLabel("Answer");
		lblAnswer.setBounds(36, 142, 70, 15);
		contentPane.add(lblAnswer);
		
		textField_3 = new JTextField();
		textField_3.setBounds(186, 140, 221, 19);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Password");
		lblNewLabel_2.setBounds(37, 185, 93, 15);
		contentPane.add(lblNewLabel_2);
		
		textField_2 = new JTextField();
		textField_2.setBounds(185, 181, 222, 19);
		textField_2.setEditable(false);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String a1=textField.getText();
				String sql="select sec_q from Employee where code='"+a1+"'";
				try{
					pst=conn.prepareStatement(sql);
					rs=pst.executeQuery();
					if(rs.next())
					{
						textField_1.setText(rs.getString(1));
						rs.close();
						pst.close();
					}
					else{
						JOptionPane.showMessageDialog(null, "Account doesn't exist");
					}
					
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(null,e);
				}
			}
		});
		btnNewButton.setBounds(419, 49, 107, 25);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Retrieve");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String a1=textField.getText();
				String a2=textField_3.getText();
				String sql="select password from Employee where code='"+a1+"' and answer='"+a2+"'";
				try{
					pst=conn.prepareStatement(sql);
					rs=pst.executeQuery();
					if(rs.next())
					{
						textField_2.setText(rs.getString(1));
						rs.close();
						pst.close();
					}
					else{
						JOptionPane.showMessageDialog(null, "Answer is wrong");
					}
					
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(null,e);
				}
			}
		});
		btnNewButton_1.setBounds(419, 137, 107, 25);
		contentPane.add(btnNewButton_1);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				Login ob=new Login();
				ob.setVisible(true);
			}
		});
		btnBack.setBounds(197, 245, 117, 25);
		contentPane.add(btnBack);
		
		
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Forgot Password", TitledBorder.LEADING, TitledBorder.TOP, null, Color.ORANGE));
		panel.setBounds(24, 12, 532, 289);
		contentPane.add(panel);
	}
}
