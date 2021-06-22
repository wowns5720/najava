package membership;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MemberManager {

	private MemberDAO dao;
	
	public MemberManager(MemberDAO mem) {
		this.dao = mem;
	}
	
	String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";
	String user = "hr";
	String pw = "tiger";
	
	void MemberList() {
		Connection con = null;
		
		try {
			con = DriverManager.getConnection(jdbcUrl, user, pw);
			
			
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
}
