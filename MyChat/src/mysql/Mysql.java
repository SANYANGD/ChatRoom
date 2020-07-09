package mysql;
//傻瓜式连接数据库;
/*
 *| studyNumberId    | varchar(30)  | YES  |     | NULL    |       |
 *| name             | varchar(30)  | NO   |     | NULL    |       |
 *| password         | varchar(32)  | NO   |     | NULL    |       |
 *| class            | varchar(20)  | YES  |     | NULL    |       |
 *| securityAnswer   | varchar(30)  | NO   |     | NULL    |       |
 *| securityQuestion | varchar(100) | YES  |     | NULL    |       |
 *+------------------+--------------+------+-----+---------+-------+*/
/*
 * 类的结构
 * public Mysql();
 * 构造：	无参构造：(并调用ConnectSql();)
 * 方法：
 * public void connectSql();连接数据库
 * public boolean loginMatch();	//由login调用：匹配login传来的text参数，
 * 并将其与数据库对象匹配;
 * public void register();由注册窗口RegisterWindow的register方法调用，
 * 调用person()并进行注册; 将信息写入数据库
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import user.User;
import windows.WarnWindow;
public class Mysql {
	public static final String DBDRIVER = "com.mysql.cj.jdbc.Driver";
	//输入自己的名称
	public static final String DBURL = "jdbc:mysql://localhost:3306/info"
			+ "?serverTimezone=UTC&characterEncoding=utf-8";
	public static final String DBUSER ="root";
	//自己的数据库密码
	public static final String DBPW = "famliycomefirst";
	private static String tid;
	private static String tpass;
	Statement stmt = null;
	String pass = null,pass1 = null ;
	Connection connection = null;
	public Mysql() {	
	}
	public boolean register(User user) throws SQLException {
		stmt = connection.createStatement();
		String sql = "insert  into  user "
				+"values('"+user.getId()+"','"
				+user.getName()+"','"
				+user.getPassword()+"','"
				+user.getClassroom()+"','"
				+user.getSecurityAnswer()+"','"
				+user.getSecurityQuestion()+"')";
		//System.out.println("1111111111");
		if(stmt.execute(sql) ) return false;
		else return true;
	}
	public void connectSQL() {
		try {
			Class.forName(DBDRIVER);
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			connection = DriverManager.getConnection(DBURL,DBUSER,DBPW);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		System.out.println(connection);
	}

	public String findPassword(User user) throws SQLException {
		Mysql.tid = user.getId();
		Mysql.tpass = user.getSecurityAnswer();
		stmt = connection.createStatement();
		String sql2 = "SELECT securityAnswer FROM user WHERE studyNumberId ='"+Mysql.tid+"'";
		ResultSet rs = stmt.executeQuery(sql2);
		try {
			while(rs.next()){ 
			 pass = rs.getString("securityAnswer");
			}
		}catch(SQLException ex) {//对搜索数据失败的异常处理
			WarnWindow ww = new WarnWindow();
			ww.registIncomplete();
		}
		pass1 = Mysql.tpass ;
		if(pass1.equals(pass)) {
			String sql = "SELECT password FROM user WHERE studyNumberId ='"+Mysql.tid+"'";
			ResultSet rs2 = stmt.executeQuery(sql);
			while(rs2.next()){ 
				pass = rs2.getString("password");
			}
			return pass;
		}else return pass;
	}
	public boolean loginMatch(String tid,String tpass)throws SQLException,Exception {
		//开始查询
		Mysql.tid = tid;
		Mysql.tpass = tpass;
		stmt = connection.createStatement();
		String sql = "SELECT password FROM user WHERE studyNumberId ='"+Mysql.tid+"'";
		ResultSet rs = stmt.executeQuery(sql);
		while(rs.next()){ 
			pass = rs.getString("password");
		}
		pass1 = Mysql.tpass ;
		if(pass1.equals(pass)) return true;
		else return false;
	}
	public boolean idCheck(String tid)throws SQLException,Exception {
		//开始查询
		Mysql.tid = tid;
		String id = null;
		stmt = connection.createStatement();
		String sql = "SELECT studyNumberId FROM user WHERE studyNumberId ='"+Mysql.tid+"'";
		ResultSet rs = stmt.executeQuery(sql);
		while(rs.next()){ 
			id =rs.getString("studyNumberId");
		}
		System.out.println(id);
		System.out.println(tid);
		if(tid.equals(id)) return false;
		else return true;
	}
	/*
	public void spider(Content ct) {
		connectSQL();
		String insertSql = "insert into blogInfo "+"value(?,?,?)";
		try {
			PreparedStatement psm = connection.prepareStatement(insertSql);
			psm.setString(1, ct.getTitle());
			psm.setString(2, ct.getid());
			psm.setString(3, ct.getDate());
			psm.execute();
			psm.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}*/
	public void closeConnection() {
		  try { 
			  connection.close(); } 
		  catch (SQLException e) { e.printStackTrace(); }
		 
	}
}