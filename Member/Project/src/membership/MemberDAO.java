package membership;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MemberDAO {
	
	private MemberDAO() {
	}
	
	static private MemberDAO dao = new MemberDAO();
	public static MemberDAO getInstance() {
		return dao;
	}
	
	
	ArrayList<MemberDTO> getMemberList(Connection con){
		
		ArrayList<MemberDTO> list = null;
		
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "select * from member";
		
		try {
			
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			
			list = new ArrayList<>();
			
			while(rs.next()) {
				list.add(new MemberDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),rs.getString(6)));
			}
			
		} catch (SQLException e) {

			e.printStackTrace();
		
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		if(stmt != null) {

			try {
				stmt.close();
			
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
	
	
	int inserMemberDTO(Connection con, MemberDTO mem) {
	    
		int result = 0;
		
		PreparedStatement pstmt = null;
		
	    try {
	    	String sql = "INSERT INTO MEMBER VALUES (?, ?, ?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, mem.getIdx());
			pstmt.setString(2, mem.getId());
			pstmt.setString(3, mem.getPassword());
			pstmt.setString(4, mem.getName());
			pstmt.setString(5, mem.getPhonenum());
			pstmt.setString(6, mem.getEmail());
			
			
			result = pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return result;
		
	}
}
