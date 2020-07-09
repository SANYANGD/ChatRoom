package windows;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/*
 * 初始登录界面
 */
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import mysql.Mysql;

public class LoginWindow extends JFrame implements ActionListener{
	//所有成员;
	boolean flag;
	private static final long serialVersionUID = 1L;
	JPanel mb;
	JLabel lb,lb1,lb2,lb3;
	JLabel namelb ,passwordlab;
	JButton bt,bt1,bt2;
	JTextField username;	
	JPasswordField password;
	JCheckBox select1,select2;
	public LoginWindow() {
		//初始化所有对象
		lb=new JLabel(new ImageIcon("D:\\SanYang\\program\\Java\\Login\\src\\image\\backgroun.png"));
		
		//这里的图片自己找一张,大小大概 570x160像素图片
		mb=new JPanel();
		username =new JTextField(20);
		password=new JPasswordField(20);
		namelb = new JLabel("StudentID");
		passwordlab = new JLabel("Password");
	    //lb1=new JLabel("注册账号");
	    //lb2=new JLabel("找回密码");
		bt1 = new JButton("Register");
		bt2 = new JButton("Find PW");
		select1=new JCheckBox("Save PW");
		select2=new JCheckBox("Auto Login");
		bt=new JButton("Login");
		setCheckBoxInfo();
		setButtonInfo();
		setLabInfo();
		setPanelInfo();
		addComponent();
		setFrame();
	}
	public void setCheckBoxInfo() {
		select1.setFont(new Font("微软雅黑",Font.PLAIN,15));
		select1.setBackground(Color.WHITE);
		select2.setFont(new Font("微软雅黑",Font.PLAIN,15));
		select2.setBackground(new Color(255,250,250));
		select1.setBounds(130,90,120,20);
		select2.setBounds(290,90,120,20);	
	}
	public void setButtonInfo() {
		bt.setBounds(130,130,250,37);
		bt.setFont(new Font("微软雅黑",Font.PLAIN,16));
		bt.setBackground(new Color(0,178,238));
		bt.setForeground(Color.white);
		bt.addActionListener(this);
		bt1.setBounds(420,10,90,30);
		bt2.setBounds(420,50,90,30);
		bt1.setForeground(new Color(28,134,238));
		bt1.setFont(new Font("微软雅黑",Font.PLAIN,13));
		bt1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		bt1.addActionListener(this);
		bt2.setForeground(new Color(28,134,238));
		bt2.setFont(new Font("微软雅黑",Font.PLAIN,13));
		bt2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		bt2.addActionListener(this);
	}
	public void setFrame() {
		this.setSize(550, 250);
		this.setTitle("LOGIN");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocation(500, 300);
		this.setResizable(false);
		this.setVisible(true);
	}
	public void setLabInfo() {
		username.setBounds(130, 14, 250, 37);
		username.setFont(new Font("微软雅黑",Font.PLAIN,16));
		
		password.setBounds(130,48, 250, 37);
		password.setFont(new Font("微软雅黑",Font.PLAIN,16));
		
		namelb.setBounds(40,14,80,37);
		namelb.setFont(new Font("微软雅黑",Font.PLAIN,16));
		
		passwordlab.setBounds(40, 48, 80, 37);
		passwordlab.setFont(new Font("微软雅黑",Font.PLAIN,16));
		//y,x,width,height; 130为左侧居中位置
	}
	public void setPanelInfo() {
		//将面板的布局设为null，然后自定义布局	
		mb.setLayout(null);
		mb.setBackground(Color.white);
	}
	public void addComponent() {
		//将组建添加到面板当中
		mb.add(bt2);mb.add(bt1);//mb.add(select1);mb.add(select2);
		mb.add(bt);mb.add(username);mb.add(password);
		mb.setSize(540,190);mb.add(namelb);mb.add(passwordlab);
		this.add(lb,BorderLayout.NORTH);	
		this.add(mb,BorderLayout.CENTER);
	}
	public void findPassword() {
		this.dispose();
		FindPasswordWindow fw  = new FindPasswordWindow();
		fw.setVisible(true);
		
	}
	public void regist() {
		this.dispose();
		RegisterWindow rw  = new RegisterWindow();
		rw.setVisible(true);
	}
	public void loginJudge() {
		Mysql mysql = new Mysql();
		mysql.connectSQL();
		String tname = username.getText();
		String tpass = new String (password.getPassword());
		try {
			this.flag = mysql.loginMatch(tname,tpass);
		} catch (SQLException e2) {
			
			e2.printStackTrace();
		} catch (Exception e2) {
			
			e2.printStackTrace();
		}
		//清空输入栏
		username.setText("");
		password.setText("");
		//如果用户名判断成功则关闭login窗口并打开ChatRoomWindow窗口，否则状态栏显示loadfail;
		if(flag) {
			this.dispose();
			ChatRoomWindow crw  = new ChatRoomWindow();
			crw.setVisible(true);
			//WarnWindow ww = new WarnWindow();
			//ww.loginSuccess();
		}
		else {
			WarnWindow ww = new WarnWindow();
			ww.loginFail();
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==bt){
			this.loginJudge();
		}else if(e.getSource()==bt1) {
			this.regist();
		}else if(e.getSource()==bt2) {
			this.findPassword();
		}
	}
}
