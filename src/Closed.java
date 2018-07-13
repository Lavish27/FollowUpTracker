import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableCellRenderer;

import java.sql.*;
import java.util.ArrayList;


public class Closed extends JFrame {

	private JPanel contentPane;

	Connection conn;
	int count,code1;
	ArrayList<Object[]> al;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Closed frame = new Closed(2);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Closed(int code) {
		super("Recently Closed Tasks");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		Dimension DimMax = Toolkit.getDefaultToolkit().getScreenSize();
		setMaximumSize(DimMax);
		setSize(DimMax);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane); 
		code1=code;
		conn=Connect.connectDB();
		try{
			String q1="select count(*) from Task where status='Closed' and reg_code=?";
			PreparedStatement pst=conn.prepareStatement(q1);
			pst.setInt(1, code);
			ResultSet rs=pst.executeQuery();
			rs.next();
			count=rs.getInt(1);
			//rs.close();
			//pst.close();
			if(count>10){
				int c=count-10;
			String q2="delete from Task where status='Closed' and reg_code=? order by closed_time limit ?";
			PreparedStatement pst1	=conn.prepareStatement(q2);
			pst1.setInt(1,code);
			pst1.setInt(2,c);
			pst1.executeUpdate();
		}
			try{
			String q3="select task_id,descr,reg_code,reg_name,reg_mail,act_code,act_name,act_mail,comments,reg_time,closed_time from Task where status='Closed' and reg_code=? order by closed_time desc";
			PreparedStatement pst2=conn.prepareStatement(q3);
			pst2.setInt(1,code);
			ResultSet rs1=pst2.executeQuery();
			
			al=new ArrayList<>();
			int n=1;
			while(rs1.next())
			{
				Object a[]=new Object[12];
					a[0]=n++;
					a[1]=rs1.getString(1);
					a[2]=rs1.getString(2);
					a[3]=rs1.getInt(3);
					a[4]=rs1.getString(4);
					a[5]=rs1.getString(5);
					a[6]=rs1.getInt(6);
					a[7]=rs1.getString(7);
					a[8]=rs1.getString(8);
					a[9]=rs1.getString(9);
					a[10]=rs1.getString(10);
					a[11]=rs1.getString(11);
				
					al.add(a);
			}
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(null, e);
			}
			
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e);
		}
		
		Object data[][]=new Object[al.size()][];
		data=al.toArray(data);
		
		 String col[]=new String[]{"S.No.", "Task Id", "Description", "Registrant Code", "Registrant Name", "Registrant Mail", "Actionee Code", "Actionee Name", "Actionee Mail","Comments", "Registration Time", "Closing Time" };
		
		  
	        
	        JTable table_1=new JTable(data,col)
	        {
				public Component prepareRenderer(TableCellRenderer r, int row, int col) {
					
					Component c=super.prepareRenderer(r, row, col);
					if(row%2==0)
						c.setBackground(Color.WHITE);
					else 
						c.setBackground(Color.LIGHT_GRAY);
					
					if(isCellSelected(row, col))
						c.setBackground(Color.CYAN);
					return c;
				}
			};
	        
	        table_1.getColumnModel().getColumn(0).setPreferredWidth(50);
			table_1.getColumnModel().getColumn(0).setMinWidth(50);
			table_1.getColumnModel().getColumn(1).setPreferredWidth(100);
			table_1.getColumnModel().getColumn(1).setMinWidth(100);
			table_1.getColumnModel().getColumn(2).setPreferredWidth(300);
			table_1.getColumnModel().getColumn(2).setMinWidth(200);
			table_1.getColumnModel().getColumn(3).setPreferredWidth(130);
			table_1.getColumnModel().getColumn(3).setMinWidth(130);
			table_1.getColumnModel().getColumn(4).setPreferredWidth(200);
			table_1.getColumnModel().getColumn(4).setMinWidth(200);
			table_1.getColumnModel().getColumn(5).setPreferredWidth(200);
			table_1.getColumnModel().getColumn(5).setMinWidth(200);
			table_1.getColumnModel().getColumn(6).setPreferredWidth(100);
			table_1.getColumnModel().getColumn(6).setMinWidth(100);
			table_1.getColumnModel().getColumn(7).setPreferredWidth(200);
			table_1.getColumnModel().getColumn(7).setMinWidth(200);
			table_1.getColumnModel().getColumn(8).setPreferredWidth(200);
			table_1.getColumnModel().getColumn(8).setMinWidth(200);
			table_1.getColumnModel().getColumn(9).setPreferredWidth(200);
			table_1.getColumnModel().getColumn(9).setMinWidth(150);
			table_1.getColumnModel().getColumn(10).setPreferredWidth(200);
			table_1.getColumnModel().getColumn(10).setMinWidth(200);
			table_1.getColumnModel().getColumn(11).setPreferredWidth(200);
			table_1.getColumnModel().getColumn(11).setMinWidth(100);
			
			table_1.setRowHeight(30);
	        
			
			   table_1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	        JScrollPane scrollableTextArea = new JScrollPane(table_1);  
	        
	        scrollableTextArea.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);  
	        scrollableTextArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);  
	  
	        contentPane.add(scrollableTextArea,BorderLayout.CENTER);  
	        JButton btn = new JButton("Back");
			btn.setFont(new Font("Dialog", Font.BOLD, 16));
			btn.setForeground(new Color(153, 0, 0));
			btn.setBackground(new Color(51, 153, 255));
			btn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Home ob=new Home();
					setVisible(false);
					ob.setter(code1);
					ob.setVisible(true);
				}
			});
			contentPane.add(btn,BorderLayout.SOUTH);
	}

}
