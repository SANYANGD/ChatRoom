package windows;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ChatRoomWindow extends JFrame implements ActionListener,ListSelectionListener,WindowListener{
	JPanel mb;
	JLabel lb;
	JButton bt;
	public ChatRoomWindow() {
		//初始化所有对象
		lb=new JLabel();		
		mb=new JPanel();
		bt=new JButton("Log Out");
		setButtonInfo();
		setFrame();
		setPanelInfo();
		addComponent();
		
	}
	
	public void setButtonInfo() {
		bt.setBounds(550,350,100,40);
		bt.setFont(new Font("微软雅黑",Font.PLAIN,16));
		bt.setBackground(new Color(0,178,238));
		bt.setForeground(Color.white);
		bt.addActionListener(this);
	}
	public void setFrame() {
		this.setLocation(400, 180);
		this.setSize(700, 450);
		this.setTitle("ChatRoom");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	public void setPanelInfo() {
		//将面板的布局设为null，然后自定义布局	
		mb.setLayout(null);
		mb.setBackground(Color.white);
	}
	public void addComponent() {
		//将组建添加到面板当中
		mb.add(bt);
		mb.setSize(540,190);
		this.add(lb,BorderLayout.NORTH);	
		this.add(mb,BorderLayout.CENTER);
	}
	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==bt){
			LoginWindow lw = new LoginWindow();
			this.dispose();
		}
	}
}