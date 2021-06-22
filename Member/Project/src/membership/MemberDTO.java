package membership;

public class MemberDTO {

	private String id;
	private String password;
	private String name;
	private String email;
	private String phonenum;
	private String idx;
	
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
	public String getIdx() {
		return idx;
	}
	public void setIdx(String idx) {
		this.idx = idx;
	}
	public MemberDTO(String id, String password, String name, String email, String phonenum, String idx) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
		this.email = email;
		this.phonenum = phonenum;
		this.idx = idx;
	}
	

}
