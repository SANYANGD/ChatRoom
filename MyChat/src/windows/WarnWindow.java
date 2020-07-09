package windows;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class WarnWindow {
	JFrame temp;
	//临时的警告弹出窗口
	public WarnWindow() {
		temp = new JFrame();
		temp.setVisible(true);
		temp.setLocation(630, 350);
		temp.setSize(280, 140);
	}
	public void chatroomSuccessful() {
		JTextArea templb = new JTextArea("login successful!");
		templb.setLineWrap(true);
		temp.add(templb);
	}
	public void reputCheckWarning() {
		JTextArea templb = new JTextArea("The password entered twice is different!");
		templb.setLineWrap(true);
		temp.add(templb);
	}
	public void findFail() {
		JTextArea templb = new JTextArea("The answer is wrong!");
		templb.setLineWrap(true);
		temp.add(templb);
	}
	public void registerSuccessful() {
		JTextArea templb = new JTextArea("Registration success!");
		templb.setLineWrap(true);
		temp.add(templb);
	}
	public void loginFail() {
		JTextArea templb = new JTextArea("Incorrect password!");
		templb.setLineWrap(true);
		temp.add(templb);
	}
	public void loginSuccess() {
		JTextArea templb = new JTextArea("login successful!");
		templb.setLineWrap(true);
		temp.add(templb);
	}
	public void sqlConnectFail() {
		JTextArea templb = new JTextArea("Database connection error!");
		templb.setLineWrap(true);
		temp.add(templb);
	}
	public void registIncomplete() {
		JTextArea templb = new JTextArea("The registration information is incomplete or incorrect!");
		templb.setLineWrap(true);
		temp.add(templb);
	}
	public void deleteSuccessful() {
		JTextArea templb = new JTextArea("Successfully deleted!");
		templb.setLineWrap(true);
		temp.add(templb);
	}
	public void psErrorWarning() {
		JTextArea templb = new JTextArea("The password should contain uppercase "
				+ "and lowercase letters and numbers and be between 8-20 digits!");
		templb.setLineWrap(true);
		temp.add(templb);
	}
	public void idFail() {
		JTextArea templb = new JTextArea("This id already exists!");
		templb.setLineWrap(true);
		temp.add(templb);
	}
	
}
