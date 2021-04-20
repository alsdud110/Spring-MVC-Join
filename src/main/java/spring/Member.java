package spring;

import java.time.LocalDateTime;

public class Member {

	private String id;
	private String pw;
	private String name;
	private String tel;
	private String birth;

	public Member() {}
	public Member(String id, String pw, String name, String tel, String birth, String addr_road, String addr_detail,
			String email, LocalDateTime regdate) {
		super();
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.tel = tel;
		this.birth = birth;
		this.addr_road = addr_road;
		this.addr_detail = addr_detail;
		this.email = email;
		this.regdate = regdate;
	}

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getAddr_road() {
		return addr_road;
	}

	public void setAddr_road(String addr_road) {
		this.addr_road = addr_road;
	}

	public String getAddr_detail() {
		return addr_detail;
	}

	public void setAddr_detail(String addr_detail) {
		this.addr_detail = addr_detail;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDateTime getRegdate() {
		return regdate;
	}

	public void setRegdate(LocalDateTime regdate) {
		this.regdate = regdate;
	}

	private String addr_road;
	private String addr_detail;
	private String email;
	private LocalDateTime regdate;


	

	public void changePassword(String oldPassword, String newPassword) {
		if (!pw.equals(oldPassword))
			throw new WrongIdPasswordException();
		this.pw = newPassword;
	}

	public boolean matchPassword(String password) {
		return this.pw.equals(password);
	}


}
