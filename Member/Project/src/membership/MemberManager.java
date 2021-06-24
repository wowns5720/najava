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
	
	void MemberList() {//ȸ�� ��������Ʈ
		Connection con = null;
		
		try {
			con = DriverManager.getConnection(jdbcUrl, user, pw);
			List<MemberDTO> list = dao.getMemberList(con);
			
			System.out.println("ȸ�� ���� ����Ʈ");
			System.out.println("--------------------");
			System.out.println("ȸ����ȣ\t id\t pw\t �̸�\t ��ȣ\t ����");
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
		
		void MemberJoin() {//ȸ�� ����
			Connection con = null;
			try {
				con = DriverManager.getConnection(jdbcUrl, user, pw);
				System.out.println("ȸ������ �����մϴ�.");
				sc.nextLine();
				//int idx = getintInput("idx : ");
				String id = getStrInput("id : ");
				String password = getStrInput("password : ");
				String password2 = getStrInput("password confirm : ");
				String name = getStrInput("name : ");
				String phonenum = getStrInput("phonenum : ");
				String email = getStrInput("email : ");
				
			
				if(idCheck(id)) {
					System.out.println("�ߺ��� id�Դϴ�.");
				} else if (password.equals(password2)) {
					MemberDTO mem = new MemberDTO(id , password, name,phonenum, email );
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
		private void Login() {// �α���
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
		
		void MemberUpdate() {// ȸ�� ���� ����
			Connection con = null;
			try {
				
				con = DriverManager.getConnection(jdbcUrl, user, pw);
				
				System.out.printf("������ ���̵� �Է�: ");
				String id = sc.next();
				System.out.printf("������ ��й�ȣ �Է�: ");
				String pw = sc.next();
				System.out.printf("������ �̸� �Է� : ");
				String name = sc.next();
				System.out.printf("������ ����ȣ �Է� : ");
				String phonenum = sc.next();
				System.out.printf("������ �̸��� �Է� : ");
				String email = sc.next();
				
				
				MemberDTO m = new MemberDTO(id, pw, name, phonenum, email);
				ArrayList<MemberDTO> arr = new ArrayList<MemberDTO>();
				arr.add(m);
				
				System.out.println(m + "���� ����Ǿ����ϴ�.");
			}

			catch (Exception e) { 
			}
		}
			
			
		void MemberDrop() {//ȸ��Ż��
			
			Connection conn = null;
			
			try {
				conn = DriverManager.getConnection(jdbcUrl, user, pw);
				MemberList();
				System.out.println("������ ���Ͻô� ȸ����ȣ�� �Է����ּ���.");
				int idx = sc.nextInt();
				int result = dao.delMemberDTO(conn, idx);
				
				if(result > 0) {
					System.out.println("�����Ǿ����ϴ�.");
				} else {
					System.out.println("��������! �ش� ȸ����ȣ�� ������ �����ϴ�.");
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
			System.out.println("ȯ���մϴ�.");
			return getNumInput("1.�α��� 2.ȸ������ 3.ȸ�� ���� ���� 4.ȸ�� ���� 5.ȸ�� Ż�� 0.����");
					
			
		}
	
}
