import java.awt.BorderLayout;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JViewport;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import java.sql.*;

import javax.swing.JScrollBar;
import java.awt.Color;
import java.awt.Font;

public class Completion extends JFrame {

	private JPanel contentPane;
Connection conn;
PreparedStatement pst;
Statement st;
ResultSet rs;
ArrayList<Object[]> al;
	int code1;
	private JTable table_1;
	JScrollPane scrollPane;
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Completion frame = new Completion(3);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public Completion(int code) {
		super("Task Completion");
		conn=Connect.connectDB();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Dimension DimMax = Toolkit.getDefaultToolkit().getScreenSize();
		setMaximumSize(DimMax);
		setSize(DimMax);
        
     
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		
		this.code1=code;
	
		try
		{
		String q="select task_id,descr,reg_code,reg_name,reg_mail,act_code,act_name,act_mail,comments,reg_time from Task where status=? and reg_code=?";
		int n=1;
	 al=new ArrayList<>();
		
		pst=conn.prepareStatement(q);
		pst.setInt(2,code1);
		pst.setString(1,"Open");
		rs=pst.executeQuery();
		
		while(rs.next())
		{
			 Object []a=new Object[13];
			a[0]=n++;
			a[1]=rs.getString(1);
			//System.out.println(a.get(1));
			a[2]=rs.getString(2);
			//System.out.println(a.get(2));
			a[3]=rs.getInt(3);
			a[4]=rs.getString(4);
			a[5]=rs.getString(5);
			a[6]=rs.getInt(6);
			a[7]=rs.getString(7);
			a[8]=rs.getString(8);
			a[9]=rs.getString(10);
			a[10]=rs.getString(9);
			a[11]="View";
			a[12]="Close";
			al.add(a);
		}
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e);
		}
		
		
		
		Object data[][]=new Object[al.size()][];
		data=al.toArray(data); 
	
		
		table_1 = new JTable(data,new String[] {
				"S.No.", "Task Id", "Description", "Registrant Code", "Registrant Name", "Registrant Mail", "Actionee Code", "Actionee Name", "Actionee Mail", "Registration Time", "Comments", "Attachment", "Close Task"
			})
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
		table_1.getColumnModel().getColumn(11).setPreferredWidth(100);
		table_1.getColumnModel().getColumn(11).setMinWidth(100);
		table_1.getColumnModel().getColumn(12).setPreferredWidth(100);
		table_1.getColumnModel().getColumn(12).setMinWidth(100);
		table_1.setRowHeight(30);


	
	
	 table_1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table_1.getColumnModel().getColumn(11).setCellRenderer(new ButtonRenderer());
		
		table_1.getColumnModel().getColumn(11).setCellEditor(new ButtonEditor(new JTextField()));
	
table_1.getColumnModel().getColumn(12).setCellRenderer(new ButtonRenderer());
		
		table_1.getColumnModel().getColumn(12).setCellEditor(new ButtonEditor(new JTextField()));
		
	
		JScrollPane scrollPane = new JScrollPane(table_1);
		
		  scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);  
	        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);  

	
		contentPane.add(scrollPane,BorderLayout.CENTER);
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
	

class ButtonRenderer extends JButton implements TableCellRenderer
{

	public ButtonRenderer()
	{
		setOpaque(true);
	}
	@Override
	public Component getTableCellRendererComponent(JTable table, Object obj,
			boolean selected, boolean focused, int row, int col) {
		setText(obj.toString());
		
		return this;
	}}

class ButtonEditor extends DefaultCellEditor
{
	JButton btn;
	String lbl;
	Boolean clicked;
	int rown,coln;
	JTable table1;
	public ButtonEditor(JTextField textField) {
		super(textField);
		btn=new JButton();
		btn.setOpaque(true);
		//btn.setBackground(Color.BLUE);
		btn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				fireEditingStopped();
			
		}
		
	});	
}
	public Component getTableCellEditorComponent(JTable table,Object obj,boolean selected, int row,int col)
	{
		lbl=obj.toString();
		btn.setText(lbl);
	
		clicked = true;
		coln=col;
		rown=row;
		table1=table;
		
		return btn;
	}
	
	public Object getCellEditorValue()
	{
	if(clicked){
		
		String id=String.valueOf(table1.getModel().getValueAt(rown, 1));
		String att;
		Connection conn=Connect.connectDB();
		String q="select attachment from Task where task_id=?";
		if(coln==11)
		{
			
			try{
			
			PreparedStatement pst=conn.prepareStatement(q);
			pst.setString(1, id);
			ResultSet rs=pst.executeQuery();
			rs.next();
			att=rs.getString(1);
			if(att.equals(""))
				JOptionPane.showMessageDialog(null,"No Attachments For This Task");
			else{
			 File myFile = new File("docs/"+att);
		        Desktop.getDesktop().open(myFile);
			}
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(null,e);
			}
			 
		}
		
		if(coln==12)
		{
			int a=JOptionPane.showConfirmDialog(null,"Are you sure you want to close this task?");  
			if(a==JOptionPane.YES_OPTION){  
			     
			Date date = new Date();
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try{
			PreparedStatement pst=conn.prepareStatement(q);
			pst.setString(1, id);
			ResultSet rs=pst.executeQuery();
			rs.next();
			att=rs.getString(1);
			rs.close();
			pst.close();
			String q1="Update Task set status='Closed',closed_time=? where task_id=?";
			PreparedStatement pst1=conn.prepareStatement(q1);
			pst1.setString(1, dateFormat.format(date));
			pst1.setString(2, id);
			pst1.executeUpdate();
			pst1.close();
			int code=(int)table1.getModel().getValueAt(rown, 3);
			JViewport parent = (JViewport)table1.getParent();
			JScrollPane enclosing = (JScrollPane)parent.getParent();
			JPanel panel=(JPanel)enclosing.getParent();

			Completion ob1=new Completion(code);
			panel.setVisible(false);
			ob1.setVisible(true);
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(null,e);
			}
		}
		}
	}
	clicked=false;
	return new String(lbl);
	}
	@Override
	public boolean stopCellEditing() {
		clicked=false;
		return super.stopCellEditing();
	}
	
	@Override
	protected void fireEditingStopped() {
		// TODO Auto-generated method stub
		super.fireEditingStopped();
	}
}
