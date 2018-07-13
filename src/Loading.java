import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JProgressBar;

import java.awt.Color;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JRadioButton;


public class Loading extends JFrame implements Runnable {

	private JPanel contentPane;
	JProgressBar progressBar;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Loading frame = new Loading();
					frame.setVisible(true);
					Thread th=new Thread(frame);	
					th.start();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public Loading() {
		super("WELCOME");
			
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 200, 603, 294);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 204, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		 progressBar = new JProgressBar();
		 progressBar.setForeground(Color.GRAY);
		 progressBar.setStringPainted(true);
		progressBar.setBounds(12, 239, 579, 24);
		contentPane.add(progressBar);
		
		JLabel lblNewLabel = new JLabel("FollowUpTracker");
		lblNewLabel.setForeground(new Color(51, 0, 0));
		lblNewLabel.setFont(new Font("URW Chancery L", Font.BOLD, 50));
		lblNewLabel.setBounds(191, 76, 374, 92);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("images/cal.png"));
		lblNewLabel_1.setBounds(58, 64, 137, 128);
		contentPane.add(lblNewLabel_1);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(51, 153, 255));
		panel.setBounds(12, 12, 579, 262);
		contentPane.add(panel);
	
		
		
	}
	
	
	public void run()
	{
		try{
		for(int i=1;i<200;i++)
		{
			int m=progressBar.getMaximum();
			int v=progressBar.getValue();
			if(v<m)
				progressBar.setValue(v+1);
			else
			{
				setVisible(false);
				
				Login ob=new Login();
				ob.setVisible(true);
				
				
				break;
			}
			Thread.sleep(60);
		}
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e);
		}
	}
}
