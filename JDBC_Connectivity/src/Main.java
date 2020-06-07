import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.*;
public class Main {

	public static void main(String[] args) throws Exception {
		JFrame frame = new JFrame();
		JLabel user = new JLabel("Username");
		JTextField userText = new JTextField();
		JLabel pass = new JLabel("Password");
		JPasswordField passText = new JPasswordField();
		JButton submit = new JButton("Submit"); 
		JButton register = new JButton("Register");
		user.setBounds(40,40,100,20);
		userText.setBounds(110,40,120,20);
		pass.setBounds(40,100,100,20);
		passText.setBounds(110,100,120,20);
		submit.setBounds(40, 150, 80, 20);
		register.setBounds(140, 150, 90, 20);
		frame.add(user);
		frame.add(pass);
		frame.add(userText);
		frame.add(passText);
		frame.add(submit);
		frame.add(register);
		frame.setSize(300,250);
		frame.setLayout(null);
		frame.setVisible(true);
		Connector c = new Connector();
		submit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int flag=0;
					ArrayList<String> array = c.select();
					Iterator i = array.iterator();
					while(i.hasNext()) {
						String username = i.next().toString();
						if(userText.getText().equalsIgnoreCase(username)) {
							JOptionPane alert = new JOptionPane();
							alert.showMessageDialog(frame, "Welcome "+username);
							flag=1;
							break;
						}else {
							flag=0;
							JOptionPane alert = new JOptionPane();
							alert.showMessageDialog(frame, "Invalid username");
							break;
						}
					}
					if(flag==0) {
						
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		register.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String user = userText.getText();
				String pass = String.valueOf(passText.getPassword());
				try {
					ArrayList<String> array = c.select();
					Iterator i = array.iterator();
					while(i.hasNext()) {
						if(user.equalsIgnoreCase(i.next().toString())) {
							JOptionPane alert = new JOptionPane();
							alert.showMessageDialog(frame, " UserName alerady exists!");
							break;
						}else {
							c.insert(user, pass);
							JOptionPane alert = new JOptionPane();
							alert.showMessageDialog(frame, "Registered Successfully");
							break;
						}
					}
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}
	

}
