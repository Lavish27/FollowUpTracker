import java.sql.*;
import javax.swing.JOptionPane;
public class Connect {
	Connection conn;
	public static Connection connectDB()
	{
		try
		{
		String url="jdbc:mysql://localhost:3306";
		String uname="root";
		String pass="6972";
		String db="Tracker";
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn=DriverManager.getConnection(url,uname,pass);
		Statement s=conn.createStatement();
		s.executeUpdate("Create database IF NOT EXISTS "+db);
		s.close();
		conn.close();
		String url1="jdbc:mysql://localhost:3306/"+db;
		Connection conn1=DriverManager.getConnection(url1,uname,pass);
	     return conn1;
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null,e);
			return null;
		}
		
	     
	}


}

