package windows;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import mysql.Mysql;
import user.User;

public class FindPasswordWindow extends JFrame implements ActionListener{
	JPanel mb;
	JButton bt,bt1,bt2;
	JComboBox cb = null;
	Vector<String> v ;
	JLabel namelb,studyNumberlb,classroomlb,answerlb,securityQuestionlb;
	JLabel passwordlb;

	JTextField name,studyNumber,classroom,answer;	

	public FindPasswordWindow(){

		name = new JTextField(20);
		studyNumber = new JTextField(20);
		classroom = new JTextField(20);
		answer = new JTextField(20);
		classroomlb = new JLabel("Class");
		namelb = new JLabel("Name");
		studyNumberlb = new JLabel("ID");
		answerlb = new JLabel("Answer");
		securityQuestionlb = new JLabel("Question");
		setComboBox();
		mb=new JPanel();
		bt=new JButton("Find");
		bt1=new JButton("Quit");
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
		//rePassword.setFont(new Font("微软雅黑",Font.PLAIN,16));
	}
	public void setFrame() {
		this.setLocation(400, 180);
		this.setSize(700, 450);
		this.setVisible(true);
	}
	public void setLocation() {
		namelb.setBounds(50,14,60,37);
		studyNumberlb.setBounds(50,48,60,37);
		//passwordlb.setBounds(50,268,40,37);
		//passwordlb2.setBounds(50,224,40,37);
		classroomlb.setBounds(50, 92, 60, 37);
		securityQuestionlb.setBounds(50, 136, 60,37);
		cb.setBounds(160,136,250,45);
		answerlb.setBounds(50,180,60,37);
		name.setBounds(160, 14, 250, 37);
		studyNumber.setBounds(160,48, 250, 37);
		classroom.setBounds(160,92, 250, 37);
		answer.setBounds(160,180,250,37);
		//password.setBounds(160,180, 250, 37);
		//rePassword.setBounds(160,136, 250, 37); 
		bt.setBounds(160,350,100,37);
		bt1.setBounds(270,350,100,37);
		bt2.setBounds(380,350,100,37);
		
	}
	public void setPanelInfo() {
		//将面板的布局设为null，然后自定义布局	
		mb.setLayout(null);
		mb.setBackground(Color.white);
		mb.add(namelb); mb.add(answerlb); mb.add(classroomlb);
	
		mb.add(studyNumberlb);
		mb.add(securityQuestionlb);mb.add(studyNumber);
		mb.add(answer); mb.add(classroom); mb.add(name);
		
		mb.add(bt); mb.add(bt1); mb.add(bt2);
		mb.add(cb);
		this.add(mb);
	}
	public void clearTextfield() {

		name.setText("");
		studyNumber.setText("");
		classroom.setText("");
		answer.setText("");
	}

	public boolean checkLegal(String tpass, String repass) {
		if(tpass.equals(repass)) return true;
		else return false;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==bt){
				//判断注册信息是否输入完整
				if(classroom.getText().length()==0||name.getText().length()==0) {
					WarnWindow ww = new WarnWindow();
					ww.registIncomplete();
				}else {//完整的情况
					User user = new User(); 
					user.setName(name.getText());
					user.setId(studyNumber.getText());
					user.setClassroom(classroom.getText());
					user.setSecurityQuestion((String) cb.getSelectedItem());
					user.setSecurityAnswer(answer.getText());
					Mysql mq = new Mysql();
					mq.connectSQL();
					try {
						String pass = mq.findPassword(user);
						if(pass != null) {
							passwordlb = new JLabel("Your Password: "+pass);
							mb.add(passwordlb);
							passwordlb.setBounds(160,268, 250, 37);
							passwordlb.setFont(new Font("微软雅黑",Font.PLAIN,16));
						}else {
							WarnWindow ww = new WarnWindow();
							ww.findFail();
						}
					}catch (SQLException e1) {e1.printStackTrace();}	
				}
			}
		else if(e.getSource()==bt1) {
			this.dispose();
		}else if(e.getSource()==bt2) {
			LoginWindow lw = new LoginWindow();
			this.dispose();
		}
	}
}
