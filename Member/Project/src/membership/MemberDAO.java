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
	
	
	ArrayList<MemberDTO> getMemberList(Connection con){//멤버 리스트
		
		ArrayList<MemberDTO> list = null;
		
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "select * from member";
		
		try {
			
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			
			list = new ArrayList<>();
			
			while(rs.next()) {
				list.add(new MemberDTO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),rs.getString(5)));
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
	
	
	int inserMemberDTO(Connection con, MemberDTO mem) {//회원가입
	    
		int result = 0;
		
		PreparedStatement pstmt = null;
		
	    try {
	    	String sql = "INSERT INTO MEMBER VALUES (member_idx_seq.nextval, ?, ?, ?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mem.getId());
			pstmt.setString(2, mem.getPassword());
			pstmt.setString(3, mem.getName());			
			pstmt.setString(4, mem.getPhonenum());
			pstmt.setString(5, mem.getEmail());
			
			
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
	int updateMemberDTO(Connection conn, MemberDTO m) {//업데이트

		int result = 0;
		PreparedStatement pstmt = null;


		try {

			String sql = "UPDATE MEMBER SET ID = ?, pw = ?, NAME = ?, PHONENUM = ? WHERE Email = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, m.getId());
			pstmt.setString(2, m.getPassword());
			pstmt.setString(3, m.getName());
			pstmt.setString(4, m.getPhonenum());
			pstmt.setString(5, m.getEmail());
			
			
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
		int delMemberDTO(Connection conn, int idx) {
			
			int result = 0;
			
			PreparedStatement pstmt = null;
			
			try {
				String sql = "DELETE FROM MEMBER where idx = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, idx);
				
				result = pstmt.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				
				if(pstmt != null) {
					try {
						pstmt.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
			return result;
		}
}
