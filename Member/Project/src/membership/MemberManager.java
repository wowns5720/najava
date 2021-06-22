package membership;

import java.sql.Connection;
import java.sql.DriverManager;
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
	
	void MemberList() {
		Connection con = null;
		
		try {
			con = DriverManager.getConnection(jdbcUrl, user, pw);
			List<MemberDTO> list = dao.getMemberList(con);
			
			System.out.println("ȸ�� ���� ����Ʈ");
			System.out.println("--------------------");
			System.out.println("ȸ����ȣ\t id\t pw\t �̸�\t ��ȣ\t ����");
			System.out.println("--------------------");
			
			for(MemberDTO md : list) {
				System.out.printf("%s\t %s\t %s\t %s\t %s\t %s \n",
									md.getIdx(),
									md.getId(),
									md.getPassword(),
									md.getName(),
									md.getEmail(),
									md.getPhonenum());
									
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}
	
	void InputData() {
		Connection con = null;
		try {
			con = DriverManager.getConnection(jdbcUrl, user, pw);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}	
		
		Scanner sc = new Scanner(System.in);
		List<MemberDTO> m = new ArrayList<>();
		
		void MemberJoin() {// idx����� �������� �ذ�
			Connection con = null;
			try {
				con = DriverManager.getConnection(jdbcUrl, user, pw);
				System.out.println("ȸ������ �����մϴ�.");
				sc.nextLine();
				int idx = getintInput("idx : ");
				String id = getStrInput("id : ");
				System.out.println();
				String password = getStrInput("password : ");
				String password2 = getStrInput("password confirm : ");
				String name = getStrInput("name : ");
				String email = getStrInput("email : ");
				String phonenum = getStrInput("phonenum : ");
				
			
				if(idCheck(id)) {
					System.out.println("�ߺ��� id�Դϴ�.");
				} else if (password.equals(password2)) {
					MemberDTO mem = new MemberDTO(idx, id , password, name, email, phonenum);
					m.add(mem);
					int result = dao.inserMemberDTO(con, mem);
					
					if(result > 0) {
						System.out.println("�ԷµǾ����ϴ�.");
					} else {
						System.out.println("�Է� �����Ͽ����ϴ�.");
					}
					System.out.println(id + "�� ������ ���ϵ帳�ϴ�.");
				} else {
					System.out.println("��й�ȣ�� Ȯ�����ּ���.");
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
	
	}
		private void Login() {
			sc.nextLine();
			String id = getStrInput("id : ");
			String pw = getStrInput("pw : ");
			
			MemberDTO member = FindID(id);
			if(member == null) {
				System.out.println("��ϵ��� ���� ID�Դϴ�.");
			} else if(member.getId().equals(pw)) {
				System.out.println("[" + member.getId() + "]�Բ��� �α��� �ϼ̽��ϴ�.");
			} else {
				System.out.println("��й�ȣ�� Ʋ�Ƚ��ϴ�.");
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
			return sc.nextInt();
		}
		private int menu() {
			System.out.println("ȯ���մϴ�.");
			return getNumInput("1.�α��� 2.ȸ������ 3.ȸ�� ���� ���� 0.����");
					
		}
	
}


