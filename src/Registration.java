import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.border.TitledBorder;

import java.awt.Color;
public class Registration extends JFrame {

	private JPanel contentPane;

	String reg_name, reg_mail;
	int code;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	String filepath="";
	Connection conn=Connect.connectDB();
	PreparedStatement pst;
	Statement st;
	ResultSet rs;
	String id;
	Random r=new Random();
	private JLabel lblAttachment;
	private JTextField textField_9;
	private JButton btnBack;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Registration frame = new Registration();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Registration() {
		super("Task Registration");
		try{
			String s="Create table IF NOT EXISTS Task(task_id char(8) PRIMARY KEY,descr char(250) NOT NULL, reg_code int(10), reg_name char(100), reg_mail char(40), act_code int(10) NOT NULL, act_name char(100) NOT NULL, act_mail char(40) NOT NULL, comments char(250), status char(6) NOT NULL, reg_time datetime NOT NULL, closed_time datetime, attachment varchar(60))";
			st=conn.createStatement();
			st.executeUpdate(s);
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e);
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(180, 130, 1091, 511);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		id= this.rand('a')+this.rand('a','b')+'-'+this.rand(1);
		
		JLabel lblNewLabel = new JLabel("Task Id");
		lblNewLabel.setBounds(48, 67, 70, 15);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(248, 63, 227, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		textField.setEditable(false);
		textField.setText(id);
		
		JLabel lblNewLabel_1 = new JLabel("Description");
		lblNewLabel_1.setBounds(42, 127, 106, 15);
		contentPane.add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(248, 125, 227, 19);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Registrant Employee Code");
		lblNewLabel_2.setBounds(43, 181, 198, 15);
		contentPane.add(lblNewLabel_2);
		
		textField_2 = new JTextField();
		textField_2.setBounds(248, 179, 227, 19);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		textField_2.setEditable(false);
		
		
		JLabel lblRegistrantName = new JLabel("Registrant Name");
		lblRegistrantName.setBounds(42, 234, 119, 15);
		contentPane.add(lblRegistrantName);
		
		textField_3 = new JTextField();
		textField_3.setBounds(248, 232, 227, 19);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		textField_3.setEditable(false);
		
		JLabel lblRegistrantEmailId = new JLabel("Registrant Email Id");
		lblRegistrantEmailId.setBounds(42, 289, 133, 15);
		contentPane.add(lblRegistrantEmailId);
		
		textField_4 = new JTextField();
		textField_4.setBounds(248, 287, 227, 19);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		textField_4.setEditable(false);
		
		JLabel lblNewLabel_3 = new JLabel("Actionee Employee Code");
		lblNewLabel_3.setBounds(559, 65, 179, 15);
		contentPane.add(lblNewLabel_3);
		
		textField_5 = new JTextField();
		textField_5.setBounds(774, 65, 245, 19);
		contentPane.add(textField_5);
		textField_5.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Actionee Name");
		lblNewLabel_4.setBounds(559, 127, 133, 15);
		contentPane.add(lblNewLabel_4);
		
		textField_6 = new JTextField();
		textField_6.setBounds(774, 125, 245, 19);
		contentPane.add(textField_6);
		textField_6.setColumns(10);
		
		
		JLabel lblNewLabel_5 = new JLabel("Actionee Email Id");
		lblNewLabel_5.setBounds(559, 181, 135, 15);
		contentPane.add(lblNewLabel_5);
		
		textField_7 = new JTextField();
		textField_7.setBounds(774, 179, 245, 19);
		contentPane.add(textField_7);
		textField_7.setColumns(10);
		
		
		JLabel lblNewLabel_6 = new JLabel("Comments (Optional)");
		lblNewLabel_6.setBounds(559, 234, 162, 15);
		contentPane.add(lblNewLabel_6);
		
		textField_8 = new JTextField();
		textField_8.setBounds(774, 232, 245, 19);
		contentPane.add(textField_8);
		textField_8.setColumns(10);
		
		lblAttachment = new JLabel("Attachment");
		lblAttachment.setBounds(559, 279, 93, 26);
		contentPane.add(lblAttachment);
		
		JButton btnChoose = new JButton("Choose");
		btnChoose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser=new JFileChooser();
				chooser.showOpenDialog(null);
				File f=chooser.getSelectedFile();
				filepath=f.getAbsolutePath();
				textField_9.setText(filepath);
			}
		});
		btnChoose.setBounds(685, 279, 96, 25);
		contentPane.add(btnChoose);
		
		textField_9 = new JTextField();
		textField_9.setBounds(793, 282, 226, 19);
		contentPane.add(textField_9);
		textField_9.setColumns(10);
		
		JLabel lbloptional = new JLabel("(Optional)");
		lbloptional.setBounds(559, 304, 83, 15);
		contentPane.add(lbloptional);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Date date=new Date();
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String filename;
				if(!filepath.equals("")){
				String ar[]=filepath.split("/");
				 filename=ar[ar.length-1];
				}
				else
					filename="";
				try{
					if(textField_1.getText().equals("") || textField_6.getText().equals("") || textField_7.getText().equals("") )
					{
						throw new NumberFormatException();
					}
					
				String q="insert into Task values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
				pst=conn.prepareStatement(q);
				pst.setString(1,id);
				pst.setString(2, textField_1.getText());
				pst.setInt(3,code);
				pst.setString(4,reg_name);
				pst.setString(5,reg_mail);
				pst.setInt(6, Integer.parseInt(textField_5.getText()));
				pst.setString(7, textField_6.getText());
				pst.setString(8, textField_7.getText());
				pst.setString(9, textField_8.getText());
				pst.setString(10,"Open");
				pst.setString(11, dateFormat.format(date));
				pst.setString(12,null);
				pst.setString(13,filename);
				pst.executeUpdate();
				JOptionPane.showMessageDialog(null, "Task Successfully Registered");
				if(!filename.equals(""))
				{
				Path source= Paths.get(filepath);
				Path dest=Paths.get("docs/"+filename);
				Files.copy(source, dest);
				}
				setVisible(false);
				Home ob=new Home();
				ob.setter(code);
				ob.setVisible(true);
				
				}
				catch(NumberFormatException e)
				{
					JOptionPane.showMessageDialog(null, "Improper Form Entries, Try Again");
				}
				catch(java.nio.file.FileAlreadyExistsException e)
				{
					setVisible(false);
					Home ob=new Home();
					ob.setter(code);
					ob.setVisible(true);
					
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(null, e);
				}
				
				
				
			}
		});
		btnSubmit.setBounds(400, 381, 117, 25);
		contentPane.add(btnSubmit);
		
		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				Home ob=new Home();
				ob.setter(code);
				ob.setVisible(true);
				
			}
		});
		btnBack.setBounds(550, 381, 117, 25);
		contentPane.add(btnBack);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Register New Task ", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(102, 51, 0)));
		panel.setBounds(24, 12, 1037, 447);
		contentPane.add(panel);
	}
	
	
	public void setter(int code)
	{
		this.code=code;
		textField_2.setText(Integer.toString(this.code));	
		String q="select name,email from Employee where code="+code;
		try{
		st=conn.createStatement();
		rs=st.executeQuery(q);
		rs.next();
		reg_name=rs.getString(1);
		reg_mail=rs.getString(2);
		textField_3.setText(reg_name);
		textField_4.setText(reg_mail);
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e);
		}
		
	}
	
	String rand(char a){
		return Character.toString((char)(65+r.nextInt(26)));
	} 
	
	
	String rand(char a,char b){
		String s="";
		for(int i=0;i<2;i++)
		{
		s=s+Character.toString((char)(97+r.nextInt(26)));
		}
		return s;
	} 
	
	String rand(int a){
	return Integer.toString(r.nextInt(9999));
	
	} 
}
