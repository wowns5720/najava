package membership;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class MemberManager {

	
	private MemberDAO dao;
	
	public MemberManager(MemberDAO mem) {
		this.dao = mem;
	}

	String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";
	String user = "hr";
	String pw = "tiger";
	
	void MemberList() {//회원 정보리스트
		Connection con = null;
		
		try {
			con = DriverManager.getConnection(jdbcUrl, user, pw);
			List<MemberDTO> list = dao.getMemberList(con);
			
			System.out.println("회원 정보 리스트");
			System.out.println("--------------------");
			System.out.println("회원번호\t id\t pw\t 이름\t 번호\t 메일");
			System.out.println("--------------------");
			
			for(MemberDTO md : list) {
				System.out.printf("%s\t %s\t %s\t %s\t %s \n",
									md.getId(),
									md.getPassword(),
									md.getName(),
									md.getPhonenum(),
									md.getEmail());
									
									
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}
	
		Scanner sc = new Scanner(System.in);
		List<MemberDTO> m = new ArrayList<>();
		
		void MemberJoin() {//회원 가입
			Connection con = null;
			try {
				con = DriverManager.getConnection(jdbcUrl, user, pw);
				System.out.println("회원가입 시작합니다.");
				sc.nextLine();
				//int idx = getintInput("idx : ");
				String id = getStrInput("id : ");
				String password = getStrInput("password : ");
				String password2 = getStrInput("password confirm : ");
				String name = getStrInput("name : ");
				String phonenum = getStrInput("phonenum : ");
				String email = getStrInput("email : ");
				
			
				if(idCheck(id)) {
					System.out.println("중복된 id입니다.");
				} else if (password.equals(password2)) {
					MemberDTO mem = new MemberDTO(id , password, name,phonenum, email );
					m.add(mem);
					int result = dao.inserMemberDTO(con, mem);
					
					if(result > 0) {
						System.out.println("입력되었습니다.");
					} else {
						System.out.println("입력 실패하였습니다.");
					}
					System.out.println(id + "님 가입을 축하드립니다.");
				} else {
					System.out.println("비밀번호를 확인해주세요.");
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
	
	}
		private void Login() {// 로그인
			sc.nextLine();
			String id = getStrInput("id : ");
			String pw = getStrInput("pw : ");
			
			MemberDTO member = FindID(id);
			if(member == null) {
				System.out.println("등록되지 않은 ID입니다.");
			} else if(member.getId().equals(pw)) {
				System.out.println("[" + member.getId() + "]님께서 로그인 하셨습니다.");
			} else {
				System.out.println("비밀번호가 틀렸습니다.");
			}
		}
		
		void MemberUpdate() {// 회원 정보 수정
			Connection con = null;
			try {
				
				con = DriverManager.getConnection(jdbcUrl, user, pw);
				
				System.out.printf("수정할 아이디 입력: ");
				String id = sc.next();
				System.out.printf("수정할 비밀번호 입력: ");
				String pw = sc.next();
				System.out.printf("수정할 이름 입력 : ");
				String name = sc.next();
				System.out.printf("수정할 폰번호 입력 : ");
				String phonenum = sc.next();
				System.out.printf("수정할 이메일 입력 : ");
				String email = sc.next();
				
				
				MemberDTO m = new MemberDTO(id, pw, name, phonenum, email);
				ArrayList<MemberDTO> arr = new ArrayList<MemberDTO>();
				arr.add(m);
				
				System.out.println(m + "건이 실행되었습니다.");
			}

			catch (Exception e) { 
			}
		}
			
			
		void MemberDrop() {//회원탈퇴
			
			Connection conn = null;
			
			try {
				conn = DriverManager.getConnection(jdbcUrl, user, pw);
				MemberList();
				System.out.println("삭제를 원하시는 회원번호를 입력해주세요.");
				int idx = sc.nextInt();
				int result = dao.delMemberDTO(conn, idx);
				
				if(result > 0) {
					System.out.println("삭제되었습니다.");
				} else {
					System.out.println("삭제실패! 해당 회원번호의 정보가 없습니다.");
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		public void run() {
			
			int key = 0;
			while((key = menu()) != 0) {
				switch(key) {
				case 1:
					Login();
					break;
				case 2:
					MemberJoin();
					break;
				case 3:
					MemberList();
					break;
				case 4:
					MemberUpdate();
					break;
				case 5:
					MemberDrop();
					break;
				}
			}
		}
		
		
		private MemberDTO FindID(String id) {
			for(MemberDTO memberDTO : m) {
				if(memberDTO.getId().equals(id)) {
					return memberDTO;
				}
			}
			return null;
		}

		private boolean idCheck(String id) {
			boolean check = true;
			MemberDTO member = FindID(id);
			if(member == null) {
				check = false;
				return check;
			}
			return check;
		}

		private String getStrInput(String msg) {
			System.out.println(msg);
			return sc.nextLine();
		}
		
		private int getNumInput(String msg) {
			System.out.println(msg);
			return sc.nextInt();
		}
		private int getintInput(String msg) {
			System.out.println(msg);
			int result = sc.nextInt();
			sc.nextLine();
			return result;
		}
		private int menu() {
			System.out.println("환영합니다.");
			return getNumInput("1.로그인 2.회원가입 3.회원 정보 보기 4.회원 수정 5.회원 탈퇴 0.종료");
					
			
		}
	
}
