package membership;

public class MemberDTO {

	private int idx;
	private String id;
	private String password;
	private String name;
	private String phonenum;
	private String email;
	
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
	public String getPhonenum() {
		return phonenum;
	}
	public void setPhonenum(String phonenum) {
		this.phonenum = phonenum;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "MemberDTO [idx=" + idx + ", id=" + id + ", password=" + password + ", name=" + name + ", phonenum="
				+ phonenum + ", email=" + email + "]";
	}
	public MemberDTO(String id, String password, String name, String phonenum, String email) {
		this.id = id;
		this.password = password;
		this.name = name;
		this.phonenum = phonenum;
		this.email = email;
	}
	
	
	
	
}
