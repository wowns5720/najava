package membership;

public class MemberDTO {

	private int idx;
	private String id;
	private String password;
	private String name;
	private String email;
	private String phonenum;
	
	
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhonenum() {
		return phonenum;
	}
	public void setPhonenum(String phonenum) {
		this.phonenum = phonenum;
	}
	
	public MemberDTO(int idx, String password, String name, String email, String phonenum,String id ) {
		super();
		this.idx = idx;
		this.id = id;
		this.password = password;
		this.name = name;
		this.email = email;
		this.phonenum = phonenum;
		
	}
	@Override
	public String toString() {
		return "MemberDTO [idx=" + idx + ", id=" + id + ", password=" + password + ", name=" + name + ", email=" + email
				+ ", phonenum=" + phonenum + "]";
	}
	

}
