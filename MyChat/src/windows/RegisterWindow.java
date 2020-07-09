package windows;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import mysql.Mysql;
import user.User;

public class RegisterWindow extends JFrame implements ActionListener{
	JPanel mb;
	JButton bt,bt1,bt2;
	JComboBox cb = null;
	Vector<String> v ;
	JLabel namelb,studyNumberlb,classroomlb,answerlb,securityQuestionlb;
	JLabel passwordlb,passwordlb2;
	JTextField name,studyNumber,classroom,answer;	
	JPasswordField password,rePassword;
	public RegisterWindow(){
		password = new JPasswordField(20);
		rePassword = new JPasswordField(20);
		name = new JTextField(20);
		studyNumber = new JTextField(20);
		classroom = new JTextField(20);
		answer = new JTextField(20);
		classroomlb = new JLabel("Class");
		namelb = new JLabel("Name");
		passwordlb = new JLabel("Password");
		passwordlb2 = new JLabel("Reinput");
		studyNumberlb = new JLabel("ID");
		answerlb = new JLabel("Answer");
		securityQuestionlb = new JLabel("Question");
		setComboBox();
		mb=new JPanel();
		bt=new JButton("Register");
		bt1=new JButton("Clear");
		bt2=new JButton("Return");
		setLocation();
		setPanelInfo();
		setFrame();
		setButton();
		setTextfieldInfo();
	}
	public void setComboBox() {
		v = new Vector<String> ();
		cb = new JComboBox(v);
		v.add("Question1");
		v.add("Question2");
		v.add("Question3");
		cb.setBorder(BorderFactory.createTitledBorder("Select a question"));
		cb.setMaximumRowCount(3);
	}
	public void setButton() {
		bt.setFont(new Font("微软雅黑",Font.PLAIN,15));
		//bt.setBackground(new Color(0,178,238));
		bt.setForeground(Color.black);
		bt.addActionListener(this);
		bt1.setFont(new Font("微软雅黑",Font.PLAIN,15));
		//bt1.setBackground(new Color(0,178,238));
		bt1.setForeground(Color.black);
		bt1.addActionListener(this);
		bt2.setFont(new Font("微软雅黑",Font.PLAIN,15));
		//bt1.setBackground(new Color(0,178,238));
		bt2.setForeground(Color.black);
		bt2.addActionListener(this);
	}
	public void setTextfieldInfo() {
		name.setFont(new Font("微软雅黑",Font.PLAIN,16));
		answer.setFont(new Font("微软雅黑",Font.PLAIN,16));
		studyNumber.setFont(new Font("微软雅黑",Font.PLAIN,16));
		classroom.setFont(new Font("微软雅黑",Font.PLAIN,16));
		password.setFont(new Font("微软雅黑",Font.PLAIN,16));
		rePassword.setFont(new Font("微软雅黑",Font.PLAIN,16));
		name.setBounds(130, 14, 250, 37);
		studyNumber.setBounds(130,48, 250, 37);
		classroom.setBounds(130,180, 250, 37);
		answer.setBounds(130,268, 250, 37);
		password.setBounds(130,92, 250, 37);
		rePassword.setBounds(130,136, 250, 37);
	}
	public void setFrame() {
		this.setLocation(400, 180);
		this.setSize(700, 450);
		this.setVisible(true);
	}
	public void setLocation() {
		namelb.setBounds(50,14,60,37);
		studyNumberlb.setBounds(50,48,60,37);
		passwordlb.setBounds(50, 92, 60, 37);
		passwordlb2.setBounds(50, 136, 60,37);
		classroomlb.setBounds(50,180,60,37);
		securityQuestionlb.setBounds(50,224,60,37);
		answerlb.setBounds(50,268,60,37);
		name.setBounds(160, 14, 250, 37);
		studyNumber.setBounds(160,48, 250, 37);
		classroom.setBounds(160,180, 250, 37);
		answer.setBounds(160,268, 250, 37);
		password.setBounds(160,92, 250, 37);
		rePassword.setBounds(160,136, 250, 37);
		bt.setBounds(160,350,100,37);
		bt1.setBounds(270,350,100,37);
		bt2.setBounds(380,350,100,37);
		cb.setBounds(130,224,250,45);
	}
	public void setPanelInfo() {
		//将面板的布局设为null，然后自定义布局	
		mb.setLayout(null);
		mb.setBackground(Color.white);
		mb.add(namelb); mb.add(answerlb); mb.add(classroomlb);
		mb.add(passwordlb);mb.add(passwordlb2);mb.add(studyNumberlb);
		mb.add(securityQuestionlb);mb.add(studyNumber);
		mb.add(answer); mb.add(classroom); mb.add(name);
		mb.add(password); mb.add(rePassword); mb.add(bt); mb.add(bt1);mb.add(bt2);
		mb.add(cb);
		this.add(mb);
	}
	public void clearTextfield() {
		password.setText("");
		rePassword.setText("");
		name.setText("");
		studyNumber.setText("");
		classroom.setText("");
		answer.setText("");
		password.setText("");
	}

	public boolean checkLegal(String tpass, String repass) {
		if(tpass.equals(repass)) return true;
		else return false;
	}
	
	/**
	* 包含大小写字母及数字且在8-20位
	* 是否包含
	* @param str
	* @return
	*/
	public static boolean isLetterDigit(String str) {
		boolean isDigit = false;//定义一个boolean值，用来表示是否包含数字
		boolean isLetter = false;//定义一个boolean值，用来表示是否包含字母
		for (int i = 0; i < str.length(); i++) {
			if (Character.isDigit(str.charAt(i))) { //用char包装类中的判断数字的方法判断每一个字符
				isDigit = true;
			} else if (Character.isLetter(str.charAt(i))) { //用char包装类中的判断字母的方法判断每一个字符
				isLetter = true;
			}
		}
		String regex = "^[a-zA-Z0-9]{8,20}$";
		boolean isRight = isDigit && isLetter && str.matches(regex);
		return isRight;
		//return true;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==bt){
			String tpass = new String (password.getPassword());
			String repass = new String (rePassword.getPassword());
			boolean isRight = isLetterDigit(tpass);
			if(isRight) {
				if (this.checkLegal(tpass, repass)) {
					//判断注册信息是否输入完整
					if(classroom.getText().length()==0||name.getText().length()==0||answer.getText().length()==0){
						WarnWindow ww = new WarnWindow();
						ww.registIncomplete();
					}else{//完整的情况
						User user = new User(); 
						user.setName(name.getText());
						String str = new String(password.getPassword());
						user.setPassword(str);
						user.setId(studyNumber.getText());
						user.setClassroom(classroom.getText());
						user.setSecurityQuestion((String) cb.getSelectedItem());
						user.setSecurityAnswer(answer.getText());
						Mysql mq = new Mysql();
						mq.connectSQL();
						try {
							if(mq.idCheck(studyNumber.getText())) {
								if(mq.register(user)) {
									//LoginWindow lw = new LoginWindow();
									//this.dispose();							
									WarnWindow ww = new WarnWindow();
									ww.registerSuccessful();
								}
							}else {
								WarnWindow ww = new WarnWindow();
								//ww.sqlConnectFail();
								ww.idFail();
							}
						}catch (Exception e1) {e1.printStackTrace();}	
					}
				}else{
					WarnWindow ww = new WarnWindow();
					ww.reputCheckWarning();clearTextfield();
				}
			}else {
				WarnWindow ww = new WarnWindow();
				ww.psErrorWarning();clearTextfield();
			}
		}else if(e.getSource()==bt1) {
			clearTextfield();
		}else if(e.getSource()==bt2) {
			LoginWindow lw = new LoginWindow();
			this.dispose();
		}
		
	}
}

